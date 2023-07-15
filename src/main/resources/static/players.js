$(document).ready(function() {
    var dataTable = $('#example').DataTable({
        "ajax": {
            "url": "http://localhost:8080/players/all",
            "dataSrc": ""
        },
        "columns": [
            { "data": "id" },
            { "data": "name" },
            { "data": "age" },
            { "data": "nationality" },
            { "data": "team.name", "render": function(data, type, row) { return data ? data : ""; } },
            {
                "data": null,
                "render": function(data, type, row) {
                    return '<button class="btn btn-danger deleteButton" data-id="' + row.id + '">Delete</button>';
                }
            }
        ]
    });

    $('#example').on('click', '.deleteButton', function() {
        var playerId = $(this).data('id');
        var deleteUrl = "http://localhost:8080/players/delete/" + playerId;
        $.ajax({
            url: deleteUrl,
            type: 'DELETE',
            success: function() {
                dataTable.ajax.reload();
            },
            error: function() {
                alert('Error deleting the player.');
            }
        });
    });

    $('#addPlayerButton').on('click', function() {
        window.location.href = "add-player";
    });

    $('#homeButton').on('click', function() {
        window.location.href = "home";
    });
});
