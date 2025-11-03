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

---

## Analisis Kode Mendalam

Bagian ini memberikan penjelasan mendetail tentang file-file kode utama dalam proyek.

<details>
<summary><strong>MainActivity.kt</strong></summary>

File ini bertanggung jawab untuk layar utama aplikasi, tempat pengguna mengirim pesan dan melihat riwayat percakapan.

```kotlin
// Mendefinisikan paket untuk file Kotlin, memastikan kode terorganisir dan menghindari konflik penamaan.
package com.example.explicit_intent

// Mengimpor kelas-kelas yang diperlukan dari pustaka Android dan Jetpack Compose.
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.explicit_intent.ui.theme.Explicit_intentTheme

// Data class untuk merepresentasikan satu pesan chat.
// 'data class' secara otomatis menghasilkan fungsi seperti equals(), hashCode(), toString().
data class Message(val text: String, val sender: Sender, val senderName: String)

// Enum untuk mengidentifikasi pengirim pesan (Saya atau Orang Lain).
// Enum adalah tipe data khusus yang memungkinkan variabel menjadi satu set konstanta yang telah ditentukan.
enum class Sender {
    ME, OTHER
}

// Kelas utama untuk Activity pertama. Mewarisi dari ComponentActivity, basis untuk aplikasi Compose.
class MainActivity : ComponentActivity() {
    // Metode ini dipanggil saat Activity dibuat.
    override fun onCreate(savedInstanceState: Bundle?) {
        // Memanggil implementasi metode onCreate dari superclass. Ini wajib.
        super.onCreate(savedInstanceState)
        // Menetapkan konten UI Activity menggunakan Jetpack Compose.
        setContent {
            // Menerapkan tema kustom aplikasi.
            Explicit_intentTheme {
                // Surface adalah container dari Material Design yang menerapkan warna latar belakang.
                Surface(
                    modifier = Modifier.fillMaxSize(), // Mengisi seluruh ukuran layar.
                    color = MaterialTheme.colorScheme.background // Menggunakan warna latar dari tema.
                ) {
                    // Memanggil Composable utama yang berisi UI layar.
                    MainScreen()
                }
            }
        }
    }

    // Companion object digunakan untuk mendefinisikan konstanta yang terkait dengan kelas.
    companion object {
        // Kunci untuk data tambahan (extra) yang dikirim dalam Intent.
        // Praktik terbaik adalah menggunakan nama paket sebagai prefix untuk memastikan keunikan.
        const val EXTRA_MESSAGE = "com.example.explicit_intent.EXTRA_MESSAGE"
    }
}

// Anotasi @OptIn menunjukkan bahwa kita menggunakan API yang masih eksperimental dari Material 3.
@OptIn(ExperimentalMaterial3Api::class)
// Anotasi @Composable menandakan bahwa fungsi ini adalah komponen UI Jetpack Compose.
@Composable
fun MainScreen() {
    // Mendapatkan konteks lokal, yang diperlukan untuk membuat Intent.
    val context = LocalContext.current
    // 'remember' dan 'mutableStateOf' digunakan untuk membuat state yang akan diingat oleh Compose
    // di antara rekomposisi. Perubahan pada state ini akan memicu UI untuk diperbarui.
    var messageText by remember { mutableStateOf("") }
    // 'mutableStateListOf' membuat daftar yang dapat diobservasi. Perubahan pada daftar ini
    // (seperti menambah item) akan memicu rekomposisi.
    val messages = remember { mutableStateListOf<Message>() }

    // 'rememberLauncherForActivityResult' adalah hook Compose untuk Activity Result API.
    // Ini cara modern untuk memulai activity dan mendapatkan hasil kembali.
    val launcher = rememberLauncherForActivityResult(
        // 'StartActivityForResult' adalah kontrak standar yang menangani Intent dan hasil.
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result -> // Lambda ini dieksekusi ketika SecondActivity mengembalikan hasil.
        // Memeriksa apakah hasilnya sukses.
        if (result.resultCode == Activity.RESULT_OK) {
            // Mengambil data balasan dari Intent hasil.
            val reply = result.data?.getStringExtra(SecondActivity.EXTRA_REPLY)
            // Jika balasan tidak null atau kosong, tambahkan ke daftar pesan.
            if (!reply.isNullOrEmpty()) {
                messages.add(Message(reply, Sender.OTHER, "second"))
            }
        }
    }

    // Column adalah Composable tata letak yang menempatkan turunannya secara vertikal.
    Column(modifier = Modifier.fillMaxSize()) {
        // LazyColumn adalah daftar yang dapat di-scroll dan hanya me-render item yang terlihat di layar.
        // Ini efisien untuk daftar panjang.
        LazyColumn(
            modifier = Modifier
                .weight(1f) // Mengambil semua ruang vertikal yang tersedia.
                .padding(horizontal = 8.dp),
            reverseLayout = true // Item akan dimuat dari bawah ke atas.
        ) {
            // 'items' adalah DSL untuk menambahkan item ke LazyColumn dari sebuah daftar.
            items(messages.reversed()) { msg -> // Daftar dibalik agar pesan terbaru di bawah.
                MessageBubble(message = msg)
            }
        }

        // Row adalah Composable tata letak yang menempatkan turunannya secara horizontal.
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically // Menyelaraskan item di tengah secara vertikal.
        ) {
            // Input field teks dengan gaya Material Design.
            OutlinedTextField(
                value = messageText, // Nilai yang ditampilkan (terikat pada state).
                onValueChange = { messageText = it }, // Callback saat nilai berubah.
                label = { Text("Type a message") }, // Teks placeholder.
                modifier = Modifier.weight(1f) // Mengambil semua ruang horizontal yang tersedia.
            )
            // Memberi jarak horizontal.
            Spacer(modifier = Modifier.width(8.dp))
            // Tombol Material Design.
            Button(onClick = { // Lambda yang dieksekusi saat tombol diklik.
                if (messageText.isNotBlank()) {
                    // Tambahkan pesan baru ke daftar state.
                    messages.add(Message(messageText, Sender.ME, "main"))
                    // Buat Intent eksplisit untuk memulai SecondActivity.
                    val intent = Intent(context, SecondActivity::class.java).apply {
                        // 'apply' memungkinkan kita mengkonfigurasi objek tanpa mengulang namanya.
                        // Menambahkan data ke Intent menggunakan kunci yang telah ditentukan.
                        putExtra(MainActivity.EXTRA_MESSAGE, messageText)
                    }
                    // Meluncurkan SecondActivity dengan Intent yang dibuat dan menunggu hasilnya.
                    launcher.launch(intent)
                    // Mengosongkan input field setelah pesan dikirim.
                    messageText = ""
                }
            }) {
                Text("Kirim")
            }
        }
    }
}

@Composable
fun MessageBubble(message: Message) {
    // Menentukan apakah pesan ini dari "saya" atau "orang lain".
    val isMe = message.sender == Sender.ME
    // Menentukan perataan gelembung pesan (kanan jika dari saya, kiri jika dari orang lain).
    val alignment = if (isMe) Alignment.CenterEnd else Alignment.CenterStart
    // Menentukan warna gelembung pesan.
    val bubbleColor = if (isMe) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.secondaryContainer
    // Menentukan bentuk gelembung dengan sudut yang berbeda untuk meniru aplikasi chat.
    val shape = if (isMe) {
        RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 4.dp)
    } else {
        RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp, bottomStart = 4.dp, bottomEnd = 16.dp)
    }

    // Box adalah container dasar di Compose.
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        contentAlignment = alignment // Menyelaraskan konten di dalamnya (Column).
    ) {
        Column(
            horizontalAlignment = if (isMe) Alignment.End else Alignment.Start
        ) {
            // Menampilkan nama pengirim.
            Text(
                text = message.senderName,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 4.dp)
            )
            // Box untuk gelembung pesan itu sendiri.
            Box(
                modifier = Modifier
                    .clip(shape) // Menerapkan bentuk yang telah ditentukan.
                    .background(bubbleColor) // Menerapkan warna latar belakang.
                    .padding(horizontal = 16.dp, vertical = 10.dp) // Padding di dalam gelembung.
            ) {
                // Menampilkan teks pesan.
                Text(text = message.text, color = MaterialTheme.colorScheme.onSurface)
            }
        }
    }
}
```

</details>

<details>
<summary><strong>SecondActivity.kt</strong></summary>

File ini bertanggung jawab untuk layar kedua, yang menerima pesan, menampilkannya, dan memungkinkan pengguna mengirim balasan.

```kotlin
// Mendefinisikan paket untuk file.
package com.example.explicit_intent

// Mengimpor kelas-kelas yang diperlukan.
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.explicit_intent.ui.theme.Explicit_intentTheme

// Kelas untuk Activity kedua.
class SecondActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Mengambil Intent yang memulai Activity ini, lalu mengambil data string
        // dengan kunci EXTRA_MESSAGE. Jika tidak ada, gunakan nilai default "No message found".
        // Operator '?:' (Elvis operator) adalah singkatan untuk ini.
        val message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE) ?: "No message found"

        // Menetapkan UI menggunakan Jetpack Compose.
        setContent {
            Explicit_intentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Memanggil Composable untuk layar kedua.
                    SecondScreen(
                        message = message, // Meneruskan pesan yang diterima.
                        onReplyClicked = { replyText -> // Meneruskan lambda sebagai callback.
                            // Membuat Intent kosong untuk menampung data balasan.
                            val replyIntent = Intent()
                            // Menambahkan data balasan ke Intent.
                            replyIntent.putExtra(EXTRA_REPLY, replyText)
                            // Menetapkan hasil operasi sebagai RESULT_OK dan menyertakan Intent balasan.
                            setResult(Activity.RESULT_OK, replyIntent)
                            // Menutup Activity saat ini dan kembali ke Activity sebelumnya (MainActivity).
                            finish()
                        }
                    )
                }
            }
        }
    }

    // Companion object untuk konstanta.
    companion object {
        // Kunci unik untuk data balasan.
        const val EXTRA_REPLY = "com.example.explicit_intent.EXTRA_REPLY"
    }
}

@ExperimentalMaterial3Api
@Composable
fun SecondScreen(message: String, onReplyClicked: (String) -> Unit) {
    // State untuk menyimpan teks balasan dari pengguna.
    var replyText by remember { mutableStateOf("") }

    // Menata UI secara vertikal dan di tengah layar.
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center, // Menyelaraskan secara vertikal di tengah.
        horizontalAlignment = Alignment.CenterHorizontally // Menyelaraskan secara horizontal di tengah.
    ) {
        Text(text = "Received message:")
        // Menampilkan pesan yang diterima dengan gaya tipografi yang lebih besar.
        Text(text = message, style = MaterialTheme.typography.headlineSmall)
        // Memberi jarak vertikal.
        Spacer(modifier = Modifier.height(24.dp))
        // Input field untuk balasan.
        OutlinedTextField(
            value = replyText,
            onValueChange = { replyText = it },
            label = { Text("Type your reply") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        // Tombol untuk mengirim balasan.
        Button(onClick = { onReplyClicked(replyText) }) { // Memanggil callback saat diklik.
            Text("Balas")
        }
    }
}
```

</details>

<details>
<summary><strong>AndroidManifest.xml</strong></summary>

File ini adalah "peta" aplikasi Anda untuk sistem Android. Ia mendeklarasikan semua komponen penting seperti `Activity` dan `Service`.

```xml
<?xml version="1.0" encoding="utf-8"?>
<!-- Elemen root dari manifest. -->
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <!-- Mendeklarasikan aplikasi. -->
    <application
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.Explicit_intent"
        tools:targetApi="31">

        <!-- Deklarasi untuk SecondActivity. -->
        <activity
            android:name=".SecondActivity"
            android:exported="false" />
            <!-- android:exported="false":
                 Menunjukkan bahwa Activity ini tidak dapat diluncurkan oleh komponen dari aplikasi lain.
                 Ini adalah praktik keamanan yang baik untuk Activity internal. -->

        <!-- Deklarasi untuk MainActivity. -->
        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:label="@string/app_name"
            android:theme="@style/Theme.Explicit_intent">
            <!-- android:exported="true":
                 Menunjukkan bahwa Activity ini dapat diluncurkan oleh komponen dari aplikasi lain.
                 Ini WAJIB untuk Activity yang memiliki intent-filter LAUNCHER. -->

            <!-- Intent filter menentukan jenis Intent yang dapat direspons oleh Activity. -->
            <intent-filter>
                <!-- ACTION_MAIN: Menandakan ini adalah titik masuk utama aplikasi. -->
                <action android:name="android.intent.action.MAIN" />
                <!-- CATEGORY_LAUNCHER: Menandakan Activity ini harus muncul di peluncur aplikasi sistem. -->
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```
</details>