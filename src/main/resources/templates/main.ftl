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
        <div class="col-12 col-lg-10">
                <div class="row justify-content-between">
                    <select id="phrase-type" class="custom-select col-5 col-lg-4 responsive-text-3">
                        <option disabled>Choose type</option>
                        <option selected value="verb">verb -></option>
                        <option value="number">number -></option>
                        <option value="fraction_number">fraction -></option>
                        <option value="noun">noun -></option>
                        <option value="sentence">sentence -></option>
                    </select>
                    <select id="direction" class="custom-select col-5 col-lg-4 responsive-text-3">
                        <option disabled>Choose direction</option>
                        <option value="russian">russian -></option>
                        <option selected value="hebrew">hebrew -></option>
                        <option value="voice">voice -></option>
                    </select>
                    <button type="button" class="col-2 col-lg-1 btn btn-md btn-block btn-outline-secondary responsive-text-3" id="admin-button">
                        admin
                    </button>
                </div>
        </div>
    </div>


</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-0 col-lg-1"></div>
        <div id="question" class="col-12 col-lg-10 responsive-text-1"></div>
    </div>
    <br/>
    <div class="row">
        <div class="col-0 col-lg-1"></div>
        <button type="button" id="answer1-button" class="col-2 col-lg-1 btn btn-md btn-outline-primary responsive-text-3" >
            answer
        </button>
        <div id="answer1" class="col-10 col-lg-9 responsive-text-2"></div>
    </div>
    <div class="row">
        <div class="col-0 col-lg-1"></div>
        <button type="button" id="answer2-button" class="col-2 col-lg-1 btn btn-md btn-block btn-outline-primary responsive-text-3">
            answer 2
        </button>
        <div id="answer2" class="col-10 col-lg-9 responsive-text-2"></div>
    </div>
    <div class="row">
        <div class="col-0 col-lg-1"></div>
        <button type="button" id="words-button" class="col-2 col-lg-1 btn btn-md btn-block btn-outline-primary responsive-text-3">
            words
        </button>
        <div class="col-10 col-lg-9 invisible" id="words" >
            <#-- insert words here by jscript -->
        </div>
    </div>
    <br/>
    <div class="row">
        <div class="col-0 col-lg-1"></div>
        <button type="button" class="col-12 col-lg-10 btn btn-md btn-block btn-primary responsive-text-3" id="new-phrase-button">
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
