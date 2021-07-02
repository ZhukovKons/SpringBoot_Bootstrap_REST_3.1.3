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
            getAllRoles(document.querySelector('#newUserAddDivAllFilds'));
            break;
    }
}

async function getAllRoles(objInput) {
    getPromiseAllRoles().then(result => addRolesSelector(result));

    function addRolesSelector(roles) {
        let optionalSelectRole = objInput.querySelector('#selectRoles');
        console.log("addRolesSelector()" + roles);
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