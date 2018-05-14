<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/taglibs.jsp"%>


<!-- 修改密码窗口 -->
<div class="modal fade" id="updatePasswordModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div id="updatePasswordBody" class="modal-content"></div>
    </div>
</div>
<section id="userTablePanel" >
	<div class="row">
		<article class="col-md-12">
			<div class="widget " >
				<div>
				<input type="hidden" id="userId" value="${user.id}" name="userId"/>
					<!-- widget content -->
					<div class="widget-body no-padding">
						<table id="userTable" class="table table-striped table-hover">
							<tbody>
								<tr>
										<th>工号</th>
										<th>姓名</th>
										<th>年</th>
										<th>月</th>
										<th>基础薪资</th>
										<th>实发薪资</th>
								</tr>
								<c:forEach items="${attendence}" var="attendence" varStatus="loop">
								<tr>
									<td>${attendence.empId}</td>
									<td>${attendence.name}</td>
									<td>${attendence.year}</td>
									<td>${attendence.month}</td>
									<td>${salaryStandard[loop.count-1].baseSalary}</td>
									<td>${finalSalary[loop.count]}</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</article>
	</div>
</section>

  


