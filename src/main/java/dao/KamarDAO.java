package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import config.Koneksi;

public class KamarDAO {
    
    // Menarik semua data kamar (Default)
    public List<Object[]> getAllKamar() throws Exception {
        List<Object[]> list = new ArrayList<>();
        Connection conn = Koneksi.getKoneksi();
        // Menggunakan LEFT JOIN agar JTable depan tetap menampilkan Nama Tipe Kamar dengan mulus
        String sql = "SELECT k.kode_kamar, t.nama_tipe, k.kapasitas, k.lantai, k.fasilitas, k.harga_per_malam, k.status " +
                     "FROM kamar k " +
                     "LEFT JOIN master_tipe_kamar t ON k.id_tipe = t.id_tipe";
        ResultSet rs = conn.createStatement().executeQuery(sql);
        while (rs.next()) {
            list.add(new Object[]{
                rs.getString("kode_kamar"), 
                rs.getString("nama_tipe"), 
                rs.getString("kapasitas"), 
                rs.getString("lantai"), 
                rs.getString("fasilitas"), 
                rs.getString("harga_per_malam"), 
                rs.getString("status")
            });
        }
        return list;
    }

    // Fungsi baru untuk Live Search
    public List<Object[]> searchKamar(String keyword) throws Exception {
        List<Object[]> list = new ArrayList<>();
        Connection conn = Koneksi.getKoneksi();
        
        // Mencari berdasarkan Kode Kamar, Nama Tipe, atau isi Fasilitas
        String sql = "SELECT k.kode_kamar, t.nama_tipe, k.kapasitas, k.lantai, k.fasilitas, k.harga_per_malam, k.status " +
                     "FROM kamar k LEFT JOIN master_tipe_kamar t ON k.id_tipe = t.id_tipe " +
                     "WHERE k.kode_kamar LIKE ? OR t.nama_tipe LIKE ? OR k.fasilitas LIKE ?";
                     
        PreparedStatement pst = conn.prepareStatement(sql);
        // Tanda % di depan dan belakang artinya "mengandung kata"
        pst.setString(1, "%" + keyword + "%");
        pst.setString(2, "%" + keyword + "%");
        pst.setString(3, "%" + keyword + "%");
        
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            list.add(new Object[]{
                rs.getString("kode_kamar"), 
                rs.getString("nama_tipe"), 
                rs.getString("kapasitas"), 
                rs.getString("lantai"), 
                rs.getString("fasilitas"), 
                rs.getString("harga_per_malam"), 
                rs.getString("status")
            });
        }
        return list;
    }

    // Mengecek apakah kode kamar sudah ada di database (Mencegah Duplikat)
    public boolean cekKodeKamar(String kodeKamar) throws Exception {
        Connection conn = Koneksi.getKoneksi();
        PreparedStatement pst = conn.prepareStatement("SELECT kode_kamar FROM kamar WHERE kode_kamar = ?");
        pst.setString(1, kodeKamar);
        ResultSet rs = pst.executeQuery();
        return rs.next();
    }

    // Menggunakan int idTipe untuk menjaga Foreign Key Integrity
    public void insert(String kode, int idTipe, int kapasitas, int lantai, String fasilitas, int harga, String status) throws Exception {
        Connection conn = Koneksi.getKoneksi();
        String sql = "INSERT INTO kamar (kode_kamar, id_tipe, kapasitas, lantai, fasilitas, harga_per_malam, status) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, kode);
        pst.setInt(2, idTipe);
        pst.setInt(3, kapasitas);
        pst.setInt(4, lantai);
        pst.setString(5, fasilitas);
        pst.setInt(6, harga);
        pst.setString(7, status);
        pst.executeUpdate();
    }

    // Mengupdate data kamar berdasarkan kode_kamar
    public void update(String kode, int idTipe, int kapasitas, int lantai, String fasilitas, int harga, String status) throws Exception {
        Connection conn = Koneksi.getKoneksi();
        String sql = "UPDATE kamar SET id_tipe=?, kapasitas=?, lantai=?, fasilitas=?, harga_per_malam=?, status=? WHERE kode_kamar=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, idTipe);
        pst.setInt(2, kapasitas);
        pst.setInt(3, lantai);
        pst.setString(4, fasilitas);
        pst.setInt(5, harga);
        pst.setString(6, status);
        pst.setString(7, kode);
        pst.executeUpdate();
    }

    // Menghapus data kamar
    public void delete(String kode) throws Exception {
        Connection conn = Koneksi.getKoneksi();
        PreparedStatement pst = conn.prepareStatement("DELETE FROM kamar WHERE kode_kamar=?");
        pst.setString(1, kode);
        pst.executeUpdate();
    }
    
    // Fungsi untuk menghitung total seluruh kamar
    public int getTotalKamar() throws Exception {
        Connection conn = Koneksi.getKoneksi();
        ResultSet rs = conn.createStatement().executeQuery("SELECT COUNT(*) FROM kamar");
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    // Fungsi untuk menghitung jumlah kamar berdasarkan status (tersedia/terisi/perbaikan)
    public int getJumlahKamarByStatus(String status) throws Exception {
        Connection conn = Koneksi.getKoneksi();
        PreparedStatement pst = conn.prepareStatement("SELECT COUNT(*) FROM kamar WHERE status = ?");
        pst.setString(1, status);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }
    
}