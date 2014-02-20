/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itb.ai;

/**
 *
 * @author Setyo Legowo <setyo.legowo@live.com>
 */
public class Barang {
    /*
     * MEMBER
     */
    private String nama;
    private String kode;
    private int harga;
    private int stok_harian;

    /**
     * KONSTRUKTOR
     */
    public Barang(String nama, String kode, int harga, int stok_harian) {
        this.nama = nama;
        this.kode = kode;
        this.harga = harga;
        this.stok_harian = stok_harian;
    }
    
    /**
     * METODA
     */
    public void setAll(String nama, String kode, int harga, int stok_harian) {
        this.nama = nama;
        this.kode = kode;
        this.harga = harga;
        this.stok_harian = stok_harian;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public void setStok_harian(int stok_harian) {
        this.stok_harian = stok_harian;
    }

    public String getNama() {
        return nama;
    }

    public String getKode() {
        return kode;
    }

    public int getHarga() {
        return harga;
    }

    public int getStok_harian() {
        return stok_harian;
    }    

    @Override
    public String toString() {
        return "Barang{" + "nama=" + nama + ", kode=" + kode + ", harga=" + harga + ", stok_harian=" + stok_harian + '}';
    }

}
