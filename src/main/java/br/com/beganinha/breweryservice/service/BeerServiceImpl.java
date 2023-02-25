package br.com.beganinha.breweryservice.service;

import br.com.beganinha.breweryservice.domain.Beer;
import br.com.beganinha.breweryservice.repository.BeerRepository;
import br.com.beganinha.breweryservice.web.controller.NotFoundException;
import br.com.beganinha.breweryservice.web.mapper.BeerMapper;
import br.com.beganinha.breweryservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository repository;

    private final BeerMapper beerMapper;

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
}
