<div align="center">
  <h1>🏨 Enterprise-Ready: Sistem Manajemen Reservasi Hotel</h1>
  <p><i>Aplikasi Desktop Manajemen Reservasi Hotel berbasis Java Swing & MySQL dengan arsitektur modern.</i></p>

  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL">
  <img src="https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white" alt="Maven">
  <img src="https://img.shields.io/badge/github%20actions-%232671E5.svg?style=for-the-badge&logo=githubactions&logoColor=white" alt="GitHub Actions">
</div>

---

## 📖 Tentang Proyek
Sistem Manajemen Reservasi Hotel ini merupakan aplikasi berbasis *desktop* (*Enterprise-Ready*) yang dirancang khusus untuk memfasilitasi operasional manajemen perhotelan. Dibangun menggunakan **Java Swing** dan dikelola pengembangannya melalui **Apache Maven**, proyek ini menerapkan standar industri rekayasa perangkat lunak yang unggul. 

Aplikasi ini mencakup keamanan kriptografi *hashing*, arsitektur konfigurasi dinamis, pemisahan logika kueri melalui pola desain DAO (*Data Access Object*), serta pipeline CI/CD otomatis penuh.

---

## ✨ Fitur & Fungsionalitas Inti (Berdasarkan `pom.xml`)

Sistem ini didukung oleh *library* dan *plugin* yang tangguh, menghadirkan fitur-fitur tingkat lanjut sebagai berikut:

### 🔐 1. Keamanan Kriptografi Tingkat Lanjut (jBCrypt)
- **Autentikasi Terenkripsi:** Mengimplementasikan *library* `org.mindrot:jbcrypt:0.4` untuk melakukan *hashing* pada kata sandi pengguna.
- **Anti-Brute Force:** Menggunakan *Workload Factor* tinggi dan *Dynamic Salting* yang secara otomatis menambahkan teks acak pada setiap *password*. Hal ini membuat serangan *Rainbow Table* tidak berguna karena basis data murni menyimpan string *hash*, bukan *plain text*.

### 🗄️ 2. Integrasi Basis Data Andal (MySQL Connector/J)
- **Koneksi Stabil:** Terintegrasi langsung dengan relasi basis data menggunakan `mysql:mysql-connector-j:8.3.0`.
- **Eksternalisasi Konfigurasi (Dynamic Properties):** Kredensial basis data (`db.url`, `db.user`, `db.password`) diisolasi di dalam file `app.properties`. Kamu dapat memigrasikan aplikasi ke *server* atau *cloud* lain hanya dengan menyunting satu file teks tanpa perlu melakukan rekompilasi kode Java.

### 📦 3. Build Automation (Maven Assembly Plugin)
- **Fat JAR Bundling:** Berkat implementasi `maven-assembly-plugin:3.6.0`, sistem secara otomatis membungkus aplikasi dan seluruh dependensi pihak ketiganya menjadi satu file `.jar` tunggal (*executable*). *End-User* cukup melakukan klik ganda (*double-click*) untuk menjalankan aplikasi.
- **CI/CD Pipeline via GitHub Actions:** Setiap *push* ke repositori akan memicu mesin virtual untuk melakukan proses *build* secara otomatis, sehingga rilis (artefak) terbaru langsung tersedia untuk diunduh tanpa proses manual.

### 📊 4. Manajemen UI & Operasional
- **Dashboard Analitik Dinamis:** Menyajikan agregasi data ketersediaan kamar secara *real-time* (Total, Tersedia, Terisi, Perbaikan) melalui kalkulasi SQL.
- **Live Search & Auto-Formatting:** Fitur pencarian reaktif yang mendeteksi input (*keystroke*) pengguna secara langsung. Terdapat pemformatan nilai nominal harga ke format mata uang Rupiah secara otomatis pada *User Interface*.
- **Data Export & Laporan:** Kemampuan mengekstrak data operasional (laporan) langsung ke dalam format `.CSV` melalui modul utilitas bawaan.

---

## 📸 Antarmuka Pengguna (UI / UX)

*(Ganti URL gambar di bawah dengan screenshot asli aplikasi dari direktori repositori agar lebih informatif)*

| Halaman Login (Secured) | Dashboard Real-time | Manajemen Kamar |
| :---: | :---: | :---: |
| `<img src="link_gambar_login" width="250">` | `<img src="link_gambar_dashboard" width="250">` | `<img src="link_gambar_kamar" width="250">` |

---

## 🏗️ Struktur Arsitektur Proyek

Proyek ini menggunakan pemisahan *concern* yang bersih (*Clean Code Architecture*):

```text
reservasihotel/
├── .github/workflows/       # Skrip pipeline CI/CD (GitHub Actions)
├── src/main/java/
│   ├── com.rizuku...        # Class Main Entry
│   ├── config/              # Inisialisasi Driver & Koneksi
│   ├── dao/                 # Data Access Object (KamarDAO, UserDAO)
│   ├── gui/                 # Komponen UI / JFrame
│   └── utils/               # Helper Utility (UserSession, Formatters)
├── src/main/resources/      # Konfigurasi Eksternal
│   └── app.properties       # Kredensial DB
└── pom.xml                  # Manajemen Dependensi Maven

```

---

## 🗄️ Skema Basis Data & ERD

Relasi tabel menggunakan kaidah normalisasi dan integritas *Foreign Key*:

| Tabel Utama | Primary Key | Relasi (Foreign Key) | Fungsi |
| --- | --- | --- | --- |
| `users` | `username` | - | Mengelola data admin dan resepsionis. |
| `master_tipe_kamar` | `id_tipe` | - | Kamus pengelompokan tipe/kelas kamar hotel. |
| `kamar` | `kode_kamar` | `id_tipe` → `master_tipe_kamar` | Inventaris ruangan, tarif harga, dan status kamar. |

---

## 🚀 Panduan Instalasi (Quick Start)

### 1. Persiapan Lingkungan (*Requirements*)

* Java Runtime Environment (JRE) atau JDK versi 17+.
* XAMPP / MySQL Server lokal berjalan di port `3306`.

### 2. Konfigurasi Basis Data

1. Buka *phpMyAdmin* atau *MySQL Console*.
2. Buat *database* baru dengan nama `db_reservasi_hotel`.
3. Lakukan *import* struktur tabel dari file `.sql` yang tersedia, atau jalankan *query* *create table* pertama pada sistem.

### 3. Menjalankan Aplikasi

Bagi Pengguna Umum (*End-User*):

1. Buka tab **Actions** di repositori ini.
2. Unduh artefak *build* terbaru (`Aplikasi-Reservasi-Hotel.zip`).
3. Ekstrak, lalu klik dua kali pada file `jar` yang terbuat, atau jalankan perintah CLI:
```bash
java -jar namabuild-jar-with-dependencies.jar

```



Bagi *Developer*:

1. *Clone* repositori ini: `git clone https://github.com/rizuku-v2/reservasi-hotel-java-netbeans.git`
2. Buka proyek melalui Apache NetBeans.
3. Tunggu hingga Maven selesai mengunduh dependensi (proses *Resolve Dependencies*).
4. Klik **Clean and Build**, lalu jalankan kelas utama.

---

## 🗺️ Rencana Pengembangan Mendatang (Roadmap)

* [ ] Integrasi cetak struk/laporan berformat PDF menggunakan *JasperReports*.
* [ ] Implementasi modul *Booking* / Transaksi Tamu Hotel.
* [ ] Migrasi *database* ke infrastruktur *Cloud Relational Database* untuk pengelolaan lintas jaringan secara langsung (*Multi-User Live*).

---

## 🏆 Penghargaan & Lisensi (Credits)

Aplikasi ini dikembangkan dengan apresiasi tinggi kepada teknologi *Open Source*:

* **[Apache Maven](https://maven.apache.org/)** untuk manajemen dependensi dan *build automation*.
* **[jBCrypt](https://www.mindrot.org/projects/jBCrypt/)** oleh Damien Miller.
* **[MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)** oleh Oracle Corporation.

*Didesain dan dikembangkan dengan dedikasi untuk eksplorasi rekayasa perangkat lunak.* 💻☕
