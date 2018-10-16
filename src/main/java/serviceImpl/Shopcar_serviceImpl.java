package serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.Shopcar_dao;
import entity.Shopcar;
import service.Shopcar_service;
import util.SearchInfo;

@Service
public class Shopcar_serviceImpl implements Shopcar_service {
@Autowired
Shopcar_dao dao;
	public List<Shopcar> select(SearchInfo info) {
		return dao.select(info);
	}
	public void insert(Shopcar s) {
    dao.insert(s);		
	}

	public void update(Shopcar s) {
		dao.update(s);
	}
	public void delete(int id) {
		dao.delete(id);
	}
	public Shopcar getById(int id) {
		return dao.getById(id);
	}
	public int select1(SearchInfo info) {
		return dao.select1(info);
	}

}
