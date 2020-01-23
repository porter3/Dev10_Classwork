$(document).ready(function () {

    loadDvdCollectionView();

    // create on a click
    createDvd();

});

function loadDvdCollectionView(){
    // hide stuff
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
                 var dvdRow = '<tr><td>' + dvd.title + '</td><td>'
                    + dvd.releaseYear + '</td><td>'
                    + dvd.director + '</td><td>'
                    + dvd.rating + '</td><td>'
                    + dvd.notes + '</td><td onclick="editDvd(' + dvd.dvdId + ')">'
                    + '<a>Edit</a> | <a>Delete</a></td></tr>';

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

    $('#cancelButton').click(function(){
        // clear error messages
        $('#errorDiv').empty();
        // hide createEditTemplate
        $('#createEditTemplate').hide();
        // load main view again
        loadDvdCollectionView();
    });
}


function displayErrorMessage(element, message){
    element
        .append('<li>')
        .attr({class: 'list-group-item list-group-item-danger'})
        .text(message);
}