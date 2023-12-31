package ch.hearc.ig.guideresto.application;

import ch.hearc.ig.guideresto.persistence.services.CityService;
import ch.hearc.ig.guideresto.persistence.services.EvaluationCriteriaService;
import ch.hearc.ig.guideresto.persistence.services.RestaurantService;
import ch.hearc.ig.guideresto.persistence.services.RestaurantTypesService;
import ch.hearc.ig.guideresto.presentation.CLI;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        var scanner = new Scanner(System.in);

        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("guide-resto-persistence")) {
            var cityService = new CityService(emf);
            var restaurantTypeService = new RestaurantTypesService(emf);
            var restaurantService = new RestaurantService(
                    emf
            );
            var evaluationCriteriaService = new EvaluationCriteriaService(emf);

            var printStream = System.out;
            var cli = new CLI(
                    scanner,
                    printStream,
                    /*fakeItems,*/
                    restaurantService,
                    cityService,
                    restaurantTypeService,
                    evaluationCriteriaService);

            cli.start();
        }

    }
}
