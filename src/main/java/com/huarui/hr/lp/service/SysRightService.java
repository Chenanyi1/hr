package com.huarui.hr.lp.service;

import java.util.List;

import com.huarui.hr.entity.SysRight;

public interface SysRightService {
	public List<SysRight> query();
	public List<SysRight> queryById(Integer id);
}
