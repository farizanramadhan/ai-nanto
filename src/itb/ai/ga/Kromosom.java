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
    private long pseuenlightenment;
    private int barang_fail;
    private int tempat_fail;
    private int kandidat_fail;
    
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
    
    public long getPseudoEnlightenment() {
        return pseuenlightenment;
    }
    
    public int getBarangFail() {
        return barang_fail;
    }
    
    public int getTempatFail() {
        return tempat_fail;
    }
    
    public int getKandidatFail() {
        return kandidat_fail;
    }

    public void setKromosom(String kromosom) {
        this.kromosom = kromosom;
    }
    
    public void calcEnlightenment(Nanto nanto) {
        int enlight = 0;
        int enlight_oneday;
        int end_day;
        char aksi;
        barang_fail = 0;
        tempat_fail = 0;
        kandidat_fail = 0;
        
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
                        if(nanto.getEnergiSekarang() >= Tempat.getEnergiBerkurangAtribut(aksi) 
                                && Jadwal.isAvailable(Jadwal.TYPE.TEMPAT, Tempat.getIndexTempat(aksi), it2)) {
                            nanto.setincatribut(Tempat.getAtributNanto(aksi), Tempat.getNilaiBertambahAtribut(aksi));
                            nanto.setEnergiSekarang(nanto.getEnergiSekarang() - Tempat.getEnergiBerkurangAtribut(aksi));
                        } else {
                            tempat_fail += 1;
                        }
                    } else if(Kandidat.isKarakterKandidat(aksi)) { // Apakah aksi ke kandidat
                        if(Jadwal.isAvailable(Jadwal.TYPE.KANDIDAT, Kandidat.getIndexKandidat(aksi), it2)
                                && nanto.getEnergiSekarang() >= Kandidat.getEnergyHourly(aksi)
                                && Kandidat.getMeetingRest(aksi) > 0
                                && nanto.getstrength() >= Kandidat.getStrength(aksi)
                                && nanto.getcharm() >= Kandidat.getCharm(aksi)
                                && nanto.getbrain() >= Kandidat.getBrain(aksi)) {
                            if(Kandidat.getTotalPrereq(aksi) > 0) {
                                if(Kandidat.isBarangFulfilled(aksi, nanto.getBarang_NotEmpty())) {
                                    enlight_oneday += Kandidat.getEnlightenmentHourly(aksi);
                                    Kandidat.decMeetingRest(aksi);
                                } else {
                                    kandidat_fail += 1;
                                }
                            } else {
                                enlight_oneday += Kandidat.getEnlightenmentHourly(aksi);
                                Kandidat.decMeetingRest(aksi);
                            }
                        } else {
                            kandidat_fail += 1;
                        }
                    } else if(Barang.isBarangEksis(aksi)) {
                        if(Barang.getStokSekarang(aksi) > 0
                                && nanto.getmoney() > Barang.getHarga(aksi)) {
                            Barang.decStokSekarang(aksi);
                            nanto.addBarang(aksi);
                            nanto.setatribut(Nanto.ATRIBUT.MONEY, nanto.getmoney() - Barang.getHarga(aksi));
                        } else {
                            barang_fail += 1;
                        }
                    }
                }
            }
            enlight += enlight_oneday;
        }
        
        this.enlightenment = enlight;
        this.pseuenlightenment = (nanto.getbrain() + nanto.getcharm()
                + nanto.getstrength());
        this.pseuenlightenment -= (Kandidat.getMaxBrain() + Kandidat.getMaxCharm()
                + Kandidat.getMaxStrength());
    }
}
