import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Menu> daftarMenu = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        inisialisasiMenu();
        boolean aplikasi = true;
        do {
            System.out.println("\n==================================");
            System.out.println("  APLIKASI RESTORAN");
            System.out.println("==================================");
            System.out.println("1. Menu Pelanggan (Pesan Makanan)");
            System.out.println("2. Menu Pemilik (Kelola Menu)");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu (1/2/3): ");

            String pilihan = input.nextLine();

            switch (pilihan) {
                case "1":
                    menuPelanggan();
                    break;
                case "2":
                    menuPemilik();
                    break;
                case "3":
                    aplikasi = false;
                    System.out.println("Terima kasih telah menggunakan aplikasi ini.");
                    break;
                default:
                    System.out.println("Input tidak valid! Silakan pilih 1, 2, atau 3.");
            }
        } while (aplikasi);
    }

    static void inisialisasiMenu() {
        daftarMenu.add(new Menu("Nasi Goreng Spesial", "Makanan", 25000));
        daftarMenu.add(new Menu("Mie Ayam Pangsit", "Makanan", 20000));
        daftarMenu.add(new Menu("Ayam Geprek Jumbo", "Makanan", 30000));
        daftarMenu.add(new Menu("Sate Ayam Madura", "Makanan", 35000));

        daftarMenu.add(new Menu("Es Teh Manis", "Minuman", 5000));
        daftarMenu.add(new Menu("Jus Alpukat", "Minuman", 15000));
        daftarMenu.add(new Menu("Kopi Susu Gula Aren", "Minuman", 12000));
        daftarMenu.add(new Menu("Air Mineral", "Minuman", 4000));
    }

    static void tampilkanMenuRestoran() {
        System.out.println("\n======= DAFTAR MENU RESTORAN =======");
        System.out.println("--- MAKANAN ---");
        for (int i = 0; i < daftarMenu.size(); i++) {
            if (daftarMenu.get(i).kategori.equalsIgnoreCase("Makanan")) {
                System.out.println((i + 1) + ". " + daftarMenu.get(i).nama + " - Rp" + daftarMenu.get(i).harga);
            }
        }

        System.out.println("\n--- MINUMAN ---");
        for (int i = 0; i < daftarMenu.size(); i++) {
            if (daftarMenu.get(i).kategori.equalsIgnoreCase("Minuman")) {
                System.out.println((i + 1) + ". " + daftarMenu.get(i).nama + " - Rp" + daftarMenu.get(i).harga);
            }
        }
        System.out.println("====================================");
    }

    static void menuPelanggan() {
        ArrayList<Pesanan> daftarPesanan = new ArrayList<>();
        System.out.println("\nSelamat Datang! Silakan lihat daftar menu kami.");


        while (true) {
            tampilkanMenuRestoran();
            System.out.println("\nKetik 'selesai' jika sudah selesai memesan.");
            System.out.print("Masukkan nomor menu pilihan Anda: ");
            String inputPilihan = input.nextLine();


            if (inputPilihan.equalsIgnoreCase("selesai")) {
                if (daftarPesanan.isEmpty()) {
                    System.out.println("Anda belum memesan apapun. Kembali ke Menu Utama.");
                    return;
                }
                break;
            }

            try {
                int noMenu = Integer.parseInt(inputPilihan);
                if (noMenu < 1 || noMenu > daftarMenu.size()) {
                    System.out.println("ERROR: Nomor menu tidak tersedia. Silakan masukkan nomor yang benar!");
                    continue;
                }

                Menu menuTerpilih = daftarMenu.get(noMenu - 1);
                int jumlahPesanan = 0;


                while (true) {
                    System.out.print("Masukkan jumlah pesanan untuk " + menuTerpilih.nama + ": ");
                    try {
                        jumlahPesanan = Integer.parseInt(input.nextLine());
                        if (jumlahPesanan <= 0) {
                            System.out.println("ERROR: Jumlah pesanan harus lebih dari 0!");
                            continue;
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("ERROR: Masukkan angka yang valid untuk jumlah!");
                    }
                }


                daftarPesanan.add(new Pesanan(menuTerpilih, jumlahPesanan));
                System.out.println("-> Berhasil menambahkan " + jumlahPesanan + " " + menuTerpilih.nama + " ke pesanan.");

            } catch (NumberFormatException e) {
                System.out.println("ERROR: Input tidak valid. Masukkan angka atau ketik 'selesai'.");
            }
        }

        cetakStrukDanTotal(daftarPesanan);
    }

    static void cetakStrukDanTotal(ArrayList<Pesanan> pesanan) {
        int subTotal = 0;
        int promoMinuman = 0;
        String namaPromoMinuman = "";

        System.out.println("\n==============================================");
        System.out.println("               STRUK PESANAN                  ");
        System.out.println("==============================================");

        for (Pesanan p : pesanan) {
            int totalPerItem = p.menu.harga * p.jumlah;
            subTotal += totalPerItem;
            System.out.println(p.menu.nama);
            System.out.println("   " + p.jumlah + " x Rp" + p.menu.harga + " = Rp" + totalPerItem);
        }

        System.out.println("----------------------------------------------");
        System.out.println("Subtotal                    : Rp" + subTotal);


        if (subTotal > 50000) {
            for (Pesanan p : pesanan) {
                if (p.menu.kategori.equalsIgnoreCase("Minuman") && p.jumlah >= 2) {
                    promoMinuman = p.menu.harga;
                    namaPromoMinuman = p.menu.nama;
                    break;
                }
            }
        }

        if (promoMinuman > 0) {
            System.out.println("Promo (B1G1 " + namaPromoMinuman + ") : -Rp" + promoMinuman);
        }

        int totalSetelahPromo = subTotal - promoMinuman;
        int diskon = 0;


        if (totalSetelahPromo > 100000) {
            diskon = totalSetelahPromo * 10 / 100;
            System.out.println("Diskon 10% (>100k)          : -Rp" + diskon);
        }

        int totalSetelahDiskon = totalSetelahPromo - diskon;


        int pajak = totalSetelahDiskon * 10 / 100;
        int biayaPelayanan = 20000;
        int grandTotal = totalSetelahDiskon + pajak + biayaPelayanan;

        System.out.println("Pajak 10%                   : Rp" + pajak);
        System.out.println("Biaya Pelayanan             : Rp" + biayaPelayanan);
        System.out.println("----------------------------------------------");
        System.out.println("TOTAL BAYAR KESELURUHAN     : Rp" + grandTotal);
        System.out.println("==============================================\n");
    }

    static void menuPemilik() {
        while (true) {
            System.out.println("\n=== PENGELOLAAN MENU (PEMILIK) ===");
            System.out.println("1. Tambah Menu Baru");
            System.out.println("2. Ubah Harga Menu");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Kembali ke Menu Utama");
            System.out.print("Pilih aksi (1-4): ");
            String aksi = input.nextLine();

            switch (aksi) {
                case "1":
                    tambahMenu();
                    break;
                case "2":
                    ubahHargaMenu();
                    break;
                case "3":
                    hapusMenu();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    static void tambahMenu() {
        System.out.println("\n-- Tambah Menu Baru --");
        while (true) {
            System.out.print("Nama Menu   : ");
            String nama = input.nextLine();
            System.out.print("Kategori (Makanan/Minuman): ");
            String kat = input.nextLine();
            System.out.print("Harga       : ");

            try {
                int harga = Integer.parseInt(input.nextLine());
                daftarMenu.add(new Menu(nama, kat, harga));
                System.out.println("Menu berhasil ditambahkan!");
            } catch (Exception e) {
                System.out.println("Gagal menambahkan! Harga harus berupa angka.");
            }

            System.out.print("Tambah menu lagi? (ya/tidak): ");
            String lanjut = input.nextLine();
            if (!lanjut.equalsIgnoreCase("ya")) {
                break;
            }
        }
    }

    static void ubahHargaMenu() {
        tampilkanMenuRestoran();
        System.out.print("\nMasukkan nomor menu yang ingin diubah harganya: ");
        try {
            int no = Integer.parseInt(input.nextLine());
            if (no >= 1 && no <= daftarMenu.size()) {
                Menu m = daftarMenu.get(no - 1);
                System.out.print("Anda yakin ingin mengubah harga " + m.nama + "? (ya/tidak): ");
                if (input.nextLine().equalsIgnoreCase("ya")) {
                    System.out.print("Masukkan harga baru: ");
                    m.harga = Integer.parseInt(input.nextLine());
                    System.out.println("Harga berhasil diubah!");
                } else {
                    System.out.println("Perubahan dibatalkan.");
                }
            } else {
                System.out.println("Nomor menu tidak ditemukan!");
            }
        } catch (Exception e) {
            System.out.println("Input tidak valid!");
        }
    }

    static void hapusMenu() {
        tampilkanMenuRestoran();
        System.out.print("\nMasukkan nomor menu yang ingin dihapus: ");
        try {
            int no = Integer.parseInt(input.nextLine());
            if (no >= 1 && no <= daftarMenu.size()) {
                Menu m = daftarMenu.get(no - 1);
                System.out.print("Anda yakin ingin menghapus " + m.nama + " secara permanen? (ya/tidak): ");
                if (input.nextLine().equalsIgnoreCase("ya")) {
                    daftarMenu.remove(no - 1);
                    System.out.println("Menu berhasil dihapus!");
                } else {
                    System.out.println("Penghapusan dibatalkan.");
                }
            } else {
                System.out.println("Nomor menu tidak ditemukan!");
            }
        } catch (Exception e) {
            System.out.println("Input tidak valid!");
        }
    }
}