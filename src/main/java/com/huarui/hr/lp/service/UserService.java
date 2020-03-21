package com.huarui.hr.lp.service;

import java.util.List;

import com.huarui.hr.entity.SysUser;
import com.huarui.hr.lp.util.MyPage;

public interface UserService {
	public SysUser queryUserByNameAndPwd(SysUser user);
	
	public List<SysUser> queryUser(MyPage page);
	
	public Integer insertUser(SysUser user);
}
