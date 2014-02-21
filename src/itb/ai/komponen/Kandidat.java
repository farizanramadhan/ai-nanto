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
public class Kandidat {
    private static int inc_num_candidate = 0;
    private static int TOTAL = 0;
    
    /**
     * MEMBER
     */
    private int candidate_number;
    private int enlightenment_hourly;
    private int energy_hourly;
    private int meeting_rest;
    private int max_meeting_daily;
    private ArrayList<String> prereq;
    private int strength;
    private int charm;
    private int brain;
    private static Kandidat[] kandidat;
    
    /**
     * KONSTRUKTOR
     */
    public static void Inisialisasi() {
        if(TOTAL > 0 && kandidat == null) {
            kandidat = new Kandidat[TOTAL];
            for(int i = 0; i < TOTAL; i++) {
                kandidat[i] = new Kandidat(0,0,0,0,0,0,0);
            }
        }
    }
    
    private Kandidat(int enlightenment_hourly, int energy_hourly, 
            int max_meeting_daily, int meeting_rext, int strength, int charm, int brain) {
        inc_num_candidate++;
        this.candidate_number = inc_num_candidate;
        this.enlightenment_hourly = enlightenment_hourly;
        this.energy_hourly = energy_hourly;
        this.max_meeting_daily = max_meeting_daily;
        this.meeting_rest = meeting_rext;
        this.prereq = new ArrayList<>();
        this.strength = strength;
        this.charm = charm;
        this.brain = brain;
    }
    
    /**
     * METODA
     */
    public static int getTotalKandidat() {
        return TOTAL;
    }
    
    public static boolean isNumberKandidatEksis(char _cid) {
        return getIndexKandidat(_cid)<=TOTAL && getIndexKandidat(_cid) > 0;
    }
    
    public static int getIndexKandidat(char _cid) {
        return Integer.parseInt(String.valueOf(_cid));
    }
    
    public static int getCandidateNumber(char _cid) {
        return kandidat[getIndexKandidat(_cid)].getCandidate_number();
    }
    
    public int getCandidate_number() {
        return candidate_number;
    }

    public static int getEnlightenmentHourly(char _cid) {
        return kandidat[getIndexKandidat(_cid)].getEnlightenment_hourly();
    }
    
    public int getEnlightenment_hourly() {
        return enlightenment_hourly;
    }

    public static int getEnergyHourly(char _cid) {
        return kandidat[getIndexKandidat(_cid)].getEnergy_hourly();
    }
    
    public int getEnergy_hourly() {
        return energy_hourly;
    }

    public static int getMaxMeetingDaily(char _cid) {
        return kandidat[getIndexKandidat(_cid)].getMax_meeting_daily();
    }
    
    public int getMax_meeting_daily() {
        return max_meeting_daily;
    }
    
    public static int getMeetingRest(char _cid) {
        return kandidat[getIndexKandidat(_cid)].getMeeting_rest();
    }
    
    public int getMeeting_rest() {
        return meeting_rest;
    }
    
    public static int getTotalPrereq(char _cid) {
        return kandidat[getIndexKandidat(_cid)].getTotal_prereq();
    }
    
    public int getTotal_prereq() {
        return prereq.size();
    }

    public static ArrayList<String> getPrereq(char _cid) {
        return kandidat[getIndexKandidat(_cid)].getPrereq();
    }
    
    public ArrayList<String> getPrereq() {
        return prereq;
    }
    
    public static boolean isBarangFulfilled(char _cid, ArrayList<String> box_barang) {
        return kandidat[getIndexKandidat(_cid)].isBarang_fulfilled(box_barang);
    }
    
    public boolean isBarang_fulfilled(ArrayList<String> box_barang) {
        if(!box_barang.isEmpty() && !prereq.isEmpty()) {
            boolean isfound = true;
            boolean isfound2;
            for(int i = 0; i < prereq.size() && !isfound; i++) {
                isfound2 = false;
                for(int j = 0; j < box_barang.size() && !isfound2; j++) {
                    if(prereq.get(i).compareTo(box_barang.get(j)) == 0)
                        isfound2 = true;
                }
                isfound = isfound && isfound2;
            }
            return isfound;
        } else
            return false;
    }
    
    public static String popHeadPrereq(char _cid) {
        return kandidat[getIndexKandidat(_cid)].popHeadPrereq();
    }
    
    public String popHeadPrereq() {
        if(prereq.isEmpty())
            return null;
        else {
            String temp_val = prereq.get(0);
            prereq.remove(0);
            return temp_val;
        }
    }
    
    public static String popLastPrereq(char _cid) {
        return kandidat[getIndexKandidat(_cid)].popLastPrereq();
    }
    
    public String popLastPrereq() {
        if(prereq.isEmpty())
            return null;
        else {
            String temp_val = prereq.get(0);
            prereq.remove(prereq.size() - 1);
            return temp_val;
        }
    }

    public static int getStrength(char _cid) {
        return kandidat[getIndexKandidat(_cid)].getStrength();
    }
    
    public int getStrength() {
        return strength;
    }

    public static int getCharm(char _cid) {
        return kandidat[getIndexKandidat(_cid)].getCharm();
    }
    
    public int getCharm() {
        return charm;
    }

    public static int getBrain(char _cid) {
        return kandidat[getIndexKandidat(_cid)].getBrain();
    }
    
    public int getBrain() {
        return brain;
    }
    
    public static void inisialTotalKandidat(int total) {
        if(TOTAL == 0) TOTAL = total;
    }
    
    public static void setAll(char _cid, int enlightenment_hourly, int energy_hourly, 
            int max_meeting_daily, int strength, int charm, int brain) {
        kandidat[getIndexKandidat(_cid)].setAll(enlightenment_hourly, 
                energy_hourly, max_meeting_daily, strength, charm, brain);
    }
    
    public void setAll(int enlightenment_hourly, int energy_hourly, 
            int max_meeting_daily, int strength, int charm, int brain) {
        this.enlightenment_hourly = enlightenment_hourly;
        this.energy_hourly = energy_hourly;
        this.max_meeting_daily = max_meeting_daily;
        this.strength = strength;
        this.charm = charm;
        this.brain = brain;
    }

    public static void setEnlightenmentHourly(char _cid, int englightenment_hourly) {
        kandidat[getIndexKandidat(_cid)].setEnlightenment_hourly(englightenment_hourly);
    }
    
    public void setEnlightenment_hourly(int enlightenment_hourly) {
        this.enlightenment_hourly = enlightenment_hourly;
    }

    public static void setEnergyHourly(char _cid, int energy_hourly) {
        kandidat[getIndexKandidat(_cid)].setEnergy_hourly(energy_hourly);
    }
    
    public void setEnergy_hourly(int energy_hourly) {
        this.energy_hourly = energy_hourly;
    }

    public static void setMaxMeetingDaily(char _cid, int max_meeting_daily) {
        kandidat[getIndexKandidat(_cid)].setMax_meeting_daily(max_meeting_daily);
    }
    
    public void setMax_meeting_daily(int max_meeting_daily) {
        this.max_meeting_daily = max_meeting_daily;
    }
    
    public static void setMeetingRest(char _cid, int meeting_rest) {
        kandidat[getIndexKandidat(_cid)].setMeeting_rest(meeting_rest);
    }
    
    public void setMeeting_rest(int meeting_rest) {
        this.meeting_rest = meeting_rest;
    }
    
    public static void decMeetingRest(char _cid) {
        kandidat[getIndexKandidat(_cid)].decMeeting_rest();
    }
    
    public void decMeeting_rest() {
        this.meeting_rest--;
    }
    
    public static void resetMeetingRest() {
        for(int i = 0; i < TOTAL; i++) {
            kandidat[i].resetMeeting_rest();
        }
    }
    
    public static void resetMeetingRest(char _cid) {
        kandidat[getIndexKandidat(_cid)].resetMeeting_rest();
    }
    
    public void resetMeeting_rest() {
        this.meeting_rest = this.max_meeting_daily;
    }

    public static void setStrength(char _cid, int strength) {
        kandidat[getIndexKandidat(_cid)].setStrength(strength);
    }
    
    public void setStrength(int strength) {
        this.strength = strength;
    }

    public static void setCharm(char _cid, int charm) {
        kandidat[getIndexKandidat(_cid)].setCharm(charm);
    }
    
    public void setCharm(int charm) {
        this.charm = charm;
    }

    public static void setBrain(char _cid, int charm) {
        kandidat[getIndexKandidat(_cid)].getBrain();
    }
    
    public void setBrain(int brain) {
        this.brain = brain;
    }
    
    public static void addPrereq(char _cid, String value) {
        kandidat[getIndexKandidat(_cid)].addPrereq(value);
    }
    
    public void addPrereq(String value) {
        this.prereq.add(value);
    }
    
    @Override
    public String toString() {
        return "Kandidat{" + "candidate_number=" + candidate_number + ", enlightenment_hourly=" + enlightenment_hourly + ", energy_hourly=" + energy_hourly + ", max_meeting_daily=" + max_meeting_daily + '}';
    }
    
}
