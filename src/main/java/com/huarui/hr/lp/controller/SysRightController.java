package com.huarui.hr.lp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.huarui.hr.entity.SysRight;
import com.huarui.hr.entity.SysUser;
import com.huarui.hr.lp.service.SysRightService;
import com.huarui.hr.lp.util.MyUtil;
import com.huarui.hr.lp.util.RightUtil;

@Controller
@RequestMapping("/sysRightController")
public class SysRightController {
	@Autowired
	private SysRightService srs;

	// 一次性加载数据
	@RequestMapping("/query")
	public String query(HttpServletResponse resp) {
		System.out.println("query");
		// 查询所有的数据
		List<SysRight> list = srs.query();
		// 创建一个list集合保存需要转成json的数据
		// fathermap 存放所有的父类和父类自己的儿子
		List<Map<String, Object>> fathermap = new ArrayList<Map<String, Object>>();
		for (SysRight f : list) {
			// 判断是不是最大的根菜单
			if (f.getRight_parent_code() == 0 && "parent".equals(f.getRight_type())) {
				HashMap<String, Object> father = new HashMap<String, Object>();
				// 节点的ID
				father.put("id", f.getRight_code());
				// 显示的文本
				father.put("text", f.getRight_text());
				// 是否展开
				father.put("state", "closed");
				System.out.println("text : " + f.getRight_text());
				// 找儿子
				MyUtil.getSon(list, father, f.getRight_code());
				// 把父亲添加的集合
				fathermap.add(father);
				// father.put("children", );
			}
		}
		System.out.println("fathermap " + fathermap);
		Gson g = new Gson();
		String msg = g.toJson(fathermap);
		System.out.println("msg: " + msg);
		try {
			resp.setContentType("text/json;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.print(msg);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "index";
	}

	// 一次性加载数据
	@RequestMapping("/query2")
	@ResponseBody // 可以自动把返回的数据转成JSON
	public List query2(HttpServletResponse resp) {
		System.out.println("query");
		// 查询所有的数据
		List<SysRight> list = srs.query();
		// 创建一个list集合保存需要转成json的数据
		// fathermap 存放所有的父类和父类自己的儿子
		List<Map<String, Object>> fathermap = new ArrayList<Map<String, Object>>();
		for (SysRight f : list) {
			// 判断是不是最大的根菜单
			if (f.getRight_parent_code() == 0 && "parent".equals(f.getRight_type())) {
				HashMap<String, Object> father = new HashMap<String, Object>();
				// 节点的ID
				father.put("id", f.getRight_code());
				// 显示的文本
				father.put("text", f.getRight_text());
				// 是否展开
				father.put("state", "closed");
				System.out.println("text : " + f.getRight_text());
				// 找儿子
				MyUtil.getSon(list, father, f.getRight_code());
				// 把父亲添加的集合
				fathermap.add(father);
				// father.put("children", );
			}
		}
		return fathermap;
	}

	// 每点击父节点加载数据
	@RequestMapping("/query3")
	@ResponseBody // 可以自动把返回的数据转成JSON
	public List query3(HttpSession sess, Integer id) {
		System.out.println(id);
		if (id == null) {
			// 页面第一次访问的值是null,为了让数据库查询把ID换成0
			id = 0;
		}
		// 得到当前登录的用户信息
		SysUser u = (SysUser) sess.getAttribute("user");
		// 根据父ID找子菜单
		// 先拿到当前登录的用户信息,再拿当前登录用户的角色,再拿到当前登录用户的所有权限
		List<SysRight> userlist = u.getRole().getRights();
		System.out.println("111:" + userlist);
		List<SysRight> list = RightUtil.getRightByParentId(userlist, id);
		System.out.println("222:" + list);
		// List<SysRight> list = srs.queryById(id);
		// 保存全部的父节点
		List<Map<String, Object>> meunList = new ArrayList<Map<String, Object>>();
		for (SysRight s : list) {
			// 保存一个父节点
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", s.getRight_code());
			map.put("text", s.getRight_text());
			if ("parent".equals(s.getRight_type())) {
				// 是父节点就叠起来,是为了去页面点击这个节点的时候发送请求
				map.put("state", "closed");
			} else {
				// 是子节点就绑定属性
				Map<String, Object> arr = new HashMap<String, Object>();
				arr.put("url", s.getRight_url());
				arr.put("tip", s.getRight_tip());
				map.put("arr", arr);
			}
			// 发现有节点就添加到节点集合中
			meunList.add(map);
		}
		return meunList;
	}
}
