package dao;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import entity.Operator;
import entity.pass;
import util.SearchInfo;

@Repository
public interface Operator_dao {

@Select("select*from operator ${where} ${limit}")
public List<Operator>  select(SearchInfo info);
@Insert("insert into operator(nike,password,name,sex,power,status,tel,comments) values(#{nike},#{md5},#{name},#{sex},#{power},#{status},#{tel},#{comments})")
public void insert(Operator ops);
@Update("update operator set nike=#{nike},name=#{name},sex=#{sex},power=#{power},password=#{password},status=#{status},tel=#{tel},comments=#{comments} where id=#{id}")
public void update(Operator ops);
@Update("update operator set status = 1 where id=#{id}")
public void delete(int id);
@Select("select*from operator where id=#{id}")
public Operator getById(int id);
@Select("select count(id) count from operator ${where}")
public int  select1(SearchInfo info);
@Update("update operator set password=#{md5} where id=#{id}")
public void update1(pass p);

}
