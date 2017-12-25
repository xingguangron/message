<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
<!--<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>-->
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	

  <link href="css/bootstrap.min.css" rel="stylesheet">
	<link href="css/bootstrapValidator.min.css" rel="stylesheet" >
		<title>修改密码</title>
		
		
		<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrapValidator.min.js"></script>
	

<script type="text/javascript">
		
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
}
		</script>

		</head>
		
	<body style="padding: 20px;"> 
	
	<div  id="ForgetPassword"  >
         <div class="loginDiv" align="center">
                <form name="Login" id="Login" action="changPassword" method="post" >
                    <div class="form-group">
                        <div class="input-group col-sm-4">
                            <span class="input-group-addon">旧密码&nbsp&nbsp&nbsp</span>
                            <input name="oldpassword" type="password"    class="form-control" placeholder="旧密码" >
                            <!--<span class="help-block" id="UsernameMessage" /> -->
                        </div><br />
                    </div><!-- /form-group-->
                    <div class="form-group">
                        <div class="input-group col-sm-4">
                            <span class="input-group-addon">新密码&nbsp&nbsp&nbsp</span>
                            <input name="password1" type="Password" id="p1"   class="form-control"     placeholder="新密码">
                            <!--<span class="help-block" id="PasswordMessage" /> -->
                        </div>
                    </div>
					
					<div class="form-group">
                        <div class="input-group col-sm-4">
                            <span class="input-group-addon">再次输入</span>
                            <input name="password2" type="Password" id="p2" class="form-control"  placeholder="新密码"  onkeyup="validate()">
                        
                        </div>
                    </div><!-- /form-group-->
					
                    <span>请将如下计算结果填入文本框内：</span> <span id="tishi"></span>
					<br /><br/>
                    <div class="form-group">
                        <div class="input-group col-sm-4">
                            <span class="input-group-addon" id="captchaOperation"></span>
                            <input name="captcha" type="text" class="form-control" placeholder="验证码" >
                           
                        </div><br />
                    </div>
                    <br />
                    <div class="form-group" style="margin-bottom: 10%">
                        <div class="col-xs-4 col-xs-offset-4 ">
                            <button type="submit" id="submit"   class="btn btn-sm btn-info"  >修改</button>
                       <button type="reset" class="btn btn-sm btn-info">重置</button>
						 
						</div>
                    </div>
                </form>
				
            </div>
		</div>
	
	
	

	
<script type="text/javascript">
    $(document).ready(function() {

        function randomNumber(min, max) {
            return Math.floor(Math.random() * (max - min + 1) + min);
        };
        function generateCaptcha2() {
            $('#captchaOperation').html([randomNumber(1, 50), '+', randomNumber(1, 50), '='].join(' '));
        };
        generateCaptcha2();
        $('#Login')
                .bootstrapValidator({

                    fields: {
                        password1: {
                            message: 'The username is not valid',
                            validators: {
                                notEmpty: {
                                    message: '密码不能为空'
                                },
                                stringLength: {
                                    min: 3,
                                    max: 10,
                                    message: '密码长度在3到10'
                                },
                             
                               
                            }
                        },
                        password2: {
                            validators: {
                                notEmpty: {
                                    message: '密码不能为空'
                                }
                            }
                        },
                        captcha: {
                            validators: {
                                callback: {
                                    message: '验证码错误',
                                    callback: function(value, validator) {
                                        var items = $('#captchaOperation').html().split(' '), sum = parseInt(items[0]) + parseInt(items[2]);
                                        return value == sum;
                                    }
                                }
                            }
                        }
                    }
                })
                .on('error.form.bv', function(e) {
                    var $form = $(e.target),
                            bootstrapValidator = $form.data('bootstrapValidator');
                    if (!bootstrapValidator.isValidField('captcha')) {

                        generateCaptcha2();
                    }
                });
    });
</script>
	</body>
	</html>