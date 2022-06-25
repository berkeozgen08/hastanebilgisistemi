package com.hastanebilgisistemi.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hastanebilgisistemi.veriler.*;

import java.sql.PreparedStatement;

public class Veritabani {
	private static String host;
	private static String port;
	private static String dbname;
	private static String user;
	private static String password;
	private static String url;
	private static Connection connection;
	
	public static void baglan() {
		host = System.getenv("DATABASE_HOST");
		port = System.getenv("DATABASE_PORT");
		dbname = System.getenv("DATABASE_DBNAME");
		user = System.getenv("DATABASE_USER");
		password = System.getenv("DATABASE_PASSWORD");
		url = "jdbc:postgresql://" + host + ":" + port + "/" + dbname;
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.err.println("Veritabani baglantisi yapilamadi.");
			System.exit(1);
		}
	}

	private static class Sorgular {
		public static final String asiRandevuTalebi =
		"INSERT INTO asi(hasta_tam_ad, hasta_tc, tarih_epoch, yapildi_mi, doz) VALUES(?, ?, ?, false, 0)";

		public static final String asiKaydiOlustur =
		"UPDATE asi SET yapildi_mi = true, doz = ? WHERE asi.asi_id = ?";

		public static final String asiRandevuSorgulama =
		"SELECT * FROM asi WHERE asi.asi_id = ?";

		public static final String asilariListele =
		"SELECT * FROM asi";

		public static final String calisanEkle =
		"INSERT INTO calisanlar(tam_ad, rol, maas) VALUES(?, ?, ?)";

		public static final String maasListele =
		"SELECT * FROM calisanlar";

		public static final String gunlukRandevular =
		"SELECT * FROM randevular AS r WHERE r.tarih_epoch >= ? AND r.tarih_epoch <= ?";

		public static final String muayeneKaydet =
		"INSERT INTO muayeneler(muayene_id, bulgular) VALUES(?, ?)";

		public static final String tahlilTalebiOlustur =
		"INSERT INTO tahliller(calisan_id, hasta_tam_ad, hasta_tc, degera, degerb, degerc) VALUES(?, ?, ?, ?, ?, ?)";

		public static final String siradakiTahlil =
		"SELECT * FROM tahliller AS t WHERE t.degera = -1 AND t.degerb = -1 AND t.degerc = -1 ORDER BY tahlil_id ASC LIMIT 1";

		public static final String tahlilSonucuGir =
		"UPDATE tahliller AS t SET degera = ?, degerb = ?, degerc = ? WHERE t.tahlil_id = ?";

		public static final String tahlilSonucuSorgula =
		"SELECT * FROM tahliller AS t WHERE t.tahlil_id = ?";

		public static final String tahlilleriListele =
		"SELECT * FROM tahliller";

		public static final String randevuTalebi = 
		"INSERT INTO randevular(hasta_tam_ad, hasta_tc, calisan_id, tarih_epoch) VALUES(?, ?, ?, ?)";

		public static final String talepUygunluk = 
		"SELECT tarih_epoch FROM randevular AS r WHERE r.tarih_epoch = ? AND r.calisan_id = ? LIMIT 1";
	}

	public static boolean asiRandevuTalebi(Asi a) {
		boolean result = false;
		try {
			PreparedStatement ps = connection.prepareStatement(Sorgular.asiRandevuTalebi);
			ps.setString(1, a.getHastaTamAd());
			ps.setInt(2, a.getHastaTc());
			ps.setBigDecimal(3, new BigDecimal(a.getTarih().getMs()));
			result = ps.executeUpdate() != 0;
			ps.close();
		} catch (SQLException e) {
			System.err.println("Hata.");
		}
		return result;
	}

	public static boolean asiKaydiOlustur(int id, int doz) {
		boolean result = false;
		try {
			PreparedStatement ps = connection.prepareStatement(Sorgular.asiKaydiOlustur);
			ps.setInt(1, doz);
			ps.setInt(2, id);
			result = ps.executeUpdate() != 0;
			ps.close();
		} catch (SQLException e) {
			System.err.println("Hata.");
		}
		return result;
	}

	public static Asi asiRandevuSorgulama(int id) {
		Asi a = null;
		try {
			PreparedStatement ps = connection.prepareStatement(Sorgular.asiRandevuSorgulama);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				a = new Asi(
					rs.getInt("asi_id"),
					rs.getString("hasta_tam_ad"),
					rs.getInt("hasta_tc"),
					new Tarih(rs.getBigDecimal("tarih_epoch").longValue()),
					rs.getBoolean("yapildi_mi"),
					rs.getInt("doz")
				);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.err.println("Hata.");
		}
		return a;
	}

	public static List<Asi> asilariListele() {
		List<Asi> list = new ArrayList<Asi>();
		try {
			PreparedStatement ps = connection.prepareStatement(Sorgular.asilariListele);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Asi(
					rs.getInt("asi_id"),
					rs.getString("hasta_tam_ad"),
					rs.getInt("hasta_tc"),
					new Tarih(rs.getBigDecimal("tarih_epoch").longValue()),
					rs.getBoolean("yapildi_mi"),
					rs.getInt("doz")
				));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.err.println("Hata.");
		}
		return list;
	}

	public static boolean calisanEkle(Calisan c) {
		boolean result = false;
		try {
			PreparedStatement ps = connection.prepareStatement(Sorgular.calisanEkle);
			ps.setString(1, c.getTamAd());
			ps.setString(2, c.getRol());
			ps.setInt(3, c.getMaas());
			result = ps.executeUpdate() != 0;
			ps.close();
		} catch (SQLException e) {
			System.err.println("Hata.");
		}
		return result;
	}

	public static List<Calisan> maasListele() {
		List<Calisan> list = new ArrayList<Calisan>();
		try {
			PreparedStatement ps = connection.prepareStatement(Sorgular.maasListele);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Calisan(
					rs.getInt("calisan_id"),
					rs.getString("tam_ad"),
					rs.getString("rol"),
					rs.getInt("maas")
				));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.err.println("Hata.");
		}
		return list;
	}

	public static List<Randevu> gunlukRandevular() {
		List<Randevu> list = new ArrayList<Randevu>();
		try {
			PreparedStatement ps = connection.prepareStatement(Sorgular.gunlukRandevular);
			long[] aralik = new Tarih().getAralik();
			ps.setBigDecimal(1, new BigDecimal(aralik[0]));
			ps.setBigDecimal(2, new BigDecimal(aralik[1]));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Randevu(
					rs.getInt("randevu_id"),
					rs.getString("hasta_tam_ad"),
					rs.getInt("hasta_tc"),
					rs.getInt("calisan_id"),
					new Tarih(rs.getBigDecimal("tarih_epoch").longValue())
				));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.err.println("Hata.");
		}
		return list;
	}

	public static boolean muayeneKaydet(Muayene m) {
		boolean result = false;
		try {
			PreparedStatement ps = connection.prepareStatement(Sorgular.muayeneKaydet);
			ps.setInt(1, m.getMuayeneId());
			ps.setString(2, m.getBulgular());
			result = ps.executeUpdate() != 0;
			ps.close();
		} catch (SQLException e) {
			System.err.println("Girilen muayene ID ile eslesen randevu yok.");
		}
		return result;
	}

	public static boolean tahlilTalebiOlustur(Tahlil t) {
		boolean result = false;
		try {
			PreparedStatement ps = connection.prepareStatement(Sorgular.tahlilTalebiOlustur);
			ps.setInt(1, t.getCalisanId());
			ps.setString(2, t.getHastaTamAd());
			ps.setInt(3, t.getHastaTc());
			ps.setInt(4, t.getDegerA());
			ps.setInt(5, t.getDegerB());
			ps.setInt(6, t.getDegerC());
			result = ps.executeUpdate() != 0;
			ps.close();
		} catch (SQLException e) {
			System.err.println("Girilen calisan ID ile eslesen calisan yok.");
		}
		return result;
	}

	public static Tahlil siradakiTahlil() {
		Tahlil t = null;
		try {
			PreparedStatement ps = connection.prepareStatement(Sorgular.siradakiTahlil);
			ResultSet rs = ps.executeQuery();
			while (rs.next() && t == null) {
				t = new Tahlil(
					rs.getInt("tahlil_id"),
					rs.getInt("calisan_id"),
					rs.getString("hasta_tam_ad"),
					rs.getInt("hasta_tc"),
					rs.getInt("degera"),
					rs.getInt("degerb"),
					rs.getInt("degerc")
				);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.err.println("Hata.");
		}
		return t;
	}

	public static boolean tahlilSonucuGir(int id, int degerA, int degerB, int degerC) {
		boolean result = false;
		try {
			PreparedStatement ps = connection.prepareStatement(Sorgular.tahlilSonucuGir);
			ps.setInt(1, degerA);
			ps.setInt(2, degerB);
			ps.setInt(3, degerC);
			ps.setInt(4, id);
			result = ps.executeUpdate() != 0;
			ps.close();
		} catch (SQLException e) {
			System.err.println("Hata.");
		}
		return result;
	}

	public static Tahlil tahlilSonucuSorgula(int id) {
		Tahlil t = null;
		try {
			PreparedStatement ps = connection.prepareStatement(Sorgular.tahlilSonucuSorgula);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				t = new Tahlil(
					rs.getInt("tahlil_id"),
					rs.getInt("calisan_id"),
					rs.getString("hasta_tam_ad"),
					rs.getInt("hasta_tc"),
					rs.getInt("degera"),
					rs.getInt("degerb"),
					rs.getInt("degerc")
				);
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.err.println("Hata.");
		}
		return t;
	}

	public static List<Tahlil> tahlilleriListele() {
		List<Tahlil> list = new ArrayList<Tahlil>();
		try {
			PreparedStatement ps = connection.prepareStatement(Sorgular.tahlilleriListele);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Tahlil(
					rs.getInt("tahlil_id"),
					rs.getInt("calisan_id"),
					rs.getString("hasta_tam_ad"),
					rs.getInt("hasta_tc"),
					rs.getInt("degera"),
					rs.getInt("degerb"),
					rs.getInt("degerc")
				));
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			System.err.println("Hata.");
		}
		return list;
	}

	public static boolean randevuTalebi(Randevu r) {
		boolean result = false;
		try {
			PreparedStatement ps = connection.prepareStatement(Sorgular.randevuTalebi);
			ps.setString(1, r.getHastaTamAd());
			ps.setInt(2, r.getHastaTc());
			ps.setInt(3, r.getCalisanId());
			ps.setBigDecimal(4, new BigDecimal(r.getTarih().getMs()));
			result = ps.executeUpdate() != 0;
			ps.close();
		} catch (SQLException e) {
			System.err.println("Girilen calisan ID ile eslesen calisan yok.");
		}
		return result;
	}

	public static boolean talepUygunluk(Randevu r) {
		boolean result = true;
		try {
			PreparedStatement ps = connection.prepareStatement(Sorgular.talepUygunluk);
			ps.setBigDecimal(1, new BigDecimal(r.getTarih().getMs()));
			ps.setInt(2, r.getCalisanId());
			ResultSet rs = ps.executeQuery();
			while (rs.next() && result) {
				result = false;
			}
			ps.close();
			rs.close();
		} catch (SQLException e) {
			System.err.println("Hata.");
			result = false;
		}
		return result;
	}
}
