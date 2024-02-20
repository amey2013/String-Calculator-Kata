package org.example;

public class StringCalculator {

    public static String add(String numbers) {
        if (numbers.isEmpty()) {
            return "0";
        }

        String delimiter = ",|\\n";
        String[] parts = numbers.split("\n", 2);

        if (parts[0].startsWith("//")) {
            delimiter = parts[0].substring(2).replace("|", "\\|");
            numbers = parts[1];
        }

        String[] numberArray = numbers.split(delimiter);

        float sum = 0;
        int pos = 0;
        String negativeNumbers = "";

        for (String value : numberArray) {
            if (value.isEmpty()) {
                return "Number expected but '\\n' found at position " + pos + ".";
            }

            try {
                float num = Float.parseFloat(value);

                if (num < 0) {
                    if (!negativeNumbers.isEmpty()) {
                        negativeNumbers += " ,";
                    }
                    negativeNumbers += num;
                }

                sum += num;
                pos += value.length() + 1;
            } catch (NumberFormatException e) {
                return "'" + delimiter.replace("\\", "") + "' expected but '" + value.charAt(1) + "' found at position "
                        + (pos + 1) + ".";
            }
        }

        if (numbers.endsWith(",")) {
            return "Number expected but EOF found.";
        }

        if (!negativeNumbers.isEmpty()) {
            return "Negative not allowed : " + negativeNumbers;
        }

        if (sum == (int) sum) {
            return String.valueOf((int) sum);
        } else {
            return String.format("%.1f", sum);
        }
    }
    public static boolean multipleErrorChecking(String example) {
        String regex = "^[-+]?\\d+$";

        String[] multipleErrorSplit = example.split(",");

        boolean isError = false;
        for (int i = 0; i < multipleErrorSplit.length; i++) {
            if (multipleErrorSplit[i].matches(regex) && Integer.parseInt(multipleErrorSplit[i]) < 0) {
                System.out.println("Negative not allowed here: " + Integer.parseInt(multipleErrorSplit[i]));
                isError = true;
            } else if (multipleErrorSplit[i].isEmpty()) {
                System.out.println("Number expected at position: " + i + " but found this: ,");
                isError = true;
            } else {
                System.out.println("Number expected at position: " + i + " but found this: " + multipleErrorSplit[i]);
                isError = true;
            }
        }

        return isError;
    }

    public static void main(String[] args) {

        System.out.println(add(""));
        System.out.println(add("1,2,3"));
        System.out.println(add("//;\n1;2"));
        System.out.println(add("//|\n1|2|3"));
        System.out.println(add("//sep\n2sep3"));
        System.out.println(add("//|\n1|2,3"));
        System.out.println(add("1"));
        System.out.println(add("1.1,2.2"));
        System.out.println(add("1\n2,3"));
        System.out.println(add("175.2,\n35"));
        System.out.println(add("1,3,"));
        System.out.println(add("-1,2"));
        System.out.println(add("2,-4,-5"));
        System.out.println(add(""));
        boolean errorResult = multipleErrorChecking("-1,,-2");
        if(errorResult){
            System.out.println(errorResult);
        }

    }
}