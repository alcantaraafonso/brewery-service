package br.com.beganinha.breweryservice.bootstrap;

import br.com.beganinha.breweryservice.domain.Beer;
import br.com.beganinha.breweryservice.repository.BeerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@Slf4j
/**
 * This class is used to load data into the H2 Database
 */
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository repository;

    public BeerLoader(BeerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if(repository.count() == 0) {
            repository.save(Beer.builder()
                    .beerName("Mango Bobs")
                    .beerStyle("IPA")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(337010000001L)
                    .price(new BigDecimal("12.95"))
                    .build());

            repository.save(Beer.builder()
                    .beerName("Galaxy Cat")
                    .beerStyle("PALE_ALE")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(337010000002L)
                    .price(new BigDecimal("11.95"))
                    .build());
        }

        log.info("Loaded beers: " + repository.count());
        repository.findAll().forEach(b -> log.info("Beers' IDs: " + b.getId()));
    }
}
