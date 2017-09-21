
function passwordFieldKeyPress(event){
    if (event.keyCode == '13'){ //enter
        logIn();
    }
}
function passwordFieldChange(){
    $('#password-help').removeClass('text-danger');
    $('#password-help').addClass('text-muted');
    $('#password-input').removeClass('has-error');
    $('#show-password').removeClass('has-error');
}

function logIn(){
    $.post(
        '/ajax/password',
        { password : $("#password-input").val() },
        function (data) {
            if (data==1){
                location.href = 'admin/prog';
            } else {
                $('#password-help').removeClass('text-muted');
                $('#password-help').addClass('text-danger');
                $('#password-input').addClass('has-error');
                $('#show-password').addClass('has-error');
            }
        },
        "json"
    )
}

function guestLogIn(){
    location.href = 'password/guest';
}
function returnToMain(){
    location.href = '/';
}

function showPassword() {
    var $password = $("#password-input");
    if ($password.attr('type') == 'text'){
        $password.attr('type','password');
        $("#show-password").html("Show");
    } else if ($password.attr('type') == 'password'){
        $password.attr('type','text');
        $("#show-password").html("Hide");
    }
}

$(document).ready(function () {
    $('#password-input').on( "keypress", passwordFieldKeyPress );
    $('#password-input').change( passwordFieldChange );
    $('#log-in').on("click", logIn);
    $('#guest-log-in').on("click", guestLogIn);
    $('#return').on("click", returnToMain);
    $('#show-password').on("click", showPassword);
});