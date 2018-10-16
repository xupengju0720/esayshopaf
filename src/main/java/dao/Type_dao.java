package dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import entity.Type;
import util.SearchInfo;
@Repository
public interface Type_dao {
@Select("select type.*,t.name parent_name from type left join type t on t.id = type.parentid ${where}")
public List<Type> select(SearchInfo info);
@Insert("insert into type(name,parentid) values(#{name},#{parentid})")
public void insert(Type t);
@Update("update type set name=#{name} where id=#{id}")
public void update(Type t);
@Delete("delete from type where id=#{id}")
public void delete(int id);
@Select("select*from type where id=#{id}")
public Type getById(int id);
@Select("select count(id) count from type ${where}")
public int  select1(SearchInfo info);




}
