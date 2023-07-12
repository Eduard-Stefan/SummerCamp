function makeTableGames(container, data) {
    var table = $("<table/>").addClass('gameTable');

    var headerRow = $("<tr/>");
    headerRow.append($("<th/>").text("Id"));
    headerRow.append($("<th/>").text("Type"));
    headerRow.append($("<th/>").text("Location"));
    headerRow.append($("<th/>").text("Date"));
    headerRow.append($("<th/>").text("Score 1"));
    headerRow.append($("<th/>").text("Score 2"));
    headerRow.append($("<th/>").text("Team 1"));
    headerRow.append($("<th/>").text("Team 2"));
    table.append(headerRow);
    $.each(data, function (rowIndex, r) {
        var row = $("<tr/>");
        var i=0;
        $.each(r, function (colIndex, c) {
            i++;
            if(i<7)
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
        url: "http://localhost:8080/games/all",
        type: "GET",
        dataType: "json",
        success: function (data) {
            var gameTable = $("#gameTable");
            makeTableGames(gameTable, data);
        },
        error: function (data) {
            alert('Error: ' + data);
        }
    });
});
function addGame() {
  const dateInput = document.getElementById("gamedate");
  const locationInput = document.getElementById("gamelocation");
  const typeInput = document.getElementById("gametype");
  const score1Input = document.getElementById("gamescore1");
  const score2Input = document.getElementById("gamescore2");
  const team1Input = document.getElementById("gameteam1");
  const team2Input = document.getElementById("gameteam2");
  const date = dateInput.value;
  const location = locationInput.value.trim();
  const type = typeInput.value.trim();
  const score1 = score1Input.value.trim();
  const score2 = score2Input.value.trim();
  const team1 = team1Input.value.trim();
  const team2 = team2Input.value.trim();

  fetch("http://localhost:8080/games/new", {
    method: "POST",
    body: JSON.stringify({
      date: date,
      location : location,
      gameType : type,
      score1 : score1,
      score2 : score2,
      team1: {"id": team1},
      team2: {"id": team2},
    }),
    headers: {
      "Content-Type": "application/json; charset=UTF-8",
    },
  })
    .then((response) => (response.json(), alert("Successfully added to the DB!")))
    .catch((error) => console.log("error: ", error.message));
}