var AddressBooks = {
    setup: function() {
        $('#buddyForm').submit(AddressBooks.addBuddy);
    }
    ,createAddressBook: function() {
        $.ajax({
            type: 'POST',
            url: "spa/createAddressBook",
            timeout: 5000,
            success: $('<div>Success</div>').appendTo($('#createAddressBook')),
            error: function (xhrObj, textStatus, exception) {
                alert('Error!');
            }
            // 'success' and 'error' functions will be passed 3 args
        }).then(function (data) {
            console.log(data);
        });
        return (false);
    },
    addBuddy:function(event) {
        event.preventDefault();
        let addressBookId = $('#addressBookId').val();
        let name = $('#name').val();
        let address = $('#address').val();
        let phoneNumber = $('#phoneNumber').val();
        let url = "spa/addressBook/addBuddy/" + addressBookId;
        $.ajax({
            type: 'POST',
            url: url,
            timeout: 5000,
            datatype: 'json',
            contentType: 'application/json',
            data: JSON.stringify({ "name": name, "address" : address,"phoneNumber" : phoneNumber}),
            success: alert(name),
            error: function(xhrObj, textStatus, exception) { alert('Error!'); }
            // 'success' and 'error' functions will be passed 3 args
        }).then(function(data) {
            console.log(data);
        });
        return (false);
    }, viewAddressBook: function() {
        $.ajax({type: 'GET',
            url: "spa/addressBook/1",
            timeout: 5000,
            success: AddressBooks.showAddressBook,
            error: function(xhrObj, textStatus, exception) { alert('Error!'); }
            // 'success' and 'error' functions will be passed 3 args
        }).then(function(data) {
            console.log(data);
        });
        console.log("haha");
        return (false);
    }
    ,showAddressBook: function(data, requestStatus, xhrObject) {
        $('#addressBookInfo').html($(''));
        var i;
        for (i=0; i < data["buddyInfos"].length; i++) {
            $('<ul>' + data["buddyInfos"][i].name +
                '</ul>').appendTo($('#addressBookInfo'))
        }
        return(false);  // prevent default link action
    }
};
$(AddressBooks.setup);