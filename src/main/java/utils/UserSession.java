package utils;

import java.util.prefs.Preferences;

public class UserSession {
    // Membuat node preferences khusus untuk aplikasi ini
    private static Preferences prefs = Preferences.userNodeForPackage(UserSession.class);

    // Menyimpan sesi saat login berhasil
    public static void setLogin(String namaLengkap) {
        prefs.putBoolean("is_logged_in", true);
        prefs.put("nama_lengkap", namaLengkap);
    }

    // Mengecek apakah user sudah login sebelumnya
    public static boolean isLogin() {
        return prefs.getBoolean("is_logged_in", false);
    }

    // Mengambil nama user
    public static String getNamaLengkap() {
        return prefs.get("nama_lengkap", "Guest");
    }

    // Menghapus sesi saat logout
    public static void logout() {
        prefs.remove("is_logged_in");
        prefs.remove("nama_lengkap");
    }
}