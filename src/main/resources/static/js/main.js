
function getPhrase(){
    $.post(
        '/ajax/phrase',
        { phraseType : $('#phrase-type').val() },
        function (data) {
            setData(data);
        },
        "json"
    )
}

function setData(data) {

    var $question = $("#question");
    var $answer1 = $("#answer1");
    var $answer2 = $("#answer2");

    var question = '';
    var answer1 = '';
    var answer2 = '';

    var buttonCaption1 ='';
    var buttonCaption2 ='';
    var russianCaption = 'russian';
    var hebrewCaption = 'hebrew';
    var transcriptCaption = 'voice';

    if (data.phraseType=='transcription'){
        question = data.transcription;
        textLeft($question);
        answer1 = data.hebrew;
        textRight($answer1);
        buttonCaption1 = hebrewCaption;
        answer2 = data.russian;
        textLeft($answer2);
        buttonCaption2 = russianCaption;
    } else if (data.phraseType=='hebrew'){
        question = data.hebrew;
        textRight($question);
        answer1 = data.russian;
        textLeft($answer1);
        buttonCaption1 = russianCaption;
        answer2 = data.transcription;
        textLeft($answer2);
        buttonCaption2 = transcriptCaption;
    } else {
        question = data.russian;
        textLeft($question);
        answer1 = data.hebrew;
        textRight($answer1);
        buttonCaption1 = hebrewCaption;
        answer2 = data.transcription;
        textLeft($answer2);
        buttonCaption2 = transcriptCaption;
    }

    $question.html(question);
    $answer1.addClass('invisible');
    $answer1.html(answer1);
    $answer2.addClass('invisible');
    $answer2.html(answer2);
    $( "#answer1-button" ).html(buttonCaption1);
    $( "#answer2-button" ).html(buttonCaption2);

}

function textLeft($obj){
    $obj.removeClass("text-right");
    $obj.addClass("text-left");
}
function textRight($obj){
    $obj.removeClass("text-left");
    $obj.addClass("text-right");
}

function showHideAnswer($obj){
    if ($obj.hasClass('invisible')){
        $obj.removeClass('invisible');
    } else {
        $obj.addClass('invisible');
    }
}

function showHideAnswer1() {
    showHideAnswer($('#answer1'));
}
function showHideAnswer2() {
    showHideAnswer($('#answer2'));
}

function adminPage(){
    location.href = 'admin/prog';
}

$(document).ready(function () {

    $('#new-phrase-button').on('click', getPhrase);
    $('#answer1-button').on('click', showHideAnswer1);
    $('#answer2-button').on('click', showHideAnswer2);
    $('#admin-button').on('click', adminPage);
    getPhrase();
});
