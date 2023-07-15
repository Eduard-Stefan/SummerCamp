$(document).ready(function() {
    var dataTable = $('#example').DataTable({
        "ajax": {
            "url": "http://localhost:8080/teams/all",
            "dataSrc": ""
        },
        "columns": [
            { "data": "id" },
            { "data": "name" },
            { "data": "location" },
            { "data": "coach" },
            { "data": "totalScore" },
            { "data": "totalScoreHome" },
            { "data": "totalScoreAway" },
            {
                "data": null,
                "render": function(data, type, row) {
                    return '<button class="btn btn-danger deleteButton" data-id="' + row.id + '">Delete</button>';
                }
            }
        ]
    });

    $('#example').on('click', '.deleteButton', function() {
        var teamId = $(this).data('id');
        var deleteUrl = "http://localhost:8080/teams/delete/" + teamId;
        $.ajax({
            url: deleteUrl,
            type: 'DELETE',
            success: function() {
                dataTable.ajax.reload();
            },
            error: function() {
                alert('Error deleting the team.');
            }
        });
    });

    $('#addTeamButton').on('click', function() {
        window.location.href = "add-team";
    });

    $('#homeButton').on('click', function() {
        window.location.href = "home";
    });
});
