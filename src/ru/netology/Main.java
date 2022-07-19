package ru.netology;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Queue<Integer> queue = new LinkedList<>();
        String input = "";

        while (!input.equals("0")) {
            try {
                System.out.println("Ожидаю ввода этажа: (для завершения введите 0)");
                input = scanner.nextLine();
                int floorNumber = Integer.parseInt(input);
                if (floorNumber >= 0 && floorNumber <= 25) {
                    if (queue.offer(floorNumber)) {
                        System.out.println("Этаж добавлен!\n");
                    } else {
                        System.out.println("Что-то пошло не так...\n");
                    }
                } else {
                    System.out.println("Такого этажа нет в доме!\n");
                }
            } catch (Exception e) {
                System.out.println("Номер этажа не может быть '" + input + "' !\n");
            }
        }

        int waitDoorsInSeconds = 10; // количество секунд, которое потребуется на закрытие/открытие дверей;
        int waitMoveInSeconds = 5; // количество секунд, затрачиваемое лифтом на движение между этажами;
        int totalSeconds = 0; // всего потрачено времени на движение и остановки лифтом;
        int previousFloor = -1; // переменная для хранения предыдущей остановки

        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {

            int floorNumber = queue.poll();

            sb.append("этаж ");
            sb.append(floorNumber);

            if (floorNumber != 0) {
                sb.append(" -> ");
            }
            if (previousFloor != -1) {
                totalSeconds += Math.abs(floorNumber - previousFloor) * waitMoveInSeconds;
            }

            totalSeconds += waitDoorsInSeconds;
            previousFloor = floorNumber;
        }

        String allFloorsRoute = sb.toString();
        System.out.println("Маршрут лифта: " + allFloorsRoute);
        System.out.println("Время затраченное лифтом на маршрут : " + totalSeconds + " с.");

    }
}
