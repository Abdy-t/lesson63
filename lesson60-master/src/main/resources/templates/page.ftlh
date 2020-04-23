<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lesson 57. Javascript 101</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
    <link rel="stylesheet" href="/css/styles.css">
    <script src="/js/script.js"></script>
</head>
<body id="no-scroll" class="no-scroll"  onload="addPostElements()">
<button class="close-button" onclick="splashScreenHiddenFalse()">Exit</button>
<h3>Add new post</h3>
<form id="form" name="form" enctype="multipart/form-data" >
    <input type="hidden" name="idUser" value="${user.id}">
    <input type="file" name="photo">
    <input type="text" placeholder="description" name="description">
    <button type="submit" id="save-photo">Publish</button>
</form>

<div class="container">
    <div class="row justify-content-center">
        <div class="col col-lg-7 posts-container" id="posts">
            <!-- single post start -->
        </div>
    </div>
</div>

<section id="page-splash" class="page-splash">
    <button class="open-button"><a class="back flex align-center" href="/registration">Registration</a></button>
    <div class="d-flex justify-content-center">
        <div class="mx-3">
            <form id="login-form">
                <h3>Вход</h3>
                <input type="text" name="username" class="form-control mb-1" placeholder="Логин">
                <input type="password" name="password" class="form-control mb-1" placeholder="Пароль">
                <button type="submit" class="btn btn-outline-primary btn-block">Войти</button>
            </form>
        </div>
    </div>
</section>

</body>
</html>

