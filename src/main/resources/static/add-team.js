function addTeam() {
  const nameInput = document.getElementById("teamname");
  const locationInput = document.getElementById("teamlocation");
  const coachInput = document.getElementById("teamcoach");
  const name = nameInput.value.trim();
  const location = locationInput.value.trim();
  const coach = coachInput.value.trim();

  if (name === "" || location === "" || coach === "") {
    // Display an error message or handle empty inputs
    alert("Please fill in all fields");
    return;
  }

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
    .then((response) => {
      if (response.ok) {
        alert("Team successfully added!");
      } else {
        throw new Error("Failed to add team");
      }
    })
    .catch((error) => console.log("error: ", error.message));
}

function redirectToTeams() {
    window.location.href = "teams";
}
