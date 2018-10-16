package service;

import java.util.List;
import entity.Orders;
import entity.Orders_status;
import util.SearchInfo;

public interface Orders_service {
	public List<Orders>  select(SearchInfo info);
	public void update(Orders ops);
	public Orders getById(int id);
	public int  select1(SearchInfo info);
	public void update1(Orders_status ops);
	public void ordersremove(int id);
}
