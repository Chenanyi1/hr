package com.huarui.hr.lp.service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huarui.hr.lp.mapper.MyCountMapper;
import com.huarui.hr.lp.service.MyCountService;

@Service
public class MyCountServiceImp implements MyCountService {
	@Autowired
	private MyCountMapper mm;
	
	@Override
	public Integer getTableCount(String rowsName, String tableName) {
		Integer count = mm.getTableCount(rowsName, tableName);
		return count;
	}

}
