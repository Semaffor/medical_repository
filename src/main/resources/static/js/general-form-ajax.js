function generalFormAjax(method, url, csrfToken, data) {
    $.ajax({
        type: method,
        url: url,
        headers: {
            'X-XSRF-TOKEN': csrfToken,
        },
        contentType: "application/json",
        data: JSON.stringify(data),
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
}