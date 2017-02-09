<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<tiles:insertDefinition name="template">
	<tiles:putAttribute name="body">
		<p>Login to Application</p>
		<c:if test="${not empty error}"> <p> Sai ten dang nhap </p> </c:if>
		<form method="post" action="/j_spring_security_check">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<p>
				<input type="text" name="username" id="username" placeholder="Username">
			</p>
			<p>
				<input type="password" name="password" id="password" placeholder="Password">
			</p>
			<p class="submit">
				<input type="submit" name="commit" value="Login">
			</p>
		</form>

		<form name='facebookSocialloginForm' action="/auth/facebook" method="POST">
            <input type="hidden" name="scope" value="public_profile,email,user_about_me,user_birthday"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit">
                <i>Sign In with Facebook</i>
            </button>
            <div class="clear"></div>
		</form>
	</tiles:putAttribute>
</tiles:insertDefinition>