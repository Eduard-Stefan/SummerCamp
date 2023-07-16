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

    $('#example').on('click', '.editButton', function() {
        var teamId = $(this).data('id');
        var teamData = dataTable.row($(this).closest('tr')).data();
        $('#editTeamId').val(teamData.id);
        $('#editName').val(teamData.name);
        $('#editLocation').val(teamData.location);
        $('#editCoach').val(teamData.coach);
        $('#editModal').modal('show');
    });

    $('#saveChangesBtn').on('click', function() {
        var editForm = $('#editForm');
        var teamId = $('#editTeamId').val();

        if (!editForm[0].checkValidity()) {
            editForm[0].reportValidity();
            return;
        }

        var editUrl = "http://localhost:8080/teams/update/" + teamId;
        $.ajax({
            url: editUrl,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify({
                name: $('#editName').val(),
                location: $('#editLocation').val(),
                coach: $('#editCoach').val()
            }),
            success: function() {
                $('#editModal').modal('hide');
                dataTable.ajax.reload();
            },
            error: function() {
                alert('Error updating the team.');
            }
        });
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
