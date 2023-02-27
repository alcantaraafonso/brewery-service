package br.com.beganinha.breweryservice.web.mapper;

import br.com.beganinha.breweryservice.domain.Beer;
import br.com.beganinha.breweryservice.web.model.BeerDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class}, componentModel = "spring")
public interface BeerMapper {
    Beer toBeer(BeerDto beerDto);

    BeerDto toBeerDto(Beer beer);

}
