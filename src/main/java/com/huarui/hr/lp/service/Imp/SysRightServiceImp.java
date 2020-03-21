package com.huarui.hr.lp.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huarui.hr.entity.SysRight;
import com.huarui.hr.lp.mapper.SysRightMapper;
import com.huarui.hr.lp.service.SysRightService;

@Service
public class SysRightServiceImp implements SysRightService {
	@Autowired
	private SysRightMapper sysRightMapper;

	@Override
	public List<SysRight> query() {
		List<SysRight> list = sysRightMapper.query();
		return list;
	}

	@Override
	public List<SysRight> queryById(Integer id) {
		List<SysRight> queryById = sysRightMapper.queryById(id);
		return queryById;
	}
	
	
}
