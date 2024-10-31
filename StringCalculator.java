import java.util.ArrayList;
import java.util.List;

public class StringCalculator {
    public int add(String numbers) throws IllegalArgumentException {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter = ",|\n"; // Default delimiter is comma or newline
        String numString = numbers;

        // Check for custom delimiter
        if (numbers.startsWith("//")) {
            String[] parts = numbers.split("\n", 2);
            delimiter = parts[0].substring(2); // Get custom delimiter
            numString = parts[1];
        }

        String[] numArray = numString.split(delimiter);
        List<Integer> numbersList = new ArrayList<>();

        for (String num : numArray) {
            int number = Integer.parseInt(num.trim());
            checkForNegatives(number);
            numbersList.add(number);
        }

        return numbersList.stream().mapToInt(Integer::intValue).sum();
    }

    private void checkForNegatives(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("negative numbers not allowed " + number);
        }
    }

    public static void main(String[] args) {
        StringCalculator calculator = new StringCalculator();

        // Test cases
        System.out.println(calculator.add("")); // Output: 0
        System.out.println(calculator.add("1")); // Output: 1
        System.out.println(calculator.add("1,5")); // Output: 6
        System.out.println(calculator.add("1\n2,3")); // Output: 6
        System.out.println(calculator.add("//;\n1;2")); // Output: 3
        
        try {
            System.out.println(calculator.add("-1")); // Should throw an exception
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); // Output: negative numbers not allowed -1
        }
        
        try {
            System.out.println(calculator.add("1,-2,3,-4")); // Should throw an exception
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage()); // Output: negative numbers not allowed -2
        }
    }
}
