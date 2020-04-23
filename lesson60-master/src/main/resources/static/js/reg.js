'use strict';
window.addEventListener('load', function () {

    const registrationForm = document.getElementById('registration-form');
    registrationForm.addEventListener('submit', onRegisterHandler);

    function onRegisterHandler(e) {
        e.preventDefault();
        const form = e.target;
        const data = new FormData(form);
        const userJSON = JSON.stringify(Object.fromEntries(data));
        createUser(data);
    }

    const baseUrl = 'http://localhost:8080';

    async function createUser(userJSON) {
        const settings = {
            method: 'POST',
            body: userJSON
        };

        const response = await fetch(baseUrl + '/registration', settings);
        const responseData = await response.json();

        console.log(responseData);

        window.location.href = baseUrl + "/login";
    }


    /*const user = fetch("http://localhost:8080/demo/getUser");
    console.log(user);

    const saveButton = document.getElementById("save-candidate");
    saveButton.addEventListener("click", function() {
        const candidateForm = document.getElementById("form");
        let data = new FormData(candidateForm);

        fetch("http://localhost:8080/candidate", {
            method: 'POST',
            body: data
        }).then(r => r.json()).then(data => {window.location.href = "http://localhost:8080/"});
    });*/

});
