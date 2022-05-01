function hideLabel(label, speed) {
    $(String(label)).hide(String(speed));
}

function hideElementAfterTime(elem, time, velocity='slow') {
    setTimeout(() => {
        $(String(elem)).hide(String(velocity));
    }, time)
}

function showElement(name, message) {
    $(name).show('slow').html(message);
}

function textAlert(name, message, time) {
    showElement(name, message);
    hideElementAfterTime(name, time);
}

function getHtmlValByName(elemName) {
    return $('[name=' + elemName + ']').val();
}