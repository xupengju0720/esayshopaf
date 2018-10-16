package util;

public class SearchInfo {
	Integer count=0; 
	Integer max=10;
	Integer pmax=0;
	Integer pageno = 1;
	String where="";
	String limit=" limit " + (pageno - 1) * max + "," + max;
	public   SearchInfo() {
		
	} 
	public  SearchInfo(String where,boolean canpage) {
		this.where = where;
		setCanPage(canpage);
	}
    public void pagemax() {
	this.pmax =(count-1)/max+1;
       }
	public Integer getPageno() {
		return pageno;
	}
	public Integer getNext() {
		return pageno<pmax?pageno+1:pmax;
	}
	public Integer getPmax() {
		return pmax;
	}
	public Integer getPrev() {
		return pageno>1?pageno-1:1;
	}
	public void setPageno(Integer pageno) {
		if (pageno == null || pageno < 1)
			pageno = 1;
		this.pageno = pageno;
		limit = " limit " + (pageno - 1) * max + "," + max;
	}
	
	public void setCanPage(boolean iscan) {
		if(!iscan) limit="";
	}

	
	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

}
