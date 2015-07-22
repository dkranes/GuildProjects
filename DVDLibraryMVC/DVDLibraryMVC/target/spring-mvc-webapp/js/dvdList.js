/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    loadDVDLibrary();


    $('#add-button').on('click', function (e) {

        e.preventDefault();

        $.ajax({
            type: 'POST',
            url: 'dvd',
            cache: 'false',
            data: JSON.stringify({
                title: $('#add-title').val(),
                releaseDate: $('#add-release-date').val(),
                mpaaRating: $('#add-mpaa-rating').val(),
                directorName: $('#add-director-name').val(),
                studio: $('#add-studio').val(),
                userNote: $('#add-user-note').val() //$.makeArray($('#add-user-note').val())


            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            dataType: 'json'

        }).success(function (data, status) {

            $('#add-title').val('');
            $('#add-release-date').val('');
            $('#add-mpaa-rating').val('');
            $('#add-director-name').val('');
            $('#add-studio').val('');
            $('#add-user-note').val('');

            loadDVDLibrary();



        }).error(function (data, status) {
            var errors = data.responseJSON.fieldErrors;
            $('#validationErrors').empty();
            $.each(errors, function (index, validationError) {
                var errorDiv = $('#validationErrors');
                errorDiv.append(validationError.message).append($('<br>'));
            });

        });


    });



});

function dateFormatter(releaseDate) {

    var now = new Date(releaseDate)

    var day = ("0" + (now.getDate() + 1)).slice(-2);
    var month = ("0" + (now.getMonth() + 1)).slice(-2);

    return now.getFullYear() + "-" + (month) + "-" + (day);
}



function fillDvdTable(dvdList, status) {
    clearDvdTable();

    var cTable = $('#contentRows');

    $.each(dvdList, function (index, dvd)
    {
        var finalDate = dateFormatter(dvd.releaseDate);

        cTable.append($('<tr>')
                .append($('<td>').append($('<a>')
                        .attr({
                            'data-dvd-id': dvd.dvdId,
                            'data-toggle': 'modal',
                            'data-target': '#detailsModal'
                        })
                        .text(dvd.title)))
                .append($('<td>').text((finalDate)))
                //.append($('<td>').text((finaldate)))
                .append($('<td>').append($('<a>')
                        .attr({
                            'data-dvd-id': dvd.dvdId,
                            'data-toggle': 'modal',
                            'data-target': '#editModal'
                        })
                        .text('Edit')))
                .append($('<td>').append($('<a>')
                        .attr({
                            'data-dvd-id': dvd.dvdId,
                            'data-toggle': 'modal',
                            'data-target': '#editNoteModal'
                        })
                        .text('Edit User Note')))
                .append($('<td>').append($('<a>')
                        .attr({'onClick': 'deleteDvd(' + dvd.dvdId + ')'})
                        .text('Delete')))
                );
    });
}


function loadDVDLibrary() {

    $.ajax({
        url: 'dvds'
    }).success(function (data, status) {

        fillDvdTable(data, status);
    });
}



function clearDvdTable()
{
    $('#contentRows').empty();
}
;




$('#detailsModal').on('show.bs.modal', function (event) {

    var element = $(event.relatedTarget);

    var dvdId = element.data('dvd-id');

    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'dvd/' + dvdId
    }).success(function (dvd) {
        modal.find('#dvd-id').text(dvdId);
        modal.find('#dvd-title').text(dvd.title);
        modal.find('#dvd-release-date').text(dateFormatter(dvd.releaseDate));
        modal.find('#dvd-mpaa-rating').text(dvd.mpaaRating);
        modal.find('#dvd-director-name').text(dvd.directorName);
        modal.find('#dvd-studio').text(dvd.studio);
        modal.find('#dvd-user-note').text(dvd.userNote);
    });

});

$('#editModal').on('show.bs.modal', function (event) {


    var element = $(event.relatedTarget);

    var dvdId = element.data('dvd-id');

    var modal = $(this);



    $.ajax({
        type: 'GET',
        url: 'dvd/' + dvdId
    }).success(function (dvd) {
        modal.find('#dvd-id').text(dvdId);
        modal.find('#edit-dvd-id').val(dvdId);
        modal.find('#edit-title').val(dvd.title);


        modal.find('#edit-release-date').val(dateFormatter(dvd.releaseDate));
        modal.find('#edit-mpaa-rating').val(dvd.mpaaRating);
        modal.find('#edit-director-name').val(dvd.directorName);
        modal.find('#edit-studio').val(dvd.studio);
        modal.find('#edit-user-note').val(dvd.userNote);
    });

});


$('#edit-button').on('click', function (e) {
    e.preventDefault();


    $.ajax({
        type: 'PUT',
        url: 'dvd/' + $('#edit-dvd-id').val(),
        cache: 'true',
        data: JSON.stringify(
                {
                    dvdId: $('#edit-dvd-id').val(),
                    title: $('#edit-title').val(),
                    releaseDate: $('#edit-release-date').val(),
                    mpaaRating: $('#edit-mpaa-rating').val(),
                    directorName: $('#edit-director-name').val(),
                    studio: $('#edit-studio').val(),
                    userNote: $('#edit-user-note').val()
                }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: 'json'

    }).success(function () {
        loadDVDLibrary();

    });


});

$('#editNoteModal').on('show.bs.modal', function (event) {

    var element = $(event.relatedTarget);

    var dvdId = element.data('dvd-id');

    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'dvd/' + dvdId
    }).success(function (dvd) {
        modal.find('#dvd-id').text(dvdId);
        modal.find('#edit-note-dvd-id').val(dvdId);
        modal.find('#edit-title').text(dvd.title);
        // modal.find('#edit-user-note').val(dvd.userNote);
    });


});


$('#edit-note-button').on('click', function (e) {
    e.preventDefault();

    $.ajax({
        type: 'POST',
        url: 'dvd/' + $('#edit-note-dvd-id').val(),
        cache: 'true',
        data: $('#edit-userxx-note').val(),
        //$.find($('#edit-user-note').push($('#edit-user-note').val())
        //)),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: 'json'

    }).success(function (data, status) {

        //$('#edit-user-note').val('');    //.concat($('#add-user-note'));

        loadDVDLibrary();
    });
});


function deleteDvd(id)
{
    var answer = confirm("Do you really want to delete this dvd?");

    if (answer === true)
    {
        $.ajax({
            type: 'DELETE',
            url: 'dvd/' + id
        }).success(function () {
            loadDVDLibrary();
        });
    }
}


$('#search-button').click(function (event) {

    event.preventDefault();

    $.ajax({
        type: 'POST',
        url: 'search/dvds',
        data: JSON.stringify({
            title: $('#search-title').val(),
            releaseDate: $('#search-release-date').date(),
            mpaaRating: $('#search-mpaa-rating').val(),
            directorName: $('#search-director-name').val(),
            studio: $('#search-studio').val(),
            userNote: $('#search-user-note').val()
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: 'json'
    }).success(function (data, status)
    {
        $('#search-title').val('');
        $('#search-release-date').date('');
        $('#search-mpaa-rating').val('');
        $('#search-director-name').val('');
        $('#search-studio').val('');
        $('#search-user-note').val('');

        fillDvdTable(data, status);
    });

});

