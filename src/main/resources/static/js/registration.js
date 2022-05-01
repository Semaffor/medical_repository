$(document).ready(function () {
    $('form').submit(function (event) {
        register(event);
    });

    function register(event) {
        event.preventDefault();
        $(".alert").html("").hide();

        // checkPasswords();
        let formData = getDataFromFormById('form');
        fetch('http://localhost:8080/auth/registration', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(formData),
        })
            .then(response => {
                if (response.status === 200) {
                    hideLabel('#captcha', "normal");
                    hideLabel('#reg_button', 'normal');
                    $('#successRegistrationLabel').show('slow');
                }
                return response.json();})
            .then(data => {console.log(data)
                Object.keys(data).forEach((key) => {
                    textAlert("#" + key + "_error_text", data[key], 2000);
                })
            });

        // if (!grecaptcha.getResponse()) {
        //     textAlert("#captchaError", "Please verify that you are not a robot.", 2000);
        //     return false;
        // }

    }

    function checkPasswords() {
        $('#pass_error_text').html(/*[[#{dto.user.password.mismatch}]]*/);

        if (getHtmlValByName("matchingPassword") !== getHtmlValByName("password")) {
            $('#pass_error_text').html("asdrewrwer")
            return false;
        }
        return true;
    }

    function checkCaptcha(name) {

    }

});

