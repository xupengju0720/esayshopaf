package serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.Operator_dao;
import entity.Operator;
import entity.pass;
import service.Operator_service;
import util.SearchInfo;
@Service
public class Operator_serviceImpl implements Operator_service{
   @Autowired
   Operator_dao dao;
	public List<Operator> select(SearchInfo info) {
		return dao.select(info);
	}
	public void insert(Operator ops) {
       dao.insert(ops);		
	}
	public void update(Operator ops) {
		  dao.update(ops);
	}
	public void delete(int id) {
		 dao.delete(id);
	}
	public Operator getById(int id) {
		return dao.getById(id);
	}
	public int select1(SearchInfo info) {
		return dao.select1(info);
	}
	public void update1(pass p) {
		dao.update1(p);
	}



}
