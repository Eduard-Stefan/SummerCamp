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
                    return '<button class="btn btn-primary editButton" data-id="' + row.id + '">Edit</button>';
                }
            },
            {
                "data": null,
                "render": function(data, type, row) {
                    return '<button class="btn btn-danger deleteButton" data-id="' + row.id + '">Delete</button>';
                }
            }
        ]
    });

    $.ajax({
        url: "http://localhost:8080/teams/all",
        method: "GET",
        success: function(teams) {
            var editTeamHomeDropdown = $('#editTeamHome');
            var editTeamAwayDropdown = $('#editTeamAway');
            var addTeamHomeDropdown = $('#addTeamHome');
            var addTeamAwayDropdown = $('#addTeamAway');

            editTeamHomeDropdown.empty();
            editTeamAwayDropdown.empty();
            addTeamHomeDropdown.empty();
            addTeamAwayDropdown.empty();

            editTeamHomeDropdown.append('<option value="">Select a team</option>');
            editTeamAwayDropdown.append('<option value="">Select a team</option>');
            addTeamHomeDropdown.append('<option value="">Select a team</option>');
            addTeamAwayDropdown.append('<option value="">Select a team</option>');

            teams.forEach(function(team) {
                editTeamHomeDropdown.append('<option value="' + team.id + '">' + team.name + '</option>');
                editTeamAwayDropdown.append('<option value="' + team.id + '">' + team.name + '</option>');
                addTeamHomeDropdown.append('<option value="' + team.id + '">' + team.name + '</option>');
                addTeamAwayDropdown.append('<option value="' + team.id + '">' + team.name + '</option>');
            });
        },
        error: function() {
            alert('Error fetching teams.');
        }
    });

    $('#example').on('click', '.editButton', function() {
        var gameId = $(this).data('id');
        var gameData = dataTable.row($(this).closest('tr')).data();
        $('#editGameId').val(gameData.id);
        $('#editGameType').val(gameData.gameType);
        $('#editLocation').val(gameData.location);
        $('#editDate').val(gameData.date);
        $('#editScoreHome').val(gameData.scoreHome);
        $('#editScoreAway').val(gameData.scoreAway);
        $('#editTeamHome').val(gameData.teamHome.id);
        $('#editTeamAway').val(gameData.teamAway.id);
        $('#editModal').modal('show');
    });

    $('#saveChangesBtn').on('click', function() {
        var editForm = $('#editForm');
        var gameId = $('#editGameId').val();

        if (!editForm[0].checkValidity()) {
            editForm[0].reportValidity();
            return;
        }

        var scoreHome = parseInt($('#editScoreHome').val(), 10);
        var scoreAway = parseInt($('#editScoreAway').val(), 10);

        if (scoreHome < 0) {
            $('#editScoreHome')[0].setCustomValidity('Score Home must be a positive number or zero.');
            $('#editScoreHome')[0].reportValidity();
            return;
        }

        if (scoreAway < 0) {
            $('#editScoreAway')[0].setCustomValidity('Score Away must be a positive number or zero.');
            $('#editScoreAway')[0].reportValidity();
            return;
        }

        var editTeamHomeValue = $('#editTeamHome').val();
        var editTeamAwayValue = $('#editTeamAway').val();

        if (editTeamHomeValue === editTeamAwayValue) {
            alert('Team Home and Team Away must be different.');
            return;
        }

        var editUrl = "http://localhost:8080/games/update/" + gameId;
        $.ajax({
            url: editUrl,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify({
                gameType: $('#editGameType').val(),
                location: $('#editLocation').val(),
                date: $('#editDate').val(),
                scoreHome: scoreHome,
                scoreAway: scoreAway,
                teamHome: {
                    id: editTeamHomeValue
                },
                teamAway: {
                    id: editTeamAwayValue
                }
            }),
            success: function() {
                $('#editModal').modal('hide');
                dataTable.ajax.reload();
            },
            error: function() {
                alert('Error updating the game.');
            }
        });
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

    function clearAddGameModal() {
        $('#addGameType').val('');
        $('#addLocation').val('');
        $('#addDate').val('');
        $('#addScoreHome').val('');
        $('#addScoreAway').val('');
        $('#addTeamHome').val('');
        $('#addTeamAway').val('');
        $('#addForm')[0].reset();
    }

    $('#addGameButton').on('click', function() {
        clearAddGameModal();
        $('#addModal').modal('show');
    });

    $('#saveGameBtn').on('click', function() {
        var addForm = $('#addForm');

        if (!addForm[0].checkValidity()) {
            addForm[0].reportValidity();
            return;
        }

        var scoreHome = parseInt($('#addScoreHome').val(), 10);
        var scoreAway = parseInt($('#addScoreAway').val(), 10);

        if (scoreHome < 0) {
            $('#addScoreHome')[0].setCustomValidity('Score Home must be a positive number or zero.');
            $('#addScoreHome')[0].reportValidity();
            return;
        }

        if (scoreAway < 0) {
            $('#addScoreAway')[0].setCustomValidity('Score Away must be a positive number or zero.');
            $('#addScoreAway')[0].reportValidity();
            return;
        }

        var addTeamHomeValue = $('#addTeamHome').val();
        var addTeamAwayValue = $('#addTeamAway').val();

        if (addTeamHomeValue === addTeamAwayValue) {
            alert('Team Home and Team Away must be different.');
            return;
        }

        $.ajax({
            url: "http://localhost:8080/games/new",
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                gameType: $('#addGameType').val(),
                location: $('#addLocation').val(),
                date: $('#addDate').val(),
                scoreHome: scoreHome,
                scoreAway: scoreAway,
                teamHome: {
                    id: addTeamHomeValue
                },
                teamAway: {
                    id: addTeamAwayValue
                }
            }),
            success: function() {
                $('#addModal').modal('hide');
                dataTable.ajax.reload();
            },
            error: function() {
                alert('Error adding the game.');
            }
        });
    });

    $('#homeButton').on('click', function() {
        window.location.href = "home";
    });

});
