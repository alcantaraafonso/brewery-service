package br.com.beganinha.breweryservice.web.controller;

import br.com.beganinha.breweryservice.service.BeerService;
import br.com.beganinha.breweryservice.web.model.BeerDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerService;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId) {

        BeerDto beer = beerService.getBeerById(beerId);
        return new ResponseEntity<>(beer, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity saveNewBeer(@Valid @RequestBody BeerDto beerDto) {
        //
        BeerDto beer = beerService.saveNewBeer(beerDto);
        return new ResponseEntity(beer, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable UUID beerId, @Valid @RequestBody BeerDto beerDto) {
        BeerDto beer = beerService.updateBeerById(beerId, beerDto);
        return new ResponseEntity(beer, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    public ResponseEntity deleteBeerById(@PathVariable UUID beerId) {

        beerService.deleteBeerById(beerId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
