$(document).ready(function () {
    $('h1, h2').addClass('text-center');

    $('.myBannerHeading').addClass('page-header').removeClass('myBannerHeading');
    $('#yellowHeading').text('Yellow Team');
    $('.orange').css('background-color', 'orange');
    $('.blue').css('background-color', 'blue');
    $('.red').css('background-color', 'red');
    $('.yellow').css('background-color', 'yellow');

    $('#yellowTeamList').append('<li>Joseph Banks</li><li>Simon Jones</li>');
    $('#oops').hide();
    $('#footerPlaceholder').remove();
    $('footer').append('<p>Jake Porter</p><p>jakeporter310@gmail.com</p>').css('font-family', 'courier').css('line-height', '24px');
});