public class Main {

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
    }
}