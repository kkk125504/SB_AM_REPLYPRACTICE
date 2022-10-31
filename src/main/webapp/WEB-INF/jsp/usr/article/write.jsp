<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="WRITE" />
<%@ include file="../common/head.jspf" %>
	<section class="mt-8 text-xl">
		<div class="container mx-auto px-3">
			<form class="table-box-type-1" method="post" action="doWrite">
				<table>
					<colgroup>
						<col width="200" />
					</colgroup>	
					<tbody>														
						<tr>
							<td>게시판 선택</td>
							<td>
								<select name="boardId" class="select select-bordered">
									<option disabled>게시판을 선택해주세요</option>
									<option value="1">공지사항</option>
									<option value="2">자유게시판</option>
								</select>
							</td>
						</tr>
						<tr>
							<td>제목</td>
							<td><input required="required" type="text" class="w-4/6 input input-bordered input-lg" name="title" value="${article.title }" placeholder="제목을 입력해주세요." /></td>						
						</tr>
						<tr>
							<td>내용</td>
							<td><textarea required="required" name="body" class="textarea textarea-bordered h-52 w-11/12" placeholder="내용을 입력해주세요." >${article.body }</textarea></td>						
						</tr>
						<tr>
							<td>작성자</td>
							<td>${rq.loginedMember.nickname }</td>						
						</tr>						
					</tbody>								
				</table>
				
				<div class= "btns flex justify-end">
					<button class ="btn-text-link mx-4 btn btn-active btn-ghost" type="submit">게시글 작성</button>					
					<button class ="btn-text-link btn btn-active btn-ghost" type="button" onclick="history.back()">뒤로가기</button>
				</div>
			</form>
		</div>
	</section>
<%@ include file="../common/foot.jspf" %>