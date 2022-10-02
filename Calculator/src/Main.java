import Calculation.ArabicCalcs;
import Calculation.RomanCalcs;
import Identification.NumberIdentifier;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<Integer> hay = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введи выражение = ");

        String input = scanner.nextLine();
        String[] operands = input.split("[+\\-*\\/]");

        if (input.equals("Стоп")) {
            System.exit(0);
        }

        if (input.replaceAll("[IVXivx\\d+\\-*\\/]", "").length() > 0 ||
                operands.length != 2 ||
                (Arrays.stream(operands).anyMatch(NumberIdentifier::isRomanNumber) &&
                        Arrays.stream(operands).anyMatch(NumberIdentifier::isArabicNumber))) {
            System.err.println("Неверный формат чисел");
            main(args);
        }

        if (Arrays.stream(operands).allMatch(NumberIdentifier::isArabicNumber)) {
            switch (input.replaceAll("\\d", "")) {
                case "+" -> System.out.println(new ArabicCalcs().addition(operands));
                case "-" -> System.out.println(new ArabicCalcs().subtraction(operands));
                case "*" -> System.out.println(new ArabicCalcs().multiplication(operands));
                case "/" -> System.out.println(new ArabicCalcs().division(operands));
            }
        } else if (Arrays.stream(operands).allMatch(NumberIdentifier::isRomanNumber)) {
            switch (input.replaceAll("[IVXivx]", "")) {
                case "+" -> System.out.println(new RomanCalcs().addition(operands));
                case "-" -> {
                    if (Integer.parseInt(new RomanCalcs().subtraction(operands)) == -1) {
                        System.out.println("Неположительный результат");
                    } else {
                        System.out.println(new RomanCalcs().subtraction(operands));
                    }
                }
                case "*" -> System.out.println(new RomanCalcs().multiplication(operands));
                case "/" -> System.out.println(new RomanCalcs().division(operands));
            }
        }

        main(args);

    }
}
