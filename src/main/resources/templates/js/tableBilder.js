function addUserTable() {
    let tbody = document.createElement('tbody');
    tbody.id = "userContainer";

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

        //модальное окно

        let modal = document.createElement('div');
        modal.append(editModalTemp.content.cloneNode(true));
        document.querySelector("#tableAllUsers").before(modal);
        modal.querySelector('#editId').setAttribute('id', 'edit_' + user.id);
        {
            modal.querySelector('#edit_' + user.id).querySelectorAll(".form-control").forEach(fild => {
                let atrId = fild.getAttribute("id");
                if (atrId != "password") {
                    fild.setAttribute('value', user[atrId]);
                }
            });
        }

        //console.log("11111: " + inputFilds.length);
    }

    $('#bodyTableAllUsers').html(tbody);
}

function putUser(user){

}

function deleteUser(id){

}