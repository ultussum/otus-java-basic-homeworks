package ru.otus.java.basic.homeworks.homework10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    private Map<List<String>, List<String>> phoneBook;

    public PhoneBook() {
        phoneBook = new HashMap<>();
    }

    @Override
    public String toString() {
        return "PhoneBook{" +
                "phoneBook=" + phoneBook +
                '}';
    }

    public void add(List<String> name, String phoneNumber) {
        List<String> phoneNumbers = phoneBook.get(name);
        if (phoneNumbers == null) {
            phoneNumbers = new ArrayList<>();
            phoneBook.put(name, phoneNumbers);
        }
        phoneNumbers.add(phoneNumber);
    }
    public void find(List<String> name){
        List<String> resultSearch = new ArrayList<>();
        String searchLastName = name.get(0);
        for (Map.Entry<List<String>, List<String>> entry : phoneBook.entrySet()) {
            List<String> nameList = entry.getKey();
            List<String> phoneNumbers = entry.getValue();
            if (nameList.get(0).equals(searchLastName) || nameList.equals(name)) {
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
