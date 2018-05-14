<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="widget">
	<header role="heading">
		<h2>编辑修改考勤信息</h2>
	</header>
	<div role="content">
		<div class="widget-body no-padding">
			<form novalidate="novalidate" action="#" method="post" id="attendenceForm" class="smart-form">
			     <input type="hidden" id="attendenceId" name="id" value="${attendenceSet.id }" />
			     <input type="hidden" id="createTime" name="createTime" value="${attendenceSet.createTime }" /> 
			     <input type="hidden" id="updateTime" name="updateTime" value="${attendenceSet.updateTime }" />   
				<fieldset>
						<div class="row">
						<section id="password" class="col col-4">
							<label class="label">迟到/次</label> <label class="input"> 
							     <input type="text" value="${attendenceSet.lateCome }" data-rule="required" name="lateCome" maxlength="20">
							</label>
						</section>
						<section class="col col-4">
							<label class="label">早退/小时</label> <label class="input"> 
							     <input type="text" value="${attendenceSet.earlyLeave }" data-rule="required" name="earlyLeave" maxlength="20">
							</label>
						</section>
						</div>
						<div class="row">
						<section class="col col-4">
							<label class="label">请假/天</label> <label class="input"> 
							     <input type="text" value="${attendenceSet.vacate }" data-rule="required" name="vacate" maxlength="20">
							</label>
						</section>
						<section class="col col-4">
							<label class="label">加班/小时</label> <label class="input"> 
							     <input type="text" value="${attendenceSet.overtime }" data-rule="required" name="overtime" maxlength="20">
							</label>
						</section>
						</div>
						<div class="row">
						<section class="col col-4">
							<label class="label">旷工/天</label> <label class="input"> 
							     <input type="text" value="${attendenceSet.negletwork }" data-rule="required" name="negletwork" maxlength="20">
							</label>
						</section>
						</div>
				</fieldset>

				<footer>
					<button type="button" class="attendence-save btn btn-primary">保存</button>
				</footer>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
    $(function() {
        
        // 初始化保存按钮
        $("#attendenceForm").find('.attendence-save').click(function() {
            saveAttendence();
        });
    });

    // 保存用户信息
    function saveAttendence() {
        // 校验通过提交
        $("#attendenceForm").ajaxSubmit({
            url : "attendence/saveSet",
            dataType : "json",
            data : {

            },
            success : function(result) {
                // 返回状态是0代表操作成功
                if (result.status == 0) {
                    toastr.success('保存成功！');
                    // 进入首页
                    loadContent("attendence/attendenceSet");
                }  else {
                    toastr.error('保存失败！');
                }
            }
        });
    }
</script>

