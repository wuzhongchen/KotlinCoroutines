import java.util.*;

/**
 * @author ningchuanqi
 * @description
 */
public class FunctionnalTest {

    public static void main(String[] args) {
        List<String> employees = Arrays.asList("Jack", "Jason", "Tommy");
        List<String> shirtSize = Arrays.asList("large", "x-large", "medium");
        Map<String, String> employeeShirtSizes = new HashMap<>();
        for (int i = 0; i < employees.size(); i++) {
            employeeShirtSizes.put(employees.get(i), shirtSize.get(i));
        }


        List<String> formattedList = new ArrayList<>();
        for (Map.Entry<String, String> entry : employeeShirtSizes.entrySet()) {
            formattedList.add(String.format("%s, shirt size:%s", entry.getKey(), entry.getValue()));
        }
        System.out.println(formattedList);

    }

}
