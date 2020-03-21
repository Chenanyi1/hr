package com.huarui.hr.lp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huarui.hr.entity.SysUser;
import com.huarui.hr.lp.service.MyCountService;
import com.huarui.hr.lp.service.UserService;
import com.huarui.hr.lp.util.MyPage;

@Controller
@RequestMapping("/userController")
public class UserController {
	@Autowired
	private UserService us;
	@Autowired
	private MyCountService ms;
	
	
	@RequestMapping("/queryUserByNameAndPwd")
	public String queryUserByNameAndPwd(HttpSession sess,SysUser s) {
		System.out.println(s);
		SysUser sysUser = us.queryUserByNameAndPwd(s);
		if(sysUser!=null) {
			sess.setAttribute("user", sysUser);
		}else {
			return "redirect:../login.jsp";
		}
		return "redirect:../page/index.jsp";
	}
	
	@RequestMapping("/queryUser")
	@ResponseBody
	public Map queryUser(MyPage page) {
		List<SysUser> list = us.queryUser(page);
		System.out.println(page);
		Integer count = ms.getTableCount("u_id", "users");
		Map map = new HashMap();
		map.put("rows", list);//每页几条数据
		map.put("total", count);//总共几条数据
		return map;
	}
	
	@RequestMapping("insertUser")
	@ResponseBody
	public int insertUser(SysUser user) {
		System.out.println("添加用户 "+user);
		Integer i = us.insertUser(user);
		return i;
	}
}
