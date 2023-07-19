$(document).ready(function() {
    var dataTable = $('#example').DataTable({
        "ajax": {
            "url": "http://localhost:8080/teams/all",
            "dataSrc": ""
        },
        "columns": [
            { "data": "name" },
            { "data": "totalScore" }
        ],
        "order": [[1, "desc"]]
    });
});
