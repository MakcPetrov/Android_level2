package k113.dweather;

import android.util.Log;

class BackEnd {//вся логика программы, данные и обработка их - здесь, в бэкэнде
    private static final String TAG = "fLC";
    private static boolean debugLog = true;
    private static float localTerm=-273;

    static void isLog(String send){if (debugLog) Log.i(TAG,send);}

    private static String this_city="          MyCity";
    static String getCity() {
        return this_city;
    }
    static void setCity(String city) {
        BackEnd.this_city = city;
    }
    static float getLtms() { return localTerm; }

    static boolean isDebug() { return debugLog; }

    static void debugLog(boolean debug) {
        BackEnd.debugLog = debug;
        Log.i(TAG,"Debug is "+((debug) ? "on":"off"));
    }

    static void setLtms(float ltms) {//тупо меняем температуру ручками, потому что датчиков в эмуляторе нет
        isLog("new term sens= "+ltms);
        BackEnd.localTerm = ltms;    }

}//BackEnd


