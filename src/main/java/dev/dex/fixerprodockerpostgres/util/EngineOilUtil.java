package dev.dex.fixerprodockerpostgres.util;

import java.util.*;

public class EngineOilUtil {
    public static String makeJpqlBrands(String[] brands) {
        String jpql = "";
        for (int i = 0; i < brands.length; i++) {
            // For brands I have to use percentage after the brand
            brands[i] += "%";
            if (i > 0) {
                jpql += " OR";
            }
            jpql += " title LIKE ?" + (i + 1);
        }
        return jpql;
    }

    public static String makeJpqlLiters(String[] liters, int startParam) {
        String jpql = "";
        for (int i = 0; i < liters.length; i++) {
            if (i > 0) {
                jpql += " OR";
            }
            jpql += " liters = ?" + (startParam++);
        }
        return jpql;
    }

    public static <T> T[] arrayConcat(T[] arr1, T[] arr2) {
        T[] res = Arrays.copyOf(arr1, arr1.length + arr2.length);
        System.arraycopy(arr2, 0, res, arr1.length, arr2.length);
        return res;
    }
}
