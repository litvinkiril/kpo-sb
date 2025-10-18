package ru.moscowzoo.app;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;
import ru.moscowzoo.clinic.InspectionReport;
import ru.moscowzoo.domain.animal.Animal;
import ru.moscowzoo.domain.animal.Monkey;
import ru.moscowzoo.domain.animal.Rabbit;
import ru.moscowzoo.domain.animal.Tiger;
import ru.moscowzoo.domain.animal.Wolf;
import ru.moscowzoo.domain.thing.Computer;
import ru.moscowzoo.domain.thing.Table;
import ru.moscowzoo.domain.thing.Thing;
import ru.moscowzoo.io.ConsoleGateway;
import ru.moscowzoo.zoo.Zoo;

/**
 * Console workflow providing textual user interface.
 */
public final class ZooConsole {
    private final ConsoleGateway console;
    private final Zoo zoo;
    private final AtomicInteger inventorySequence = new AtomicInteger(1000);

    public ZooConsole(ConsoleGateway console, Zoo zoo) {
        this.console = console;
        this.zoo = zoo;
    }

    public void run() {
        console.println("Добро пожаловать в Московский зоопарк!");
        boolean running = true;
        while (running) {
            printMenu();
            String input = console.readLine();
            if (input == null) {
                break;
            }
            try {
                switch (input.trim()) {
                    case "1" -> admitAnimal();
                    case "2" -> addThing();
                    case "3" -> printFoodReport();
                    case "4" -> printContactZooList();
                    case "5" -> printInventory();
                    case "0" -> running = false;
                    default -> console.println("Неизвестная команда");
                }
            } catch (IllegalStateException interrupted) {
                console.println("Ввод прерван: " + interrupted.getMessage());
                running = false;
            }
        }
        console.println("Завершение работы.");
    }

    private void printMenu() {
        console.println("");
        console.println("1. Принять животное");
        console.println("2. Добавить инвентарный предмет");
        console.println("3. Показать суммарный расход еды");
        console.println("4. Показать животных контактного зоопарка");
        console.println("5. Показать инвентаризацию");
        console.println("0. Выход");
        console.println("Введите команду:");
    }

    private void admitAnimal() {
        console.println("Выберите животное (monkey, rabbit, tiger, wolf):");
        String type = console.readLine();
        if (type == null) {
            throw new IllegalStateException("оператор отменил ввод");
        }
        String nickname = ask("Кличка:");
        int food = askInt("Килограммы еды в сутки:");
        Animal animal = switch (type.trim().toLowerCase(Locale.ROOT)) {
            case "monkey" -> new Monkey(nickname, food, nextNumber(), askInt("Уровень доброты (0-10):"));
            case "rabbit" -> new Rabbit(nickname, food, nextNumber(), askInt("Уровень доброты (0-10):"));
            case "tiger" -> new Tiger(nickname, food, nextNumber());
            case "wolf" -> new Wolf(nickname, food, nextNumber());
            default -> null;
        };
        if (animal == null) {
            console.println("Неизвестный тип животного");
            return;
        }
        InspectionReport report = zoo.admitAnimal(animal);
        console.println(report.getMessage());
        if (report.isAccepted()) {
            console.println("Животное принято в зоопарк.");
        } else {
            console.println("Животное отклонено.");
        }
    }

    private void addThing() {
        console.println("Выберите предмет (table, computer, other):");
        String type = console.readLine();
        if (type == null) {
            throw new IllegalStateException("оператор отменил ввод");
        }
        String name = ask("Название предмета:");
        Thing thing = switch (type.trim().toLowerCase(Locale.ROOT)) {
            case "table" -> new Table(name, nextNumber());
            case "computer" -> new Computer(name, nextNumber());
            default -> new Thing(name, nextNumber());
        };
        zoo.registerThing(thing);
        console.println("Предмет зарегистрирован.");
    }

    private void printFoodReport() {
        console.println("Всего животных: " + zoo.getAnimals().size());
        console.println("Суммарный расход еды в сутки (кг): " + zoo.totalFoodPerDay());
    }

    private void printContactZooList() {
        var list = zoo.contactZooCandidates();
        if (list.isEmpty()) {
            console.println("Нет животных для контактного зоопарка.");
            return;
        }
        console.println("Животные для контактного зоопарка:");
        list.forEach(animal -> console.println("- " + animal.getInventoryName() + ", доброта: " + animal.getKindness()));
    }

    private void printInventory() {
        console.println("Инвентарные объекты зоопарка:");
        zoo.getInventory().forEach(item -> console.println(item.getInventoryNumber() + ": " + item.getInventoryName()));
    }

    private String ask(String prompt) {
        console.println(prompt);
        String answer = console.readLine();
        if (answer == null) {
            throw new IllegalStateException("оператор отменил ввод");
        }
        return answer;
    }

    private int askInt(String prompt) {
        while (true) {
            String input = ask(prompt);
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException ex) {
                console.println("Введите целое число.");
            }
        }
    }

    private int nextNumber() {
        return inventorySequence.incrementAndGet();
    }
}
