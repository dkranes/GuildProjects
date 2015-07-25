/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//$( document.body ).on( 'click', '.dropdown-menu li', function( event ) {
// 
//   var $target = $( event.currentTarget );
// 
//   $target.closest( '.btn-group' )
//      .find( '[data-bind="label"]' ).text( $target.text() )
//         .end()
//      .children( '.dropdown-toggle' ).dropdown( 'toggle' );
// 
//   return false;
// 
//});
//
//$('#conversion-type').on('click', '.dropdown-menu li a', function () {
//    console.log("Selected Option:"+$(this).text());
//})

$(function () { // wait for page to load
    var conversionTypeDropdown = $("#conversionTypes"),
            conversionDropdown = $('<select></select>'); // create a conversion dropdown
           

    var conversionTypeDropdown2 = $("#conversionTypes2"),
            conversionDropdown2 = $('<select></select>'); // create a conversion dropdown
          

    // parse the nested dropdown
    conversionTypeDropdown.children().each(function () {
        var group = $(this),
                conversionName = group.attr('label'),
                option;

        // create an option for the conversion
        option = $('<option></option>').text(conversionName);

        // store the associated conversion options
        option.data('conversionTypes', group.children());

        // check if the conversion type should be selected
        if (group.find(':selected').length > 0) {
            option.prop('selected', true);
        }

        // add the conversion type to the dropdown
        conversionDropdown.append(option);
        //conversionDropdown2.append(option);
    });
    
    conversionTypeDropdown2.children().each(function () {
        var group = $(this),
                conversionName = group.attr('label'),
                option;

        // create an option for the conversion
        option = $('<option></option>').text(conversionName);

        // store the associated conversion options
        option.data('conversionTypes', group.children());

        // check if the conversion type should be selected
        if (group.find(':selected').length > 0) {
            option.prop('selected', true);
        }

        // add the conversion type to the dropdown
        conversionDropdown2.append(option);
    });

    // add the conversion dropdown to the page
    conversionTypeDropdown.before(conversionDropdown);
    //conversionTypeDropdown2.before(conversionDropdown2);

    // this function updates the conversion dropdown based on the selected conversion type
    function updateConversionTypes() {
        var type = conversionDropdown.find(':selected');
        conversionTypeDropdown.empty().append(type.data('conversionTypes'));
        var type2 = conversionDropdown2.find(':selected');
        conversionTypeDropdown2.empty().append(type2.data('conversionTypes'));
    }

    // call the function to set the initial conversion types
    updateConversionTypes();

    // and add the change handler
    conversionDropdown.on('change', updateConversionTypes);
    conversionDropdown2.on('change', updateConversionTypes);
});
