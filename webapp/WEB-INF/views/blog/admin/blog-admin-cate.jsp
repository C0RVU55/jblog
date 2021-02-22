<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">
		
		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content">
			<ul id="admin-menu" class="clearfix">
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${blogVo.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${blogVo.id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${blogVo.id}/admin/writeForm">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
			
				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<tbody id="cateList">
		      			<!-- 리스트 영역
		      			<c:forEach items="${cateList }" var="vo">
			      			<tr>
								<td>${vo.cateNo }</td>
								<td>${vo.cateName }</td>
								<td>${vo.postSum }</td>
								<td>${vo.description }</td>
							    <td class='text-center'>
							    	<img class="btnCateDel" src="${pageContext.request.contextPath}/assets/images/delete.jpg">
							    </td>
							</tr>
						</c:forEach>
						리스트 영역 -->
					</tbody>
				</table>
      	
		      	<table id="admin-cate-add" > <!-- 카테고리 추가 -->
		      		<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      	</table> 
			
				<div id="btnArea">
		      		<button id="btnAddCate" class="btn_l" type="submit" >카테고리추가</button>
		      	</div>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		
		<!-- 개인블로그 푸터 -->
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
	
	
	</div>
	<!-- //wrap -->
</body>

<script type="text/javascript">

	//////////////리스트 출력////////////// 
	$("document").ready(function(){
		console.log("ready");
		
		//전체 리스트 출력 함수(복잡해지면 함수는 아래 모아두고 위에서는 이렇게만 실행 가능)
		fetchList();
	});
	
	//////////////카테고리 추가 --> form으로 하고 삭제만 ajax? //////////////
	$("#btnAddCate").on("click", function(){
		console.log("카테고리추가 버튼");
		
		var cateName = $("[name='name']").val();
		var description = $("[name='desc']").val();
		console.log(cateName);
		console.log(description);

		$.ajax({
			url : "${pageContext.request.contextPath }/${blogVo.id}/admin/category/add",		
			type : "post",
			//contentType : "application/json", 
			data : {cateName: cateName, description: description},
			
			dataType : "json",
			success : function(count){ 
				
				console.log(count);
				
			},
			error : function(XHR, status, error) { //오류메세지 보려고 쓰는 거
				console.error(status + " : " + error);
			}
		});
	});
	
	//////////////카테고리 삭제//////////////
	$("#btnSubmit").on("click", function(){
		console.log("방명록 등록 버튼 클릭");
		
		var cateName = $("name").val();
		var description = $("desc").val();
		console.log(name);
		console.log(password);

		$.ajax({
			url : "${pageContext.request.contextPath }/${blogVo.id}/admin/category/remove",		
			type : "post",
			//contentType : "application/json", 
			data : {cateName: cateName, description: description},
			
			dataType : "json",
			success : function(count){ 
				
				console.log(count);
				
			},
			error : function(XHR, status, error) { //오류메세지 보려고 쓰는 거
				console.error(status + " : " + error);
			}
		});
	});


</script>


</html>