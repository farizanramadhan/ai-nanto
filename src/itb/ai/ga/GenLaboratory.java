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
    
    /**
     * KONSTRUKTOR
     */
    public static void Inisialisasi() {
        if(instance == null) {
            instance = new GenLaboratory();
            for(int i = 0; i < MAX_KROMOSOM; i++) {
                instance.list_kromosom.add(instance.GenerateNewGen());
            }
        }
    }
    
    private GenLaboratory() {
        list_kromosom = new ArrayList<>();
    }
    
    private Kromosom GenerateNewGen() {
        String list_calon = "0";
        int kromosom_length = Nanto.getTotalWorkDay();
        Kromosom kromosom;
        
        list_calon += Tempat.getGenKandidat() + "0";
        list_calon += Kandidat.getGenKandidat() + "0";
        list_calon += Barang.getGenKandidat() + "0";
        
        Random rand = new Random();
        rand.setSeed(System.nanoTime());
        String string_kromosom = "";
        int calon_length = list_calon.length();
        for(int i = 0; i < kromosom_length; i++) {
            string_kromosom += list_calon.charAt(rand.nextInt(calon_length) % calon_length);
        }
        
        kromosom = new Kromosom(string_kromosom);
        
        return kromosom;
    }
}
