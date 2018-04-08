<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="widget">
	<header role="heading">
		<h2>编辑修改用户</h2>
	</header>
	<div role="content">
		<div class="widget-body no-padding">
			<form novalidate="novalidate" action="#" method="post" id="userForm" class="smart-form">
			     <input type="hidden" id="userId" name="id" value="${userModel.id }" /> 
			     <input type="hidden" id="genderId" value="${userModel.gender }" /> 
			     <input name="createTime" type="hidden" value="${userModel.createTime }" /> 
			    <input name="status.code" type="hidden" value="${userModel.status.code }" />
			    <input name="department.id" type="hidden" value="${userModel.department.id }" />
			    <input name="password" type="hidden" value="${userModel.password }" />
				<fieldset>
					<div class="row">
						<section class="col col-4">
							<label class="label">工号</label> <label class="input"> 
							      <input readonly="readonly" name="empId" value="${userModel.empId }" data-rule="required" type="text" maxlength="50">
							</label>
						</section>
						<section class="col col-4">
							<label class="label">年龄</label> <label class="input"> 
							      <input readonly="readonly" name="age" value="${userModel.age }" data-rule="required" type="text" maxlength="50">
							</label>
						</section>
						<section class="col col-4">
							<label class="label">登录名</label> <label class="input"> 
							      <input name="loginName" value="${userModel.loginName }" data-rule="required" type="text" maxlength="50">
							</label>
						</section>
						</div>
						<div class="row">
						<section class="col col-4">
							<label class="label">用户名</label> <label class="input"> 
							     <input type="text" value="${userModel.name }" data-rule="required" name="name" maxlength="50">
							</label>
						</section>
						<section class="col col-4">
							<label class="label">婚姻状况</label> <label class="input"> 
							     <input type="text" value="${userModel.marry }" data-rule="required" name="marry" maxlength="20">
							</label>
						</section>
						<section class="col col-4">
							<label class="label">政治面貌</label> <label class="input"> 
							     <input type="text" value="${userModel.polity }" data-rule="required" name="polity" maxlength="20">
							</label>
						</section>
						</div>
						<div class="row">
						<section class="col col-4">
							<label class="label">居住地</label> <label class="input"> 
							     <input type="text" value="${userModel.address }" data-rule="required" name="address" maxlength="20">
							</label>
						</section>
						<section class="col col-4">
							<label class="label">毕业院校</label> <label class="input"> 
							     <input readonly="readonly" type="text" value="${userModel.school }" data-rule="required" name="school" maxlength="20">
							</label>
						</section>
						<section class="col col-4">
							<label class="label">学历</label> <label class="input"> 
							     <input readonly="readonly" type="text" value="${userModel.degree }" data-rule="required" name="degree" maxlength="20">
							</label>
						</section>
						</div>
						<div class="row">
						<section class="col col-4">
							<label class="label">邮箱</label> <label class="input"> 
							     <input type="email" name="email" value="${userModel.email }" data-rule="required; email;">
							</label>
						</section>
						<section class="col col-4">
							<label class="label">电话</label> <label class="input"> 
							     <input type="text" name="phone" value="${userModel.phone }" data-rule="required;mobile;">
							</label>
						</section>
						<section class="col col-4">
							<label class="label">性别:</label>
							<div class="inline-group">
								<label class="radio"> <input  readonly="readonly" name="gender" value="男" checked="checked" type="radio"> <i></i> 男
								</label> <label class="radio"> <input  readonly="readonly" name="gender" value="女" type="radio"> <i></i> 女
								</label>
							</div>
						</section>
					</div>

				</fieldset>

				<footer>
					<button type="button" class="user-save btn btn-primary">保存</button>
				</footer>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
    $(function() {
      
        // 修改的时候初始化性别选项
        initUserGender();

        // 初始化密码输入框
        if ($("#userId").val() != "") {
            $("#password").css("display", "none");
        }

        // 初始化保存按钮
        $("#userForm").find('.user-save').click(function() {
            saveUser();
        });
    });

    // 修改的时候初始化性别选项
    function initUserGender() {
        var typeObj = $('input[name="gender"]');
        // 设置选择的类型选中
        for (var i = 0; i < typeObj.length; i++) {
            if (typeObj[i].value == $("#genderId").val()) {
                typeObj[i].checked = true;
            }
        }
    }
    // 保存用户信息
    function saveUser() {
        // 校验通过提交
        $("#userForm").ajaxSubmit({
            url : "user/userSaveByOneself",
            dataType : "json",
            data : {
            },
            success : function(result) {
                // 返回状态是0代表操作成功
                if (result.status == 0) {
                    toastr.success('保存成功！');
                    // 进入用户列表页面
                    loadContent("index");
                } else if (result.status == 2) {
                    toastr.error('登录名已存在！');
                } else {
                    toastr.error('保存失败！');
                }
            }
        });
    }
</script>

