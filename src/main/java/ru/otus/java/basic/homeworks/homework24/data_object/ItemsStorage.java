package ru.otus.java.basic.homeworks.homework24.data_object;


import java.util.*;


public class ItemsStorage {
    private static List<Item> items;

    public static void init() {
        items = new ArrayList<>(Arrays.asList(
                new Item(1L, "Milk", 80),
                new Item(2L, "Bread", 40),
                new Item(3L, "Cheese", 400)
        ));
    }

    public static List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    public static void createItem(Item item) {
        item.setId(items.stream().mapToLong(Item::getId).max().orElse(0) + 1);
        items.add(item);
    }

    public static void deleteItem(Long id) {
        items.removeIf(item -> item.getId().equals(id));
    }

    public static Item searchItem(Long id){
        return items.stream().filter(item -> item.getId().equals(id)).findFirst().orElse(null);
    }
}
