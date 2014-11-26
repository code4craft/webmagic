var system = require('system');
var url = system.args[1];

var page = require('webpage').create();
page.settings.loadImages = false;
page.settings.resourceTimeout = 5000;

page.open(url, function (status) {
    if (status != 'success') {
        console.log("HTTP request failed!");
    } else {
        console.log(page.content);
    }

    page.close();
    phantom.exit();
});