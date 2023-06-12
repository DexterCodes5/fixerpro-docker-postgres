package dev.dex.fixerprodockerpostgres.util;

import dev.dex.fixerprodockerpostgres.entity.*;

public class Utilities {

    public static String getEngineOilBrand(EngineOil engineOil) {
        String title = engineOil.getTitle();
        String result = String.valueOf(title.charAt(0));
        for (int i = 1; i < title.length(); i++) {
            if (title.charAt(i) == ' ') return result;
            result += String.valueOf(title.charAt(i)).toLowerCase();
        }
        return null;
    }

}
