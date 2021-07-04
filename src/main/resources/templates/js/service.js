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
            addUserTable();
            break;
        case 'nav-profile-tab':
            // alert('nav-profile-tab');
            break;
        case 'nav-profile-tab newUserOpenPage':
            insertSelectorRoles(document.querySelector('#newUserAddDivAllFilds'));
            break;
    }
}

async function insertSelectorRoles(objInput) {
    getPromiseAllRoles().then(result => addRolesSelector(result));

    function addRolesSelector(roles) {
        let optionalSelectRole = objInput.querySelector('#roles');
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

function openModal(id, type) {
    let modal = document.querySelector("#modalWindows");
    let form = modal.querySelector('#formSendDataModal');
    insertSelectorRoles(modal);

    getPromiseUser(id).then(user => {
        let button = modal.querySelector('#buttonEndOderDelete');
        modal.querySelector('.modal-title').innerHTML = type + ' user';
        button.innerHTML = type;
        form.setAttribute('onsubmit', 'user' + type + '(' + id + ')');
        for (let userKey in user) {
            let el = modal.querySelector('#' + userKey);

            if (el != null && !el.hasAttribute("viewFalse")) {
                el.setAttribute("value", user[userKey]);
            }
        }
        if (type == "Delete") {  //todo
            button.setAttribute("class", "btn btn-danger");
            modal.querySelector('#passFild').style.display = 'none';
        } else {
            button.setAttribute("class", "btn btn-primary");
            modal.querySelector('#passFild').style.display = 'inline';
        }
    });



}
function userDelete(id) {
    deleteUser(id);
}

function userEdit() {
    alert(111);
}