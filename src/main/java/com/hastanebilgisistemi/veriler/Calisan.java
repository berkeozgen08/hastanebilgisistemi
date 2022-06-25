package com.hastanebilgisistemi.veriler;

public class Calisan {
	private int calisanId;
	private String tamAd;
	private String rol;
	private int maas;
	
	public Calisan(int calisanId, String tamAd, String rol, int maas) {
		this.calisanId = calisanId;
		this.tamAd = tamAd;
		this.rol = rol;
		this.maas = maas;
	}
	
	public Calisan(String tamAd, String rol, int maas) {
		calisanId = -1;
		this.tamAd = tamAd;
		this.rol = rol;
		this.maas = maas;
	}

	public int getCalisanId() {
		return calisanId;
	}

	public void setCalisanId(int calisanId) {
		this.calisanId = calisanId;
	}

	public String getTamAd() {
		return tamAd;
	}

	public void setTamAd(String tamAd) {
		this.tamAd = tamAd;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public int getMaas() {
		return maas;
	}

	public void setMaas(int maas) {
		this.maas = maas;
	}

	@Override
	public String toString() {
		return String.format(
			"ID: %s, Tam Ad: %s, Rol: %s, Maas: %d",
			calisanId,
			tamAd,
			rol,
			maas
		);
	}
}
