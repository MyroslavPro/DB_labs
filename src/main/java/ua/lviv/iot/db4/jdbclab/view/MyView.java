package ua.lviv.iot.db4.jdbclab.view;

import ua.lviv.iot.db4.jdbclab.controllers.ClientController;
import ua.lviv.iot.db4.jdbclab.controllers.CountryController;
import ua.lviv.iot.db4.jdbclab.controllers.GuideController;
import ua.lviv.iot.db4.jdbclab.controllers.LanguageController;
import ua.lviv.iot.db4.jdbclab.controllers.LocationController;
import ua.lviv.iot.db4.jdbclab.controllers.PhoneController;
import ua.lviv.iot.db4.jdbclab.controllers.TourController;
import ua.lviv.iot.db4.jdbclab.controllers.TripController;
import ua.lviv.iot.db4.jdbclab.models.Client;
import ua.lviv.iot.db4.jdbclab.models.Country;
import ua.lviv.iot.db4.jdbclab.models.Guide;
import ua.lviv.iot.db4.jdbclab.models.Language;
import ua.lviv.iot.db4.jdbclab.models.Location;
import ua.lviv.iot.db4.jdbclab.models.Phone;
import ua.lviv.iot.db4.jdbclab.models.Tour;
import ua.lviv.iot.db4.jdbclab.models.Trip;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.sql.Date;


@Component
public class MyView {

    @Autowired
    private CountryController countryController;
    @Autowired
    private ClientController clientController;
    @Autowired
    private GuideController guideController;
    @Autowired
    private LanguageController languageController;
    @Autowired
    private LocationController locationController;
    @Autowired
    private PhoneController phoneController;
    @Autowired
    private TourController tourController;
    @Autowired
    private TripController tripController;

    private final Map<String, String> menu;
    private final Map<String, Printable> methodsMenu;
    private final Scanner input = new Scanner(System.in);
    private final Country nullCountry = new Country(null, null);
    private final Phone nullPhone = new Phone(null, null);
    private final Language nullLanguage = new Language(null, null);
    private final Location nullLocation = new Location(null, null, null);
    private final Client nullClient = new Client(null, null, null, null, null);
    private final Guide nullGuide = new Guide(null, null, null, null, null, null);
    private final Tour nullTour = new Tour(null, null, null,null,null,null,null);
    private final Trip nullTrip = new Trip(null, null, null, null, null);

    public MyView() {
        menu = new LinkedHashMap<>();
        menu.put("A", "  A - Select all table");

        menu.put("1", "   1 - Table: Country");
        menu.put("11", "  11 - Create Country");
        menu.put("12", "  12 - Update Country");
        menu.put("13", "  13 - Delete from Country");
        menu.put("14", "  14 - Find all Countries");
        menu.put("15", "  15 - Find Country by ID");
        menu.put("16", "  16 - Find Country by сountry_name");
        
        menu.put("2", "   2 - Table: Client");
        menu.put("21", "  21 - Create Client");
        menu.put("22", "  22 - Update Client");
        menu.put("23", "  23 - Delete from Client");
        menu.put("24", "  24 - Find all Clients");
        menu.put("25", "  25 - Find Client by ID");
        menu.put("26", "  26 - Find Client by client_name");
        
        menu.put("3", "   3 - Table: Guide");
        menu.put("31", "  31 - Create Guide");
        menu.put("32", "  32 - Update Guide");
        menu.put("33", "  33 - Delete from Guide");
        menu.put("34", "  34 - Find all Guides");
        menu.put("35", "  35 - Find Guide by ID");
        menu.put("36", "  36 - Find Guide by guide_name");
        menu.put("37", "  37 - Find Guide by guide_surname");
        menu.put("38", "  38 - Find Guide by guide_email");
        
        menu.put("4", "   4 - Table: Location");
        menu.put("41", "  41 - Create Location");
        menu.put("42", "  42 - Update Location");
        menu.put("43", "  43 - Delete from Location");
        menu.put("44", "  44 - Find all Locations");
        menu.put("45", "  45 - Find Location by ID");
        
        menu.put("5", "   5 - Table: Phone");
        menu.put("51", "  51 - Create Phone");
        menu.put("52", "  52 - Update Phone");
        menu.put("53", "  53 - Delete from Phone");
        menu.put("54", "  54 - Find all Phones");
        menu.put("55", "  55 - Find Phone by ID");
        
        menu.put("6", "   6 - Table: Language");
        menu.put("61", "  61 - Create Language");
        menu.put("62", "  62 - Update Language");
        menu.put("63", "  63 - Delete from Language");
        menu.put("64", "  64 - Find all Languages");
        menu.put("65", "  65 - Find Language by ID");
        menu.put("66", "  66 - Find Language by language_name");
        
        menu.put("7", "   7 - Table: Tour");
        menu.put("71", "  71 - Create Tour");
        menu.put("72", "  72 - Update Tour");
        menu.put("73", "  73 - Delete from Tour");
        menu.put("74", "  74 - Find all Tours");
        menu.put("75", "  75 - Find Tour by ID");
        menu.put("76", "  76 - Find Tour by tour_name");
        menu.put("77", "  77 - Find all tours that are cheaper then proposed value");
        
        menu.put("8", "   8 - Table: Trip");
        menu.put("81", "  81 - Create Trip");
        menu.put("82", "  82 - Update Trip");
        menu.put("83", "  83 - Delete from Trip");
        menu.put("84", "  84 - Find all Trips");
        menu.put("85", "  85 - Find Trip by ID");
        menu.put("86", "  86 - Find Trip by trip date");
        
        menu.put("Q", "  Q - exit");

        methodsMenu = new LinkedHashMap<>();
        methodsMenu.put("A", this::selectAllTable);

        methodsMenu.put("11", this::createCountry);
        methodsMenu.put("12", this::updateCountry);
        methodsMenu.put("13", this::deleteFromCountry);
        methodsMenu.put("14", this::findAllCountries);
        methodsMenu.put("15", this::findCountryById);
        methodsMenu.put("16", this::findCountryByCountryName);
        
        methodsMenu.put("21", this::createClient);
        methodsMenu.put("22", this::updateClient);
        methodsMenu.put("23", this::deleteFromClient);
        methodsMenu.put("24", this::findAllClients);
        methodsMenu.put("25", this::findClientById);
        methodsMenu.put("26", this::findClientByClientName);
        
        methodsMenu.put("31", this::createGuide);
        methodsMenu.put("32", this::updateGuide);
        methodsMenu.put("33", this::deleteFromGuide);
        methodsMenu.put("34", this::findAllGuides);
        methodsMenu.put("35", this::findGuideById);
        methodsMenu.put("36", this::findGuideByGuideName);
        methodsMenu.put("37", this::findGuideByGuideSurname);
        methodsMenu.put("38", this::findGuideByGuideEmail);
        
        methodsMenu.put("41", this::createLocation);
        methodsMenu.put("42", this::updateLocation);
        methodsMenu.put("43", this::deleteFromLocation);
        methodsMenu.put("44", this::findAllLocations);
        methodsMenu.put("45", this::findLocationById);
        
        methodsMenu.put("51", this::createPhone);
        methodsMenu.put("52", this::updatePhone);
        methodsMenu.put("53", this::deleteFromPhone);
        methodsMenu.put("54", this::findAllPhones);
        methodsMenu.put("55", this::findPhoneById);

        methodsMenu.put("61", this::createLanguage);
        methodsMenu.put("62", this::updateLanguage);
        methodsMenu.put("63", this::deleteFromLanguage);
        methodsMenu.put("64", this::findAllLanguages);
        methodsMenu.put("65", this::findLanguageById);
        methodsMenu.put("66", this::findLanguageByLanguageName);
        
        methodsMenu.put("71", this::createTour);
        methodsMenu.put("72", this::updateTour);
        methodsMenu.put("73", this::deleteFromTour);
        methodsMenu.put("74", this::findAllTours);
        methodsMenu.put("75", this::findTourById);
        methodsMenu.put("76", this::findTourByTourName);
        methodsMenu.put("77", this::getAllOfTheToursThatCostLowerThanGivePrice);
        
        methodsMenu.put("81", this::createTrip);
        methodsMenu.put("82", this::updateTrip);
        methodsMenu.put("83", this::deleteFromTrip);
        methodsMenu.put("84", this::findAllTrips);
        methodsMenu.put("85", this::findTripById);
        methodsMenu.put("86", this::findTripByTripDate);
    }

    private void selectAllTable() {
        findAllCountries();
        findAllClients();
        findAllGuides();
        findAllLocations();
        findAllPhones();
        findAllLanguages();
        findAllTours();
        findAllTrips();
    }
    
    
    // -------------------- Country ---------------------------------------------------
    
    private void createCountry() {
        System.out.println("Input 'country_name': ");
        String countryName = input.nextLine();
        Country country = new Country(null, countryName);

        int count = countryController.create(country);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateCountry() {
        System.out.println("Input for update 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        String countryName = input.nextLine();
        Country country = new Country(null, countryName);

        int count = countryController.update(id, country);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromCountry() {
        System.out.println("Input for delete 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = countryController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllCountries() {
        System.out.println("\nTable: Country");
        List<Country> countrys = countryController.findAll();
        for (Country country : countrys) {
            System.out.println(country);
        }
    }

    private void findCountryById() {
        System.out.println("Input for find 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Country> country = countryController.findById(id);
        System.out.println(country.orElse(nullCountry));
    }

    private void findCountryByCountryName() {
        System.out.println("Input 'country_name': ");
        String countryName = input.nextLine();

        Optional<Country> country = countryController.findByCountryName(countryName);
        System.out.println(country.orElse(nullCountry));
    }

    
    
    // ---------------------------- Client ---------------------------------------------------
    
    private void createClient() {
        System.out.println("Input 'client_name': ");
        String clientName = input.nextLine();
        System.out.println("Input his 'country_id': ");
        Integer countryId = Integer.valueOf((input.nextLine()));
        System.out.println("Input his 'language_id': ");
        Integer languageId = Integer.valueOf((input.nextLine()));
        System.out.println("Input his 'phone_id': ");
        Integer phoneId = Integer.valueOf((input.nextLine()));
        Client client = new Client(null, clientName, countryId,languageId, phoneId);

        int count = clientController.create(client);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateClient() {
        System.out.println("Input for update 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));
        System.out.println("Input 'client_name': ");
        String clientName = input.nextLine();
        System.out.println("Input his 'country_id': ");
        Integer countryId = Integer.valueOf((input.nextLine()));
        System.out.println("Input his 'language_id': ");
        Integer languageId = Integer.valueOf((input.nextLine()));
        System.out.println("Input his 'client_name': ");
        Integer phoneId = Integer.valueOf((input.nextLine()));
        Client client = new Client(null, clientName, countryId, languageId, phoneId);
        
        int count = clientController.update(id, client);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromClient() {
        System.out.println("Input for delete 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = clientController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllClients() {
        System.out.println("\nTable: Client");
        List<Client> clients = clientController.findAll();
        for (Client client : clients) {
            System.out.println(client);
        }
    }

    private void findClientById() {
        System.out.println("Input for find 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Client> client = clientController.findById(id);
        System.out.println(client.orElse(nullClient));
    }

    private void findClientByClientName() {
        System.out.println("Input 'client_name': ");
        String clientName = input.nextLine();

        Optional<Client> client = clientController.findByClientName(clientName);
        System.out.println(client.orElse(nullClient));
    }
    
    
    
// ---------------------------- Guide ---------------------------------------------------
    
    private void createGuide() {
        System.out.println("Input 'guide_name': ");
        String guideName = input.nextLine();
        System.out.println("Input 'guide_surname': ");
        String guideSurname= input.nextLine();
        System.out.println("Input 'guide_email': ");
        String guideEmail= input.nextLine();
        System.out.println("Input his 'country_id': ");
        Integer countryId = Integer.valueOf((input.nextLine()));
        System.out.println("Input his 'phone_id': ");
        Integer phoneId = Integer.valueOf((input.nextLine()));
        Guide guide = new Guide(null, guideName, guideSurname, guideEmail, countryId, phoneId);

        int count = guideController.create(guide);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateGuide() {
        System.out.println("Input for update 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));
        System.out.println("Input 'guide_name': ");
        String guideName = input.nextLine();
        System.out.println("Input 'guide_surname': ");
        String guideSurname= input.nextLine();
        System.out.println("Input 'guide_email': ");
        String guideEmail= input.nextLine();
        System.out.println("Input his 'country_id': ");
        Integer countryId = Integer.valueOf((input.nextLine()));
        System.out.println("Input his 'phone_id': ");
        Integer phoneId = Integer.valueOf((input.nextLine()));
        Guide guide = new Guide(null, guideName, guideSurname, guideEmail, countryId, phoneId);
        
        int count = guideController.update(id, guide);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromGuide() {
        System.out.println("Input for delete 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = guideController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllGuides() {
        System.out.println("\nTable: Guide");
        List<Guide> guides = guideController.findAll();
        for (Guide guide : guides) {
            System.out.println(guide);
        }
    }

    private void findGuideById() {
        System.out.println("Input for find 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Guide> guide = guideController.findById(id);
        System.out.println(guide.orElse(nullGuide));
    }

    private void findGuideByGuideName() {
        System.out.println("Input 'Guide_name': ");
        String guideName = input.nextLine();

        Optional<Guide> guide = guideController.findByGuideName(guideName);
        System.out.println(guide.orElse(nullGuide));
    }
    
    private void findGuideByGuideSurname() {
        System.out.println("Input 'Guide_surname': ");
        String guideSurname = input.nextLine();

        Optional<Guide> guide = guideController.findByGuideSurname(guideSurname);
        System.out.println(guide.orElse(nullGuide));
    }
    
    private void findGuideByGuideEmail() {
        System.out.println("Input 'Guide_email': ");
        String guideEmail = input.nextLine();

        Optional<Guide> guide = guideController.findByGuideEmail(guideEmail);
        System.out.println(guide.orElse(nullGuide));
    }
    
    
// -------------------- Location ---------------------------------------------------
    
    private void createLocation() {
        System.out.println("Input 'Location_name': ");
        String locationName = input.nextLine();
        System.out.println("Input 'country_id': ");
        Integer countryId = Integer.valueOf((input.nextLine()));
        
        Location location = new Location(null, locationName, countryId);

        int count = locationController.create(location);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateLocation() {
        System.out.println("Input for update 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input 'Location_name': ");
        String locationName = input.nextLine();
        System.out.println("Input 'country_id': ");
        Integer countryId = Integer.valueOf((input.nextLine()));
        
        Location location = new Location(null, locationName, countryId);

        int count = locationController.update(id, location);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromLocation() {
        System.out.println("Input for delete 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = locationController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllLocations() {
        System.out.println("\nTable: Location");
        List<Location> locations = locationController.findAll();
        for (Location location : locations) {
            System.out.println(location);
        }
    }

    private void findLocationById() {
        System.out.println("Input for find 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Location> location = locationController.findById(id);
        System.out.println(location.orElse(nullLocation));
    }
    
    
// -------------------- Phone ---------------------------------------------------
    
    private void createPhone() {
        System.out.println("Input 'Phone_name': ");
        String phoneName = input.nextLine();
        
        Phone phone = new Phone(null, phoneName);

        int count = phoneController.create(phone);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updatePhone() {
        System.out.println("Input for update 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input 'Phone_name': ");
        String phoneName = input.nextLine();
        
        Phone phone = new Phone(null, phoneName);

        int count = phoneController.update(id, phone);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromPhone() {
        System.out.println("Input for delete 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = phoneController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllPhones() {
        System.out.println("\nTable: Phone");
        List<Phone> phones = phoneController.findAll();
        for (Phone phone : phones) {
            System.out.println(phone);
        }
    }

    private void findPhoneById() {
        System.out.println("Input for find 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Phone> phone = phoneController.findById(id);
        System.out.println(phone.orElse(nullPhone));
    }
    
// -------------------- Language ---------------------------------------------------
    
    private void createLanguage() {
        System.out.println("Input 'language_name': ");
        String languageName = input.nextLine();
        Language language = new Language(null, languageName);

        int count = languageController.create(language);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateLanguage() {
        System.out.println("Input for update 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        String languageName = input.nextLine();
        Language language = new Language(null, languageName);

        int count = languageController.update(id, language);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromLanguage() {
        System.out.println("Input for delete 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = languageController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllLanguages() {
        System.out.println("\nTable: Language");
        List<Language> languages = languageController.findAll();
        for (Language language : languages) {
            System.out.println(language);
        }
    }

    private void findLanguageById() {
        System.out.println("Input for find 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Language> language = languageController.findById(id);
        System.out.println(language.orElse(nullLanguage));
    }

    private void findLanguageByLanguageName() {
        System.out.println("Input 'Language_name': ");
        String languageName = input.nextLine();

        Optional<Language> language = languageController.findByLanguageName(languageName);
        System.out.println(language.orElse(nullLanguage));
    }

    
 // ---------------------------- Tour ---------------------------------------------------
    
    private void createTour() {
        System.out.println("Input Tour name: ");
        String tourName = input.nextLine();
        System.out.println("Input price of the tour: ");
		Double price = Double.parseDouble(input.nextLine());  
        System.out.println("Input it's duration : ");
        Integer tourDays = Integer.valueOf((input.nextLine()));
        System.out.println("Input Tour description: ");
        String tourDescription = input.nextLine();
        System.out.println("Input 'language_id': ");
        Integer languageId = Integer.valueOf((input.nextLine()));
        System.out.println("Input 'location_id': ");
        Integer locationId = Integer.valueOf((input.nextLine()));
        Tour tour = new Tour(null, tourName, price, tourDays, tourDescription, languageId, locationId);

        int count = tourController.create(tour);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateTour() {
        System.out.println("Input for update 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));
        
        System.out.println("Input Tour name: ");
        String tourName = input.nextLine();
        System.out.println("Input price of the tour: ");
		Double price = Double.parseDouble(input.nextLine());  
        System.out.println("Input it's duration : ");
        Integer tourDays = Integer.valueOf((input.nextLine()));
        System.out.println("Input Tour description: ");
        String tourDescription = input.nextLine();
        System.out.println("Input 'language_id': ");
        Integer languageId = Integer.valueOf((input.nextLine()));
        System.out.println("Input 'location_id': ");
        Integer locationId = Integer.valueOf((input.nextLine()));
        Tour tour = new Tour(null, tourName, price, tourDays, tourDescription, languageId, locationId);
        
        int count = tourController.update(id, tour);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromTour() {
        System.out.println("Input for delete 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = tourController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllTours() {
        System.out.println("\nTable: Tour");
        List<Tour> tours = tourController.findAll();
        for (Tour tour : tours) {
            System.out.println(tour);
        }
    }

    private void findTourById() {
        System.out.println("Input for find 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Tour> tour = tourController.findById(id);
        System.out.println(tour.orElse(nullTour));
    }

    private void findTourByTourName() {
        System.out.println("Input 'Tour_name': ");
        String tourName = input.nextLine();

        Optional<Tour> tour = tourController.findByTourName(tourName);
        System.out.println(tour.orElse(nullTour));
    }
    
    private void getAllOfTheToursThatCostLowerThanGivePrice() {
        System.out.println("Input your wished price: ");
        Double price = Double.parseDouble(input.nextLine());
        
        List<Tour> tours = tourController.getAllAvailableTours(price);
        tours.forEach(tour->System.out.println(tour));
    }
    
// -------------------- Trip ---------------------------------------------------
    
    private void createTrip() {
        System.out.println("Input 'tour_id': ");
        Integer tourId = Integer.valueOf((input.nextLine()));
        System.out.println("Input 'guide_id': ");
        Integer guideId = Integer.valueOf((input.nextLine()));
        System.out.println("Input date of the start of the trip: ");
		Date dateStart = Date.valueOf(input.nextLine());
        System.out.println("Input guide's salary: ");
		Double guideSalary = Double.parseDouble(input.nextLine());        
	
        Trip trip = new Trip(null, tourId, guideId, dateStart, guideSalary);

        int count = tripController.create(trip);
        System.out.printf("There are created %d rows\n", count);
    }

    private void updateTrip() {
    	System.out.println("Input for update 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        System.out.println("Input 'tour_id': ");
        Integer tourId = Integer.valueOf((input.nextLine()));
        System.out.println("Input 'guide_id': ");
        Integer guideId = Integer.valueOf((input.nextLine()));
        System.out.println("Input date of the start of the trip: ");
        Date dateStart = Date.valueOf(input.nextLine());
        System.out.println("Input date of the start of the trip: ");
		Double guideSalary = Double.parseDouble(input.nextLine());        
	
        Trip trip = new Trip(null, tourId, guideId, dateStart, guideSalary);

        int count = tripController.update(id, trip);
        System.out.printf("There are updated %d rows\n", count);
    }

    private void deleteFromTrip() {
        System.out.println("Input for delete 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        int count = tripController.delete(id);
        System.out.printf("There are deleted %d rows\n", count);
    }

    private void findAllTrips() {
        System.out.println("\nTable: Trip");
        List<Trip> trips = tripController.findAll();
        for (Trip trip : trips) {
            System.out.println(trip);
        }
    }

    private void findTripById() {
        System.out.println("Input for find 'id': ");
        Integer id = Integer.valueOf((input.nextLine()));

        Optional<Trip> Trip = tripController.findById(id);
        System.out.println(Trip.orElse(nullTrip));
    }

    private void findTripByTripDate() {
        System.out.println("Input 'Trip_name': ");
        Date tripDate = Date.valueOf(input.nextLine());

        Optional<Trip> Trip = tripController.findByTripDate(tripDate);
        System.out.println(Trip.orElse(nullTrip));
    }

    //-------------------------------------------------------------------------------------   
   // Menu
    private void outputMenu() {
        System.out.println("\nMENU:");
        for (String key : menu.keySet())
            if (key.length() == 1) System.out.println(menu.get(key));
    }

    private void outputSubMenu(String fig) {

        System.out.println("\nSubMENU:");
        for (String key : menu.keySet())
            if (key.length() >= 2 && key.substring(0, 1).equals(fig)) System.out.println(menu.get(key));
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
            	if (keyMenu.equals("Q")) {
            		System.out.println("Exit");
            	}
            	else {
            		System.out.println(e);
            	}
            } 
            
        } while (!keyMenu.equals("Q"));        
    }
}

