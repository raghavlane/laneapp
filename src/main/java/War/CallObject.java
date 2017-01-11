package War;

import java.util.ArrayList;
import java.util.List;

public class CallObject {

	private List<String> mlist = new ArrayList<String>();
	private List<String> olist = new ArrayList<String>();
	private List<String> ilist = new ArrayList<String>();

	public List<String> getMlist() {
		return mlist;
	}

	public void setMlist(List<String> mlist) {
		this.mlist = mlist;
	}

	public List<String> getOlist() {
		return olist;
	}

	public void setOlist(List<String> olist) {
		this.olist = olist;
	}

	public List<String> getIlist() {
		return ilist;
	}

	public void setIlist(List<String> ilist) {
		this.ilist = ilist;
	}

	@Override
	public String toString() {
		return "CallObject [mlist=" + mlist + ", olist=" + olist + ", ilist="
				+ ilist + "]";
	}
}
