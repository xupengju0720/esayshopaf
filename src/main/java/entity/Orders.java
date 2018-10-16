package entity;

public class Orders {
public static String statuss[]={"未支付 ","已支付","已发货","已收货","已退货","已取消","用户已删除"};
public static String assessstatuss[]= {"可评价","不能评价"};
int id;
String date;
String code;
Double amount;
Double nowamount;
int address_id;
int user_id;
int status;
int assessstatus;
String comments;
String user_email;
String address_zone;
String address_addr;

public String getDetail_Name(){
	return address_zone+address_addr ;
}
public String getStatus_Name(){
	return statuss[status];
}
public String getAssessstatus_Name(){
	return assessstatuss[assessstatus];
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}

public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public Double getAmount() {
	return amount;
}
public void setAmount(Double amount) {
	this.amount = amount;
}
public Double getNowamount() {
	return nowamount;
}
public void setNowamount(Double nowamount) {
	this.nowamount = nowamount;
}
public int getAddress_id() {
	return address_id;
}
public void setAddress_id(int address_id) {
	this.address_id = address_id;
}
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public String getComments() {
	return comments;
}
public void setComments(String comments) {
	this.comments = comments;
}
public int getAssessstatus() {
	return assessstatus;
}
public void setAssessstatus(int assessstatus) {
	this.assessstatus = assessstatus;
}
public String getUser_email() {
	return user_email;
}
public void setUser_email(String user_email) {
	this.user_email = user_email;
}
}
