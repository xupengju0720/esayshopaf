package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.Type;
import service.Type_service;
import util.SearchInfo;
import util.jsonInfo;

@Controller
@RequestMapping("Type")
public class Type_controller {
@Autowired
Type_service service;

@RequestMapping("gettypelist")
public @ResponseBody List<Type> gettypelist(int id){
	return service.select(new SearchInfo(" where type.parentid="+id,false));
}

@RequestMapping("index")
public void Type(SearchInfo info, Integer parentid, ModelMap m) {
	 m.put("typelist", service.select(new SearchInfo(" where type.parentid=0 ",false)));
	if (parentid == null)
		parentid = 0;
	m.put("parentid", parentid);
	if (parentid > 0) {
		Type t = service.getById(parentid);
		m.put("name", t.getName());
		String path = "/<a href=\"index?parentid=" + t.getId() + "\">" + t.getName() + "</a>";
		while (t.getParentid() > 0) {
			t = service.getById(t.getParentid());
			path = "/<a href=\"index?parentid=" + t.getId() + "\">" + t.getName() + "</a>"+path;
		}
		path = "<a href=\"index?parentid=0\">根节点</a>" + path;
		m.put("path", path);
	} else {
		m.put("name", "根节点");
		m.put("path", "<a href=\"index?parentid=0\">根节点</a>");
	}

	info.setCanPage(false);
	info.setWhere(" where type.parentid=" + parentid);
	m.put("list", service.select(info));

}
@RequestMapping("insert")
protected @ResponseBody jsonInfo insert(Type a) {
	service.insert(a);
	return new jsonInfo(1, "");
}

@RequestMapping("add")
protected String add(ModelMap m,Integer parentid) {
	m.put("parentid", parentid);
	return "Type/edit";
}

@RequestMapping("update")
protected @ResponseBody jsonInfo update(Type t) { // 返回值是jsonInfo类内的数据组成的json字符串{"status":"属性值","text":"属性值"}
	service.update(t);
	return new jsonInfo(1, "");
}

@RequestMapping("edit")
protected String edit(ModelMap m, int id) {
	m.put("info", service.getById(id));
	return "Type/edit";
}



}
