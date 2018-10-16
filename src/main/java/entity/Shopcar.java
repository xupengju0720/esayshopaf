package entity;

public class Shopcar {
int id;
int user_id;
int product_id;
int count;
String user_email;
String  product_fullname;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public int getProduct_id() {
	return product_id;
}
public void setProduct_id(int product_id) {
	this.product_id = product_id;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}

public String getProduct_fullname() {
	return product_fullname;
}
public void setProduct_fullname(String product_fullname) {
	this.product_fullname = product_fullname;
}
public String getUser_email() {
	return user_email;
}
public void setUser_email(String user_email) {
	this.user_email = user_email;
}


}
