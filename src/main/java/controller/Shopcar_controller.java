package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import service.Shopcar_service;
import util.SearchInfo;

@Controller
@RequestMapping("Shopcar")
public class Shopcar_controller {
	@Autowired
	Shopcar_service service;
	@RequestMapping("index")
	public void index(ModelMap m, String txt, SearchInfo info,Integer select) {
	   
		String where = "";
		if (txt == null)
			txt = "";
		if ( select == null) select =0;
		if (txt != null && txt.length() > 0) {
			switch (select) {
			case 1:
				where = " where product.fullname like '%" + txt + "%' ";
				break;
			default:
				where = " where user.email like '%" + txt + "%' ";
				break;
			}
			
		}
		Integer count = service.select1(info);
		info.setCount(count);
		info.pagemax();
		info.setWhere(where);
		m.put("search", info);
		m.put("list", service.select(info));
	}
}
