/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    loadAddresses();


    $('#add-button').on('click', function (e) {

        e.preventDefault();

        $.ajax({
            type: 'POST',
            url: 'address',
            data: JSON.stringify({
                firstName: $('#add-first-name').val(),
                lastName: $('#add-last-name').val(),
                street: $('#add-street').val(),
                city: $('#add-city').val(),
                state: $('#add-state').val(),
                zip: $('#add-zip').val()
            }),
            headers: {
                'Accept': 'application/json',
                'Content-type': 'application/json'
            },
            dataType: 'json'

        }).success(function (data, status) {
            //we're clearing the table by setting all the values to empty strings
            $('#add-first-name').val('');
            $('#add-last-name').val('');
            $('#add-street').val('');
            $('#add-city').val('');
            $('#add-state').val('');
            $('#add-zip').val('');

            loadAddresses();

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

function fillAddressTable(addressBook, status) {
    clearAddressTable();

    var cTable = $('#contentRows');

    $.each(addressBook, function (index, contact)
    {
        cTable.append($('<tr>')
                .append($('<td>').append($('<a>')
                        .attr({
                            'data-contact-id': contact.contactId,
                            'data-toggle': 'modal',
                            'data-target': '#detailsModal'
                        })
                        .text(contact.firstName + ' ' + contact.lastName)))
                .append($('<td>').text(contact.city))
                .append($('<td>').append(
                        $('<a>')
                        .attr({
                            'data-contact-id': contact.contactId,
                            'data-toggle': 'modal',
                            'data-target': '#editModal'
                        })
                        .text('Edit')
                        ))
                .append($('<td>')
                        .append($('<a>')
                                .attr({'onClick': 'deleteAddress(' + contact.contactId + ')'})
                                .text('Delete')))
                );
    });
}

function loadAddresses() {

    $.ajax({
        url: "addresses"
    }).success(function (data, status) {
        fillAddressTable(data, status);
    });

}

function clearAddressTable()
{
    $('#contentRows').empty();
}

$('#detailsModal').on('show.bs.modal', function (event) {

    var element = $(event.relatedTarget);

    var contactId = element.data('contact-id');

    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'address/' + contactId

    }).success(function (contact) {
        modal.find('#contact-id').text(contact.contactId);
        modal.find('#contact-firstName').text(contact.firstName);
        modal.find('#contact-lastName').text(contact.lastName);
        modal.find('#contact-street').text(contact.street);
        modal.find('#contact-city').text(contact.city);
        modal.find('#contact-state').text(contact.state);
        modal.find('#contact-zip').text(contact.zip);

    });


});

$('#editModal').on('show.bs.modal', function (event) {

    var element = $(event.relatedTarget);

    var contactId = element.data('contact-id');

    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'address/' + contactId
    }).success(function (contact) {
        modal.find('#contact-id').text(contact.contactId);
        modal.find('#edit-contact-id').val(contact.contactId);
        modal.find('#edit-first-name').val(contact.firstName);
        modal.find('#edit-last-name').val(contact.lastName);
        modal.find('#edit-street').val(contact.street);
        modal.find('#edit-city').val(contact.city);
        modal.find('#edit-state').val(contact.state);
        modal.find('#edit-zip').val(contact.zip);
    });

});

$('#edit-button').click(function (event) {

    event.preventDefault();

    $.ajax({
        type: 'PUT',
        url: 'address/' + $('#edit-contact-id').val(),
        data: JSON.stringify(
                {
                    contactId: $('#edit-contact-id').val(),
                    firstName: $('#edit-first-name').val(),
                    lastName: $('#edit-last-name').val(),
                    street: $('#edit-street').val(),
                    city: $('#edit-city').val(),
                    state: $('#edit-state').val(),
                    zip: $('#edit-zip').val()
                }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function () {
        loadAddresses();
    });

});

function deleteAddress(id) {
    var answer = confirm("Do you really want to delete this address?");

    if (answer === true) {
        $.ajax({
            type: 'delete',
            url: 'address/' + id
        }).success(function () {
            loadAddresses();
        });
    }
}

$('#search-button').click(function (event) {
    event.preventDefault();
    $.ajax({
        type: 'POST',
        url: 'search/addresses',
        data: JSON.stringify({
            firstName: $('#search-first-name').val(),
            lastName: $('#search-last-name').val(),
            street: $('#search-street').val(),
            city: $('#search-city').val(),
            state: $('#search-state').val(),
            zip: $('#search-zip').val(),
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status) {
        $('#search-first-name').val('');
        $('#search-last-name').val('');
        $('#search-street').val('');
        $('#search-city').val('');
        $('#search-state').val('');
        $('#search-zip').val('');

        fillAddressTable(data, status);

    });
});


