/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itb.ai.ga;

import itb.ai.komponen.Barang;
import itb.ai.komponen.Kandidat;
import itb.ai.komponen.Nanto;
import itb.ai.komponen.Tempat;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Setyo Legowo <setyo.legowo@live.com>
 */
public class GenLaboratory {
    public static int MAX_KROMOSOM = 4;
    private ArrayList<Kromosom> list_kromosom;
    private static GenLaboratory instance;
    private static int CO_minus;
    private static double MUTATE_rate;
    
    /**
     * KONSTRUKTOR
     */
    public static void Inisialisasi() {
        if(instance == null) {
            instance = new GenLaboratory();
            for(int i = 0; i < MAX_KROMOSOM; i++) {
                instance.list_kromosom.add(instance.GenerateNewGen());
            }
            CO_minus = 0;
            MUTATE_rate = 0.75;
        }
    }
    
    private GenLaboratory() {
        list_kromosom = new ArrayList<>();
    }
    
    private Kromosom GenerateNewGen() {
        String list_calon = "0";
        int kromosom_length = Nanto.getTotalWorkDay();
        Kromosom kromosom;
        
        list_calon += Tempat.getGenKandidat() + Tempat.getGenKandidat() + "0";
        list_calon += Kandidat.getGenKandidat() + "0";
        list_calon += Barang.getGenKandidat() + "0";
        
        Random rand = new Random();
        rand.setSeed(System.nanoTime());
        int calon_length = list_calon.length();
        char[] temp_calon = list_calon.toCharArray();
        list_calon = String.valueOf(temp_calon, 0, calon_length);
        String string_kromosom = "";
        for(int i = 0; i < kromosom_length; i++) {
            string_kromosom += list_calon.charAt(rand.nextInt(calon_length));
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
                    && instance.list_kromosom.get(indeks_hapus2).getEnlightenment() >= instance.list_kromosom.get(indeks_duplikat2).getEnlightenment()) {
                Kromosom kromosom = new Kromosom(instance.list_kromosom.get(indeks_duplikat).getKromosom());
                instance.list_kromosom.remove(indeks_hapus);
                instance.list_kromosom.add(kromosom);
                instance.list_kromosom.get(total_kromosom - 1).calcEnlightenment(Nanto.getOriginCopy());
            } else {
                Kromosom kromosom = new Kromosom(instance.list_kromosom.get(indeks_duplikat2).getKromosom());
                instance.list_kromosom.remove(indeks_hapus2);
                instance.list_kromosom.add(kromosom);
                instance.list_kromosom.get(total_kromosom - 1).calcEnlightenment(Nanto.getOriginCopy());
            }
        }
    }
    
    public static void crossOverKromosom() {
        int total_kromosom = instance.list_kromosom.size();
        for(int i = 0; i < total_kromosom; i+=2) {
            crossOverKromosom(i, i+1);
        }
        if(CO_minus >= 0 && MUTATE_rate > 0.5)
            CO_minus += 2;
        else if(CO_minus > 0)
            CO_minus -= 1;
    }
    
    private static void crossOverKromosom(int index1, int index2) {
        char[] skrom1 = instance.list_kromosom.get(index1).getKromosom().toCharArray();
        char[] skrom2 = instance.list_kromosom.get(index2).getKromosom().toCharArray();
        int kromosom_length = instance.list_kromosom.get(index2).getKromosom().length();
        Random rand = new Random();
        
        rand.setSeed(System.nanoTime());
        int start_cross = rand.nextInt(kromosom_length/2);
        int end_cross = start_cross + kromosom_length/2 - CO_minus;
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
        int start = 0;
        String list_calon = "0";
        int length_calon;
        Random rand = new Random();
        
        rand.setSeed(System.nanoTime());
        list_calon += Tempat.getGenKandidat() + Tempat.getGenKandidat() + Tempat.getGenKandidat() + "0";
        list_calon += Kandidat.getGenKandidat() + "0";
        list_calon += Barang.getGenKandidat() + "0";
        length_calon = list_calon.length();
        int total_loop = (int) (kromosom_length*rate);
        int mutation_num;
        for(int i = 0; i < total_loop; i++) {
            mutation_num = (rand.nextInt(kromosom_length - start)) % kromosom_length;
            skrom[mutation_num] = list_calon.charAt(rand.nextInt(length_calon));
            start = start + (kromosom_length - start)/total_loop;
        }
        
        Kromosom kromosom = new Kromosom(String.copyValueOf(skrom));
        kromosom.calcEnlightenment(Nanto.getOriginCopy());
        if(instance.list_kromosom.get(index).getEnlightenment() < kromosom.getEnlightenment())
            instance.list_kromosom.get(index).setKromosom(String.copyValueOf(skrom));
    }
    
    public static boolean isEnglightenmentPositive() {
        boolean isExist = false;
        int total_kromosom = instance.list_kromosom.size();
        for(int i = 0; i < total_kromosom && !isExist; i++) {
            if(instance.list_kromosom.get(i).getEnlightenment() > 0)
                isExist = true;
        }
        return isExist;
    }
}
