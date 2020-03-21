package com.huarui.hr.lp.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huarui.hr.entity.SysUser;
import com.huarui.hr.lp.mapper.UserMapper;
import com.huarui.hr.lp.service.UserService;
import com.huarui.hr.lp.util.MyPage;
@Service
public class UserServiceImp implements UserService {
	@Autowired
	private UserMapper um;
	
	
	@Override
	public SysUser queryUserByNameAndPwd(SysUser user) {
		SysUser sysUser = um.queryUserByNameAndPwd(user);
		return sysUser;
	}


	@Override
	public List<SysUser> queryUser(MyPage page) {
		List<SysUser> list = um.queryUser(page);
		return list;
	}


	@Transactional
	public Integer insertUser(SysUser user) {
		Integer insertUser = um.insertUser(user);
		return insertUser;
	}

}
