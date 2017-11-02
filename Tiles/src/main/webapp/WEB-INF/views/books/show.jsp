<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ page pageEncoding="utf-8"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
<title>Books</title>
</head>
<body>
	<div class="container">

		<%@ include file="/WEB-INF/views/tiles/components/header.jsp"%>


		<div class="jumbotron">
			<h1>${ book.title }</h1>
			<p>${ book.author }저</p>
		</div>
		<div class="thumbnail">
			<img src="${ book.image }">
		</div>
		<div class="page-header">
			<h2>리뷰</h2>
		</div>
		<c:url var="reviewsPath" value="/reviews" />
		<f:form modelAttribute="review" action="${ reviewsPath }"
			method="post">
			<c:forEach var="error" items="${ fieldErrors }">
				<div class="alert alert-warning">
					<strong>${ error.getField() }</strong>: ${ error.getDefaultMessage() }
				</div>
			</c:forEach>
			<f:textarea path="text" cssClass="form-control" rows="5" />
			<f:hidden path="bookId" />
			<f:hidden path="userId" />
			<button class="btn btn-block btn-primary" type="submit">리뷰
				등록</button>
		</f:form>
		<table class="table table-stripped">
			<thead>
				<tr>
					<th>User</th>
					<th>Text</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="review" items="${ reviews }">
					<tr>
						<td>익명</td>
						<td>${ review.text }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="<c:url value="/js/bootstrap.min.js" />"></script>

</body>
</html>