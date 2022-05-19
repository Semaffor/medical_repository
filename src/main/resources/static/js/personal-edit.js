$(document).ready(() => {

    $("#userPhoto").click((e) => {
        $("#edit_photo")[0].disabled = false;
    })

    $("#edit_photo").click((e) => {
        let photo = document.getElementById('userPhoto').files[0];
        const username = $('#username').val();
        let formData = new FormData();
        formData.append("photo", photo);

        fetch(`/user/personal/edit/photo/${username}`, {
            method: "PATCH",
            headers: {
                // 'Content-Type': 'multipart/form-data',
                'X-XSRF-TOKEN': getCsrfTokenFromCookie()
            },
            body: formData
        }).then((response) => {
            if (response.status !== 200) {
                showAndHideAfterTime("#photo_fail", 2000)
            } else {
                showAndHideAfterTime("#photo_success", 2000)
                return response.text();
            }
        }).then(pathPhoto => {
            if (pathPhoto != null) {
                $("#avatar_id")[0].src = `/img/${pathPhoto}`;
            }
        });
    })

    $("#submit_edit").click((e) => {
        e.preventDefault();
        $(".error").hide('slow')

        if (!isDataValid()) {
            return false;
        }

        const csrfToken = document.cookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*\=\s*([^;]*).*$)|^.*$/, '$1');
        const cardDto = Object.fromEntries(new FormData(document.forms['userPersonalForm']).entries());
        const url = "/user/personal/edit/" + cardDto.username;
        generalFormAjax("POST", url, csrfToken, cardDto);
    })

    $("#submit_edit_role").click((e) => {
        e.preventDefault();

        let elem = $('#role')[0];

        const old = elem.options[0].value;
        const newRole = elem.options[elem.selectedIndex].value;
        const username = $('#username').val();

        console.log(old);
        console.log(newRole);
        if (old !== newRole) {
            fetch(serverURL + "user/personal/edit/" + username + "/role", {
                method: "PUT",
                headers: {'Content-Type': 'application/json', 'X-XSRF-TOKEN': getCsrfTokenFromCookie()},
                body: JSON.stringify(newRole),
            }).then(response => {
                console.log(response.status)
                if (response.status === 201) {
                    showAndHideAfterTime(".success", 2000);
                } else {
                    showAndHideAfterTime("#failRole", 2000);
                }
            });
        }
    })
})
function isDataValid() {
    let nameVal = $("#name").val();
    if (nameVal.length < 3 ||nameVal.length > 28) {
        showAndHideAfterTime(".name_error");
        return false;
    }
    let surnameVal = $("#surname").val();
    if (surnameVal.length < 3 ||surnameVal.length > 28) {
        showAndHideAfterTime(".surname_error");
        return false;
    }
    let thirdNameVal = $("#thirdName").val();
    if (thirdNameVal.length < 3 ||thirdNameVal.length > 28) {
        showAndHideAfterTime(".thirdName_error");
        return false;
    }
    let emailVal = $("#email").val();
    let emailPattern = /^(.+)@(.+)$/;
    if (!emailPattern.test(emailVal)) {
        console.log(emailPattern.test(emailVal));
        showAndHideAfterTime(".email_error");
        return false;
    }
    let mobileVal = $("#mobile").val();
    let mobilePattern = /^(\d{2})-(\d{7})/
    if (!mobilePattern.test(mobileVal)) {
        console.log(mobilePattern.test(mobileVal))
        showAndHideAfterTime(".mobile_error");
        return false;
    }
    return true;
}
