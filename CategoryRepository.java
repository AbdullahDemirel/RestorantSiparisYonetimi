package repository;

import model.Category;

import java.io.*;
import java.util.ArrayList;

public class CategoryRepository {

    private final String FILE_PATH = "src/data/categories.txt";

    public CategoryRepository() {
        File f = new File(FILE_PATH);
        if (!f.exists()) {
            try { f.createNewFile(); }
            catch (IOException e) { e.printStackTrace(); }
        }
    }

    public ArrayList<Category> loadCategories() {
        ArrayList<Category> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {

            String line;
            while ((line = br.readLine()) != null) {

                String[] p = line.split(";");
                if (p.length < 2) continue;

                int id = Integer.parseInt(p[0]);
                String ad = p[1];

                list.add(new Category(id, ad));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void saveCategories(ArrayList<Category> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {

            for (Category c : list) {
                bw.write(c.toFileString());
                bw.newLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
