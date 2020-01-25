$(document).ready(function () {

    var itemID;
    var totalMoney = 0;
    
    // load items, display money
    loadItemsView();
    displayTotalMoney(totalMoney);
    //add dollar
    $('#addDollar').click(function(){
        totalMoney += 1;
        displayTotalMoney(totalMoney);
    });
    //add quarter
    $('#addQuarter').click(function(){
        totalMoney += .25;
        displayTotalMoney(totalMoney);
    });
    //add dime
    $('#addDime').click(function(){
        totalMoney += .1;
        displayTotalMoney(totalMoney);
    });
    //add nickel
    $('#addNickel').click(function(){
        totalMoney += .05;
        displayTotalMoney(totalMoney);
    });

    selectItem();
    $('#makePurchaseButton').click(function(){
        purchaseItem();
    });
});

function loadItemsView(){
    console.log('loading items');
    // clear errors

    // clear previous data

    $.ajax({
        type: "GET",
        url: "http://tsg-vending.herokuapp.com/items",
        success: function (items) {
            console.log('ITEMS: ', items);
            // add hidden IDs to all boxes
            var idArray = $('.id');
            for (i = 0; i < idArray.length; i++){
                // get a JSON object from an array
                var item = items[i];
                console.log('ITEM: ', item);
                console.log('ID: ' + item.id);
                idArray[i].append(item.id);
            }

            // add indices to all boxes
            var numberArray = $('.number');
            for (i = 0; i < numberArray.length; i++){
                numberArray[i].append(i+1);
            }

            // add item name to all boxes
            var nameArray = $('.name');
            for (i = 0; i < nameArray.length; i++){
                nameArray[i].append(items[i].name);
            }

            // add item cost to all boxes
            var costArray = $('.cost');
            for (i = 0; i < costArray.length; i++){
                costArray[i].append(items[i].price);
            }

            // add item name to all boxes
            var quantityArray = $('.quantity');
            for (i = 0; i < quantityArray.length; i++){
                quantityArray[i].append(items[i].quantity);
            }

            
        },
        error: function(){
            alert('ITEM LOADING ERROR');
        }
    });
}

function displayTotalMoney(totalMoney){
    totalMoney = accounting.formatMoney(totalMoney, ["$"], [2]);

    // append totalMoney to moneyDisplay
    $('#moneyDisplay').val(totalMoney);
}

function selectItem(){

    // when a box is clicked on, execute this
    $('.box').click(function(){

        var boxID = $(this).attr('id');
        console.log(boxID);

        // clear messages
        $('#messageDisplay').val('');

        // get the item number of the box clicked on
        var itemNumber = $(this).find('.number').text();

        // add item number to item box
        $('#itemNumberDisplay').val(itemNumber);

        // get the item's ID and assign it for returning
        var itemID = $(this).find('input').text();
        console.log('ID object:', itemID);
        return itemID;
    });
}

function purchaseItem(){

    // get item number from itemNumberDisplay
    var itemNumber = $('#itemNumberDisplay').val();

    // get item ID from item number
    var itemID = $('#box'+itemNumber).find('input').text();

    // get the money
    var money = accounting.unformat($('#moneyDisplay').val());

    var change;

    $.ajax({
        type: "POST",
        url: "http://tsg-vending.herokuapp.com/money/" + money + '/item/' + itemID,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: "json",
        success: function (data) {
            alert('success');
            // calculate change
            change = (data.quarters*.25) + (data.dimes*.1) + (data.nickels*.05) + (data.pennies*.01);
            change = accounting.formatMoney(change, ["$"], [2]);
            displayTotalMoney(change);
        },
        error: function(){
            alert('purchasing error, URL ACCESSED: ' + 'http://tsg-vending.herokuapp.com/money/' + money + '/item/' + itemID);
            return 0;
        }
    });
    return change;
}