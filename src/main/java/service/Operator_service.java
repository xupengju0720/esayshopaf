package service;

import java.util.List;

import entity.Operator;
import entity.pass;
import util.SearchInfo;

public interface Operator_service {
	public List<Operator>  select(SearchInfo info);
	public void insert(Operator ops);
	public void update(Operator ops);
	public void delete(int id);
	public Operator getById(int id);
	public int  select1(SearchInfo info);
	public void update1(pass p);
}
