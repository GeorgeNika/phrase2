<html>
<head>
    <!-- Set the base href -->
    <script>document.write('<base href="' + document.location + '" />');</script>
    <title>Router Sample</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">

    <!-- Polyfills -->
    <#--<script src="https://unpkg.com/core-js/client/shim.min.js"></script>-->
    <script src="https://cdn.jsdelivr.net/npm/core-js/client/shim.min.js"></script>


    <script src="https://unpkg.com/zone.js@0.7.4?main=browser"></script>
    <script src="https://unpkg.com/systemjs@0.19.39/dist/system.src.js"></script>

    <script src="systemjs.config.js"></script>
    <script>
        System.import('main.ts')
            .catch(function(err){ console.error(err); });
    </script>
</head>

<body>
<admin>loading...</admin>
</body>

</html>