$(document).ready(function() {
    function addTeam() {
        const nameInput = document.getElementById("name");
        const locationInput = document.getElementById("location");
        const coachInput = document.getElementById("coach");
        const name = nameInput.value.trim();
        const location = locationInput.value.trim();
        const coach = coachInput.value.trim();

        if (name === "" || location === "" || coach === "") {
            alert("Please enter valid data in all fields");
            return;
        }

        $.ajax({
            url: "http://localhost:8080/teams/new",
            type: "POST",
            data: JSON.stringify({
                name: name,
                location: location,
                coach: coach,
            }),
            contentType: "application/json; charset=UTF-8",
            success: function(response) {
                alert("Team successfully added!");
                resetForm();
            },
            error: function() {
                alert("Failed to add team");
            }
        });
    }

    function resetForm() {
        document.getElementById("name").value = "";
        document.getElementById("location").value = "";
        document.getElementById("coach").value = "";
    }

    function redirectToTeams() {
        window.location.href = "teams";
    }

    $('#addTeam-button').on('click', function() {
        addTeam();
    });

    $('#redirectToTeams').on('click', function() {
        redirectToTeams();
    });
});
