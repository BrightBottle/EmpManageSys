<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<div class="widget">
	<header role="heading">
		<h2>考勤信息</h2>
	</header>
	<div role="content">
		<div class="widget-body no-padding">
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
		</div>
	</div>
</div>

