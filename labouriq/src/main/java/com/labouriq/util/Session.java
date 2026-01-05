package com.labouriq.util;

import com.labouriq.model.User;

public class Session {

    private static User currentUser;

    public static void start(User user) {
        currentUser = user;
    }

    public static User getUser() {
        return currentUser;
    }

    public static int getUserId() {
        if (currentUser == null)
            throw new IllegalStateException("User not logged in");
        return currentUser.getId();
    }

    public static String getRole() {
        return currentUser != null ? currentUser.getRole() : null;
    }

    public static boolean isLoggedIn() {
        return currentUser != null;
    }

    public static void logout() {
        currentUser = null;
    }
}
