/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package itb.ai;

/**
 *
 * @author Setyo Legowo <setyo.legowo@live.com>
 */
public class Nanto {
    public final static String TAG = Nanto.class.getSimpleName();
    public static enum ATRIBUT { MONEY, STRENGTH, CHARM, BRAIN }
    
    private static Nanto my;
    
    /**
     * MEMBER
     */
    private int[] nilai_attribut;
    
    /**
     * KONSTRUKTOR
     */
    public static void Inisialisasi() {
        if(my == null) {
            my = new Nanto(0,0,0,0);
        }
    }
    
    private Nanto(int money, int strength, int charm, int brain) {
        this.nilai_attribut = new int[4];
        this.nilai_attribut[0] = money;
        this.nilai_attribut[1] = strength;
        this.nilai_attribut[2] = charm;
        this.nilai_attribut[3] = brain;
    }
    
    /**
     * METODA
     */
    public static String my_name() {
        return TAG;
    }
    
    private static Nanto getMy() {
        Inisialisasi();
        return my;
    }
    
    public static int getAtribut(Nanto.ATRIBUT att) {
        int temp_val = 0;
        Nanto me = Nanto.getMy();
        
        switch(att) {
            case MONEY: temp_val = me.nilai_attribut[0]; break;
            case STRENGTH: temp_val = me.nilai_attribut[1]; break;
            case CHARM: temp_val = me.nilai_attribut[2]; break;
            case BRAIN: temp_val = me.nilai_attribut[3]; break;
        }
        
        return temp_val;
    }
    
    public static int getAtribut(int index) {
        Nanto me = Nanto.getMy();
        return (index >= 0 && index < me.nilai_attribut.length)?me.nilai_attribut[index]:0;
    }
    
    public static int getMoney() {
        return getAtribut(ATRIBUT.MONEY);
    }
    
    public static int getStrength() {
        return getAtribut(ATRIBUT.STRENGTH);
    }
    
    public static int getCharm() {
        return getAtribut(ATRIBUT.CHARM);
    }
    
    public static int getBrain() {
        return getAtribut(ATRIBUT.BRAIN);
    }
    
    public static void setAtribut(Nanto.ATRIBUT att, int value) {
        Nanto me = Nanto.getMy();
        switch(att) {
            case MONEY: me.nilai_attribut[0] = value; break;
            case STRENGTH: me.nilai_attribut[1] = value; break;
            case CHARM: me.nilai_attribut[2] = value; break;
            case BRAIN: me.nilai_attribut[3] = value; break;
        }
    }
    
    public static void setAtribut(int index, int value) {
        Nanto me = Nanto.getMy();
        if(index >= 0 && index < me.nilai_attribut.length) me.nilai_attribut[index] = value;
    }
    
    public static void setAllAtribut(int money, int strength, int charm, int brain) {
        Nanto me = Nanto.getMy();
        me.nilai_attribut[0] = money;
        me.nilai_attribut[1] = strength;
        me.nilai_attribut[2] = charm;
        me.nilai_attribut[3] = brain;
    }
    
    public static void setMoney(int value) {
        setAtribut(ATRIBUT.MONEY, value);
    }
    
    public static void setStrength(int value) {
        setAtribut(ATRIBUT.STRENGTH, value);
    }
    
    public static void setCharm(int value) {
        setAtribut(ATRIBUT.CHARM, value);
    }
    
    public static void setBrain(int value) {
        setAtribut(ATRIBUT.BRAIN, value);
    }

    public static String tostring() {
        Nanto me = Nanto.getMy();
        return "Nanto{" + "money=" + me.nilai_attribut[0] + 
                ", strength=" + me.nilai_attribut[1] + 
                ", charm=" + me.nilai_attribut[2] + 
                ", brain=" + me.nilai_attribut[3] + '}';
    }
    
}
