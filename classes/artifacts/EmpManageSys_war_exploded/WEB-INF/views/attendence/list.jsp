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
					<div class="widget-body-toolbar">
						<button class="add-attendence-btn btn btn-primary pull-left" type="button">
							<i class="fa fa-plus"></i> 新增
						</button>
						
						<input class="form-control list-search" name="empId" id="empId" placeholder="工号..." type="text"> 
						<input class="form-control list-search-contact" name="year" id="year" placeholder="年..." type="text" >
						<input class="form-control list-search-contact" name="month" id="month" placeholder="月..." type="text" >
						
						<button class="search-user-btn btn btn-success" type="button">
							<i class="fa fa-search"></i>
						</button>
						<button class="reset-user-btn btn btn-default" type="button">
							<i class="fa fa-mail-reply"></i>
						</button>
					</div>
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
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</article>
	</div>
</section>

<script type="text/javascript">
    var attendenceTable;
    var $attendenceTablePanel;
    $(function() {
        // 定义数据列
        var columnData = [ {
            "data" : "empId"
        },{
            "data" : "name"
        }, {
            "data" : "year"
        }, {
            "data" : "month"
        }, {
            "data" : "lateCome"
        },{
            "data" : "earlyLeave"
        },{
            "data" : "vacate"
        }, {
            "data" : "overtime"
        },{
            "data" : "negletwork"
        }, {
            "data" : ""
        }, ];
        // 显示列数
        var columnNumber = columnData.length - 1;
        // 初始化表格信息
        attendenceTable = $qingshixun.dataTable($('#attendenceTable'), {
            "ajax" : {
                "bRetrieve" : true,
                "url" : "attendence/attendenceInfoForm", //指定后端获取数据地址
                "type" : "POST"
            },
            "columns" : columnData,
            "columnDefs" : [ {
                "data" : null,
                //在数据最后一列定义相关操作按键
                "defaultContent" : "<button type='button' title='修改' class='btn btn-primary btn-xs edit'><i class='fa fa-pencil'></i></button>&nbsp;&nbsp;" 
                        + "<button type='button' title='删除' class='btn btn-danger btn-xs delete'><i class='fa fa-trash-o'></i></button>&nbsp;&nbsp;"  ,
                orderable : false, // 禁止排序
                targets : [ columnNumber ] //指定禁止排序的列（最后一列为操作按钮，不需要排序功能）
            } ],
            "createdRow" : function(row, data, index) {

            },
            rowCallback : function(row, data) {
                // 编辑用户操作
                $('td', row).last().find(".edit").click(function() {
                    editAttendence(data.id);
                });

                // 删除用户操作
                $('td', row).last().find(".delete").click(function() {
                	deleteAttendence(data.id);
                });
             
            },
            "fnDrawCallback" : function(row) {

            },
            "fnServerParams" : function(aoData) {
                aoData.columnNumber = columnNumber;
                //定义数据查询参数，此参数将在后端 UserDao.java 类中的 getUserPage 方法中使用
                aoData.empId = $("#empId").val();
                aoData.year = $("#year").val();
                 aoData.month = $("#month").val();
                
            }
        });

        $attendenceTablePanel = $("#attendenceTablePanel");

        // 初始化新增按钮
        $attendenceTablePanel.find('.add-attendence-btn').click(function() {
            loadContent("attendence/form/0");
        });

        // 初始化查询按钮
        $attendenceTablePanel.find('.search-user-btn').click(function() {
            attendenceTable.ajax.reload();
        });

        // 初始化重置按钮
        $attendenceTablePanel.find('.reset-user-btn').click(function() {
            $("#empId").val("");
            $("#year").val("");
             $("#month").val("");
            attendenceTable.ajax.reload();
        });
    });

    //编辑用户
    function editAttendence(attendenceId) {
        loadContent("attendence/form/" + attendenceId);
    }
    
    // 删除用户
    function deleteAttendence(attendenceId) {
        $.confirm({
            title : false,
            content : '是否确认删除该用户？',
            confirmButton : '确认',
            cancelButton : '取消',
            confirm : function() {
                //执行删除
                $.ajax({
                    type : "post",
                    async : false,
                    url : 'attendence/delete/' + attendenceId,
                    data : {

                    },
                    dataType : "json",
                    success : function(result) {
                        attendenceTable.ajax.reload();
                        toastr.success('用户删除成功！');
                    }
                });
            },
            cancel : function() {
            }
        });
    }

</script>

