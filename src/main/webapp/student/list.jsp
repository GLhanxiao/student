<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://v3.bootcss.com/assets/js/ie-emulation-modes-warning.js">
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://cdn.bootcss.com/jquery/2.2.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/jquery-validate/1.17.0/jquery.validate.min.js"></script>
<script src="https://cdn.bootcss.com/jquery-validate/1.17.0/localization/messages_zh.min.js"></script>
<title>学生列表</title>
<style type="text/css">
	td{text-align: center;}
	th{text-align: center;}
	.error{color: red;}
</style>
</head>
<body>
	<!-- 学习信息列表 -->
	<div class="container">
	<li class="list-group-item list-group-item-info">
		<span class="glyphicon glyphicon-list" aria-hidden="true" style="font-weight:400; font-size:medium;"> 学生列表</span>
		<span style="margin-left:860px;"><button type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#myModal">添加新学生</button></span>
	</li>
		<table class="table table-striped table-bordered table-hover">							
				<tr>
            		<th>编号</th>
            		<th>学生id</th>
            		<th>姓名</th>
            		<th>出身日期</th>
            		<th>备注</th>
            		<th>平均分</th>
            		<th>操作</th>
                </tr>						
	            <c:forEach items="${pageBean.list}" var="l">
	            	<tr>
	             		<td>${pageBean.index+1}</td>
	             		<td>${l.id}</td>
	             		<td>${l.name}</td>
	             		<td>${l.birthday}</td>
	             		<td>${l.description}</td>
	             		<td>${l.avgscore}</td>	
	             		<td>
	             			 <button type="button" class="btn btn-info btn-xs" onclick="echo('${l.id}')" data-toggle="modal" data-target="#myModal1">修改</button>
	             			 <button type="button" class="btn btn-danger btn-xs" onclick="delstu('${l.id}')">删除</button>	  		                			 	  		                			 
	             		</td>
             		</tr>
	            </c:forEach> 	
		</table>
	</div>	
	<!-- 学生信息分页 -->
	<div class="container" align="center">
		<nav aria-label="Page navigation">
			<ul class="pagination">
				<!-- 上一页 -->
				<c:if test="${pageBean.currentPage==1}">
					<li class="disabled">
					<a href="javascript:void(0);" aria-label="Previous">
					    <span aria-hidden="true">&laquo;</span>
					</a>
					</li>
				</c:if>
				<c:if test="${pageBean.currentPage!=1}">
					<li>
					<a href="${pageContext.request.contextPath}/showStudentList?currentPage=${pageBean.currentPage-1}" aria-label="Previous">
					    <span aria-hidden="true">&laquo;</span>
					</a>
				    </li>
				</c:if>
				
				<!--分页  -->
				<c:forEach begin="1" end="${pageBean.totalPage}" var="page">
					<c:if test="${pageBean.currentPage==page}">
						<li class="active"><a href="javascript:void(0);">${page}</a></li>
					</c:if>
					<c:if test="${pageBean.currentPage!=page}">
						<li><a href="${pageContext.request.contextPath}/showStudentList?currentPage=${page}">${page}</a></li>
					</c:if>							
			    </c:forEach>
			
                         <!--下一页  -->
				<c:if test="${pageBean.currentPage==pageBean.totalPage}">
					<li class="disabled">						
					<a href="javascript:void(0);" aria-label="Next"> 
						<span aria-hidden="true">&raquo;</span>
					</a>
				    </li>
				</c:if>
				<c:if test="${pageBean.currentPage!=pageBean.totalPage}">
					<li>						
					<a href="${pageContext.request.contextPath}/showStudentList?currentPage=${pageBean.currentPage+1}" aria-label="Next"> 
						<span aria-hidden="true">&raquo;</span>
					</a>
				   </li>
				</c:if>
			</ul>
		</nav>
	</div>	
	<!--添加學生弹出层 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">填写学生信息</h4>
	      </div>
	      <div class="modal-body">
	      <!--   表单开始 -->
	        <form id="myform" method="post" action="${pageContext.request.contextPath}/addStudent">
			  <div class="form-group">
			    <label for="myname">姓名</label>
			    <input name="name" type="text" id="myname" class="form-control" placeholder="请输入姓名" autocomplete="off">
			  </div>
			  <div class="form-group">
			    <label for="mydate">出生日期</label>
			    <input name="birthday" type="text" id="mydate" class="form-control" placeholder="请选择出生日期" onfocus="(this.type='date')">
			  </div>
			  <div class="form-group">
			    <label for="mycontent">备注</label>
			    <textarea name="description" class="form-control" rows="2" id="mycontent" placeholder="请输入备注" ></textarea>
			  </div>
			  <div class="form-group">
			    <label for="myscore">平均分</label>
			    <input name="avgscore" type="text" id="myscore" class="form-control" placeholder="请输入平均分" >
			  </div>
			  <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		        <button id="mybutton" type="submit" class="btn btn-primary">保存</button>
	      	</div>
			 <!--  表单结束 -->
			</form>
	      </div>
	    </div>
	  </div>
	</div>
	<!--修改信息弹出层 -->
	<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="myModalLabel">修改学生信息</h4>
	      </div>
	      <div class="modal-body">
	      <!--   表单开始 -->
	        <form id="myform1" method="post" action="${pageContext.request.contextPath}/updataStudent">
			  <div class="form-group">
			    <label for="myname1">姓名</label>
			    <input name="id" type="hidden" >
			    <input name="name" type="text" id="myname1" class="form-control" placeholder="请输入姓名">
			  </div>
			  <div class="form-group">
			    <label for="mydate1">出生日期</label>
			    <input name="birthday" type="date" id="mydate1" class="form-control">
			  </div>
			  <div class="form-group">
			    <label for="mycontent1">备注</label>
			    <textarea name="description" class="form-control" rows="2" id="mycontent1" placeholder="请输入备注"></textarea>
			  </div>
			  <div class="form-group">
			    <label for="myscore1">平均分</label>
			    <input name="avgscore" type="text" id="myscore1" class="form-control" placeholder="请输入平均分">
			  </div>
			  <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		        <button id="mybutton1" type="submit" class="btn btn-primary">保存</button>
	      	 </div>
			</form>
			 <!--  表单结束 -->
	      </div>
	    </div>
	  </div>
	</div>
	<script type="text/javascript">
		/* 添加学生信息校验 */
		$(function(){
				$("#myform").validate({
					rules:{
						name:{
							required:true
						},
						description:{
							required:true
						},
						birthday:{
							required:true
						},
						avgscore:{
							required:true,
							digits:true,
							min:0,
							max:100
						}
					},
					messages:{
						name:{
							required:"姓名不能为空"
						},
						description:{
							required:"备注不能为空"
						},
						birthday:{
							required:"请选择出生日期"
						},
						avgscore:{
							required:"平均分不能为空",
							digits:"请输入整数",
							min:"平均分不能小于0",
							max:"平均分不能大于100"
						}
					}
				});
			});
		/* 添加学生信息校验 */
		$(function(){
				$("#myform1").validate({
					rules:{
						name:{
							required:true
						},
						description:{
							required:true
						},
						birthday:{
							required:true
						},
						avgscore:{
							required:true,
							digits:true,
							min:0,
							max:100
						}
					},
					messages:{
						name:{
							required:"姓名不能为空"
						},
						description:{
							required:"备注不能为空"
						},
						birthday:{
							required:"请选择出生日期"
						},
						avgscore:{
							required:"平均分不能为空",
							digits:"请输入整数",
							min:"平均分不能小于0",
							max:"平均分不能大于100"
						}
					}
				});
			});
		/* 回显信息 */
		function echo(id){
			$.post("${pageContext.request.contextPath}/echoInfo", { currentId : id },
			  function(data){
				$.each( data , function(i, n){
					$("input[name="+i+"]").val(n);
					$("textarea[name="+i+"]").val(n);
					$("#myname").val("");
					$("#mydate").val("");
					$("#mycontent").val("");
					$("#myscore").val("");
					});
			  });
		}
		/* 删除一条学生信息 */
		function delstu(id){
			if(confirm("确定要删除吗？")){
				location.href="${pageContext.request.contextPath}/deleteStudent?id="+id;
			}
		}
	</script>		
</body>
</html>