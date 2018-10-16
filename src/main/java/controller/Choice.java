package controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.MD5;
import entity.Operator;
import service.Operator_service;
import util.SearchInfo;
import util.jsonInfo;

@Controller
public class Choice {
@Autowired
Operator_service service;
@RequestMapping("login")
public String index(SearchInfo info,Operator op,HttpSession session) {
	session.removeAttribute("statecount");
	session.removeAttribute("sdetail");
	 String a=(String) session.getAttribute("randomCode").toString();
	 if (a.equalsIgnoreCase(op.getComments())) {
		 List<Operator> oo=service.select(info);
			for (int i = 0; i <oo.size(); i++) {
				if (oo.get(i).getNike().equals(op.getNike())) {
					if (oo.get(i).getPassword().equals(MD5.MD5(op.getPassword()))) {
						session.setMaxInactiveInterval(6000);
						int id =oo.get(i).getId();
						session.setAttribute("sdetail",service.getById(id));
						return "redirect:/index.jsp";
					}else {
						 session.setAttribute("statecount", "µÇÂ½Ê§°Ü!");
						return "redirect:/login.jsp";
					}
				}
			}
	}else {
		session.setAttribute("statecount", "ÑéÖ¤Âë´íÎó!");
	}
	 
	return "redirect:/login.jsp";
}

}
