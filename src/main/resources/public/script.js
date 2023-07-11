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
