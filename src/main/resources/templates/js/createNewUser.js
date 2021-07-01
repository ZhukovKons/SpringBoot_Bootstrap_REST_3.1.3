

function getAllRoles() {
    let optionalSelectRole = document.querySelector('#selectRoles');

    let myHeaders = new Headers();
    myHeaders.append("Cookie", "JSESSIONID=DFE762A422823953B4ED8ECA9B469328");

    let requestOptions = {
        method: 'GET',
        headers: myHeaders,
        redirect: 'follow'
    };

    fetch("http://localhost:8080/roles", requestOptions)
        .then(response => response.json())
        .then(result => {
            addRolesSelector(result)
        })
        .catch(error => console.log('error', error));

    function addRolesSelector(roles) {
        while (optionalSelectRole.firstChild) {
            optionalSelectRole.removeChild(optionalSelectRole.firstChild);
        }
        optionalSelectRole.size = roles.length;
        for (let i = 0; i < roles.length; i++) {
            let optionRoles = document.createElement('option');
            optionRoles.setAttribute('value', roles[i]);
            optionRoles.textContent = roles[i];
            optionalSelectRole.append(optionRoles);
        }
    }
}

function addNewUser() {
    let newUserAdd = document.querySelectorAll('.form-control');

    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var raw = JSON.stringify({
        "name": newUserAdd[0].value,
        "lastname": newUserAdd[1].value,
        "email": newUserAdd[3].value,
        "age": parseInt(newUserAdd[2].value),
        "password": newUserAdd[4].value,
        "roles": $('#selectRoles').val()
    });

    var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    fetch("http://localhost:8080/new", requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}