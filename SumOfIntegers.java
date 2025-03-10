package PBLJ;

import java.util.ArrayList;
import java.util.List;

public class SumOfIntegers {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(Integer.parseInt("10"));
        numbers.add(Integer.parseInt("20"));
        numbers.add(Integer.parseInt("30"));

        int sum = calculateSum(numbers);
        System.out.println("The sum of the list is: " + sum);
    }

    public static int calculateSum(List<Integer> numbers) {
        int sum = 0;
        for (Integer number : numbers) {
            sum += number; // Unboxing happens here
        }
        return sum;
    }
}
