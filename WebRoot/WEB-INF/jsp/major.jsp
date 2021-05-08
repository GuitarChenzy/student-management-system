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
</head>
<script type="text/javascript">
	//查询学生
	function findStudent() {
		var sno = $("#pnoo").val();
		if (sno == "" || sno == null) {
			alert("查询课程号不能为空！");
			return;
		}
		$.post("<%=basePath%>major/getmajor", {
			pno : sno
		},
			function(result) {
				if (result.length < 30) {
					alert("没有课程号为:" + sno + "的课程");
					return;
				}
				else {
					$("#dg").datagrid("loadData", {
						total : 0,
						rows : []
					});
					var data = $.parseJSON(result); //这里一定要把json字符串转成json格式的数据
					$("#dg").datagrid("loadData", data);
				}
			});
	}

	//查找学生
	function findStudentAll() {
		$("#dg").datagrid("reload");
	}

	//关闭窗口
	function closeStudentDialog() {
		$("#dlg").dialog("close");
		resetValue();
	}

	//添加窗口
	function openUserAddDialog() {
		$("#dlg").dialog("open").dialog("setTitle", "添加用户信息");
		url = "<%=basePath%>major/save";
	}

	//修改窗口
	function openStudentModifyDialog() {
		var selectedRows = $("#dg").datagrid("getSelections");
		if (selectedRows.length != 1) {
			$.messager.alert("系统提示", "请选择一条要修改的信息！");
			return;
		}
		var row = selectedRows[0].major;
		$("#dlg").dialog("open").dialog("setTitle", "编辑课程信息");
		$("#fm").form("load", row);
		url = "<%=basePath%>major/update";
	}

	//重置
	function resetValue() {
		$("#pno").val("");
		$("#pname").val("");
		$("#pdept").val("");
	}

	//删除学生信息
	function deleteStudent() {
		var selectedRows = $("#dg").datagrid("getSelections");
		if (selectedRows.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的课程信息！");
			return;
		}
		var sno = selectedRows[0].major.pno; //这里由于Course是个对象，所以必须这样才能拿到cno
		$.messager.confirm("系统提示", "您确定要删除这<font color=red>" +
			selectedRows.length + "</font>条数据吗？", function(r) {
				if (r) {
					$.post("<%=basePath%>major/deleteMajor", {
						pno : sno
					}, function(result) {
						if (result.length > 4) {
							$.messager.alert("系统提示", "数据已删除成功！");
							$("#dg").datagrid("reload");
						}
						else {
							$.messager.alert("系统提示", "数据删除失败！");
						}
					});
				}
			});
	}

	//增加学生信息
	function saveStudent() {
		$("#fm").form("submit", {
			url : url,
			onSubmit : function() {
				if ($("#pno").val() == "" || $("#pname").val() == "" ||
					$("#pdept").val() == "") {
					$.messager.alert("系统提示", "请填写完整信息！");
					return false;
				}
				return $(this).form("validate");
			},
			success : function(result) {
				if (result.length > 4) {
					$.messager.alert("系统提示", "保存成功！");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}
				else {
					$.messager.alert("系统提示", "保存失败！");
					return;
				}
			}
		});
	}
</script>
<body>
	<div>
		专业编号： <input type="text" id="pnoo" size="20"
			onkeydown="if(event.keyCode == 4)findStudent()" /> <a
			href="javascript:findStudent()" class="easyui-linkbutton"
			iconCls="icon-search" plain="true">查询</a>
	</div>
	<table class="easyui-datagrid" id="dg" title="学籍管理"
		style="width:100%;height:370px" toolbar="#toolbar" idField="id"
		url="<%=basePath%>major/getAll"
		data-options="
				fitColumns:true, 
                rownumbers:true,
                singleSelect:true,
                autoRowHeight:true,
                pagination:true,
				pageNumber : 1,
				pageList : [ 5, 10, 15, 20, 25 ],
				beforePageText : '第',   
				afterPageText : '页    共 {pages} 页',
				displayMsg : '当前显示 {from} - {to} 条记录   共 {total} 条记录',
                pageSize:10">
		<thead>
			<tr>
				<th
					data-options="field:'pno',formatter:function(value,row){
return row.major.pno;
}"
					width="10" align="center">专业编号</th>
				<th
					data-options="field:'cname',formatter:function(value,row){
return row.major.pname;
}"
					width="10" align="center">专业名称</th>
				<th
					data-options="field:'cperiod',formatter:function(value,row){
return row.major.pdept;
}"
					width="10" align="center">所在院系</th>
				<th data-options="field:'num'" width="10" align="center">专业总人数</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-search"
			onclick="javascript:findStudentAll()" plain="true">查询</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="javascript:openUserAddDialog()">添加</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-remove" plain="true"
			onclick="javascript:deleteStudent()">删除</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-save" plain="true"
			onclick="javascript:openStudentModifyDialog()">修改</a> <a
			href="<%=basePath%>major/getMajorsExcel" class="easyui-linkbutton"
			iconCls="icon-print" plain="true">生成报表</a>
	</div>

	<div id="dlg-buttons">
		<a href="javascript:saveStudent()" class="easyui-linkbutton"
			iconCls="icon-ok">保存</a> <a href="javascript:closeStudentDialog()"
			class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>

	<div id="dlg" class="easyui-dialog"
		style="width: 730px;height:280px;padding:10px 10px;" modal="true"  closed="true"
		buttons="#dlg-buttons">


		<form method="post" id="fm">
			<table cellspacing="8px;">
				<tr>
					<td>专业编号</td>
					<td><input type="text" id="pno" name="pno"
						class="easyui-validatebox" required="true" /> <span
						style="color: red">*</span></td>
					<td></td>
					<td>专业名称</td>
					<td><input type="text" id="pname" name="pname"
						class="easyui-validatebox" required="true" /> <span
						style="color: red">*</span></td>
				</tr>
				<tr>
					<td>专业所在院系</td>
					<td><input type="text" id="pdept" name="pdept"
						class="easyui-validatebox" required="true" /> <span
						style="color: red">*</span></td>
					<td></td>
			</table>
		</form>
	</div>
</body>
</html>
