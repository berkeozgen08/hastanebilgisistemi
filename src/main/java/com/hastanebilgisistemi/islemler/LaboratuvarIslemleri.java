package com.hastanebilgisistemi.islemler;

import java.util.List;

import com.hastanebilgisistemi.util.Veritabani;
import com.hastanebilgisistemi.veriler.Tahlil;

public class LaboratuvarIslemleri {
	public static boolean tahlilTalebiOlustur(Tahlil t) {
		return Veritabani.tahlilTalebiOlustur(t);
	}

	public static Tahlil siradakiTahlil() {
		return Veritabani.siradakiTahlil();
	}

	public static boolean tahlilSonucuGir(int id, int degerA, int degerB, int degerC) {
		return Veritabani.tahlilSonucuGir(id, degerA, degerB, degerC);
	}

	public static Tahlil tahlilSonucuSorgula(int id) {
		return Veritabani.tahlilSonucuSorgula(id);
	}

	public static List<Tahlil> tahlilleriListele() {
		return Veritabani.tahlilleriListele();
	}
}