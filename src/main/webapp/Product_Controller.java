package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import utils.SearchInfo;
import utils.jsonInfo;

@Controller
@RequestMapping("Products")
public class Product_Controller {

	@Autowired
	Product_service service;
	@Autowired
	Type_service  tservice;
	
	@RequestMapping("gettypelist")
	public @ResponseBody List<Type> gettypelist(int id){
		return tservice.select(new SearchInfo(" where type.parentid="+id,false));
	}
	@RequestMapping("getalltype")
	public @ResponseBody List<List<Type>> getalltype(int id){
		ArrayList<List<Type>> data=new ArrayList<List<Type>>();
		Type s=tservice.getById(id);
		List<Type> slist=tservice.select(new SearchInfo(" where type.parentid="+s.getParentid(),false));
		for(int i=0;i<slist.size();i++)if(slist.get(i).getId()==id) {
			slist.get(i).setParent_name("1");
		}
		data.add(slist);
		while(s.getParentid()>0) {
			s=tservice.getById(s.getParentid());
			slist=tservice.select(new SearchInfo(" where type.parentid="+s.getParentid(),false));
		    for(int i=0;i<slist.size();i++)if(slist.get(i).getId()==s.getId()) {
		    	slist.get(i).setParent_name("1");
		    }
		    data.add(0,slist);
		}
		return data;
	}
	
	@RequestMapping("Product")
	public void Product(SearchInfo info, String txt, Integer select, ModelMap m) {
		m.put("typelist", tservice.select(new SearchInfo(" where type.parentid=0 ",false)));
		
		
		if (select == null)
			select = 0;
		String where = "";
		if (txt != null && txt.length() > 0) {
			switch (select) {
			case 1:
				where = " where product.type_id = " + txt + " ";
				break;
			case 2:
				where = " where product.info like '%" + txt + "%'";
				break;
			case 3:
				where = " where product.priority = " + txt + " ";
				break;
			case 4:
				where = " where product.status = " + txt + " ";
				break;
			default:
				where = " where product.fullname like '%" + txt + "%'";

			}
			
			
		}
	
			m.put("select", select);
			m.put("txt", select==0?"'"+txt+"'":txt);
			m.put("sstatus",Product.sstatus);
			m.put("pristatus",Product.pristatus);
			m.put("typerow",tservice.select(info));
			
			
			info.setWhere(where);
			m.put("search",info);
			Integer count=service.select1(info);
			info.setCount(count);
			//info.setCanPage(false);
			m.put("list", service.select(info));
			
	}

	@RequestMapping("insert")
	protected @ResponseBody jsonInfo insert(Product p) {
	
		service.insert(p);
		
		return new jsonInfo(1, "");
	}

	@RequestMapping("update")
	protected @ResponseBody jsonInfo update(Product p) {

		service.update(p);
		return new jsonInfo(1, "");
	}
	@RequestMapping("dele")
	protected String dele(int id){
	 	 
		 service.delete(id);
		 return "redirect:Product";
	}
	
	
	
	@RequestMapping("add")
	protected String add(SearchInfo info, ModelMap m) {
	m.put("pristatus",Product.pristatus);
	m.put("sstatus",Product.sstatus);
	m.put("typerow",tservice.select(info));
	return "Products/Predit";
	}
	
	@RequestMapping("Predit")
	protected String Predit(SearchInfo info,ModelMap m,int id) {
		m.put("info",service.getById(id));
	 return add(info,m);
	
}

}
