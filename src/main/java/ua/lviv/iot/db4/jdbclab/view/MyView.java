package ua.lviv.iot.db4.jdbclab.view;

import ua.lviv.iot.db4.jdbclab.controllers.CountryController;
import ua.lviv.iot.db4.jdbclab.models.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MyView {

    @Autowired
    private CountryController countryController;

    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);
    private final Country nullCountry = new Country(null, null);

    public MyView() {
        menu = new LinkedHashMap<>();
        menu.put("A", "  A - Select all table");

        menu.put("1", "   1 - Table: Country");
        menu.put("11", "  11 - Create Country");
        menu.put("12", "  12 - Update Country");
        menu.put("13", "  13 - Delete from Country");
        menu.put("14", "  14 - Find all Countries");
        menu.put("15", "  15 - Find Country by ID");
        menu.put("16", "  16 - Find Book by сountry_name");

//        menu.put("2", "   2 - Table: City");
//        menu.put("21", "  21 - Create City");
//        menu.put("22", "  22 - Update City");
//        menu.put("23", "  23 - Delete from City");
//        menu.put("24", "  24 - Find all Cities");
//        menu.put("25", "  25 - Find City by ID");
//
//        menu.put("3", "   3 - Table: Person");
//        menu.put("31", "  31 - Create Person");
//        menu.put("32", "  32 - Update Person");
//        menu.put("33", "  33 - Delete from Person");
//        menu.put("34", "  34 - Find all Persons");
//        menu.put("35", "  35 - Find Person by ID");
//        menu.put("36", "  36 - Find all Books by Person ID");
//        menu.put("37", "  37 - addBookByNameToPersonBySurname");

        menu.put("Q", "  Q - exit");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("A", this::selectAllTable);

        methodsMenu.put("11", this::createCountry);
        methodsMenu.put("12", this::updateCountry);
        methodsMenu.put("13", this::deleteFromCountry);
        methodsMenu.put("14", this::findAllCountrys);
        methodsMenu.put("15", this::findCountryById);
        methodsMenu.put("16", this::findBookByCountryName);

    }

    private void selectAllTable() {
        findAllCountrys();
     //  findAllCities();
     // findAllPersons();
    }

    // region Country ---------------------------------------------------
    private void createCountry() {
        System.out.println("Input 'country_name': ");
        String countryName = input.nextLine();
        Country country = new Country(null, countryName);

        int count = countryController.create(country);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateCountry() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        String countryName = input.nextLine();
        Country country = new Country(null, countryName);

        int count = countryController.update(id, country);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromCountry() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = countryController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllCountrys() {
        System.out.println("\nTable: Country");
        List<Country> countrys = countryController.findAll();
        for (Country country : countrys) {
            System.out.println(country);
        }
    }

    private void findCountryById() {
        System.out.println("Input 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Country> country = countryController.findById(id);
        System.out.println(country.orElse(nullCountry));
    }

    private void findBookByCountryName() {
        System.out.println("Input 'country_name': ");
        String countryName = input.nextLine();

        Optional<Country> country = countryController.findByCountryName(countryName);
        System.out.println(country.orElse(nullCountry));
    }
    //end region

    
    
    //-------------------------------------------------------------------------
    // region output
    private void outputMenu() {
        System.out.println("\nMENU:");
        for (String key : menu.keySet())
            if (key.length() == 1) System.out.println(menu.get(key));
    }

    private void outputSubMenu(String fig) {

        System.out.println("\nSubMENU:");
        for (String key : menu.keySet())
            if (key.length() != 1 && key.substring(0, 1).equals(fig)) System.out.println(menu.get(key));
    }

    public void show() {
        String keyMenu;
        do {
            outputMenu();
            System.out.println("Please, select menu point.");
            keyMenu = input.nextLine().toUpperCase();

            if (keyMenu.matches("^\\d")) {
                outputSubMenu(keyMenu);
                System.out.println("Please, select menu point.");
                keyMenu = input.nextLine().toUpperCase();
            }

            try {
                methodsMenu.get(keyMenu).print();
            } catch (Exception e) {
                System.out.println(e);
            }
        } while (!keyMenu.equals("Q"));
    }

    //end region
}

