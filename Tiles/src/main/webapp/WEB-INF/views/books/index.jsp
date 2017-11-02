<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="s"%>
<%@ page pageEncoding="utf-8" session="false"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- CSS -->
<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/css/jquery-ui.min.css" />" rel="stylesheet">
<title>Books</title>
</head>
<body>
	<div class="container">

		<%@ include file="/WEB-INF/views/tiles/components/header.jsp"%>

		<div class="jumbotron">
			<h1>Books INDEX</h1>
			<p>views/books/index.jsp</p>
		</div>

		<div class="search">
			<form action="<c:url value="/books/search" />" method="get">
				<div class="row">
					<div class="col-md-10">
						<input name="query" id="searchBook" class="form-control input-lg"
							type="text" placeholder="도서명으로 검색">
					</div>
					<div class="col-md-2">
						<button type="submit"
							class="form-control input-lg btn btn-primary">
							<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
							검색
						</button>
					</div>
				</div>
			</form>
		</div>

		<script>
			$(function() {
				$("#searchBook").autocomplete({
					source : function(request, response) {
						$.ajax({
							type : 'get',
							url : "<c:url value='/api/books/search'/>",
							data : {
								term : request.term
							},
							success : function(data) {
								var bookList = data.bookList;
								response($.map(bookList, function(item) {
									return item.title;
								}));
							}
						});
					}
				});
			});
		</script>

		<div class="row">
			<c:forEach var="book" items="${books}" varStatus="status">
				<div class="col-md-4">
					<div class="thumbnail">
						<c:url var="show" value="/books/${ book.id }" />
						<a href="${ show }"> <img src="${ book.image }" />
						</a>

						<div class="caption">
							<h3>${ book.title }
								<small>${ book.author }</small>
							</h3>
							<s:authorize access="hasRole('ADMIN')">
								<a href="<c:url value='/books/edit/${ book.id }' />"
									class="btn btn-lg btn-default">수정</a>
								<a href="<c:url value='/books/delete/${ book.id }' />"
									class="btn btn-lg btn-danger">삭제</a>
							</s:authorize>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>

		<s:authorize access="hasRole('ADMIN')">
			<a href="<c:url value="/books/new" />"
				class="btn btn-block btn-lg btn-primary">도서등록</a>
		</s:authorize>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<c:url value="/js/bootstrap.min.js" />"></script>

<!-- JS -->
<Script src="<c:url value="/js/jquery.min.js" />"></script>
<Script src="<c:url value="/js/jquery-ui.min.js" />"></script>
</body>
</html>

