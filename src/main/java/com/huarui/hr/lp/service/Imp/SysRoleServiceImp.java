package com.huarui.hr.lp.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huarui.hr.entity.SysRole;
import com.huarui.hr.lp.mapper.SysRoleMapper;
@Service
public class SysRoleServiceImp implements com.huarui.hr.lp.service.SysRoleService {
	@Autowired
	private SysRoleMapper srm;
	
	@Override
	public List<SysRole> query() {
		List<SysRole> list = srm.query();
		return list;
	}

}
