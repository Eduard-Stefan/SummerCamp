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

  fetch("http://localhost:8080/games/new", {
    method: "POST",
    body: JSON.stringify({
      gameType: type,
      location: location,
      date: date,
      scoreHome: scoreHome,
      scoreAway: scoreAway,
      teamHome: { id: teamHome },
      teamAway: { id: teamAway },
    }),
    headers: {
      "Content-Type": "application/json; charset=UTF-8",
    },
  })
    .then((response) => {
      if (response.ok) {
        alert("Game successfully added!");
      } else {
        throw new Error("Failed to add game");
      }
    })
    .catch((error) => console.log("error: ", error.message));
}

function redirectToGames() {
  window.location.href = "games";
}
