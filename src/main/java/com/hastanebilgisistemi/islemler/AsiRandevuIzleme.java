package com.hastanebilgisistemi.islemler;

import java.util.List;

import com.hastanebilgisistemi.util.Veritabani;
import com.hastanebilgisistemi.veriler.Asi;

public class AsiRandevuIzleme {
	public static boolean asiRandevuTalebi(Asi a) {
		return Veritabani.asiRandevuTalebi(a);
	}

	public static boolean asiKaydiOlustur(int id, int doz) {
		return Veritabani.asiKaydiOlustur(id, doz);
	}

	public static Asi asiRandevuSorgulama(int id) {
		return Veritabani.asiRandevuSorgulama(id);
	}

	public static List<Asi> asilariListele() {
		return Veritabani.asilariListele();
	}
}
