package com.huarui.hr.lp.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.huarui.hr.entity.SysRight;

@Repository
public interface SysRightMapper {
	public List<SysRight> query();
	public List<SysRight> queryById(Integer id);
	public List<SysRight> queryRightAndRoleByRoleId(Integer roleid);
}
