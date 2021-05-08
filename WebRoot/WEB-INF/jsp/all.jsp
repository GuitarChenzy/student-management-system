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
<style type="text/css">
p::selection {
	background: blue;
}

table {
	font-size: 12px;
	table-layout: fixed;
	empty-cells: show;
	border-collapse: collapse;
	margin: 0 auto;
}

td {
	height: 30px;
}

h1, h2, h3 {
	font-size: 12px;
	margin: 0;
	padding: 0;
}

.table {
	border: 1px solid #cad9ea;
	color: #666;
}

.table th {
	background-repeat: repeat-x;
	height: 30px;
}

.table td, .table th {
	border: 1px solid #cad9ea;
	padding: 0 1em 0;
}

.table tr.alter {
	background-color: #f5fafe;
}
</style>
<script type="text/javascript">

	//查询
	function findStudent() {
		var sno = $("#snoo").val();
		if (sno == "") {
			$.messager.alert("系统提示", "学号不能为空！");
			return;
		}
		$.post("<%=basePath%>studentAll/getStudentBase",
			{
				sno : sno
			}, function(data) {
				if (data.length < 60 ) {
					$.messager.alert("系统提示", "没有学号为" + sno + "的学生！");
					return;
				}
				data = $.parseJSON(data);
				$("#base_sno").text(data[0].student.sno);
				$("#base_sname").text(data[0].student.sname);
				$("#base_ssex").text(data[0].student.ssex);
				$("#base_scard").text(data[0].student.scard);
				$("#base_stele").text(data[0].student.stele);
				$("#base_sbirth").text(data[0].student.sbirth);
				$("#base_sstudytime").text(data[0].student.sstudytime);
				$("#base_pdept").text(data[0].pdept);
				$("#base_pname").text(data[0].pname);
				$("#base_num").text(data[0].num);
			});
		var options = $("#dg").datagrid("getPager").data("pagination").options;
		var page = options.pageNumber;
		var rows = options.pageSize;
		$.post("<%=basePath%>studentAll/getAllCourses", {
			sno : sno,
			page : page,
			rows : rows
		}, function(result) {
			result = $.parseJSON(result);
			$("#dg").datagrid("loadData", {
				total : 0,
				rows : []
			});
			$("#dg").datagrid("loadData", result);
		});
	}

	//关闭窗口
	function closeStudentDialog() {
		$("#dlg").dialog("close");
		resetValue();
	}
	//添加窗口
	function openUserAddDialog() {
		var sno = $("#snoo").val();
		if (sno == "") {
			$.messager.alert("系统提示", "学号不能为空！");
			return;
		}
		$("#dlg").dialog("open").dialog("setTitle", "添加课程信息");
		$.post("<%=basePath%>studentAll/getCourses", {
			sno : sno
		},
			function(data) {
				var datalist = [];
				data = $.parseJSON(data);
				for (i in data) {
					datalist.push({
						"id" : data[i].cno,
						"text" : data[i].cname
					});
				}
				$("#cno").combobox({
					data : datalist,
					valueField : 'id',
					textField : 'text'
				});
			}
		);
		$("#sno").val(sno);
		url = "<%=basePath%>studentAll/saveCourse";
	}

	//重置
	function resetValue() {
		$("#sno").val("");
		$("#sname").val("");
		$("#cno").val("");
		$("#cname").val("");
		$("#grade").val("");
		$("#psenior").val("");
	}

	//删除学生信息
	function deleteStudent() {
		var selectedRows = $("#dg").datagrid("getSelections");
		if (selectedRows.length == 0) {
			$.messager.alert("系统提示", "请选择要删除的学生信息！");
			return;
		}
		var sno = selectedRows[0].stuCourse.sno;
		var cno = selectedRows[0].stuCourse.cno;
		var psenior = selectedRows[0].stuCourse.psenior;
		$.messager.confirm("系统提示", "您确定要删除这<font color=red>" +
			selectedRows.length + "</font>条数据吗？", function(r) {
				if (r) {
					$.post("<%=basePath%>studentAll/deleteCourse", {
						sno : sno,
						cno : cno,
						psenior : psenior
					}, function(result) {
						if (result.length > 4) {
							$.messager.alert("系统提示", "数据已删除成功！");
							var sno = $("#snoo").val();
							var options = $("#dg").datagrid("getPager").data("pagination").options;
							var page = options.pageNumber;
							var rows = options.pageSize;
							$.post("<%=basePath%>studentAll/getAllCourses", {
								sno : sno,
								page : page,
								rows : rows
							}, function(result) {
								result = $.parseJSON(result);
								$("#dg").datagrid("loadData", {
									total : 0,
									rows : []
								});
								$("#dg").datagrid("loadData", result);
							});
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
				if ($("#sno").val() == "" || $("#sname").val() == "" ||
					$("#scard").val() == "" || $("#tele").val == "") {
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
					var sno = $("#snoo").val();
					var options = $("#dg").datagrid("getPager").data("pagination").options;
					var page = options.pageNumber;
					var rows = options.pageSize;
					$.post("<%=basePath%>studentAll/getAllCourses", {
						sno : sno,
						page : page,
						rows : rows
					}, function(result) {
						result = $.parseJSON(result);
						$("#dg").datagrid("loadData", {
							total : 0,
							rows : []
						});
						$("#dg").datagrid("loadData", result);
					});
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
	<div class="easyui-panel" title="学生详细信息" iconCls="icon-search"
		collapsible="true" style="width:100%;height:250px;">
		用户名： <input type="text" id="snoo" size="20"
			onkeydown="if(event.keyCode == 12)findStudent()" /> <a href="#"
			class="easyui-linkbutton" iconCls="icon-search"
			onclick="javascript:findStudent()" plain="true">查询</a>
		<div class="easyui-layout" fit="true">
			<div region="west" class="p-search" width="70%">
				<table class="table">
					<tr>
						<th><h2>学生综合信息</h2></th>
					</tr>
					<tr>
						<td>学号：</td>
						<td><p id="base_sno"></p></td>
						<td>姓名：</td>
						<td><p id="base_sname"></p></td>
						<td>性别：</td>
						<td><p id="base_ssex"></p></td>
					</tr>
					<tr>
						<td>身份证号：</td>
						<td><p id="base_scard"></p></td>
						<td>电话号码：</td>
						<td><p id="base_stele"></p></td>
					</tr>
					<tr>
						<td>出生年月：</td>
						<td><p id="base_sbirth"></p></td>
						<td>入学时间：</td>
						<td><p id="base_sstudytime"></p></td>
					</tr>
					<tr>
						<td>所在学院：</td>
						<td><p id="base_pdept"></p></td>
						<td>所在专业：</td>
						<td><p id="base_pname"></p></td>
						<td>选修课程数：</td>
						<td><p id="base_num"></p></td>
					</tr>
				</table>
			</div>
			<div region="center" height="100%" width="30%">
				<img alt="这是头像" src="<%=basePath%>file/fileUploadPage" width="100px" height="100px">
				<form:form action="../file/fileUploadPage" method="POST"
					modelAttribute="fileUpload" enctype="multipart/form-data">
      			请选择一个文件上传 : 
      			<br>
					<input type="file" name="file" />
					<br>
					<input type="submit" value="提交上传" />
					<br>
			</form:form>
			${applicationScope.imgcode}
			</div>
		</div>
	</div>
	<table class="easyui-datagrid" id="dg" title="课程管理"
		style="width:100%;height:370px" toolbar="#toolbar" idField="id"
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
					data-options="field:'sno',formatter:function(value,row){return row.stuCourse.sno;}"
					width="10" align="center">学号</th>
				<th data-options="field:'sname'" width="10" align="center">姓名</th>
				<th
					data-options="field:'cno',formatter:function(value,row){return row.cnameAndCnoDTO.cno;}"
					width="10" align="center">课程号</th>
				<th
					data-options="field:'cname',formatter:function(value,row){return row.cnameAndCnoDTO.cname;}"
					width="10" align="center">课程名称</th>
				<th
					data-options="field:'grade',formatter:function(value,row){return row.stuCourse.grade;}"
					width="10" align="center">课程分数</th>
				<th
					data-options="field:'psenior',formatter:function(value,row){return row.stuCourse.psenior;}"
					width="10" align="center">学年</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<div id="toolbar">
		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="javascript:openUserAddDialog()">添加</a> <a href="#"
			class="easyui-linkbutton" iconCls="icon-remove" plain="true"
			onclick="javascript:deleteStudent()">删除</a>
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
					<td>学年</td>
					<td><input id="sno" name="sno" class="easyui-validatebox"
						style="width:200px;" readonly="readonly"></input><span
						style="color: red">*</span></td>
					<td></td>
				</tr>
				<tr>
					<td>可选课程</td>
					<td><select id="cno" name="cno" class="easyui-combobox"
						style="width:200px;"></select><span style="color: red">*</span></td>
					<td></td>
				</tr>
				<tr>
					<td>成绩</td>
					<td><input id="grade" name="grade" class="easyui-validatebox"
						style="width:200px;"></input><span style="color: red">*</span></td>
					<td></td>
				</tr>
				<tr>
					<td>学年</td>
					<td><input id="psenior" name="psenior"
						class="easyui-validatebox" style="width:200px;"></input><span
						style="color: red">*</span></td>
					<td></td>
				</tr>
			</table>
		</form>
	</div>

</body>
</html>
