<!DOCTYPE html>
<html>
<head>
    <title>Phrase 2017</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/common.css">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
</head>
<body>
<div class="container-fluid">
    <br/>
    <div class="row">
        <div class="col-0 col-lg-1"></div>
        <div class="col-5 col-lg-5"></div>
        <select id="phrase-type" class="custom-select col-5 col-lg-4 responsive-text-3">
            <option disabled>Выберите направление</option>
            <option selected value="russian">русский -></option>
            <option value="hebrew">иврит -></option>
            <option value="transcription">транскрипция -></option>
        </select>
        <button type="button" class="col-2 col-lg-1 btn btn-md btn-block btn-outline-secondary responsive-text-3" id="admin-button">
            admin
        </button>
    </div>


</div>

<div class="container-fluid">
    <br/>
    <div class="row">
        <div class="col-0 col-lg-1"></div>
        <h1 id="question" class="col-12 col-lg-10 responsive-text-1"></h1>
    </div>
    <br/>
    <div class="row">
        <div class="col-0 col-lg-1"></div>
        <button type="button" class="col-2 col-lg-1 btn btn-md btn-info responsive-text-3" id="answer1-button">
            answer
        </button>
        <div id="answer1" class="col-10 col-lg-9 responsive-text-2"></div>
    </div>
    <br/>
    <div class="row">
        <div class="col-0 col-lg-1"></div>
        <button type="button" class="col-2 col-lg-1 btn btn-md btn-block btn-info responsive-text-3" id="answer2-button">
            answer 2
        </button>
        <h2 id="answer2" class="col-10 col-lg-9 responsive-text-2"></h2>
    </div>
    <br/>
    <div class="row">
        <div class="col-0 col-lg-1"></div>
        <button type="button" class="col-12 col-lg-10 btn btn-md btn-block btn-info responsive-text-3" id="new-phrase-button">
            new phrase
        </button>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
<script src="/js/main.js"></script>
</body>
</html>
