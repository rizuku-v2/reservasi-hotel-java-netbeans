package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.mindrot.jbcrypt.BCrypt;
import config.Koneksi;

public class UserDAO {

    public String login(String username, String password) throws Exception {
        Connection conn = Koneksi.getKoneksi();
        
        
        String sql = "SELECT nama_lengkap, password FROM users WHERE username=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, username);
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()) {
            String hashedDbPassword = rs.getString("password");
            
            // Cocokkan password mentah dengan hash BCrypt
            if (BCrypt.checkpw(password, hashedDbPassword)) {
                return rs.getString("nama_lengkap");
            }
        }
        return null;
    }
    
    public boolean registerUser(String username, String password, String namaLengkap) throws Exception {
        Connection conn = config.Koneksi.getKoneksi();
        
        // 1. Cek duplikasi username
        PreparedStatement cekPst = conn.prepareStatement("SELECT username FROM users WHERE username = ?");
        cekPst.setString(1, username);
        ResultSet rs = cekPst.executeQuery();
        if (rs.next()) {
            return false; 
        }
        
        // 2. Hash password menggunakan BCrypt dengan kekuatan salt 12
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(12));
        
        // 3. Simpan ke database
        String sql = "INSERT INTO users (username, password, nama_lengkap) VALUES (?, ?, ?)";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, username);
        pst.setString(2, hashedPassword);
        pst.setString(3, namaLengkap);
        pst.executeUpdate();
        
        return true; 
    }
}