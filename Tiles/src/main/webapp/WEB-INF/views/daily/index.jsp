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
<title>Daily</title>
</head>
<body>
	<div class="container">

		<%@ include file="/WEB-INF/views/tiles/components/header.jsp"%>

		<div class="jumbotron">
			<h1>Daily INDEX</h1>
			<p>views/daily/index.jsp</p>
		</div>

		<div class="row">
			<c:forEach var="daily" items="${daily}" varStatus="status">
				<div class="col-md-4">
					<div class="thumbnail">
					
						<h3>
						<a href="<c:url value='/daily/${ daily.id }' />">${ daily.title }</a>
						</h3>
					
						<div class="caption">
							<a href="<c:url value='/daily/edit/${ daily.id }' />"
								class="btn btn-lg btn-default">수정</a> <a
								href="<c:url value='/daily/delete/${ daily.id }' />"
								class="btn btn-lg btn-danger">삭제</a>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>

		<s:authorize access="hasRole('ADMIN')">
			<a href="<c:url value="/daily/new" />"
				class="btn btn-block btn-lg btn-primary">일기 등록</a>
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