package com.huarui.hr.lp.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huarui.hr.entity.SysRight;

public class MyUtil {
	//菜单找儿子的方法 
	public static void getSon(List<SysRight> list,Map<String,Object> fatherMap,Integer fatherId) {
		//存放儿子的集合
		List<Map<String, Object>> sons = new ArrayList<Map<String,Object>>();
		for (SysRight s : list) {
			//判断是不是指定的儿子
			if(s.getRight_parent_code()==fatherId) {
				Map<String, Object> son = new HashMap<String, Object>();
				son.put("id", s.getRight_parent_code());
				son.put("text", s.getRight_text());
				//此儿子节点还是别人的父节点。。。。(递归)
				//s.getRight_parent_code()!=0是为了 表示不是最大的节点
				if(s.getRight_parent_code()!=0&&"parent".equals(s.getRight_type())) {
					son.put("state", "closed");
					//找到当前对象的儿子(递归)
					getSon(list, son, s.getRight_code());
				}
				//把儿子存放到集合
				sons.add(son);
			}
		}
		//把儿子绑定到父亲
		fatherMap.put("children", sons);
	}
}
