$(document).ready(() => {
    $('#example').DataTable({
        // "processing": true,
        // "serverSide": true,
        "ajax": {
            "url": "http://localhost:8080/admin/management/all",
            'dataSrc': '',
        },
        "columns": [
            {data: "username"},
            {data: "email"},
            {data: "roles"},
            {data: "blocked"},
        ]
    });
});
