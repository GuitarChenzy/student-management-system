<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML >
<html>
<head>
<meta http-equiv="Content-type" content="text/html" charset="UTF-8">
<title>Student Mangenment</title>
<script type="text/javascript"
	src="<%=basePath%>js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/jquery.easyui.min.js"></script>
<link href="<%=basePath%>js/themes/icon.css" rel="stylesheet"
	type="text/css">
<link href="<%=basePath%>js/themes/default/easyui.css" rel="stylesheet"
	type="text/css">
<style type="text/css">
#menu {
	width: 60px;
	/*border:1px solid red;*/
}

#menu ul {
	list-style: none;
	padding: 0px;
	margin: 0px;
}

#menu ul li {
	border-bottom: 1px solid #fff;
}

#menu ul li a {
	/*先将a标签转换为块级元素，才能设置宽和内间距*/
	display: block;
	background-color: #00a6ac;
	color: #fff;
	padding: 5px;
	text-decoration: none;
}

#menu ul li a:hover {
	background-color: #008792;
}
</style>
<script type="text/javascript">

	$(function() {
		$('#bodylayout').layout();
		$("a[title]").click(function() {
			var text = $(this).text();
			var href = null;
			href = $(this).attr("title");
			//判断当前右边是否已有相应的tab  
			if ($("#tt").tabs("exists", text)) {
				$("#tt").tabs("select", text);
			}
			else if (href == "student") {
				//如果没有则创建一个新的tab，否则切换到当前tag  
				var src = "<%=basePath%>student/openstudent";
				$("#tt").tabs("add", {
					title : text,
					closable : true,
					content : '<iframe src= ' + src + ' frameborder="0" width="100%" height="100%" />'
				}
				);
				href = null;
			}
			else if (href == "course") {
				var src = "<%=basePath%>course/opencourse";
				$("#tt").tabs("add", {
					title : text,
					closable : true,
					content : '<iframe src= ' + src + ' frameborder="0" width="100%" height="100%" />'
				}
				);
				href = null;
			}
			else if (href == "major") {
				var src = "<%=basePath%>major/openmajor";
				$("#tt").tabs("add", {
					title : text,
					closable : true,
					content : '<iframe src= ' + src + ' frameborder="0" width="100%" height="100%" />'
				}
				);
				href = null;
			}
			else if (href == "study") {
				var src = "<%=basePath%>study/openstudy";
				$("#tt").tabs("add", {
					title : text,
					closable : true,
					content : '<iframe src= ' + src + ' frameborder="0" width="100%" height="100%" />'
				}
				);
				href = null;
			}
			else {
				var src = "<%=basePath%>user/openall";
				$("#tt").tabs("add", {
					title : text,
					closable : true,
					content : '<iframe src= ' + src + ' frameborder="0" width="100%" height="100%" />'
				}
				);
				href = null;
			}
		}
		);
	})
</script>
</head>
<body id="bodylayout">
	<div style="background-color: #AFEEEE ;height:100px" data-options="region:'north',title:'首页'" >
		<h2 align="center">高校学籍管理系统</h2>
		<div style="float: right">
			欢迎您！${username}<a href="<%=basePath%>user/quit">注销</a>
		</div>
	</div>
	<div data-options="region:'west',title:'导航菜单栏'" style="width:210px;">
		<div id="menu" class="easyui-accordion" width="100%">
			<div title="学生管理" data-options="iconCls:'icon-folder'"
				style="overflow:auto;padding:10px;">
				<ul>
					<li><a href="#" title="student">学生基本信息管理</a></li>
					<li><a href="#" title="study">学生就读信息管理</a></li>
					<li><a href="#" title="course">课程信息管理</a></li>
					<li><a href="#" title="major">专业信息管理</a></li>
					<li><a href="#" title="all">学生详细信息管理</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div data-options="region:'center',title:'操作栏'" style="padding:1px;">
		<div id="tt" class="easyui-tabs" data-options="fit:true" title="首页"
			width="500px"></div>
	</div>
	<!-- 	<div
		data-options="region:'east',iconCls:'icon-reload',title:'学生文件上传',split:true"
		style="width:150px;">
		<div class="easyui-calendar" style="width:100%;height:250px;"></div>
	</div> -->
	<div data-options="region:'south',title:'底框',split:false"
		style="height:75px;">
		<p align="center">学籍管理系统 @author:陈泽宇</p>
	</div>

</body>
</html>
