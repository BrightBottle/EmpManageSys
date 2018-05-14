<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
										<th>年龄</th>
										<th>登录名</th>
										<th>用户名</th>
								</tr>
								<tr>
									<td>${user.empId}</td>
									<td>${user.age}</td>
									<td>${user.loginName}</td>
									<td>${user.name}</td>
								</tr>
								<tr>
										<th>性别</th>
										<th>婚姻状况</th>
										<th>政治面貌</th>
										<th>电话</th>
								</tr>
								<tr>
									<td>${user.gender}</td>
									<td>${user.marry}</td>
									<td>${user.polity}</td>
									<td>${user.phone}</td>
								</tr>
								<tr>
										<th>居住地址</th>
										<th>毕业院校</th>
										<th>学历</th>
										<th>邮箱</th>
								</tr>
								<tr>
									<td>${user.address}</td>
									<td>${user.school}</td>
									<td>${user.degree}</td>
									<td>${user.email}</td>
								</tr>
								<tr>
										<th>操作</th>
										<th>状态</th>
								</tr>
								<tr>
									<td>${user.status.name}</td>
									<td>
										<button type='button' title='修改' class='btn btn-primary btn-xs edit'>
										<i class='fa fa-pencil'></i>
										</button>&nbsp;&nbsp;
										<button type='button' title='修改密码' class='btn btn-warning btn-xs update-password'>
										<i class='fa fa-key'></i>
										</button>&nbsp;&nbsp;
	                        		</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</article>
	</div>
</section>
<script type="text/javascript">
var userId=$("#userId").val();
//编辑用户信息
$(".edit").click(function(){
	loadContent("${ctx}/user/formByOneself/"+userId);
});

//修改密码
$(".update-password").click(function() {
	$("#updatePasswordModal").modal("show");
    $('#updatePasswordBody').load('${ctx}/user/password/' + userId, function(e) {

    });
})

</script>
  


