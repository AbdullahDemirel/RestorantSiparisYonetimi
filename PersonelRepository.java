package repository;

import model.Personel;

import java.io.*;
import java.util.ArrayList;

public class PersonelRepository {

    private final String FILE_PATH = "src/data/personel.txt";

    public PersonelRepository() {
        File f = new File(FILE_PATH);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Personel> loadPersonel() {

        ArrayList<Personel> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {

            String line;
            while ((line = br.readLine()) != null) {

                line = line.trim();
                if (line.isEmpty()) continue;

                String[] p = line.split(";");
                if (p.length != 7) continue;

                int id = Integer.parseInt(p[0].trim());
                String adSoyad = p[1].trim();
                String kullaniciAdi = p[2].trim();
                String gorev = p[3].trim();
                boolean aktif = Boolean.parseBoolean(p[4].trim());
                boolean yoneticiMi = Boolean.parseBoolean(p[5].trim());
                String sifre = p[6].trim();

                Personel per = new Personel(
                        id,
                        adSoyad,
                        kullaniciAdi,
                        gorev,
                        yoneticiMi,
                        sifre
                );

                per.setAktif(aktif);
                list.add(per);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void savePersonel(ArrayList<Personel> list) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {

            for (Personel p : list) {
                bw.write(p.toFileString());
                bw.newLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
