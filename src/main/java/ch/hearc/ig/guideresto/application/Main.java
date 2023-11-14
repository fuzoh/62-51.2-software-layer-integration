package ch.hearc.ig.guideresto.application;

import ch.hearc.ig.guideresto.persistence.FakeItems;
import ch.hearc.ig.guideresto.persistence.mappers.RestaurantMapper;
import ch.hearc.ig.guideresto.persistence.services.RestaurantService;
import ch.hearc.ig.guideresto.presentation.CLI;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        var scanner = new Scanner(System.in);

        var fakeItems = new FakeItems();

        var restaurantService = new RestaurantService(new RestaurantMapper());

        var printStream = System.out;
        var cli = new CLI(scanner, printStream, fakeItems, restaurantService);

        cli.start();
    }
}
