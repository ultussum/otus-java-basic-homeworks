package ru.otus.java.basic.homeworks.homework13;

import java.util.List;

public class Operations {
    public static final String PLUS = "+";
    public static final String MINUS = "-";
    public static final String MULTIPLICATION = "*";
    public static final String DIVISION = "/";
    public static final String LEFT_BRACKET = "(";
    public static final String RIGHT_BRACKET = ")";

    public static void checkAndExecution(List<String> example) {
        if (example.size() <= 1) {
            try {
                int result = Integer.parseInt(String.join(" ", example));
            } catch (NumberFormatException e) {
                example.set(0, "Ошибка: Пример введен без пробелов");
            }
            return;
        }
        if (findBrackets(example)) {
            checkAndExecution(example);
            return;
        }
        if (findBrackets(example)) {
            checkAndExecution(example);
            return;
        }
        if (orderCalculationOperator(example, MULTIPLICATION, DIVISION) ||
                orderCalculationOperator(example, PLUS, MINUS)) {
            checkAndExecution(example); // Рекурсия после операции
        }

    }

    private static boolean orderCalculationOperator(List<String> example, String op1, String op2) {
        for (int i = 0; i < example.size(); i++) {
            String operator = example.get(i);
            if (operator.equals(op1) || operator.equals(op2)) {
                try {
                    int leftValue = Integer.parseInt(example.get(i - 1));
                    int rightValue = Integer.parseInt(example.get(i + 1));

                    int result = 0;
                    switch (operator) {
                        case PLUS:
                            result = leftValue + rightValue;
                            break;
                        case MINUS:
                            result = leftValue - rightValue;
                            break;
                        case MULTIPLICATION:
                            result = leftValue * rightValue;
                            break;
                        case DIVISION:
                            if (rightValue == 0) throw new ArithmeticException("На ноль делить нельзя");
                            result = leftValue / rightValue;
                            break;
                    }
                    example.set(i - 1, Integer.toString(result));
                    example.remove(i);
                    example.remove(i);
                    return true;
                } catch (ArithmeticException e) {
                    example.set(0, "Ошибка: " + e.getMessage());
                    return false;
                } catch (NumberFormatException e) {
                    example.set(0, "Ошибка: Некорректные данные");
                }
            }
        }
        return false;
    }

    private static boolean findBrackets(List<String> example) {
        int startBracket = 0;
        int endBracket = 0;
        for (int i = 0; i < example.size(); i++) {
            if (example.get(i).equals(LEFT_BRACKET)) {
                startBracket = i + 1;
            }
            if (example.get(i).equals(RIGHT_BRACKET)) {
                endBracket = i;
                List<String> subBrackets = example.subList(startBracket, endBracket);
                checkAndExecution(subBrackets);
                example.set(startBracket - 1, subBrackets.get(0));
                example.subList(startBracket, startBracket + 2).clear();
                return true;
            }
        }
        if (startBracket>endBracket){
            example.set(0, "Ошибка: Пропущена скобка");
            return false;
        }
        return false;
    }
}



