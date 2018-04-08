<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="widget">
	<header role="heading">
		<h2>编辑修改基础薪资信息</h2>
	</header>
	<div role="content">
		<div class="widget-body no-padding">
			<form novalidate="novalidate" action="#" method="post" id="salaryStandardForm" class="smart-form">
			     <input type="hidden" id="salaryStandardId" name="id" value="${salaryStandard.id }" />
			     <input type="hidden" id="createTime" name="createTime" value="${salaryStandard.createTime }" /> 
			     <input type="hidden" id="updateTime" name="updateTime" value="${salaryStandard.updateTime }" />   
				<fieldset>
					<div class="row">
						<section class="col col-4">
							<label class="label">工号</label> <label class="input"> 
							      <input name="empId" value="${salaryStandard.empId }" data-rule="required" type="text" maxlength="50">
							</label>
						</section>
						<section class="col col-4">
							<label class="label">姓名</label> <label class="input"> 
							      <input name="name" value="${salaryStandard.name }" data-rule="required" type="text" maxlength="50">
							</label>
						</section>
						<section class="col col-4">
							<label class="label">基础薪资</label> <label class="input"> 
							     <input type="text" value="${salaryStandard.baseSalary }" data-rule="required" name="baseSalary" maxlength="20">
							</label>
						</section>
						</div>
				</fieldset>

				<footer>
					<button type="button" class="salaryStandard-save btn btn-primary">保存</button>
				</footer>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
    $(function() {
        
        // 初始化保存按钮
        $("#salaryStandardForm").find('.salaryStandard-save').click(function() {
        	saveSalaryStandard();
        });
    });

    // 保存用户信息
    function saveSalaryStandard() {
        // 校验通过提交
        $("#salaryStandardForm").ajaxSubmit({
            url : "salary/saveSalaryStandard",
            dataType : "json",
            data : {

            },
            success : function(result) {
                // 返回状态是0代表操作成功
                if (result.status == 0) {
                    toastr.success('保存成功！');
                    // 进入首页
                    loadContent("salary/salaryStandardList");
                } else {
                    toastr.error('保存失败！');
                }
            }
        });
    }
</script>

