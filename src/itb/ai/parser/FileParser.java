package itb.ai.parser;

//=============================================================================================
// README
//
// Nama File : FileParser.java
// Deskripsi : Kelas untuk melakukan pembacaan data dari file txt untuk kemudian diubah menjadi
// representasi spesifik untuk pengerjaan Tubes 1 AI.
// Ada tiga jenis file txt yang bisa diterima : file berisi informasi umum, file jadwal kandidat
// dan jadwal tempat
// 
// Last modified:
// 	22 Feb 2014 by Vai
//		- Implements all functionalities and driver to test them
//		- add readme
//
// Cara penggunaan:
// 	Objek dari kelas ini merupakan singleton, sehingga hanya bisa dibuat dan di-invoke
//  menggunakan static method #getInstance
//  Untuk melakukan parsing terhadap file teks bisa menggunakan #parseGeneralData,
//  #parseCandidateData, dan #parseSiteData, ketiganya menerima parameter berupa path ke file
//  yang akan dibaca, #parseGeneralData harus di-invoke terlebih dahulu untuk mendapat jumlah
//  kandidat, alternatif lain bisa menggunakan #parseAll yang menerima tiga parameter untuk
//  masing-masing file
//
//
// Setelah parsing dilakukan, data hasil parse bisa diperoleh dengan cara sbb:
//
// (Informasi umum)
// - Untuk mendapat modal awal Nanto, panggil #getModal : long
// - Untuk mendapat lama waktu bermain dalam minggu, panggil #getWaktu : int
// - Untuk mendapat energi harian Nanto, panggil #getEnergi : int
// - Untuk mendapat strength awal Nanto, panggil getStrengthAwal : int
// - Untuk mendapat charm awal Nanto, panggil #getCharmAwal : int
// - Untuk mendapat brain awal Nanto, panggil #getBrainAwal : int
//
// (Informasi Kandidat, semua method memiliki parameter yang merupakan id kandidat : 0...jumlahKandidat)
// - Untuk mendapat enlightment per jam, panggil #getEnlightmentPerJam : int
// - Untuk mendapat energi yang dihabiskan per jam, panggil #getEnergiPerJam : int
// - Untuk mendapat maximum jumlah bertemu, panggil #getMaxPerHari : int
// - Untuk mendapat kode parang prerequisite, panggil #getKodePrereq : String
// - Untuk mendapat Strength yang dibutuhkan, panggil #getStrengthNeeded : int
// - Untuk mendapat Charm yang dibutuhkan, panggil #getCharmNeeded : int
// - Untuk mendapat Brain yang dibutuhkan, panggil #getBrainNeeded : int
// (Informasi barang, semua method memiliki parameter berupa id barang : 0...jumlahBarang)
// - Untuk mendapat kode barang, panggil #getKodeBarang : String
// - Untuk mendapat harga barang, panggil #getHargaBarang : int
// - Untuk mendapat restock barang per hari, panggil #getRestockPerHari : int
//
// (Informasi Jadwal, memiliki dua parameter, yang pertama yaitu id kandidat, yang kedua hari keberapa)
// Untuk mendapat jadwal bertemu dengan candidate, panggil #getCandidateSchedule : boolean
// Untuk mendapat jadwal tempat yang buka, panggil #getSiteSchedule : boolean
//=============================================================================================

import itb.ai.komponen.Jadwal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileParser {
	
	private final int jumlahTempat = 4;
	private static FileParser instance = null;
	
	String generalData;
	String candidateScheduleData;
	String siteScheduleData;
	
	private long modal;
	private int waktu;
	private int energi;
	private int strength;
	private int charm;
	private int brain;
	private int jumlahKandidat;
	private int[] enlightmentPerJam;
	private int[] energiPerJam;
	private int[] maxPerHari;
	private String[] kodePrerequisite;
	private int[] strengthNeeded;
	private int[] charmNeeded;
	private int[] brainNeeded;
	private int jumlahBarang;
	private String[] kodeBarang;
	private int[] hargaBarang;
	private int[] restockBarang;
	
	private boolean[][] candidateSchedule;
	private boolean[][] siteSchedule;
	
	private FileParser(){
		
	}
	
	public static FileParser getInstance(){
		if (instance == null){
			instance = new FileParser();
		}
		return instance;
	}
	
	public void parseGeneralData(String generalFile){
		generalData = readFile(generalFile);
		
		//metadata
		int generalMultiplier = 6;
		int candidateMultiplier = 7; //Jumlah atribut kandidat
		int itemMultiplier = 3;
		
		String[] temp = generalData.split("\\s+");
		
		try {
			modal = Long.parseLong(temp[0]);
			waktu = Integer.parseInt(temp[1]);
			energi = Integer.parseInt(temp[2]);
			strength = Integer.parseInt(temp[3]);
			charm = Integer.parseInt(temp[4]);
			brain = Integer.parseInt(temp[5]);
			jumlahKandidat = Integer.parseInt(temp[6]);
			jumlahBarang = Integer.parseInt(temp[generalMultiplier + 1 + (jumlahKandidat*candidateMultiplier)]);
		
		
			enlightmentPerJam = new int[jumlahKandidat];
			energiPerJam = new int[jumlahKandidat];
			maxPerHari = new int[jumlahKandidat];
			kodePrerequisite = new String[jumlahKandidat];
			strengthNeeded = new int[jumlahKandidat];
			charmNeeded = new int[jumlahKandidat];
			brainNeeded = new int[jumlahKandidat];
			int offset = 0;
			for (int i = 0;i < jumlahKandidat; i++){
				offset = generalMultiplier + 1 + (i*candidateMultiplier);
				enlightmentPerJam[i] = Integer.parseInt(temp[offset]);
				energiPerJam[i] = Integer.parseInt(temp[offset + 1]);
				maxPerHari[i] = Integer.parseInt(temp[offset + 2]);
				kodePrerequisite[i] = temp[offset + 3];
				strengthNeeded[i] = Integer.parseInt(temp[offset + 4]);
				charmNeeded[i] = Integer.parseInt(temp[offset + 5]);
				brainNeeded[i] = Integer.parseInt(temp[offset + 6]);
			}
			
			kodeBarang = new String[jumlahBarang];
			hargaBarang = new int[jumlahBarang];
			restockBarang = new int[jumlahBarang];
			int base = generalMultiplier + 2 + (jumlahKandidat*candidateMultiplier);
			for(int i = 0; i < jumlahBarang; i++){
				offset = base + (i*itemMultiplier);
				kodeBarang[i] = temp[offset];
				hargaBarang[i] = Integer.parseInt(temp[offset + 1]);
				restockBarang[i] = Integer.parseInt(temp[offset + 2]);
			}
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return;
		}
	}
	
	public void parseCandidateSchedule(String candidateScheduleFile){
		candidateScheduleData = readFile(candidateScheduleFile);
		if (jumlahKandidat <= 0 || waktu <= 0) return;
		candidateSchedule = new boolean[jumlahKandidat][];
		for(int i = 0; i < jumlahKandidat; i++) {
            candidateSchedule[i] = new boolean[waktu*Jadwal.TIME_ONE_WEEK];
            for(int j = 0; j < waktu*Jadwal.TIME_ONE_WEEK; j++) {
                candidateSchedule[i][j] = false;
            }
        }
		
		String[] temp = candidateScheduleData.split("\\s+");
		if (temp.length != jumlahKandidat){
			System.out.println("ERROR : Jadwal Invalid. Jumlah Kandidat tidak sesuai.");
			return;
		}
		char[] perCandidateData;
		for(int i = 0; i < jumlahKandidat; i++) {
			perCandidateData = temp[i].toCharArray();
			if (perCandidateData.length != waktu*Jadwal.TIME_ONE_WEEK){
				System.out.println("ERROR : Jadwal Invalid. Jumlah waktu tidak sesuai.");
				return;
			}
			for(int j = 0; j < waktu*Jadwal.TIME_ONE_WEEK; j++) {
                candidateSchedule[i][j] = (perCandidateData[j] == '1') ? true : false;
            }
        }
	}
	
	public void parseSiteScheduleData(String siteScheduleFile){
		siteScheduleData = readFile(siteScheduleFile);
		if (waktu <= 0) return;
		siteSchedule = new boolean[jumlahTempat][];
		for(int i = 0; i < jumlahTempat; i++) {
            siteSchedule[i] = new boolean[waktu*Jadwal.TIME_ONE_WEEK];
            for(int j = 0; j < waktu*Jadwal.TIME_ONE_WEEK; j++) {
                siteSchedule[i][j] = false;
            }
        }
		
		String[] temp = siteScheduleData.split("\\s+");
		if (temp.length != jumlahTempat){
			System.out.println("ERROR : Jadwal Invalid. Jumlah Tempat tidak sesuai.");
			return;
		}
		char[] perSiteData;
		for(int i = 0; i < jumlahTempat; i++) {
			perSiteData = temp[i].toCharArray();
			if (perSiteData.length != waktu*Jadwal.TIME_ONE_WEEK){
				System.out.println("ERROR : Jadwal Invalid. Jumlah waktu tidak sesuai.");
				return;
			}
			for(int j = 0; j < waktu*Jadwal.TIME_ONE_WEEK; j++) {
                siteSchedule[i][j] = (perSiteData[j] == '1') ? true : false;
            }
        }
	}
	
	public void parseAll(String generalFilePath, String candidateScheduleFilePath, String siteScheduleFilePath){
		parseGeneralData(generalFilePath);
		parseCandidateSchedule(candidateScheduleFilePath);
		parseSiteScheduleData(siteScheduleFilePath);
	}
	
	public String readFile(String filepath){
		StringBuffer buffer = new StringBuffer("");
    	String text;
    	
    	FileInputStream finput = null;
    	int data;
    	
    	try{
    		finput = new FileInputStream(filepath);    		
    	} catch (FileNotFoundException e) {
    		e.printStackTrace();
    		return null;
    	}
    	
    	try {
    		while ((data = finput.read()) != -1) {
    			buffer.append((char) data);
    		}
    		text = buffer.toString();
    	} catch (IOException e){
    		e.printStackTrace();
    		return null;
    	}
    	
    	try{
    		finput.close();
    	} catch (IOException e){
    		e.printStackTrace();
    	}
    	
    	return text;
	}
	
	public long getModalAwal(){
		return modal;
	}
	
	public int getWaktu(){
		return waktu;
	}
	
	public int getEnergi(){
		return energi;
	}
	
	public int getStrengthAwal(){
		return strength;
	}
	
	public int getCharmAwal(){
		return charm;
	}
	
	public int getBrainAwal(){
		return brain;
	}
	
	public int getJumlahKandidat(){
		return jumlahKandidat;
	}
	
	public int getJumlahBarang(){
		return jumlahBarang;
	}
	
	public int getEnlightmentPerJam(int n){
		return enlightmentPerJam[n];
	}
	
	public int getEnergiPerJam(int n){
		return energiPerJam[n];
	}
	
	public int getMaxPerHari(int n){
		return maxPerHari[n];
	}
	
	public String getKodePrereq(int n){
		return kodePrerequisite[n];
	}
	
	public int getStrengthNeeded(int n){
		return strengthNeeded[n];
	}
	
	public int getCharmNeeded(int n){
		return charmNeeded[n];
	}
	
	public int getBrainNeeded(int n){
		return brainNeeded[n];
	}
	
	public String getKodeBarang(int m){
		return kodeBarang[m];
	}
	
	public int getHargaBarang(int m){
		return hargaBarang[m];
	}
	
	public int getRestockPerHari(int m){
		return restockBarang[m];
	}
	
	public boolean getCandidateSchedule(int i, int j){
		return candidateSchedule[i][j];
	}
	
	public boolean getSiteSchedule(int i, int j){
		return siteSchedule[i][j];
	}
	
	public void printGeneralData(){
		StringBuffer candidateBuffer = new StringBuffer();
		StringBuffer itemBuffer = new StringBuffer();
		
		try{
			for (int i = 0; i < getJumlahKandidat();i++) {
				candidateBuffer.append(
						"\nEnlightment/jam " + (i+1) + " : " + getEnlightmentPerJam(i)
						+ "\nEnergi/jam " + (i+1) + " : " + getEnergiPerJam(i)
						+ "\nMax/hari " + (i+1) + " : " + getMaxPerHari(i)
						+ "\nKode Prereq " + (i+1) + " : " + getKodePrereq(i)
						+ "\nStrength " + (i+1) + " : " + getStrengthNeeded(i)
						+ "\nCharm " + (i+1) + " : " + getCharmNeeded(i)
						+ "\nBrain " + (i+1) + " : " + getBrainNeeded(i)
						);
			}
			for (int i = 0;i < getJumlahBarang();i++){
				itemBuffer.append(
						"\nKode " + (i+1) + " : " + getKodeBarang(i)
						+ "\nHarga " + (i+1) + " : " + getHargaBarang(i)
						+ "\nRestock/hari " + (i+1) + " : " + getRestockPerHari(i)
						);
			}
		} catch (NumberFormatException e){
			e.printStackTrace();
			return;
		}
		
		
		System.out.println(
				"\nModal awal : " + getModalAwal()
				+ "\nWaktu : " + getWaktu()
				+ "\nEnergi : " + getEnergi()
				+ "\nStrength awal : " + getStrengthAwal()
				+ "\nCharm awal : " + getCharmAwal()
				+ "\nBrain awal : " + getBrainAwal()
				+ "\n\nJumlah Kandidat : " + getJumlahKandidat()
				+ candidateBuffer.toString()
				+ "\n\nJumlah Barang : " + getJumlahBarang()
				+ itemBuffer.toString()
				);
		
	}
	
	public void printCandidateScheduleData(){
		if (candidateScheduleData == null) return; 
		
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < getJumlahKandidat(); i++) {
			buffer.append(
					"\n\nJadwal Kandidat " + (i+1) + " "
					);
			for(int j = 0; j < waktu*Jadwal.TIME_ONE_WEEK;j++){
				buffer.append(getCandidateSchedule(i, j) + " ");
			}
		}
		
		System.out.println(
				"\nJumlah Kandidat : " + getJumlahKandidat()
				+ buffer.toString()
				);
	}
	
	public void printSiteScheduleData(){
		if (siteScheduleData == null) return; 
		
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < jumlahTempat; i++) {
			buffer.append(
					"\n\nJadwal Tempat " + (i+1) + " "
					);
			for(int j = 0; j < waktu*Jadwal.TIME_ONE_WEEK;j++){
				buffer.append(getSiteSchedule(i, j) + " ");
			}
		}
		
		System.out.println(
				"\nJumlah Tempat : " + jumlahTempat
				+ buffer.toString()
				);
	}
	
	public void printAll(){
		printGeneralData();
		printCandidateScheduleData();
		printSiteScheduleData();
	}
	
	
	@Override
	public String toString(){
		return "General Data:\n" + generalData + "\nCandidate Schedule:\n" + candidateScheduleData + "\nSite Schedule:\n" + siteScheduleData;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String generalFilePath = "asset/general.txt";
    	String candidateScheduleFilePath = "asset/candidate.txt";
    	String siteScheduleFilePath = "asset/site.txt";
    	FileParser.getInstance().parseAll(generalFilePath, candidateScheduleFilePath, siteScheduleFilePath);
    	FileParser.getInstance().printAll();
	}
	
}
