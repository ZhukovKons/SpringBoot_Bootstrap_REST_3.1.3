async function addUserTable() {
    let tbody = document.createElement('tbody');
    tbody.id = "userContainer";

    getPromiseAllUsers().then(list => Array.from(list).forEach(user => addUserForTable(user)));


    function addUserForTable(user) {
        let table = document.querySelector('#bodyTableAllUsers');
        let lineUserParam = document.createElement('tr');

        lineUserParam.innerHTML = '<td scope="row">' + user.id + '</td>\n' +
            '<td >' + user.name + '</td>\n' +
            '<td >' + user.lastname + '</td>\n' +
            '<td >' + user.age + '</td>\n' +
            '<td >' + user.email + '</td>\n' +
            '<td >' + user.roles.join(" ") + '</td>\n' +
            '<td><button type="button" data-toggle="modal" class="btn btn-info" data-target="#edit_' +
            user.id + '">Edit</button></td>' +
            '<td><button type="button" data-toggle="modal" class="btn btn-danger" data-target="#delete_' +
            user.id + '">Delete</button></td>';

        table.append(lineUserParam);
        setAtrrModalWindow("edit");
        setAtrrModalWindow("delete");

        //модальное окно
        function setAtrrModalWindow(type) {
            let modal = document.createElement('div');
            modal.append(editModalTemp.content.cloneNode(true));
            document.querySelector("#tableAllUsers").before(modal);
            let strTypeAndId;
            switch (type) {
                case 'edit':
                    strTypeAndId = 'edit_' + user.id;
                    break;
                case 'delete':
                    strTypeAndId = 'delete_' + user.id;
                    break;
            }
            modal.querySelector('#editId').setAttribute('id', strTypeAndId);

            {
                modal.querySelector('#' + strTypeAndId).querySelectorAll(".form-control").forEach(fild => {
                    let atrId = fild.getAttribute("id");
                    if (atrId != "password") {
                        fild.setAttribute('value', user[atrId]);
                    }
                    if (type == "delete") {
                        fild.setAttribute('readonly', "true");
                    }
                });
                let button = modal.querySelector('#' + strTypeAndId).querySelector("#buttonEndOderDelete");
                if (type == "delete") {
                    modal.querySelector('#' + strTypeAndId).querySelector(".modal-title").innerHTML = 'Delete user';
                    button.innerHTML = 'Delete';
                    button.setAttribute('class', "btn btn-danger");
                    modal.querySelector('#' + strTypeAndId).querySelector("#formSendDataModal").setAttribute('onsubmit', 'deleteUser(' + user.id + ')');
                    getAllRoles(modal)
                }else{
                    modal.querySelector('#' + strTypeAndId).querySelector("#formSendDataModal").setAttribute('onsubmit', 'putUser(' + user.id + ')');
                    getAllRoles(modal);
                }
            }
        }
    }

    $('#bodyTableAllUsers').html(tbody);

}

function putUser(user) {
    alert('putUser: ' + user);
}

function deleteUser(id) {
    alert('deleteUser: ' + id);
}