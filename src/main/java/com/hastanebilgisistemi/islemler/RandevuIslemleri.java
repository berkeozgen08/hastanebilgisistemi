package com.hastanebilgisistemi.islemler;

import com.hastanebilgisistemi.util.Veritabani;
import com.hastanebilgisistemi.veriler.Randevu;

public class RandevuIslemleri {
	public static boolean randevuTalebi(Randevu r) {
		return Veritabani.randevuTalebi(r);
	}

	public static boolean talepUygunluk(Randevu r) {
		return Veritabani.talepUygunluk(r);
	}
}
