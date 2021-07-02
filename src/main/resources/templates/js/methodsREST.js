function sendUser(raw, method) {
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var requestOptions = {
        method: method,
        headers: myHeaders,
        body: JSON.stringify(raw),
        redirect: 'follow'
    };

    fetch("http://localhost:8080/new", requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}

async function getPromiseAllUsers() {
    let myHeaders = new Headers();
    myHeaders.append("Cookie", "JSESSIONID=921E9A2496A56129D3DE3B9B67335179");

    let requestOptions = {
        method: 'GET',
        headers: myHeaders,
        redirect: 'follow'
    };
    return fetch("http://localhost:8080/all", requestOptions)
        .then(response => response.json())
        .catch(error => alert('Error: ' + error));

}

async function getPromiseAllRoles(){
    let myHeaders = new Headers();
    myHeaders.append("Cookie", "JSESSIONID=DFE762A422823953B4ED8ECA9B469328");

    let requestOptions = {
        method: 'GET',
        headers: myHeaders,
        redirect: 'follow'
    };

    return fetch("http://localhost:8080/roles", requestOptions)
        .then(response => response.json())
        .catch(error => console.log('error', error));
}