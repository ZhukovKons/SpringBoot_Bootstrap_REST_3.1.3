let allRoles;

function getAllRoles() {
    let optionalSelectRole = document.querySelector('#selectRoles');
    let optionRoles = document.createElement('option');

    var myHeaders = new Headers();
    myHeaders.append("Cookie", "JSESSIONID=DFE762A422823953B4ED8ECA9B469328");

    var requestOptions = {
        method: 'GET',
        headers: myHeaders,
        redirect: 'follow'
    };

    fetch("http://localhost:8080/roles", requestOptions)
        .then(response => response.json())
        .then(result => {allRoles = result; addRolesSelector(allRoles)})
        .catch(error => console.log('error', error));

    function addRolesSelector(roles) {
        optionalSelectRole.size = roles.length;
        let str = "";
        for (let i = 0; i < roles.length; i++) {
            console.log(roles[i]);
            str += '<option value="' + roles[i] +
                '">' + roles[i] +
                '</option>\n';

        }
        optionRoles.innerHTML = str;
        optionalSelectRole.append(optionRoles);

    }
}

function addNewUser() {


    console.log(allRoles);

    // console.log(document.querySelectorAll());
}

function saffafsfs() {
    let newUserAdd = document.querySelectorAll('.form-control');

    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    let requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: JSON.stringify({
            "id": "",
            "name": newUserAdd[0],
            "lastname": newUserAdd[1],
            "email": newUserAdd[2],
            "age": newUserAdd[3],
            "password": newUserAdd[4],
            "roles": [
                "ADMIN",
                "USER"
            ]
        }),
        redirect: 'follow'
    };

    fetch("http://localhost:8080/new", requestOptions)
        .then(response => response.json())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}