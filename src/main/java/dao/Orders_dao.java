package dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import entity.Orders;
import entity.Orders_status;
import util.SearchInfo;

@Repository
public interface Orders_dao {

@Select("select orders.*,user.email user_email,address.zone address_zone,address.addr address_addr from orders inner join user on orders.user_id=user.id inner join address on orders.address_id=address.id ${where}")
public List<Orders>  select(SearchInfo info);
@Update("update orders set date=#{date}, code=#{code} ,amount=#{amount},nowamount=#{nowamount},address_id=#{address_id},user_id=#{user_id},status=#{status},assessstatus=#{assessstatus},comments=#{comments} where id=#{id}")
public void update(Orders ops);
@Select("select*from orders where id=#{id}")
public Orders getById(int id);
@Select("select count(id) count from orders ${where}")
public int  select1(SearchInfo info);
@Update("update orders set status=#{dest_status} where id=#{orders_id}")
public void update1(Orders_status ops);
@Update("update orders set status = 5 where id=#{id}")
public void ordersremove(int id);




}
