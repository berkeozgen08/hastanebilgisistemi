package com.hastanebilgisistemi.veriler;

public class Tahlil {
	private int tahlilId;
	private int calisanId;
	private String hastaTamAd;
	private int hastaTc;
	private int degerA;
	private int degerB;
	private int degerC;
	
	public Tahlil(int calisanId, String hastaTamAd, int hastaTc) {
		tahlilId = -1;
		this.calisanId = calisanId;
		this.hastaTamAd = hastaTamAd;
		this.hastaTc = hastaTc;
		this.degerA = -1;
		this.degerB = -1;
		this.degerC = -1;
	}

	public Tahlil(int tahlilId, int calisanId, String hastaTamAd, int hastaTc, int degerA, int degerB, int degerC) {
		this.tahlilId = tahlilId;
		this.calisanId = calisanId;
		this.hastaTamAd = hastaTamAd;
		this.hastaTc = hastaTc;
		this.degerA = degerA;
		this.degerB = degerB;
		this.degerC = degerC;
	}

	public int getTahlilId() {
		return tahlilId;
	}

	public void setTahlilId(int tahlilId) {
		this.tahlilId = tahlilId;
	}

	public int getCalisanId() {
		return calisanId;
	}

	public void setCalisanId(int calisanId) {
		this.calisanId = calisanId;
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

	public int getDegerA() {
		return degerA;
	}

	public void setDegerA(int degerA) {
		this.degerA = degerA;
	}

	public int getDegerB() {
		return degerB;
	}

	public void setDegerB(int degerB) {
		this.degerB = degerB;
	}

	public int getDegerC() {
		return degerC;
	}

	public void setDegerC(int degerC) {
		this.degerC = degerC;
	}

	public boolean yapildiMi() {
		return degerA != -1 && degerB != -1 && degerC != -1;
	}

	@Override
	public String toString() {
		if (yapildiMi()) {
			return String.format(
				"ID: %d, Calisan ID: %d, Hasta Adi: %s, Hasta TC: %d, Deger A: %d, Deger B: %d, Deger C: %d",
				tahlilId,
				calisanId,
				hastaTamAd,
				hastaTc,
				degerA,
				degerB,
				degerC
			);
		} else {
			return String.format(
				"ID: %d, Calisan ID: %d, Hasta Adi: %s, Hasta TC: %d",
				tahlilId,
				calisanId,
				hastaTamAd,
				hastaTc
			);
		}
	}
}
