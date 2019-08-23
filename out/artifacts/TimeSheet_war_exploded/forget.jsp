<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: TOSHIBA
  Date: 8/7/2019
  Time: 11:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Login V18</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="Login-template/images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="Login-template/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="Login-template/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="Login-template/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="Login-template/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="Login-template/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="Login-template/vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="Login-template/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="Login-template/vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="Login-template/css/util.css">
    <link rel="stylesheet" type="text/css" href="Login-template/css/main.css">
    <!--===============================================================================================-->
</head>
<body style="background-color: #666666;">

<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <form action="cs?action=forgetPassword" method="post" class="login100-form validate-form"> <%--form submit ile gonderirik--%>
                <span class="login100-form-title p-b-43">
						Forget Password
					</span>

                <div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
                    <input class="input100" type="text" id="usernameId" name="email"  required>
                    <span class="focus-input100"></span>
                    <span class="label-input100">Email</span>
                </div>



                <c:if test="${not empty invalid}">
                    <label class="error">${invalid}</label>
                </c:if>
                <br>

                <div class="flex-sb-m w-full p-t-3 p-b-32">
                    <div class="contact100-form-checkbox">
                        <input class="input-checkbox100" id="ckb1" type="checkbox" name="remember-me">
                        <label class="label-checkbox100" for="ckb1">
                            Remember me
                        </label>
                    </div>

                    <%--<div>
                        <a href="cs?action=forgetView" class="txt1">
                            Forgot Password?
                        </a>
                    </div>--%>
                </div>


                <div class="container-login100-form-btn">
                    <%--<input type="submit" value="Log In" >--%>
                    <button type="submit" class="login100-form-btn">
                        Change Password
                    </button>
                </div>

                <div class="text-center p-t-46 p-b-20">
						<span class="txt2">
							or sign up using
						</span>
                </div>

                <div class="login100-form-social flex-c-m">
                    <a href="#" class="login100-form-social-item flex-c-m bg1 m-r-5">
                        <i class="fa fa-facebook-f" aria-hidden="true"></i>
                    </a>

                    <a href="#" class="login100-form-social-item flex-c-m bg2 m-r-5">
                        <i class="fa fa-twitter" aria-hidden="true"></i>
                    </a>
                </div>
            </form>

            <div class="login100-more" style="background-image: url('Login-template/images/bg-01.jpg');">
            </div>
        </div>
    </div>
</div>





<!--===============================================================================================-->
<script src="Login-template/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="Login-template/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="Login-template/vendor/bootstrap/js/popper.js"></script>
<script src="Login-template/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="Login-template/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="Login-template/vendor/daterangepicker/moment.min.js"></script>
<script src="Login-template/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="Login-template/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
<script src="Login-template/js/main.js"></script>

</body>
</html>
