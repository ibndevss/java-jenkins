package myor.matrix.master.entity;

public class SearchBrowseDto {
    private String kode;
    private String keterangan;

    // generate constructor from super class 
    public SearchBrowseDto() {
        super();
    }

    public SearchBrowseDto(String kode, String keterangan) {
        this.kode = kode;
        this.keterangan = keterangan;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }
}
