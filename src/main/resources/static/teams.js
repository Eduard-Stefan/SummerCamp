function makeTableTeams(container, data) {
    var table = $("<table/>").addClass('teamTable');

    var headerRow = $("<tr/>");
    headerRow.append($("<th/>").text("Id"));
    headerRow.append($("<th/>").text("Name"));
    headerRow.append($("<th/>").text("Location"));
    headerRow.append($("<th/>").text("Coach"));
    table.append(headerRow);
    $.each(data, function (rowIndex, r) {

        var row = $("<tr/>");
        $.each(r, function (colIndex, c) {
            row.append($("<td/>").text(c));
        });
        table.append(row);
    });
    return container.append(table);
}
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
    .then((response) => (response.json(), alert("Successfully added to the DB!")))
    .catch((error) => console.log("error: ", error.message));
}

