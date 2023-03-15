package com.motrechko.taxservice.utils;

import com.motrechko.taxservice.model.Inspector;

import java.util.Random;

public class InspectorFactory {
    private static final String[] FIRST_NAMES = {"John", "Mary", "William", "Elizabeth", "James", "Sarah", "Thomas", "Margaret", "George", "Anne"};
    private static final String[] LAST_NAMES = {"Smith", "Johnson", "Brown", "Taylor", "Anderson", "Wilson", "Jones", "Clark", "Wright", "Walker"};
    private static final String[] EMAIL_DOMAINS = {"gmail.com", "yahoo.com", "hotmail.com", "outlook.com", "aol.com"};

    public static Inspector createRandomInspector() {
        Random rand = new Random();
        String firstName = FIRST_NAMES[rand.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[rand.nextInt(LAST_NAMES.length)];
        String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + EMAIL_DOMAINS[rand.nextInt(EMAIL_DOMAINS.length)];
        String password = "password" + rand.nextInt(1000);

        Inspector inspector = new Inspector();
        inspector.setFirstName(firstName);
        inspector.setLastName(lastName);
        inspector.setEmail(email);
        inspector.setPassword(password);
        return inspector;
    }


}
