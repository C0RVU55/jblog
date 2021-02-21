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
				<li class="tabbtn selected"><a href="${pageContext.request.contextPath}/${blogVo.id}/admin/basic">기본설정</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${blogVo.id}/admin/category">카테고리</a></li>
				<li class="tabbtn"><a href="${pageContext.request.contextPath}/${blogVo.id}/admin/writeForm">글작성</a></li>
			</ul>
			<!-- //admin-menu -->
			
			<div id="admin-content">
				<form action="${pageContext.request.contextPath}/${blogVo.id}/admin/basic/modify" method="post" enctype="multipart/form-data"> <!-- !!!enctype 주의!!! -->
	 		      	<table id="admin-basic">
	 		      		<colgroup>
							<col style="width: 100px;">
							<col style="">
						</colgroup>
			      		<tr>
			      			<td><label for="textTitle">블로그 제목</label></td>
			      			<td><input id="textTitle" type="text" name="blogTitle" value="${blogVo.blogTitle }"></td>
			      		</tr>
			      		<tr>
			      			<td><label>로고이미지</label></td>
			      			<td class="text-left"><img src="${pageContext.request.contextPath}/upload/${blogVo.logoFile}"></td>   
			      			<!-- 수정한 로고이미지도 계속 assets 안에 저장했는데 인식을 못 함. 선생님 사이트처럼 javastudy/upload폴더로 뺌. -->
			      		</tr>      		
			      		<tr>
			      			<td>&nbsp;</td>
			      			<td><input id="textLogo" type="file" name="file"></td>      			
			      		</tr>           		
			      	</table>
			      	<div id="btnArea">
			      		<button class="btn_l" type="submit" >기본설정변경</button>
			      	</div>
				</form>
			
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

	//input file 태그 초기화 --> 이거 안 하면 블로그 제목만 수정할 때 file에 null이 아니라
	//org.springframework.web.multipart.commons.CommonsMultipartFile@42708404 가 들어가서 blogService의 substring()에서 글자수 초과로 오류남.
	$("#textLogo").val("");


</script>

</html>
