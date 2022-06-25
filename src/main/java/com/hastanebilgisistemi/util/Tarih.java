package com.hastanebilgisistemi.util;

import java.text.DateFormat;
import java.util.Calendar;

public class Tarih {
	private Calendar calendar;

	public Tarih() {
		calendar = Calendar.getInstance();
	}

	public Tarih(int yil, int ay, int gun, int saat, int dakika) {
		this();
		calendar.set(yil, ay - 1, gun, saat, dakika, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}

	public Tarih(long ms) {
		this();
		calendar.setTimeInMillis(ms);
	}

	public long getMs() {
		return calendar.getTimeInMillis();
	}

	public long[] getAralik() {
		long[] aralik = new long[2];
		Calendar temp = (Calendar) calendar.clone();
		temp.set(Calendar.MINUTE, 0);
		temp.set(Calendar.SECOND, 0);
		temp.set(Calendar.MILLISECOND, 0);
		temp.set(Calendar.HOUR_OF_DAY, 0);
		aralik[0] = temp.getTimeInMillis();
		temp.set(Calendar.HOUR_OF_DAY, 24);
		aralik[1] = temp.getTimeInMillis();
		return aralik;
	}

	@Override
	public String toString() {
		String s = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG).format(calendar.getTime());
		return s.substring(0, s.lastIndexOf(":"));
	}
}
