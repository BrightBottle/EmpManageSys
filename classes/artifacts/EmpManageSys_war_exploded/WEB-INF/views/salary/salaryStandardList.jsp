<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/common/taglibs.jsp"%>

<!-- 标准薪资列表 -->
<section id="salaryStandardTablePanel">
	<!-- 标准薪资列表 -->
	<div class="row">
		<article class="col-md-12">
			<div class="widget">
				<div>
					<div class="widget-body-toolbar">
						<button class="add-salaryStandard-btn btn btn-primary pull-left" type="button">
							<i class="fa fa-plus"></i> 新增
						</button>
						
						<input class="form-control list-search" name="empId" id="empId" placeholder="工号..." type="text"> 
						
						<button class="search-user-btn btn btn-success" type="button">
							<i class="fa fa-search"></i>
						</button>
						<button class="reset-user-btn btn btn-default" type="button">
							<i class="fa fa-mail-reply"></i>
						</button>
					</div>
					<div class="widget-body no-padding">
						<table id="salaryStandardTable" class="table table-striped table-hover">
							<thead>
								<tr>
									<th>工号</th>
									<th>姓名</th>
									<th>基础薪资</th>
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
    var salaryStandardTable;
    var $salaryStandardTablePanel;
    $(function() {
        // 定义数据列
        var columnData = [ {
            "data" : "empId"
        },{
            "data" : "name"
        }, {
            "data" : "baseSalary"
        }, {
            "data" : ""
        }, ];
        // 显示列数
        var columnNumber = columnData.length - 1;
        // 初始化表格信息
        salaryStandardTable = $qingshixun.dataTable($('#salaryStandardTable'), {
            "ajax" : {
                "bRetrieve" : true,
                "url" : "salary/salaryStandardInfo", //指定后端获取数据地址
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
                    editSalaryStandard(data.id);
                });

                // 删除用户操作
                $('td', row).last().find(".delete").click(function() {
                	deleteSalaryStandard(data.id);
                });
             
            },
            "fnDrawCallback" : function(row) {

            },
            "fnServerParams" : function(aoData) {
                aoData.columnNumber = columnNumber;
                //定义数据查询参数，此参数将在后端 UserDao.java 类中的 getUserPage 方法中使用
                aoData.empId = $("#empId").val();
                
            }
        });

        $salaryStandardTablePanel = $("#salaryStandardTablePanel");

        // 初始化新增按钮
        $salaryStandardTablePanel.find('.add-salaryStandard-btn').click(function() {
            loadContent("salary/salaryStandardForm/0");
        });

        // 初始化查询按钮
        $salaryStandardTablePanel.find('.search-user-btn').click(function() {
        	salaryStandardTable.ajax.reload();
        });

        // 初始化重置按钮
        $salaryStandardTablePanel.find('.reset-user-btn').click(function() {
            $("#empId").val("");
             salaryStandardTable.ajax.reload();
        });
    });

    //编辑用户
    function editSalaryStandard(salaryStandardId) {
        loadContent("salary/salaryStandardForm/" + salaryStandardId);
    }
    
    // 删除用户
    function deleteSalaryStandard(salaryStandardId) {
        $.confirm({
            title : false,
            content : '是否确认删除该用户基础薪资？',
            confirmButton : '确认',
            cancelButton : '取消',
            confirm : function() {
                //执行删除
                $.ajax({
                    type : "post",
                    async : false,
                    url : 'salary/deleteSalaryStandard/' + salaryStandardId,
                    data : {

                    },
                    dataType : "json",
                    success : function(result) {
                    	salaryStandardTable.ajax.reload();
                        toastr.success('信息删除成功！');
                    }
                });
            },
            cancel : function() {
            }
        });
    }

</script>

