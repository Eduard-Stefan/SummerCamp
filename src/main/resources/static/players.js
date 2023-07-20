$(document).ready(function() {
    var dataTable = $('#example').DataTable({
        "ajax": {
            "url": "http://localhost:8080/players/all",
            "dataSrc": ""
        },
        "columns": [
            { "data": "name" },
            { "data": "age" },
            { "data": "nationality" },
            { "data": "team.name", "render": function(data, type, row) { return data ? data : ""; } },
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
            var editTeamDropdown = $('#editTeam');
            var addTeamDropdown = $('#addTeam');

            editTeamDropdown.empty();
            addTeamDropdown.empty();

            editTeamDropdown.append('<option value="">Select a team</option>');
            addTeamDropdown.append('<option value="">Select a team</option>');

            teams.forEach(function(team) {
                editTeamDropdown.append('<option value="' + team.id + '">' + team.name + '</option>');
                addTeamDropdown.append('<option value="' + team.id + '">' + team.name + '</option>');
            });
        },
        error: function() {
            alert('Error fetching teams.');
        }
    });

    $('#example').on('click', '.editButton', function() {
        var playerId = $(this).data('id');
        var playerData = dataTable.row($(this).closest('tr')).data();
        $('#editPlayerId').val(playerData.id);
        $('#editName').val(playerData.name);
        $('#editAge').val(playerData.age);
        $('#editNationality').val(playerData.nationality);
        $('#editTeam').val(playerData.team.id);
        $('#editModal').modal('show');
    });

    $('#saveChangesBtn').on('click', function() {
        var editForm = $('#editForm');
        var playerId = $('#editPlayerId').val();

        if (!editForm[0].checkValidity()) {
            editForm[0].reportValidity();
            return;
        }

        var age = parseInt($('#editAge').val(), 10);

        if (age < 1) {
            $('#editAge')[0].setCustomValidity('Age must be greater than or equal to 1.');
            $('#editAge')[0].reportValidity();
            return;
        }

        var editUrl = "http://localhost:8080/players/update/" + playerId;
        $.ajax({
            url: editUrl,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify({
                name: $('#editName').val(),
                age: age,
                nationality: $('#editNationality').val(),
                team: {
                    id: $('#editTeam').val()
                }
            }),
            success: function() {
                $('#editModal').modal('hide');
                dataTable.ajax.reload();
                var editToast = new bootstrap.Toast($('#editToast'));
                editToast.show();
            },
            error: function() {
                alert('Error updating the player.');
            }
        });
    });

    $('#example').on('click', '.deleteButton', function() {
        var playerId = $(this).data('id');
        $('#confirmDeleteButton').data('player-id', playerId);
        $('#confirmDeleteModal').modal('show');
    });

    $('#confirmDeleteButton').on('click', function() {
        var playerId = $(this).data('player-id');
        var deleteUrl = "http://localhost:8080/players/delete/" + playerId;
        $.ajax({
            url: deleteUrl,
            type: 'DELETE',
            success: function() {
                dataTable.ajax.reload();
                var deleteToast = new bootstrap.Toast($('#deleteToast'));
                deleteToast.show();
            },
            error: function() {
                alert('Error deleting the player.');
            }
        });

        $('#confirmDeleteModal').modal('hide');
    });

    function clearAddPlayerModal() {
            $('#addName').val('');
            $('#addAge').val('');
            $('#addNationality').val('');
            $('#addTeam').val('');
            $('#addForm')[0].reset();
    }

    $('#addPlayerButton').on('click', function() {
        clearAddPlayerModal();
        $('#addModal').modal('show');
    });

    $('#savePlayerBtn').on('click', function() {
        var addForm = $('#addForm');

        if (!addForm[0].checkValidity()) {
            addForm[0].reportValidity();
            return;
        }

        var age = parseInt($('#addAge').val(), 10);

        if (age < 1) {
            $('#addAge')[0].setCustomValidity('Age must be greater than or equal to 1.');
            $('#addAge')[0].reportValidity();
            return;
        }

        $.ajax({
            url: "http://localhost:8080/players/new",
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                name: $('#addName').val(),
                age: age,
                nationality: $('#addNationality').val(),
                team: {
                    id: $('#addTeam').val()
                }
            }),
            success: function() {
                $('#addModal').modal('hide');
                dataTable.ajax.reload();
                var addToast = new bootstrap.Toast($('#addToast'));
                addToast.show();
            },
            error: function() {
                alert('Error adding the player.');
            }
        });
    });
});
