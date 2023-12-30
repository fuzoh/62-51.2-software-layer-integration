package ch.hearc.ig.guideresto.application;

import ch.hearc.ig.guideresto.business.City;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        var scanner = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("guide-resto-persistence");
        EntityManager em = emf.createEntityManager();

        City test = em.find(City.class, 1);
        System.out.println(test);
        // var fakeItems = new FakeItems();

        // Start services
//        var cityService = new CityService(new CityMapper());
//        var restaurantTypeService = new RestaurantTypesService(new RestaurantTypeMapper());
//        var restaurantService = new RestaurantService(
//                new RestaurantMapper()
//        );
//        var evaluationCriteriaService = new EvaluationCriteriaService(new EvaluationCriteriaMapper());
//
//        var printStream = System.out;
//        var cli = new CLI(
//                scanner,
//                printStream,
//                /*fakeItems,*/
//                restaurantService,
//                cityService,
//                restaurantTypeService,
//                evaluationCriteriaService);
//
//        cli.start();
    }
}
