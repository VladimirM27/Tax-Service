package com.motrechko.taxservice.utils;

import com.motrechko.taxservice.model.User;

import java.util.Random;

public class UserFactory {
    private static final Random random = new Random();

    public static User createRandomUser() {
        User user = new User();
        user.setId(random.nextInt(Integer.MAX_VALUE));
        user.setEmail(generateRandomEmail());
        user.setPassword(generateRandomPassword());
        user.setEntity(generateRandomEntity());
        user.setRole(generateRandomRole());
        user.setFirstName(generateRandomFirstName());
        user.setLastName(generateRandomLastName());
        user.setCompany(generateRandomCompany());
        user.setTIN(generateRandomTIN());
        user.setCity(generateRandomCity());
        user.setStreet(generateRandomStreet());
        user.setNumberOfBuilding(generateRandomNumberOfBuilding());
        return user;
    }

    private static String generateRandomEmail() {
        return "user" + random.nextInt(1000) + "@example.com";
    }

    private static String generateRandomPassword() {
        return "password" + random.nextInt(1000);
    }

    private static String generateRandomEntity() {
        return random.nextBoolean() ? "Individual" : "Company";
    }

    private static String generateRandomRole() {
        return random.nextBoolean() ? "user" : "inspector";
    }

    private static String generateRandomFirstName() {
        String[] firstNames = {"John", "Jane", "Bob", "Alice", "Tom", "Emily"};
        return firstNames[random.nextInt(firstNames.length)];
    }

    private static String generateRandomLastName() {
        String[] lastNames = {"Doe", "Smith", "Johnson", "Brown", "Lee", "Taylor"};
        return lastNames[random.nextInt(lastNames.length)];
    }

    private static String generateRandomCompany() {
        String[] companies = {"Google", "Microsoft", "Apple", "Amazon", "Facebook", "Oracle"};
        return companies[random.nextInt(companies.length)];
    }

    private static long generateRandomTIN() {
        return  random.nextInt(900000000);
    }

    private static String generateRandomCity() {
        String[] cities = {"New York", "Los Angeles", "Chicago", "Houston", "Phoenix", "Philadelphia"};
        return cities[random.nextInt(cities.length)];
    }

    private static String generateRandomStreet() {
        String[] streets = {"Main St", "Broadway", "Market St", "Washington St", "Park Ave"};
        return streets[random.nextInt(streets.length)];
    }

    private static String generateRandomNumberOfBuilding() {
        return "" + (random.nextInt(1000) + 1);
    }
}
