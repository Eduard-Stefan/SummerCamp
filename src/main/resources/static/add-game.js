$(document).ready(function() {
    // Fetch teams data from the server
    $.ajax({
        url: "http://localhost:8080/teams/all",
        type: "GET",
        success: function(data) {
            populateTeamDropdowns(data);
        },
        error: function() {
            alert('Error retrieving teams data.');
        }
    });

    function populateTeamDropdowns(teams) {
        const teamHomeDropdown = $('#teamHome');
        const teamAwayDropdown = $('#teamAway');

        teams.forEach(function(team) {
            teamHomeDropdown.append($('<option>', {
                value: team.id,
                text: team.name
            }));

            teamAwayDropdown.append($('<option>', {
                value: team.id,
                text: team.name
            }));
        });
    }

    function addGame() {
        const typeInput = document.getElementById("type");
        const locationInput = document.getElementById("location");
        const dateInput = document.getElementById("date");
        const scoreHomeInput = document.getElementById("scoreHome");
        const scoreAwayInput = document.getElementById("scoreAway");
        const teamHomeInput = document.getElementById("teamHome");
        const teamAwayInput = document.getElementById("teamAway");

        const type = typeInput.value.trim();
        const location = locationInput.value.trim();
        const date = dateInput.value;
        const scoreHome = scoreHomeInput.value.trim();
        const scoreAway = scoreAwayInput.value.trim();
        const teamHome = teamHomeInput.value.trim();
        const teamAway = teamAwayInput.value.trim();

        if (
            type === "" ||
            location === "" ||
            date === "" ||
            scoreHome === "" ||
            scoreAway === "" ||
            teamHome === "" ||
            teamAway === ""
        ) {
            alert("Please fill in all fields");
            return;
        }

        if (teamHome === teamAway) {
            alert("Please select different teams for home and away");
            return;
        }

        const gameData = {
            gameType: type,
            location: location,
            date: date,
            scoreHome: scoreHome,
            scoreAway: scoreAway,
            teamHome: { id: teamHome },
            teamAway: { id: teamAway }
        };

        $.ajax({
            url: "http://localhost:8080/games/new",
            type: "POST",
            data: JSON.stringify(gameData),
            contentType: "application/json; charset=UTF-8",
            success: function(response) {
                alert("Game successfully added!");
                resetForm();
            },
            error: function() {
                alert("Failed to add game");
            }
        });
    }

    function resetForm() {
        document.getElementById("type").value = "";
        document.getElementById("location").value = "";
        document.getElementById("date").value = "";
        document.getElementById("scoreHome").value = "";
        document.getElementById("scoreAway").value = "";
        document.getElementById("teamHome").selectedIndex = 0;
        document.getElementById("teamAway").selectedIndex = 0;
    }

    function redirectToGames() {
        window.location.href = "games";
    }

    // Bind click event to the submit button
    $('#addGame-button').on('click', function() {
        addGame();
    });

    // Bind click event to the "SHOW GAMES" button
    $('#redirectToGames').on('click', function() {
        redirectToGames();
    });
});
