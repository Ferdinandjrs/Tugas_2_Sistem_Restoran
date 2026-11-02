# Aplikasi Pemesanan Restoran Java 

Aplikasi berbasis *Command Line Interface* (CLI) sederhana yang ditulis menggunakan bahasa Java. Program ini dibuat untuk memenuhi Tugas Praktik 2 mata kuliah Pemrograman Java, mengimplementasikan konsep *Object-Oriented Programming* (OOP), struktur pengulangan, struktur keputusan, dan `ArrayList` dinamis.

## Fitur Utama 
Program ini memiliki dua mode akses utama:
1. **Mode Pelanggan (Pemesanan)**
    - Menampilkan daftar menu makanan dan minuman.
    - Pemesanan menu tanpa batas (hingga mengetik 'selesai').
    - Validasi input (anti-error) jika memasukkan teks pada isian angka atau menginput angka negatif.
    - Perhitungan total otomatis lengkap dengan Pajak (10%) dan Biaya Pelayanan (Rp 20.000).
    - **Sistem Promo Beli 1 Gratis 1:** Otomatis memotong harga minuman jika subtotal > Rp50.000 dan membeli minimal 2 porsi minuman.
    - **Sistem Diskon 10%:** Otomatis memotong 10% dari total tagihan jika subtotal pesanan > Rp100.000.

2. **Mode Pemilik (Manajemen Menu)**
    - Menambah menu baru (dinamis menggunakan `ArrayList`).
    - Mengubah harga menu restoran beserta validasinya.
    - Menghapus menu dari daftar restoran.

## Struktur File 
- `Menu.java` : *Class blueprint* untuk atribut data menu (Nama, Kategori, Harga).
- `Pesanan.java` : *Class blueprint* untuk keranjang pesanan pelanggan.
- `Main.java` : *Class* utama yang mengendalikan alur program (Menu Utama, Validasi, Looping, dan Perhitungan).
