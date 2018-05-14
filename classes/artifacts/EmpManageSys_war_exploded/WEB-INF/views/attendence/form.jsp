<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="widget">
	<header role="heading">
		<h2>编辑修改考勤信息</h2>
	</header>
	<div role="content">
		<div class="widget-body no-padding">
			<form novalidate="novalidate" action="#" method="post" id="attendenceForm" class="smart-form">
			     <input type="hidden" id="attendenceId" name="id" value="${attendence.id }" />
			     <input type="hidden" id="uId" name="uid" value="${attendence.uid }" />
			     <input type="hidden" id="createTime" name="createTime" value="${attendence.createTime }" /> 
			     <input type="hidden" id="updateTime" name="updateTime" value="${attendence.updateTime }" />   
				<fieldset>
					<div class="row">
						<section class="col col-4">
							<label class="label">工号</label> <label class="input"> 
							      <input name="empId" value="${attendence.empId }" data-rule="required" type="text" maxlength="50">
							</label>
						</section>
						<section class="col col-4">
							<label class="label">姓名</label> <label class="input"> 
							      <input name="name" value="${attendence.name }" data-rule="required" type="text" maxlength="50">
							</label>
						</section>
						<section class="col col-4">
							<label class="label">入职时间/年</label> <label class="input"> 
							      <input name="year" value="${attendence.year }" data-rule="required" type="text" maxlength="50">
							</label>
						</section>
						</div>
						<div class="row">
						<section class="col col-4">
							<label class="label">入职时间/月</label> <label class="input"> 
							     <input type="text" value="${attendence.month }" data-rule="required" name="month" maxlength="50">
							</label>
						</section>
						<section id="password" class="col col-4">
							<label class="label">迟到</label> <label class="input"> 
							     <input type="text" value="${attendence.lateCome }" data-rule="required" name="lateCome" maxlength="20">
							</label>
						</section>
						<section class="col col-4">
							<label class="label">早退</label> <label class="input"> 
							     <input type="text" value="${attendence.earlyLeave }" data-rule="required" name="earlyLeave" maxlength="20">
							</label>
						</section>
						</div>
						<div class="row">
						<section class="col col-4">
							<label class="label">请假</label> <label class="input"> 
							     <input type="text" value="${attendence.vacate }" data-rule="required" name="vacate" maxlength="20">
							</label>
						</section>
						<section class="col col-4">
							<label class="label">加班</label> <label class="input"> 
							     <input type="text" value="${attendence.overtime }" data-rule="required" name="overtime" maxlength="20">
							</label>
						</section>
						<section class="col col-4">
							<label class="label">旷工</label> <label class="input"> 
							     <input type="text" value="${attendence.negletwork }" data-rule="required" name="negletwork" maxlength="20">
							</label>
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
        
        // 初始化保存按钮
        $("#attendenceForm").find('.user-save').click(function() {
            saveAttendence();
        });
    });

    // 保存用户信息
    function saveAttendence() {
        // 校验通过提交
        $("#attendenceForm").ajaxSubmit({
            url : "attendence/save",
            dataType : "json",
            data : {

            },
            success : function(result) {
                // 返回状态是0代表操作成功
                if (result.status == 0) {
                    toastr.success('保存成功！');
                    // 进入首页
                    loadContent("attendence/attendenceList");
                } else if (result.status == 2) {
                    toastr.error('考勤记录已存在！');
                } else {
                    toastr.error('保存失败！');
                }
            }
        });
    }
</script>

