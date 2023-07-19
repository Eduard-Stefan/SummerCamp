$(document).ready(function() {
    var dataTable = $('#example').DataTable({
        "ajax": {
            "url": "http://localhost:8080/teams/all",
            "dataSrc": ""
        },
        "columns": [
            { "data": "totalScore" },
            { "data": "name" }
        ],
        "order": [[0, "desc"]]
    });
});
