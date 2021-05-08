<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML >
<html>
<head>
<base href="<%=basePath%>">

<title>高校学籍管理系统</title>
<meta charset="UTF-8">
</head>
<script type="text/javascript" src="js/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<link href="js/themes/icon.css" rel="stylesheet" type="text/css">
<link href="js/themes/default/easyui.css" rel="stylesheet"
	type="text/css">
<script type="text/JavaScript">
	$(function() {
		$("#code").click(function() {
			$(this).attr("src", "user/code?time=" + new Date());
		});
	})
</script>
<body>
	<form title="登录界面" class="easyui-window" collapsible="false"
		minimizable="false" maximizable="false" resizable="false"
		style="width:500px;height:200px;" method="POST"
		action="user/userlogin">
		<table>
			<tr>
				<td>用户名：</td>
				<td><input type="text" name="username" required="required" /></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" name="password" required="required" /></td>
			</tr>
			<tr>
				<td><input type="hidden" value="身份" /></td>
				<td><input type="hidden" checked="checked" type="radio"
					name="status" value="1" /></td>
			</tr>
			<tr>
				<td>验证码：</td>
				<td><input type="text" name="checkcode" required="required" /></td>
				<td><img id="code" src="user/code" /></td>
			</tr>
			<tr>
				<td colspan="2">${message}</td>
				<td colspan="2"><input width="100px" type="submit" value="登录" />
					<input width="100px" type="reset" value="重置" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
