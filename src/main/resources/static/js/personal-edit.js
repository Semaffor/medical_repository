$(document).ready(() => {
    $("#submit_edit").click((e) => {
        e.preventDefault();
        $(".error").hide('slow')

        const csrfToken = document.cookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*\=\s*([^;]*).*$)|^.*$/, '$1');
        const cardDto = Object.fromEntries(new FormData(document.forms['userPersonalForm']).entries());
        const url = "/user/personal/edit/" + cardDto.username;
        generalFormAjax("POST", url, csrfToken, cardDto);
    })
})