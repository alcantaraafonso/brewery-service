package br.com.beganinha.breweryservice.service;

import br.com.beganinha.breweryservice.domain.Beer;
import br.com.beganinha.breweryservice.repository.BeerRepository;
import br.com.beganinha.breweryservice.web.controller.NotFoundException;
import br.com.beganinha.breweryservice.web.mapper.BeerMapper;
import br.com.beganinha.breweryservice.web.model.BeerDto;
import br.com.beganinha.breweryservice.web.model.BeerPagedList;
import br.com.beganinha.breweryservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository repository;

    private final BeerMapper beerMapper;

    public BeerServiceImpl(BeerRepository repository, BeerMapper beerMapper) {
        this.repository = repository;
        this.beerMapper = beerMapper;
    }

    @Override
    public BeerDto getBeerById(UUID beerId) {
        return beerMapper.toBeerDto(repository.findById(beerId).orElseThrow(() -> new NotFoundException()));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        Beer beer = beerMapper.toBeer(beerDto);
        Beer beerReturned = repository.save(beer);
        return beerMapper.toBeerDto(beerReturned);
    }

    @Override
    public BeerDto updateBeerById(UUID beerId, BeerDto beerDto) {
        Beer beer = beerMapper.toBeer(beerDto);
        beer.setId(beerId);
        Beer beerReturned = repository.save(beer);
        return beerMapper.toBeerDto(beerReturned);
    }

    @Override
    public void deleteBeerById(UUID beerId) {
        repository.deleteById(beerId);
    }

    @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false ")
    @Override
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand) {
        BeerPagedList beerPagedList;
        Page<Beer> beerPage;

        if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            //search both
            beerPage = repository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
            //search beer_service name
            beerPage = repository.findAllByBeerName(beerName, pageRequest);
        } else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            //search beer_service style
            beerPage = repository.findAllByBeerStyle(beerStyle, pageRequest);
        } else {
            beerPage = repository.findAll(pageRequest);
        }

        beerPagedList = new BeerPagedList(beerPage
                .getContent()
                .stream()
                .map(beerMapper::toBeerDto)
                .collect(Collectors.toList()),
                PageRequest
                        .of(beerPage.getPageable().getPageNumber(),
                                beerPage.getPageable().getPageSize()),
                beerPage.getTotalElements());

        return beerPagedList;
    }
}
