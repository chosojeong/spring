<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>/views/cafe/detail.jsp</title>
</head>
<body>
<h3>상세글 내용 페이지</h3>
<%-- 로그인한 회원이 작성한 글이라면 수정 링크를 제공해준다. --%>
<c:if test="${id eq dto.writer }">
	<a href="private/updateform.do?num=${dto.num }">수정</a>
	<a href="javascript:deleteCheck()">삭제</a>
</c:if>
<table>
	<tr>
		<th>글번호</th>
		<td>${dto.num }</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>${dto.writer }</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${dto.title }</td>
	</tr>
</table>
<div class="content">${dto.content }</div>
<div class="comments">
	<c:forEach var="tmp" items="${commentList }">
		<div class="comment" <c:if test="${tmp.num ne tmp.comment_group }">style="margin-left:100px"</c:if> >	
			<c:if test="${tmp.num ne tmp.comment_group }">
				<div class="reply_icon"></div>
			</c:if>
			<div>		
				<strong>from ${tmp.writer }</strong>
				${tmp.regdate }<br/>
				<strong>to ${tmp.target_id }</strong>
				<a href="javascript:">답글</a>
			</div>
			<textarea rows="5" disabled>${tmp.content }</textarea><br/>
			<form action="comment_insert.do" method="post">
				<!-- 덧글 작성자 -->
				<input type="hidden" name="writer" value="${id }"/>
				<!-- 덧글 그룹 -->
				<input type="hidden" name="ref_group" value="${dto.num }" />
				<!-- 덧글 대상 -->
				<input type="hidden" name="target_id" value="${tmp.writer }" />
				<input type="hidden" name="comment_group" value="${tmp.comment_group }" />
				<textarea name="content"></textarea>
				<button type="submit">등록</button>
			</form>
		</div>
		
	</c:forEach>

	<div class="comment_form">
		<form action="comment_insert.do" method="post">
			<!-- 덧글 작성자 -->
			<input type="hidden" name="writer" value="${id }"/>
			<!-- 덧글 그룹 -->
			<input type="hidden" name="ref_group" value="${dto.num }" />
			<!-- 덧글 대상 -->
			<input type="hidden" name="target_id" value="${dto.writer }" />
			<textarea name="content"></textarea>
			<button type="submit">등록</button>
		</form>
	</div>
</div>
<script>
	function deleteCheck(){
		var isDelete=confirm("글을 삭제 하시겠습니까?");
		if(isDelete){
			location.href="private/delete.do?num=${dto.num}";
		}
	}
</script>
</body>
</html>