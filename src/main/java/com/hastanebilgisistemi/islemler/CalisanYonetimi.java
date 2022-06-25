package com.hastanebilgisistemi.islemler;

import java.util.List;

import com.hastanebilgisistemi.util.Veritabani;
import com.hastanebilgisistemi.veriler.Calisan;

public class CalisanYonetimi {
	public static boolean calisanEkle(Calisan c) {
		return Veritabani.calisanEkle(c);
	}

	public static List<Calisan> maasListele() {
		return Veritabani.maasListele();
	}
}