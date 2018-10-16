package service;

import java.util.List;

import entity.Type;
import util.SearchInfo;

public interface Type_service {
	public List<Type> select(SearchInfo info);
	public void insert(Type t);
	public void update(Type t);
	public void delete(int id);
	public Type getById(int id);
	public int  select1(SearchInfo info);
}
