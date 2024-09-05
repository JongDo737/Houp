document.addEventListener("DOMContentLoaded", function () {
  const apiKey = "93677929b299e7c765a052b6bc1dd8c5";
  const city = "Seoul";
  const apiUrl = `https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=${apiKey}&units=metric&lang=kr`;

  fetch(apiUrl)
    .then((response) => response.json())
    .then((data) => {
      const date = new Date().toISOString().split("T")[0];
      const temperature = `${Math.round(data.main.temp)}°C`;
      const description = data.weather[0].description;
      const location = data.name;

      document.getElementById("date").textContent = date;
      document.getElementById("temperature").textContent = temperature;
      document.getElementById("description").textContent = description;
      const weatherIcon = document.getElementById("weather-icon");
      const weatherDescription =
        document.getElementById("description").textContent;

      if (
        weatherDescription.includes("비") ||
        weatherDescription.includes("태풍")
      ) {
        weatherIcon.src = "images/main/rain.png";
      } else {
        weatherIcon.src = "images/main/sun.png";
      }
    })
    .catch((error) => {
      console.error("Error fetching weather data:", error);
    });
});
