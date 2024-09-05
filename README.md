# AI기반 건강 진단 및 산재 보험 예측 서비스 HOUP
![%EC%8A%AC%EB%9D%BC%EC%9D%B4%EB%93%9C1 1](https://github.com/user-attachments/assets/867a5abf-d25e-43ef-8e12-7d0cce59d7aa)
## 프로젝트 소개 영상

https://youtu.be/f80cOfu-WbQ

## 제안 배경
<img width="555" alt="image" src="https://github.com/user-attachments/assets/93b54b28-1470-4251-ba5b-6f755bb1e096">

업무 상 질병 발생현황 통계 그래프에 따르면, 연도 별 업무 상 질병 발생률과 노동자의 추이가 계속적으로 증가하고 있습니다. 그에 비해 산재가 발생했을 시 산업재해에 대한 대처 반응이나 보상을 어떻게 받아야 할 지 모르는 경우는 대다수라고 합니다.

이와 같은 상황에서 저희 호우프는 번거롭고 복잡한 절차를 제외하고, 정말로 노동자에게 필요한 서비스를 구성하여 독보적인 호우프만의 서비스를 구성하였습니다.
## 소개
호우프(HOUP)는 산업재해로 인한 피해를 최소화하고 노동자들이 더 나은 건강 관리를 할 수 있도록 돕기 위해 개발된 웹 서비스입니다. 산업재해 발생 시 많은 노동자들이 올바른 대처 방법을 알지 못하고 복잡한 절차에 어려움을 겪는다는 점에 착안하여, 호우프는 이러한 문제를 해결하고자 설계되었습니다.

호우프는 고용노동부의 실제 데이터와 산업재해 통계를 바탕으로 AI를 활용하여 사용자 맞춤형 건강진단을 제공합니다. 사용자는 이 서비스에서 자신의 증상 결과를 빠르고 쉽게 확인할 수 있으며, 예방 방법과 연령대별 질병 발병 통계, 평균 진료비 계산 등 유익한 정보를 일목요연하게 얻을 수 있습니다.

호우프의 핵심 기능은 다음과 같습니다.

1. **산재보상 판결 확률 예측**: 사용자가 자신의 상황을 입력하면, AI가 산재보상을 받을 가능성을 예측하여 핵심 결론을 제시합니다.
2. **유사 판결 사례 제시**: 산재보상과 관련된 유사 판결 사례를 제공하여 사용자가 참고할 수 있도록 구체적인 정보를 제공합니다.
3. **AI 기반 건강 진단**: 사용자가 입력한 데이터를 바탕으로 사용자의 건강 데이터를 분석해 질병을 예측하고, 필요한 정보를 제공합니다.
4. **예방과 대비를 위한 매뉴얼 제공**: 로딩 화면을 활용해 사용자에게 산업재해 예방 및 대비를 위한 매뉴얼을 제공, 교육적 가치를 더합니다.

이 웹 서비스는 절차를 간소화하여 노동자들이 더 쉽게 접근하고 활용할 수 있도록 했으며, 실제로 필요한 정보를 신속하고 정확하게 제공하는 것을 목표로 합니다. 이를 통해 노동자들은 산재 발생 시 더 효과적으로 대처할 수 있으며, 산재보험의 중요성도 쉽게 이해할 수 있게 됩니다.

호우프의 이러한 혁신적인 접근 방식은 고용노동부 공공데이터 대회에서 우수상을 수상하며 그 가치를 인정받았습니다.
## 팀원 구성

<div align="center">

| **신종민 (팀장)** | **김세훈** | **한성익** | **이은솔** |
| :------: |  :------: | :------: | :------: |
| [<img src="https://avatars.githubusercontent.com/u/92903481?v=4" height=150 width=150> <br/> @JongDo737](https://github.com/JongDo737) | [<img src="https://avatars.githubusercontent.com/u/101192772?v=4" height=150 width=150> <br/> @ki-met-hoon](https://github.com/ki-met-hoon) | [<img src="https://avatars.githubusercontent.com/u/108441979?v=4" height=150 width=150> <br/> @seongikx](https://github.com/seongikx) | [<img src="https://avatars.githubusercontent.com/u/165554618?v=4" height=150 width=150> <br/> @unezzol](https://github.com/unezzol) |
| 🍉 **DevOps** | 🍋 **Backend** | 🍊 **Frontend** | 🍇 **Design** |

</div>

## 기술 스택
> **Frontend**
> 
- Vanilla JS

> **Backend**
> 
- Spring Boot 3.2.7
- open feign

> **DevOps**
> 
- AWS
- Python
- Docker
- OpenAI : GPT-4o

> **Design**
> 
- Figma
- Photoshop
- Illustrator
- After Effect
## 사용 데이터
| 출처 | 데이터 항목 |
| --- | --- |
| KOSIS 국가통계 포털 | 경제활동인구 총괄 |
|  | 년도별 총 업무상 질병 발생 현황 |
| 고용노동부 데이터 | 업종별 산재신청 승인현황 |
|  | 업무상질병판정위원회 지역별 판정현황 |
|  | 업무상질병판정위원회 질병별 판정현황 |
|  | 질병판정서 조회 서비스 |
|  | 산재 예방 메뉴얼 |
| 보건복지부 데이터 | 다빈도 질병 통계 데이터 |
| Hugging Face | 의료대화 데이터 셋(ko_medical_chat) |

이 서비스를 구축하기 위해 크게 네가지 데이터를 사용했습니다. 산재와 관련된 데이터는 고용노동부 데이터, 진료와 관련된 보건 복지부 데이터는 진단을 위한 프롬프트 엔지니어링, 진단 결과물의 분석 데이터로 활용되고 있습니다. 의료 대화 데이터셋은 AI의사를 학습시키기 위한 데이터이고, 산재 예방 메뉴얼 데이터를 사용했습니다. 의료 대화 데이터 셋은 약품, 치료 및 진단에 대한 정보를 제공하는 MedGPT 모델 X 위키피디아의 질병 데이터베이스를 통해 만들어진 진단서 번역본입니다.
## 프로젝트 구조
```markdown
├─ frontend/
	│
	├── index.html
	├── ins.html
	├── survey.html
	├── waiting.html
	├── result.html
	├── waiting2.html
	│
	├── css/
	│   ├── result.css
	│   ├── survey.css
	│   ├── main.css
	│   ├── temp.css
	│   ├── ins.css
	│   └── waiting2.css
	│   └── waiting.css
	│
	└── js/
	    ├── chart.js
	    ├── weather.js
	    ├── ins.js
	    ├── survey.js
	    ├── waiting.js
	    └── result.js
	
```

```
  ├─ backend.houp.src.main
      ├─ java
      │  └─ com
      │     └─ example
      │        └─ houp
      │           ├─ HoupApplication.java
      │           ├─ advice
      │           │  ├─ BadRequestException.java
      │           │  ├─ BusinessException.java
      │           │  ├─ ControllerAdvice.java
      │           │  ├─ ErrorResponse.java
      │           │  └─ NotFoundException.java
      │           ├─ support
      │           │  ├─ ToAIDiseaseInfoCaller.java
      │           │  ├─ ToAIReportCaller.java
      │           │  ├─ ToComwelCaller.java
      │           │  ├─ config
      │           │  │  ├─ ComwelFeignConfig.java
      │           │  │  ├─ DiseaseFeignConfig.java
      │           │  │  ├─ ReportFeignConfig.java
      │           │  │  └─ WebMvcConfig.java
      │           │  ├─ exception
      │           │  │  ├─ FeignBadRequestException.java
      │           │  │  ├─ FeignNotFoundException.java
      │           │  │  ├─ JsonParseToAiException.java
      │           │  │  ├─ RetreiveMessageErrorDecoder.java
      │           │  │  └─ XmlParseToComwelException.java
      │           │  └─ util
      │           │     ├─ KindValidator.java
      │           │     ├─ UrlUtility.java
      │           │     └─ exception
      │           │        ├─ DiseaseKindException.java
      │           │        ├─ JobKindException.java
      │           │        └─ UrlDecodingException.java
      │           ├─ toai
      │           │  ├─ dto
      │           │  │  └─ CaseExamples.java
      │           │  └─ service
      │           │     └─ ToAIService.java
      │           ├─ toclient
      │           │  ├─ controller
      │           │  │  └─ ToClientController.java
      │           │  ├─ dto
      │           │  │  ├─ JudgementDocumentResponse.java
      │           │  │  ├─ PredictionResponse.java
      │           │  │  ├─ ReportToClient.java
      │           │  │  ├─ UserDiseaseInfoRequest.java
      │           │  │  └─ UserInfoRequest.java
      │           │  ├─ service
      │           │  │  └─ ToClientService.java
      │           │  └─ support
      │           │     └─ PredictionResponseValidator.java
      │           └─ tocomwel
      │              ├─ dto
      │              │  ├─ Decoded.java
      │              │  ├─ ReportToObject.java
      │              │  └─ StrategyResult.java
      │              ├─ service
      │              │  └─ ToComwelService.java
      │              └─ support
      │                 ├─ DiseaseJobTypeStrategy.java
      │                 ├─ JobStrategy.java
      │                 ├─ JobTypeStrategy.java
      │                 ├─ ReportStrategy.java
      │                 └─ TypeStrategy.java
      └─ resources
         └─ application.yml

```

```
devops/
├── README.md
├── ai
│   ├── lambda_function.py
│   └── lambda_function_san.py
└── docker
│   └── Dockerfile
└── data
    ├── 다빈도질병통계_질별연령별0세구간별_2023_외래.xlsx
    ├── 다빈도질병통계_질별연령별0세구간별_2023_입원.xlsx
    ├── 다빈도질병통계_질별연령별10세구간별_2023_외래.xlsx
    ├── 다빈도질병통계_질별연령별10세구간별_2023_입원.xlsx
    ├── 다빈도질병통계_질별연령별20세구간별_2023_외래.xlsx
    ├── 다빈도질병통계_질별연령별20세구간별_2023_입원.xlsx
    ├── 다빈도질병통계_질별연령별30세구간별_2023_외래.xlsx
    ├── 다빈도질병통계_질별연령별30세구간별_2023_입원.xlsx
    ├── 다빈도질병통계_질별연령별40세구간별_2023_외래.xlsx
    ├── 다빈도질병통계_질별연령별40세구간별_2023_입원.xlsx
    ├── 다빈도질병통계_질별연령별50세구간별_2023_외래.xlsx
    ├── 다빈도질병통계_질별연령별50세구간별_2023_입원.xlsx
    ├── 다빈도질병통계_질별연령별60세구간별_2023_외래.xlsx
    ├── 다빈도질병통계_질별연령별60세구간별_2023_입원.xlsx
    ├── 다빈도질병통계_질별연령별70세구간별_2023_외래.xlsx
    ├── 다빈도질병통계_질별연령별70세구간별_2023_입원.xlsx
    ├── 다빈도질병통계_질별연령별80세구간별_2023_외래.xlsx
    └── 다빈도질병통계_질별연령별80세구간별_2023_입원.xlsx
```
## 인프라 아키텍처
<img src="https://github.com/user-attachments/assets/d799f0d3-2694-4d85-a9e2-48fe869218de" width="90%" height="40%">

## 개발 기간
* `2024-06-26 ~ 2024-08-12`

## 페이지별 기능

### [메인 홈 화면]

- 서비스 소개 섹션
    - **메인 배너**: 화면 상단에 큰 배너로 서비스의 핵심 기능을 한눈에 알 수 있도록 설명하고, 텍스트와 이미지를 함께 사용하여 처음 사용하는 이용자도 쉽고 편하게 시각적으로 구성
    - **서비스 슬로건**: 간단한 문구로 서비스의 가치를 축약하여 서비스의 목적을 사용자에게 전달
    - **주요 기능**: 3~4개의 주요 기능을 아이콘과 함께 소개하여 사용자들이 쉽게 이해할 수 있도록 배치
    


---

<img src="https://github.com/user-attachments/assets/40a763b5-719e-4631-9e9c-3435dd48e9dc" width="70%" height="10%" align="center">

---

### [ 건강 진단 화면 ]

- **건강 진단 첫 화면**

**타이틀**: 상단에 간단하고 직관적인 타이틀을 배치하여 처음 이용하는 사용자도 누구나 쉽게 서비스를 편하게 이용할 수 있는 친절한 문구와 이미지를 이용하여 정리합니다.

**설명 텍스트**: "건강 상태를 자유롭게 입력하세요. (예시: ‘요즘 피곤하고 허리가 아파요’" 같은 안내 문구를 배치하여 사용자가 어떻게 입력할지 쉽게 이해할 수 있도록 합니다.)

- **자유로운 입력 필드**

**입력 창**: 화면 중앙에 **넓고 눈에 띄는 텍스트 입력 창**을 배치합니다.  창을 이용하여 사용자가 자신의 건강 상태를 자연스럽게, 제한 없이 문장으로 입력할 수 있도록 합니다.


---

<img src="https://github.com/user-attachments/assets/97458aa9-93f9-4e0b-80d3-e4ed1eb4fefe" width="70%" height="5%" align="center">

---

### [ 진단 로딩 화면 및 산재 예방, 대처 메뉴얼 ]

- **로딩 화면과 함께 제공되는 정보**

  **정보 제공 공간**: 로딩 화면을 비워두지 않고, **산재 예방 및 대응 방안** 관련 정보를 텍스트 형식으로 배치합니다. 정보는 간결하면서도 핵심적인 내용으로 구성하여 짧은 시간 동안도 충분히 읽고 이해할 수 있도록 합니다.

- **산재 예방 및 대응 방안 정보 구성**

**간결한 텍스트**: 각 메뉴얼은 짧고 이해하기 쉬운 문장으로 구성됩니다. (예시: “작업장에서 넘어질 위험을 줄이기 위해 주변 정리정돈을 철저히 하세요.” 또는 “작업 중 보호 장비 착용은 부상 예방에 필수적입니다.”)

**아이콘 및 이미지**: 각 예방 및 대응 방안을 설명할 때, 해당 정보를 시각적으로 보완할 수 있는 **아이콘**이나 **일러스트**를 함께 배치합니다. 

- **산재 관련 팁**

정보는 실제 상황에서 적용할 수 있는 **실용적인 팁**을 중심으로 구성합니다. 예를 들어, "물건을 들 때는 허리가 아닌 무릎을 굽혀서 드세요" 또는 "위험 구역은 반드시 표지판을 설치하세요" 등의 간단한 조언을 제공합니다.


---

<img src="https://github.com/user-attachments/assets/58256e65-86fd-4e8a-a153-e21ff4f5a83b" width="70%" height="10%" align="center">

---

### [진단 결과 화면 및 관련 정보 제공]

- **진단 결과 및 예방, 대처 방법 제안 :** 사용자가 건강 상태를 문장으로 입력하면, AI가 분석을 마친 후 **진단 결과**를 화면 상단에 한눈에 볼 수 있게 제시됩니다.
- **병원비 계산 및 추정 :** AI가 진단 결과와 연관된 질환이나 상태를 분석한 후, 사용자가 예상할 수 있는 **병원비 계산**을 표시합니다.
- **입원 및 내원 확률 계산**

**확률 계산 그래프** : AI가 사용자 건강 상태를 기반으로 입원 가능성 및 내원 빈도에 대한 **확률을 계산**하여 **막대 그래프**나 **파이 차트**로 시각화합니다.

**< 진단 결과 화면 UI 흐름 요약 >**

- **진단 결과**: AI가 분석한 건강 상태와 주요 문제를 보여줌.
- **예방 방법**: 건강을 개선할 수 있는 실질적인 조언을 카드나 리스트 형식으로 제공.
- **병원비 추정**: 예상 병원비를 세부 항목별로 나누어 제공하고, 보험 적용 여부 선택 가능.
- **입원 및 내원 확률 계산**: 그래프와 차트를 통해 건강 상태에 따른 입원 및 내원 확률을 시각적으로 보여줌.
- **사용자 상호작용**: 사용자 입력에 따라 즉시 변동하는 데이터를 제공하여 보다 개인화된 결과를 제시.


---

<img src="https://github.com/user-attachments/assets/78e9051c-370b-44a2-8a64-e824d2108d42" width="70%" height="5%" align="center">

---

### [산재 판결 화면 ]

- **산재 인정 가능성 계산**

**AI 산재 확률 계산**: **공공 데이터**(산재 판결 데이터를 포함한 통계 정보)를 기반으로 사용자 건강 상태와 유사한 상황에서 산재로 인정될 가능성을 **확률로 계산**하여 제공합니다.

- **유사 판결 사례 제공**

**유사 사례 검색**: AI는 사용자가 입력한 **건강 진단 데이터**와 업무 환경을 기반으로, **공공 데이터에서 유사한 판결 사례**를 찾아 제공합니다.

- **인정된 사례와 불인정된 사례 구분**

사례를 **인정된 사례**와 **불인정된 사례**로 구분하여 보여줍니다. 각 사례에는 짧은 설명과 함께 해당 판결의 요점을 표시합니다.

**인정된 사례**: 어떤 조건이 산재 인정에 핵심적인 역할을 했는지 요약.

**불인정된 사례**: 산재 불인정의 주요 이유 설명

- **유사 판결 재판문 불러오기**

**원문 판결문 링크**: 유사한 사례에서 법원이 내린 **판결문 원문**을 제공하여, 사용자가 더 자세히 확인하고 싶을 경우 **재판문 원문**을 다운로드하거나 읽을 수 있게 합니다.

이 서비스는 사용자가 자신의 산재 인정 가능성을 **AI 분석을 통해 명확히** 파악하고, **유사한 판결 사례**를 기반으로 중요한 결정을 내릴 수 있도록 돕습니다. 산재가 인정될 가능성이나 불인정 이유를 이해하기 쉽게 시각화하고, 관련 판결문을 제공하는 기능은 **법률적 복잡성을 단순화**시켜 사용자의 이해도를 높이는 데 큰 역할을 합니다.


---

<img src="https://github.com/user-attachments/assets/afa77282-a05f-48ed-abaf-a91075be5307" width="47%" height="20%" align="left">
<img src="https://github.com/user-attachments/assets/c36b0f17-6a0a-41f1-8568-a2679dcc6292" width="47%" height="20%"align="right">

---

<br>
<br>

## 프로젝트 후기
> 🍊 신종민 : 안녕하세요, 이렇게 큰 상을 받게 되어 매우 기쁩니다. 이번 프로젝트를 통해 AI 기술을 산업재해와 같은 실질적인 문제에 적용해 볼 수 있어 매우 의미 있는 경험이었습니다. 특히 고용노동부와 보건복지부 데이터를 활용하여 AI 모델을 개발하고, 사용자들에게 유용한 정보를 제공할 수 있었던 점이 자랑스럽습니다. 앞으로도 다양한 공공데이터를 활용한 AI 솔루션을 개발하여 사회에 기여하고 싶습니다. 감사합니다.
> 

> 👻 김세훈 : 안녕하세요, 백엔드 개발을 담당한 김세훈입니다. 수상하게 되어 정말 영광입니다. 이번 프로젝트에서는 다양한 공공데이터 API를 활용하여 사용자 요청에 대한 빠르고 정확한 응답을 제공하는 백엔드 시스템을 구축하는 데 집중했습니다. 특히 데이터의 실시간 처리를 최적화하고, 신뢰성 있는 정보를 사용자에게 제공할 수 있도록 노력했습니다. 앞으로도 더 나은 서비스를 제공할 수 있도록 최선을 다하겠습니다. 감사합니다.
> 

> 😎 한성익 : 안녕하세요, 프론트엔드 개발을 맡은 한성익입니다. 이 프로젝트를 통해 사용자 경험을 극대화할 수 있는 인터페이스를 개발할 수 있어서 기뻤습니다. 사용자가 쉽게 정보를 입력하고 결과를 확인할 수 있도록 직관적이고 반응형 디자인을 구현하는 데 중점을 두었습니다. 사용자 피드백을 반영하여 더 많은 개선을 이루고 싶습니다. 이번 수상이 큰 동기부여가 되었고, 앞으로도 더 나은 사용자 경험을 제공하기 위해 노력하겠습니다. 감사합니다.
>

> 🐬 이은솔 : 안녕하세요, 디자이너 이은솔입니다. 수상하게 되어 정말 기쁘고 감사드립니다. 프로젝트의 디자인을 통해 복잡한 정보를 사용자에게 쉽게 전달하고, 서비스의 사용성을 높이기 위해 많은 고민을 했습니다. 데이터 시각화와 사용자 인터페이스 디자인을 통해 사용자가 직관적으로 서비스를 이용할 수 있도록 노력했습니다. 앞으로도 더욱 창의적이고 사용자 중심적인 디자인을 통해 좋은 서비스를 제공할 수 있도록 하겠습니다. 감사합니다.
> 
