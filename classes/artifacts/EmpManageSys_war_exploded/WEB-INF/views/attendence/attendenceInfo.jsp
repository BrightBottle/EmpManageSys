<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/taglibs.jsp"%>

<!-- 个人考勤列表 -->
<section id="attendenceTablePanel">
	<!-- 个人考勤列表 -->
	<div class="row">
		<article class="col-md-12">
			<div class="widget">
				<div>
					<div class="widget-body no-padding">
						<table id="attendenceTable" class="table table-striped table-hover">
							<thead>
								<tr>
									<th>工号</th>
									<th>姓名</th>
									<th>时间/年</th>
									<th>时间/月</th>
									<th>迟到</th>
									<th>早退</th>
									<th>请假</th>
									<th>加班</th>
									<th>旷工</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>${attendenceInfo.empId}</td>
									<td>${attendenceInfo.name}</td>
									<td>${attendenceInfo.year}</td>
									<td>${attendenceInfo.month}</td>
									<td>${attendenceInfo.lateCome}</td>
									<td>${attendenceInfo.earlyLeave}</td>
									<td>${attendenceInfo.vacate}</td>
									<td>${attendenceInfo.overtime}</td>
									<td>${attendenceInfo.negletwork}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</article>
	</div>
</section>

