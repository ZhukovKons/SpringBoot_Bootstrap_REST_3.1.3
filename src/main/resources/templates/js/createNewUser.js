function addNewUser(serchInputFildIdContainer){

    let newUserAdd = document.querySelector("#" + serchInputFildIdContainer).querySelectorAll('.form-control');

    let user = {
        "name": newUserAdd[0].value,
        "lastname": newUserAdd[1].value,
        "email": newUserAdd[3].value,
        "age": parseInt(newUserAdd[2].value),
        "password": newUserAdd[4].value,
        "roles": Array.from(newUserAdd[5]).filter(o => o.selected).map(el => el.value)
    };
    sendUser(user, 'POST');
}