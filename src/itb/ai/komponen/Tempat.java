/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itb.ai.komponen;

/**
 *
 * @author Setyo Legowo <setyo.legowo@live.com>
 */
public class Tempat {
    public static enum ATRIBUT { MALL, GYMNASIUM, CAFE, UNIVERSITY }
    private static String GEN = "mgcu";
    public final static int TOTAL_TEMPAT = 4;
    private static Tempat[] tempat;
    
    /**
     * MEMBER
     */
    private String nama;
    private char cid;
    private Nanto.ATRIBUT atribut;
    private int jumlah_bertambah;
    private int jumlah_energi_berkurang;
    
    /**
     * KONSTRUKTOR
     */
    public static void Inisialisasi() {
        if(tempat == null) {
            tempat = new Tempat[4];
            tempat[0] = new Tempat("Mall", 'm', Nanto.ATRIBUT.MONEY, 10000, 8);
            tempat[1] = new Tempat("Gymnasium", 'g', Nanto.ATRIBUT.STRENGTH, 2, 12);
            tempat[2] = new Tempat("Cafe", 'c', Nanto.ATRIBUT.CHARM, 2, 6);
            tempat[3] = new Tempat("University", 'u', Nanto.ATRIBUT.BRAIN, 3, 15);
        }
    }
    
    private Tempat(String nama, char cid, Nanto.ATRIBUT atribut, int jumlah_bertambah, int jumlah_energi_berkurang) {
        this.nama = nama;
        this.cid = cid;
        this.atribut = atribut;
        this.jumlah_bertambah = jumlah_bertambah;
        this.jumlah_energi_berkurang = jumlah_energi_berkurang;
    }
    
    /**
     * METODA
     */
    public static String getGenKandidat() {
        return GEN;
    }
    
    private static int getIndexTempat(Tempat.ATRIBUT atribut) {
        int return_val = 0;
        switch(atribut) {
            case GYMNASIUM: return_val = 1; break;
            case CAFE: return_val = 2; break;
            case UNIVERSITY: return_val = 3; break;
        }
        return return_val;
    }
    
    public static int getIndexTempat(char _cid) {
        int return_val;
        switch(_cid) {
            case 'm': return_val = 0; break;
            case 'g': return_val = 1; break;
            case 'c': return_val = 2; break;
            case 'u': return_val = 3; break;
            default: return_val = -1; break;
        }
        return return_val;
    }
    
    public static Nanto.ATRIBUT getAtributNanto(char _cid) {
        Nanto.ATRIBUT return_val = Nanto.ATRIBUT.MONEY;
        switch(_cid) {
            // case 'm': return_val = Nanto.ATRIBUT.MONEY; break;
            case 'g': return_val = Nanto.ATRIBUT.STRENGTH; break;
            case 'c': return_val = Nanto.ATRIBUT.CHARM; break;
            case 'u': return_val = Nanto.ATRIBUT.BRAIN; break;
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
    
    public static String getName(char _cid) {
        Tempat temp_tempat = Tempat.getMy(Tempat.getIndexTempat(_cid));
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
    
    public static int getNilaiBertambahAtribut(char _cid) {
        Tempat temp_tempat = Tempat.getMy(Tempat.getIndexTempat(_cid));
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
    
    public static int getEnergiBerkurangAtribut(char _cid) {
        Tempat temp_tempat = Tempat.getMy(Tempat.getIndexTempat(_cid));
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
    
    public static void setNilaiBertambahAtribut(char _cid, int value) {
        Tempat temp_tempat = Tempat.getMy(Tempat.getIndexTempat(_cid));
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
    
    public static void setEnergiBerkurangAtribut(char _cid, int value) {
        Tempat temp_tempat = Tempat.getMy(Tempat.getIndexTempat(_cid));
        temp_tempat.jumlah_energi_berkurang = value;
    }
}
