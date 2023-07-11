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
            var tableContainer = $("#tableContainer");
            makeTablePlayers(tableContainer, data);
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
            var tableContainer = $("#tableContainer");
            makeTableGames(tableContainer, data);
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
            var tableContainer = $("#tableContainer");
            makeTableTeams(tableContainer, data);
        },
        error: function (data) {
            alert('Error: ' + data);
        }
    });
});

