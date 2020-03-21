<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../easyui/themes/default/easyui.css" />
<link rel="stylesheet" href="../easyui/themes/icon.css" />
<script type="text/javascript" src="../easyui/jquery.min.js"></script>
<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	$(function() {
		$("#tt").tree(
				{
					//url : 'sysRightController/query'
					//url : 'sysRightController/query2',
					url : '../sysRightController/query3',
					lines : true,
					animate : true,
					formatter : function(node) {
						//console.log(node);
						if (node.state == "open") {
							return '<a href="javascript:addTabs(\'' + node.text
									+ '\',\'' + node.arr.url + '\')">'
									+ node.text + "</a>";
						}
						return node.text;
					}
				});
	})
	//追加标签页
	function addTabs(name, url) {
		//标记法
		var num = 0;
		//得到所有的面板[是一个数组接收]
		var ts = $('#tt2').tabs('tabs');
		for(var i = 0;i < ts.length;i++){
			t = ts[i];
			//得到一个面板对象,从面板对象上得到指定的数据
			var title = t.panel("options").title;
			console.log(title);
			//判断面板和标签是否有相同的
			if(title==name){
				num = 1;
				break;
			}
		}
		//判断是否存在
		if(num>0){
			//如果存在就选中
			$('#tt2').tabs('select',name);
		}else{
			//如果不存在就添加面板
			$('#tt2').tabs('add',{
				title:name,
				content:"<iframe src='"+url+"' style='width:100%;height:100%;'></iframe>",
				closable:true
			})
		}
	}
</script>
</head>

<body class="easyui-layout">
	<div data-options="region:'north',title:'欢迎'" style="height: 100px;">
		${user.u_name}--${user.role.role_name}
	</div>
	<div data-options="region:'west',title:'菜单'" style="width: 200px;">
		<ul id="tt"></ul>
	</div>
	<div data-options="region:'center'">
		<div id="tt2" class="easyui-tabs" style="width: 100%; height: 100%;">
			<div title="首页" style="padding: 20px; display: none;">欢迎</div>
		</div>
	</div>
</body>

</html>