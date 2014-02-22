/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itb.ai.komponen;

import java.util.ArrayList;

/**
 *
 * @author Setyo Legowo <setyo.legowo@live.com>
 */
public class Nanto {
    public final static String TAG = Nanto.class.getSimpleName();
    public static enum ATRIBUT { MONEY, STRENGTH, CHARM, BRAIN }
    
    private static Nanto my;
    
    /**
     * MEMBER
     */
    private int waktu_eksekusi; // satua minggu
    private int energi_harian;
    private int energi_sekarang;
    private ArrayList<String> box_barang;
    private ArrayList<Integer> box_barang_jumlah;
    private int[] nilai_attribut;
    
    /**
     * KONSTRUKTOR
     */
    public static void Inisialisasi() {
        if(my == null) { 
            my = new Nanto(0,0,0,0,0,0,0);
        }
    }
    
    private Nanto(int money, int strength, int charm, int brain, int waktu_eksekusi, int energi_harian, int energi_sekarang) {
        this.nilai_attribut = new int[4];
        this.nilai_attribut[0] = money;
        this.nilai_attribut[1] = strength;
        this.nilai_attribut[2] = charm;
        this.nilai_attribut[3] = brain;
        this.box_barang = new ArrayList<>();
        this.box_barang_jumlah = new ArrayList<>();
        this.waktu_eksekusi = waktu_eksekusi;
        this.energi_harian = energi_harian;
        this.energi_sekarang = energi_sekarang;
    }
    
    public static Nanto getOriginCopy() {
        Inisialisasi();
        return new Nanto(getMoney(), getStrength(), getCharm(), 
                getBrain(), getWaktu_eksekusi(), getEnergi_harian(), getEnergi_sekarang());
    }
    
    /**
     * METODA
     */
    public static String my_name() {
        return TAG;
    }
    
    private static Nanto getMy() {
        Inisialisasi();
        return my;
    }
    
    public static int getAtribut(Nanto.ATRIBUT att) {
        Nanto me = Nanto.getMy();
        return me.getatribut(att);
    }
    
    public int getatribut(Nanto.ATRIBUT att) {
        int temp_val = 0;
        
        switch(att) {
            case MONEY: temp_val = this.nilai_attribut[0]; break;
            case STRENGTH: temp_val = this.nilai_attribut[1]; break;
            case CHARM: temp_val = this.nilai_attribut[2]; break;
            case BRAIN: temp_val = this.nilai_attribut[3]; break;
        }
        
        return temp_val;
    }
    
    public static int getAtribut(int index) {
        Nanto me = Nanto.getMy();
        return me.getatribut(index);
    }
    
    public int getatribut(int index) {
        return (index >= 0 && index < this.nilai_attribut.length)?this.nilai_attribut[index]:0;
    }
    
    public static int getMoney() {
        return getAtribut(ATRIBUT.MONEY);
    }
    
    public int getmoney() {
        return getatribut(ATRIBUT.MONEY);
    }
    
    public static int getStrength() {
        return getAtribut(ATRIBUT.STRENGTH);
    }
    
    public int getstrength() {
        return getatribut(ATRIBUT.STRENGTH);
    }
    
    public static int getCharm() {
        return getAtribut(ATRIBUT.CHARM);
    }
    
    public int getcharm() {
        return getatribut(ATRIBUT.CHARM);
    }
    
    public static int getBrain() {
        return getAtribut(ATRIBUT.BRAIN);
    }
    
    public int getbrain() {
        return getatribut(ATRIBUT.BRAIN);
    }

    public static int getWaktu_eksekusi() {
        Nanto me = Nanto.getMy();
        return me.getWaktuEksekusi();
    }
    
    public int getWaktuEksekusi() {
        return this.waktu_eksekusi;
    }

    public static int getEnergi_harian() {
        Nanto me = Nanto.getMy();
        return me.getEnergiHarian();
    }
    
    public int getEnergiHarian() {
        return this.energi_harian;
    }

    public static int getEnergi_sekarang() {
        Nanto me = Nanto.getMy();
        return me.getEnergiSekarang();
    }
    
    public int getEnergiSekarang() {
        return this.energi_sekarang;
    }
    
    public static int getTotalWorkDay() {
        Nanto me = Nanto.getMy();
        return me.gettotalworkday();
    }
    
    public int gettotalworkday() {
        return this.waktu_eksekusi*Jadwal.TIME_ONE_WEEK;
    }
    
    public boolean isBarangEksis(String kode_barang) {
        if(this.box_barang.size() > 0) {
            boolean found = false;
            for(int i = 0; i < this.box_barang.size() && !found; i++) {
                if(this.box_barang.get(i).compareTo(kode_barang) == 0)
                    found = true;
            }
            return found;
        } else
            return false;
    }
    
    public static ArrayList<String> getBarang() {
        Nanto me = Nanto.getMy();
        return me.getbarang();
    }
    
    public ArrayList<String> getbarang() {
        return this.box_barang;
    }
    
    public static ArrayList<String> getBarangNotEmpty() {
        Nanto me = Nanto.getMy();
        return me.getBarang_NotEmpty();
    }
    
    public ArrayList<String> getBarang_NotEmpty() {
        ArrayList<String> temp = new ArrayList<>();
        for(int i = 0; i < box_barang.size(); i++) {
            if(box_barang_jumlah.get(i) > 0)
                temp.add(box_barang.get(i));
        }
        return temp;
    }
    
    private int getBarangIndeks(String kode_barang) {
        if(this.box_barang.size() > 0) {
            int indeks;
            for(indeks = 0; indeks < this.box_barang.size(); indeks++) {
                if(this.box_barang.get(indeks).compareTo(kode_barang) == 0)
                    break;
            }
            if(indeks < this.box_barang.size())
                return indeks;
            else
                return -1;
        } else
            return -1;
    }
    
    public static int jumlahBarang(String kode_barang) {
        Nanto me = Nanto.getMy();
        return me.jumlahbarang(kode_barang);
    }
    
    public int jumlahbarang(String kode_barang) {
        int indeks = getBarangIndeks(kode_barang);
        if(indeks < 0)
            return indeks;
        else {
            return this.box_barang_jumlah.get(indeks);
        }
    }
    
    public static void setAtribut(Nanto.ATRIBUT att, int value) {
        Nanto me = Nanto.getMy();
        me.setatribut(att, value);
    }
    
    public void setatribut(Nanto.ATRIBUT att, int value) {
        switch(att) {
            case MONEY: this.nilai_attribut[0] = value; break;
            case STRENGTH: this.nilai_attribut[1] = value; break;
            case CHARM: this.nilai_attribut[2] = value; break;
            case BRAIN: this.nilai_attribut[3] = value; break;
        }
    }
    
    public static void setIncAtribut(Nanto.ATRIBUT att, int value) {
        Nanto me = Nanto.getMy();
        me.setincatribut(att, value);
    }
    
    public void setincatribut(Nanto.ATRIBUT att, int value) {
        switch(att) {
            case MONEY: this.nilai_attribut[0] += value; break;
            case STRENGTH: this.nilai_attribut[1] += value; break;
            case CHARM: this.nilai_attribut[2] += value; break;
            case BRAIN: this.nilai_attribut[3] += value; break;
        }
    }
    
    public static void setAtribut(int index, int value) {
        Nanto me = Nanto.getMy();
        me.setatribut(index, value);
    }
    
    public void setatribut(int index, int value) {
        if(index >= 0 && index < this.nilai_attribut.length) this.nilai_attribut[index] = value;
    }
    
    public static void setAllAtribut(int money, int strength, int charm, int brain) {
        Nanto me = Nanto.getMy();
        me.setallatribut(money, strength, charm, brain);
    }
    
    public void setallatribut(int money, int strength, int charm, int brain) {
        this.nilai_attribut[0] = money;
        this.nilai_attribut[1] = strength;
        this.nilai_attribut[2] = charm;
        this.nilai_attribut[3] = brain;
    }
    
    public static void setMoney(int value) {
        setAtribut(ATRIBUT.MONEY, value);
    }
    
    public void setmoney(int value) {
        setatribut(ATRIBUT.MONEY, value);
    }
    
    public static void setStrength(int value) {
        setAtribut(ATRIBUT.STRENGTH, value);
    }
    
    public void setstrength(int value) {
        setatribut(ATRIBUT.STRENGTH, value);
    }
    
    public static void setCharm(int value) {
        setAtribut(ATRIBUT.CHARM, value);
    }
    
    public void setcharm(int value) {
        setatribut(ATRIBUT.CHARM, value);
    }
    
    public static void setBrain(int value) {
        setAtribut(ATRIBUT.BRAIN, value);
    }
    
    public void setbrain(int value) {
        setatribut(ATRIBUT.BRAIN, value);
    }

    public static void setWaktu_eksekusi(int waktu_eksekusi) {
        Nanto me = Nanto.getMy();
        me.waktu_eksekusi = waktu_eksekusi;
    }

    public static void setEnergi_harian(int energi_harian) {
        Nanto me = Nanto.getMy();
        me.energi_harian = energi_harian;
    }

    public static void setEnergi_sekarang(int energi_sekarang) {
        Nanto me = Nanto.getMy();
        me.energi_sekarang = energi_sekarang;
    }
    
    public void setEnergiSekarang(int energi_sekarang) {
        this.energi_sekarang = energi_sekarang;
    }
    
    public void addBarang(char kode_barang) {
        if(isBarangEksis(String.valueOf(kode_barang))) {
            int indeks = getBarangIndeks(String.valueOf(kode_barang));
            this.box_barang_jumlah.set(indeks, 
                    this.box_barang_jumlah.get(indeks) + 1);
        } else {
            this.box_barang.add(String.valueOf(kode_barang));
            this.box_barang_jumlah.add(1);
        }
    }

    public static String tostring() {
        Nanto me = Nanto.getMy();
        return "Nanto{" + "money=" + me.nilai_attribut[0] + 
                ", strength=" + me.nilai_attribut[1] + 
                ", charm=" + me.nilai_attribut[2] + 
                ", brain=" + me.nilai_attribut[3] +
                ", energi=" + me.energi_sekarang + '/' + me.energi_harian + '}';
    }

    @Override
    public String toString() {
        return "Nanto{" + "waktu_eksekusi=" + waktu_eksekusi + ", energi_harian=" + energi_harian + ", energi_sekarang=" + energi_sekarang + ", nilai_attribut=" + nilai_attribut + '}';
    }
}
