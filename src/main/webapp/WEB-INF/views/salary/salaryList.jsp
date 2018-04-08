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
										<th>迟到罚款</th>
										<th>早退罚款</th>
										<th>请假罚款</th>
										<th>加班奖励</th>
										<th>旷工罚款</th>
										<th>养老保险</th>
										<th>失业保险</th>
										<th>工伤保险</th>
										<th>生育保险</th>
										<th>医疗保险</th>
										<th>住房公积金</th>
										<th>基础薪资</th>
										<th>实发薪资</th>
								</tr>
								<tr>
									<c:forEach items="${attendence}" var="attendence">
									<td>${attendence.empId}</td>
									<td>${attendence.name}</td>
									<td>${attendence.year}</td>
									<td>${attendence.month}</td>
									</c:forEach>
									<td>${lateCome}</td>
									<td>${earlyLeave}</td>
									<td>${vacate}</td>
									<td>${overtime}</td>
									<td>${negletwork}</td>
									<td>${old}</td>
									<td>${unemployment}</td>
									<td>${medical}</td>
									<td>${bear}</td>
									<td>${injury}</td>
									<td>${house}</td>
									<td>${baseSalary}</td>
									<td>${finalSalary}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</article>
	</div>
</section>

  


