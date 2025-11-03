# Analisis Proyek Explicit Intent Android

Dokumen ini memberikan analisis mendalam tentang proyek Android "Explicit Intent". Proyek ini menunjukkan cara memulai `Activity` baru dan mengirim data di antara keduanya menggunakan `Intent` eksplisit.

## Struktur Proyek

Proyek ini mengikuti struktur standar aplikasi Android. Berikut adalah file dan direktori penting:

- **`app`**: Modul utama aplikasi.
    - **`src/main/java`**: Berisi kode sumber Kotlin.
        - **`MainActivity.kt`**: Titik masuk utama aplikasi.
        - **`SecondActivity.kt`**: Activity kedua yang diluncurkan dari `MainActivity`.
    - **`src/main/res`**: Berisi semua sumber daya non-kode.
        - **`layout`**: (Tidak ada di daftar awal, tetapi secara implisit ada untuk `setContentView`) File UI XML.
        - **`values`**: File XML yang berisi nilai-nilai seperti string dan warna.
    - **`src/main/AndroidManifest.xml`**: Mendeklarasikan komponen aplikasi dan izin.
    - **`build.gradle.kts`**: Skrip build untuk modul `app`.
- **`build.gradle.kts` (tingkat proyek)**: Skrip build untuk seluruh proyek.
- **`settings.gradle.kts`**: Mendefinisikan modul yang termasuk dalam build.
- **`gradle/libs.versions.toml`**: Mengelola dependensi dan versinya secara terpusat.

---

## Analisis File Kode

### `app/src/main/java/com/example/explicit_intent/MainActivity.kt`

File ini mendefinisikan `Activity` utama yang muncul saat aplikasi diluncurkan.

```kotlin
package com.example.explicit_intent

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.explicit_intent.ui.theme.Explicit_intentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }
}
```

**Penjelasan Baris per Baris:**

- **`package com.example.explicit_intent`**:
    - **`package`**: Kata kunci Kotlin yang mendeklarasikan paket untuk file ini.
    - **`com.example.explicit_intent`**: Nama paket, yang merupakan pengidentifikasi unik untuk aplikasi Anda. Ini juga digunakan untuk mengatur file sumber Anda dalam struktur direktori.

- **`import ...`**:
    - **`import`**: Kata kunci yang memungkinkan Anda menggunakan kelas dan fungsi dari pustaka lain.
    - **`android.content.Intent`**: Mengimpor kelas `Intent`, yang merupakan objek pesan yang dapat Anda gunakan untuk meminta tindakan dari komponen aplikasi lain.
    - **`android.os.Bundle`**: Mengimpor kelas `Bundle`, yang digunakan untuk meneruskan data antar `Activity`. `savedInstanceState` adalah sebuah `Bundle`.
    - **`androidx.activity.ComponentActivity`**: Mengimpor kelas dasar untuk `Activity` yang menggunakan Jetpack Compose.
    - **`...`**: Impor lain untuk Jetpack Compose, yang merupakan toolkit UI modern Android.

- **`class MainActivity : ComponentActivity()`**:
    - **`class`**: Kata kunci Kotlin untuk mendeklarasikan sebuah kelas.
    - **`MainActivity`**: Nama kelas. Sesuai konvensi, nama `Activity` diakhiri dengan "Activity".
    - **`:`**: Simbol yang menunjukkan pewarisan. `MainActivity` mewarisi dari `ComponentActivity`.
    - **`ComponentActivity()`**: Superclass. `MainActivity` mendapatkan semua fungsionalitas dari `ComponentActivity`. Tanda kurung `()` menunjukkan pemanggilan konstruktor superclass.

- **`override fun onCreate(savedInstanceState: Bundle?)`**:
    - **`override`**: Kata kunci yang menunjukkan bahwa fungsi ini menggantikan fungsi dengan nama yang sama dari superclass (`ComponentActivity`).
    - **`fun`**: Kata kunci untuk mendeklarasikan sebuah fungsi.
    - **`onCreate`**: Nama fungsi. Metode ini dipanggil ketika `Activity` pertama kali dibuat. Ini adalah tempat Anda harus melakukan semua inisialisasi normal.
    - **`savedInstanceState: Bundle?`**: Parameter fungsi.
        - **`savedInstanceState`**: Nama parameter. Ini adalah objek `Bundle` yang berisi status `Activity` yang terakhir disimpan.
        - **`:`**: Memisahkan nama parameter dari jenisnya.
        - **`Bundle`**: Jenis parameter.
        - **`?`**: Simbol "safe call". Ini menunjukkan bahwa parameter `savedInstanceState` bisa `null`.

- **`super.onCreate(savedInstanceState)`**:
    - **`super`**: Mengacu pada superclass (`ComponentActivity`).
    - **`.`**: Operator akses anggota.
    - **`onCreate(savedInstanceState)`**: Memanggil implementasi `onCreate` dari superclass. **Penting:** Anda harus selalu memanggil metode superclass ini.

- **`val intent = Intent(this, SecondActivity::class.java)`**:
    - **`val`**: Kata kunci untuk mendeklarasikan variabel hanya-baca (immutable).
    - **`intent`**: Nama variabel.
    - **`=`**: Operator penugasan.
    - **`Intent(...)`**: Membuat instance baru dari kelas `Intent`.
        - **`this`**: Argumen pertama, merujuk pada konteks saat ini, yaitu instance `MainActivity`.
        - **`,`**: Memisahkan argumen.
        - **`SecondActivity::class.java`**: Argumen kedua. Ini adalah referensi kelas dari `Activity` yang ingin Anda mulai. `::class` adalah sintaks Kotlin untuk mendapatkan referensi `KClass`, dan `.java` mengubahnya menjadi referensi `Class` Java yang dibutuhkan oleh konstruktor `Intent`.

- **`startActivity(intent)`**:
    - **`startActivity`**: Sebuah metode (diwarisi dari `ComponentActivity`) yang meluncurkan `Activity` baru.
    - **`intent`**: Argumen yang diteruskan ke `startActivity`. Sistem Android menerima `Intent` ini dan meluncurkan `SecondActivity` yang ditentukan di dalamnya.

### `app/src/main/java/com/example/explicit_intent/SecondActivity.kt`

File ini mendefinisikan `Activity` kedua, yang kosong dan hanya menampilkan konten default.

```kotlin
package com.example.explicit_intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }
}
```
*(Catatan: `R.layout.activity_second` tidak ada dalam daftar file awal, tetapi diasumsikan ada berdasarkan kode ini. Jika tidak, kode ini akan menyebabkan error saat runtime.)*

**Penjelasan Baris per Baris:**

- **`package com.example.explicit_intent`**: Sama seperti di `MainActivity.kt`.
- **`import ...`**:
    - **`androidx.appcompat.app.AppCompatActivity`**: Mengimpor kelas dasar yang berbeda untuk `Activity`. `AppCompatActivity` menyediakan kompatibilitas mundur untuk fitur UI di versi Android yang lebih lama.
    - **`android.os.Bundle`**: Sama seperti di `MainActivity.kt`.

- **`class SecondActivity : AppCompatActivity()`**:
    - **`class SecondActivity`**: Mendeklarasikan kelas `SecondActivity`.
    - **`: AppCompatActivity()`**: Mewarisi dari `AppCompatActivity`.

- **`override fun onCreate(savedInstanceState: Bundle?)`**: Sama seperti di `MainActivity.kt`, metode ini dipanggil saat `SecondActivity` dibuat.

- **`super.onCreate(savedInstanceState)`**: Memanggil implementasi superclass, yang wajib.

- **`setContentView(R.layout.activity_second)`**:
    - **`setContentView`**: Metode untuk mengatur tata letak UI `Activity`.
    - **`R.layout.activity_second`**: Argumen adalah referensi ke file tata letak.
        - **`R`**: Kelas yang dibuat secara otomatis yang berisi ID semua sumber daya dalam proyek.
        - **`layout`**: Subkelas di dalam `R` untuk sumber daya tata letak.
        - **`activity_second`**: Nama file tata letak XML (misalnya, `activity_second.xml`) di direktori `res/layout`.

---

### `app/src/main/AndroidManifest.xml`

File ini adalah "peta" aplikasi Anda untuk sistem Android.

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

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
        <activity
            android:name=".SecondActivity"
            android:exported="false" />
        <activity
            android:name=".MainActivity"
            android:exported="true"
            android:label="@string/app_name"
            android:theme="@style/Theme.Explicit_intent">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```

**Penjelasan Elemen:**

- **`<manifest>`**: Elemen root dari file.
    - **`xmlns:android`**: Mendefinisikan namespace XML untuk atribut Android.
    - **`package`**: (Tidak ada di sini, tetapi sering ada) Nama paket aplikasi.

- **`<application>`**: Mendeklarasikan aplikasi.
    - **`android:allowBackup="true"`**: Mengizinkan pengguna untuk mencadangkan data aplikasi.
    - **`android:icon="@mipmap/ic_launcher"`**: Menentukan ikon aplikasi.
    - **`android:label="@string/app_name"`**: Menentukan nama aplikasi yang ditampilkan kepada pengguna. Nilainya diambil dari `res/values/strings.xml`.
    - **`android:theme="@style/Theme.Explicit_intent"`**: Menentukan tema default untuk semua `Activity`.

- **`<activity>`**: Mendeklarasikan sebuah `Activity`. Setiap `Activity` dalam aplikasi Anda harus dideklarasikan di sini.
    - **`android:name=".MainActivity"`**: Nama kelas `Activity`. Tanda titik `.` di awal adalah singkatan untuk nama paket aplikasi.
    - **`android:exported="true"`**:
        - **`true`**: `Activity` ini dapat diluncurkan oleh komponen dari aplikasi lain. Ini **wajib** untuk `Activity` utama (peluncur).
    - **`<intent-filter>`**: Menentukan jenis `Intent` yang dapat direspons oleh `Activity`.
        - **`<action android:name="android.intent.action.MAIN" />`**: Menunjukkan bahwa ini adalah titik masuk utama aplikasi.
        - **`<category android:name="android.intent.category.LAUNCHER" />`**: Menunjukkan bahwa `Activity` ini harus muncul di peluncur aplikasi di layar utama.

- **`<activity android:name=".SecondActivity" android:exported="false" />`**:
    - **`android:name=".SecondActivity"`**: Mendeklarasikan `SecondActivity`.
    - **`android:exported="false"`**:
        - **`false`**: `Activity` ini hanya dapat diluncurkan oleh komponen dari aplikasi yang sama. Ini adalah praktik keamanan yang baik untuk `Activity` yang tidak dimaksudkan untuk diakses dari luar.

---

## Analisis File Build

### `gradle/libs.versions.toml`

File ini menggunakan [Gradle Version Catalog](https://docs.gradle.org/current/userguide/version_catalogs.html) untuk mengelola dependensi. Ini adalah cara modern dan terpusat untuk menangani versi pustaka.

```toml
[versions]
activityCompose = "1.8.0"
# ... versi lainnya

[libraries]
androidx-activity-compose = { group = "androidx.activity", name = "activity-compose", version.ref = "activityCompose" }
# ... pustaka lainnya

[plugins]
androidApplication = { id = "com.android.application", version.ref = "agp" }
# ... plugin lainnya

[bundles]
compose = ["androidx-compose-ui", "androidx-compose-ui-graphics", "androidx-compose-ui-tooling-preview", "androidx-compose-material3"]
```

**Penjelasan Bagian:**

- **`[versions]`**: Mendefinisikan variabel untuk nomor versi. Ini memungkinkan Anda memperbarui versi di satu tempat.
    - **`activityCompose = "1.8.0"`**: Mendefinisikan alias `activityCompose` dengan nilai string `"1.8.0"`.
- **`[libraries]`**: Mendefinisikan alias untuk setiap dependensi.
    - **`androidx-activity-compose = { ... }`**: Mendefinisikan alias `androidx-activity-compose`.
        - **`group`**: ID grup Maven dari artefak.
        - **`name`**: Nama artefak.
        - **`version.ref = "activityCompose"`**: Mengacu pada versi yang didefinisikan di bagian `[versions]`.
- **`[plugins]`**: Mendefinisikan alias untuk plugin Gradle.
- **`[bundles]`**: Mendefinisikan grup dependensi yang sering digunakan bersama.
    - **`compose = [...]`**: Membuat bundel bernama `compose` yang berisi beberapa pustaka Jetpack Compose.

### `build.gradle.kts` (Tingkat Proyek)

Skrip ini mengkonfigurasi properti build yang berlaku untuk semua modul dalam proyek.

```kotlin
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
}
```

**Penjelasan:**

- **`plugins { ... }`**: Blok untuk mendeklarasikan plugin.
- **`alias(libs.plugins.androidApplication) apply false`**:
    - **`alias(libs.plugins.androidApplication)`**: Mengambil plugin `androidApplication` dari version catalog (`libs`).
    - **`apply false`**: Menerapkan plugin ini ke subproyek (modul), bukan ke proyek root itu sendiri. Ini adalah praktik standar dalam build Gradle modern.

### `app/build.gradle.kts` (Tingkat Modul)

Skrip ini mengkonfigurasi build untuk modul `app`.

```kotlin
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.example.explicit_intent"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.explicit_intent"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        // ...
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            // ...
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.compose)
    // ...
}
```

**Penjelasan Bagian:**

- **`plugins { ... }`**: Menerapkan plugin yang diperlukan untuk build aplikasi Android dengan Kotlin dan Compose.
    - **`alias(libs.plugins.androidApplication)`**: Menerapkan plugin aplikasi Android.

- **`android { ... }`**: Blok konfigurasi utama untuk plugin Android.
    - **`namespace`**: Pengidentifikasi unik untuk kode sumber Kotlin/Java.
    - **`compileSdk = 34`**: Menentukan API level Android yang digunakan untuk mengkompilasi aplikasi Anda (Android 14).
    - **`defaultConfig { ... }`**: Konfigurasi default yang berlaku untuk semua varian build.
        - **`applicationId`**: Pengidentifikasi unik untuk aplikasi di Google Play Store.
        - **`minSdk = 24`**: API level minimum yang dapat menjalankan aplikasi Anda (Android 7.0).
        - **`targetSdk = 34`**: API level yang ditargetkan aplikasi Anda.
        - **`versionCode = 1`**: Nomor versi internal. Harus dinaikkan untuk setiap rilis.
        - **`versionName = "1.0"`**: Nomor versi yang ditampilkan kepada pengguna.
    - **`buildFeatures { compose = true }`**: Mengaktifkan dukungan untuk Jetpack Compose.

- **`dependencies { ... }`**: Blok untuk mendeklarasikan dependensi pustaka.
    - **`implementation(...)`**: Menambahkan dependensi ke classpath kompilasi dan mengemasnya ke dalam APK.
    - **`libs.androidx.core.ktx`**: Mengambil dependensi `core-ktx` dari version catalog.
    - **`platform(libs.androidx.compose.bom)`**: Mengimpor "Bill of Materials" (BOM) Compose. Ini memastikan bahwa semua pustaka Compose Anda menggunakan versi yang kompatibel satu sama lain.
    - **`libs.bundles.compose`**: Menambahkan semua pustaka dari bundel `compose` yang didefinisikan di `libs.versions.toml`.