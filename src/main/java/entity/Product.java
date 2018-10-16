package entity;

public class Product {
public static String statuss[]= {"Œ¥…œº‹ ","“—…œº‹"};
int id;
String  fullname;
int type_id;
String activity;
String tip;
String sale;
String info;
Double price;
Double nowprice;
int salecount;
int collectcount;
int priority;
int status;
String pics;
String comments;
String t_name;
public String[] getPiclist() {
	if(pics.length()<5)return new String[0];
	return pics.split(",");
}
public String getPic() {
	if(pics.length()<5)return null;
	String [] pic=pics.split(",");
	
	if(pic.length>0)
	return pic[0];
	else return null;
}


public String getStatus_Name() {
	return statuss[status];
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getFullname() {
	return fullname;
}
public void setFullname(String fullname) {
	this.fullname = fullname;
}
public int getType_id() {
	return type_id;
}
public void setType_id(int type_id) {
	this.type_id = type_id;
}
public String getActivity() {
	return activity;
}
public void setActivity(String activity) {
	this.activity = activity;
}
public String getTip() {
	return tip;
}
public void setTip(String tip) {
	this.tip = tip;
}
public String getSale() {
	return sale;
}
public void setSale(String sale) {
	this.sale = sale;
}
public String getInfo() {
	return info;
}
public void setInfo(String info) {
	this.info = info.replace('\"', '\'');
}
public Double getPrice() {
	return price;
}
public void setPrice(Double price) {
	this.price = price;
}
public Double getNowprice() {
	return nowprice;
}
public void setNowprice(Double nowprice) {
	this.nowprice = nowprice;
}

public int getCollectcount() {
	return collectcount;
}
public void setCollectcount(int collectcount) {
	this.collectcount = collectcount;
}
public int getPriority() {
	return priority;
}
public void setPriority(int priority) {
	this.priority = priority;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public String getPics() {
	return pics;
}
public void setPics(String pics) {
	this.pics = pics;
}
public String getComments() {
	return comments;
}
public void setComments(String comments) {
	this.comments = comments;
}
public int getSalecount() {
	return salecount;
}
public void setSalecount(int salecount) {
	this.salecount = salecount;
}
public String getT_name() {
	return t_name;
}
public void setT_name(String t_name) {
	this.t_name = t_name;
}

}
