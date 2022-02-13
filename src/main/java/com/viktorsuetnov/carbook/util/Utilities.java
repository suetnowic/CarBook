package com.viktorsuetnov.carbook.util;

import com.viktorsuetnov.carbook.model.Car;
import com.viktorsuetnov.carbook.model.User;

public class Utilities {

    public static double getParseDouble(String value) {
        return Double.parseDouble(value.replace(',', '.'));
    }

    public static boolean isEquals(User currentUser, Car car) {
        return currentUser.getUsername().equals(car.getOwner().getUsername());
    }
}
