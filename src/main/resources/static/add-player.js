function addPlayer() {
  const nameInput = document.getElementById("name");
  const ageInput = document.getElementById("age");
  const nationalityInput = document.getElementById("nationality");
  const teamInput = document.getElementById("team");
  const name = nameInput.value.trim();
  const age = ageInput.value.trim();
  const nationality = nationalityInput.value.trim();
  const team = teamInput.value.trim();

  if (name === "" || age === "" || nationality === "" || team === "") {
    alert("Please fill in all fields");
    return;
  }

  fetch("http://localhost:8080/players/new", {
    method: "POST",
    body: JSON.stringify({
      name: name,
      age: age,
      nationality: nationality,
      team: { id: team },
    }),
    headers: {
      "Content-Type": "application/json; charset=UTF-8",
    },
  })
    .then((response) => {
      if (response.ok) {
        alert("Player successfully added!");
      } else {
        throw new Error("Failed to add player");
      }
    })
    .catch((error) => console.log("error: ", error.message));
}

function redirectToPlayers() {
  window.location.href = "players";
}
