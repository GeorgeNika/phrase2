const $question = $("#question");
const $answer1 = $("#answer1");
const $answer2 = $("#answer2");
const $words = $("#words");
const $direction = $("#direction");

const russianCaption = 'russian';
const hebrewCaption = 'hebrew';
const voiceCaption = 'voice';

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
    if ($direction.val()=='voice'){
        setDataForVoiceType(data);
    }else if ($direction.val()=='hebrew'){
        setDataForHebrewType(data);
    }else {
        setDataForRussianType(data);
    }
    setInvisibleForAllButtons();
}

function setDataForVoiceType(data){
    $question.html(data.voice);
    $answer1.html(data.hebrew);
    $answer2.html(data.russian);

    textLeft($question);
    textRight($answer1);
    textLeft($answer2);

    $( "#answer1-button" ).html(hebrewCaption);
    $( "#answer2-button" ).html(russianCaption);

    setWords(data,"transcription");
}

function setDataForHebrewType(data){
    $question.html(data.hebrew);
    $answer1.html(data.russian);
    $answer2.html(data.voice);

    textRight($question);
    textLeft($answer1);
    textLeft($answer2);

    $( "#answer1-button" ).html(russianCaption);
    $( "#answer2-button" ).html(voiceCaption);

    setWords(data,"hebrew");
}

function setDataForRussianType(data){
    $question.html(data.russian);
    $answer1.html(data.hebrew);
    $answer2.html(data.voice);

    textLeft($question);
    textRight($answer1);
    textLeft($answer2);

    $( "#answer1-button" ).html(hebrewCaption);
    $( "#answer2-button" ).html(voiceCaption);

    setWords(data,"russian");
}

function setWords(data, language) {
    $words.html("");
    for (wordIndex in data.wordsIdentification) {
        var wordData = data.wordsIdentification[wordIndex]
        var $newWordButton = $("<a>", {href: "/word/"+wordData.type+"/"+wordData.wordId,
            "class": "col-2 btn btn-md btn-info responsive-text-3",
            "html": wordData.languageUnit[language]});
        $words.append($newWordButton);
    }
}

function setInvisibleForAllButtons() {
    $answer1.addClass('invisible');
    $answer2.addClass('invisible');
    $words.addClass('invisible');
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
function showHideWords() {
    showHideAnswer($('#words'));
}
function adminPage(){
    location.href = 'admin/prog';
}

$(document).ready(function () {

    $('#new-phrase-button').on('click', getPhrase);
    $('#answer1-button').on('click', showHideAnswer1);
    $('#answer2-button').on('click', showHideAnswer2);
    $('#words-button').on('click', showHideWords);
    $('#admin-button').on('click', adminPage);
    getPhrase();
});
