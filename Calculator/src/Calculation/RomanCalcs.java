package Calculation;

import Operations.Addition;
import Operations.Division;
import Operations.Multiplication;
import Operations.Subtraction;

public class RomanCalcs extends Calculations {

    private static final int[] cases = {100, 90, 80, 70, 60, 50, 40, 30, 20, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};


    @Override
    public String addition(String[] s) {
        return convertationToRome(new Addition().execute(convertationToArabic(s[0]), convertationToArabic(s[1])));
    }

    @Override
    public String subtraction(String[] s) {
        return convertationToRome(new Subtraction().execute(convertationToArabic(s[0]), convertationToArabic(s[1])));
    }

    @Override
    public String multiplication(String[] s) {
        return convertationToRome(new Multiplication().execute(convertationToArabic(s[0]), convertationToArabic(s[1])));
    }

    @Override
    public String division(String[] s) {
        return convertationToRome(new Division().execute(convertationToArabic(s[0]), convertationToArabic(s[1])));
    }

    private int convertationToArabic(String input) {

        switch (input) {
            case "I" -> {
                return 1;
            }
            case "II" -> {
                return 2;
            }
            case "III" -> {
                return 3;
            }
            case "IV" -> {
                return 4;
            }
            case "V" -> {
                return 5;
            }
            case "VI" -> {
                return 6;
            }
            case "VII" -> {
                return 7;
            }
            case "VIII" -> {
                return 8;
            }
            case "IX" -> {
                return 9;
            }
            case "X" -> {
                return 10;
            }
        }
        return 0;
    }

    private String convertationToRome(int input) {
        if (input <= 0) {
            return "-1";
        }
        StringBuilder output = new StringBuilder();

        for (int i : cases) {
            if (input >= i) {
                switch (i) {
                    case 1 -> output.append("I");
                    case 2 -> output.append("II");
                    case 3 -> output.append("III");
                    case 4 -> output.append("IV");
                    case 5 -> output.append("V");
                    case 6 -> output.append("VI");
                    case 7 -> output.append("VII");
                    case 8 -> output.append("VIII");
                    case 9 -> output.append("IX");
                    case 10 -> output.append("X");
                    case 20 -> output.append("XX");
                    case 30 -> output.append("XXX");
                    case 40 -> output.append("XL");
                    case 50 -> output.append("L");
                    case 60 -> output.append("LX");
                    case 70 -> output.append("LXX");
                    case 80 -> output.append("LXXX");
                    case 90 -> output.append("XC");
                    case 100 -> output.append("C");
                }
                input -= i;
            }
        }
        return output.toString();
    }
}
