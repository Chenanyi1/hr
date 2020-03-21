<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="../../easyui/themes/icon.css" />
<script type="text/javascript" src="../../easyui/jquery.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../../easyui/easyui-lang-zh_CN.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {

		$('#dg')
				.datagrid(
						{
							url : "../../userController/queryUser",
							fitColumns : true, //自适应(铺满页面)
							pagination : true, //开启分页
							pageNumber : 1, //第几页开始
							pageSize : 3, //每页显示几条
							pageList : [ 1, 2, 3, 4, 5 ], //每页可以显示的条数
							sortName : "u_id",
							sortOrder : "asc",
							toolbar : [ {
								iconCls : 'icon-add',
								text : '添加用户',
								handler : function() {
									//初始化角色下拉列表
									$("#roleselectd").combobox({
										url : "../../sysRoleController/query",
										valueField : 'role_id',
										textField : 'role_name',
										loadFilter : function(data) {
											//console.log(data);
											var arr = {};
											arr.role_id = "-1";
											arr.role_name = "--请选择--";
											//selected=true表示默认选中
											arr.selected = true;
											//第一个参数是插入的下标,第二个参数是是否替换,0表示不替换,第三个参数是插入的数据
											data.splice(0, 0, arr);
											return data;
										}
									})
									//打开添加模态框
									$("#insertDiv").dialog("open");
								}
							} ],
							columns : [ [
									{
										field : "u_id",
										title : "用户编号",
										width : 100
									},
									{
										field : "u_name",
										title : "用户名称",
										width : 100
									},
									{
										field : "u_true_name",
										title : "真实姓名",
										width : 100
									},
									{
										field : "role",
										title : "用户身份",
										width : 100,
										formatter : function(value, row, index) {
											return value.role_name;
										}
									},
									{
										field : "操作",
										title : "操作",
										width : 100,
										formatter : function(value, row, index) {
											var id = row.usr_id;
											//alert(row.u_name+row.u_true_name+row.u_password+row.role.role_id);
											return "<a href='" + id + "'>删除</a> <a href=\"javascript:updateUser('"
													+ row.u_name
													+ "','"
													+ row.u_true_name
													+ "','"
													+ row.u_password
													+ "','"
													+ row.role.role_id
													+ "')\">修改</a>";
										}
									} ] ]
						})
		//初始化表单
		$("#insertUser").form({
			url : "../../userController/insertUser",
			success : function(data) {
				//data就是服务器返回的信息  
				//alert(data);
				if (data == 1) {
					alert("添加成功！");
					//重置表单
					$('#insertUser').form("reset");
					//关闭模态框
					$("#insertDiv").dialog("close");
					//刷新页面
					location.reload();
				}
			}
		})
	})
	//提交用户信息
	function insertUser() {
		$('#insertUser').submit();
	}
	//关闭添加模态框
	function resetUserForm() {
		//重置表单
		$("#insertUser").form("reset");
		//关闭模态框
		$("#insertDiv").dialog("close");
	}
	//修改模态框
	function updateUser(name, truename, pwd, roleid) {
		//alert(name+truename+pwd+roleid);
		$("#u_name").textbox({
			value : name
		})
		$("#u_password").textbox({
			value : pwd
		})
		$("#u_true_name").textbox({
			value : truename
		})

		//打开修改模态框
		$("#updateDiv").dialog("open");
		//初始化角色下拉列表
		$("#roleselectd2").combobox({
			url : "../../sysRoleController/query",
			valueField : 'role_id',
			textField : 'role_name',
			loadFilter : function(data) {
				for (var i = 0; i < data.length; i++) {
					if (data[i].role_id == roleid) {
						data[i].selected = true;
						break;
					}
				}
				return data;
			}
		})
	}
	//关闭修改模态框
	function resetUserForm2() {
		//重置表单
		$("#updateUser").form("reset");
		//关闭模态框
		$("#updateDiv").dialog("close");
	}
	//重置密码
	function restPWD(){
		$("#u_password").textbox({
			value :"123456"
		})
	}
</script>
</head>

<body>
	<table id="dg"></table>
	<div id="updateDiv" class="easyui-dialog" title="修改"
		style="width: 600px; height: 400px;"
		data-options="closable:false,top:30,draggable:false,iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{text:'保存',handler:function (){updateUser()}},{text:'取消',handler:function (){resetUserForm2()}}]">
		<form style="margin-top: 20px;" id="updateUser" class="easyui-form"
			method="post">
			<div style="margin-bottom: 20px; margin-left: 30px;">
				<input id="u_name" class="easyui-textbox" style="width: 400px;"
					data-options="label:'用户名称:',required:true" name="u_name" />
			</div>
			<div style="margin-bottom: 20px; margin-left: 30px;">
				<input id="u_password" class="easyui-textbox" readonly="readonly"
					value="123456" style="width: 400px;"
					data-options="label:'用户密码:',required:true" name="u_password" />
					<a href="javascript:restPWD()">重置密码</a>
			</div>
			<div style="margin-bottom: 20px; margin-left: 30px;">
				<input id="u_true_name" class="easyui-textbox" style="width: 400px;"
					data-options="label:'真实姓名:',required:true" name="u_true_name" />
			</div>
			<div style="margin-bottom: 20px; margin-left: 30px;">
				<input id="roleselectd2" style="width: 400px;"
					data-options="label:'用户身份:'" name="role.role_id" />
			</div>
		</form>
	</div>
	<div id="insertDiv" class="easyui-dialog" title="添加"
		style="width: 600px; height: 400px;"
		data-options="closable:false,top:30,draggable:false,iconCls:'icon-save',resizable:true,modal:true,closed:true,buttons:[{text:'保存',handler:function (){insertUser()}},{text:'取消',handler:function (){resetUserForm()}}]">
		<form style="margin-top: 20px;" id="insertUser" class="easyui-form"
			method="post">
			<div style="margin-bottom: 20px; margin-left: 30px;">
				<input class="easyui-textbox" style="width: 400px;"
					data-options="label:'用户名称:',required:true" name="u_name" />
			</div>
			<div style="margin-bottom: 20px; margin-left: 30px;">
				<input class="easyui-textbox" readonly="readonly" value="123456"
					style="width: 400px;" data-options="label:'用户密码:',required:true"
					name="u_password" />
			</div>
			<div style="margin-bottom: 20px; margin-left: 30px;">
				<input class="easyui-textbox" style="width: 400px;"
					data-options="label:'真实姓名:',required:true" name="u_true_name" />
			</div>
			<div style="margin-bottom: 20px; margin-left: 30px;">
				<input id="roleselectd" style="width: 400px;"
					data-options="label:'用户身份:'" name="role.role_id" />
			</div>
		</form>
	</div>
</body>

</html>