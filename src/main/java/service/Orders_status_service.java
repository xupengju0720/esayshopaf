package service;

import java.util.List;

import entity.Orders_status;
import util.SearchInfo;

public interface Orders_status_service {

public List<Orders_status>  select(SearchInfo info1);
public void insert(Orders_status ops);
public void update(Orders_status ops);
public Orders_status getById(int id);
public int  select1(SearchInfo info1);

}
