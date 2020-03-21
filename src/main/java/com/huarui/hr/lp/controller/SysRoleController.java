package com.huarui.hr.lp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huarui.hr.entity.SysRole;
import com.huarui.hr.lp.service.SysRoleService;

@Controller
@RequestMapping("/sysRoleController")
public class SysRoleController {
	@Autowired
	private SysRoleService srs;
	
	@RequestMapping("/query")
	@ResponseBody
	public List query() {
		System.out.println(srs);
		List<SysRole> list = srs.query();
		for (SysRole s : list) {
			System.out.println(s);
		}
		return list;
	}
}
