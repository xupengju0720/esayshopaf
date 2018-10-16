package serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.Orders_status_dao;
import entity.Orders_status;
import service.Orders_status_service;
import util.SearchInfo;
@Service
public class Orders_status_serviceImpl implements Orders_status_service{
@Autowired
Orders_status_dao dao;
	public List<Orders_status> select(SearchInfo info1) {
		return dao.select(info1);
	}

	public void insert(Orders_status ops) {
    dao.insert(ops);		
	}

	public void update(Orders_status ops) {
		dao.update(ops);
	}

	public Orders_status getById(int id) {
		return dao.getById(id);
	}

	public int select1(SearchInfo info1) {
		// TODO Auto-generated method stub
		return dao.select1(info1);
	}

}
