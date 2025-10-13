package ru.otus.java.basic.homeworks.homework10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    private Map<String, List<String>> phoneBook;

    public PhoneBook() {
        phoneBook = new HashMap<>();
    }

    @Override
    public String toString() {
        return "PhoneBook{" +
                "phoneBook=" + phoneBook +
                '}';
    }

    public void add(String name, String phoneNumber) {
        List<String> phoneNumbers = phoneBook.get(name);
        if (phoneNumbers == null) {
            phoneNumbers = new ArrayList<>();
            phoneBook.put(name, phoneNumbers);
        }
        phoneNumbers.add(phoneNumber);
    }
    public void find(String name){
        List<String> resultSearch = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : phoneBook.entrySet()) {
            String nameList = entry.getKey();
            List<String> phoneNumbers = entry.getValue();
            if (nameList.equals(name) || nameList.contains(name)) {
                String fullName = String.join(" ", nameList);
                System.out.println("Найденные номера для " + fullName + ":");
                for (int i = 0; i < phoneNumbers.size(); i++) {
                    System.out.println(phoneNumbers.get(i));
                }
                resultSearch.addAll(phoneNumbers);
            }
        }
        if (resultSearch.isEmpty()){
            System.out.println("Искомых записей нет.");
        }
    }
    public boolean containsPhoneNumber(String phoneNumber){
        for (List<String> numbers : phoneBook.values()){
            if (numbers.contains(phoneNumber)){
                System.out.println("Номер есть в справочнике.");
                return true;
            }
        }
        System.out.println("Номера нет в справочнике.");
        return false;
    }
}
