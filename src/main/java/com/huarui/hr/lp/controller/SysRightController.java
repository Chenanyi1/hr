package com.huarui.hr.lp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.huarui.hr.entity.SysRight;
import com.huarui.hr.entity.SysUser;
import com.huarui.hr.lp.service.SysRightService;
import com.huarui.hr.lp.util.MyUtil;
import com.huarui.hr.lp.util.RightUtil;

@Controller
@RequestMapping("/sysRightController")
public class SysRightController {
	@Autowired
	private SysRightService srs;

	// һ���Լ�������
	@RequestMapping("/query")
	public String query(HttpServletResponse resp) {
		System.out.println("query");
		// ��ѯ���е�����
		List<SysRight> list = srs.query();
		// ����һ��list���ϱ�����Ҫת��json������
		// fathermap ������еĸ���͸����Լ��Ķ���
		List<Map<String, Object>> fathermap = new ArrayList<Map<String, Object>>();
		for (SysRight f : list) {
			// �ж��ǲ������ĸ��˵�
			if (f.getRight_parent_code() == 0 && "parent".equals(f.getRight_type())) {
				HashMap<String, Object> father = new HashMap<String, Object>();
				// �ڵ��ID
				father.put("id", f.getRight_code());
				// ��ʾ���ı�
				father.put("text", f.getRight_text());
				// �Ƿ�չ��
				father.put("state", "closed");
				System.out.println("text : " + f.getRight_text());
				// �Ҷ���
				MyUtil.getSon(list, father, f.getRight_code());
				// �Ѹ�����ӵļ���
				fathermap.add(father);
				// father.put("children", );
			}
		}
		System.out.println("fathermap " + fathermap);
		Gson g = new Gson();
		String msg = g.toJson(fathermap);
		System.out.println("msg: " + msg);
		try {
			resp.setContentType("text/json;charset=utf-8");
			PrintWriter out = resp.getWriter();
			out.print(msg);
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "index";
	}

	// һ���Լ�������
	@RequestMapping("/query2")
	@ResponseBody // �����Զ��ѷ��ص�����ת��JSON
	public List query2(HttpServletResponse resp) {
		System.out.println("query");
		// ��ѯ���е�����
		List<SysRight> list = srs.query();
		// ����һ��list���ϱ�����Ҫת��json������
		// fathermap ������еĸ���͸����Լ��Ķ���
		List<Map<String, Object>> fathermap = new ArrayList<Map<String, Object>>();
		for (SysRight f : list) {
			// �ж��ǲ������ĸ��˵�
			if (f.getRight_parent_code() == 0 && "parent".equals(f.getRight_type())) {
				HashMap<String, Object> father = new HashMap<String, Object>();
				// �ڵ��ID
				father.put("id", f.getRight_code());
				// ��ʾ���ı�
				father.put("text", f.getRight_text());
				// �Ƿ�չ��
				father.put("state", "closed");
				System.out.println("text : " + f.getRight_text());
				// �Ҷ���
				MyUtil.getSon(list, father, f.getRight_code());
				// �Ѹ�����ӵļ���
				fathermap.add(father);
				// father.put("children", );
			}
		}
		return fathermap;
	}

	// ÿ������ڵ��������
	@RequestMapping("/query3")
	@ResponseBody // �����Զ��ѷ��ص�����ת��JSON
	public List query3(HttpSession sess, Integer id) {
		System.out.println(id);
		if (id == null) {
			// ҳ���һ�η��ʵ�ֵ��null,Ϊ�������ݿ��ѯ��ID����0
			id = 0;
		}
		// �õ���ǰ��¼���û���Ϣ
		SysUser u = (SysUser) sess.getAttribute("user");
		// ���ݸ�ID���Ӳ˵�
		// ���õ���ǰ��¼���û���Ϣ,���õ�ǰ��¼�û��Ľ�ɫ,���õ���ǰ��¼�û�������Ȩ��
		List<SysRight> userlist = u.getRole().getRights();
		System.out.println("111:" + userlist);
		List<SysRight> list = RightUtil.getRightByParentId(userlist, id);
		System.out.println("222:" + list);
		// List<SysRight> list = srs.queryById(id);
		// ����ȫ���ĸ��ڵ�
		List<Map<String, Object>> meunList = new ArrayList<Map<String, Object>>();
		for (SysRight s : list) {
			// ����һ�����ڵ�
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", s.getRight_code());
			map.put("text", s.getRight_text());
			if ("parent".equals(s.getRight_type())) {
				// �Ǹ��ڵ�͵�����,��Ϊ��ȥҳ��������ڵ��ʱ��������
				map.put("state", "closed");
			} else {
				// ���ӽڵ�Ͱ�����
				Map<String, Object> arr = new HashMap<String, Object>();
				arr.put("url", s.getRight_url());
				arr.put("tip", s.getRight_tip());
				map.put("arr", arr);
			}
			// �����нڵ����ӵ��ڵ㼯����
			meunList.add(map);
		}
		return meunList;
	}
}
