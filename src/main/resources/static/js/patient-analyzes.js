$(document).ready(() => {
    let number = 1;
    $('#bio_blood_table').DataTable({
        "processing": true,
        // "serverSide": true,
        "ajax": {
            "url": "/user/analyzes/bioBlood",
            'dataSrc': '',
        },
        "columns": [
            {
                data: "id", "render": () => {
                    return '<span>' + number++ + '</span>'
                },
                width: "5%"
            },
            {data: "cholesterol", width: "15%"},
            {data: "glucose", width: "15%"},
            {data: "protein", width: "15%"},
            {data: "createdOn", width: "15%"},
            {
                data: "result",
                width: "35%",
                render: (data, type, row) => {
                    console.log(data + "\n" + type + "\n" + row);
                    if (data === null) {
                        console.log("23123")
                        return '<img class="w-24 me-3 img_link bio" ' +
                            'data-bs-toggle="modal" data-bs-target="#addRecommendation"' +
                            'src="/static/images/edit.png"  alt="setRecommendation"/>';
                    } else {
                        return data;
                    }
                }
            },

        ]
    });
});
