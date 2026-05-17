package test;

import config.Koneksi;
import utils.LogHelper;
import java.sql.Connection;

public class TestKoneksi {
    public static void main(String[] args) {
        LogHelper.info("Memulai test koneksi database...");
        
        // Memanggil method getKoneksi() dari class Koneksi
        Connection conn = Koneksi.getKoneksi();
        
        if (conn != null) {
            LogHelper.info("Test Selesai: Database db_reservasi_hotel siap digunakan!");
        } else {
            LogHelper.error("Test Selesai: Gagal terhubung ke database. Cek XAMPP dan Library MySQL JDBC Driver.");
        }
    }
}