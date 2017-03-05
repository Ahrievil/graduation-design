package util;

import java.util.ArrayList;
import java.util.List;


public class PageUtil {

	private int totalPage;
	private int count;
	private List<Object> list = new ArrayList<Object>();
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public List<Object> getList() {
		return list;
	}
	public void setList(List<Object> list) {
		this.list = list;
	}
	public PageUtil queryByPage(int page,BaseDao bd){
		PageUtil pu = new PageUtil();
		int curPage = 5;
		int beginIndex = (page-1)*curPage+1;
		int endIndex = page*curPage;
		List<Object> list = bd.queryAllByPage(beginIndex, endIndex);
		int cou = bd.queryCount();
		int totalPage = cou / 5;
		if(cou % 5 != 0){
			totalPage += 1;
		}
		pu.setCount(cou);
		pu.setTotalPage(totalPage);
		pu.setList(list);
		return pu;
	}
	@Override
	public String toString() {
		return "PageUtil [totalPage=" + totalPage + ", count=" + count
				+ ", list=" + list + ", getTotalPage()=" + getTotalPage()
				+ ", getCount()=" + getCount() + ", getList()=" + getList()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
}
