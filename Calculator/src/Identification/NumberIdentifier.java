package Identification;

public class NumberIdentifier {

    public static boolean isArabicNumber(String number) {
        return number.replaceAll("^(10|[0-9])$", "").length() == 0;
    }

    public static boolean isRomanNumber(String number) {
        return number.replaceAll("^[IVXivx]+$", "").length() == 0;
    }

}
