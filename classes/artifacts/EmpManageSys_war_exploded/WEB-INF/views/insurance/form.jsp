<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="widget">
	<header role="heading">
		<h2>编辑修改保险设置信息</h2>
	</header>
	<div role="content">
		<div class="widget-body no-padding">
			<form novalidate="novalidate" action="#" method="post" id="insuranceForm" class="smart-form">
			     <input type="hidden" id="insuranceId" name="id" value="${insurance.id }" />
			     <input type="hidden" id="createTime" name="createTime" value="${insurance.createTime }" /> 
			     <input type="hidden" id="updateTime" name="updateTime" value="${insurance.updateTime }" />   
				<fieldset>
						<div class="row">
						<section id="password" class="col col-4">
							<label class="label">养老保险</label> <label class="input"> 
							     <input type="text" value="${insurance.old }" data-rule="required" name="old" maxlength="20">
							</label>
						</section>
						<section class="col col-4">
							<label class="label">失业保险</label> <label class="input"> 
							     <input type="text" value="${insurance.unemployment }" data-rule="required" name="unemployment" maxlength="20">
							</label>
						</section>
						</div>
						<div class="row">
						<section class="col col-4">
							<label class="label">医疗保险</label> <label class="input"> 
							     <input type="text" value="${insurance.medical }" data-rule="required" name="medical" maxlength="20">
							</label>
						</section>
						<section class="col col-4">
							<label class="label">生育保险</label> <label class="input"> 
							     <input type="text" value="${insurance.bear }" data-rule="required" name="bear" maxlength="20">
							</label>
						</section>
						</div>
						<div class="row">
						<section class="col col-4">
							<label class="label">工伤保险</label> <label class="input"> 
							     <input type="text" value="${insurance.injury }" data-rule="required" name="injury" maxlength="20">
							</label>
						</section>
						<section class="col col-4">
							<label class="label">公积金</label> <label class="input"> 
							     <input type="text" value="${insurance.house }" data-rule="required" name="house" maxlength="20">
							</label>
						</section>
						</div>
				</fieldset>

				<footer>
					<button type="button" class="insurance-save btn btn-primary">保存</button>
				</footer>
			</form>
		</div>
	</div>
</div>
<script type="text/javascript">
    $(function() {
        
        // 初始化保存按钮
        $("#insuranceForm").find('.insurance-save').click(function() {
            saveInsurance();
        });
    });

    // 保存用户信息
    function saveInsurance() {
        // 校验通过提交
        $("#insuranceForm").ajaxSubmit({
            url : "insurance/save",
            dataType : "json",
            data : {

            },
            success : function(result) {
                // 返回状态是0代表操作成功
                if (result.status == 0) {
                    toastr.success('保存成功！');
                    // 进入首页
                    loadContent("insurance/insuranceList");
                }  else {
                    toastr.error('保存失败！');
                }
            }
        });
    }
</script>

