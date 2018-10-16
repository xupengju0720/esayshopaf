package entity;

public class pass {
String password1;
String password2;
String password3;
int id;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getPassword1() {
	return password1;
}
public void setPassword1(String password1) {
	this.password1 = password1;
}
public String getPassword2() {
	return password2;
}
public void setPassword2(String password2) {
	this.password2 = password2;
}
public String getPassword3() {
	return password3;
}
public void setPassword3(String password3) {
	this.password3 = password3;
}
public String getMd5() {
	return MD5.MD5(password2);
}

}