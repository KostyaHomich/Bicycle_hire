<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="epam.project.command.CommandType" %>
<%@ page import="epam.project.entity.UserRole" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="lang" tagdir="/WEB-INF/tags" %>

<div class="container">
    <section id="content">

        <form action="${pageContext.request.contextPath}/bicycle_list" method="post">

            <h1><fmt:message key="page.title.bicycle_details"/></h1>

            <c:if test="${not empty requestScope.error}">
                <fmt:message key="${requestScope.error}"/>
            </c:if>

            <c:if test="${not empty requestScope.errorsList}">
                <c:forEach var="entry" items="${requestScope.errorsList.getErrors()}">
                    <c:if test="${entry.value.size() >0}">
                        <c:forEach var="value" items="${entry.value}">
                            <fmt:message key="${value}"/>
                        </c:forEach>
                    </c:if>
                </c:forEach>
            </c:if>
            <input type="hidden" name="bicycleId" value="${requestScope.bicycle.getId()}">
            <div>
                <input type="text"
                        <c:if test="${not empty sessionScope.signInUser
                        && sessionScope.signInUser.role.equalsIgnoreCase(UserRole.USER)}">
                            readonly
                        </c:if>
                       value="${requestScope.bicycle.getName()}"
                       placeholder="<fmt:message key="bicycle.name"/>"
                       id="name"
                       name="name"
                       required
                       minlength="4"
                       maxlength="25"
                       required
                       pattern="^[a-zA-Z]{4,25}$"
                       title="<fmt:message key="bicycle.error.invalid_name"/>"
                />
            </div>
            <div>
                <input type="text"
                        <c:if test="${not empty sessionScope.signInUser
                        && sessionScope.signInUser.role.equalsIgnoreCase(UserRole.USER)}">
                            readonly
                        </c:if>
                       value="${requestScope.bicycle.getDaily_rental_price()}"
                       placeholder="<fmt:message key="bicycle.daily_rental_price"/>"
                       id="daily_rental_price"
                       name="daily_rental_price"
                       required
                       pattern="^[1-9]{1,10}$"
                       title="<fmt:message key="bicycle.error.invalid_daily_rental_price"/>"
                />
            </div>
            <c:if test="${not empty sessionScope.signInUser
            && sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN)}">
                <div>
                    <p><fmt:message key="bicycle.status"/></p>
                    <select id="status" name="status">
                        <option
                                <c:if test="${requestScope.bicycle.getStatus().equalsIgnoreCase('broken')}">
                                    selected
                                </c:if>>
                            broken
                        </option>

                        <option
                                <c:if test="${requestScope.bicycle.getStatus().equalsIgnoreCase('available')}">
                                    selected
                                </c:if>>
                            available
                        </option>
                        <option
                                <c:if test="${requestScope.bicycle.getStatus().equalsIgnoreCase('rented')}">
                                    selected
                                </c:if>>
                            rented
                        </option>
                    </select>
                </div>
            </c:if>
            <div>
                <textarea style="height: 60px;width: 300px;"
                        <c:if test="${not empty sessionScope.signInUser && sessionScope.signInUser.role.equalsIgnoreCase(UserRole.USER)}">
                            readonly
                        </c:if>
                          placeholder="<fmt:message key="bicycle.description"/>" id="description" name="description">
                    ${requestScope.bicycle.getDescription()}
                </textarea>
            </div>
            <div>
                <c:if test="${not empty sessionScope.signInUser
            && sessionScope.signInUser.role.equalsIgnoreCase(UserRole.ADMIN)}">
                    <c:if test="${requestScope.bicycle.getId()==0}">
                        <input type="hidden" name="pointHireId" value="${requestScope.bicycle.getPoint_hire_id()}">
                        <input type="hidden" name="command" value="${CommandType.ADD_BICYCLE}">
                        <input style="display: inline-block;" type="submit"
                               value="<fmt:message key="page.button.add"/>">
                    </c:if>

                    <c:if test="${requestScope.bicycle.getId()!=0}">
                        <input type="hidden" name="command" value="${CommandType.UPDATE_BICYCLE}">

                        <input style="display: inline-block;" type="submit"
                               value="<fmt:message key="page.button.update"/>">
                    </c:if>

                </c:if>
                <form style="display: inline-block; text-align:right;"
                      action="${pageContext.request.contextPath}/bicycle_details" method="post">
                    <div>
                        <input type="submit" value="<fmt:message key="page.button.back"/>">
                        <input type="hidden" name="command" value="${requestScope.lastCommand}">
                    </div>
                </form>
            </div>

        </form>

    </section>
</div>

