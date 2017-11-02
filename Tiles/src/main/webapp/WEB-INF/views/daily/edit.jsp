<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="utf-8" session="false"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
<title>Daily EDIT</title>
</head>
<body>
	<div class="container">

		<%@ include file="/WEB-INF/views/tiles/components/header.jsp"%>

		<div class="jumbotron">
			<h1>Daily EDIT</h1>
			<p>views/daily/edit.jsp</p>
		</div>

		<!-- action 값을 잘 설정해 주세요 -->
		<form action="<c:url value='/daily/update' />" method="post">
			<div class="form-group form-group-lg">
				<label class="control-label">일기 제목</label> <input name="title"
					type="text" class="form-control" value="${ daily.title }">
			</div>
			<div class="form-group form-group-lg">
				<label class="control-label">내용</label>
				<textarea class="form-control" rows="10" name="author">${daily.author}</textarea>
			</div>

			<input name="id" type="hidden" value="${ daily.id }">
			<button type="submit" class="btn btn-lg btn-primary">전송</button>
		</form>

	</div>
</body>
</html>