package ch.hearc.ig.guideresto.application;

import ch.hearc.ig.guideresto.persistence.FakeItems;
import ch.hearc.ig.guideresto.persistence.mappers.*;
import ch.hearc.ig.guideresto.persistence.services.CityService;
import ch.hearc.ig.guideresto.persistence.services.EvaluationCriteriaService;
import ch.hearc.ig.guideresto.persistence.services.RestaurantService;
import ch.hearc.ig.guideresto.persistence.services.RestaurantTypesService;
import ch.hearc.ig.guideresto.presentation.CLI;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //var test = new BasicEvaluationMapper().getAll();
        var test = new BasicEvaluationMapper().getWhere("FK_REST", "1");

        var scanner = new Scanner(System.in);

        var fakeItems = new FakeItems();

        var cityService = new CityService(new CityMapper());
        var restaurantTypeService = new RestaurantTypesService(new RestaurantTypeMapper());
        var restaurantService = new RestaurantService(
                new RestaurantMapper()
        );
        var evaluationCriteriaService = new EvaluationCriteriaService(new EvaluationCriteriaMapper());

        var printStream = System.out;
        var cli = new CLI(
                scanner,
                printStream,
                fakeItems,
                restaurantService,
                cityService,
                restaurantTypeService,
                evaluationCriteriaService);

        cli.start();
    }
}
