<%@ page import="java.util.ArrayList" %>
<%@ page import="com.web.jsp_coffee.dao.Product" %><%--
  Created by IntelliJ IDEA.
  User: My-PC
  Date: 7/2/2024
  Time: 10:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Coffee - Free Bootstrap 4 Template by Colorlib</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href="https://fonts.googleapis.com/css?family=Poppins:300,400,500,600,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Sans:400,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Great+Vibes" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/web/css/open-iconic-bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/web/css/animate.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/web/css/owl.carousel.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/web/css/owl.theme.default.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/web/css/magnific-popup.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/web/css/aos.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/web/css/ionicons.min.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/web/css/bootstrap-datepicker.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/web/css/jquery.timepicker.css">


    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/web/css/flaticon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/web/css/icomoon.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/views/web/css/style.css">
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
    <div class="container">
        <a class="navbar-brand" href="trang-chu">Coffee<small>Blend</small></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="oi oi-menu"></span> Menu
        </button>
        <div class="collapse navbar-collapse" id="ftco-nav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a href="trang-chu" class="nav-link">Home</a></li>
                <li class="nav-item"><a href="menu.jsp" class="nav-link">Menu</a></li>
                <li class="nav-item"><a href="services.jsp" class="nav-link">Services</a></li>
                <li class="nav-item"><a href="blog.jsp" class="nav-link">Blog</a></li>
                <li class="nav-item"><a href="about.jsp" class="nav-link">About</a></li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="room.html" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Shop</a>
                    <div class="dropdown-menu" aria-labelledby="dropdown04">
                        <a class="dropdown-item" href="shop.jsp">Shop</a>
                        <a class="dropdown-item" href="product-single.jsp">Single Product</a>
                        <a class="dropdown-item" href="cart.jsp">Cart</a>
                        <a class="dropdown-item" href="checkout.jsp">Checkout</a>
                    </div>
                </li>
                <li class="nav-item"><a href="contact.jsp" class="nav-link">Contact</a></li>
                <li class="nav-item cart"><a href="${pageContext.request.contextPath}/cart" class="nav-link"><span class="icon icon-shopping_cart"></span><span class="bag d-flex justify-content-center align-items-center"><small>${count}</small></span></a></li>
            </ul>
        </div>
    </div>
</nav>
<!-- END nav -->

<section class="home-slider owl-carousel">

    <div class="slider-item" style="background-image: url(images/bg_3.jpg);" data-stellar-background-ratio="0.5">
        <div class="overlay"></div>
        <div class="container">
            <div class="row slider-text justify-content-center align-items-center">

                <div class="col-md-7 col-sm-12 text-center ftco-animate">
                    <h1 class="mb-3 mt-5 bread">Cart</h1>
                    <p class="breadcrumbs"><span class="mr-2"><a href="index.html">Home</a></span> <span>Cart</span></p>
                </div>

            </div>
        </div>
    </div>
</section>

<section class="ftco-section ftco-cart">
    <div class="container">
        <div class="row">
            <div class="col-md-12 ftco-animate">
                <div class="cart-list">
                    <table class="table">
                        <thead class="thead-primary">
                        <tr class="text-center">
                            <th>&nbsp;</th>
                            <th>&nbsp;</th>
                            <th>Product</th>
                            <th>Price</th>
                            <th>Quantity</th>
                            <th>Total</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            ArrayList<Product> list = (ArrayList<Product>) session.getAttribute("cart");
                            if (list != null && list.size() > 0){
                                for (Product product : list) {
                        %>
                        <tr class="text-center">
                            <td class="product-remove"><a href="${pageContext.request.contextPath}/remove-cart?idProduct=<%=product.getProductID()%>"><span class="icon-close"></span></a></td>

                            <td class="image-prod"><div class="img" style="background-image:url(<%=product.getImageURL()%>);"></div></td>

                            <td class="product-name">
                                <h3><%=product.getName()%></h3>
                                <p><%=product.getMoTa()%></p>
                            </td>

                            <td class="price">$<%=product.getPrice()%></td>

                            <td class="quantity">
                                <div class="input-group mb-3">
                                    <input type="number" name="quantity" class="quantity form-control input-number " onchange="handleOnchange(event)" value=<%=product.getQuantity()%> min="1" max="100"  data-product-id="<%=product.getProductID()%>">
                                </div>
                            </td>

                            <td class="total">$<%=String.format("%.2f", Float.parseFloat(product.getPrice()) * product.getQuantity())%></td>
                        </tr><!-- END TR-->
                        <%
                                }
                            }else {
                        %>
                        <tr class="text-center">
                            <td colspan="6">No product in cart</td>
                        </tr>
                        <%
                            }
                        %>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="row justify-content-end">
            <div class="col col-lg-3 col-md-6 mt-5 cart-wrap ftco-animate">
                <div class="cart-total mb-3">
                    <h3>Cart Totals</h3>
                    <p class="d-flex">
                        <span>Subtotal</span>
                        <span class="tien-phai-tra">$20.60</span>
                    </p>
                    <p class="d-flex">
                        <span>Delivery</span>
                        <span>$0.00</span>
                    </p>
                    <p class="d-flex">
                        <span>Discount</span>
                        <span class="giam-gia">$3.00</span>
                    </p>
                    <hr>
                    <p class="d-flex total-price">
                        <span>Total</span>
                        <span class="tong-tien-thanh-toan">$17.60</span>
                    </p>
                </div>
                <p class="text-center"><a href="${pageContext.request.contextPath}/checkout" class="btn btn-primary py-3 px-4">Proceed to Checkout</a></p>
            </div>
        </div>
    </div>
</section>

<section class="ftco-section">
    <div class="container">
        <div class="row justify-content-center mb-5 pb-3">
            <div class="col-md-7 heading-section ftco-animate text-center">
                <span class="subheading">Discover</span>
                <h2 class="mb-4">Related products</h2>
                <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
            </div>
        </div>
        <div class="row">

            <div class="col-md-3">
                <div class="menu-entry">
                    <a href="#" class="img" style="background-image: url(${pageContext.request.contextPath}/views/web/images/menu-1.jpg);"></a>
                    <div class="text text-center pt-4">
                        <h3><a href="#">Coffee Capuccino</a></h3>
                        <p>A small river named Duden flows by their place and supplies</p>
                        <p class="price"><span>$5.90</span></p>
                        <p><a href="#" class="btn btn-primary btn-outline-primary">Add to Cart</a></p>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="menu-entry">
                    <a href="#" class="img" style="background-image: url(${pageContext.request.contextPath}/views/web/images/menu-2.jpg);"></a>
                    <div class="text text-center pt-4">
                        <h3><a href="#">Coffee Capuccino</a></h3>
                        <p>A small river named Duden flows by their place and supplies</p>
                        <p class="price"><span>$5.90</span></p>
                        <p><a href="#" class="btn btn-primary btn-outline-primary">Add to Cart</a></p>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="menu-entry">
                    <a href="#" class="img" style="background-image: url(${pageContext.request.contextPath}/views/web/images/menu-3.jpg);"></a>
                    <div class="text text-center pt-4">
                        <h3><a href="#">Coffee Capuccino</a></h3>
                        <p>A small river named Duden flows by their place and supplies</p>
                        <p class="price"><span>$5.90</span></p>
                        <p><a href="#" class="btn btn-primary btn-outline-primary">Add to Cart</a></p>
                    </div>
                </div>
            </div>
            <div class="col-md-3">
                <div class="menu-entry">
                    <a href="#" class="img" style="background-image: url(${pageContext.request.contextPath}/views/web/images/menu-4.jpg);"></a>
                    <div class="text text-center pt-4">
                        <h3><a href="#">Coffee Capuccino</a></h3>
                        <p>A small river named Duden flows by their place and supplies</p>
                        <p class="price"><span>$5.90</span></p>
                        <p><a href="#" class="btn btn-primary btn-outline-primary">Add to Cart</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<footer class="ftco-footer ftco-section img">
    <div class="overlay"></div>
    <div class="container">
        <div class="row mb-5">
            <div class="col-lg-3 col-md-6 mb-5 mb-md-5">
                <div class="ftco-footer-widget mb-4">
                    <h2 class="ftco-heading-2">About Us</h2>
                    <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
                    <ul class="ftco-footer-social list-unstyled float-md-left float-lft mt-5">
                        <li class="ftco-animate"><a href="#"><span class="icon-twitter"></span></a></li>
                        <li class="ftco-animate"><a href="#"><span class="icon-facebook"></span></a></li>
                        <li class="ftco-animate"><a href="#"><span class="icon-instagram"></span></a></li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-4 col-md-6 mb-5 mb-md-5">
                <div class="ftco-footer-widget mb-4">
                    <h2 class="ftco-heading-2">Recent Blog</h2>
                    <div class="block-21 mb-4 d-flex">
                        <a class="blog-img mr-4" style="background-image: url(${pageContext.request.contextPath}/views/web/images/image_1.jpg);"></a>
                        <div class="text">
                            <h3 class="heading"><a href="#">Even the all-powerful Pointing has no control about</a></h3>
                            <div class="meta">
                                <div><a href="#"><span class="icon-calendar"></span> Sept 15, 2018</a></div>
                                <div><a href="#"><span class="icon-person"></span> Admin</a></div>
                                <div><a href="#"><span class="icon-chat"></span> 19</a></div>
                            </div>
                        </div>
                    </div>
                    <div class="block-21 mb-4 d-flex">
                        <a class="blog-img mr-4" style="background-image: url(${pageContext.request.contextPath}/views/web/images/image_2.jpg);"></a>
                        <div class="text">
                            <h3 class="heading"><a href="#">Even the all-powerful Pointing has no control about</a></h3>
                            <div class="meta">
                                <div><a href="#"><span class="icon-calendar"></span> Sept 15, 2018</a></div>
                                <div><a href="#"><span class="icon-person"></span> Admin</a></div>
                                <div><a href="#"><span class="icon-chat"></span> 19</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-2 col-md-6 mb-5 mb-md-5">
                <div class="ftco-footer-widget mb-4 ml-md-4">
                    <h2 class="ftco-heading-2">Services</h2>
                    <ul class="list-unstyled">
                        <li><a href="#" class="py-2 d-block">Cooked</a></li>
                        <li><a href="#" class="py-2 d-block">Deliver</a></li>
                        <li><a href="#" class="py-2 d-block">Quality Foods</a></li>
                        <li><a href="#" class="py-2 d-block">Mixed</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 mb-5 mb-md-5">
                <div class="ftco-footer-widget mb-4">
                    <h2 class="ftco-heading-2">Have a Questions?</h2>
                    <div class="block-23 mb-3">
                        <ul>
                            <li><span class="icon icon-map-marker"></span><span class="text">203 Fake St. Mountain View, San Francisco, California, USA</span></li>
                            <li><a href="#"><span class="icon icon-phone"></span><span class="text">+2 392 3929 210</span></a></li>
                            <li><a href="#"><span class="icon icon-envelope"></span><span class="text">info@yourdomain.com</span></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 text-center">

                <p><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                    Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="icon-heart" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
                    <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
            </div>
        </div>
    </div>
</footer>



<!-- loader -->
<div id="ftco-loader" class="show fullscreen"><svg class="circular" width="48px" height="48px"><circle class="path-bg" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke="#eeeeee"/><circle class="path" cx="24" cy="24" r="22" fill="none" stroke-width="4" stroke-miterlimit="10" stroke="#F96D00"/></svg></div>


<script src="${pageContext.request.contextPath}/views/web/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/views/web/js/jquery-migrate-3.0.1.min.js"></script>
<script src="${pageContext.request.contextPath}/views/web/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/views/web/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/views/web/js/jquery.easing.1.3.js"></script>
<script src="${pageContext.request.contextPath}/views/web/js/jquery.waypoints.min.js"></script>
<script src="${pageContext.request.contextPath}/views/web/js/jquery.stellar.min.js"></script>
<script src="${pageContext.request.contextPath}/views/web/js/owl.carousel.min.js"></script>
<script src="${pageContext.request.contextPath}/views/web/js/jquery.magnific-popup.min.js"></script>
<script src="${pageContext.request.contextPath}/views/web/js/aos.js"></script>
<script src="${pageContext.request.contextPath}/views/web/js/jquery.animateNumber.min.js"></script>
<script src="${pageContext.request.contextPath}/views/web/js/bootstrap-datepicker.js"></script>
<script src="${pageContext.request.contextPath}/views/web/js/jquery.timepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/views/web/js/scrollax.min.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBVWaKrjvy3MaE7SQ74_uJiULgl1JY0H2s&sensor=false"></script>
<script src="${pageContext.request.contextPath}/views/web/js/google-map.js"></script>
<script src="${pageContext.request.contextPath}/views/web/js/main.js"></script>

<script>
    let tong_tien = document.querySelector('.tong-tien-thanh-toan');
    let tien_phai_tra = document.querySelector('.tien-phai-tra');
    let giam_gia = document.querySelector('.giam-gia');
    let sum = 0;
    let list_prices = document.querySelectorAll(".total");
    list_prices.forEach(function (price) {
        sum += parseFloat(price.innerHTML.substring(1));
    });
    tien_phai_tra.innerHTML = "$" + sum.toFixed(2);
    giam_gia.innerHTML = "$" + (sum * 0.15).toFixed(2);
    tong_tien.innerHTML = "$" + (sum - (sum * 0.15)).toFixed(2);

    function handleOnchange(event) {
        let quantity = event.target.value;

        let price = event.target.parentElement.parentElement.parentElement.querySelector('.price').innerHTML.substring(1);
        let total = event.target.parentElement.parentElement.parentElement.querySelector('.total');
        total.innerHTML = "$" + (parseFloat(price) * quantity);
        sum = 0;
        list_prices = document.querySelectorAll(".total");
        list_prices.forEach(function (price) {
            sum += parseFloat(price.innerHTML.substring(1));
        });
        tien_phai_tra.innerHTML = "$" + sum.toFixed(2);
        giam_gia.innerHTML = "$" + (sum * 0.15).toFixed(2);
        tong_tien.innerHTML = "$" + (sum - (sum * 0.15)).toFixed(2);
        const productId = event.target.dataset.productId;
       if (productId && quantity) {
           handleSendUpdate(quantity, productId);
       }

    }
    var contextPath = '<%= request.getContextPath() %>';

    function handleSendUpdate(quantity, idProduct) {
        const xhr = new XMLHttpRequest();
        xhr.open('GET', contextPath+"/update-cart?quantity="+quantity+"&idProduct="+idProduct, true);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.send();
        // Optional: handle the server response
        xhr.onload = function() {
            if (xhr.status != 200) { // analyze HTTP response status
                alert(`Error ${xhr.status}: ${xhr.statusText}`); // e.g. 404: Not Found
            } else { // show the result
                console.log(`Done, server response: ${xhr.response}`); // response is the server
            }
        };

        xhr.onerror = function() {
            alert("Request failed");
        };
    }
</script>

</body>
</html>