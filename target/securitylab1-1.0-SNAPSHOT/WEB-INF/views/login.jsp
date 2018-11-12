<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Log in with your account</title>

    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/common.css" rel="stylesheet">
    <!-- reCAPTCHA with English language -->
    <script src='https://www.google.com/recaptcha/api.js'></script>
</head>

<body>

<div class="container">

    <form method="POST" action="${pageContext.request.contextPath}/login" class="form-signin">
        <h2 class="form-heading">Log in</h2>
        <p style=" color: red;">${error}</p>
        <input name="username" type="text" class="form-control" value="${username}" placeholder="Username"
                   autofocus="true"/>
        <input name="password" type="password" class="form-control" value="${password}" placeholder="Password"/>

        <!-- reCAPTCHA -->
        <div class="g-recaptcha" data-sitekey="6LePCncUAAAAAJfRtDtoLBMcz5ufFBcpujtfNW0a"></div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>

    </form>

</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>