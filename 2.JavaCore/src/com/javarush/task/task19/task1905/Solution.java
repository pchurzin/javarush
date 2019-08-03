package com.javarush.task.task19.task1905;

import java.util.HashMap;
import java.util.Map;

/* 
Закрепляем адаптер
*/

public class Solution {
    public static Map<String,String> countries = new HashMap<String,String>();
    static {
        countries.put("UA", "Ukraine");
        countries.put("RU", "Russia");
        countries.put("CA", "Canada");
    }

    public static void main(String[] args) {
        String phone = "+38(050)123-45-67";
        phone = phone.replaceAll("[^0-9]", "");
        System.out.println("callto://+" + phone);

        String name = "Ivanov, Ivan";
        System.out.println("fn=" + name.substring(name.indexOf(",") + 1).trim());
        System.out.println("ln=" + name.substring(0, name.indexOf(",")).trim());

    }

    public static class DataAdapter implements RowItem {
        private Customer customer;
        private Contact contact;

        public DataAdapter(Customer customer, Contact contact) {
            this.contact = contact;
            this.customer = customer;
        }

        @Override
        public String getCountryCode() {
            String country = customer.getCountryName();
            for (Map.Entry<String, String> pair : countries.entrySet()) {
                if (country.equals(pair.getValue())) return pair.getKey();
            }
            return null;
        }

        @Override
        public String getCompany() {
            return customer.getCompanyName();
        }

        @Override
        public String getContactFirstName() {
            String name = contact.getName();
            return name.substring(name.indexOf(",") + 1).trim();
        }

        @Override
        public String getContactLastName() {
            String name = contact.getName();
            return name.substring(0, name.indexOf(",")).trim();
        }

        @Override
        public String getDialString() {
            String phone = contact.getPhoneNumber();
            phone = phone.replaceAll("[^0-9]", "");
            return "callto://+" + phone;
        }
    }

    public static interface RowItem {
        String getCountryCode();        //example UA
        String getCompany();            //example JavaRush Ltd.
        String getContactFirstName();   //example Ivan
        String getContactLastName();    //example Ivanov
        String getDialString();         //example callto://+380501234567
    }

    public static interface Customer {
        String getCompanyName();        //example JavaRush Ltd.
        String getCountryName();        //example Ukraine
    }

    public static interface Contact {
        String getName();               //example Ivanov, Ivan
        String getPhoneNumber();        //example +38(050)123-45-67
    }
}