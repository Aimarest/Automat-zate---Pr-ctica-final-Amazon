package com.selenium.utils;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Common {

    public static List<List<WebElement>> getSublist(List<WebElement> list, int number) {
        List<List<WebElement>> spansOfMoney = new ArrayList<>();
        for (int i = 0; i < list.size() -1; i += number) {
            spansOfMoney.add(new ArrayList<>(
                    list.subList(i, Math.min(list.size(), i + number))));
        }
        return spansOfMoney;
    }
    public static ArrayList<String> getTextOfProducts(List<WebElement> elements) throws Exception {
        try {
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i <= (elements.size()-1); i++) {
                String title = elements.get(i).getText();
                list.add(i, title);
            }
                return list;

            } catch (Exception e) {
                throw new Exception("Is not possible get the array of strings");
            }
        }
    }
