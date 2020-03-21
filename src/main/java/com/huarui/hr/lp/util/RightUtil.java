package com.huarui.hr.lp.util;

import java.util.ArrayList;
import java.util.List;

import com.huarui.hr.entity.SysRight;

public class RightUtil {
	//���ݸ���ID�õ���ص�Ȩ��
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
