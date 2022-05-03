$(document).ready(function () {
    $('form').submit(function (event) {
        register(event);
    });

    function register(event) {
        event.preventDefault();
        $(".alert").html("").hide();

        if (checkPasswords()) {

            if (!grecaptcha.getResponse()) {
                textAlert("#captchaError", "Please verify that you are not a robot.", 2000);
                return false;
            }
            hideLabel('#captcha', "normal");
            hideLabel('#reg_button', 'normal');
            $('#sending').show('slow');
            let formData = getDataFromFormById('form');
            let csrfToken = getCsrfTokenFromCookie();
            fetch('/auth/registration', {
                method: 'POST',
                headers: {'Content-Type': 'application/json','X-XSRF-TOKEN': csrfToken},
                body: JSON.stringify(formData),
            })
                .then(response => {
                    if (response.status === 200) {
                        hideLabel('#captcha', "normal");
                        hideLabel('#reg_button', 'normal');
                        $('#sending').hide('slow');
                        $('#successRegistrationLabel').show('slow');
                    }
                    return response.json();
                })
                .then(data => {
                    console.log(data)
                    Object.keys(data).forEach((key) => {
                        textAlert("#" + key + "_error_text", data[key], 2000);
                    })
                });
        }
    }

    function checkPasswords() {
        if (getHtmlValByName("matchingPassword") !== getHtmlValByName("password")) {
            console.log("here");
            textAlert('#repeat_pass_error_text', "Passwords are not similar", 3000);
            return false;
        }
        return true;
    }

});

