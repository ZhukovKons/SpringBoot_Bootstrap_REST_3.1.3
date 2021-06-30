function addUserForTable(user) {
    let table = document.querySelector('#bodyTableAllUsers');
    let lineUserParam = document.createElement('tr');
    let strRole;
    user.roles.forEach(r => strRole += r);

    lineUserParam.innerHTML = '<td scope="row">' + user.id + '</td>\n' +
        '<td >' + user.name + '</td>\n' +
        '<td >' + user.lastname + '</td>\n' +
        '<td >' + user.age + '</td>\n' +
        '<td >' + user.email + '</td>\n' +
        '<td >' + strRole + '</td>\n' +
        '<td><button type="button" data-toggle="modal" class="btn btn-info">Edit</button></td>' +
        '<td><button type="button" data-toggle="modal" class="btn btn-danger">Delete</button></td>';

    table.append(lineUserParam);

    let modal = document.createElement('div');

    modal.innerHTML =
    '<div attr="id'+ "='edit_'" + user.id + "'" +
        '" insert="~{pages/editor :: modal_editor}" className="modal fade" id="exampleModal" tabIndex="-1" role="dialog" ' +
    'aria-labelledby="exampleModalLabel" aria-hidden="true">Modal editor</div>\n';

    document.querySelector("#tableAllUsers").append(modal);
}