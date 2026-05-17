package utils;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogHelper {
    // Nama file log yang akan dibuat otomatis di dalam folder project
    private static final String LOG_FILE = "app_backend.log";
    
    private static String getTimeStamp() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return dtf.format(LocalDateTime.now());
    }

    // Fungsi utama untuk menulis ke file
    private static void writeToFile(String type, String message) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println("[" + type + "] [" + getTimeStamp() + "] " + message);
        } catch (IOException e) {
            System.out.println("Gagal menulis log ke file: " + e.getMessage());
        }
    }

    public static void info(String message) {
        System.out.println("[INFO] [" + getTimeStamp() + "] " + message);
        writeToFile("INFO", message);
    }

    public static void error(String message) {
        System.err.println("[ERROR] [" + getTimeStamp() + "] " + message);
        writeToFile("ERROR", message);
    }
}