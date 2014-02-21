/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itb.ai.komponen;

/**
 *
 * @author taktyong
 */
public class Jadwal {
    public static enum TYPE {KANDIDAT, TEMPAT};
    public final static int TIME_ONE_DAY = 12;
    public final static int DAY_IN_ONE_WEEK = 7;
    public final static int TIME_ONE_WEEK = DAY_IN_ONE_WEEK * TIME_ONE_DAY;
    
    /**
     * Member
     */
    private static Jadwal my_tempat, my_kandidat;
    private boolean[][] jadwal;

    public static void InisialisasiJadwal(int waktu, int kandidat) {
        if(my_tempat == null && my_kandidat == null) {
            my_tempat = new Jadwal(TYPE.TEMPAT, waktu, Tempat.TOTAL_TEMPAT);
            my_kandidat = new Jadwal(TYPE.KANDIDAT, waktu, kandidat);
        }
    }
    
    /**
     * 
     * @param type Tipe penjadwalan yang akan digunakan
     * @param waktu waktu dalam satuan minggu
     * @param num jumlah kandidat atau jumlah tempat
     */
    private Jadwal(Jadwal.TYPE type, int waktu, int num) {
        jadwal = new boolean[num][];
        for(int i = 0; i < num; i++) {
            jadwal[i] = new boolean[waktu*TIME_ONE_WEEK];
            for(int j = 0; j < waktu; j++) {
                jadwal[i][j] = false;
            }
        }
    }
    
    public static int getIndexJadwal(Jadwal.TYPE type) {
        return type==Jadwal.TYPE.TEMPAT ? 0 : 1;
    }
    
    public static boolean isAvailable(Jadwal.TYPE type, int row, int i) {
        return type==Jadwal.TYPE.TEMPAT?
                my_tempat.isAvailable(row, i):
                my_kandidat.isAvailable(row, i);
    }
    
    private boolean isAvailable(int i, int j) {
        return jadwal[i][j];
    }
    
    public static boolean[] getAvailableOneDay(Jadwal.TYPE type, int row, int day) {
        return type==Jadwal.TYPE.TEMPAT?
                my_tempat.getAvailableOneDay(row, day):
                my_kandidat.getAvailableOneDay(row, day);
    }
    
    private boolean[] getAvailableOneDay(int row, int day) {
        boolean[] temp_value = new boolean[TIME_ONE_DAY];
        for(int i = day*TIME_ONE_DAY; i < (day+1)*TIME_ONE_DAY; i++) {
            temp_value[i] = isAvailable(row, i);
        }
        return temp_value;
    }
    
    public static boolean[] getAvailableOneWeek(Jadwal.TYPE type, int row, int week) {
        return type==Jadwal.TYPE.TEMPAT?
                my_tempat.getAvailableOneWeek(row, week):
                my_kandidat.getAvailableOneWeek(row, week);
    }
    
    private boolean[] getAvailableOneWeek(int row, int week) {
        boolean[] temp_value = new boolean[TIME_ONE_WEEK];
        for(int i = week * TIME_ONE_WEEK; i < (week + 1)*TIME_ONE_WEEK; i++) {
            temp_value[i] = isAvailable(row, i);
        }
        return temp_value;
    }
    
    public static boolean[] getAvailableAllDay(Jadwal.TYPE type, int row) {
        return type==Jadwal.TYPE.TEMPAT?
                my_tempat.getAvailableAllDay(row):
                my_kandidat.getAvailableAllDay(row);
    }
    
    private boolean[] getAvailableAllDay(int row) {
        boolean[] temp_value = new boolean[jadwal[row].length];
        for(int i = 0; i < jadwal[row].length; i++) {
            temp_value[i] = isAvailable(row, i);
        }
        return temp_value;
    }
    
    public static void setAvailable(Jadwal.TYPE type, int row, int i, boolean value) {
        if(type==Jadwal.TYPE.TEMPAT) my_tempat.setAvailable(row, i, value);
        else my_kandidat.setAvailable(row, i, value);
    }
    
    private void setAvailable(int row, int j, boolean value) {
        jadwal[row][j] = value;
    }
    
    public static void setAvailableOneDay(Jadwal.TYPE type, int row, int day, boolean[] value) {
        if(type==Jadwal.TYPE.TEMPAT) my_tempat.setAvailableOneDay(row, day, value);
        else my_kandidat.setAvailableOneDay(row, day, value);
    }
    
    private void setAvailableOneDay(int row, int day, boolean[] value) {
        int it = 0;
        for(int i = day*TIME_ONE_DAY; i < (day + 1)*TIME_ONE_DAY; i++, it++) {
            jadwal[row][i] = value[it];
        }
    }
    
    public static void setAvailableOneWeek(Jadwal.TYPE type, int row, int week, boolean[] value) {
        if(type==Jadwal.TYPE.TEMPAT) my_tempat.setAvailableOneWeek(row, week, value);
        else my_kandidat.setAvailableOneWeek(row, week, value);
    }
    
    private void setAvailableOneWeek(int row, int week, boolean[] value) {
        int it = 0;
        for(int i = week*TIME_ONE_WEEK; i < (week + 1)*TIME_ONE_WEEK; i++, it++) {
            jadwal[row][i] = value[it];
        }
    }
    
    public static void setAvailableAllDay(Jadwal.TYPE type, int row, boolean[] value) {
        if(type==Jadwal.TYPE.TEMPAT) my_tempat.setAvailableAllDay(row, value);
        else my_kandidat.setAvailableAllDay(row, value);
    }
    
    private void setAvailableAllDay(int row, boolean[] value) {
        System.arraycopy(value, 0, jadwal[row], 0, jadwal[row].length);
    }
    
    public static void setAvailableAll(Jadwal.TYPE type, boolean[][] value) {
        if(type==Jadwal.TYPE.TEMPAT) my_tempat.setAvailableAll(value);
        else my_kandidat.setAvailableAll(value);
    }
    
    private void setAvailableAll(boolean[][] value) {
        for(int i = 0; i < value.length; i++) {
            setAvailableAllDay(i, value[i]);
        }
    }
}
