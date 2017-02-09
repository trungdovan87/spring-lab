<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring Boot Application</title>
</head>
<body>
    <h2> Welcome to Spring Class </h2>
    <p>
        <a href="/"> Trang chu </a>
        <sec:authorize access="!hasAnyRole('ROLE_USER')">
            <a href="/dang-nhap" style="margin-left: 30px"> Dang Nhap </a>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_USER')">
            <a href="/nguoi-dung" style="margin-left: 30px"> Nguoi Dung </a>
            <a href="javascript:document.getElementById('logout').submit()" style="margin-left: 30px"> Dang Xuat </a>
        </sec:authorize>
    </p>
    <form action="/j_spring_security_logout" id="logout" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
	<tiles:insertAttribute name="body" />
</body>
</html>