<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>图书信息管理</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var url;
	
	function searchBook(){
		$('#dg').datagrid('load',{
			book_name:$('#s_book_name').val()
		});
	}
	
	function deleteBook(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length==0){
			$.messager.alert("系统提示","请选择要删除的数据！");
			return;
		}
		var strIds=[];
		for(var i=0;i<selectedRows.length;i++){
			strIds.push(selectedRows[i].book_id);
		}
		var ids=strIds.join(",");
		$.messager.confirm("系统提示","您确认要删掉这<font color=red>"+selectedRows.length+"</font>条数据吗？",function(r){
			if(r){
				$.post("BookDelete",{delIds:ids},function(result){
					if(result.success){
						$.messager.alert("系统提示","您已成功删除<font color=red>"+result.delNums+"</font>条数据！");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('系统提示','<font color=red>'+selectedRows[result.errorIndex].BookName+'</font>'+result.errorMsg);
					}
				},"json");
			}
		});
	}
	
	
	function openBookAddDialog(){//打开添加添加图书信息对话框
		$("#dlg").dialog("open").dialog("setTitle","添加图书信息");
		url="bookAdd";
	}
	
	/*修改图书信息对话框  */
	function openBookModifyDialog(){
		var selectedRows=$("#dg").datagrid('getSelections');
		if(selectedRows.length!=1){
			$.messager.alert("系统提示","请选择一条要编辑的数据！");
			return;
		}
		var row=selectedRows[0];
		$("#dlg").dialog("open").dialog("setTitle","编辑图书信息");
		$("#fm").form("load",row);
		url="bookAdd?book_id="+row.id;
	}
	
	function closeBookDialog(){//下面：关闭对话框按钮
		/* 
		在视图中：点击添加跟修改：里面有一个保存按钮,
		一个关闭按钮
		*/
		
		$("#dlg").dialog("close");
		resetValue();
	}
	
	function resetValue(){//closeBookDialog()中调用
		$("#BookName").val("");
		$("#BookDesc").val("");
	}
	
	
	function saveBook(){//下面：保存按钮
		/* 
		在视图中：点击添加跟修改：里面有一个保存按钮,
		一个关闭按钮
		*/
		$("#fm").form("submit",{
			url:url,
			onSubmit:function(){
				return $(this).form("validate");
			},
			success:function(result){
				if(result.errorMsg){
					$.messager.alert("系统提示",result.errorMsg);
					return;
				}else{
					$.messager.alert("系统提示","保存成功");
					resetValue();
					$("#dlg").dialog("close");
					$("#dg").datagrid("reload");
				}
			}
		});
	}
	
	
</script>
</head>
<body style="margin: 5px;">
	<table id="dg" title="图书信息" class="easyui-datagrid" fitColumns="true"
	 pagination="true" rownumbers="true" url="bookList" fit="true" toolbar="#tb">
		<!-- url="bookList" 是controller路径 -->
		<thead>
			<tr>
				<th field="cb" checkbox="true"></th>
				<th field="book_id" width="100">图书编号</th>
				<th field="book_name" width="100">图书名称</th>
				<th field="author" width="100">作者</th>
				<th field="publish" width="100">出版社</th>
				<!-- <th title="操作"><a href="">详细</a></th> -->
			</tr>
		</thead>
	</table>
	
	<div id="tb">
		<div>
			<a href="javascript:openBookAddDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加</a>
			<a href="javascript:openBookModifyDialog()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a href="javascript:deleteBook()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
		<div>&nbsp;图书名称：&nbsp;<input type="text" name="book_name" id="s_book_name"/><a href="javascript:searchBook()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a></div>
	</div>
	
	<!-- 添加图书信息对话框 -->
	<div id="dlg" class="easyui-dialog" style="width: 400px;height: 280px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<form id="fm" method="post">
		<!-- String book_name, String author,String publish,String content,String reason -->
			<table>
				<tr>
					<td>图书名称：</td>
					<td><input type="text" name="book_name" id="bookame" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>作者：</td>
					<td><input type="text" name="author" id="author" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>出版社：</td>
					<td><input type="text" name="publish" id="publish" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td>内容：</td>
					<td><input type="text" name="content" id="content" class="easyui-validatebox" required="true"/></td>
				</tr>
				<tr>
					<td valign="top">推荐理由：</td>
					<td><textarea rows="7" cols="30" name="reason" id="reason"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="dlg-buttons">
		<a href="javascript:saveBook()" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
		<a href="javascript:closeBookDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
	</div>
</body>
</html>