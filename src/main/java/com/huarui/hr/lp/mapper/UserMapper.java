package com.huarui.hr.lp.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.huarui.hr.entity.SysUser;
import com.huarui.hr.lp.util.MyPage;

@Repository
public interface UserMapper {

	public SysUser queryUserByNameAndPwd(SysUser user);

	public List<SysUser> queryUser(MyPage page);
	
	public Integer insertUser(SysUser user);
	
}
