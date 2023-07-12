function makeTablePlayers(container, data) {
    var table = $("<table/>").addClass('playerTable');

    var headerRow = $("<tr/>");
    headerRow.append($("<th/>").text("Id"));
    headerRow.append($("<th/>").text("Name"));
    headerRow.append($("<th/>").text("Age"));
    headerRow.append($("<th/>").text("Nationality"));
    headerRow.append($("<th/>").text("Team"));
    table.append(headerRow);
    $.each(data, function (rowIndex, r) {
            var row = $("<tr/>");
            var i=0;
            $.each(r, function (colIndex, c) {
                i++;
                if(i<5)
                    row.append($("<td/>").text(c));
                else
                    row.append($("<td/>").text(c.name));
            });
            table.append(row);
        });
    return container.append(table);
}
$(document).ready(function () {
    $.ajax({
        url: "http://localhost:8080/players/all",
        type: "GET",
        dataType: "json",
        success: function (data) {
            var playerTable = $("#playerTable");
            makeTablePlayers(playerTable, data);
        },
        error: function (data) {
            alert('Error: ' + data);
        }
    });
});
function addPlayer() {
  const nameInput = document.getElementById("playername");
  const ageInput = document.getElementById("playerage");
  const nationalityInput = document.getElementById("playernationality");
  const teamInput = document.getElementById("playerteam");
  const name = nameInput.value.trim();
  const nationality = nationalityInput.value.trim();
  const age = ageInput.value.trim();
  const team = teamInput.value.trim();
  fetch("http://localhost:8080/players/new", {
    method: "POST",
    body: JSON.stringify({
      name: name,
      age: age,
      nationality: nationality,
      team: {"id": team},
    }),
    headers: {
      "Content-Type": "application/json; charset=UTF-8",
    },
  })
    .then((response) => (response.json(), alert("Successfully added to the DB!")))
    .catch((error) => console.log("error: ", error.message));
}
