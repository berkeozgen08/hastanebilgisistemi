package com.hastanebilgisistemi.veriler;

import com.hastanebilgisistemi.util.Tarih;

public class Asi {
	private int asiId;
	private String hastaTamAd;
	private int hastaTc;
	private Tarih tarih;
	private boolean yapildiMi;
	private int doz;
	
	public Asi(int asiId, String hastaTamAd, int hastaTc, Tarih tarih, boolean yapildiMi, int doz) {
		this.asiId = asiId;
		this.hastaTamAd = hastaTamAd;
		this.hastaTc = hastaTc;
		this.tarih = tarih;
		this.yapildiMi = yapildiMi;
		this.doz = doz;
	}
	
	public Asi(String hastaTamAd, int hastaTc, Tarih tarih) {
		asiId = -1;
		this.hastaTamAd = hastaTamAd;
		this.hastaTc = hastaTc;
		this.tarih = tarih;
		yapildiMi = false;
		doz = 0;
	}

	public int getAsiId() {
		return asiId;
	}

	public void setAsiId(int asiId) {
		this.asiId = asiId;
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

	public Tarih getTarih() {
		return tarih;
	}

	public void setTarih(Tarih tarih) {
		this.tarih = tarih;
	}

	public boolean isYapildiMi() {
		return yapildiMi;
	}

	public void setYapildiMi(boolean yapildiMi) {
		this.yapildiMi = yapildiMi;
	}

	public int getDoz() {
		return doz;
	}

	public void setDoz(int doz) {
		this.doz = doz;
	}

	@Override
	public String toString() {
		return String.format(
			"ID: %d, Hasta Adi: %s, Hasta TC: %d, Tarih: %s, Yapildi Mi: %s, Doz: %d",
			asiId,
			hastaTamAd,
			hastaTc,
			tarih,
			yapildiMi ? "Evet" : "Hayir",
			doz
		);
	}
}
