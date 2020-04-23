'use strict';

function createPostElement(post, user) {
    let posts = `
          <!-- image block start -->
          <div>
            <img class="d-block w-100" src="${post.photo}" alt="Post image">
          </div>
          <!-- image block end -->
          <div class="px-4 py-3">
            <!-- post reactions block start -->
            <div class="d-flex justify-content-around">
              <span class="h1 mx-2 muted" id="likeIcon.${post.id}" onclick="changeLikeStatus(this.id);">
                <i class="far fa-heart"></i>
              </span>
              <span class="h1 mx-2 muted" id="dbl-likeIcon.${post.id}" ondblclick="changeLikeStatus2(this.id);">
                  <i class="far fa-heart"></i>
              </span>
              <span class="h1 mx-2 muted">
                <i class="far fa-comment"></i>
              </span>
              <span class="mx-auto"></span>
              <span class="h1 mx-2 muted" id="bookmarkIcon.${post.id}" onclick="changeBookmarkStatus(this.id);">
                <i class="far fa-bookmark"></i>
              </span>
            </div>
            <!-- post reactions block end -->
            <hr>
            <!-- post section start -->
            <div>
                <p> ${post.description}</p>
            </div>
            <!-- post section end -->
            <hr>
            <!-- comments section start -->
            <button type="submit" class='change' id="block.${post.id}" onclick="splashScreenHidden(this.id);">Show comments</button>
            <div id="comment-block.${post.id}" class="closed">
            </div>
            <form id="form-comment.${post.id}" name="form-comment" enctype="multipart/form-data">
                        <input type="hidden" name="idUser" value="${user.id}">
                        <input type="hidden" name="idPhoto" value="${post.id}">
                        <input type="text" placeholder="${post.id}" size="20" name="comment"/>
                        <button type="submit" class='change' id="comment.${post.id}" onclick="addComment(this.id);">Add comment</button>
            </form>
            <!-- comments section end -->
          </div>`;
    let newPost = document.createElement(`div`);
    newPost.innerHTML += posts;
    newPost.classList.add("card", "my-3");
    newPost.id = post.id;
    return newPost;
}
function splashScreenHiddenTrue(){
    document.getElementById('page-splash').hidden = true;
    document.body.classList.remove('no-scroll');
}
function redirect() {
    window.location.href = "http://localhost:8080/page"
}
function logout() {
    localStorage.clear();
}
function splashScreenHiddenFalse(){
    document.getElementById('page-splash').hidden = false;
    document.body.classList.add('no-scroll');
    logout();
}
function splashScreenHidden(id){
    let comBlock =  document.getElementById("comment-" + id).children[0];
    if(comBlock.classList.contains("closed")){
        comBlock.classList.remove("closed");
        document.getElementById("comment-" + id).hidden = false;
    }else{
        comBlock.classList.add("closed");
        document.getElementById("comment-" + id).hidden = true;
    }

}

function changeBookmarkStatus(id){
    let mark = document.getElementById(id).children[0];
    if(mark.classList.contains("fas")){
        mark.classList.remove("text-muted","fas");
    }else{
        mark.classList.add("text-muted","fas");
    }
}
function changeLikeStatus(id){
    let heart = document.getElementById(id).children[0];
    if(heart.classList.contains("text-danger")){
        heart.classList.remove("text-danger","fas");
    }else{
        heart.classList.add("text-danger","fas");
    }
}
function changeLikeStatus2(id){
    let heart = document.getElementById(id).children[0];
        heart.classList.add("text-danger","fas");
        setTimeout( function(){
            heart.classList.remove("text-danger","fas");
            console.log('wait');
        }, 500 );
}
function addComment(idPost){
    const commentForm = document.getElementById("form-" + idPost);
    let data = new FormData(commentForm);
    console.log("COMMENT ELEMENT SAVED");
    console.log(data);
    fetch("http://localhost:8080/page/comment", {
        method: 'POST',
        body: data
    }).then(r => r.json()).then(data => {
        window.location.href = "http://localhost:8080/page"
    });
}
function createCommentElement(comment, user) {
    let content = '<a href="#" class="muted">' + user.email + '</a>' + '<p>'+ comment.commentText + '</p>';
    let element = document.createElement('div');
    element.innerHTML = content;
    element.className = "py-2 pl-3";
    return element;
}
async function getPosts() {
    let url = 'http://localhost:8080/getPosts';
    let response = await fetch(url);
    console.log(response);
    return await response.json();
}
async function getComments() {
    let url = 'http://localhost:8080/getComments';
    let response = await fetch(url);
    console.log(response);
    return await response.json();
}

async function getUser() {
    let url = 'http://localhost:8080/getUser';
    let response = await fetch(url);
    console.log(response);
    return await response.json();
}

async function addPostElements(){
    const userAsJSON = localStorage.getItem('user');
    const user12 = JSON.stringify(userAsJSON);
    console.log("Laala " + user12);
    if (user12.length === 4){
        console.log("null NULL user12");
        // splashScreenHiddenTrue();
    } else {
        splashScreenHiddenTrue();
        console.log("NOT null user12");
        let posts = await getPosts();
        let user = await getUser();
        let comments = await getComments();

        let user1 = {
            id: user.id,
            email: user.email
        };
        for (let i = 0; i < posts.length; i++){
            let post = {
                id: posts[i].id,
                photo: posts[i].photo,
                description: posts[i].description
            };
            document.getElementById("posts").append(createPostElement(post, user1));
        }
        for (let i = 0; i < comments.length; i++){
            let comment = {
                id: comments[i].id,
                idUser: comments[i].idUser,
                idPhoto: comments[i].idPhoto,
                commentText: comments[i].commentText
            };
            document.getElementById("comment-block." + comments[i].idPhoto).prepend(createCommentElement(comment, user1));
        }
    }

}

window.addEventListener('load', function () {
    const saveButton = document.getElementById("save-photo");
    saveButton.addEventListener("click",function () {
        const publicationForm = document.getElementById("form");
        let data = new FormData(publicationForm);
        console.log("PHOTO ELEMENT SAVED");
        // alert.apply("DDD");
        fetch("http://localhost:8080/page",{
            method: 'POST',
            body: data
        }).then(r => r.json()).then(data => {
            window.location.href = "http://localhost:8080/page"
        });
    });




    const BASE_URL = 'http://localhost:8080';

    const loginForm = document.getElementById('login-form');
    loginForm.addEventListener('submit', onLoginHandler);

    function onLoginHandler(e) {
        e.preventDefault();
        const form = e.target;
        const userFormData = new FormData(form);
        const user = Object.fromEntries(userFormData);
        saveUser(user);
        fetchAuthorised(BASE_URL + '/demo/getUser')
    }

    function fetchAuthorised(url, options) {
        const settings = options || {}
        return fetch(url, updateOptions(settings)).then(r => r.json()).then(data => {console.log(data);window.location.href= "http://localhost:8080/page"});
    }

    function updateOptions(options) {
        const update = { ...options };
        update.mode = 'cors';
        update.headers = { ... options.headers };
        update.headers['Content-Type'] = 'application/json';
        const user = restoreUser();
        if(user) {
            update.headers['Authorization'] = 'Basic ' + btoa(user.username + ':' + user.password);
        }
        return update;
    }

    function saveUser(user) {
        const userAsJSON = JSON.stringify(user)
        localStorage.setItem('user', userAsJSON);
    }

    function restoreUser() {
        const userAsJSON = localStorage.getItem('user');
        const user = JSON.parse(userAsJSON);
        return user;
    }
});