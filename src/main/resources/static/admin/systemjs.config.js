/**
 * WEB ANGULAR VERSION
 * (based on systemjs.config.js in angular.io)
 * System configuration for Angular samples
 * Adjust as necessary for your application needs.
 */

var angularVersion4;
angularVersion4 = '@4.4.4';

(function (global) {
    System.config({
        // DEMO ONLY! REAL CODE SHOULD NOT TRANSPILE IN THE BROWSER
        transpiler: 'ts',
        typescriptOptions: {
            // Copy of compiler options in standard tsconfig.json
            "target": "es5",
            "module": "commonjs",
            "moduleResolution": "node",
            "sourceMap": true,
            "emitDecoratorMetadata": true,
            "experimentalDecorators": true,
            "lib": ["es2015", "dom"],
            "noImplicitAny": true,
            "suppressImplicitAnyIndexErrors": true
        },
        meta: {
            'typescript': {
                "exports": "ts"
            }
        },
        paths: {
            // paths serve as alias
            'unpkg-npm:': 'https://unpkg.com/',
            'npm:': 'https://cdn.jsdelivr.net/npm/'
        },
        // map tells the System loader where to look for things
        map: {
            // our app is within the app folder
            'app': 'app',

            // angular bundles
            '@angular/animations': 'npm:@angular/animations'+ angularVersion4 + '/bundles/animations.umd.js',
            '@angular/animations/browser': 'npm:@angular/animations'+ angularVersion4 + '/bundles/animations-browser.umd.js',
            '@angular/core': 'npm:@angular/core'+ angularVersion4 + '/bundles/core.umd.js',
            '@angular/common': 'npm:@angular/common'+ angularVersion4 + '/bundles/common.umd.js',
            '@angular/compiler': 'npm:@angular/compiler'+ angularVersion4 + '/bundles/compiler.umd.js',
            '@angular/platform-browser': 'npm:@angular/platform-browser'+ angularVersion4 + '/bundles/platform-browser.umd.js',
            '@angular/platform-browser/animations': 'npm:@angular/platform-browser'+ angularVersion4 + '/bundles/platform-browser-animations.umd.js',
            '@angular/platform-browser-dynamic': 'npm:@angular/platform-browser-dynamic'+ angularVersion4 + '/bundles/platform-browser-dynamic.umd.js',
            '@angular/http': 'npm:@angular/http'+ angularVersion4 + '/bundles/http.umd.js',
            '@angular/common/http': 'npm:@angular/common'+angularVersion4 +'/bundles/common-http.umd.js',
            '@angular/router': 'npm:@angular/router'+ angularVersion4 + '/bundles/router.umd.js',
            '@angular/router/upgrade': 'npm:@angular/router'+ angularVersion4 + '/bundles/router-upgrade.umd.js',
            '@angular/forms': 'npm:@angular/forms'+ angularVersion4 + '/bundles/forms.umd.js',
            '@angular/upgrade': 'npm:@angular/upgrade'+ angularVersion4 + '/bundles/upgrade.umd.js',
            '@angular/upgrade/static': 'npm:@angular/upgrade'+ angularVersion4 + '/bundles/upgrade-static.umd.js',

            // other libraries
            'rxjs': 'npm:rxjs@5.4.3',
            'rxjs/operators': 'npm:rxjs@5.4.3/operators/index.js',
            'angular-in-memory-web-api': 'npm:angular-in-memory-web-api@0.4/bundles/in-memory-web-api.umd.js',
            'ts':                        'npm:plugin-typescript@5.2.7/lib/plugin.js',
            'typescript':                'npm:typescript@2.3.2/lib/typescript.js',
            'tslib':                     'npm:tslib/tslib.js'

        },
        // packages tells the System loader how to load when no filename and/or no extension
        packages: {
            app: {
                main: './main.ts',
                defaultExtension: 'ts',
                meta: {
                    './*.ts': {
                        loader: 'systemjs-angular-loader.js'
                    }
                }
            },
            rxjs: {
                defaultExtension: 'js'
            }
        }
    });

})(this);

/*
 Copyright 2016 Google Inc. All Rights Reserved.
 Use of this source code is governed by an MIT-style license that
 can be found in the LICENSE file at http://angular.io/license
 */
