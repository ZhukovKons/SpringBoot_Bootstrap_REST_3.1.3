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
            getAllRoles();
            break;
    }
}