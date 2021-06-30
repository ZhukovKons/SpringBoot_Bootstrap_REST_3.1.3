let butAdmin = document.querySelector('#buttonAdmin');
let butUser = document.querySelector('#buttonUser');
let buttonTablAllUsers = document.querySelector('#nav-home-tab'); //#table
let buttonNewAllUsers = document.getElementById('nav-profile-tab'); //#new

document.onclick = event => {
    switch (event.target.id) {
        case 'buttonAdmin':
            // alert(this.id);
            break;
        case 'buttonUser':
            // alert('buttonUser');
            break;
        case 'nav-home-tab':
            clickAllUser();
            break;
        case 'nav-profile-tab':
            // alert('nav-profile-tab');
            break;
        case 'nav-profile-tab newUserOpenPage':
            getAllRoles();
            break;
    }
}

function clickAllUser(){
    var myHeaders = new Headers();
    myHeaders.append("Cookie", "JSESSIONID=921E9A2496A56129D3DE3B9B67335179");

    var requestOptions = {
        method: 'GET',
        headers: myHeaders,
        redirect: 'follow'
    };

    fetch("http://localhost:8080/all", requestOptions)
        .then(response => response.json())
        .then(result => Array.from(result).forEach(user => addUserForTable(user)))
        .catch(error => alert('Error: ' + error));
}