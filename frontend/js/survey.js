document.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("surveyForm");
  const autoFillButton = document.getElementById("autoFillButton");

  // 자동 입력 버튼 클릭 시 실행되는 함수
  autoFillButton.addEventListener("click", function () {
    document.getElementById("name").value = "홍길동";
    document.getElementById("male").checked = true;
    document.querySelector('div[data-value="male"]').classList.add("selected");
    document.getElementById("age").value = 2; // 30대로 설정
    document.getElementById("occupation").value = "콘크리트믹서트럭운전사";
    document
      .querySelector('.pain-point-marker[data-id="왼쪽 정강이"]')
      .classList.add("checked");
    document.getElementById("painAreas").value = "왼쪽 정강이";
    document.getElementById("existing-condition").value =
      "어린 시절 계단을 오르다가 심하게 넘어져서 무릎이 다친적이 있습니다.";
    document.getElementById("detailed-pain").value =
      "무릎이 시큰시큰 거리면서 아프고 움직일 때 따끔거리게 아픕니다.";
  });

  form.addEventListener("submit", function (event) {
    event.preventDefault(); // 기본 폼 제출 방지

    // 필수 질문들에 대한 검증
    let isValid = true;
    let missingFields = [];

    const name = document.getElementById("name").value.trim();
    const gender = document.querySelector('input[name="gender"]:checked');
    const job = document.getElementById("occupation").value.trim();
    const painAreas = document.getElementById("painAreas").value.trim();
    const detailedPain = document.getElementById("detailed-pain").value.trim();
    const ageSliderValue = document.getElementById("age").value;
    const ageGroups = ["10대", "20대", "30대", "40대", "50대", "60대", "70대"];
    const ageGroup = ageGroups[ageSliderValue];

    // 이름 검증
    if (!name) {
      isValid = false;
      missingFields.push("이름");
    }

    // 성별 검증
    if (!gender) {
      isValid = false;
      missingFields.push("성별");
    }

    // 직업 검증
    if (!job) {
      isValid = false;
      missingFields.push("직업");
    }

    // 아픈 부위 검증
    if (!painAreas) {
      isValid = false;
      missingFields.push("아픈 부위 체크");
    }

    // 상세 통증 설명 검증
    if (!detailedPain) {
      isValid = false;
      missingFields.push("통증 부위 상세 설명");
    }

    // 검증 결과에 따라 제출 또는 경고 메시지 표시
    if (isValid) {
      const formData = {
        name: name,
        gender: gender.value,
        ageGroup: ageGroup, // 선택 사항으로 포함됨
        job: job,
        painArea: painAreas,
        painDescription: detailedPain,
      };

      // 로컬 스토리지에 폼 데이터 저장
      localStorage.setItem("formData", JSON.stringify(formData));

      // Waiting 페이지로 즉시 이동
      window.location.href = "waiting.html";
    } else {
      alert("다음 필수 항목들을 입력해주세요: " + missingFields.join(", "));
    }
  });

  // 타이핑 효과 함수
  function typeText(element, text, delay, callback) {
    let index = 0;
    element.style.visibility = "visible"; // 타이핑 시작 시 텍스트 보이도록 설정

    function addLetter() {
      if (index < text.length) {
        element.innerHTML += text.charAt(index);
        index++;
        setTimeout(addLetter, delay);
      } else if (callback) {
        callback(); // 타이핑이 끝나면 콜백 호출 (다음 텍스트 시작)
      }
    }

    element.innerHTML = ""; // 기존 텍스트 초기화
    addLetter();
  }

  // 타이핑 효과 적용할 요소들
  const h1Element = document.querySelector(".intro h1");
  const pElement = document.querySelector(".intro h2");
  const surveyInfoElement = document.querySelector(".survey-info p");

  // 모든 텍스트를 초기에는 숨김
  [h1Element, pElement, surveyInfoElement].forEach((el) =>
    el.classList.add("hidden-text")
  );

  // 첫 번째 텍스트 타이핑 효과 적용 후 두 번째 텍스트로 이어짐
  typeText(h1Element, "반가워요!", 50, () => {
    typeText(pElement, "지금부터 간단한 설문조사를 시작할게요.", 30, () => {
      typeText(surveyInfoElement, "약 7분, 7개의 질문", 30);
    });
  });

  // 스크롤 애니메이션 효과
  const steps = document.querySelectorAll(".step");

  function checkScroll() {
    steps.forEach((step) => {
      const rect = step.getBoundingClientRect();
      if (rect.top < window.innerHeight - 100) {
        step.classList.add("visible");
      }
    });
  }

  window.addEventListener("scroll", checkScroll);
  checkScroll(); // 초기 로드 시 바로 체크

  const painPoints = [
    { id: "이마", x: "50.5%", y: "12%" },
    { id: "왼쪽 어깨", x: "40%", y: "25%" },
    { id: "오른쪽 어깨", x: "60%", y: "25%" },
    { id: "가슴", x: "50.5%", y: "28%" },
    { id: "몸통", x: "50.5%", y: "35%" },
    { id: "왼쪽 팔꿈치", x: "30%", y: "31%" },
    { id: "오른쪽 팔꿈치", x: "70%", y: "31%" },
    { id: "왼쪽 손", x: "20%", y: "30%" },
    { id: "오른쪽 손", x: "80%", y: "30%" },
    { id: "골반 및 엉덩이", x: "50.5%", y: "40%" },
    { id: "성기", x: "50.5%", y: "45%" },
    { id: "왼쪽 허벅지", x: "43%", y: "52%" },
    { id: "오른쪽 허벅지", x: "57%", y: "52%" },
    { id: "왼쪽 무릎", x: "43%", y: "60%" },
    { id: "오른쪽 무릎", x: "57%", y: "60%" },
    { id: "왼쪽 정강이", x: "42%", y: "68%" },
    { id: "오른쪽 정강이", x: "57%", y: "68%" },
    { id: "왼쪽 발목", x: "42%", y: "75%" },
    { id: "오른쪽 발목", x: "57%", y: "75%" },
    { id: "왼쪽 발", x: "40%", y: "80%" },
    { id: "오른쪽 발", x: "58%", y: "80%" },
  ];

  const painPointsContainer = document.querySelector(".pain-points");

  painPoints.forEach((point) => {
    const marker = document.createElement("div");
    marker.classList.add("pain-point-marker");
    marker.style.left = point.x;
    marker.style.top = point.y;
    marker.dataset.id = point.id;
    marker.addEventListener("click", function () {
      clearAllChecks();
      this.classList.toggle("checked");
      updatePainAreas();
    });
    painPointsContainer.appendChild(marker);
  });

  function clearAllChecks() {
    document
      .querySelectorAll(".pain-point-marker.checked")
      .forEach((marker) => {
        marker.classList.remove("checked");
      });
  }

  function updatePainAreas() {
    const selectedPainPoints = [];
    document
      .querySelectorAll(".pain-point-marker.checked")
      .forEach((marker) => {
        selectedPainPoints.push(marker.dataset.id);
      });

    const painAreasInput = document.getElementById("painAreas");
    painAreasInput.value = selectedPainPoints.join(", ");
  }
});
