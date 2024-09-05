document.addEventListener("DOMContentLoaded", function () {
  const ctx = document.getElementById("myPieChart").getContext("2d");
  const myPieChart = new Chart(ctx, {
    type: "pie",
    data: {
      labels: [
        "손목 터널 증후군",
        "목과 척추 질환",
        "류마티스 관절염",
        "무릎 통증",
      ],
      datasets: [
        {
          data: [44.9, 11.5, 23.2, 21.4],
          backgroundColor: ["#FF6384", "#36A2EB", "#FFCE56", "#4BC0C0"],
          hoverBackgroundColor: ["#FF6384", "#36A2EB", "#FFCE56", "#4BC0C0"],
        },
      ],
    },
    options: {
      responsive: true,
      maintainAspectRatio: true,
      plugins: {
        legend: {
          position: "top",
        },
        tooltip: {
          callbacks: {
            label: function (tooltipItem) {
              return tooltipItem.label + ": " + tooltipItem.raw + "%";
            },
          },
        },
      },
    },
  });
});
