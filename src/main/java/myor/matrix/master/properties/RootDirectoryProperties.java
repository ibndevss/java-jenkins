package myor.matrix.master.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class RootDirectoryProperties {
	private String filePathPdf = "files/pdf";
	private String filePathImg = "files/img";
	private String filePathImgSpg = "files/img/spg";
	private String filePathTxt = "files/txt";
	private String filePathCsv = "files/csv";
	private String filePathExcel = "files/excel";

	public String getFilePathPdf() {
		return filePathPdf;
	}
	public void setFilePathPdf(String filePathPdf) {
		this.filePathPdf = filePathPdf;
	}
	public String getFilePathImg() {
		return filePathImg;
	}
	public void setFilePathImg(String filePathImg) {
		this.filePathImg = filePathImg;
	}
	public String getFilePathImgSpg() {
		return filePathImgSpg;
	}
	public void setFilePathImgSpg(String filePathImgSpg) {
		this.filePathImgSpg = filePathImgSpg;
	}
	public String getFilePathTxt() {
		return filePathTxt;
	}
	public void setFilePathTxt(String filePathTxt) {
		this.filePathTxt = filePathTxt;
	}
	public String getFilePathCsv() {
		return filePathCsv;
	}
	public void setFilePathCsv(String filePathCsv) {
		this.filePathCsv = filePathCsv;
	}
	public String getFilePathExcel() {
		return filePathExcel;
	}
	public void setFilePathExcel(String filePathExcel) {
		this.filePathExcel = filePathExcel;
	}


}
