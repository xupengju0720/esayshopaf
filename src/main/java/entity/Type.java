package entity;

public class Type {
int id;
int parentid;
String name ;
String parent_name;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getParentid() {
	return parentid;
}
public void setParentid(int parentid) {
	this.parentid = parentid;
}
public String getParent_name() {
	return parent_name;
}
public void setParent_name(String parent_name) {
	this.parent_name = parent_name;
}
}

