document.addEventListener("DOMContentLoaded", function () {
  // 로컬 스토리지에서 데이터 가져오기
  const storedData = JSON.parse(localStorage.getItem("insData"));
  const formData = JSON.parse(localStorage.getItem("formData"));

  // 사용자 이름 설정
  document.getElementById("userName").textContent = formData.name;
  document.getElementById("userName2").textContent = formData.name;

  // 로컬 스토리지에서 가져온 데이터 사용
  document.getElementById("approvalProbability").textContent =
    storedData.approvalProbability + "%";
  document.getElementById("result").textContent = storedData.result;
  document.getElementById("firstCaseSummary").textContent =
    storedData.caseExamples.firstCase.summary;
  document.getElementById("secondCaseSummary").textContent =
    storedData.caseExamples.secondCase.summary;
  document.getElementById("judgmentDocument").textContent =
    storedData.caseExamples.firstCase.judgmentDocument;
});

function showCase(caseNumber) {
  document.getElementById("firstCase").style.display =
    caseNumber === "first" ? "block" : "none";
  document.getElementById("secondCase").style.display =
    caseNumber === "second" ? "block" : "none";

  const tabs = document.querySelectorAll(".case-tab");
  tabs.forEach((tab) => {
    tab.classList.remove("active");
  });
  document
    .querySelector(`.case-tab:nth-child(${caseNumber === "first" ? 1 : 2})`)
    .classList.add("active");
}

// 불필요한 중복 함수 제거
function toggleFullData(caseNumber) {
  const fullData = document.querySelector(
    `#${caseNumber}Case .full-data-content`
  );
  const button = document.querySelector(
    `#${caseNumber}Case .show-full-data-button`
  );

  // 로컬 스토리지에서 데이터를 가져옴
  const storedData = JSON.parse(localStorage.getItem("insData"));

  if (fullData.style.display === "none" || fullData.style.display === "") {
    fullData.style.display = "block";
    button.innerHTML = "판결 데이터 숨기기";
  } else {
    fullData.style.display = "none";
    button.innerHTML = "판결 데이터 확인하러가기 ";
  }

  // 현재 활성화된 사례에 따라 판결 데이터를 설정
  const judgmentDocument =
    caseNumber === "first"
      ? storedData.caseExamples.firstCase.judgmentDocument
      : storedData.caseExamples.secondCase.judgmentDocument;

  document.querySelector(`#${caseNumber}Case .judgmentDocument`).textContent =
    judgmentDocument;
}
