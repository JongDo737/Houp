document.addEventListener("DOMContentLoaded", function () {
  // 5개의 배경 이미지 배열 생성
  const backgroundImages = [
    "../images/waiting/1.png",
    "../images/waiting/2.png",
    "../images/waiting/3.png",
    "../images/waiting/4.png",
    "../images/waiting/5.png",
  ];

  // 랜덤으로 이미지를 선택
  const randomIndex = Math.floor(Math.random() * backgroundImages.length);
  const selectedImage = backgroundImages[randomIndex];

  // body의 배경 이미지를 선택된 이미지로 설정
  document.body.style.backgroundImage = `url(${selectedImage})`;

  // 로컬 스토리지에서 폼 데이터 가져오기
  const formData = JSON.parse(localStorage.getItem("formData"));

  // 서버에 폼 데이터 전송
  function sendPredictionRequest() {
    fetch("your_domain_url", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(formData),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok.");
        }
        return response.json();
      })
      .then((data) => {
        // 정상 응답이 오면 데이터를 저장하고 result.html로 이동
        localStorage.setItem("predictionResult", JSON.stringify(data));
        window.location.href = "result.html";
      })
      .catch((error) => {
        console.error("Error occurred, retrying request:", error);
        // 에러 발생 시 다시 요청
        setTimeout(sendPredictionRequest, 1000); // 1초 후에 다시 시도
      });
  }

  // 첫 요청 보내기
  sendPredictionRequest();
});
