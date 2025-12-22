package repository;

import model.MenuItem;
import model.Order;
import model.OrderDetail;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class OrderRepository {

    private final String FILE_PATH = "src/data/orders.txt";

    public OrderRepository() {
        File f = new File(FILE_PATH);
        if (!f.exists()) {
            try { f.createNewFile(); }
            catch (IOException e) { e.printStackTrace(); }
        }
    }

    public ArrayList<Order> loadOrders() {
        ArrayList<Order> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = br.readLine()) != null && !line.isBlank()) {

                String[] main = line.split(";");
                int id = Integer.parseInt(main[0]);
                int masaId = Integer.parseInt(main[1]);
                LocalDateTime tarih = LocalDateTime.parse(main[2]);
                boolean closed = Boolean.parseBoolean(main[3]);

                Order order = new Order(id, masaId, tarih, closed);

                if (main.length == 5 && !main[4].isBlank()) {
                    String[] detaylar = main[4].split("\\|");

                    for (String d : detaylar) {
                        String[] p = d.split(",");
                        int urunId = Integer.parseInt(p[0]);
                        int adet = Integer.parseInt(p[1]);

                        MenuItem item = MenuItemCache.getById(urunId);
                        if (item != null) {
                            order.detayEkle(new OrderDetail(item, adet));
                        }
                    }
                }

                list.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void saveOrders(ArrayList<Order> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Order o : list) {
                bw.write(o.toFileString());
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
