# Membedah Proyek "Explicit Intent" 📱

Selamat datang di dokumentasi proyek "Explicit Intent"! Dokumen ini akan menguraikan setiap bagian dari proyek ini dengan sangat detail menggunakan analogi yang mudah dipahami, sehingga siapa pun dapat mengerti alur dan konsep yang digunakan.

---

## Bab 1: Analogi Proyek - Aplikasi Perpesanan Sederhana 💬

Sebelum kita masuk ke kode, mari kita bayangkan proyek ini seperti apa.

### Definisi
Proyek ini adalah aplikasi Android yang memiliki dua layar. Layar pertama memungkinkan Anda mengirim pesan ke layar kedua. Layar kedua menampilkan pesan tersebut dan memungkinkan Anda mengirim balasan kembali ke layar pertama.

### Analogi
Bayangkan Anda memiliki dua ruangan, **Ruang A** (`MainActivity`) dan **Ruang B** (`SecondActivity`).

1.  Di **Ruang A**, Anda menulis sebuah surat, memasukkannya ke dalam amplop, menuliskan "Untuk Ruang B" di depannya, lalu memberikannya kepada seorang kurir.
2.  Kurir tersebut pergi ke **Ruang B** dan menyerahkan surat itu.
3.  Orang di **Ruang B** membaca surat Anda, menulis surat balasan, memasukkannya ke dalam amplop, dan memberikannya kembali kepada kurir yang sama.
4.  Kurir kembali ke **Ruang A** dan menyerahkan surat balasan tersebut kepada Anda.

Itulah persisnya cara kerja aplikasi ini. "Surat" adalah data (teks pesan), dan "kurir" adalah apa yang kita sebut `Intent` dalam dunia Android.

### Hubungannya ke Proyek
-   **`MainActivity.kt`**: Ini adalah **Ruang A**. Di sinilah Anda mengetik pesan pertama.
-   **`SecondActivity.kt`**: Ini adalah **Ruang B**. Layar ini menerima pesan dan tempat Anda mengetik balasan.
-   **Tombol "Kirim"**: Ini adalah tindakan Anda memanggil kurir untuk mengirim surat.
-   **Tombol "Balas"**: Ini adalah tindakan Anda memberikan surat balasan kepada kurir untuk diantar kembali.

---

## Bab 2: Konsep Inti - "Intent" si Kurir Pesan 🏃‍♂️

Komponen paling fundamental dalam proyek ini adalah `Intent`.

### Definisi
`Intent` adalah sebuah objek "pesan" yang digunakan di Android untuk meminta suatu tindakan dari komponen aplikasi lain. Tindakan ini bisa berupa memulai layar baru (Activity), menyiarkan pesan ke seluruh sistem, atau memulai layanan di latar belakang.

### Analogi
`Intent` adalah seorang **kurir**. Tugas seorang kurir adalah mengantarkan sesuatu dari satu titik ke titik lain. Agar kurir dapat bekerja dengan baik, Anda perlu memberinya dua hal:
1.  **Alamat Tujuan**: Ke mana paket ini harus diantar?
2.  **Paket (Opsional)**: Apa isi paket yang ingin Anda kirim?

### Hubungannya ke Proyek
Dalam `MainActivity.kt`, kita melihat baris kode ini:

```kotlin
val intent = Intent(context, SecondActivity::class.java)
```

Di sini, kita sedang mempersiapkan "kurir" kita.
-   `Intent(...)`: Kita memanggil perusahaan kurir.
-   `context`: Ini adalah alamat kita saat ini (dari mana kurir berangkat).
-   `SecondActivity::class.java`: Ini adalah **alamat tujuan** yang sangat spesifik. Kita memberitahu kurir untuk pergi ke kelas `SecondActivity`.

---

## Bab 3: "Intent Eksplisit" - Kurir dengan Alamat Jelas 🗺️

Ada dua jenis utama Intent, dan proyek ini menggunakan jenis "Eksplisit".

### Definisi
**Intent Eksplisit** adalah jenis `Intent` di mana Anda secara eksplisit (jelas dan gamblang) menentukan komponen aplikasi mana yang harus menerima pesan. Anda menyebutkan nama kelas tujuannya secara langsung.

### Analogi
Ini seperti memberikan alamat yang sangat lengkap kepada kurir: "Antarkan paket ini ke **Bapak Budi di Jalan Mawar No. 5, Kota Jakarta**". Tidak ada keraguan sama sekali ke mana kurir harus pergi. Anda tidak bilang "antarkan ke toko buku terdekat", tetapi Anda bilang "antarkan ke Gramedia Matraman".

### Hubungannya ke Proyek
Kode `Intent(context, SecondActivity::class.java)` adalah contoh sempurna dari Intent Eksplisit. Kita tidak mengatakan "Hai Android, tolong buka layar yang bisa menampilkan pesan," melainkan kita mengatakan, "Hai Android, saya mau kamu **buka `SecondActivity`**, titik."

Ini adalah praktik yang paling umum digunakan untuk navigasi di dalam aplikasi Anda sendiri, karena Anda tahu persis layar mana yang ingin Anda buka.

---

## Bab 4: Membawa Data - "Extras" dalam Paket Kurir 📦

Bagaimana cara kita mengirim pesan "Halo" dari `MainActivity` ke `SecondActivity`? Kita menggunakan "Extras".

### Definisi
"Extras" adalah data tambahan dalam bentuk *key-value pair* (pasangan kunci-nilai) yang dapat Anda selipkan ke dalam sebuah `Intent`.

### Analogi
"Extras" adalah **isi dari paket** yang dibawa kurir. Jika `Intent` adalah kurir dan amplopnya, maka "Extra" adalah surat di dalamnya. Agar isinya tidak tertukar, kita memberinya label (kunci).

-   **Kunci**: `EXTRA_MESSAGE` (Label pada surat, misal: "Surat Penting")
-   **Nilai**: `messageText` (Isi surat, misal: "Halo, apa kabar?")

### Hubungannya ke Proyek
1.  **Mengirim Data (`MainActivity.kt`)**:
    ```kotlin
    intent.putExtra(MainActivity.EXTRA_MESSAGE, messageText)
    ```
    -   `putExtra`: Kita memasukkan sesuatu ke dalam "paket".
    -   `MainActivity.EXTRA_MESSAGE`: Ini adalah "kunci" atau labelnya.
    -   `messageText`: Ini adalah "nilai" atau isi pesannya.

2.  **Menerima Data (`SecondActivity.kt`)**:
    ```kotlin
    val message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE)
    ```
    -   `getStringExtra`: Kita mengambil data dari dalam "paket" dan mengharapkannya berupa teks (String).
    -   `MainActivity.EXTRA_MESSAGE`: Kita menggunakan kunci yang sama untuk menemukan data yang benar.

---

## Bab 5: Menunggu Balasan - `rememberLauncherForActivityResult` 📬

Dulu, untuk mendapatkan data kembali dari Activity lain itu sedikit rumit. Sekarang, dengan Jetpack Compose, kita menggunakan `rememberLauncherForActivityResult`.

### Definisi
Ini adalah sebuah "kait" (hook) dari Jetpack Compose yang memungkinkan kita untuk memulai sebuah Activity dan mendefinisikan *callback* (fungsi yang akan dijalankan) ketika Activity tersebut selesai dan mengirimkan hasil kembali.

### Analogi
Bayangkan Anda menyuruh kurir pergi, tetapi Anda memberinya instruksi tambahan: "**Setelah kamu sampai dan dapat surat balasan, jangan langsung pulang. Telepon aku di nomor ini dan bacakan isinya.**"

-   `rememberLauncherForActivityResult(...)`: Anda memberikan "nomor telepon" (callback) kepada sistem sebelum kurir berangkat.
-   `launcher.launch(intent)`: Anda menyuruh kurir untuk berangkat sekarang.
-   `setResult(...)` & `finish()`: Di `SecondActivity`, ini adalah tindakan memberikan surat balasan kepada kurir dan menyuruhnya pulang.

### Hubungannya ke Proyek
1.  **Di `MainActivity.kt`**:
    ```kotlin
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Kode ini akan berjalan KETIKA SecondActivity mengirim balasan
        if (result.resultCode == Activity.RESULT_OK) {
            val reply = result.data?.getStringExtra(SecondActivity.EXTRA_REPLY)
            // ...tampilkan balasan...
        }
    }
    
    // ... di dalam Button onClick
    launcher.launch(intent) // Kurir berangkat!
    ```

2.  **Di `SecondActivity.kt`**:
    ```kotlin
    val replyIntent = Intent()
    replyIntent.putExtra(EXTRA_REPLY, replyText)
    setResult(Activity.RESULT_OK, replyIntent) // Memberi surat balasan ke kurir
    finish() // Menyuruh kurir pulang (menutup layar SecondActivity)
    ```

---

## Bab 6: Rangkuman Alur Kerja Proyek (End-to-End)

Mari kita satukan semuanya dari awal hingga akhir.

1.  **Pengguna di `MainActivity`** mengetik "Halo Dunia" dan menekan tombol "Kirim".
2.  **`MainActivity`** membuat `Intent` eksplisit yang menunjuk ke `SecondActivity`.
3.  **`MainActivity`** menggunakan `putExtra` untuk memasukkan pesan "Halo Dunia" ke dalam `Intent` dengan kunci `EXTRA_MESSAGE`.
4.  **`MainActivity`** memanggil `launcher.launch(intent)`, yang menyuruh sistem Android untuk memulai `SecondActivity`.
5.  **Sistem Android** menerima permintaan, membuat instance `SecondActivity`, dan mengirimkan `Intent` tersebut kepadanya.
6.  **`SecondActivity`** di dalam `onCreate` membaca `Intent` yang masuk menggunakan `intent.getStringExtra(EXTRA_MESSAGE)` untuk mendapatkan pesan "Halo Dunia".
7.  **`SecondActivity`** menampilkan pesan tersebut di layar.
8.  **Pengguna di `SecondActivity`** mengetik balasan "Hai juga" dan menekan tombol "Balas".
9.  **`SecondActivity`** membuat `Intent` baru yang kosong (`replyIntent`).
10. **`SecondActivity`** menggunakan `putExtra` untuk memasukkan pesan "Hai juga" ke dalam `replyIntent` dengan kunci `EXTRA_REPLY`.
11. **`SecondActivity`** memanggil `setResult(Activity.RESULT_OK, replyIntent)`. Ini seperti menandai "misi berhasil" dan menyerahkan paket balasan ke sistem.
12. **`SecondActivity`** memanggil `finish()`, yang menghancurkan dirinya sendiri dan kembali ke layar sebelumnya (`MainActivity`).
13. **`MainActivity`** yang tidak pernah benar-benar tertutup (hanya dijeda), kini aktif kembali. *Callback* dari `launcher` yang kita buat tadi otomatis terpicu.
14. **Di dalam *callback* `launcher`**, kode memeriksa apakah `resultCode` adalah `RESULT_OK`. Jika ya, ia akan mengambil data dari `replyIntent` menggunakan `getStringExtra(EXTRA_REPLY)` untuk mendapatkan pesan "Hai juga".
15. **`MainActivity`** kemudian menampilkan pesan balasan tersebut di layarnya.

Siklus selesai!
