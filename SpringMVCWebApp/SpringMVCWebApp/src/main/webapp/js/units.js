function GetConvertOptions(option) {
    var one = [{
        val: 1,
        text: "Fahrenheit"
    }, {
        val: 2,
        text: "Celsius"
    }, {
        val: 3,
        text: "Kelvin"
    }];
    var two = [{
        val: 1,
        text: "Dollar"
    }, {
        val: 2,
        text: "Euro"
    }];
    var three = [{
        val: 1,
        text: "Gallon"
    }, {
        val: 2,
        text: "Liter"
    }];
    var four = [{
        val: 1,
        text: "Pound"
    }, {
        val: 2,
        text: "Kilogram"
    }];
    var choice = [];
    if (option === "Temperature") {
        choice = one;
    } else if (option === "Currency") {
        choice = two;
    } else if (option === "Volume") {
        choice = three;
    } else {
        choice = four;
    } 
    return choice;
}

$("#conversionType").on("change", function () {
    var options = GetConvertOptions($(this).val());
    $("#convertFrom").empty();
    $("#convertTo").empty();
    $.each(options, function (index, item) {

        $("#convertFrom").append($("<option></option>").val(item.val).text(item.text));
        $("#convertTo").append($("<option></option>").val(item.val).text(item.text));
    });
});