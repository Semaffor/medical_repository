$(document).ready(() => {
    const editPersonLink = "http://localhost:8080/user/personal/edit/"
        let number = 1;
    $('#example').DataTable({
        "processing": true,
        // "serverSide": true,
        "ajax": {
            "url": "http://localhost:8080/admin/management/all",
            'dataSrc': '',
        },

        // "columnDefs": [
        //     {"targets": -1, "render": () => {return '<span>' + number++ +'</span>'}},
        //     {"targets": 5, "render": (data) => {
        //         console.log(data)
        //         return '<a src="' + editPersonLink +'"><img class="w-24 me-3" src="/static/images/edit.png"></a>'}}
        // ],
        "columns": [
            {data: "id", "render": () => {return '<span>' + number++ +'</span>'}},
            {data: "username"},
            {data: "card", render: data => {return data.surname + " " + data.name + " " + data.thirdName;}},
            {data: "card", render: data => {return data.gender;}},
            {data: "roles"},
            {
                data: "blocked",
                "render": function (data, type, row) {
                    let newData = '<img class="w-24 me-3" src="/static/images/';
                    if (data === false) {
                        newData += 'active.png">';
                    } else {
                        newData += 'disabled.png">';
                    }
                    return newData;
                }
            },
            {
                data: "blocked",
                "render": function (data, type, row) {
                    let newData = '<a href="management/blocking/'+row.username + '"><img class="w-24 me-3" src="/static/images/';
                    if (data === true) {
                        newData += 'active.png"></a>';
                    } else {
                        newData += 'disabled.png"></a>';
                    }
                    return newData;
                }
            },
        ]
    });
});
