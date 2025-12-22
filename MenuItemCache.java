package repository;

import model.MenuItem;

import java.util.ArrayList;

public class MenuItemCache {

    private static ArrayList<MenuItem> items = new ArrayList<>();

    public static void setItems(ArrayList<MenuItem> list) {
        items = list;
    }

    public static MenuItem getById(int id) {
        for (MenuItem m : items) {
            if (m.getId() == id) return m;
        }
        return null;
    }
}
