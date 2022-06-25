package com.hastanebilgisistemi.veriler;

public class Muayene {
	private int muayeneId;
	private String bulgular;
	
	public Muayene(int muayeneId, String bulgular) {
		this.muayeneId = muayeneId;
		this.bulgular = bulgular;
	}
	
	public Muayene(String bulgular) {
		muayeneId = -1;
		this.bulgular = bulgular;
	}

	public int getMuayeneId() {
		return muayeneId;
	}

	public void setMuayeneId(int muayeneId) {
		this.muayeneId = muayeneId;
	}

	public String getBulgular() {
		return bulgular;
	}

	public void setBulgular(String bulgular) {
		this.bulgular = bulgular;
	}

	@Override
	public String toString() {
		return String.format(
			"ID: %d, Bulgular: %s",
			muayeneId,
			bulgular
		);
	}
}
