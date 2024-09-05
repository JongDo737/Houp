document.addEventListener("DOMContentLoaded", function () {
  console.log("JS Loaded");
  // 로컬 스토리지에서 데이터 가져오기
  const predictionResult = JSON.parse(localStorage.getItem("predictionResult"));
  const formData = JSON.parse(localStorage.getItem("formData"));

  // 사용자 이름 채우기
  document.getElementById("userName").textContent = formData.name;
  document.getElementById("userName2").textContent = formData.name;

  // 랭킹 정보
  const place1Name = predictionResult.diseaseDetails.name; // rankings.place1.name 값
  const age = formData.ageGroup; // age 값
  const ranks = predictionResult.diseaseDetails.rank;

  document.getElementById("place1Name").textContent = place1Name;
  document.querySelector("#rankingTitle .age").textContent = age;
  document.querySelector("#rankingTitle .ranking").textContent = ranks; // # 대신 . 사용

  // 질병 정보 채우기
  document.getElementById("diseaseNameValue").textContent =
    predictionResult.diseaseDetails.name;
  document.getElementById("diseaseKindValue").textContent =
    predictionResult.diseaseDetails.category;
  document.getElementById("diseaseDescription").textContent =
    predictionResult.diseaseDetails.description;

  // document.getElementById("diseaseRankValue").textContent =
  //   predictionResult.diseaseDetails.rank;
  // document.getElementById("diseasePatients").textContent =
  //   predictionResult.diseaseDetails.patients;

  console.log("Prediction Result: ", predictionResult);

  // 치료비용 채우기
  document.getElementById("medicalCost").textContent =
    predictionResult.diseaseDetails.medicalCost.toLocaleString();

  // 입원 확률 및 외래 확률 채우기
  document.getElementById("hospitalizationRate").textContent =
    predictionResult.hospitalization.rate.toFixed(2) + "%";
  document.getElementById("outpatientRate").textContent =
    predictionResult.outpatient.rate.toFixed(2) + "%";

  // 질병 순위 차트 그리기
  drawRankingChart(predictionResult.rankings, predictionResult.diseaseDetails);

  // 입원 환자 추이 그래프
  drawLineChart("hospitalizationChart", predictionResult.hospitalization.trend);
  document.getElementById(
    "averageHospitalizationDays"
  ).textContent = `${predictionResult.hospitalization.averageDays}일`;

  //외래환자
  drawLineChart("outpatientChart", predictionResult.outpatient.trend);
  document.getElementById(
    "averageOutpatientDays"
  ).textContent = `${predictionResult.outpatient.averageDays}일`;

  // 산재처리 확률 버튼 초기 설정
  const riskButton = document.querySelector(".risk-button");
  riskButton.textContent = "산재처리 확률 확인중...";
  riskButton.disabled = true;

  // 질병명, 직종 유형, 질병 유형을 URL로 인코딩하여 요청
  // const encodedDiseaseName = encodeURIComponent(
  //   predictionResult.diseaseDetails.name
  // );
  // const encodedJobKind = encodeURIComponent(predictionResult.jobKind);
  // const encodedDiseaseKind = encodeURIComponent(predictionResult.diseaseKind);

  function requestCompensationInfo(retryCount = 0) {
    const maxRetries = 10; // 최대 재시도 횟수
    const apiUrl = "your_domain_url";

    // 로컬 스토리지에서 predictionResult 데이터 가져오기
    const predictionResult = JSON.parse(
      localStorage.getItem("predictionResult")
    );

    // POST 요청에 사용할 데이터를 준비합니다.
    const requestData = {
      diseaseName: predictionResult.diseaseDetails.name, // 예: "급성 충수염"
      jobKind: predictionResult.jobKind, // 예: "제조관련 단순 종사원"
      diseaseKind: predictionResult.diseaseKind, // 예: "악성신생물(직업성 암 포함)"
      painDescription: formData.painDescription, //
    };

    // POST 요청을 수행합니다.
    fetch(apiUrl, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(requestData),
    })
      .then((response) => response.json())
      .then((data) => {
        if (
          data.approvalProbability !== undefined &&
          data.caseExamples !== undefined
        ) {
          // 응답 데이터 로컬 스토리지에 저장
          localStorage.setItem("insData", JSON.stringify(data));

          // 버튼 활성화
          riskButton.textContent = "산재 확률 지금 바로 확인하기";
          riskButton.disabled = false;

          // 버튼 클릭 시 ins.html로 이동
          riskButton.addEventListener("click", function () {
            window.location.href = "ins.html";
          });
        } else {
          console.error("Data structure is not as expected:", data);
          if (retryCount < maxRetries) {
            setTimeout(() => requestCompensationInfo(retryCount + 1), 2000);
          } else {
            riskButton.textContent = "산재처리 확률 확인중...";
          }
        }
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
        if (retryCount < maxRetries) {
          setTimeout(() => requestCompensationInfo(retryCount + 1), 2000);
        } else {
          riskButton.textContent = "산재처리 확률중...";
        }
      });
  }

  // 첫 번째 요청 시작
  requestCompensationInfo();
});

function drawRankingChart(rankings, description) {
  // 기본 데이터 (랭킹 1위부터 3위까지)
  const data = [
    { rank: 1, name: rankings.place1.name, value: rankings.place1.patients },
    { rank: 2, name: rankings.place2.name, value: rankings.place2.patients },
    { rank: 3, name: rankings.place3.name, value: rankings.place3.patients },
  ];

  // description.rank가 3보다 크면, 추가 데이터를 포함시킴
  if (description.rank > 3) {
    data.push({
      rank: description.rank,
      name: description.name,
      value: description.patients,
    });
  }

  const svg = d3
    .select("#rankingChart")
    .append("svg")
    .attr("width", "100%")
    .attr("height", "100%");

  const margin = { top: 80, right: 50, bottom: 80, left: 250 };
  const width = parseInt(svg.style("width")) - margin.left - margin.right;
  const height = parseInt(svg.style("height")) - margin.top - margin.bottom;

  const maxValue = d3.max(data, (d) => d.value);
  const x = d3
    .scaleLinear()
    .domain([0, maxValue])
    .range([0, width * 0.7]); // 막대 길이 조절 (1위 막대 최대 100%)

  const y = d3
    .scaleBand()
    .domain(data.map((d) => d.name))
    .range([0, height])
    .padding(0.1);

  const g = svg
    .append("g")
    .attr("transform", `translate(${margin.left},${margin.top})`);

  // 그라데이션 효과를 위한 정의
  const gradient = svg
    .append("defs")
    .append("linearGradient")
    .attr("id", "barGradient")
    .attr("x1", "0%")
    .attr("y1", "0%")
    .attr("x2", "100%")
    .attr("y2", "0%");
  gradient
    .append("stop")
    .attr("offset", "0%")
    .attr("style", "stop-color:#a8a8f7;stop-opacity:1");
  gradient
    .append("stop")
    .attr("offset", "100%")
    .attr("style", "stop-color:#6a5acd;stop-opacity:1");

  g.selectAll(".bar")
    .data(data)
    .enter()
    .append("rect")
    .attr("class", "bar")
    .attr("x", 0)
    .attr("y", (d) => y(d.name))
    .attr("width", (d) => x(d.value))
    .attr("height", y.bandwidth())
    .attr("rx", y.bandwidth() / 3) // 둥근 모서리 적용
    .style("fill", (d) =>
      d.value === maxValue ? "url(#barGradient)" : "#a8a8f7"
    ); // 제일 긴 막대에만 그라데이션 적용

  // 이름이 13자 이상이면 ...로 표시
  g.selectAll(".bar-label")
    .data(data)
    .enter()
    .append("text")
    .attr("class", "bar-label")
    .attr("x", -10)
    .attr("y", (d) => y(d.name) + y.bandwidth() / 2 + 4)
    .style("font-size", "13px") // 텍스트 크기 축소
    .text(
      (d) =>
        `${d.rank}위 ${
          d.name.length > 13 ? d.name.slice(0, 13) + "..." : d.name
        }`
    );

  // 4번째 막대가 있는 경우, 3위와 4위 사이에 물결 표시 추가
  if (data.length > 3) {
    g.append("text")
      .attr("x", -10)
      .attr("y", y(data[2].name) + y.bandwidth() + y.bandwidth() / 2)
      .attr("class", "bar-label")
      .style("font-size", "50px");
  }

  // 값 텍스트
  g.selectAll(".bar-value")
    .data(data)
    .enter()
    .append("text")
    .attr("class", "bar-value")
    .attr("x", (d) => x(d.value) + 5)
    .attr("y", (d) => y(d.name) + y.bandwidth() / 2 + 4)
    .text((d) => d3.format(",")(d.value));
}

function drawLineChart(chartId, data) {
  const svg = d3
    .select(`#${chartId}`)
    .append("svg")
    .attr("width", "100%")
    .attr("height", "100%");

  const width = parseInt(svg.style("width")) - 40;
  const height = parseInt(svg.style("height")) - 40;

  const maxValue = d3.max(Object.values(data));

  // x축 도메인을 설정하고, 2021과 2023 사이의 간격을 좁히기 위해 범위를 조정
  const x = d3
    .scalePoint()
    .domain(Object.keys(data))
    .range([width * 0.25, width * 0.75]); // 2021과 2023 간의 간격을 좁히기 위해 범위를 조정

  const y = d3
    .scaleLinear()
    .domain([0, maxValue])
    .range([height * 3, 0]);

  const line = d3
    .line()
    .x((d) => x(d.year))
    .y((d) => y(d.value))
    .curve(d3.curveMonotoneX);

  const trendData = Object.keys(data).map((year) => ({
    year: year,
    value: data[year],
  }));

  const g = svg.append("g").attr("transform", "translate(20, 20)");

  // Path for the line chart
  g.append("path")
    .datum(trendData)
    .attr("class", "line")
    .attr("d", line)
    .style("stroke-width", "5px") // 선의 두께를 더 굵게 설정
    .style("stroke", "#a8a8f7"); // 선 색상 지정

  // Dots on the line chart
  g.selectAll(".dot")
    .data(trendData)
    .enter()
    .append("circle")
    .attr("class", "dot")
    .attr("cx", (d) => x(d.year))
    .attr("cy", (d) => y(d.value))
    .attr("r", 8)
    .style("fill", "#5635d9"); // 점 색상 지정

  // Labels for the data points
  g.selectAll(".point-label")
    .data(trendData)
    .enter()
    .append("text")
    .attr("class", "point-label")
    .attr("x", (d) => x(d.year))
    .attr("y", (d) => y(d.value) - 10)
    .text((d) => d.value)
    .style("font-size", "12px") // 텍스트 크기 조정
    .style("fill", "#a8a8f7"); // 텍스트 색상 지정

  // X Axis (하단 축만 남기고 상단 축 제거)
  const xAxis = g
    .append("g")
    .attr("class", "axis axis-x")
    .call(d3.axisBottom(x).tickSize(0))
    .attr("transform", `translate(0, ${height})`)
    .style("stroke-width", "1px") // 축의 두께를 설정
    .style("stroke", "#000"); // 축의 색상 지정

  // Y Axis (좌측 축만 남기고 우측 축 제거)
  const yAxis = g
    .append("g")
    .attr("class", "axis axis-y")
    .call(d3.axisLeft(y).tickSize(0))
    .style("stroke-width", "1px") // 축의 두께를 설정
    .style("stroke", "#000"); // 축의 색상 지정

  // 하단과 좌측 축을 연결
  g.append("line")
    .attr("x1", 0)
    .attr("y1", height)
    .attr("x2", width)
    .attr("y2", height)
    .style("stroke", "#000")
    .style("stroke-width", "1px");

  g.append("line")
    .attr("x1", 0)
    .attr("y1", 0)
    .attr("x2", 0)
    .attr("y2", height)
    .style("stroke", "#000")
    .style("stroke-width", "1px");
}
