/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itb.ai;

/**
 *
 * @author Setyo Legowo <setyo.legowo@live.com>
 */
public class Tempat {
    public static enum ATRIBUT { MALL, GYMNASIUM, CAFE, UNIVERSITY }
    public final static int TOTAL_TEMPAT = 4;
    private static Tempat[] tempat;
    
    /**
     * MEMBER
     */
    private String nama;
    private Nanto.ATRIBUT atribut;
    private int jumlah_bertambah;
    private int jumlah_energi_berkurang;
    
    /**
     * KONSTRUKTOR
     */
    public static void Inisialisasi() {
        if(tempat == null) {
            tempat = new Tempat[4];
            tempat[0] = new Tempat("Mall", Nanto.ATRIBUT.MONEY, 10000, 8);
            tempat[1] = new Tempat("Gymnasium", Nanto.ATRIBUT.STRENGTH, 2, 12);
            tempat[2] = new Tempat("Cafe", Nanto.ATRIBUT.CHARM, 2, 6);
            tempat[3] = new Tempat("University", Nanto.ATRIBUT.BRAIN, 3, 15);
        }
    }
    
    private Tempat(String nama, Nanto.ATRIBUT atribut, int jumlah_bertambah, int jumlah_energi_berkurang) {
        this.nama = nama;
        this.atribut = atribut;
        this.jumlah_bertambah = jumlah_bertambah;
        this.jumlah_energi_berkurang = jumlah_energi_berkurang;
    }
    
    /**
     * METODA
     */
    private static int getIndexTempat(Tempat.ATRIBUT atribut) {
        int return_val = 0;
        switch(atribut) {
            case GYMNASIUM: return_val = 1; break;
            case CAFE: return_val = 2; break;
            case UNIVERSITY: return_val = 3; break;
        }
        return return_val;
    }
    
    private static Tempat getMy(int index) {
        Tempat.Inisialisasi();
        return (index >= 0 && index < tempat.length)?tempat[index]:null;
    }
    
    public static String getName(Tempat.ATRIBUT atribut) {
        Tempat temp_tempat = Tempat.getMy(Tempat.getIndexTempat(atribut));
        return temp_tempat.nama;
    }
    
    public static String getName(int index) {
        Tempat.Inisialisasi();
        if(index >= 0 && index < tempat.length) {
            Tempat temp = Tempat.getMy(index);
            return temp.nama;
        } else {
            return null;
        }
    }
    
    public static int getNilaiBertambahAtribut(int index) {
        Tempat.Inisialisasi();
        if(index >= 0 && index < tempat.length) {
            Tempat temp_tempat = Tempat.getMy(index);
            return temp_tempat.jumlah_bertambah;
        } else {
            return 0;
        }
    }
    
    public static int getNilaiBertambahAtribut(Tempat.ATRIBUT atribut) {
        Tempat temp_tempat = Tempat.getMy(Tempat.getIndexTempat(atribut));
        return temp_tempat.jumlah_bertambah;
    }
    
    public static int getEnergiBerkurangAtribut(int index) {
        Tempat.Inisialisasi();
        if(index >= 0 && index < tempat.length) {
            Tempat temp_tempat = Tempat.getMy(index);
            return temp_tempat.jumlah_energi_berkurang;
        } else {
            return 0;
        }
    }
    
    public static int getEnergiBerkurangAtribut(Tempat.ATRIBUT atribut) {
        Tempat temp_tempat = Tempat.getMy(Tempat.getIndexTempat(atribut));
        return temp_tempat.jumlah_energi_berkurang;
    }
    
    public static void setNilaiBertambahAtribut(int index, int value) {
        Tempat.Inisialisasi();
        if(index >= 0 && index < tempat.length) {
            Tempat temp_tempat = Tempat.getMy(index);
            temp_tempat.jumlah_bertambah = value;
        }
    }
    
    public static void setNilaiBertambahAtribut(Tempat.ATRIBUT atribut, int value) {
        Tempat temp_tempat = Tempat.getMy(Tempat.getIndexTempat(atribut));
        temp_tempat.jumlah_bertambah = value;
    }
    
    public static void setEnergiBerkurangAtribut(int index, int value) {
        Tempat.Inisialisasi();
        if(index >= 0 && index < tempat.length) {
            Tempat temp_tempat = Tempat.getMy(index);
            temp_tempat.jumlah_energi_berkurang = value;
        }
    }
    
    public static void setEnergiBerkurangAtribut(Tempat.ATRIBUT atribut, int value) {
        Tempat temp_tempat = Tempat.getMy(Tempat.getIndexTempat(atribut));
        temp_tempat.jumlah_energi_berkurang = value;
    }
    
}
