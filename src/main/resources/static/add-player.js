$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/teams/all",
        type: "GET",
        success: function(data) {
            populateTeamDropdown(data);
        },
        error: function() {
            alert('Error retrieving teams data.');
        }
    });

    function populateTeamDropdown(teams) {
        const teamDropdown = $('#team');

        teams.forEach(function(team) {
            teamDropdown.append($('<option>', {
                value: team.id,
                text: team.name
            }));
        });
    }

    function addPlayer() {
        const nameInput = document.getElementById("name");
        const ageInput = document.getElementById("age");
        const nationalityInput = document.getElementById("nationality");
        const teamInput = document.getElementById("team");
        const name = nameInput.value.trim();
        const age = parseInt(ageInput.value.trim());
        const nationality = nationalityInput.value.trim();
        const team = teamInput.value.trim();

        if (name === "" || isNaN(age) || age <= 0 || nationality === "" || team === "") {
            alert("Please enter valid data in all fields");
            return;
        }

        $.ajax({
            url: "http://localhost:8080/players/new",
            type: "POST",
            data: JSON.stringify({
                name: name,
                age: age,
                nationality: nationality,
                team: { id: team }
            }),
            contentType: "application/json; charset=UTF-8",
            success: function(response) {
                alert("Player successfully added!");
                resetForm();
            },
            error: function() {
                alert("Failed to add player");
            }
        });
    }

    function resetForm() {
        document.getElementById("name").value = "";
        document.getElementById("age").value = "";
        document.getElementById("nationality").value = "";
        document.getElementById("team").selectedIndex = 0;
    }

    function redirectToPlayers() {
        window.location.href = "players";
    }

    $('#addPlayer-button').on('click', function() {
        addPlayer();
    });

    $('#redirectToPlayers').on('click', function() {
        redirectToPlayers();
    });
});
