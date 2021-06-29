let butAdmin = document.querySelector('#buttonAdmin');
let butUser = document.querySelector('#buttonUser');
let buttonTablAllUsers = document.querySelector('#table');
let buttonNewAllUsers = document.querySelector('#new');

document.querySelector('#butPanel').onclick = event => {
    switch (event.target.id) {
        case 'buttonAdmin':
            select(butAdmin);
            break;
        case 'buttonUser':
            select(butUser);
            break;
        case 'buttonTablAllUsers':
            select(butUser);
            break;
    }
}


function select(button){
    let classActive = 'btn btn-primary text-left';
    let classDisable = 'btn btn-light text-left text-primary';
    $(document).ready(function(){
        $('div').click(function () {
            $(this).toggleClass('click');
        });
    });

    //button.class = 'btn btn-primary text-left';
}