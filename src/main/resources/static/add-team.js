$(document).ready(function () {
    $.ajax({
        url: "http://localhost:8080/teams/all",
        type: "GET",
        dataType: "json",
        success: function (data) {
            var teamTable = $("#teamTable");
            makeTableTeams(teamTable, data);
        },
        error: function (data) {
            alert('Error: ' + data);
        }
    });
});

function addTeam() {
  const nameInput = document.getElementById("teamname");
  const locationInput = document.getElementById("teamlocation");
  const coachInput = document.getElementById("teamcoach");
  const name = nameInput.value.trim();
  const location = locationInput.value.trim();
  const coach = coachInput.value.trim();
  fetch("http://localhost:8080/teams/new", {
    method: "POST",
    body: JSON.stringify({
      name: name,
      location: location,
      coach: coach,
    }),
    headers: {
      "Content-Type": "application/json; charset=UTF-8",
    },
  })
    .then((response) => (response.json(), alert("Team successfully added!")))
    .catch((error) => console.log("error: ", error.message));
}

function redirectToTeams() {
    window.location.href = "teams";
}