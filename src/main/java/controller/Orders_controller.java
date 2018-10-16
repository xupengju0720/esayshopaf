package controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.Orders;
import entity.Orders_status;
import service.Orders_service;
import service.Orders_status_service;
import util.SearchInfo;
import util.jsonInfo;

@Controller
@RequestMapping("Orders")
public class Orders_controller {
@Autowired
Orders_service service;
@Autowired
Orders_status_service sservice;
@RequestMapping("index")
public void index(ModelMap m, String txt1, String txt2,SearchInfo info,SearchInfo info1) {
	
	String where = "";
	if (txt1== null&&txt2==null) {
		txt2 = "";
	    txt1 = "";
	}
	if (txt1!= null&&txt2==null) {
	    txt2 = "";
	    where ="where orders.date >="+txt1+" ";
	}
	if (txt1== null&&txt2!=null) {
		txt1 = "";
		where ="where orders.date <="+txt2+" ";
	}
	if (txt1!= null && txt1.length() > 0&&txt2!=null&&txt2.length()>0) {
		if(txt1.compareTo(txt2)>0) {
          String ptxt =txt1;
          txt1 =txt2;
          txt2=ptxt;
		}
		where = " where orders.date >='"+txt1+"' and orders.date <='"+txt2+"' ";
	}
	Integer count = service.select1(info);
	info.setCount(count);
	info.pagemax();
	info.setWhere(where);
	m.put("search", info);
	m.put("list", sservice.select(info1));
	m.put("list", service.select(info));
}

@RequestMapping("add")
protected String add(ModelMap m) {
	m.put("statuss", Orders.statuss);
	m.put("assessstatuss", Orders.assessstatuss);
	return "Orders/edit";
}

@RequestMapping("update")
protected @ResponseBody jsonInfo update(Orders ops) { 
	service.update(ops);
	return new jsonInfo(1, "");
}

@RequestMapping("edit")
protected String edit(ModelMap m, int id) {
	m.put("info", service.getById(id));
	return add(m);
}

@RequestMapping("edit1")
protected String edit1(ModelMap m, int id) {
	return "Orders/edit1";
}

@RequestMapping("insert")
protected @ResponseBody jsonInfo insert(Orders_status a) {
	sservice.insert(a);
	service.update1(a);
	return new jsonInfo(1, "");
}

@RequestMapping("add1")  //订单状态表
protected String add1(ModelMap m,int id) {
	SimpleDateFormat d1 = new SimpleDateFormat("yyyy-MM-dd");
	String date =d1.format(new Date());
	   m.put("orders_id",id);
	   m.put("date",date);
	  Orders p =  service.getById(id);
	  int status = p.getStatus()+1;
	  m.put("dest_status", status);
	return "Orders/edit1";
}
@RequestMapping("cancel")
protected String cancel(ModelMap m,int id) {
	service.ordersremove(id);
	Orders_status ords=new Orders_status();
	SimpleDateFormat d1 = new SimpleDateFormat("yyyy-MM-dd");
	String date =d1.format(new Date());
	ords.setOrders_id(id);
	ords.setDate(date);
	ords.setDest_status(5);
	ords.setComments("0");
	ords.setAmount(0.0);
    ords.setInfo("退货完成");
    ords.setNum(0);
    sservice.insert(ords);
	return "redirect:index";
}

}
