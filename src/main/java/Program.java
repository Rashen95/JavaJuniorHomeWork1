import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Program {
    public static void main(String[] args) {
        // 1. Создать список из 1_000 рандомных чисел от 1 до 1_000_000
        ArrayList<Integer> randomIntList = randomIntListGenerate();

        // 1.1 Найти максимальное
        System.out.println(findMaxInList(randomIntList));

        // 1.2 Все числа, большие, чем 500_000, умножить на 5, отнять от них 150 и просуммировать
        System.out.println(changeIntOver500000(randomIntList));

        // 1.3 Найти количество чисел, квадрат которых меньше, чем 100_000
        System.out.println(findIntSquareLess100_000(randomIntList));

        // 2. Создать класс Employee (Сотрудник) с полями: String name, int age, double salary, String department
        // 2.1 Создать список из 10-20 сотрудников
        ArrayList<Employee> listEmployee = new ArrayList<>(List.of(
                new Employee("Сергей", 28, 5000, "IT"),
                new Employee("Анатолий", 30, 150000, "IT"),
                new Employee("Петр", 35, 155000, "IT"),
                new Employee("Артем", 40, 20000, "IT"),
                new Employee("Анжелика", 50, 19000, "HR"),
                new Employee("Анастасия", 52, 28000, "HR"),
                new Employee("Дмитрий", 20, 37000, "HR"),
                new Employee("Елена", 18, 48000, "HR"),
                new Employee("Георгий", 26, 9000, "Cleaning"),
                new Employee("Дмитрий", 38, 111000, "Cleaning"),
                new Employee("Григорий", 59, 5000, "Cleaning"),
                new Employee("Татьяна", 66, 8000, "Cleaning"),
                new Employee("Владимир", 34, 79000, "Legal"),
                new Employee("Алексей", 47, 48000, "Legal"),
                new Employee("Сергей", 45, 160000, "Legal")
        ));

        // 2.2 Вывести список всех различных отделов (department) по списку сотрудников
        System.out.println(departmentListOf(listEmployee));

        // 2.3 Всем сотрудникам, чья зарплата меньше 10_000, повысить зарплату на 20%
        salaryUP(listEmployee, 10_000, 20);

        // 2.4 * Из списка сотрудников с помощью стрима создать Map<String, List<Employee>> с отделами и сотрудниками внутри отдела
        System.out.println(distributionByDepartments(listEmployee));

        // 2.5 * Из списока сорудников с помощью стрима создать Map<String, Double> с отделами и средней зарплатой внутри отдела
        System.out.println(avgSalaryInDepartment(listEmployee));

    }

    public static ArrayList<Integer> randomIntListGenerate() {
        return Stream.generate(() -> new Random().nextInt(1, 1_000_001))
                .limit(1000)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static int findMaxInList(ArrayList<Integer> list) {
        AtomicInteger max = new AtomicInteger(Integer.MIN_VALUE);
        list.forEach(it -> {
            if (it > max.get()) {
                max.set(it);
            }
        });
        return max.get();
    }

    public static int changeIntOver500000(ArrayList<Integer> list) {
        AtomicInteger sum = new AtomicInteger();
        list.forEach(it -> {
            if (it > 500_000) {
                int a = it * 5 - 150;
                sum.addAndGet(a);
            }
        });
        return sum.get();
    }

    public static int findIntSquareLess100_000(ArrayList<Integer> list) {
        AtomicInteger count = new AtomicInteger();
        list.forEach(it -> {
            if (Math.pow(it, 2) < 100_000) {
                count.getAndIncrement();
            }
        });
        return count.get();
    }

    public static ArrayList<String> departmentListOf(ArrayList<Employee> list) {
        return list.stream()
                .map(Employee::getDepartment)
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public static void salaryUP(ArrayList<Employee> list, double below, int percent) {
        list.forEach(it -> {
            if (it.getSalary() < below) {
                it.setSalary(it.getSalary() * (1 + (double) percent / 100));
            }
        });
    }

    public static Map<String, List<Employee>> distributionByDepartments(ArrayList<Employee> list) {
        return list.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    public static Map<String, Double> avgSalaryInDepartment(ArrayList<Employee> list) {
        return list.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
    }
}


