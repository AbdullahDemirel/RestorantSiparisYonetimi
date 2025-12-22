package repository;

import model.EndOfDayReport;
import java.io.*;

public class EndOfDayReportRepository {

    private final String FILE_PATH = "src/data/endofday.txt";

    public EndOfDayReport loadReport() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = br.readLine();
            if (line == null || line.isBlank()) return new EndOfDayReport();
            return EndOfDayReport.fromFileString(line);
        } catch (Exception e) {
            return new EndOfDayReport();
        }
    }

    public void saveReport(EndOfDayReport report) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            bw.write(report.toFileString());
        } catch (Exception ignored) {}
    }

    public void clearReport() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            bw.write("");
        } catch (Exception ignored) {}
    }
}
