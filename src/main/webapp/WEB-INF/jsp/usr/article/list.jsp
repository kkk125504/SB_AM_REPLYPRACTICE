<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="${board.name}" />
<%@ include file="../common/head.jspf" %>
	<section class="mt-8 text-xl">
		<div class="container mx-auto px-3">
			<div class="flex">
				<div>
					총 게시물 갯수 : <span class="badge">${articlesCount }&nbsp;개</span>
				</div>
				<div class="flex-grow"></div>
				<form class="flex">
					<input type="hidden" name="boardId" value="${board.id}" />

					<select data-value="${param.searchKeywordType}" name="searchKeywordType" class="select select-bordered">
						<option value="title">제목</option>
						<option value="body">내용</option>
						<option value="title,body">제목 + 내용</option>
					</select>
					
					<input name="searchKeyword" type="text" class="ml-2 w-96 input input-bordered" placeholder="검색어를 입력해주세요."
					maxlength="20" value="${param.searchKeyword }"/>
					
					<button type="submit" class="ml-2 btn btn-ghost">SEARCH</button>
				</form>
			</div>
			<div class="table-box-type-1 mt-2">				
				<table class="table-fixed">
				<colgroup>
				<col width ="80" />
				<col width ="200"/>
				<col />
				<col width ="200"/>
				<col width ="100"/>
				<col width ="100"/>
				</colgroup>
						<thead class="bg-gray-200">
							<tr>
								<th>번호</th>
								<th>날짜</th>
								<th>제목</th>
								<th>작성자</th>
								<th>조회수</th>
								<th>추천</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="article" items="${articles}">
								<tr>
									<td>${article.id } </td>
									<td>${article.forPrintType1RegDate}</td>
									<td><a class="hover:underline block w-full truncate" href="../article/detail?id=${article.id}">${article.title}</a></td>
									<td>${article.extra__writer}</td>
									<td>${article.hitCount}</td>
									<td>${article.goodReactionPoint}</td>
								</tr>
							</c:forEach>
						</tbody>
				</table>
			</div>
			<div class="page-menu mt-3 flex justify-center">
				<c:set var="pageMenuLen" value="5"/>
				<c:set var="startPage" value="${page - pageMenuLen >= 1 ? page - pageMenuLen : 1}"/>
				<c:set var="endPage" value="${page + pageMenuLen <= pagesCount ? page + pageMenuLen : pagesCount}"/>
				<c:set var="pageBaseUri" value="?boardId=${board.id}"/>
				<c:set var="pageBaseUri" value="${pageBaseUri }&searchKeywordType=${param.searchKeywordType}"/>
				<c:set var="pageBaseUri" value="${pageBaseUri}&searchKeyword=${param.searchKeyword}"/>

				<div class="btn-group">
					<c:if test="${startPage>1 }">
						<a class="btn btn-md" href="${pageBaseUri }&page=1">1</a>
					</c:if>
					<c:if test="${startPage > 2 }">
						<a class="btn btn-md btn-disabled" href="#">...</a>
					</c:if>
					<c:forEach begin="${startPage }" end="${endPage}" var="i">
						<a class="btn btn-md ${page == i ? 'btn-active' : '' }" href="${pageBaseUri }&page=${i }">${i }</a>
					</c:forEach>
					<c:if test="${endPage < pagesCount-1 }">
						<a class="btn btn-md btn-disabled" href="#">...</a>
					</c:if>
					<c:if test="${endPage < pagesCount }">
						<a class="btn btn-md" href="${pageBaseUri }&page=${pagesCount }">${pagesCount }</a>
					</c:if>
				</div>				
			</div>
		</div>
	</section>
<%@ include file="../common/foot.jspf" %>