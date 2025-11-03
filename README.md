# Proyek Contoh Explicit Intent & Result API

Proyek ini adalah aplikasi Android sederhana yang dibuat dengan Kotlin dan Jetpack Compose untuk mendemonstrasikan cara kerja **Explicit Intent** dan **Activity Result API**. Aplikasi ini mensimulasikan antarmuka chat sederhana antara dua layar (`Activity`).

## Deskripsi

Aplikasi ini memiliki dua layar utama:

1.  **Layar Utama (`MainActivity`)**: Menampilkan antarmuka chat. Pengguna dapat mengetik pesan dan mengirimkannya ke layar kedua. Pesan balasan dari layar kedua juga akan ditampilkan di sini.
2.  **Layar Kedua (`SecondActivity`)**: Menerima dan menampilkan pesan dari layar utama. Pengguna dapat mengetik balasan dan mengirimkannya kembali ke layar utama.

Tujuan proyek ini adalah untuk memberikan contoh yang jelas dan praktis tentang:
-   Memulai `Activity` baru menggunakan **Explicit Intent**.
-   Mengirim data ke `Activity` baru melalui `Intent`.
-   Mengembalikan data dari `Activity` kedua ke `Activity` pertama menggunakan **Activity Result API** (`rememberLauncherForActivityResult`).

## Fitur Utama

-   **Antarmuka Chat**: Tampilan percakapan sederhana untuk melacak pesan yang dikirim dan diterima.
-   **Pengiriman Pesan**: Mengirim data (teks pesan) dari `MainActivity` ke `SecondActivity`.
-   **Penerimaan Balasan**: Menerima data balasan dari `SecondActivity` kembali ke `MainActivity`.
-   **UI Modern**: Dibangun sepenuhnya menggunakan Jetpack Compose.

## Alur Kerja Aplikasi

1.  Pengguna berada di `MainActivity`.
2.  Pengguna mengetik pesan di `OutlinedTextField` dan menekan tombol **"Kirim"**.
3.  Sebuah `Intent` eksplisit dibuat untuk membuka `SecondActivity`. Pesan yang diketik pengguna ditambahkan ke `Intent` sebagai *extra*.
4.  `MainActivity` meluncurkan `SecondActivity` menggunakan `rememberLauncherForActivityResult`, yang siap menerima hasil kembali.
5.  `SecondActivity` dimulai dan mengambil pesan dari `Intent` yang diterimanya, lalu menampilkannya di layar.
6.  Pengguna mengetik pesan balasan di `SecondActivity` dan menekan tombol **"Balas"**.
7.  `SecondActivity` membuat `Intent` baru yang berisi pesan balasan.
8.  `SecondActivity` ditutup (`finish()`) dan mengembalikan `Intent` balasan sebagai hasilnya (`setResult`).
9.  `MainActivity` menerima hasil ini melalui *callback* `rememberLauncherForActivityResult`.
10. Pesan balasan diekstrak dan ditampilkan di daftar chat `MainActivity`.

## Teknologi & Pustaka yang Digunakan

-   **Kotlin**: Bahasa pemrograman utama.
-   **Jetpack Compose**: Toolkit UI modern untuk membangun UI native Android.
-   **Material 3**: Komponen desain untuk antarmuka yang modern dan menarik.
-   **Activity Result API**: Cara modern dan aman untuk mendapatkan hasil dari `Activity` lain.
-   **Gradle (Kotlin DSL)**: Sistem build otomatis dengan skrip yang ditulis dalam Kotlin.
-   **Gradle Version Catalog**: Untuk manajemen dependensi yang terpusat dan mudah dikelola.

## Panduan Menjalankan Proyek

Untuk menjalankan proyek ini di komputer Anda, ikuti langkah-langkah berikut.

### Prasyarat

-   **Android Studio**: Pastikan Anda telah menginstal versi terbaru (disarankan Android Studio Iguana atau lebih baru).
-   **Emulator Android atau Perangkat Fisik**: Siapkan emulator di Android Studio atau hubungkan perangkat Android fisik dengan mode developer aktif.

### Instalasi & Menjalankan

1.  **Clone Repositori**
    ```bash
    git clone https://github.com/username/explicit_intent.git
    ```
    *(Ganti `username/explicit_intent.git` dengan URL repositori Anda yang sebenarnya)*

2.  **Buka di Android Studio**
    -   Buka Android Studio.
    -   Pilih **"Open an Existing Project"**.
    -   Arahkan ke direktori tempat Anda meng-clone repositori, lalu klik **"OK"**.

3.  **Sinkronkan Gradle**
    -   Android Studio akan secara otomatis menyinkronkan proyek dengan file Gradle. Tunggu hingga proses ini selesai.

4.  **Jalankan Aplikasi**
    -   Pilih target (emulator atau perangkat fisik) dari dropdown di toolbar atas.
    -   Klik tombol **"Run 'app'"** (ikon segitiga hijau) atau gunakan shortcut `Shift + F10`.
    -   Aplikasi akan di-build dan diinstal di target yang Anda pilih.

## Struktur Proyek

Berikut adalah struktur file dan direktori penting dalam proyek ini:

```
explicit_intent/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/explicit_intent/
│   │   │   │   ├── MainActivity.kt      # Layar utama, pengirim pesan
│   │   │   │   ├── SecondActivity.kt    # Layar kedua, penerima & pembalas
│   │   │   │   └── ui/theme/            # Tema Jetpack Compose
│   │   │   ├── res/                     # Sumber daya (gambar, ikon, dll.)
│   │   │   └── AndroidManifest.xml      # Deklarasi komponen aplikasi
│   │   └── build.gradle.kts           # Konfigurasi build untuk modul 'app'
│
├── gradle/
│   └── libs.versions.toml             # Katalog versi dependensi
│
└── build.gradle.kts                   # Konfigurasi build tingkat proyek
```
