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
                <h2 class="col-5 col-lg-4 btn btn-md btn-outline-secondary responsive-text-3">
                    ${word_type}
                </h2>
                <h2 class="col-5 col-lg-4 btn btn-md btn-outline-secondary responsive-text-3">
                    ${word_id}
                </h2>
                <button type="button" onclick="window.location.href='/admin/prog'"
                        class="col-2 col-lg-1 btn btn-md btn-block btn-outline-secondary responsive-text-3">
                    admin
                </button>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid">
    <br/>
    <div class="row">
        <div class="col-0 col-lg-1"></div>
        <div class="col-12 col-lg-10">
            <table class="table table-hover table-striped">
                <thead>
                <tr class="row bg-info">
                    <td class="col-3"> info </td>
                    <td class="col-3"> russian </td>
                    <td class="col-3"> voice </td>
                    <td class="col-3 text-right"> hebrew </td>
                </tr>
                </thead>
                <tbody>
                    <#list wordInfoList as wordInfoItem>
                        <tr class="row">
                            <td class="col-3"> --- </td>
                            <td class="col-3"> <#if wordInfoItem.getRussian() ??>${wordInfoItem.getRussian()}</#if></td>
                            <td class="col-3"> <#if wordInfoItem.getTranscription() ??>${wordInfoItem.getTranscription()} </#if></td>
                            <td class="col-3 text-right"> <#if wordInfoItem.getHebrew() ??>${wordInfoItem.getHebrew()} </#if></td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="container-fluid">
    <br/>
    <div class="row">
        <div class="col-0 col-lg-1"></div>
        <button type="button" onclick="window.location.href='/'"
                class="col-12 col-lg-10 btn btn-md btn-block btn-info responsive-text-3">
            new phrase
        </button>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
</body>
</html>
