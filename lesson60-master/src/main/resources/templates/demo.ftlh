
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
<body id="no-scroll" class="no-scroll" onload="addPostElements()">
<div class="container">
    <div class="row justify-content-center">
        <div class="col col-lg-7 posts-container" id="posts">
            <div class="card my-3">
            </div>
        </div>
    </div>
</div>
<form id="form-comment" name="form-comment" enctype="multipart/form-data" >
    <input type="hidden" name="idUser" value="${user.id}"/>
    <input type="hidden" name="idPhoto" value="${post.id}">
    <input type="text" placeholder="COMMENT" size="20" name="comment">
    <button type="submit" class='change' id="save-comments">Add comment</button>
</form>
</body>
</html>

