# Campus Manager

Campus Manager adalah aplikasi Android yang dirancang untuk memudahkan pengelolaan data mahasiswa di kampus. Aplikasi ini menyediakan fitur autentikasi pengguna dan pendataan mahasiswa dengan menggunakan Firebase sebagai backend.

## Demo Aplikasi
[Campus Manager App Demo Link](https://drive.google.com/file/d/16jDVMyTDjmdHZHtmc_jcfs58sL5969BH/view?usp=sharing)

## Fitur

### 1. Autentikasi Pengguna
- **Login**: Pengguna dapat masuk ke aplikasi menggunakan email dan kata sandi yang terdaftar di Firebase.
- **Register**: Pengguna dapat membuat akun baru dengan memasukkan email dan kata sandi.

### 2. Pendataan Mahasiswa (CRUD)
Aplikasi ini memungkinkan pengguna untuk melakukan operasi CRUD (Create, Read, Update, Delete) pada data mahasiswa:
- **Create**: Menambahkan data mahasiswa baru ke dalam database.
- **Read**: Melihat daftar mahasiswa yang terdaftar.
- **Update**: Memperbarui informasi mahasiswa yang ada.
- **Delete**: Menghapus data mahasiswa dari database.

## Teknologi yang Digunakan
- **Android**: Pengembangan aplikasi menggunakan Kotlin dan Android Studio.
- **Firebase**: Digunakan untuk autentikasi dan penyimpanan data mahasiswa.

## Persyaratan
- Android SDK 34 atau lebih tinggi
- Koneksi internet untuk mengakses Firebase

## Instalasi
1. Clone repositori ini:
   ```bash
   git clone https://github.com/username/campus-manager.git

2. Buka proyek di Android Studio.
3. Sinkronkan gradle dan pastikan semua dependensi terinstal.
4. Jalankan aplikasi pada emulator atau perangkat fisik.

## Cara Penggunaan
1. Membuat Akun: Pilih opsi "Register" untuk membuat akun baru.
2. Masuk: Setelah akun dibuat, gunakan email dan kata sandi untuk login.
3. Pendataan Mahasiswa: Setelah login, pengguna dapat menambah, melihat, memperbarui, atau menghapus data mahasiswa melalui antarmuka yang disediakan.

## Kontribusi
Kontribusi sangat diterima! Jika Anda ingin berkontribusi, silakan buat pull request atau buka isu untuk diskusi.