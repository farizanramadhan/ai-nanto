/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itb.ai.ga;

import itb.ai.komponen.Barang;
import itb.ai.komponen.Jadwal;
import itb.ai.komponen.Kandidat;
import itb.ai.komponen.Nanto;
import itb.ai.komponen.Tempat;

/**
 *
 * @author Setyo Legowo <setyo.legowo@live.com>
 */
public class Kromosom {
    /**
     * MEMBER
     */
    private String kromosom;
    private int enlightenment;
    
    /**
     * KONSTRUKTOR
     */
    public Kromosom(String kromosom) {
        this.kromosom = kromosom;
    }

    /**
     * METODA
     */
    public String getKromosom() {
        return kromosom;
    }

    public int getEnlightenment() {
        return enlightenment;
    }

    public void setKromosom(String kromosom) {
        this.kromosom = kromosom;
    }
    
    public void calcEnlightenment(Nanto nanto) {
        int enlight = 0;
        int enlight_oneday;
        int end_day;
        char aksi;
        
        for(int it = 0; it < Jadwal.DAY_IN_ONE_WEEK*Nanto.getWaktu_eksekusi(); it++) {
            Kandidat.resetMeetingRest();
            Barang.resetAllStokSekarang();
            nanto.setEnergiSekarang(Nanto.getEnergi_harian());
            enlight_oneday = 0;
            end_day = (it+1)*Jadwal.TIME_ONE_DAY;
            for(int it2 = it*Jadwal.TIME_ONE_DAY; it2 < end_day; it2++) {
                aksi = kromosom.charAt(it2);
                if(aksi != '0') {
                    // Apakah aksi ke tempat
                    if(Tempat.getIndexTempat(aksi) != -1) {
                        if(nanto.getEnergiSekarang() >= Tempat.getEnergiBerkurangAtribut(aksi)) {
                            nanto.setincatribut(Tempat.getAtributNanto(aksi), Tempat.getNilaiBertambahAtribut(aksi));
                            nanto.setEnergiSekarang(nanto.getEnergiSekarang() - Tempat.getEnergiBerkurangAtribut(aksi));
                        }
                    } else if(Kandidat.isNumberKandidatEksis(aksi)) { // Apakah aksi ke kandidat
                        if(nanto.getEnergiSekarang() >= Kandidat.getEnergyHourly(aksi)
                                && Kandidat.getMeetingRest(aksi) > 0
                                && nanto.getstrength() >= Kandidat.getStrength(aksi)
                                && nanto.getcharm() >= Kandidat.getCharm(aksi)
                                && nanto.getbrain() >= Kandidat.getBrain(aksi)) {
                            if(Kandidat.getTotalPrereq(aksi) > 0) {
                                if(Kandidat.isBarangFulfilled(aksi, nanto.getBarang_NotEmpty())) {
                                    enlight_oneday += Kandidat.getEnlightenmentHourly(aksi);
                                    Kandidat.decMeetingRest(aksi);
                                }
                            } else {
                                enlight_oneday += Kandidat.getEnlightenmentHourly(aksi);
                                Kandidat.decMeetingRest(aksi);
                            }
                        }
                    } else if(Barang.isBarangEksis(aksi)) {
                        if(Barang.getStokSekarang(aksi) > 0
                                && nanto.getmoney() > Barang.getHarga(aksi)) {
                            Barang.decStokSekarang(aksi);
                            nanto.setatribut(Nanto.ATRIBUT.MONEY, nanto.getmoney() - Barang.getHarga(aksi));
                        }
                    }
                }
            }
            enlight += enlight_oneday;
        }
        
        this.enlightenment = enlight;
    }
}
