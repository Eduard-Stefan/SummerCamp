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
            {
                "data": null,
                "render": function(data, type, row) {
                    return '<button class="btn btn-danger deleteButton" data-id="' + row.id + '">Delete</button>';
                }
            }
        ]
    });

    // Handle delete button click event
    $('#example').on('click', '.deleteButton', function() {
        var teamId = $(this).data('id');
        var deleteUrl = "http://localhost:8080/teams/delete/" + teamId;

        // Perform the delete request
        $.ajax({
            url: deleteUrl,
            type: 'DELETE',
            success: function() {
                // Refresh the DataTable
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