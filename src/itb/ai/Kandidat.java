/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itb.ai;

/**
 *
 * @author Setyo Legowo <setyo.legowo@live.com>
 */
public class Kandidat {
    private static int inc_num_candidate = 1;
    
    /**
     * MEMBER
     */
    private int candidate_number;
    private int enlightenment_hourly;
    private int energy_hourly;
    private int max_meeting_daily;
    
    /**
     * KANDIDAT
     */
    public static Kandidat SalinBaruKandidat(Kandidat kandidat) {
        Kandidat temp_kandidat = new Kandidat(kandidat.getEnlightenment_hourly(), kandidat.getEnergy_hourly(), kandidat.getMax_meeting_daily());
        return temp_kandidat;
    }
    
    public Kandidat(int enlightenment_hourly, int energy_hourly, int max_meeting_daily) {
        this.candidate_number = inc_num_candidate;
        this.enlightenment_hourly = enlightenment_hourly;
        this.energy_hourly = energy_hourly;
        this.max_meeting_daily = max_meeting_daily;
        inc_num_candidate++;
    }
    
    public Kandidat SalinBaruKandidat() {
        Kandidat temp_kandidat = new Kandidat(this.getEnlightenment_hourly(), this.getEnergy_hourly(), this.getMax_meeting_daily());
        return temp_kandidat;
    }
    
    /**
     * METODA
     */
    public int getCandidate_number() {
        return candidate_number;
    }

    public int getEnlightenment_hourly() {
        return enlightenment_hourly;
    }

    public int getEnergy_hourly() {
        return energy_hourly;
    }

    public int getMax_meeting_daily() {
        return max_meeting_daily;
    }
    
    public void setAll(int enlightenment_hourly, int energy_hourly, int max_meeting_daily) {
        this.enlightenment_hourly = enlightenment_hourly;
        this.energy_hourly = energy_hourly;
        this.max_meeting_daily = max_meeting_daily;
    }

    public void setEnlightenment_hourly(int enlightenment_hourly) {
        this.enlightenment_hourly = enlightenment_hourly;
    }

    public void setEnergy_hourly(int energy_hourly) {
        this.energy_hourly = energy_hourly;
    }

    public void setMax_meeting_daily(int max_meeting_daily) {
        this.max_meeting_daily = max_meeting_daily;
    }

    @Override
    public String toString() {
        return "Kandidat{" + "candidate_number=" + candidate_number + ", enlightenment_hourly=" + enlightenment_hourly + ", energy_hourly=" + energy_hourly + ", max_meeting_daily=" + max_meeting_daily + '}';
    }
    
}
