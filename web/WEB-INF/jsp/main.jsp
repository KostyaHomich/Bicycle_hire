<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="epam.project.command.CommandType" %>
<html lang="en">
<head>
    <title>home</title>
    <meta charset="utf-8">
    <meta name="description" content="La casa free real state fully responsive html5/css3 home page website template"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/responsive.css">
    <link rel="text/javascript" href="${pageContext.request.contextPath}/static/js/jquery.js">
    <link rel="text/javascript" href="${pageContext.request.contextPath}/static/js/main.js">

</head>
<body>
<section class="hero">
    <header>
        <div class="wrapper">
            <a href="#"><img src="${pageContext.request.contextPath}/static/img/logo.png" class="logo" alt=""
                             title=""/></a>
            <a href="#" class="hamburger"></a>
            <nav>
                <ul>
                    <li><a href="#">Rent</a></li>
                    <li><a href="#">About</a></li>
                    <li><a href="#">Contact</a></li>
                </ul>
                <c:if test="${sessionScope.role==null}">
                <a href="${pageContext.request.contextPath}/registration.jsp ?command=${CommandType.SHOW_REGISTRATION_PAGE}" class="login_btn">Registration</a>
                <a href="${pageContext.request.contextPath}/login.jsp ?command=${CommandType.SHOW_LOGIN_PAGE}" class="login_btn">Login</a>
                </c:if>
                <c:if test="${sessionScope.role!=null}">
                <a href="${pageContext.request.contextPath}/admin_page.jsp ?command=${CommandType.SHOW_ADMIN_PAGE}" class="login_btn">Account</a>
                </c:if>

            </nav>
        </div>
    </header>

    <!--  end header section  -->
    <section class="caption">
        <h2 class="caption">Find You Dream Home</h2>
        <h3 class="properties">Apartments - Houses - Mansions</h3>
    </section>
</section>
<!--  end hero section  -->


<section class="listings">
    <div class="wrapper">
        <ul class="properties_list">
            <li>
                <a href="#">
                    <img src="static/img/property_1.jpg" alt="" title=""
                         class="property_img"/>
                </a>
                <span class="price">$2500</span>
                <div class="property_details">
                    <h1>
                        <a href="#">Fuisque dictum tortor at purus libero</a>
                    </h1>
                    <h2>2 kitchens, 2 bed, 2 bath... <span class="property_size">(288ftsq)</span></h2>
                </div>
            </li>
            <li>
                <a href="#">
                    <img src="static/img/property_2.jpg" alt="" title=""
                         class="property_img"/>
                </a>
                <span class="price">$1000</span>
                <div class="property_details">
                    <h1>
                        <a href="#">Fuisque dictum tortor at purus libero</a>
                    </h1>
                    <h2>2 kitchens, 2 bed, 2 bath... <span class="property_size">(288ftsq)</span></h2>
                </div>
            </li>
            <li>
                <a href="#">
                    <img src="static/img/property_3.jpg" alt="" title=""
                         class="property_img"/>
                </a>
                <span class="price">$500</span>
                <div class="property_details">
                    <h1>
                        <a href="#">Fuisque dictum tortor at purus libero</a>
                    </h1>
                    <h2>2 kitchens, 2 bed, 2 bath... <span class="property_size">(288ftsq)</span></h2>
                </div>
            </li>
            <li>
                <a href="#">
                    <img src="/static/img/property_1.jpg" alt="" title="" class="property_img"/>
                </a>
                <span class="price">$2500</span>
                <div class="property_details">
                    <h1>
                        <a href="#">Fuisque dictum tortor at purus libero</a>
                    </h1>
                    <h2>2 kitchens, 2 bed, 2 bath... <span class="property_size">(288ftsq)</span></h2>
                </div>
            </li>
            <li>
                <a href="#">
                    <img src="/static/img/property_2.jpg" alt="" title="" class="property_img"/>
                </a>
                <span class="price">$1000</span>
                <div class="property_details">
                    <h1>
                        <a href="#">Fuisque dictum tortor at purus libero</a>
                    </h1>
                    <h2>2 kitchens, 2 bed, 2 bath... <span class="property_size">(288ftsq)</span></h2>
                </div>
            </li>
            <li>
                <a href="#">
                    <img src="/static/img/property_3.jpg" alt="" title="" class="property_img"/>
                </a>
                <span class="price">$500</span>
                <div class="property_details">
                    <h1>
                        <a href="#">Fuisque dictum tortor at purus libero</a>
                    </h1>
                    <h2>2 kitchens, 2 bed, 2 bath... <span class="property_size">(288ftsq)</span></h2>
                </div>
            </li>
            <li>
                <a href="#">
                    <img src="/static/img/property_1.jpg" alt="" title="" class="property_img"/>
                </a>
                <span class="price">$2500</span>
                <div class="property_details">
                    <h1>
                        <a href="#">Fuisque dictum tortor at purus libero</a>
                    </h1>
                    <h2>2 kitchens, 2 bed, 2 bath... <span class="property_size">(288ftsq)</span></h2>
                </div>
            </li>
            <li>
                <a href="#">
                    <img src="/static/img/property_2.jpg" alt="" title="" class="property_img"/>
                </a>
                <span class="price">$1000</span>
                <div class="property_details">
                    <h1>
                        <a href="#">Fuisque dictum tortor at purus libero</a>
                    </h1>
                    <h2>2 kitchens, 2 bed, 2 bath... <span class="property_size">(288ftsq)</span></h2>
                </div>
            </li>
            <li>
                <a href="#">
                    <img src="/static/img/property_3.jpg" alt="" title="" class="property_img"/>
                </a>
                <span class="price">$500</span>
                <div class="property_details">
                    <h1>
                        <a href="#">Fuisque dictum tortor at purus libero</a>
                    </h1>
                    <h2>2 kitchens, 2 bed, 2 bath... <span class="property_size">(288ftsq)</span></h2>
                </div>
            </li>
        </ul>
        <div class="more_listing">
            <a href="#" class="more_listing_btn">View More Listings</a>
        </div>
    </div>
</section>    <!--  end listing section  -->

<footer>
    <div class="wrapper footer">
        <ul>
            <li class="links">
                <ul>
                    <li><a href="#">About</a></li>
                    <li><a href="#">Support</a></li>
                    <li><a href="#">Terms</a></li>
                    <li><a href="#">Policy</a></li>
                    <li><a href="#">Contact</a></li>
                </ul>
            </li>

            <li class="links">
                <ul>
                    <li><a href="#">Appartements</a></li>
                    <li><a href="#">Houses</a></li>
                    <li><a href="#">Villas</a></li>
                    <li><a href="#">Mansions</a></li>
                    <li><a href="#">...</a></li>
                </ul>
            </li>

            <li class="links">
                <ul>
                    <li><a href="#">New York</a></li>
                    <li><a href="#">Los Anglos</a></li>
                    <li><a href="#">Miami</a></li>
                    <li><a href="#">Washington</a></li>
                    <li><a href="#">...</a></li>
                </ul>
            </li>

            <li class="about">
                <p>La Casa is real estate minimal html5 website template, designed and coded by pixelhint, tellus
                    varius, dictum erat vel, maximus tellus. Sed vitae auctor ipsum</p>
                <ul>
                    <li><a href="http://facebook.com/pixelhint" class="facebook" target="_blank"></a></li>
                    <li><a href="http://twitter.com/pixelhint" class="twitter" target="_blank"></a></li>
                    <li><a href="http://plus.google.com/+Pixelhint" class="google" target="_blank"></a></li>
                    <li><a href="#" class="skype"></a></li>
                </ul>
            </li>
        </ul>
    </div>

    <div class="copyrights wrapper">
        Copyright © 2015 <a href="http://pixelhint.com" target="_blank" class="ph_link"
                            title="Download more free Templates">Pixelhint.com</a>. All Rights Reserved.
    </div>
</footer><!--  end footer  -->

</body>
</html>