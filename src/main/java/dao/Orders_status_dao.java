package dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import entity.Orders_status;
import util.SearchInfo;

@Repository
public interface Orders_status_dao {

@Select("select orders_status.*,orders.code orders_code from orders_status inner join orders on orders_status.orders_id=orders.id")
public List<Orders_status>  select(SearchInfo info1);
@Insert("insert into orders_status(orders_id,date,dest_status,info,amount,comments,num) values(#{orders_id},#{date},#{dest_status},#{info},#{amount},#{comments},#{num})")
public void insert(Orders_status ops);
@Update("update orders_status set oders_id=#{oders_id}, date=#{date} ,dest_status=#{dest_status},info=#{info},amount=#{amount},comments=#{comments} where id=#{id}")
public void update(Orders_status ops);
@Select("select*from orders_status where orders_id=#{id}")
public Orders_status getById(int id);
@Select("select count(id) count from orders_status ${where}")
public int  select1(SearchInfo info1);
}
