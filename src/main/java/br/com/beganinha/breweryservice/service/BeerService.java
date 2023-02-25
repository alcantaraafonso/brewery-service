package br.com.beganinha.breweryservice.service;

import br.com.beganinha.breweryservice.web.model.BeerDto;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface BeerService {
   BeerDto getBeerById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeerById(UUID beerId, BeerDto beerDto);

    void deleteBeerById(UUID beerId);
}
