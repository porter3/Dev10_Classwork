$(document).ready(function () {

    // set money to 0.00
    $('#moneyDisplay').val(accounting.formatMoney(0, ["$"], [2]));
    
    // load items, display money
    loadItemsView();

    //add dollar
    $('#addDollar').click(function(){
        // get how much money there is currently
        currentMoney = accounting.unformat($('#moneyDisplay').val());
        // set the display to hold how much there is currently plus 1
        $('#moneyDisplay').val(accounting.formatMoney(currentMoney + 1, ["$"], [2]));
    });
    //add quarter
    $('#addQuarter').click(function(){
        currentMoney = accounting.unformat($('#moneyDisplay').val());
        $('#moneyDisplay').val(accounting.formatMoney(currentMoney + .25, ["$"], [2]));
    });
    //add dime
    $('#addDime').click(function(){
        currentMoney = accounting.unformat($('#moneyDisplay').val());
        $('#moneyDisplay').val(accounting.formatMoney(currentMoney + .1, ["$"], [2]));
    });
    //add nickel
    $('#addNickel').click(function(){
        currentMoney = accounting.unformat($('#moneyDisplay').val());
        $('#moneyDisplay').val(accounting.formatMoney(currentMoney + .05, ["$"], [2]));
    });

    // item selection display method
    selectItem();
    // when 'make purchase' is clicked
    $('#makePurchaseButton').click(function(){
        if ($('#itemNumberDisplay').val() == ''){
            $('#messageDisplay').val('Please make a selection');
            return;
        }
        purchaseItem();
    });

    // when 'Change Return' is clicked
    changeReturnOnClick();
});

function loadItemsView(){
    console.log('loading items');
    // clear errors
    

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
                costArray[i].append(accounting.formatMoney(items[i].price, ["$"], [2]));
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

// displays the change from purchasing an item
function displayTotalMoney(change){
    change = accounting.formatMoney(change, ["$"], [2]);

    // display the leftover change
    $('#moneyDisplay').val(change);
}

function displayChange(quarters, dimes, nickels, pennies){
    var changeDisplay = $('#changeDisplay');
    if (quarters !== 0){
        var quarterWord = 'quarter';
        if (quarters !== 1){
            quarterWord += 's';
        }
        changeDisplay.val(quarters + ' ' + quarterWord + ' ');
    }
    if (dimes !== 0){
        var dimeWord = 'dime';
        if (dimes !== 1){
            dimeWord += 's';
        }
        changeDisplay.val(changeDisplay.val() + dimes + ' ' + dimeWord + ' ');
    }
    if (nickels !== 0){
        var nickelWord = 'nickel';
        if (nickels !== 0){
            nickelWord += 's';
        }
        changeDisplay.val(changeDisplay.val() + nickels + ' ' + nickelWord + ' ');
    }
    if (pennies !== 0){
        var pennyWord = 'penny';
        if (pennies !== 1){
            pennyWord = 'pennies';
        }
        changeDisplay.val(changeDisplay.val() + pennies + ' ' + pennyWord);
    }
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

function clearAndLoadItems(){
    // clear previous data
    // no idea why jQuery wouldn't work. Plain old JS works fine here.
    var idNodeList = document.querySelectorAll('.id');
    var idArray = Array.from(idNodeList);
    for (i = 0; i < idArray.length; i++){
        idArray[i].innerText = '';
    }

    var numberNodeList = document.querySelectorAll('.number');
    var numberArray = Array.from(numberNodeList);
    for (i = 0; i < numberArray.length; i++){
        numberArray[i].innerText = '';
    }

    var nameNodeList = document.querySelectorAll('.name');
    var nameArray = Array.from(nameNodeList);
    for (i = 0; i < nameArray.length; i++){
        nameArray[i].innerText = '';
    }

    var costNodeList = document.querySelectorAll('.cost');
    var costArray = Array.from(costNodeList);
    for (i = 0; i < costArray.length; i++){
        costArray[i].innerText = '';
    }

    var quantityNodeList = document.querySelectorAll('.quantity');
    var quantityArray = Array.from(quantityNodeList);
    for (i = 0; i < quantityArray.length; i++){
        quantityArray[i].innerText = '';
    }

    loadItemsView();
}

function purchaseItem(){

    // get item number from itemNumberDisplay
    var itemNumber = $('#itemNumberDisplay').val();
    console.log('ITEM NUMBER SELECTED: ', itemNumber);

    // get item ID from item number
    var itemID = $('#box'+itemNumber).find('input').text();

    // get the money
    var money = accounting.unformat($('#moneyDisplay').val());
    console.log('MONEY VALUE: ', money);

    $.ajax({
        type: "POST",
        url: "http://tsg-vending.herokuapp.com/money/" + money + '/item/' + itemID,
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        dataType: "json",
        success: function (data) {
            // calculate change
            var change = (data.quarters*.25) + (data.dimes*.1) + (data.nickels*.05) + (data.pennies*.01);
            displayTotalMoney(change);
            displayChange(data.quarters, data.dimes, data.nickels, data.pennies);
            // display message
            $('#messageDisplay').val('Thank you!');
            // clear out previous view and load items
            clearAndLoadItems();
        },
        error: function(){
            alert('purchasing error, URL ACCESSED: ' + 'http://tsg-vending.herokuapp.com/money/' + money + '/item/' + itemID);
            return 0;
        }
    });
}

function changeReturnOnClick(){
    $('#changeReturnButton').click(function(){
        // get the current money in the machine
        var totalMoney = accounting.unformat($('#moneyDisplay').val());
        console.log('total money: ', totalMoney);

        // set MoneyDisplay to zero
        $('#moneyDisplay').val(accounting.formatMoney(0, ["$"], [2]));

        var remainder = totalMoney % .25;
        var quarters = (totalMoney - remainder) / .25;
        console.log('QuARTERS: ', quarters);

        var newRemainder = remainder % .1;
        var dimes =  (remainder - newRemainder) / .1;
        console.log('DIMES: ', dimes);
        remainder = newRemainder;

        newRemainder = remainder % .05;
        var nickels = (remainder - newRemainder) / .05;
        console.log('nickels: ', nickels);
        remainder = newRemainder;

        newRemainder = remainder % .01;
        var pennies = (remainder - newRemainder) / .01;
        console.log('pennies: ', pennies);

        displayChange(quarters, dimes, nickels, pennies);
    });
}