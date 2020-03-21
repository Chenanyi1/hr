package com.huarui.hr.lp.util;

import java.util.ArrayList;
import java.util.List;

import com.huarui.hr.entity.SysRight;

public class RightUtil {
	//根据父类ID得到相关的权限
	public static List<SysRight> getRightByParentId(List<SysRight> list,Integer parentId){
		ArrayList<SysRight> list2 = new ArrayList<SysRight>();
		for (SysRight s : list) {
			if(s.getRight_parent_code()==parentId) {
				list2.add(s);
			}
		}
		return list2;
	}
}
