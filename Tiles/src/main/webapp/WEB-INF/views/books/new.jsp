<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ page pageEncoding="utf-8" session="false"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet">
<title>Books NEW</title>
</head>
<body>
	<div class="container">

		<%@ include file="/WEB-INF/views/tiles/components/header.jsp"%>

		<div class="jumbotron">
			<h1>Books NEW</h1>
			<p>views/books/new.jsp</p>
		</div>

		<!-- new에서 받은 값을 books로 보낸다. -->
		<form action="<c:url value="/books" />" method="post"
			enctype="multipart/form-data">
			<div class="form-group form-group-lg">
				<label class="control-label">도서 제목</label> <input name="title"
					type="text" class="form-control">
			</div>
			<div class="form-group form-group-lg">
				<label class="control-label">저자</label> <input name="author"
					type="text" class="form-control">
			</div>
			<div class="form-group form-group-lg">
				<label class="control-label">이미지</label> <input name="file"
					type="file">
			</div>
			
			
			<div class="form-action">
				<sec:csrfInput/>
				<button type="submit" class="btn btn-lg btn-primary">전송</button>
			</div>
				
		</form>
	</div>
</body>
</html>