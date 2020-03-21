package com.huarui.hr.lp.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.huarui.hr.entity.SysRight;

public class MyUtil {
	//�˵��Ҷ��ӵķ��� 
	public static void getSon(List<SysRight> list,Map<String,Object> fatherMap,Integer fatherId) {
		//��Ŷ��ӵļ���
		List<Map<String, Object>> sons = new ArrayList<Map<String,Object>>();
		for (SysRight s : list) {
			//�ж��ǲ���ָ���Ķ���
			if(s.getRight_parent_code()==fatherId) {
				Map<String, Object> son = new HashMap<String, Object>();
				son.put("id", s.getRight_parent_code());
				son.put("text", s.getRight_text());
				//�˶��ӽڵ㻹�Ǳ��˵ĸ��ڵ㡣������(�ݹ�)
				//s.getRight_parent_code()!=0��Ϊ�� ��ʾ�������Ľڵ�
				if(s.getRight_parent_code()!=0&&"parent".equals(s.getRight_type())) {
					son.put("state", "closed");
					//�ҵ���ǰ����Ķ���(�ݹ�)
					getSon(list, son, s.getRight_code());
				}
				//�Ѷ��Ӵ�ŵ�����
				sons.add(son);
			}
		}
		//�Ѷ��Ӱ󶨵�����
		fatherMap.put("children", sons);
	}
}
