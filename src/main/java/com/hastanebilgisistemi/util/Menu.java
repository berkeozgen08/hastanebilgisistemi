package com.hastanebilgisistemi.util;

import java.util.List;
import java.util.Scanner;

import com.hastanebilgisistemi.islemler.*;
import com.hastanebilgisistemi.veriler.*;

public class Menu {
	private static Scanner scanner;
	private static int menu;

	public static void baslat() {
		scanner = new Scanner(System.in);
		menu = 0;
		while (menu != -1) {
			try {
				switch (menu) {
					case 0: anaMenu(); break;
					case 1: calisanIslemleri(); break;
					case 2: randevuIslemleri(); break;
					case 3: muayeneIslemleri(); break;
					case 4: laboratuvarIslemleri(); break;
					case 5: asiIslemleri(); break;
				}
			} catch (Exception e) {
				System.err.println("Hatali giris.");
				if (scanner.hasNextLine()) scanner.nextLine();
			}
		}
		scanner.close();
	}

	public static void anaMenu() {
		System.out.print("\nANA MENU\n0. Cikis\n1. Calisan islemleri\n2. Randevu islemleri\n3. Muayene islemleri\n4. Laboratuvar islemleri\n5. Asi islemleri\nIslem menusu seciniz: ");
		int input = scanner.nextInt();
		if (input >= 1 && input <= 5) {
			menu = input;
		} else if (input == 0) {
			menu = -1;
		} else {
			System.err.println("Gecerli bir secim yapiniz.");
			anaMenu();
		}
	}

	public static void calisanIslemleri() {
		System.out.print("\nCALISAN ISLEMLERI\n0. Geri\n1. Calisan ekle\n2. Calisan ucret listesi goruntule\nIslem seciniz: ");
		int input = scanner.nextInt();
		switch (input) {
			case 0: menu = 0; break;
			case 1: calisanEkle(); break;
			case 2: maasListele(); break;
			default:
				System.err.println("Gecerli bir secim yapiniz.");
				calisanIslemleri();
		}
	}

	public static void calisanEkle() {
		if (CalisanYonetimi.calisanEkle(calisanGir())) {
			System.out.println("Basarili.");
		} else {
			System.out.println("Basarisiz.");
		}
	}

	public static void maasListele() {
		List<Calisan> list = CalisanYonetimi.maasListele();
		if (list.size() != 0) {
			list.forEach(System.out::println);
		} else {
			System.err.println("Kayit bulunamadi.");
		}
	}

	public static void randevuIslemleri() {
		System.out.print("\nRANDEVU ISLEMLERI\n0. Geri\n1. Randevu talebi\n2. Talep uygunluk kontrolu\nIslem seciniz: ");
		int input = scanner.nextInt();
		switch (input) {
			case 0: menu = 0; break;
			case 1: randevuTalebi(); break;
			case 2: talepUygunluk(); break;
			default:
				System.err.println("Gecerli bir secim yapiniz.");
				randevuIslemleri();
		}
	}

	public static void randevuTalebi() {
		Randevu r = randevuGir();
		if (RandevuIslemleri.talepUygunluk(r)) {
			if (RandevuIslemleri.randevuTalebi(r)) {
				System.out.println("Basarili.");
			} else {
				System.err.println("Basarisiz.");
			}
		} else {
			System.err.println("Randevu talebi uygun degil.");
		}
	}

	public static void talepUygunluk() {
		if (RandevuIslemleri.talepUygunluk(randevuGir())) {
			System.out.println("Randevu talebi uygun.");
		} else {
			System.err.println("Randevu talebi uygun degil.");
		}
	}

	public static void muayeneIslemleri() {
		System.out.print("\nMUAYENE ISLEMLERI\n0. Geri\n1. Gunluk randevulari listele\n2. Muayene kaydi olustur\nIslem seciniz: ");
		int input = scanner.nextInt();
		switch (input) {
			case 0: menu = 0; break;
			case 1: gunlukRandevular(); break;
			case 2: muayeneKaydet(); break;
			default:
				System.err.println("Gecerli bir secim yapiniz.");
				muayeneIslemleri();
		}
	}

	public static void gunlukRandevular() {
		List<Randevu> list = DoktorMuayeneIslemleri.gunlukRandevular();
		if (list.size() != 0) {
			list.forEach(System.out::println);
		} else {
			System.err.println("Kayit bulunamadi.");
		}
	}

	public static void muayeneKaydet() {
		if (DoktorMuayeneIslemleri.muayeneKaydet(muayeneGir())) {
			System.out.println("Basarili.");
		} else {
			System.err.println("Basarisiz.");
		}
	}

	public static void laboratuvarIslemleri() {
		System.out.print("\nLABORATUVAR ISLEMLERI\n0. Geri\n1. Tahlil talebi olustur\n2. Siradaki tahlili goruntule\n3. Tahlil sonucu gir\n4. Tahlil sonucu sorgula\n5. Tahlilleri listele\nIslem seciniz: ");
		int input = scanner.nextInt();
		switch (input) {
			case 0: menu = 0; break;
			case 1: tahlilTalebiOlustur(); break;
			case 2: siradakiTahlil(); break;
			case 3: tahlilSonucuGir(); break;
			case 4: tahlilSonucuSorgula(); break;
			case 5: tahlilleriListele(); break;
			default:
				System.err.println("Gecerli bir secim yapiniz.");
				laboratuvarIslemleri();
		}
	}

	public static void tahlilTalebiOlustur() {
		if (LaboratuvarIslemleri.tahlilTalebiOlustur(tahlilGir())) {
			System.out.println("Basarili.");
		} else {
			System.err.println("Basarisiz.");
		}
	}

	public static void siradakiTahlil() {
		Tahlil t = LaboratuvarIslemleri.siradakiTahlil();
		if (t != null) {
			System.out.println(t);
		} else {
			System.err.println("Kayit bulunamadi.");
		}
	}

	public static void tahlilSonucuGir() {
		System.out.print("Tahlil ID'si: ");
		int id = scanner.nextInt();

		System.out.print("Deger A: ");
		int degerA = scanner.nextInt();
		System.out.print("Deger B: ");
		int degerB = scanner.nextInt();
		System.out.print("Deger C: ");
		int degerC = scanner.nextInt();

		if (LaboratuvarIslemleri.tahlilSonucuGir(id, degerA, degerB, degerC)) {
			System.out.println("Basarili.");
		} else {
			System.err.println("Basarisiz.");
		}
	}

	public static void tahlilSonucuSorgula() {
		System.out.print("Tahlil ID'si: ");
		int id = scanner.nextInt();

		Tahlil t = LaboratuvarIslemleri.tahlilSonucuSorgula(id);
		if (t != null) {
			System.out.println(t);
		} else {
			System.err.println("Kayit bulunamadi.");
		}
	}

	public static void tahlilleriListele() {
		List<Tahlil> list = LaboratuvarIslemleri.tahlilleriListele();
		if (list.size() != 0) {
			list.forEach(System.out::println);
		} else {
			System.err.println("Kayit bulunamadi.");
		}
	}

	public static void asiIslemleri() {
		System.out.print("\nASI ISLEMLERI\n0. Geri\n1. Asi randevu talebi\n2. Asi kaydi olustur\n3. Asi randevu sorgulama\n4. Asilari listele\nIslem seciniz: ");
		int input = scanner.nextInt();
		switch (input) {
			case 0: menu = 0; break;
			case 1: asiRandevuTalebi(); break;
			case 2: asiKaydiOlustur(); break;
			case 3: asiRandevuSorgulama(); break;
			case 4: asilariListele(); break;
			default:
				System.err.println("Gecerli bir secim yapiniz.");
				laboratuvarIslemleri();
		}
	}

	public static void asiRandevuTalebi() {
		if (AsiRandevuIzleme.asiRandevuTalebi(asiGir())) {
			System.out.println("Basarili.");
		} else {
			System.err.println("Basarisiz.");
		}
	}

	public static void asiKaydiOlustur() {
		System.out.print("Asi ID'si: ");
		int id = scanner.nextInt();

		System.out.print("Doz: ");
		int doz = scanner.nextInt();
		if (doz != 1 && doz != 2) {
			System.err.println("Gecerli bir doz giriniz.");
		} else {
			if (AsiRandevuIzleme.asiKaydiOlustur(id, doz)) {
				System.out.println("Basarili.");
			} else {
				System.err.println("Basarisiz.");
			}
		}
	}

	public static void asiRandevuSorgulama() {
		System.out.print("Asi ID'si: ");
		int id = scanner.nextInt();
		Asi a = AsiRandevuIzleme.asiRandevuSorgulama(id);
		if (a != null) {
			System.out.println(a);
		} else {
			System.err.println("Kayit bulunamadi.");
		}
	}

	public static void asilariListele() {
		List<Asi> list = AsiRandevuIzleme.asilariListele();
		if (list.size() != 0) {
			list.forEach(System.out::println);
		} else {
			System.err.println("Kayit bulunamadi.");
		}
	}

	public static Tarih tarihGir() {
		System.out.print("Yil: ");
		int yil = scanner.nextInt();
		System.out.print("Ay: ");
		int ay = scanner.nextInt();
		System.out.print("Gun: ");
		int gun = scanner.nextInt();
		System.out.print("Saat: ");
		int saat = scanner.nextInt();
		System.out.print("Dakika: ");
		int dakika = scanner.nextInt();
		return new Tarih(yil, ay, gun, saat, dakika);
	}

	public static Randevu randevuGir() {
		if (scanner.hasNextLine()) scanner.nextLine();
		System.out.print("Hasta Adi: ");
		String hastaTamAd = scanner.nextLine();
		System.out.print("Hasta TC: ");
		int hastaTc = scanner.nextInt();
		System.out.print("Calisan ID: ");
		int calisanId = scanner.nextInt();
		return new Randevu(hastaTamAd, hastaTc, calisanId, tarihGir());
	}

	public static Calisan calisanGir() {
		if (scanner.hasNextLine()) scanner.nextLine();
		System.out.print("Calisan adi: ");
		String tamAd = scanner.nextLine();
		System.out.print("Calisan rolu: ");
		String rol = scanner.nextLine();
		System.out.print("Calisan maasi: ");
		int maas = scanner.nextInt();
		return new Calisan(tamAd, rol, maas);
	}

	public static Muayene muayeneGir() {
		System.out.print("Muayene ID: ");
		int muayeneId = scanner.nextInt();
		if (scanner.hasNextLine()) scanner.nextLine();
		System.out.print("Bulgular: ");
		String bulgular = scanner.nextLine();
		return new Muayene(muayeneId, bulgular);
	}

	public static Tahlil tahlilGir() {
		System.out.print("Calisan ID: ");
		int calisanId = scanner.nextInt();
		System.out.print("Hasta adi: ");
		if (scanner.hasNextLine()) scanner.nextLine();
		String hastaTamAd = scanner.nextLine();
		System.out.print("Hasta TC: ");
		int hastaTc = scanner.nextInt();
		return new Tahlil(calisanId, hastaTamAd, hastaTc);
	}

	public static Asi asiGir() {
		System.out.print("Hasta adi: ");
		if (scanner.hasNextLine()) scanner.nextLine();
		String hastaTamAd = scanner.nextLine();
		System.out.print("Hasta TC: ");
		int hastaTc = scanner.nextInt();
		Tarih tarih = tarihGir();
		return new Asi(hastaTamAd, hastaTc, tarih);
	}
}
