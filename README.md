# 🏨 Enterprise-Ready: Sistem Manajemen Reservasi Hotel

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![GitHub Actions](https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white)
![Platform](https://img.shields.io/badge/Platform-Windows%20%7C%20Linux%20%7C%20macOS-lightgrey)

Aplikasi *Desktop-based* Manajemen Reservasi Hotel berskala *Enterprise-Ready* yang dikembangkan menggunakan **Java Swing** dan **Apache Maven**. Proyek ini dirancang dengan mengedepankan standar industri rekayasa perangkat lunak (Software Engineering), mencakup keamanan kriptografi *hashing*, arsitektur konfigurasi dinamis, serta alur *Continuous Integration / Continuous Deployment* (CI/CD) otomatis.

Proyek ini dikembangkan sebagai implementasi komprehensif dari mata kuliah Rekayasa Perangkat Lunak dan Sistem Basis Data.

---

## 📸 Antarmuka Pengguna (UI / UX)

*(Tambahkan URL gambar screenshot aplikasi Anda di sini agar repositori lebih menarik)*

| Halaman Login (BCrypt Secured) | Dashboard & Real-time Analytics | Manajemen Master Kamar |
| :---: | :---: | :---: |
| `<img src="link_gambar_login" width="250">` | `<img src="link_gambar_dashboard" width="250">` | `<img src="link_gambar_kamar" width="250">` |

---

## 🚀 Analisis Fitur & Fungsionalitas Sistem

Sistem ini memisahkan logika antarmuka dan manipulasi data melalui pendekatan yang terstruktur, menghasilkan fitur-fitur tingkat lanjut berikut:

### 1. 🔐 Standar Keamanan Kriptografi (BCrypt Hashing)

Meninggalkan metode enkripsi usang seperti MD5/SHA, aplikasi ini mengimplementasikan algoritma **jBCrypt**.

- **Workload Factor 12:** Memastikan waktu komputasi *hash* cukup lama untuk menggagalkan serangan *Brute Force*.
- **Dynamic Salting:** Menambahkan teks acak pada setiap *password* secara dinamis, sehingga membuat serangan *Rainbow Table* tidak berguna. Basis data murni menyimpan string *hash*, bukan *plain text*.

### 2. ⚙️ Eksternalisasi Konfigurasi (Dynamic Properties)

Arsitektur *backend* terisolasi dari *hardcode* kredensial.

- **`app.properties` Engine:** Koneksi basis data (`db.url`, `db.user`, `db.password`) serta metadata *build* dibaca melalui mekanisme *Input Stream* dari *resources*. Hal ini memfasilitasi distribusi atau migrasi aplikasi ke *server/cloud* lain hanya dengan menyunting satu file teks tanpa rekompilasi Java.

### 3. 🤖 Continuous Integration (GitHub Actions)

Terintegrasi secara penuh dengan *workflow* otomatis GitHub.

- Setiap instruksi `push` ke *branch* utama memicu mesin virtual Ubuntu untuk memasang JDK 26, mengunduh dependensi Maven, melakukan proses *build*, dan mengunggah artefak **Fat JAR** secara otomatis yang langsung siap diunduh oleh *End-User*.

### 4. 📊 Modul Fungsionalitas Inti

- **Stateful Session Management:** Sistem mengenali sesi pengguna aktif (Login/Logout) menggunakan utilitas sesi global.
- **Dashboard Analitik Dinamis:** Menyajikan agregasi data kamar (Total, Tersedia, Terisi, Perbaikan) yang dihitung secara *real-time* menggunakan agregasi SQL (`COUNT`).
- **Live Search & Auto-Formatting:** Tabel data yang reaktif. Fitur pencarian mendeteksi *keystroke* pengguna. Angka raw dimanipulasi melalui *NumberFormat* menjadi mata uang (Rp) murni di sisi UI tanpa merusak tipe data *Integer* di basis data.
- **Data Export Validation:** Modul pengunduhan laporan data ke format `.CSV` (Comma Separated Values) terintegrasi menggunakan implementasi `FileWriter`.

---

## 🏗️ Arsitektur & Struktur Proyek

Proyek ini menggunakan pola *Data Access Object* (DAO) untuk memisahkan *Query* SQL dari kode UI (Swing).

```text
reservasihotel/
├── .github/workflows/       # Skrip konfigurasi CI/CD (GitHub Actions)
├── src/main/java/
│   ├── com.rizuku...        # Class Main Entry (ReservasiHotel.java)
│   ├── config/              # Inisialisasi Driver & Engine Koneksi (Koneksi.java)
│   ├── dao/                 # Data Access Object (KamarDAO, TipeKamarDAO, UserDAO)
│   ├── gui/                 # JFrame/UI Components (Login, Dashboard, DataKamar, dll)
│   └── utils/               # Helper Utility (UserSession, LogHelper)
├── src/main/resources/      # Konfigurasi Eksternal
│   └── app.properties       # Kredensial DB & Versi Build Maven
└── pom.xml                  # Konfigurasi Maven (Dependencies & Build Plugins)
```

---

## 🗄️ Skema Basis Data & ERD

Relasi basis data menggunakan kaidah normalisasi dengan integritas *Foreign Key*.

| Tabel | Primary Key | Foreign Key | Deskripsi |
| --- | --- | --- | --- |
| `users` | `username` (VARCHAR) | - | Menyimpan kredensial akses dengan *password* ter-*hash*. |
| `master_tipe_kamar` | `id_tipe` (INT - AI) | - | Kamus master untuk klasifikasi tipe kamar (Standar, Suite, dll). |
| `kamar` | `kode_kamar` (VARCHAR) | `id_tipe` → `master_tipe_kamar` | Menyimpan inventaris ruang, relasi tipe, harga, dan *State* (Tersedia/Terisi). |

---

## 🛠️ Stack Teknologi & Dependensi

- **Environment:** Java SE Development Kit (JDK 26)
- **GUI Framework:** Java Swing (Theme: Nimbus Look & Feel)
- **Build Automation:** Apache Maven v3.x
- **Database Management:** MySQL
- **Libraries (Maven dependencies):**
  - `mysql:mysql-connector-j:8.3.0` — JDBC Bridge
  - `org.mindrot:jbcrypt:0.4` — Cryptography
  - `maven-assembly-plugin:3.6.0` — Fat JAR Bundler

---

## 📦 Panduan Instalasi (Quick Start)

### 1. Prasyarat Sistem

- Java Runtime Environment (JRE) versi 17 atau ke atas.
- XAMPP / MySQL Server lokal berjalan pada port `3306`.

### 2. Konfigurasi Basis Data

- Akses *phpMyAdmin* atau *MySQL Console*.
- Buat *database* bernama `db_reservasi_hotel`.
- Salin skrip SQL pembuatan tabel (dapat dilihat di dokumentasi *commit* awal) atau *import* file `.sql` jika tersedia, agar struktur tabel terbentuk sempurna.

### 3. Mengunduh & Menjalankan Aplikasi

1. Navigasi ke menu **Actions** di bagian atas *repository* ini.
2. Pilih *Workflow Run* terbaru yang memiliki centang hijau ✅.
3. Gulir ke bawah hingga menemukan bagian **Artifacts**, dan unduh `Aplikasi-Reservasi-Hotel`.
4. Ekstrak *file* berformat `.zip` tersebut.
5. Jalankan *file* berformat `.jar` dengan klik ganda (*Double-Click*) atau via CLI:

```bash
java -jar namabuild-jar-with-dependencies.jar
```

6. Lakukan pendaftaran (Register) pada antarmuka aplikasi untuk pembuatan akun perdana (proses enkripsi).

---

## 🗺️ Roadmap Pengembangan (*Future Updates*)

- [ ] Integrasi Laporan berbasis PDF menggunakan *JasperReports*.
- [ ] Migrasi basis data dari *Localhost* (XAMPP) ke *Cloud Relational Database* (ex: TiDB / Aiven) untuk distribusi *Multi-User Live*.
- [ ] Penambahan modul Transaksi / *Booking* tamu dengan kalkulasi total harga & struk.

---

## 🏆 Kredensial & Pengakuan (Credits)

**Third-Party Licenses & Acknowledgements:**

- [Apache Maven](https://maven.apache.org/) untuk *dependency management*.
- [jBCrypt](https://www.mindrot.org/projects/jBCrypt/) oleh Damien Miller.
- [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) oleh Oracle.
- *Special thanks to the Open Source community and GitHub for the CI/CD pipeline capabilities.*

*Dibuat dan dipelihara dengan dedikasi untuk inovasi teknologi perangkat lunak.* 💻☕
