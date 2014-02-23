/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itb.ai;

import itb.ai.ga.GenLaboratory;
import itb.ai.komponen.Barang;
import itb.ai.komponen.Jadwal;
import itb.ai.komponen.Kandidat;
import itb.ai.komponen.Nanto;
import itb.ai.komponen.Tempat;
import itb.ai.parser.FileParser;

/**
 *
 * @author Setyo Legowo <setyo.legowo@live.com>
 */
public class AINanto_NantiAja {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // PARSER
        String generalFilePath = "asset/general.txt";
        String candidateScheduleFilePath = "asset/candidate.txt";
        String siteScheduleFilePath = "asset/site.txt";
    	FileParser.getInstance().parseAll(generalFilePath, candidateScheduleFilePath, siteScheduleFilePath);
        
        // NANTO
        Nanto.Inisialisasi();
        Nanto.setAllAtribut(
                (int) FileParser.getInstance().getModalAwal(),
                FileParser.getInstance().getStrengthAwal(), 
                FileParser.getInstance().getCharmAwal(),
                FileParser.getInstance().getBrainAwal());
        Nanto.setWaktu_eksekusi(FileParser.getInstance().getWaktu());
        Nanto.setEnergi_harian(FileParser.getInstance().getEnergi());
        
        // BARANG
        int num_barang = FileParser.getInstance().getJumlahBarang();
        Barang.inisialisasiTotalBarang(num_barang);
        Barang.inisialisasi();
        for(int i = 0; i < num_barang; i++) {
            Barang.setAll(i, 
                    FileParser.getInstance().getKodeBarang(i),
                    FileParser.getInstance().getKodeBarang(i).charAt(0),
                    FileParser.getInstance().getHargaBarang(i), 
                    FileParser.getInstance().getRestockPerHari(i));
        }
        
        // KANDIDAT
        int num_kandidat = FileParser.getInstance().getJumlahKandidat();
        String temp_prereq;
        Kandidat.inisialTotalKandidat(num_kandidat);
        Kandidat.Inisialisasi();
        for(int i = 0; i < num_kandidat; i++) {
            Kandidat.setAll(Character.forDigit(i+1, 10),
                    FileParser.getInstance().getEnlightmentPerJam(i), 
                    FileParser.getInstance().getEnergiPerJam(i), 
                    FileParser.getInstance().getMaxPerHari(i),
                    FileParser.getInstance().getStrengthNeeded(i), 
                    FileParser.getInstance().getCharmNeeded(i), 
                    FileParser.getInstance().getBrainNeeded(i));
            temp_prereq = FileParser.getInstance().getKodePrereq(i);
            for(int j = 0; j < temp_prereq.length(); j++) {
                Kandidat.addPrereq(Character.forDigit(i+1, 10), 
                        String.valueOf(temp_prereq.charAt(j)));
            }
        }
        
        // JADWAL
        Jadwal.InisialisasiJadwal(FileParser.getInstance().getWaktu(), 
                num_kandidat);
        int waktu = Nanto.getTotalWorkDay();
        // Jadwal untuk kandidat
        for(int i = 0; i < num_kandidat; i++) {
            for(int j = 0; j < waktu; j++) {
                Jadwal.setAvailable(Jadwal.TYPE.KANDIDAT, i, j,
                        FileParser.getInstance().getCandidateSchedule(i, j));
            }
        }
        // Jadwal untuk tempat
        for(int i = 0; i < Tempat.TOTAL_TEMPAT; i++) {
            for(int j = 0; j < waktu; j++) {
                Jadwal.setAvailable(Jadwal.TYPE.TEMPAT, i, j,
                        FileParser.getInstance().getSiteSchedule(i, j));
            }
        }
        
        // SEKARANG NGAPAIN???
        GenLaboratory.Inisialisasi();
        GenLaboratory.CalcKromosomEnlightenment();
        int it = 0;
        double renew_rate = 0.5;
        int maks_iterate = 100000*(32/GenLaboratory.MAX_KROMOSOM);
        while(GenLaboratory.willNext() && it < maks_iterate) {
            while(GenLaboratory.willNext() && it < maks_iterate) {
                GenLaboratory.selectKromosom();
                GenLaboratory.crossOverKromosom();
                GenLaboratory.mutasiKromosom();
                GenLaboratory.CalcKromosomEnlightenment();
                it++;
                if(it % 10000 == 0) {
                    System.out.println("Checkpoint: Iterasi ke-" + it
                        + ", Max Englightenment="
                        + GenLaboratory.getMaxEnlightenment());
                }
                if(it % 50000 == 0 && it > 100000) {
                    System.out.println("Generate Kromosom Baru. Iterasi ke-" + it
                            + ", Max Englightenment="
                            + GenLaboratory.getMaxEnlightenment());
                    GenLaboratory.renewLastListKromosom(renew_rate);
                    if(renew_rate > 0.1) renew_rate -= 0.02;
                }
            }
            if(it < 100000) {
                System.out.println("Generate Kromosom Baru. Iterasi ke-" + it
                        + ", Max Englightenment="
                        + GenLaboratory.getMaxEnlightenment());
                GenLaboratory.renewLastListKromosom(renew_rate + 0.1);
                if(renew_rate > 0.1) renew_rate -= 0.02;
            }
        }
        GenLaboratory.cetakKromosom();
        System.out.println("Jumlah iterasi = " + it);
    }
}
