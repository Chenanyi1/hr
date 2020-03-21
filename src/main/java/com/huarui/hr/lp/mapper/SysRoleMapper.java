package com.huarui.hr.lp.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.huarui.hr.entity.SysRole;
import com.huarui.hr.entity.SysUser;

@Repository
public interface SysRoleMapper {

	public SysRole queryRoleById(Integer roleID);

	public SysRole queryRoleById2(Integer roleID);
	
	public List<SysRole> query();
}
