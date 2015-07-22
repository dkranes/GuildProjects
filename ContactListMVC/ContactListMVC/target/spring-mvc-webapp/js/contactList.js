/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    loadContacts();

    //line directly below is where we hook into the click event
    $('#add-button').on('click', function (e) {

        e.preventDefault();

        $.ajax({
            type: 'POST',
            url: 'contact',
            data: JSON.stringify({
                firstName: $('#add-first-name').val(),
                lastName: $('#add-last-name').val(),
                company: $('#add-company').val(),
                phone: $('#add-phone').val(),
                email: $('#add-email').val()
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
            $('#add-company').val('');
            $('#add-phone').val('');
            $('#add-email').val('');
            
            $('#validationErrors').empty();
            
            loadContacts();
            

        }).error(function(data,status){
            var errors = data.responseJSON.fieldErrors;
            $('#validationErrors').empty();
            $.each(errors,function(index,validationError){
                var errorDiv = $('#validationErrors');
                errorDiv.append(validationError.message).append($('<br>'));
            });
            
            
        });

    });


});

function fillContactTable(contactList, status) {
    clearContactTable();

    var cTable = $('#contentRows');

    $.each(contactList, function (index, contact)
    {
        cTable.append($('<tr>')
                .append($('<td>').append($('<a>')
                        .attr({
                            'data-contact-id': contact.contactId,
                            'data-toggle': 'modal',
                            'data-target': '#detailsModal'
                        })
                        .text(contact.firstName + ' ' + contact.lastName)))
                .append($('<td>').text(contact.company))
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
                                .attr({'onClick': 'deleteContact(' + contact.contactId + ')'})
                                .text('Delete')))
                );
    });
}

function loadContacts() {

    $.ajax({
        url: "contacts"
    }).success(function (data, status) {
        fillContactTable(data, status);
    });

}

function clearContactTable()
{
    $('#contentRows').empty();
}

$('#detailsModal').on('show.bs.modal', function (event) {

    var element = $(event.relatedTarget);

    var contactId = element.data('contact-id');  

    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'contact/' + contactId

    }).success(function (contact) {
        modal.find('#contact-id').text(contact.contactId);
        modal.find('#contact-firstName').text(contact.firstName);
        modal.find('#contact-lastName').text(contact.lastName);
        modal.find('#contact-company').text(contact.company);
        modal.find('#contact-phone').text(contact.phone);
        modal.find('#contact-email').text(contact.email);

    });


});

$('#editModal').on('show.bs.modal', function (event) {

    var element = $(event.relatedTarget);

    var contactId = element.data('contact-id');

    var modal = $(this);

    $.ajax({
        type: 'GET',
        url: 'contact/' + contactId
    }).success(function (contact) {
        modal.find('#contact-id').text(contact.contactId);
        modal.find('#edit-contact-id').val(contact.contactId);
        modal.find('#edit-first-name').val(contact.firstName);
        modal.find('#edit-last-name').val(contact.lastName);
        modal.find('#edit-company').val(contact.company);
        modal.find('#edit-phone').val(contact.phone);
        modal.find('#edit-email').val(contact.email);
    });

});

$('#edit-button').click(function (event) {

    event.preventDefault();

    $.ajax({
        type: 'PUT',
        url: 'contact/' + $('#edit-contact-id').val(),
        data: JSON.stringify(
                {
                    contactId: $('#edit-contact-id').val(),
                    firstName: $('#edit-first-name').val(),
                    lastName: $('#edit-last-name').val(),
                    company: $('#edit-company').val(),
                    phone: $('#edit-phone').val(),
                    email: $('#edit-email').val(),
                }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function () {
        loadContacts();
    });

});

function deleteContact(id) {
    var answer = confirm("Do you really want to delete this contact?");

    if (answer === true) {
        $.ajax({
            type: 'delete',
            url: 'contact/' + id
        }).success(function () {
            loadContacts();
        });
    }
}

$('#search-button').click(function (event) {
    event.preventDefault();
    $.ajax({
        type: 'POST',
        url: 'search/contacts',
        data: JSON.stringify({
            firstName: $('#search-first-name').val(),
            lastName: $('#search-last-name').val(),
            company: $('#search-company').val(),
            phone: $('#search-phone').val(),
            email: $('#search-email').val()
        }),
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'dataType': 'json'
    }).success(function (data, status) {
        $('#search-first-name').val('');
        $('#search-last-name').val('');
        $('#search-company').val('');
        $('#search-phone').val('');
        $('#search-email').val('');

        fillContactTable(data, status);

    });
});

//TEST DATA
//
//var testContactData = [
//    {
//        contactId: 1,
//        firstName: "Susan",
//        lastName: "Williams",
//        company: "IBM",
//        email: "swilliams@ibm.com",
//        phone: "555-1212"
//    },
//    {
//        contactId: 2,
//        firstName: "George",
//        lastName: "Smith",
//        company: "EMC",
//        email: "smith@emc.com",
//        phone: "555-1234"
//    },
//    {
//        contactId: 3,
//        firstName: "Chuck",
//        lastName: "Knobloch",
//        company: "3M",
//        email: "chuck@3m.com",
//        phone: "555-5656"
//    }
//]
//
//var dummyDetailsContact =
//        {
//            contactId: 42,
//            firstName: "Kent",
//            lastName: "Hrbek",
//            company: "3M",
//            email: "kent@3m.com",
//            phone: "444-6789"
//        };
//
//var dummyEditContact =
//        {
//            contactId: 52,
//            firstName: "Kirby",
//            lastName: "Puckett",
//            company: "Sun",
//            email: "kirby@sun.com",
//            phone: "123-5599"
//        };