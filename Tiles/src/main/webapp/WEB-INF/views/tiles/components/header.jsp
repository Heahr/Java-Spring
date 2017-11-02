<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ page pageEncoding="utf-8"%>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="<c:url value="/js/bootstrap.min.js" />"></script>
<nav class="navbar navbar-default">
	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header">
		<button type="button" class="navbar-toggle collapsed"
			data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
			aria-expanded="false">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<a class="navbar-brand" href="/tiles">Home</a>
	</div>
	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav navbar-left">

			<!-- 관리자 페이지 버튼 -->
			<sec:authorize access="hasRole('ADMIN')">
				<li><a href="<c:url value='/admin'/>">관리</a></li>
			</sec:authorize>

			<li><a href="<c:url value='/books'/>">도서</a></li>

			<li><a href="<c:url value='/daily'/>">일기</a></li>
		</ul>
		<!-- 로그인 버튼 -->
		<sec:authorize access="isAnonymous()">
			<c:url var="loginUrl" value="/login" />
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${ loginUrl }">로그인</a></li>
			</ul>
		</sec:authorize>
		<!-- 로그아웃 버튼 -->
		<sec:authorize access="isAuthenticated()">
			<c:url var="logoutUrl" value="/logout" />
			<form action="${logoutUrl}" method="post"
				class="navbar-form navbar-right">
				<button type="submit" class="btn btn-default">로그아웃</button>
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
		</sec:authorize>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>
<!-- JS -->
<Script src="<c:url value="/js/jquery.min.js" />"></script>
<Script src="<c:url value="/js/bootstrap.min.js" />"></script>
<Script src="<c:url value="/js/jquery-ui.min.js" />"></script>