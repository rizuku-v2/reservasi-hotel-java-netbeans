package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import config.Koneksi;

public class TipeKamarDAO {
    public List<Object[]> getAllTipeKamar() throws Exception {
        List<Object[]> list = new ArrayList<>();
        Connection conn = Koneksi.getKoneksi();
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM master_tipe_kamar");
        while (rs.next()) {
            list.add(new Object[]{rs.getString("id_tipe"), rs.getString("nama_tipe")});
        }
        return list;
    }

    // Fungsi baru untuk mendapatkan ID berdasarkan Nama Tipe Kamar dari Combo Box
    public int getIdTipeByNama(String namaTipe) throws Exception {
        Connection conn = Koneksi.getKoneksi();
        PreparedStatement pst = conn.prepareStatement("SELECT id_tipe FROM master_tipe_kamar WHERE nama_tipe = ?");
        pst.setString(1, namaTipe);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return rs.getInt("id_tipe");
        }
        return 0; // Kembalikan 0 jika tidak ditemukan
    }

    public void insert(String namaTipe) throws Exception {
        Connection conn = Koneksi.getKoneksi();
        PreparedStatement pst = conn.prepareStatement("INSERT INTO master_tipe_kamar (nama_tipe) VALUES (?)");
        pst.setString(1, namaTipe);
        pst.executeUpdate();
    }

    public void update(String idTipe, String namaTipe) throws Exception {
        Connection conn = Koneksi.getKoneksi();
        PreparedStatement pst = conn.prepareStatement("UPDATE master_tipe_kamar SET nama_tipe=? WHERE id_tipe=?");
        pst.setString(1, namaTipe);
        pst.setString(2, idTipe);
        pst.executeUpdate();
    }

    public void delete(String idTipe) throws Exception {
        Connection conn = Koneksi.getKoneksi();
        PreparedStatement pst = conn.prepareStatement("DELETE FROM master_tipe_kamar WHERE id_tipe=?");
        pst.setString(1, idTipe);
        pst.executeUpdate();
    }
}