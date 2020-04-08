var collectionEmptyCounter = 0;
var collectionAppendCounter = 0;

$(document).ready(function () {

    loadDvdCollectionView();

    // create on a click
    createDvd();
    // search by category
    searchByTitle();

    editViewCancelButton();
    displayViewBackButton();
});

function loadDvdCollectionView(){
    // hide errors
    $('#errorDiv').hide();

    // show this view
    $('#viewingTable').show();
    // empty table body of any preexisting data
    $('#dvdCollectionBody').empty();

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/dvds",
        success: function (data, status) {
            
            $.each(data, function (index, dvd) { 
                 // append dvd collection to table rows in dvdCollectionTable
                 var dvdRow = '<tr><td onclick="displayDvd(' + dvd.dvdId + ')">' + dvd.title + '</td><td>'
                    + dvd.releaseYear + '</td><td>'
                    + dvd.director + '</td><td>'
                    + dvd.rating + '</td><td>'
                    + dvd.notes + '</td><td>'
                    + '<a onclick="editDvd(' + dvd.dvdId + ')">Edit</a> | <a onclick="deleteDvd(' + dvd.dvdId + ')">Delete</a></td></tr>';

                 $('#dvdCollectionBody').append(dvdRow);
            });
        },
        error: function(){
            displayErrorMessage($('#errorDiv'), 'COLLECTION LOADING ERROR');
        }
    });
}

function createDvd(){

    $('#createDVD').click(function(){
        // hide everything else
        $('#viewingTable').hide();
        $('#errorDiv').hide();

        // show createEditTemplate container
        $('#createEditTemplate').css('display', '');

        // when save changes is clicked, POST the form
        $('#saveChangesButton').click(function(){
            $.ajax({
                type: "POST",
                url: "http://localhost:8080/dvd",
                // the data I'm sending to the API
                data: JSON.stringify({
                    title: $('#dvdTitle').val(),
                    releaseYear: $('#releaseYear').val(),
                    director: $('#director').val(),
                    rating: $('#ratingSelector').val(),
                    // may need to use a 'valHook' here if it returns weird values
                    notes: $('#notes').val()
                }),
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                datatype: 'json',
                success: function () {
                    $('#errorDiv').empty();
                    $('#dvdTitle').empty();
                    $('#releaseYear').empty();
                    $('#director').empty();
                    $('#ratingSelector').empty();
                    $('#notes').empty();
                    loadDvdCollectionView();
                },
                error: function(){
                    displayErrorMessage($('#errorDiv'), "CREATION EROR");
                }

            });
        });
    });
}

function editDvd(dvdId){

    console.log('DVD ID: ' + dvdId);
    // hide errors, viewing table
    $('#errorDiv, #viewingTable').hide();
[]  // show createEditTemplate
    $('#createEditTemplate').show();
    $('#saveChangesButton').click(function(){
        $.ajax({
            type: "PUT",
            url: "http://localhost:8080/dvd/" + dvdId,
            data: JSON.stringify({
                    dvdId: dvdId,
                    title: $('#dvdTitle').val(),
                    releaseYear: $('#releaseYear').val(),
                    director: $('#director').val(),
                    rating: $('#ratingSelector').val(),
                    // may need to use a 'valHook' here if it returns weird values
                    notes: $('#notes').val()
            }),
            headers:{
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            dataType: "json",
            success: function () {
                // clear error messages
                $('#errorDiv').empty();
                // hide createEditTemplate
                $('#createEditTemplate').hide();
                // load main view again
                loadDvdCollectionView();
            },
            error: function(){
                alert('EDITING ERROR');
                displayErrorMessage($('#errorDiv'), 'EDITING ERROR');
            }
        });
    });
}

function editViewCancelButton(){
    $('#cancelButton').click(function(){
        // clear error messages
        $('#errorDiv').empty();
        // hide createEditTemplate
        $('#createEditTemplate').hide();
        // load main view again
        loadDvdCollectionView();
    });
}

function deleteDvd(dvdId){
    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/dvd/" + dvdId,
        data: "data",
        dataType: "dataType",
        success: function () {
            loadDvdCollectionView();
        },
        error: function(){
            displayErrorMessage($('#errorDiv'), 'DELETION ERROR');
        }
    });
}

function displayDvd(dvdId){
    //hide viewingTable
    $('#viewingTable').hide();

    // empty preexisting data
    $('#releaseYearDisplay, #directorDisplay, #ratingDisplay, #notesDisplay, #dvdTitleHeader').empty();

    // show table
    $('#dvdDetailsDisplay, #dvdTitleHeader').show();

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/dvd/" + dvdId,
        success: function (data) {
            console.log('appending data');
            $('#dvdTitleHeader').append('<h3>' + data.title + '</h3>');
            $('#releaseYearDisplay').append('<h3>' + data.releaseYear + '</h3>');
            $('#directorDisplay').append('<h3>' + data.director + '</h3>');
            $('#ratingDisplay').append('<h3>' + data.rating + '</h3>');
            $('#notesDisplay').append('<h3>' + data.notes + '</h3>');
        },
        error: function(){
            displayErrorMessage($('#errorDiv'), 'DISPLAY DETAILS ERROR');
        }
    });
}

function displayViewBackButton(){
    $('#backButton').click(function(){
        // hide current display and header
        $('#dvdDetailsDisplay, #dvdTitleHeader').hide();
        // load main view again
        loadDvdCollectionView();
    });
}

function searchByTitle(){
    // hide errors
    $('#errorDiv').hide();

    // show this view
    $('#viewingTable').show();
    // empty table body of any preexisting data
    $('#dvdCollectionBody').empty();

    // when the search button is clicked:
    $('#search').click(function(){
        var searchTerm = $('#searchBox').val();
        
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/dvds",
            success: function (dvdCollection) {
                var valuesOfCategory = new Array();
                var filteredSearchValues = new Array();
                filteredSearchValues = $.grep(dvdCollection, function(dvd, index){
                    return (dvd.title.toLowerCase() === searchTerm.toLowerCase()); // DOES NOT WORK
                })
                // $.each(dvdCollection, function (index, dvd) { 
                //     console.log(category);
                //     // add all values of the specified category to an array
                //     switch(category){
                //         case "title":
                //             valuesOfCategory.push(dvd.title.toLowerCase());
                //             break;
                //         case "releaseYear":
                //             valuesOfCategory.push(dvd.releaseYear);
                //             break;
                //         case "director":
                //             valuesOfCategory.push(dvd.director.toLowerCase());
                //             break;
                //         case "rating":
                //             valuesOfCategory.push(dvd.rating.toLowerCase());
                //             break;
                //         default:
                //             break;
                //     }
                // });
                console.log('array: ' + Object.values(valuesOfCategory));
            }
        });
    });
}

function displayErrorMessage(element, message){
    $('#errorDiv').show();
    element
        .append('<p>')
        .attr({class: 'list-group-item list-group-item-danger'})
        .text(message);
}