# AI기반 건강 진단 및 산재 보험 예측 서비스 HOUP
![%EC%8A%AC%EB%9D%BC%EC%9D%B4%EB%93%9C1 1](https://github.com/user-attachments/assets/867a5abf-d25e-43ef-8e12-7d0cce59d7aa)
## 프로젝트 소개 영상
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
3. **예방과 대비를 위한 매뉴얼 제공**: 로딩 화면을 활용해 사용자에게 산업재해 예방 및 대비를 위한 매뉴얼을 제공, 교육적 가치를 더합니다.

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
![image 1](https://github.com/user-attachments/assets/d799f0d3-2694-4d85-a9e2-48fe869218de)

## 개발 기간
* `2024-06-26 ~ 2024-08-12`

## 페이지별 기능
## 프로젝트 후기
> 🍊 신종민 : 안녕하세요, 이렇게 큰 상을 받게 되어 매우 기쁩니다. 이번 프로젝트를 통해 AI 기술을 산업재해와 같은 실질적인 문제에 적용해 볼 수 있어 매우 의미 있는 경험이었습니다. 특히 고용노동부와 보건복지부 데이터를 활용하여 AI 모델을 개발하고, 사용자들에게 유용한 정보를 제공할 수 있었던 점이 자랑스럽습니다. 앞으로도 다양한 공공데이터를 활용한 AI 솔루션을 개발하여 사회에 기여하고 싶습니다. 감사합니다.
> 

> 👻 김세훈 : 안녕하세요, 백엔드 개발을 담당한 김세훈입니다. 수상하게 되어 정말 영광입니다. 이번 프로젝트에서는 다양한 공공데이터 API를 활용하여 사용자 요청에 대한 빠르고 정확한 응답을 제공하는 백엔드 시스템을 구축하는 데 집중했습니다. 특히 데이터의 실시간 처리를 최적화하고, 신뢰성 있는 정보를 사용자에게 제공할 수 있도록 노력했습니다. 앞으로도 더 나은 서비스를 제공할 수 있도록 최선을 다하겠습니다. 감사합니다.
> 

> 😎 한성익 : 안녕하세요, 프론트엔드 개발을 맡은 한성익입니다. 이 프로젝트를 통해 사용자 경험을 극대화할 수 있는 인터페이스를 개발할 수 있어서 기뻤습니다. 사용자가 쉽게 정보를 입력하고 결과를 확인할 수 있도록 직관적이고 반응형 디자인을 구현하는 데 중점을 두었습니다. 사용자 피드백을 반영하여 더 많은 개선을 이루고 싶습니다. 이번 수상이 큰 동기부여가 되었고, 앞으로도 더 나은 사용자 경험을 제공하기 위해 노력하겠습니다. 감사합니다.
>

> 🐬 이은솔 : 안녕하세요, 디자이너 이은솔입니다. 수상하게 되어 정말 기쁘고 감사드립니다. 프로젝트의 디자인을 통해 복잡한 정보를 사용자에게 쉽게 전달하고, 서비스의 사용성을 높이기 위해 많은 고민을 했습니다. 데이터 시각화와 사용자 인터페이스 디자인을 통해 사용자가 직관적으로 서비스를 이용할 수 있도록 노력했습니다. 앞으로도 더욱 창의적이고 사용자 중심적인 디자인을 통해 좋은 서비스를 제공할 수 있도록 하겠습니다. 감사합니다.
> 
