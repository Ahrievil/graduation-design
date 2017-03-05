package po;

public class OaFile {

	private int flid;
	private String flName;
	private int flSize;
	private String flType;
	private String prName;
	private String flupdate;
	private String fluper;
	public int getFlid() {
		return flid;
	}
	public void setFlid(int flid) {
		this.flid = flid;
	}
	public String getFluper() {
		return fluper;
	}
	public void setFluper(String fluper) {
		this.fluper = fluper;
	}
	public String getFlName() {
		return flName;
	}
	public void setFlName(String flName) {
		this.flName = flName;
	}
	public int getFlSize() {
		return flSize;
	}
	public void setFlSize(int flSize) {
		this.flSize = flSize;
	}
	public String getFlType() {
		return flType;
	}
	public void setFlType(String flType) {
		this.flType = flType;
	}
	public String getPrName() {
		return prName;
	}
	public void setPrName(String prName) {
		this.prName = prName;
	}
	public String getFlupdate() {
		return flupdate;
	}
	public void setFlupdate(String flupdate) {
		this.flupdate = flupdate;
	}
	@Override
	public String toString() {
		return "OaFile [flid=" + flid + ", flName=" + flName + ", flSize="
				+ flSize + ", flType=" + flType + ", prName=" + prName
				+ ", flupdate=" + flupdate + ", fluper=" + fluper + "]";
	}
	
}
