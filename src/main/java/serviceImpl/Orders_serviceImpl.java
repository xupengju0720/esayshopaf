package serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.Orders_dao;
import entity.Orders;
import entity.Orders_status;
import service.Orders_service;
import util.SearchInfo;

@Service
public class Orders_serviceImpl implements Orders_service{
	@Autowired
     Orders_dao dao;
	public List<Orders> select(SearchInfo info) {
		return dao.select(info);
	}



	public Orders getById(int id) {
		return dao.getById(id);
	}

	public int select1(SearchInfo info) {
		return dao.select1(info);
	}

	public void update(Orders ops) {
		dao.update(ops);
	}



	public void update1(Orders_status ops) {
		dao.update1(ops);
	}

	public void ordersremove(int id) {
      dao.ordersremove(id);		
	}
}
