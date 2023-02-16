package br.com.beganinha.breweryservice.web.controller;

import br.com.beganinha.breweryservice.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId) {

        return new ResponseEntity<>(BeerDto.builder().build(), HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity saveNewBeer(@RequestBody BeerDto beerDto) {
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable UUID beerId, @RequestBody BeerDto beerDto) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    public ResponseEntity deleteBeerById(@PathVariable UUID beerId) {
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
