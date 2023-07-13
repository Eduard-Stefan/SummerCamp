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
    .then((response) => (response.json(), alert("Player successfully added!")))
    .catch((error) => console.log("error: ", error.message));
}

function redirectToPlayers() {
    window.location.href = "players";
}
