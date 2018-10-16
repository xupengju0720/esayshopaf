package service;

import java.util.List;

import entity.Shopcar;
import util.SearchInfo;
public interface Shopcar_service {
	public List<Shopcar> select(SearchInfo info);
	public void insert(Shopcar s);
	public void update(Shopcar s);
	public void delete(int id);
	public Shopcar getById(int id);
	public int  select1(SearchInfo info);
}
