// $(document).ready(() => {
//     $("#general_btn_add").click((e) => {
//         e.preventDefault();
//         hideLabel(".fail", 'slow');
//
//         const csrfToken = getCsrfTokenFromCookie();
//         const generalTestDto = getDataFromFormById('add_general_blood_form');
//         generalTestDto['username'] = $('#username').val();
//         const url = "/user/analyzes/add/generalBlood/" + generalTestDto.username;
//         generalFormAjax("POST", url, csrfToken, generalTestDto);
//     })
// })
let menuItems = $('#menu .menu__item');

$('#menu').click((e) => {
    for(let i = 0; i < menuItems.length; i++) {
        menuItems[i].classList.remove('active');
        if (menuItems[i] === e.target) {
            openTab(i);
        }
    }
    e.target.classList.add('active')
})

function openTab(i) {
    switch (i + 1) {
        case 1: {
            changeVisible('#general_blood', '#bio_blood')
            $('#leukocytes').html("11");
            break;
        }
        case 2: {
            changeVisible('#bio_blood', '#general_blood')
            $('#protein').html("22");
        }
    }
}

function changeVisible(newActive, oldActive) {
    $(String(oldActive))[0].style.display = 'none';
    $(String(newActive))[0].style.display = 'block';
}