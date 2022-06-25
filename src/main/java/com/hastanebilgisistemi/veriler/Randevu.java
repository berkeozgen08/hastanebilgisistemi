package com.hastanebilgisistemi.veriler;

import com.hastanebilgisistemi.util.Tarih;

public class Randevu {
	private int randevuId;
	private String hastaTamAd;
	private int hastaTc;
	private int calisanId;
	private Tarih tarih;
	
	public Randevu(int randevuId, String hastaTamAd, int hastaTc, int calisanId, Tarih tarih) {
		this.randevuId = randevuId;
		this.hastaTamAd = hastaTamAd;
		this.hastaTc = hastaTc;
		this.calisanId = calisanId;
		this.tarih = tarih;
	}
	
	public Randevu(String hastaTamAd, int hastaTc, int calisanId, Tarih tarih) {
		randevuId = -1;
		this.hastaTamAd = hastaTamAd;
		this.hastaTc = hastaTc;
		this.calisanId = calisanId;
		this.tarih = tarih;
	}

	public int getRandevuId() {
		return randevuId;
	}

	public void setRandevuId(int randevuId) {
		this.randevuId = randevuId;
	}

	public String getHastaTamAd() {
		return hastaTamAd;
	}

	public void setHastaTamAd(String hastaTamAd) {
		this.hastaTamAd = hastaTamAd;
	}

	public int getHastaTc() {
		return hastaTc;
	}

	public void setHastaTc(int hastaTc) {
		this.hastaTc = hastaTc;
	}

	public int getCalisanId() {
		return calisanId;
	}

	public void setCalisanId(int calisanId) {
		this.calisanId = calisanId;
	}
	
	public Tarih getTarih() {
		return tarih;
	}

	public void setTarih(Tarih tarih) {
		this.tarih = tarih;
	}

	@Override
	public String toString() {
		return String.format(
			"ID: %d, Hasta Adi: %s, Hasta TC: %d, Calisan ID: %d, Tarih: %s",
			randevuId,
			hastaTamAd,
			hastaTc,
			calisanId,
			tarih
		);
	}
}
