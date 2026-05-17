package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;
import utils.LogHelper;

public class Koneksi {
    private static Connection koneksi;

    public static Connection getKoneksi() {
        if (koneksi == null) {
            try {
                // Membaca file app.properties dari folder resources
                Properties props = new Properties();
                InputStream is = Koneksi.class.getClassLoader().getResourceAsStream("app.properties");
                
                if (is == null) {
                    throw new RuntimeException("File app.properties tidak ditemukan di folder resources!");
                }
                
                props.load(is);
                
                String url = props.getProperty("db.url");
                String user = props.getProperty("db.user");
                String pass = props.getProperty("db.password");
                
                // Mendaftarkan Driver MySQL
                Class.forName(props.getProperty("db.driver"));
                
                // Melakukan koneksi
                koneksi = DriverManager.getConnection(url, user, pass);
                LogHelper.info("Koneksi ke database berhasil diinisiasi melalui app.properties.");
                
            } catch (Exception e) {
                System.err.println("Koneksi Database Gagal: " + e.getMessage());
                try {
                    LogHelper.error("Error Koneksi Database: " + e.getMessage());
                } catch (Exception logEx) {}
            }
        }
        return koneksi;
    }
}