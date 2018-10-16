package controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.MD5;
import entity.Operator;
import entity.Type;
import entity.pass;
import service.Operator_service;
import service.Type_service;
import util.Ids;
import util.SearchInfo;
import util.jsonInfo;

@Controller
@RequestMapping("Operator")
public class Operator_Controller {
	@Autowired
	Operator_service service;
	@RequestMapping("index")
	public void index(ModelMap m, String txt, SearchInfo info) {

		String where = "";
		if (txt == null)
			txt = "";
		if (txt != null && txt.length() > 0) {
			where = " where operator.name like '%" + txt + "%' ";
		}
		Integer count = service.select1(info);
		info.setCount(count);
		info.pagemax();
		info.setWhere(where);
		m.put("search", info);
		m.put("list", service.select(info));
	}
	@RequestMapping("insert")
	protected @ResponseBody jsonInfo insert(Operator a) {
		service.insert(a);
		return new jsonInfo(1, "");
	}

	@RequestMapping("add")
	protected String add(ModelMap m) {
		m.put("status", Operator.statuss);
		m.put("sex", Operator.sexs);
		m.put("power", Operator.powers);
		return "Operator/edit";
	}

	@RequestMapping("update")
	protected @ResponseBody jsonInfo update(Operator ops) { // 返回值是jsonInfo类内的数据组成的json字符串{"status":"属性值","text":"属性值"}
		service.update(ops);
		return new jsonInfo(1, "");
	}
	
	@RequestMapping("update1")
	protected @ResponseBody jsonInfo update1(pass pp,HttpSession session) { // 返回值是jsonInfo类内的数据组成的json字符串{"status":"属性值","text":"属性值"}
		if (pp.getPassword2().equals(pp.getPassword3())) {
			Operator operator=(Operator)session.getAttribute("sdetail");
			if (operator.getPassword().equals(MD5.MD5(pp.getPassword1()))) {
				service.update1(pp);
			    Operator op= service.getById(((Operator)session.getAttribute("sdetail")).getId());
				session.setAttribute("sdetail",op);
			}else {
				return  new jsonInfo(-1, "原密码错误");
			}
		}else {
			return  new jsonInfo(-1, "两次密码不一致");
		}
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

	@RequestMapping("deleteall")
	protected String deleteall(Ids ids) {
		String[] id = ids.getId().split(",");
		for (int i = 0; i < id.length; i++) {
			service.delete(Integer.valueOf(id[i]));
		}
		return "redirect:index";
	}

	
}
