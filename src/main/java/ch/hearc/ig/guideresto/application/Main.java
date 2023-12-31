package ch.hearc.ig.guideresto.application;

import ch.hearc.ig.guideresto.persistence.services.*;
import ch.hearc.ig.guideresto.presentation.CLI;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        var scanner = new Scanner(System.in);

        // Create EntityManagerFactory in try with resources, try will automatically close it
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory(
                "guide-resto-persistence")) {

            // The factory is injected into each services
            var cityService = new CityService(emf);
            var restaurantTypeService = new RestaurantTypesService(emf);
            var restaurantService = new RestaurantService(emf);
            var evaluationCriteriaService = new EvaluationCriteriaService(emf);
            var gradeService = new GradeService(emf);

            System.out.println(restaurantService.getAll());

            var printStream = System.out;

            // The original class to manage the user interactions
            var cli = new CLI(scanner, printStream, restaurantService, cityService,
                              restaurantTypeService, evaluationCriteriaService, gradeService
            );

            cli.start();
        }

    }

}
