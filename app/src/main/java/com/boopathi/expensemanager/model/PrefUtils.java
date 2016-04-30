package com.boopathi.expensemanager.model;

/**
 * Created by Boopathi on 4/30/2016.
 */

import android.content.Context;

import com.boopathi.expensemanager.adapter.ComplexPreferences;

public class PrefUtils {

    public static void setCurrentUser(User currentUser, Context ctx) {
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "user_prefs", 0);
        complexPreferences.putObject("current_user_value", currentUser);
        complexPreferences.commit();
    }

    public static User getCurrentUser(Context ctx) {
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "user_prefs", 0);
        User currentUser = complexPreferences.getObject("current_user_value", User.class);
        return currentUser;
    }

    public static void clearCurrentUser(Context ctx) {
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, "user_prefs", 0);
        complexPreferences.clear();
        complexPreferences.commit();
    }


}