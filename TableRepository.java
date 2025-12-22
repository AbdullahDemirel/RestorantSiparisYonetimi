package repository;

import model.Table;
import java.io.*;
import java.util.ArrayList;

public class TableRepository {

    private final String FILE_PATH = "src/data/tables.txt";

    public TableRepository() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try { file.createNewFile(); }
            catch (IOException e) { e.printStackTrace(); }
        }
    }

    public ArrayList<Table> loadTables() {
        ArrayList<Table> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");

                int id = Integer.parseInt(parts[0]);
                int kapasite = Integer.parseInt(parts[1]);
                int mevcut = Integer.parseInt(parts[2]);
                int aktifSiparis = Integer.parseInt(parts[3]);

                Table t = new Table(id, kapasite);
                t.setMevcutKisi(mevcut);
                t.setAktifSiparisId(aktifSiparis);

                list.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void saveTables(ArrayList<Table> tables) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {

            for (Table t : tables) {
                bw.write(t.toFileString());
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
