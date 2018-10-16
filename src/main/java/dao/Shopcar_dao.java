package dao;

import java.util.List;


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import entity.Shopcar;
import util.SearchInfo;
@Repository
public interface Shopcar_dao {
@Select("select shopcar.*,user.email user_email,product.fullname product_fullname from shopcar inner join user on shopcar.user_id=user.id inner join product on  shopcar.product_id=product.id ${where}")
public List<Shopcar> select(SearchInfo info);
@Insert("insert into shopcar(count,product_id,user_id) values(#{count},#{product_id},#{user_id})")
public void insert(Shopcar s);
@Update("update shopcar set count=#{count},product_id=#{product_id},user_id=#{user_id}")
public void update(Shopcar s);
@Delete("delete from shopcar where id=#{id}")
public void delete(int id);
@Select("select*from shopcar where id=#{id}")
public Shopcar getById(int id);
@Select("select count(id) count from shopcar ${where}")
public int  select1(SearchInfo info);

}
