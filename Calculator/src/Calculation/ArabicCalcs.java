package Calculation;

import Operations.Addition;
import Operations.Division;
import Operations.Multiplication;
import Operations.Subtraction;

public class ArabicCalcs extends Calculations {
    @Override
    public String addition(String[] s) {
        return String.valueOf(new Addition().execute(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
    }

    @Override
    public String subtraction(String[] s) {
        return String.valueOf(new Subtraction().execute(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
    }

    @Override
    public String multiplication(String[] s) {
        return String.valueOf(new Multiplication().execute(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
    }

    @Override
    public String division(String[] s) {
        return String.valueOf(new Division().execute(Integer.parseInt(s[0]), Integer.parseInt(s[1])));
    }
}
