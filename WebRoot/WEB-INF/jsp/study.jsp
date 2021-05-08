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
<script type="text/javascript">
	//查询学生
	function findStudent() {
		var sno = $("#snoo").val();
		if (sno == "" || sno == null) {
			alert("查询学号不能为空！");
			return;
		}
		var options = $("#dg").datagrid("getPager").data("pagination").options;
		var page = options.pageNumber;
		var rows = options.pageSize;
		$.post("<%=basePath%>study/getAllBySno", {
			sno : sno,
			page : page,
			rows : rows
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
		url = "<%=basePath%>study/save";
	}

	//修改窗口
	function openStudentModifyDialog() {
		var selectedRows = $("#dg").datagrid("getSelections");
		if (selectedRows.length != 1) {
			$.messager.alert("系统提示", "请选择一条要修改的信息！");
			return;
		}
		var row = selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle", "编辑课程信息");
		$("#fm").form("load", row);
		url = "<%=basePath%>study/update";
	}

	//重置
	function resetValue() {
		$("#sno").val("");
		$("#register").val("");
		$("#graduate").val("");
		$("#studying").val("");
		$("#remain").val("");
		$("#term").val("");
	}

	//删除学生信息
	function deleteStudent() {
		var selectedRows = $("#dg").datagrid("getSelections");
		if (selectedRows.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的课程信息！");
			return;
		}
		var sno = selectedRows[0].sno; //这里由于Course是个对象，所以必须这样才能拿到cno
		var term = selectedRows[0].term;
		$.messager.confirm("系统提示", "您确定要删除这<font color=red>" +
			selectedRows.length + "</font>条数据吗？", function(r) {
				if (r) {
					$.post("<%=basePath%>study/deleteStudy", {
						sno : sno,
						term : term
					}, function(result) {
						if (result.length > 4) {
							$.messager.alert("系统提示", "数据已删除成功！");
							if($("#snoo").val()>0){
							var sno = $("#snoo").val();
					var options = $("#dg").datagrid("getPager").data("pagination").options;
					var page = options.pageNumber;
					var rows = options.pageSize;
					$.post("<%=basePath%>study/getAllBySno", {
						sno : sno,
						page : page,
						rows : rows
					},
						function(result) {
								$("#dg").datagrid("loadData", {
									total : 0,
									rows : []
								});
								var data = $.parseJSON(result); //这里一定要把json字符串转成json格式的数据
								$("#dg").datagrid("loadData", data);
							}
						);
						}else{
							$("#dg").datagrid("reload");
						}}
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
				if ($("#sno").val() == "" || $("#register").val() == "" ||
					$("#graduate").val() == "" || $("#studying").val() == ""
					|| $("#remain").val() == "" || $("#term").val() == "") {
					$.messager.alert("系统提示", "请填写完整信息！");
					return false;
				}
				return $(this).form("validate");
			},
			success : function(result) {
				if (result.length > 5) {
					$.messager.alert("系统提示", "保存成功！");
					resetValue();
					$("#dlg").dialog("close");
					if($("#snoo").val().length>0){
					var sno = $("#snoo").val();
					var options = $("#dg").datagrid("getPager").data("pagination").options;
					var page = options.pageNumber;
					var rows = options.pageSize;
					$.post("<%=basePath%>study/getAllBySno", {
						sno : sno,
						page : page,
						rows : rows
					},
						function(result) {
								$("#dg").datagrid("loadData", {
									total : 0,
									rows : []
								});
								var data = $.parseJSON(result); //这里一定要把json字符串转成json格式的数据
								$("#dg").datagrid("loadData", data);
							}
						);
				}else{
					$("#dg").datagrid("reload");
				}}
				else {
					$.messager.alert("系统提示", "保存失败！");
					return;
				}
			}
		});
	}
</script>
</head>
<body>
	<div>
		学号： <input type="text" id="snoo" size="20"
			onkeydown="if(event.keyCode == 4)findStudent()" /> <a
			href="javascript:findStudent()" class="easyui-linkbutton"
			iconCls="icon-search" plain="true">查询</a>
	</div>
	<table class="easyui-datagrid" id="dg" title="学籍管理"
		style="width:100%;height:370px" toolbar="#toolbar" idField="id"
		url="<%=basePath%>study/getAll"
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
				<th data-options="field:'sno'" width="10" align="center">学号</th>
				<th data-options="field:'register'" width="10" align="center">是否注册</th>
				<th data-options="field:'graduate'" width="10" align="center">是否毕业</th>
				<th data-options="field:'studying'" width="10" align="center">是否肆业</th>
				<th data-options="field:'remain'" width="10" align="center">是否休学</th>
				<th data-options="field:'term'" width="10" align="center">学期</th>
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
			onclick="javascript:openStudentModifyDialog()">修改</a> <a href="<%=basePath%>study/getStudyExcel"
			class="easyui-linkbutton" iconCls="icon-print" plain="true"
			>生成报表</a>
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
					<td>学号</td>
					<td><input type="text" id="sno" name="sno"
						class="easyui-validatebox" required="true" /> <span
						style="color: red">*</span></td>
					<td></td>
					<td>是否注册</td>
					<td><input type="text" id="register" name="register"
						class="easyui-validatebox" required="true" /> <span
						style="color: red">*</span></td>
				</tr>
				<tr>
					<td>是否毕业</td>
					<td><input type="text" id="graduate" name="graduate"
						class="easyui-validatebox" required="true" /> <span
						style="color: red">*</span></td>
					<td></td>
				</tr>
				<tr>
					<td>是否肆业</td>
					<td><input type="text" id="studying" name="studying"
						class="easyui-validatebox" required="true" /> <span
						style="color: red">*</span></td>
					<td></td>
				</tr>
				<tr>
					<td>是否休学</td>
					<td><input type="text" id="remain" name="remain"
						class="easyui-validatebox" required="true" /> <span
						style="color: red">*</span></td>
					<td></td>
				</tr>
				<tr>
					<td>学年</td>
					<td><input type="text" id="term" name="term"
						class="easyui-validatebox" required="true" /> <span
						style="color: red">*</span></td>
					<td></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
