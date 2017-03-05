package po;
/**
 * 功能菜单项的po
 * @author Ahri6
 *
 */
public class OaFunctions {

	private int fid;
	private String fname;
	private String fhref;
	private int pid;//父级的对应的fuid
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFhref() {
		return fhref;
	}
	public void setFhref(String fhref) {
		this.fhref = fhref;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	@Override
	public String toString() {
		return "OaFunctions [fid=" + fid + ", fname=" + fname + ", fhref="
				+ fhref + ", pid=" + pid + "]";
	}
	
	
}
