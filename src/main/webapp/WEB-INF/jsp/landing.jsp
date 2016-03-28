<%@include file="commons/init.jspf"%>

<!DOCTYPE html>
<html class="no-js" lang="en">
    <%@include file="commons/head.jspf" %>
    <body class="container">
        <%@include file="commons/header.jspf" %>
        <section>
            <c:choose>
                <c:when test="${page eq '/passengers'}">
                    <%@include file="passenger/list.jspf" %>
                </c:when>
                <c:when test="${page eq '/routes'}">
                    <%@include file="route/list.jspf" %>
                </c:when>
                <c:when test="${page eq '/planes'}">
                    <%@include file="plane/list.jspf" %>
                </c:when>
                <c:when test="${page eq '/flights'}">
                    <%@include file="flight/list.jspf" %>
                </c:when>
            </c:choose>
        </section>
    </body>
    <%@include file="commons/footer.jspf" %>
</html>