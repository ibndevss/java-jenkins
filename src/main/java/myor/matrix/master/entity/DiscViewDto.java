package myor.matrix.master.entity;

import java.util.List;

public class DiscViewDto {
	DiscDto data;
	List<SelectItem<String>> products;
	List<DiscOutletDto> outlets;
	List<SelectItem<String>> groupOutlet;
	List<SelectItem<String>> channels;
	List<SelectItem<String>> salesforces;
	List<SelectItem<String>> outletSpesifik;
	List<SelectItem<String>> groupDisk;
	List<SelectItem<String>> groupHarga;
	List<SelectItem<String>> teams;
	List<DiscRangeDto> ranges;
	List<DiscSyaratGProdDto> syaratGroupProd;
	List<DiscSyaratKuota> syaratKuota;
	//Disc Promo
	List<DiscPromoDto> promo;
	//Disc Promo
	private String berlakuHierarki;
	/*atribut tab outlet*/
	private String edtTabOutlet;
	private String edtTabSubdist;
	/*atribut tab outlet*/
	private String edtTabGroupOutlet; // atribut group outlet
	private String edtTabGroupHarga; // atribut group harga
	private String edtTabChannel; //atribut channel
	private String edtTabTeam; //atribut team
	private String edtTabSlsForce;
	private String edtTabGroupDisc;
	private String edtTabSpecificOutlet;
	public DiscViewDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DiscViewDto(DiscDto data, List<SelectItem<String>> products, List<DiscOutletDto> outlets,
			List<SelectItem<String>> groupOutlet, List<SelectItem<String>> channels,
			List<SelectItem<String>> salesforces, List<SelectItem<String>> outletSpesifik,
			List<SelectItem<String>> groupDisk, List<SelectItem<String>> groupHarga, List<SelectItem<String>> teams,
			List<DiscRangeDto> ranges, List<DiscSyaratGProdDto> syaratGroupProd,
			List<DiscSyaratKuota> syaratKuota) {
		super();
		this.data = data;
		this.products = products;
		this.outlets = outlets;
		this.groupOutlet = groupOutlet;
		this.channels = channels;
		this.salesforces = salesforces;
		this.outletSpesifik = outletSpesifik;
		this.groupDisk = groupDisk;
		this.groupHarga = groupHarga;
		this.teams = teams;
		this.ranges = ranges;
		this.syaratGroupProd = syaratGroupProd;
		this.syaratKuota = syaratKuota;
	}
	public DiscDto getData() {
		return data;
	}
	public void setData(DiscDto data) {
		this.data = data;
	}
	public List<SelectItem<String>> getProducts() {
		return products;
	}
	public void setProducts(List<SelectItem<String>> products) {
		this.products = products;
	}
	public List<DiscOutletDto> getOutlets() {
		return outlets;
	}
	public void setOutlets(List<DiscOutletDto> outlets) {
		this.outlets = outlets;
	}
	public List<SelectItem<String>> getGroupOutlet() {
		return groupOutlet;
	}
	public void setGroupOutlet(List<SelectItem<String>> groupOutlet) {
		this.groupOutlet = groupOutlet;
	}
	public List<SelectItem<String>> getChannels() {
		return channels;
	}
	public void setChannels(List<SelectItem<String>> channels) {
		this.channels = channels;
	}
	public List<SelectItem<String>> getSalesforces() {
		return salesforces;
	}
	public void setSalesforces(List<SelectItem<String>> salesforces) {
		this.salesforces = salesforces;
	}
	public List<SelectItem<String>> getOutletSpesifik() {
		return outletSpesifik;
	}
	public void setOutletSpesifik(List<SelectItem<String>> outletSpesifik) {
		this.outletSpesifik = outletSpesifik;
	}
	public List<SelectItem<String>> getGroupDisk() {
		return groupDisk;
	}
	public void setGroupDisk(List<SelectItem<String>> groupDisk) {
		this.groupDisk = groupDisk;
	}
	public List<SelectItem<String>> getGroupHarga() {
		return groupHarga;
	}
	public void setGroupHarga(List<SelectItem<String>> groupHarga) {
		this.groupHarga = groupHarga;
	}
	public List<SelectItem<String>> getTeams() {
		return teams;
	}
	public void setTeams(List<SelectItem<String>> teams) {
		this.teams = teams;
	}
	public List<DiscRangeDto> getRanges() {
		return ranges;
	}
	public void setRanges(List<DiscRangeDto> ranges) {
		this.ranges = ranges;
	}
	public List<DiscSyaratGProdDto> getSyaratGroupProd() {
		return syaratGroupProd;
	}
	public void setSyaratGroupProd(List<DiscSyaratGProdDto> syaratGroupProd) {
		this.syaratGroupProd = syaratGroupProd;
	}
	public List<DiscSyaratKuota> getSyaratKuota() {
		return syaratKuota;
	}
	public void setSyaratKuota(List<DiscSyaratKuota> syaratKuota) {
		this.syaratKuota = syaratKuota;
	}
	public String getBerlakuHierarki() {
		return berlakuHierarki;
	}
	public void setBerlakuHierarki(String berlakuHierarki) {
		this.berlakuHierarki = berlakuHierarki;
	}
	public String getEdtTabOutlet() {
		return edtTabOutlet;
	}
	public void setEdtTabOutlet(String edtTabOutlet) {
		this.edtTabOutlet = edtTabOutlet;
	}
	public String getEdtTabSubdist() {
		return edtTabSubdist;
	}
	public void setEdtTabSubdist(String edtTabSubdist) {
		this.edtTabSubdist = edtTabSubdist;
	}
	public String getEdtTabGroupOutlet() {
		return edtTabGroupOutlet;
	}
	public void setEdtTabGroupOutlet(String edtTabGroupOutlet) {
		this.edtTabGroupOutlet = edtTabGroupOutlet;
	}
	public String getEdtTabGroupHarga() {
		return edtTabGroupHarga;
	}
	public void setEdtTabGroupHarga(String edtTabGroupHarga) {
		this.edtTabGroupHarga = edtTabGroupHarga;
	}
	public String getEdtTabChannel() {
		return edtTabChannel;
	}
	public void setEdtTabChannel(String edtTabChannel) {
		this.edtTabChannel = edtTabChannel;
	}
	public String getEdtTabTeam() {
		return edtTabTeam;
	}
	public void setEdtTabTeam(String edtTabTeam) {
		this.edtTabTeam = edtTabTeam;
	}
	public String getEdtTabSlsForce() {
		return edtTabSlsForce;
	}
	public void setEdtTabSlsForce(String edtTabSlsForce) {
		this.edtTabSlsForce = edtTabSlsForce;
	}
	public String getEdtTabGroupDisc() {
		return edtTabGroupDisc;
	}
	public void setEdtTabGroupDisc(String edtTabGroupDisc) {
		this.edtTabGroupDisc = edtTabGroupDisc;
	}
	public String getEdtTabSpecificOutlet() {
		return edtTabSpecificOutlet;
	}
	public void setEdtTabSpecificOutlet(String edtTabSpecificOutlet) {
		this.edtTabSpecificOutlet = edtTabSpecificOutlet;
	}
	public List<DiscPromoDto> getPromo() {
		return promo;
	}
	public void setPromo(List<DiscPromoDto> promo) {
		this.promo = promo;
	}
}
