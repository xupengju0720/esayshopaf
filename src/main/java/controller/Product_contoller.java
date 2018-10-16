package controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.Product;
import entity.Type;
import service.Product_service;
import service.Type_service;
import util.SearchInfo;
import util.jsonInfo;

@Controller
@RequestMapping("Product")
public class Product_contoller {
	@Autowired
	Product_service service;
	@Autowired
	Type_service sservice;
	@RequestMapping("index")
	public void index(ModelMap m,SearchInfo info,String txt) {
		String where = "";
		if (txt == null)
			txt = "";
		if (txt != null && txt.length() > 0) {
			where = " where product.fullname like '%" + txt + "%' ";
		}
		Integer count = service.select1(info);
		info.setCount(count);
		info.pagemax();
		info.setWhere(where);
		m.put("search", info);
		m.put("list",service.select(info));
	
	}
	@RequestMapping("insert")
	protected @ResponseBody jsonInfo insert(Product p) {
		service.insert(p);
		return new jsonInfo(1, "");
	}
	@RequestMapping("add")
	protected String add(ModelMap m) {
		m.put("status",Product.statuss);
		return "Product/edit";
	}
	@RequestMapping("update")
	protected @ResponseBody jsonInfo update(Product p) { // 返回值是jsonInfo类内的数据组成的json字符串{"status":"属性值","text":"属性值"}
		service.update(p);
		return new jsonInfo(1, "");
	}

	@RequestMapping("edit")
	protected String edit(ModelMap m, int id) {
		m.put("info", service.getById(id));
		return add(m);
	}
	
	@RequestMapping("delete")
	protected String delete(int id) {
		service.delete(id);
		return "redirect:index";
	}
	@RequestMapping("gettypelist")
	public @ResponseBody List<Type> gettypelist(int id){
		return sservice.select(new SearchInfo(" where type.parentid="+id,false));
	}
	@RequestMapping("getalltype")
	public @ResponseBody List<List<Type>> getalltype(int id){
		ArrayList<List<Type>> data=new ArrayList<List<Type>>();
		Type s=sservice.getById(id);
		List<Type> slist=sservice.select(new SearchInfo(" where type.parentid="+s.getParentid(),false));
		for(int i=0;i<slist.size();i++)if(slist.get(i).getId()==id) {
			slist.get(i).setParent_name("1");
		}
		data.add(slist);
		while(s.getParentid()>0) {
			s=sservice.getById(s.getParentid());
			slist=sservice.select(new SearchInfo(" where type.parentid="+s.getParentid(),false));
		    for(int i=0;i<slist.size();i++)if(slist.get(i).getId()==s.getId()) {
		    	slist.get(i).setParent_name("1");
		    }
		    data.add(0,slist);
		}
		return data;
	}
	
	
}
