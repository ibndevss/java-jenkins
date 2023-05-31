package myor.matrix.master.entity;

public class DiscDto {
	private String kodeAp;
	private String tipe;
	private String flagAktif;
	private String keterangan;
	private String tglAwal;
	private String tglAkhir;
	private String tglToleransiKlaim;
	private String klaim;
	private String kodeParent;
	private String flagExclude;
	private String exclude;
	private String cetakFaktur;
	private String kontribusi;
	private String stockKosong;
	private String berlakuBeli;
	private String satuanBeli;
	private String berlakuKelipatan;
	private String minBeli;
	private String kategoriKelipatan;
	private String tipeNilaiKelipatan;
	private String nilaiDiscKelipatan;
	private String flagMakDisk;
	private String nilaiMakDisk;
	private String flagOutlet;
	private String flagGroupOutlet;
	private String flagGroupHarga;
	private String flagChannel;
	private String flagTeam;
	private String flagSalesforce;
	private String flagGrupDisk;
	private String flagSpesifikasi;
	private String tipeRange;
	private String nilaiDiskRange;
	private String flagDiskBertingkat;
	private String flagSyaratProduk;
	private double makSyaratProduk;
	private String flagKumulatif;
	private String flagSyaratKelProd;
	private double syaratMakKel;
	private String syaratPilProd;
	private String flagKuota;
	private String kuotaBaseOn;
	private String satuanKuota;
	private String berlakuKuota;
	private String flagHistKuota;
	private String tglAwalHist;
	private String tglAkhirHist;
	private String pilHitungHist;
	private double jmlPengaliHist;
	private String flagProsesHist;
	private String flagBatasanPerOutlet;
	private double batasanPerOutlet;
	private String flagBatasanPerAp;
	private double batasanPerAp;
	private String flagGrowth;
	private double nilaiGrowth;
	private String flagEditPromo;
	private String flagHierarki;
	private String berlakuHierarki;
	private String hitungRange;
	private String hitungDiskon;
	private String description;
	public DiscDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DiscDto(String kodeAp, String tipe, String flagAktif, String keterangan, String tglAwal,
			String tglAkhir, String tglToleransiKlaim, String klaim, String kodeParent, String flagExclude,
			String exclude, String cetakFaktur, String kontribusi, String stockKosong, String berlakuBeli,
			String satuanBeli, String berlakuKelipatan, String minBeli, String kategoriKelipatan,
			String tipeNilaiKelipatan, String nilaiDiscKelipatan, String flagMakDisk, String nilaiMakDisk,
			String flagOutlet, String flagGroupOutlet, String flagGroupHarga, String flagChannel, String flagTeam,
			String flagSalesforce, String flagGrupDisk, String flagSpesifikasi, String tipeRange, String nilaiDiskRange,
			String flagDiskBertingkat, String flagSyaratProduk, double makSyaratProduk, String flagKumulatif,
			String flagSyaratKelProd, double syaratMakKel, String syaratPilProd, String flagKuota, String kuotaBaseOn,
			String satuanKuota, String berlakuKuota, String flagHistKuota, String tglAwalHist, String tglAkhirHist,
			String pilHitungHist, double jmlPengaliHist, String flagProsesHist, String flagBatasanPerOutlet,
			double batasanPerOutlet, String flagBatasanPerAp, double batasanPerAp, String flagGrowth,
			double nilaiGrowth, String flagEditPromo, String flagHierarki, String berlakuHierarki, String hitungRange,
			String hitungDiskon, String description) {
		super();
		this.kodeAp = kodeAp;
		this.tipe = tipe;
		this.flagAktif = flagAktif;
		this.keterangan = keterangan;
		this.tglAwal = tglAwal;
		this.tglAkhir = tglAkhir;
		this.tglToleransiKlaim = tglToleransiKlaim;
		this.klaim = klaim;
		this.kodeParent = kodeParent;
		this.flagExclude = flagExclude;
		this.exclude = exclude;
		this.cetakFaktur = cetakFaktur;
		this.kontribusi = kontribusi;
		this.stockKosong = stockKosong;
		this.berlakuBeli = berlakuBeli;
		this.satuanBeli = satuanBeli;
		this.berlakuKelipatan = berlakuKelipatan;
		this.minBeli = minBeli;
		this.kategoriKelipatan = kategoriKelipatan;
		this.tipeNilaiKelipatan = tipeNilaiKelipatan;
		this.nilaiDiscKelipatan = nilaiDiscKelipatan;
		this.flagMakDisk = flagMakDisk;
		this.nilaiMakDisk = nilaiMakDisk;
		this.flagOutlet = flagOutlet;
		this.flagGroupOutlet = flagGroupOutlet;
		this.flagGroupHarga = flagGroupHarga;
		this.flagChannel = flagChannel;
		this.flagTeam = flagTeam;
		this.flagSalesforce = flagSalesforce;
		this.flagGrupDisk = flagGrupDisk;
		this.flagSpesifikasi = flagSpesifikasi;
		this.tipeRange = tipeRange;
		this.nilaiDiskRange = nilaiDiskRange;
		this.flagDiskBertingkat = flagDiskBertingkat;
		this.flagSyaratProduk = flagSyaratProduk;
		this.makSyaratProduk = makSyaratProduk;
		this.flagKumulatif = flagKumulatif;
		this.flagSyaratKelProd = flagSyaratKelProd;
		this.syaratMakKel = syaratMakKel;
		this.syaratPilProd = syaratPilProd;
		this.flagKuota = flagKuota;
		this.kuotaBaseOn = kuotaBaseOn;
		this.satuanKuota = satuanKuota;
		this.berlakuKuota = berlakuKuota;
		this.flagHistKuota = flagHistKuota;
		this.tglAwalHist = tglAwalHist;
		this.tglAkhirHist = tglAkhirHist;
		this.pilHitungHist = pilHitungHist;
		this.jmlPengaliHist = jmlPengaliHist;
		this.flagProsesHist = flagProsesHist;
		this.flagBatasanPerOutlet = flagBatasanPerOutlet;
		this.batasanPerOutlet = batasanPerOutlet;
		this.flagBatasanPerAp = flagBatasanPerAp;
		this.batasanPerAp = batasanPerAp;
		this.flagGrowth = flagGrowth;
		this.nilaiGrowth = nilaiGrowth;
		this.flagEditPromo = flagEditPromo;
		this.flagHierarki = flagHierarki;
		this.berlakuHierarki = berlakuHierarki;
		this.hitungRange = hitungRange;
		this.hitungDiskon = hitungDiskon;
		this.description = description;
	}
	public String getKodeAp() {
		return kodeAp;
	}
	public void setKodeAp(String kodeAp) {
		this.kodeAp = kodeAp;
	}
	public String getTipe() {
		return tipe;
	}
	public void setTipe(String tipe) {
		this.tipe = tipe;
	}
	public String getFlagAktif() {
		return flagAktif;
	}
	public void setFlagAktif(String flagAktif) {
		this.flagAktif = flagAktif;
	}
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
	public String getTglAwal() {
		return tglAwal;
	}
	public void setTglAwal(String tglAwal) {
		this.tglAwal = tglAwal;
	}
	public String getTglAkhir() {
		return tglAkhir;
	}
	public void setTglAkhir(String tglAkhir) {
		this.tglAkhir = tglAkhir;
	}
	public String getTglToleransiKlaim() {
		return tglToleransiKlaim;
	}
	public void setTglToleransiKlaim(String tglToleransiKlaim) {
		this.tglToleransiKlaim = tglToleransiKlaim;
	}
	public String getKlaim() {
		return klaim;
	}
	public void setKlaim(String klaim) {
		this.klaim = klaim;
	}
	public String getKodeParent() {
		return kodeParent;
	}
	public void setKodeParent(String kodeParent) {
		this.kodeParent = kodeParent;
	}
	public String getFlagExclude() {
		return flagExclude;
	}
	public void setFlagExclude(String flagExclude) {
		this.flagExclude = flagExclude;
	}
	public String getExclude() {
		return exclude;
	}
	public void setExclude(String exclude) {
		this.exclude = exclude;
	}
	public String getCetakFaktur() {
		return cetakFaktur;
	}
	public void setCetakFaktur(String cetakFaktur) {
		this.cetakFaktur = cetakFaktur;
	}
	public String getKontribusi() {
		return kontribusi;
	}
	public void setKontribusi(String kontribusi) {
		this.kontribusi = kontribusi;
	}
	public String getStockKosong() {
		return stockKosong;
	}
	public void setFlagPro(String flagPro) {
		this.stockKosong = flagPro;
	}
	public String getBerlakuBeli() {
		return berlakuBeli;
	}
	public void setBerlakuBeli(String berlakuBeli) {
		this.berlakuBeli = berlakuBeli;
	}
	public String getSatuanBeli() {
		return satuanBeli;
	}
	public void setSatuanBeli(String satuanBeli) {
		this.satuanBeli = satuanBeli;
	}
	public String getBerlakuKelipatan() {
		return berlakuKelipatan;
	}
	public void setBerlakuKelipatan(String berlakuKelipatan) {
		this.berlakuKelipatan = berlakuKelipatan;
	}
	public String getMinBeli() {
		return minBeli;
	}
	public void setMinBeli(String minBeli) {
		this.minBeli = minBeli;
	}
	public String getKategoriKelipatan() {
		return kategoriKelipatan;
	}
	public void setKategoriKelipatan(String kategoriKelipatan) {
		this.kategoriKelipatan = kategoriKelipatan;
	}
	public String getTipeNilaiKelipatan() {
		return tipeNilaiKelipatan;
	}
	public void setTipeNilaiKelipatan(String tipeNilaiKelipatan) {
		this.tipeNilaiKelipatan = tipeNilaiKelipatan;
	}
	public String getNilaiDiscKelipatan() {
		return nilaiDiscKelipatan;
	}
	public void setNilaiDiscKelipatan(String nilaiDiscKelipatan) {
		this.nilaiDiscKelipatan = nilaiDiscKelipatan;
	}
	public String getFlagMakDisk() {
		return flagMakDisk;
	}
	public void setFlagMakDisk(String flagMakDisk) {
		this.flagMakDisk = flagMakDisk;
	}
	public String getNilaiMakDisk() {
		return nilaiMakDisk;
	}
	public void setNilaiMakDisk(String nilaiMakDisk) {
		this.nilaiMakDisk = nilaiMakDisk;
	}
	public String getFlagOutlet() {
		return flagOutlet;
	}
	public void setFlagOutlet(String flagOutlet) {
		this.flagOutlet = flagOutlet;
	}
	public String getFlagGroupOutlet() {
		return flagGroupOutlet;
	}
	public void setFlagGroupOutlet(String flagGroupOutlet) {
		this.flagGroupOutlet = flagGroupOutlet;
	}
	public String getFlagGroupHarga() {
		return flagGroupHarga;
	}
	public void setFlagGroupHarga(String flagGroupHarga) {
		this.flagGroupHarga = flagGroupHarga;
	}
	public String getFlagChannel() {
		return flagChannel;
	}
	public void setFlagChannel(String flagChannel) {
		this.flagChannel = flagChannel;
	}
	public String getFlagTeam() {
		return flagTeam;
	}
	public void setFlagTeam(String flagTeam) {
		this.flagTeam = flagTeam;
	}
	public String getFlagSalesforce() {
		return flagSalesforce;
	}
	public void setFlagSalesforce(String flagSalesforce) {
		this.flagSalesforce = flagSalesforce;
	}
	public String getFlagGrupDisk() {
		return flagGrupDisk;
	}
	public void setFlagGrupDisk(String flagGrupDisk) {
		this.flagGrupDisk = flagGrupDisk;
	}
	public String getFlagSpesifikasi() {
		return flagSpesifikasi;
	}
	public void setFlagSpesifikasi(String flagSpesifikasi) {
		this.flagSpesifikasi = flagSpesifikasi;
	}
	public String getTipeRange() {
		return tipeRange;
	}
	public void setTipeRange(String tipeRange) {
		this.tipeRange = tipeRange;
	}
	public String getNilaiDiskRange() {
		return nilaiDiskRange;
	}
	public void setNilaiDiskRange(String nilaiDiskRange) {
		this.nilaiDiskRange = nilaiDiskRange;
	}
	public String getFlagDiskBertingkat() {
		return flagDiskBertingkat;
	}
	public void setFlagDiskBertingkat(String flagDiskBertingkat) {
		this.flagDiskBertingkat = flagDiskBertingkat;
	}
	public String getFlagSyaratProduk() {
		return flagSyaratProduk;
	}
	public void setFlagSyaratProduk(String flagSyaratProduk) {
		this.flagSyaratProduk = flagSyaratProduk;
	}
	public double getMakSyaratProduk() {
		return makSyaratProduk;
	}
	public void setMakSyaratProduk(double makSyaratProduk) {
		this.makSyaratProduk = makSyaratProduk;
	}
	public String getFlagKumulatif() {
		return flagKumulatif;
	}
	public void setFlagKumulatif(String flagKumulatif) {
		this.flagKumulatif = flagKumulatif;
	}
	public String getFlagSyaratKelProd() {
		return flagSyaratKelProd;
	}
	public void setFlagSyaratKelProd(String flagSyaratKelProd) {
		this.flagSyaratKelProd = flagSyaratKelProd;
	}
	public double getSyaratMakKel() {
		return syaratMakKel;
	}
	public void setSyaratMakKel(double syaratMakKel) {
		this.syaratMakKel = syaratMakKel;
	}
	public String getSyaratPilProd() {
		return syaratPilProd;
	}
	public void setSyaratPilProd(String syaratPilProd) {
		this.syaratPilProd = syaratPilProd;
	}
	public String getFlagKuota() {
		return flagKuota;
	}
	public void setFlagKuota(String flagKuota) {
		this.flagKuota = flagKuota;
	}
	public String getKuotaBaseOn() {
		return kuotaBaseOn;
	}
	public void setKuotaBaseOn(String kuotaBaseOn) {
		this.kuotaBaseOn = kuotaBaseOn;
	}
	public String getSatuanKuota() {
		return satuanKuota;
	}
	public void setSatuanKuota(String satuanKuota) {
		this.satuanKuota = satuanKuota;
	}
	public String getBerlakuKuota() {
		return berlakuKuota;
	}
	public void setBerlakuKuota(String berlakuKuota) {
		this.berlakuKuota = berlakuKuota;
	}
	public String getFlagHistKuota() {
		return flagHistKuota;
	}
	public void setFlagHistKuota(String flagHistKuota) {
		this.flagHistKuota = flagHistKuota;
	}
	public String getTglAwalHist() {
		return tglAwalHist;
	}
	public void setTglAwalHist(String tglAwalHist) {
		this.tglAwalHist = tglAwalHist;
	}
	public String getTglAkhirHist() {
		return tglAkhirHist;
	}
	public void setTglAkhirHist(String tglAkhirHist) {
		this.tglAkhirHist = tglAkhirHist;
	}
	public String getPilHitungHist() {
		return pilHitungHist;
	}
	public void setPilHitungHist(String pilHitungHist) {
		this.pilHitungHist = pilHitungHist;
	}
	public double getJmlPengaliHist() {
		return jmlPengaliHist;
	}
	public void setJmlPengaliHist(double jmlPengaliHist) {
		this.jmlPengaliHist = jmlPengaliHist;
	}
	public String getFlagProsesHist() {
		return flagProsesHist;
	}
	public void setFlagProsesHist(String flagProsesHist) {
		this.flagProsesHist = flagProsesHist;
	}
	public String getFlagBatasanPerOutlet() {
		return flagBatasanPerOutlet;
	}
	public void setFlagBatasanPerOutlet(String flagBatasanPerOutlet) {
		this.flagBatasanPerOutlet = flagBatasanPerOutlet;
	}
	public double getBatasanPerOutlet() {
		return batasanPerOutlet;
	}
	public void setBatasanPerOutlet(double batasanPerOutlet) {
		this.batasanPerOutlet = batasanPerOutlet;
	}
	public String getFlagBatasanPerAp() {
		return flagBatasanPerAp;
	}
	public void setFlagBatasanPerAp(String flagBatasanPerAp) {
		this.flagBatasanPerAp = flagBatasanPerAp;
	}
	public double getBatasanPerAp() {
		return batasanPerAp;
	}
	public void setBatasanPerAp(double batasanPerAp) {
		this.batasanPerAp = batasanPerAp;
	}
	public String getFlagGrowth() {
		return flagGrowth;
	}
	public void setFlagGrowth(String flagGrowth) {
		this.flagGrowth = flagGrowth;
	}
	public double getNilaiGrowth() {
		return nilaiGrowth;
	}
	public void setNilaiGrowth(double nilaiGrowth) {
		this.nilaiGrowth = nilaiGrowth;
	}
	public String getFlagEditPromo() {
		return flagEditPromo;
	}
	public void setFlagEditPromo(String flagEditPromo) {
		this.flagEditPromo = flagEditPromo;
	}
	public String getFlagHierarki() {
		return flagHierarki;
	}
	public void setFlagHierarki(String flagHierarki) {
		this.flagHierarki = flagHierarki;
	}
	public String getBerlakuHierarki() {
		return berlakuHierarki;
	}
	public void setBerlakuHierarki(String berlakuHierarki) {
		this.berlakuHierarki = berlakuHierarki;
	}
	public String getHitungRange() {
		return hitungRange;
	}
	public void setHitungRange(String hitungRange) {
		this.hitungRange = hitungRange;
	}
	public String getHitungDiskon() {
		return hitungDiskon;
	}
	public void setHitungDiskon(String hitungDiskon) {
		this.hitungDiskon = hitungDiskon;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
