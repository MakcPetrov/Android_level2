package k113.dweather;

import android.util.Log;

public class BackEnd {
    private static final String TAG = "fLC";
    private static boolean debugLog = true;

    static void isLog(String send){if (debugLog) Log.i(TAG,send);}
    private static String this_city="Noname";

    public static String getCity() {
        return this_city;
    }

    public static void setCity(String city) {
        BackEnd.this_city = city;
    }
}


