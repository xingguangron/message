<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrapValidator.min.css" />



<script type="text/javascript">
saveBill = function() {
alert("操作成功！");
}

 function checkname()
            {
                var div = document.getElementById("div1");//获取旁边的div
                div.innerHTML = "";
                var name1 = document.form1.name.value;
                if (name1 == "") {
                    div.innerHTML = "姓名不能为空！";
                    document.form1.text1.focus();
                    return false;
                }
                if (name1.length < 3 || name1.length > 16) {
                    div.innerHTML = "姓名输入的长度3-16个字符！";
                    document.form1.text1.select();
                    return false;
                }
                var charname1 = name1.toLowerCase();
                for (var i = 0; i < name1.length; i++) {
                    var charname = charname1.charAt(i);
                    if (!(charname >= 0 && charname <= 9) && (!(charname >= 'a' && charname <= 'z')) && (charname != '_')) {
                        div.innerHTML = "姓名包含非法字母，只能包含字母，数字，和下划线";
                        document.form1.text1.select();
                        return false;
                    }
                }
                return true;
            }


function checkSubmitMobil() { 
 var div = document.getElementById("divphone");//获取旁边的div
                div.innerHTML = "";
if ($("#mobile").val() == "") { 
  div.innerHTML = "手机号不能为空！";

$("#mobile").focus(); 
return false; 
} 

if (!$("#mobile").val().match(/^(1\d{10})$/)) { 
  div.innerHTML = " 手机格式输入不正确！"; 

$("#mobile").focus(); 
return false; 
} 
return true; 
}	
			function vailEmail(){
             var email = jQuery("#email").val();
             var flag = false;
             var message = "";
             var myreg = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;  
             if(email ==''){
                 message = "邮箱不能为空！";
             }else if(!myreg.test(email)){
                 message = "请输入有效的邮箱地址！";
             }else{
                 flag = true;
             }
           
             return flag;
         }
			
			function validate() {
	 
              var pwd1 = $("#p1").val();//
              var pwd2 =$("#p2").val();
              if(pwd1 == pwd2) {
                  document.getElementById("tishi").innerHTML="<font color='green'>两次密码相同</font>";
                  document.getElementById("submit").disabled = false;
             // alert("1");
			  }
              else {
                  document.getElementById("tishi").innerHTML="<font color='red'>两次密码不相同</font>";
                document.getElementById("submit").disabled = true;
            
			 }
			 // alert("22");
          }
		
</script>
</head>
<body style="padding: 20px;">
	<form role="form" name="form1" class="form-horizontal" method="post"
		action="">
		<div class="form-group">
			<label class="col-sm-2 control-label" for="name">姓名：</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="name" name="name"
					placeholder="姓名" onblur="checkname()"></input>
				<div id="div1" color='red'>
					<!--bootstrap面板 class="alert alert-danger" -->
				</div>
			</div>
		</div>


		<div class="form-group">
			<label class="col-sm-2 control-label" for="mobile">手机</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="mobile" name="mobile"
					placeholder="手机号码" onblur="checkSubmitMobil()"></input>
				<div id="divphone" color='red'>
					<!--bootstrap面板 class="alert alert-danger" -->
				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="insured_rel_2">email</label>
			<div class="col-sm-4">
				<input type="text" class="form-control" id="email" name="email"
					placeholder="邮箱" onblur="vailEmail()"></input>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label" for="p1">密码</label>
			<div class="col-sm-4">
				<input type="password" class="form-control" id="p1" name="password"
					placeholder="密码"></input>
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label" for="p2">确认密码</label>
			<div class="col-sm-4">
				<input type="password" class="form-control" id="p2" name="password2"
					placeholder="再次输入" onkeyup="validate()"></input>
			</div>
		</div>


		<div class="col-xs-6 link">
			<p class="text-center remove-margin">
				<span id="tishi"></span>
			</p>

			<br />
			<div class="form-group" style="margin-bottom: 10%">
				<div class="col-xs-4 col-xs-offset-4 ">
					<button type="submit" id="submit" class="btn btn-sm btn-info">修改</button>
					<button type="reset" class="btn btn-sm btn-info">重置</button>

				</div>
			</div>
	</form>
	<script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/bootstrapValidator.min.js"></script>



</body>
</html>


