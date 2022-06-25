package com.hastanebilgisistemi.islemler;

import java.util.List;

import com.hastanebilgisistemi.util.Veritabani;
import com.hastanebilgisistemi.veriler.Muayene;
import com.hastanebilgisistemi.veriler.Randevu;

public class DoktorMuayeneIslemleri {
	public static List<Randevu> gunlukRandevular() {
		return Veritabani.gunlukRandevular();
	}

	public static boolean muayeneKaydet(Muayene m) {
		return Veritabani.muayeneKaydet(m);
	}
}
