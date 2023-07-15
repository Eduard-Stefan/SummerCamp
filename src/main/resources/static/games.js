$(document).ready(function() {
    var dataTable = $('#example').DataTable({
        "ajax": {
            "url": "http://localhost:8080/games/all",
            "dataSrc": ""
        },
        "columns": [
            { "data": "id" },
            { "data": "gameType" },
            { "data": "location" },
            { "data": "date" },
            { "data": "scoreHome" },
            { "data": "scoreAway" },
            { "data": "teamHome.name", "render": function(data, type, row) { return data ? data : ""; } },
            { "data": "teamAway.name", "render": function(data, type, row) { return data ? data : ""; } },
            {
                "data": null,
                "render": function(data, type, row) {
                    return '<button class="btn btn-danger deleteButton" data-id="' + row.id + '">Delete</button>';
                }
            }
        ]
    });

    $('#example').on('click', '.deleteButton', function() {
        var gameId = $(this).data('id');
        var deleteUrl = "http://localhost:8080/games/delete/" + gameId;
        $.ajax({
            url: deleteUrl,
            type: 'DELETE',
            success: function() {
                dataTable.ajax.reload();
            },
            error: function() {
                alert('Error deleting the game.');
            }
        });
    });

    $('#addGameButton').on('click', function() {
        window.location.href = "add-game";
    });

    $('#homeButton').on('click', function() {
        window.location.href = "home";
    });
});
