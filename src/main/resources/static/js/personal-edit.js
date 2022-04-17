$(document).ready(() => {
    $("#submit_edit").click((e) => {
        e.preventDefault();
        const csrfToken = document.cookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*\=\s*([^;]*).*$)|^.*$/, '$1');
        const cardDto = Object.fromEntries(new FormData(document.forms['userPersonalForm']).entries());
        const url = "/user/personal/edit/" + cardDto.username;
        $(".error").hide('slow')

        $.ajax({
            type: "POST",
            url: url,
            headers: {
                'X-XSRF-TOKEN': csrfToken,
            },
            contentType: "application/json",
            data: JSON.stringify(cardDto),
            success: (response) => {
                $(".fail").hide('slow');
                $(".success").show('slow');
            },
            error: (errors) => {
                $(".success").hide('slow');
                $(".fail").show('slow');
                errors['responseJSON'].forEach(
                    error => {
                        const field = error['field'];
                        $("." + field + "_error").html(error['defaultMessage']).show('slow')
                    }
                )
            }
        })
    })
})