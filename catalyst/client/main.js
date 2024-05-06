
function dopost(url, data, callback) {

    const options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    };

    fetch(url, options)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.text();  // Make sure to call it as a function
        })
        .then(data => {
            console.log(data);
            callback(data)
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
}

function loginFetch(username, password) {

    console.log(username+" fi")
    setCookie("username", username, 10)
    setCookie("password", password, 10)

    let data = {
        apparaw: username,
        suparaw: password
    };

    let url = "https://wallstreetxchange-838172193.development.catalystserverless.com/server/server/login"

    dopost(url, data, disp)

}

function login() {
    let username = document.getElementById("username").value;
    let password = document.getElementById("password").value;

    loginFetch(username, password)
}

function disp(data) {
    let container = document.querySelector(".container");
    console.log(data)
    if (data == "true") {
        console.log("logedin")
        container.innerHTML = "Loged in";

    }
}

function isLoggedin() {
    console.log(document.cookie)
    let username = getCookie("username")
    let password = getCookie("password")

    if (username != null && password != null) {
        return [username, password]
    }

    return null


}

function setCookie(cname, cvalue, days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = cname + "=" + (cvalue || "") + expires + "; path=/";
}

function getCookie(cname) {

    if (document.cookie != null) {
        let cookies = document.cookie.split(";");
        cname = cname.trim();

        for (let i = 0; i < cookies.length; i++) {
            let c = cookies[i].trim().split("=");
            console.log(c)
            if (c[0] == cname) {
                return c[1];
            }
        }
    }
    return null;
}


function autoLogin() {
    let isSessionAvailable = false
    let userCred = isLoggedin()
    if (userCred != null) {
        let username = userCred[0]
        let password = userCred[1]
        // console.log(username)
        // console.log(password)
        loginFetch(username, password)
        isSessionAvailable = true
        return isSessionAvailable
    }
}

document.addEventListener("DOMContentLoaded", function () {

    if (autoLogin()) {

        console.log("auto login succesfull")
    } else {

    }
})
