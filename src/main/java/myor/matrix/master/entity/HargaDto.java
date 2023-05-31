package myor.matrix.master.entity;

public class HargaDto {

	private Double hargaBesar;
	private Double hargaTengah;
	private Double hargaKecil;

	public HargaDto() {
		// TODO Auto-generated constructor stub
	}

	public HargaDto(Double hargaBesar, Double hargaTengah, Double hargaKecil) {
		super();
		this.hargaBesar = hargaBesar;
		this.hargaTengah = hargaTengah;
		this.hargaKecil = hargaKecil;
	}

	public Double getHargaBesar() {
		return hargaBesar;
	}

	public void setHargaBesar(Double hargaBesar) {
		this.hargaBesar = hargaBesar;
	}

	public Double getHargaTengah() {
		return hargaTengah;
	}

	public void setHargaTengah(Double hargaTengah) {
		this.hargaTengah = hargaTengah;
	}

	public Double getHargaKecil() {
		return hargaKecil;
	}

	public void setHargaKecil(Double hargaKecil) {
		this.hargaKecil = hargaKecil;
	}

}
