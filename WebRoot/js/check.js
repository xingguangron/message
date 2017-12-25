$(document).ready(function() {
    /**
     * 下面是进行插件初始化
     * 你只需传入相应的键值对
     * */
    $('#defaultForm').bootstrapValidator({
            message: 'This value is not valid',
            
            fields: {/*验证*/
                officer_id: {/*键名username和input name值对应*/
                    message: 'The username is not valid',
                    validators: {
                        notEmpty: {/*非空提示*/
                            message: '账号不能为空'
                        },
                        	stringLength: {/*长度提示*/
                            min: 3,
                            max: 30,
                            message: '账号长度必须在6到30之间'
                        }, 
                        regexp: {
                        	regexp: /^[0-9]*$/,
                        	message: '请输入纯数字账号'
                        }
                    }
                }, 
                password: {
                    message:'密码无效',
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        stringLength: {
                            min: 6,
                            max: 12,
                            message: '用户名长度必须在6到30之间'
                        }
                    }
                },
                repassword: {
                    message:'密码无效',
                    validators: {
                        notEmpty: {
                            message: '密码不能为空'
                        },
                        identical: {//相同
                            field: 'password',
                            message: '两次密码不一致'
                        },
                        stringLength: {
                            min: 6,
                            max: 12,
                            message: '用户名长度必须在6到30之间'
                        }
                    }
                },
                checkCode: {
                	message:'验证码无效',
                	validators: {
                		notEmpty: {
                			message: '验证码不能为空'
                		},
                		stringLength: {
                			min: 4,
                			max: 4,
                			message: '请输入四位验证码'
                		}
                	}
                }, 
                answer: {
                	message: '答案无效',
                	validators: {
                		notEmpty: {
                			message: '密保答案不能为空'
                		}
                	}
                },
                question: {
                	message: '选择错误',
                	validators: {
                		notEmpty: {
                			message: '请选择密保问题'
                		}
                	}
                }
            }
        });
});