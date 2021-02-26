var AddressBooks = {
    setup: function () {
        $('#buddyForm').submit(AddressBooks.addBuddy);
    }
    , createAddressBook: function () {
        $.ajax({
            type: 'POST',
            url: "spa/createAddressBook",
            timeout: 5000,
            error: function (xhrObj, textStatus, exception) {
                alert('Error!');
            }
            // 'success' and 'error' functions will be passed 3 args
        }).then(function (data) {
            $(`<div>Successfully create Address Book ID ${data.id}</div>`).appendTo($('#created'))
        });
        return (false);
    },
    addBuddy: function (event) {
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
            data: JSON.stringify({"name": name, "address": address, "phoneNumber": phoneNumber}),
            success: alert(name + ' is added to address book ID ' + addressBookId),
            error: function (xhrObj, textStatus, exception) {
                alert('Error!');
            }
            // 'success' and 'error' functions will be passed 3 args
        }).then(function (data) {
            console.log(data);
        });
        return (false);
    }, viewAddressBook: function () {
        let addressBookId = $('#viewAddressBookId').val();
        $.ajax({
            type: 'GET',
            url: "spa/addressBook/" + addressBookId,
            timeout: 5000,
            success: AddressBooks.showAddressBook,
            error: function (xhrObj, textStatus, exception) {
                alert('Error!');
            }
            // 'success' and 'error' functions will be passed 3 args
        }).then(function (data) {
            console.log(data);
        });
        return (false);
    }
    , showAddressBook: function (data, requestStatus, xhrObject) {
        $('#addressBookInfo').html($(''));
        var i;
        for (i = 0; i < data["buddyInfos"].length; i++) {
            $(`<ul>Buddy ID: ${data["buddyInfos"][i].id}    
                    Name: ${data["buddyInfos"][i].name}    
                    Address: ${data["buddyInfos"][i].address}    
                    Phone Number: ${data["buddyInfos"][i].phoneNumber}</ul>`)
                .appendTo($('#addressBookInfo'))
        }
        return (false);  // prevent default link action
    }, deleteBuddy: function () {
        let addressBookId = $('#deleteBuddyAddressBookId').val();
        let buddyId = $('#deleteBuddyId').val();
        $.ajax({
            type: 'DELETE',
            url: `/addressBook/${addressBookId}/remove/${buddyId}`,
            timeout: 5000,
            error: function (xhrObj, textStatus, exception) {
                alert('Error!');
            }
            // 'success' and 'error' functions will be passed 3 args
        }).then(function (data) {
            alert('Buddy deleted.');
        });
        return (false);
    }
};
$(AddressBooks.setup);