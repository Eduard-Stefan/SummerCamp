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

  if (
    date === "" ||
    location === "" ||
    type === "" ||
    score1 === "" ||
    score2 === "" ||
    team1 === "" ||
    team2 === ""
  ) {
    // Display an error message or handle empty inputs
    alert("Please fill in all fields");
    return;
  }

  fetch("http://localhost:8080/games/new", {
    method: "POST",
    body: JSON.stringify({
      date: date,
      location: location,
      gameType: type,
      score1: score1,
      score2: score2,
      team1: { id: team1 },
      team2: { id: team2 },
    }),
    headers: {
      "Content-Type": "application/json; charset=UTF-8",
    },
  })
    .then((response) => {
      if (response.ok) {
        alert("Game successfully added!");
        redirectToGames();
      } else {
        throw new Error("Failed to add game");
      }
    })
    .catch((error) => console.log("error: ", error.message));
}

function redirectToGames() {
  window.location.href = "games";
}
