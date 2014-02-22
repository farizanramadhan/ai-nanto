/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itb.ai.komponen;

/**
 *
 * @author Setyo Legowo <setyo.legowo@live.com>
 */
public class Barang {
    private static int TOTAL = 0;
    
    /*
     * MEMBER
     */
    private String nama;
    private char kode;
    private int harga;
    private int stok_sekarang;
    private int stok_harian;
    private static Barang[] barang;

    /**
     * KONSTRUKTOR
     */
    public static void inisialisasi() {
        if(TOTAL > 0 && barang == null) {
            barang = new Barang[TOTAL];
            for(int i = 0; i < TOTAL; i++) {
                barang[i] = new Barang(null, ' ', 0, 0);
            }
        }
    }
    
    private Barang(String nama, char kode, int harga, int stok_harian) {
        this.nama = nama;
        this.kode = kode;
        this.harga = harga;
        this.stok_sekarang = stok_harian;
        this.stok_harian = stok_harian;
    }
    
    /**
     * METODA
     */
    public static void inisialisasiTotalBarang(int value) {
        if(TOTAL == 0) TOTAL = value;
    }
    
    public static void setAll(int indeks, String nama, char kode, int harga, 
            int stok_harian) {
        barang[indeks].setAll(nama, kode, harga, stok_harian);
    }
    
    public void setAll(String nama, char kode, int harga, int stok_harian) {
        this.nama = nama;
        this.kode = kode;
        this.harga = harga;
        this.stok_harian = stok_harian;
    }
    
    public static void setNama(char kode, String nama) {
        barang[indeksBarang(kode)].setNama(nama);
    }
    
    public static void setNama(int indeks, String nama) {
        barang[indeks].setNama(nama);
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public static void setKode(int indeks, char kode) {
        barang[indeks].setKode(kode);
    }
    
    public void setKode(char kode) {
        this.kode = kode;
    }

    public static void setHarga(char kode, int harga) {
        barang[indeksBarang(kode)].setHarga(harga);
    }
    
    public static void setHarga(int indeks, int harga) {
        barang[indeks].setHarga(harga);
    }
    
    public void setHarga(int harga) {
        this.harga = harga;
    }
    
    public static void resetAllStokSekarang() {
        for(int i = 0; i < TOTAL; i++) {
            barang[i].resetStok_sekarang();
        }
    }
    
    public static void resetStokSekarang(char kode) {
        barang[indeksBarang(kode)].resetStok_sekarang();
    }
    
    public static void resetStokSekarang(int indeks) {
        barang[indeks].resetStok_sekarang();
    }
    
    public void resetStok_sekarang() {
        this.stok_sekarang = this.stok_harian;
    }
    
    public static void decStokSekarang(char kode) {
        barang[indeksBarang(kode)].decStok_sekarang();
    }
    
    public static void decStokSekarang(int indeks) {
        barang[indeks].decStok_sekarang();
    }
    
    public void decStok_sekarang() {
        this.stok_sekarang--;
    }
    
    public static void setStokSekarang(char kode, int stok_sekarang) {
        barang[indeksBarang(kode)].setStok_sekarang(stok_sekarang);
    }
    
    public static void setStokSekarang(int indeks, int stok_sekarang) {
        barang[indeks].setStok_sekarang(stok_sekarang);
    }
    
    public void setStok_sekarang(int stok_sekarang) {
        this.stok_sekarang = stok_sekarang;
    }
    
    public static void setStokHarian(char kode, int stok_harian) {
        barang[indeksBarang(kode)].setStok_harian(stok_harian);
    }
    
    public static void setStokHarian(int indeks, int stok_harian) {
        barang[indeks].setStok_harian(stok_harian);
    }
    
    public void setStok_harian(int stok_harian) {
        this.stok_harian = stok_harian;
    }

    public static boolean isBarangEksis(char _kode) {
        int i;
        
        for(i = 0; i < TOTAL; i++) {
            if(barang[i].kode == _kode)
                break;
        }
        
        return i!=TOTAL;
    }
    
    public static int getTotalBarang() {
        return TOTAL;
    }
    
    private static int indeksBarang(char kode) {
        inisialisasi();
        if(TOTAL > 0) {
            int indeks_found = -1;
            for(int i = 0; i < TOTAL && indeks_found == -1; i++) {
                if(barang[i].getKode() == kode)
                    indeks_found = i;
            }
            return indeks_found;
        } else
            return -1;
    }
    
    public static String getNama(int indeks) {
        return barang[indeks].getNama();
    }
    
    public static String getNama(char kode) {
        int indeks = indeksBarang(kode);
        if(indeks >= 0)
            return barang[indeks].getNama();
        else
            return null;
    }
    
    public String getNama() {
        return nama;
    }

    public static char getKode(int indeks) {
        return barang[indeks].getKode();
    }
    
    public char getKode() {
        return kode;
    }

    public static int getHarga(int indeks) {
        return barang[indeks].getHarga();
    }
    
    public static int getHarga(char kode) {
        int indeks = indeksBarang(kode);
        if(indeks >= 0)
            return barang[indeks].getHarga();
        else
            return -1;
    }
    
    public int getHarga() {
        return harga;
    }
    
    public static int getStokSekarang(char kode) {
        return barang[indeksBarang(kode)].getStok_sekarang();
    }
    
    public static int getStokSekarang(int indeks) {
        return barang[indeks].getStok_sekarang();
    }
    
    public int getStok_sekarang() {
        return stok_sekarang;
    }

    public static int getStokHarian(int indeks) {
        return barang[indeks].getStok_harian();
    }
    
    public static int getStokHarian(char kode) {
        int indeks = indeksBarang(kode);
        if(indeks >= 0)
            return barang[indeks].getStok_harian();
        else
            return -1;
    }
    
    public int getStok_harian() {
        return stok_harian;
    }
    
    public static String getGenKandidat() {
        String kandidat = "";
        for(int i = 0; i < TOTAL; i++) {
            kandidat += barang[i].getKode();
        }
        return kandidat;
    }

    @Override
    public String toString() {
        return "Barang{" + "nama=" + nama + ", kode=" + kode + ", harga=" + harga + ", stok_harian=" + stok_harian + '}';
    }

}
