import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 0.1. Посмотреть разные статьи на Хабр.ру про Stream API
 * 0.2. Посмотреть видеоролики на YouTube.com Тагира Валеева про Stream API
 * <p>
 * 1. Создать список из 1_000 рандомных чисел от 1 до 1_000_000
 * 1.1 Найти максимальное
 * 2.2 Все числа, большие, чем 500_000, умножить на 5, отнять от них 150 и просуммировать
 * 2.3 Найти количество чисел, квадрат которых меньше, чем 100_000
 * <p>
 * 2. Создать класс Employee (Сотрудник) с полями: String name, int age, double salary, String department
 * 2.1 Создать список из 10-20 сотрудников
 * 2.2 Вывести список всех различных отделов (department) по списку сотрудников
 * 2.3 Всем сотрудникам, чья зарплата меньше 10_000, повысить зарплату на 20%
 * 2.4 * Из списка сотрудников с помощью стрима создать Map<String, List<Employee>> с отделами и сотрудниками внутри отдела
 * 2.5 * Из списока сорудников с помощью стрима создать Map<String, Double> с отделами и средней зарплатой внутри отдела
 */

public class Program {
    public static void main(String[] args) {
        ArrayList<Integer> randomIntList = randomIntListGenerate();
        System.out.println(findMaxInList(randomIntList));
        System.out.println(changeIntOver500000(randomIntList));


    }

    // 1. Генератор списка
    public static ArrayList<Integer> randomIntListGenerate() {
        return Stream.generate(() -> new Random().nextInt(1, 1_000_001))
                .limit(1000)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    // 2. Поиск максимального числа из ArrayList
    public static int findMaxInList(ArrayList<Integer> list) {
        AtomicInteger max = new AtomicInteger(Integer.MIN_VALUE);
        list.forEach(it -> {
            if (it > max.get()) {
                max.set(it);
            }
        });
        return max.get();
    }

    // 3. Все числа, большие, чем 500_000, умножаем на 5, отнимаем от них 150 и суммируем
    public static int changeIntOver500000(ArrayList<Integer> list) {
        AtomicInteger sum = new AtomicInteger();
        list.forEach(it -> {
            if (it > 500_000) {
                it = it * 5 - 150;
                sum.addAndGet(it);
            }
        });
        return sum.get();
    }
}
