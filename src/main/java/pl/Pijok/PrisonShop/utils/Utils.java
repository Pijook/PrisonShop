package pl.Pijok.PrisonShop.utils;

public class Utils {

    public static boolean isInteger(final String a) {
        try {
            Integer.parseInt(a);
            return false;
        }
        catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }
}
