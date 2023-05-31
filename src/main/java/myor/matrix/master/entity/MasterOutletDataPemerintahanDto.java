package myor.matrix.master.entity;

public class MasterOutletDataPemerintahanDto {

		private String groupdivisi;
		private String custno;
		private String kdprop;
		private String prop;
		private String kdkab;
		private String kab;
		private String kdkec;
		private String kec;
		private String kdkel;
		private String kel;
		public MasterOutletDataPemerintahanDto() {
			super();
			// TODO Auto-generated constructor stub
		}
		public MasterOutletDataPemerintahanDto(String groupdivisi, String custno, String kdprop, String prop,
				String kdkab, String kab, String kdkec, String kec, String kdkel, String kel) {
			super();
			this.groupdivisi = groupdivisi;
			this.custno = custno;
			this.kdprop = kdprop;
			this.prop = prop;
			this.kdkab = kdkab;
			this.kab = kab;
			this.kdkec = kdkec;
			this.kec = kec;
			this.kdkel = kdkel;
			this.kel = kel;
		}
		public String getGroupdivisi() {
			return groupdivisi;
		}
		public void setGroupdivisi(String groupdivisi) {
			this.groupdivisi = groupdivisi;
		}
		public String getCustno() {
			return custno;
		}
		public void setCustno(String custno) {
			this.custno = custno;
		}
		public String getKdprop() {
			return kdprop;
		}
		public void setKdprop(String kdprop) {
			this.kdprop = kdprop;
		}
		public String getProp() {
			return prop;
		}
		public void setProp(String prop) {
			this.prop = prop;
		}
		public String getKdkab() {
			return kdkab;
		}
		public void setKdkab(String kdkab) {
			this.kdkab = kdkab;
		}
		public String getKab() {
			return kab;
		}
		public void setKab(String kab) {
			this.kab = kab;
		}
		public String getKdkec() {
			return kdkec;
		}
		public void setKdkec(String kdkec) {
			this.kdkec = kdkec;
		}
		public String getKec() {
			return kec;
		}
		public void setKec(String kec) {
			this.kec = kec;
		}
		public String getKdkel() {
			return kdkel;
		}
		public void setKdkel(String kdkel) {
			this.kdkel = kdkel;
		}
		public String getKel() {
			return kel;
		}
		public void setKel(String kel) {
			this.kel = kel;
		}
}
