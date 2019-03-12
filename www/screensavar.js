
function setScreenSaver() {
   
    cordova.exec(setSuccess, setFailure, "Screensaver", "setScreenSaver");
}

function unSetScreenSaver() {
    
    cordova.exec(unSetSuccess, unSetFailure, "Screensaver", "unSetScreenSaver");
}
