<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/taglibs.jsp"%>

<!-- 考勤列表 -->
<section id="attendenceTablePanel">
	<!-- 考勤列表 -->
	<div class="row">
		<article class="col-md-12">
			<div class="widget">
				<div>
					<div class="widget-body no-padding">
						<table id="attendenceTable" class="table table-striped table-hover">
							<thead>
								<tr>
									<th>迟到/次</th>
									<th>早退/小时</th>
									<th>请假/天</th>
									<th>加班/小时</th>
									<th>旷工/天</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${attendenceSet}" var="set">
								<input type="hidden" id="attendenceSetId" value="${set.id}" name="attendenceSetId"/>
									<tr>
										<td>${set.lateCome}</td>
										<td>${set.earlyLeave}</td>
										<td>${set.vacate}</td>
										<td>${set.overtime}</td>
										<td>${set.negletwork}</td>
										<td>
											<button type='button' title='修改' class='btn btn-primary btn-xs edit'>
												<i class='fa fa-pencil'></i>
											</button>
										</td>
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
<script type="text/javascript">
var attendenceSetId=$("#attendenceSetId").val();
//编辑用户信息
$(".edit").click(function(){
	loadContent("${ctx}/attendence/setForm/"+attendenceSetId);
})
</script>