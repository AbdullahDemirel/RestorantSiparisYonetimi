package repository;

import model.MenuItem;

import java.io.*;
import java.util.ArrayList;

public class MenuItemRepository {

    private static final String FILE_PATH = "src/data/menu.txt";

    public MenuItemRepository() {
        File f = new File(FILE_PATH);
        if (!f.exists()) {
            try {
                f.getParentFile().mkdirs();
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<MenuItem> loadItems() {
        ArrayList<MenuItem> items = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] p = line.split(";");

                int id = Integer.parseInt(p[0]);
                int kategoriId = Integer.parseInt(p[1]);
                String ad = p[2];
                double fiyat = Double.parseDouble(p[3]);
                int stok = Integer.parseInt(p[4]);

                items.add(new MenuItem(id, kategoriId, ad, fiyat, stok));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return items;
    }

    public void saveItems(ArrayList<MenuItem> items) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (MenuItem m : items) {
                bw.write(m.toFileString());
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MenuItem getById(int id) {
        MenuItemRepository repo = new MenuItemRepository();
        for (MenuItem m : repo.loadItems()) {
            if (m.getId() == id) return m;
        }
        return null;
    }
}
