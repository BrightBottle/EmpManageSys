<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/taglibs.jsp"%>

<!-- 保险列表 -->
<section id="insuranceTablePanel">
	<!-- 保险列表 -->
	<div class="row">
		<article class="col-md-12">
			<div class="widget">
				<div>
					<div class="widget-body no-padding">
						<table id="insuranceTable" class="table table-striped table-hover">
							<thead>
								<tr>
									<th>养老保险</th>
									<th>失业保险</th>
									<th>医疗保险</th>
									<th>生育保险</th>
									<th>工伤保险</th>
									<th>公积金</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${insurance}" var="insurance">
								<input type="hidden" id="insuranceId" value="${insurance.id}" name="insuranceId"/>
									<tr>
										<td>${insurance.old}</td>
										<td>${insurance.unemployment}</td>
										<td>${insurance.medical}</td>
										<td>${insurance.bear}</td>
										<td>${insurance.injury}</td>
										<td>${insurance.house}</td>
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
var insuranceId=$("#insuranceId").val();
//编辑用户信息
$(".edit").click(function(){
	loadContent("${ctx}/insurance/form/"+insuranceId);
})
</script>