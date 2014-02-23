/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itb.ai.ga;

import itb.ai.komponen.Barang;
import itb.ai.komponen.Kandidat;
import itb.ai.komponen.Nanto;
import itb.ai.komponen.Tempat;
import java.security.SecureRandom;
import java.util.ArrayList;

/**
 *
 * @author Setyo Legowo <setyo.legowo@live.com>
 */
public class GenLaboratory {
    public static int MAX_KROMOSOM = 16;
    private ArrayList<Kromosom> list_kromosom;
    private static GenLaboratory instance;
    private static int CO_minus;
    private final static int CO_minus_default = 0;
    private static double MUTATE_rate;
    private final static double MUTATE_rate_default = 0.5;
    private static SecureRandom rand;
    
    /**
     * KONSTRUKTOR
     */
    public static void Inisialisasi() {
        if(instance == null) {
            rand = new SecureRandom();
            rand.setSeed(SecureRandom.getSeed(3));
            instance = new GenLaboratory();
            for(int i = 0; i < MAX_KROMOSOM; i++) {
                instance.list_kromosom.add(instance.GenerateNewGen((i + 1) % 4 != 0));
            }
            CO_minus = CO_minus_default;
            MUTATE_rate = MUTATE_rate_default + 0.15;
        }
    }
    
    private GenLaboratory() {
        list_kromosom = new ArrayList<>();
    }
    
    private Kromosom GenerateNewGen(boolean isRandom) {
        String list_calon = "0";
        int kromosom_length = Nanto.getTotalWorkDay();
        Kromosom kromosom;
        
        list_calon += Kandidat.getGenKandidat() + "0";
        list_calon += Tempat.getGenKandidat() + Tempat.getGenKandidat() + "0";
        list_calon += Barang.getGenKandidat() + "0";
        
        rand.setSeed(System.nanoTime());
        int calon_length = list_calon.length();
        char[] temp_calon = list_calon.toCharArray();
        list_calon = String.valueOf(temp_calon, 0, calon_length);
        String string_kromosom = "";
        if(isRandom) {
            for(int i = 0; i < kromosom_length; i++) {
                string_kromosom += list_calon.charAt(rand.nextInt(calon_length));
            }
        } else {
            for(int i = 0; i < kromosom_length; i++) {
                string_kromosom += list_calon.charAt(i % calon_length);
            }
        }
        
        kromosom = new Kromosom(string_kromosom);
        
        return kromosom;
    }
    
    public static void CalcKromosomEnlightenment() {
        int total = instance.list_kromosom.size();
        Kromosom kromosom;
        Nanto nanto;
        for(int i = 0; i < total; i++) {
            kromosom = instance.list_kromosom.get(i);
            nanto = Nanto.getOriginCopy();
            kromosom.calcEnlightenment(nanto);
        }
    }
    
    public static boolean willNext() {
        boolean isSame = true;
        int total_kromosom = instance.list_kromosom.size();
        int englight_val = 0;
        for(int i = 0; i < total_kromosom && isSame; i++) {
            if(i == 0) {
                englight_val = instance.list_kromosom.get(i).getEnlightenment();
            } else {
                if(englight_val != instance.list_kromosom.get(i).getEnlightenment())
                    isSame = false;
            }
        }
        return !isSame;
    }
    
    public static int getMaxEnlightenment() {
        int total = instance.list_kromosom.size();
        int maks = Integer.MIN_VALUE;
        for(int i = 0; i < total; i++) {
            if(maks < instance.list_kromosom.get(i).getEnlightenment())
                maks = instance.list_kromosom.get(i).getEnlightenment();
        }
        return maks;
    }
    
    public static String getStringKromosom(int index) {
        return instance.list_kromosom.get(index).getKromosom();
    }
    
    public static long getMaxPseudoEnlightenment() {
        int total = instance.list_kromosom.size();
        long maks = Integer.MIN_VALUE;
        for(int i = 0; i < total; i++) {
            if(maks < instance.list_kromosom.get(i).getPseudoEnlightenment())
                maks = instance.list_kromosom.get(i).getPseudoEnlightenment();
        }
        return maks;
    }
    
    public static void selectKromosom() {
        int indeks_hapus = -1;
        int indeks_hapus2 = -1;
        int indeks_duplikat = -1;
        int indeks_duplikat2 = -1;
        int total_kromosom = instance.list_kromosom.size();
        
        long temp_val = Integer.MAX_VALUE;
        int temp_val2 = Integer.MAX_VALUE;
        // Searching delete worst chromosome
        for(int i = 0; i < total_kromosom; i++) {
            if(temp_val > instance.list_kromosom.get(i).getPseudoEnlightenment()) {
                indeks_hapus = i;
                temp_val = instance.list_kromosom.get(i).getPseudoEnlightenment();
            }
            if(temp_val2 > instance.list_kromosom.get(i).getEnlightenment()) {
                indeks_hapus2 = i;
                temp_val2 = instance.list_kromosom.get(i).getEnlightenment();
            }
        }
        
        temp_val = Integer.MIN_VALUE;
        temp_val2 = Integer.MIN_VALUE;
        // Searching duplicate best chromosome
        for(int i = 0; i < total_kromosom; i++) {
            if(temp_val < instance.list_kromosom.get(i).getPseudoEnlightenment()) {
                indeks_duplikat = i;
                temp_val = instance.list_kromosom.get(i).getPseudoEnlightenment();
            }
            if(temp_val2 < instance.list_kromosom.get(i).getEnlightenment()) {
                indeks_duplikat2 = i;
                temp_val2 = instance.list_kromosom.get(i).getEnlightenment();
            }
        }
        
        if(indeks_hapus > -1 && indeks_duplikat > -1) {
            if(indeks_hapus2 > -1 && indeks_duplikat2 > -1
                    && instance.list_kromosom.get(indeks_hapus2).getEnlightenment() > instance.list_kromosom.get(indeks_duplikat2).getEnlightenment()) {
                // Karena dari enlightenment yang diduplikat lebih kecil daripada yang akan dihapus
                // maka pilih dari pseudo englightenment
                Kromosom kromosom = new Kromosom(instance.list_kromosom.get(indeks_duplikat).getKromosom());
                instance.list_kromosom.remove(indeks_hapus);
                instance.list_kromosom.add(kromosom);
                instance.list_kromosom.get(total_kromosom - 1).calcEnlightenment(Nanto.getOriginCopy());
            } else {
                if(indeks_hapus != indeks_hapus2 || indeks_duplikat != indeks_duplikat2) {
                    // Usaha untuk mendapatkan enlightenment terbaik dengan
                    // menghapus pseudoenlightenment terburuk
                    Kromosom kromosom = new Kromosom(instance.list_kromosom.get(indeks_duplikat2).getKromosom());
                    instance.list_kromosom.remove(indeks_hapus);
                    instance.list_kromosom.add(kromosom);
                    instance.list_kromosom.get(total_kromosom - 1).calcEnlightenment(Nanto.getOriginCopy());
                } else { 
                    // Jika indeks yang akan dihapus == indeks yang akan diduplikat dan keduanya sama
                    // dilihat dari enlightenment dan pseudonya, maka buat gen baru di indeks tersebut
                    Kromosom kromosom;
                    if(indeks_hapus == indeks_duplikat)
                        kromosom = instance.GenerateNewGen((indeks_hapus + 1) % 2 == 0);
                    else
                        kromosom = new Kromosom(instance.list_kromosom.get(indeks_duplikat).getKromosom());
                    instance.list_kromosom.remove(indeks_hapus);
                    instance.list_kromosom.add(indeks_hapus,kromosom);
                    instance.list_kromosom.get(indeks_hapus).calcEnlightenment(Nanto.getOriginCopy());
                }
            }
        }
    }
    
    public static void renewLastListKromosom(double percent) {
        int total_kromosom = instance.list_kromosom.size();
        int total_change = (int) (total_kromosom * percent);
        Kromosom kromosom;
        
        rand.setSeed(SecureRandom.getSeed(3));
        for(int i = total_kromosom - total_change; i < total_kromosom; i++) {
            kromosom = instance.GenerateNewGen((i + 1) % 4 == 0);
            instance.list_kromosom.remove(i);
            instance.list_kromosom.add(i,kromosom);
            instance.list_kromosom.get(i).calcEnlightenment(Nanto.getOriginCopy());
        }
        
        CO_minus = CO_minus_default;
        MUTATE_rate = MUTATE_rate_default;
    }
    
    public static void crossOverKromosom() {
        int total_kromosom = instance.list_kromosom.size();
        int kromosom_length = instance.list_kromosom.get(0).getKromosom().length();
        for(int i = 0; i < total_kromosom - 1; i++) {
            crossOverKromosom(i, i+1);
        }
        if(MUTATE_rate > CO_minus_default && CO_minus < kromosom_length/2)
            CO_minus += 1;
    }
    
    private static void crossOverKromosom(int index1, int index2) {
        char[] skrom1 = instance.list_kromosom.get(index1).getKromosom().toCharArray();
        char[] skrom2 = instance.list_kromosom.get(index2).getKromosom().toCharArray();
        int kromosom_length = instance.list_kromosom.get(index2).getKromosom().length();
        
        rand.setSeed(System.nanoTime());
        int start_cross = rand.nextInt(kromosom_length/2);
        int end_cross = start_cross + kromosom_length/2 - CO_minus;
        end_cross = end_cross > start_cross? end_cross:kromosom_length;
        char temp_char;
        for(int it = start_cross; it < end_cross; it++) {
            temp_char = skrom1[it];
            skrom1[it] = skrom2[it];
            skrom2[it] = temp_char;
        }
        
        instance.list_kromosom.get(index1).setKromosom(String.copyValueOf(skrom1));
        instance.list_kromosom.get(index2).setKromosom(String.copyValueOf(skrom2));
    }
    
    public static void mutasiKromosom() {
        int size = instance.list_kromosom.size();
        for(int i = 0; i < size; i++) {
            mutationKromosom(i, MUTATE_rate);
        }
        
        // Analisis next rate
        if(MUTATE_rate > 0.05) MUTATE_rate-=0.01;
    }
    
    private static void mutationKromosom(int index, double rate) {
        char[] skrom = instance.list_kromosom.get(index).getKromosom().toCharArray();
        int kromosom_length = instance.list_kromosom.get(index).getKromosom().length();
        int kan = instance.list_kromosom.get(index).getKandidatFail();
        int bar = instance.list_kromosom.get(index).getBarangFail();
        int tem = instance.list_kromosom.get(index).getTempatFail();
        int end = kromosom_length;
        String list_calon = "0";
        int length_calon;
        
        rand.setSeed(System.nanoTime());
        list_calon += Barang.getGenKandidat() + "0"; 
        if(bar < tem && bar < kan) list_calon += Barang.getGenKandidat() + "0";
        list_calon += Tempat.getGenKandidat() + Tempat.getGenKandidat() + "0";
        if(tem < bar || tem < kan) list_calon += Tempat.getGenKandidat() + Tempat.getGenKandidat() + "0";
        //list_calon += Kandidat.getGenKandidat() + "0";
        if(kan < tem && kan < bar) list_calon += Kandidat.getGenKandidat() + "0";
        length_calon = list_calon.length();
        int total_loop = (int) (kromosom_length*rate);
        int mutation_num;
        for(int i = 0; i < total_loop; i++) {
            mutation_num = rand.nextInt(end);
            skrom[mutation_num] = list_calon.charAt(rand.nextInt(length_calon));
            end -= kromosom_length/total_loop;
        }
        
        Kromosom kromosom = new Kromosom(String.copyValueOf(skrom));
        kromosom.calcEnlightenment(Nanto.getOriginCopy());
        if(instance.list_kromosom.get(index).getEnlightenment() < kromosom.getEnlightenment())
            instance.list_kromosom.get(index).setKromosom(String.copyValueOf(skrom));
    }

    public static void cetakKromosom() {
        System.out.println("GEN LABORATORY");
        System.out.println("====================================================");
        int total_kromosom = instance.list_kromosom.size();
        Kromosom kromosom;
        for(int i = 0; i < total_kromosom; i++) {
            kromosom = instance.list_kromosom.get(i);
            System.out.println("Kromosom: " + i);
            System.out.println(kromosom.getKromosom());
            System.out.println("Enlightenment=" + kromosom.getEnlightenment());
            System.out.println("PseudoEnlightenment=" + kromosom.getPseudoEnlightenment());
            System.out.println("Err: Barang=" + kromosom.getBarangFail()
                    + ", Kandidat=" + kromosom.getKandidatFail()
                    + ", Tempat=" + kromosom.getTempatFail());
            System.out.println();
        }
    }
}
