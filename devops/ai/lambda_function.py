# 의사 AI 호출 Lambda

import json
import os
from openai import OpenAI
import pandas as pd
import boto3

client = OpenAI(
    api_key=os.environ['OPENAI_API_KEY'],  # this is also the default, it can be omitted
)

def lambda_handler(event, context):
    
    age = event['ageGroup'].split('대')[0]
    gender = event['gender']
    job = event['job']
    ageGroup = event['ageGroup']
    painArea = event['painArea']
    painDescription = event['painDescription']

    s3 = boto3.client('s3', region_name='ap-northeast-2')
    
    # S3 버킷 이름과 파일 키 설정
    bucket_name = 'team-cis-dabindo-24'  # 여기에 실제 S3 버킷 이름을 넣으세요
    
    inpatient_file_key = f"dabindo{age}_2023_in.xlsx"
    outpatient_file_key = f"dabindo{age}_2023_out.xlsx"
    
    try:
        # S3에서 입원 파일 가져오기
        inpatient_obj = s3.get_object(Bucket=bucket_name, Key=inpatient_file_key)
        inpatient_df = pd.read_excel(inpatient_obj['Body'].read(), header=3)
        
        # S3에서 외래 파일 가져오기
        outpatient_obj = s3.get_object(Bucket=bucket_name, Key=outpatient_file_key)
        outpatient_df = pd.read_excel(outpatient_obj['Body'].read(), header=3)
        
    except Exception as e:
        raise RuntimeError(f"엑셀 파일을 읽는 도중 에러가 발생했습니다: {e}")

    # 3단질병명 전체 추출
    inpatient_diseases = inpatient_df['3단질병명'].unique() #입원
    outpatient_diseases = outpatient_df['3단질병명'].unique() #외래
    
    # Construct the prompt
    prompt = f"""
    당신은 대한민국 전문 의사입니다. 다음과 같은 정보를 가진 환자가 왔습니다. 스스로 병을 진단하고, 아래 제공된 3개의 카테고리에서 각각 1개씩 선택해주시면 됩니다.:

    - 성별: {gender}
    - 연령대: {ageGroup}
    - 유저가 입력한 직업 텍스트 : {job}
    - 아픈 부위: {painArea}
    - 아픈 부위 설명: {painDescription}

    다음 정보를 JSON 형식으로 제공해주세요:
		1. 유력 병명 1가지
		2. 특징과 통증에 대한 의사의 견해, 직업과 질병의 연관성이 있다면 함께 설명해줘 (육하원칙에 따라 최소 300~500자 정도 작성해주세요)
		3. 입원 진단 카테고리 (무조건 아래 입원진단 카테고리에서 선택)
        4. 외래 진단 카테고리 (무조건 아래 외래진단 카테고리에서 선택)
        4. 직업 카테고리 (위에서 제공한 직업이 아닌 무조건 아래 직업 카테고리에서 선택) 시발 병신이냐 이거 말해줘도 모르냐 ? 아래 직업 카테고리에서 고르라고
        5. 질병 유형 (무조건 아래 질병 유형 목록에서 선택)
        진단 카테고리와 질병유형은 각각의 목록에서 따로 추출해줘

    1.  - 입원 진단 카테고리:
       {inpatient_diseases}
        - 외래 진단 카테고리:
        {outpatient_diseases}
    ,
    2. 직업 카테고리:
       - (건설기계)콘크리트믹서트럭기사
       - (특수직종)[생보||(농협2012.03.02폐지)]보험설계사
       - (특수직종)[손보||대리점||우체국]보험설계사
       - (특수직종)가전제품설치원
       - (특수직종)골프장캐디
       - (특수직종)대리운전기사
       - (특수직종)방문점검원
       - (특수직종)콘크리트믹서트럭운전자
       - (특수직종)퀵서비스기사
       - (특수직종)택배기사
       - (특수직종)학습지교사
       - (후원)방문판매원
       - [대출모집법인]대출모집인
       - [생보||(농협2012.03.02폐지)]보험설계사
       - [손보||대리점||우체국]보험설계사
       - 가사 및 육아 도우미
       - 가전제품 설치원
       - 간호사
       - 건설 및 광업 단순 종사원
       - 건설 및 채굴 기계운전원
       - 건설.전기 및 생산 관련 관리자
       - 건설?전기 및 생산 관련 관리자
       - 건설·전기 및 생산 관련 관리자
       - 건설관련 기능 종사자
       - 건설구조관련 기능 종사자
       - 건축 목공
       - 건축 및 토목 공학 기술자및 시험원
       - 건축마감관련 기능 종사자
       - 경비원 및 검표원
       - 경영관련 사무원
       - 경찰.소방 및 교도 관련 종사자
       - 경찰·소방 및 교도 관련 종사자
       - 경호 및 보안 관련 종사자
       - 계기검침.수금 및주차 관련 종사원
       - 계기검침?수금 및주차 관련 종사원
       - 계기검침·수금 및주차 관련 종사원
       - 고객 상담 및 기타 사무원
       - 고객서비스 관리자
       - 골프장캐디
       - 공예 및 귀금속 세공원
       - 구매대리인
       - 굴삭기기사
       - 금속.재료 공학 기술자및 시험원
       - 금속·재료 공학 기술자및 시험원
       - 금속공작기계 조작원
       - 금속기계부품 조립원
       - 금융 및 보험 관련 사무 종사자
       - 금융 및 보험 전문가
       - 금형.주조 및 단조원
       - 금형?주조 및 단조원
       - 금형·주조 및 단조원
       - 기계장비 설치 및 정비원
       - 기술영업 및 중개 관련종사자
       - 기업고위임원
       - 기타 건물 청소원
       - 기타 건설.전기 및 생산 관련 관리자
       - 기타 건설?전기 및 생산 관련 관리자
       - 기타 건설·전기 및 생산 관련 관리자
       - 기타 공학 전문가 및 관련종사자
       - 기타 교육 전문가
       - 기타 기능관련 종사자
       - 기타 서비스관련 단순 종사원
       - 기타 수위|| 경비 및 관련 종사원
       - 기타 식품가공관련 기계조작원
       - 기타 이미용.예식 및 의료보조 서비스 종사자
       - 기타 이미용?예식 및 의료보조 서비스 종사자
       - 기타 이미용·예식 및 의료보조 서비스 종사자
       - 기타 전문서비스 관리자
       - 기타 제조관련 기계조작원
       - 기타 판매 및 고객 서비스 관리자
       - 냉.난방 관련 설비 조작원
       - 냉?난방 관련 설비 조작원
       - 냉·난방 관련 설비 조작원
       - 농림어업관련 단순 종사원
       - 대리운전기사
       - 대학 교수 및 강사
       - 덤프트럭기사
       - 도장 및 도금기 조작원
       - 도장원|| 건설 도장원 제외
       - 디자이너
       - 매니저 및 기타 문화.예술관련 종사자
       - 매니저 및 기타 문화?예술관련 종사자
       - 매니저 및 기타 문화·예술관련 종사자
       - 매장 판매 종사자
       - 목재 및 종이 관련 기계조작원
       - 목재.가구.악기 및 간판 관련 기능 종사자
       - 목재?가구?악기 및 간판 관련 기능 종사자
       - 목재·가구·악기 및 간판 관련 기능 종사자
       - 문리.기술 및 예능 강사
       - 문리?기술 및 예능 강사
       - 문리·기술 및 예능 강사
       - 문화.예술.디자인 및 영상 관련 관리자
       - 문화·예술·디자인 및 영상 관련 관리자
       - 물품이동 장비 조작원
       - 발전 및 배전 장치 조작원
       - 방문.노점 및 통신 판매 관련 종사자
       - 방문?노점 및 통신 판매 관련 종사자
       - 방문·노점 및 통신 판매 관련 종사자
       - 방문강사
       - 방문점검원
       - 배관공
       - 배달원
       - 법률 및 감사 사무 종사자
       - 법률 전문가
       - 보건 및 사회복지 관련 관리자
       - 보건의료관련 종사자
       - 보험 및 금융 관리자
       - 비금속 제품 생산기 조작원
       - 비서 및 사무 보조원
       - 사회복지관련 종사자
       - 상.하수도 처리장치 조작원
       - 상·하수도 처리장치 조작원
       - 상품 대여 종사자
       - 상품기획.홍보 및 조사 전문가
       - 상품기획·홍보 및 조사 전문가
       - 생명 및 자연 과학 관련 시험원
       - 생명 및 자연 과학 관련전문가
       - 생산사무 종사자
       - 석유 및 화학물 가공장치 조작원
       - 선박 갑판승무원 및관련 종사원
       - 섬유 및 가죽 관련 기능 종사자
       - 섬유제조 및 가공기계조작원
       - 세탁관련 기계조작원
       - 스포츠 및 레크레이션 관련 전문가
       - 식품가공관련 기계조작원
       - 식품가공관련 기능 종사자
       - 아스팔트살포기기사
       - 안전관리 및 검사원
       - 약사 및 한약사
       - 어업관련 종사자
       - 여가 및 스포츠 관련 종사자
       - 여행.안내 및 접수 사무원
       - 여행?안내 및 접수 사무원
       - 여행·안내 및 접수 사무원
       - 연구.교육 및 법률 관련 관리자
       - 연구?교육 및 법률 관련 관리자
       - 연구·교육 및 법률 관련 관리자
       - 연극.영화 및 영상 전문가
       - 연극?영화 및 영상 전문가
       - 연극·영화 및 영상 전문가
       - 영상 및 통신 장비 관련 설치 및 수리원
       - 영양사
       - 영업종사자
       - 용접원
       - 운송 서비스 종사자
       - 운송장비 정비원
       - 운송차량 및 기계 관련 조립원
       - 원예 및 조경 종사자
       - 유치원 교사
       - 음료 제조관련 기계조작원
       - 음식관련 단순 종사원
       - 음식서비스 종사자
       - 의료.복지 관련 서비스종사자
       - 의료?복지 관련 서비스종사자
       - 의료·복지 관련 서비스종사자
       - 의료진료 전문가
       - 의복 제조관련 기능 종사자
       - 의회의원·고위공무원 및 공공단체임원
       - 이.미용 및 관련 서비스 종사자
       - 이?미용 및 관련 서비스 종사자
       - 이·미용 및 관련 서비스 종사자
       - 인문 및 사회 과학 전문가
       - 인사 및 경영 전문가
       - 인쇄 및 사진현상 관련 기계조작원
       - 임업관련 종사자
       - 자동조립라인 및 산업용 로봇 조작원
       - 자동차 운전원
       - 자동차 정비원
       - 작가.기자 및 출판 전문가
       - 작가?기자 및 출판 전문가
       - 작가·기자 및 출판 전문가
       - 작물재배 종사자
       - 장기 부사관 및 준위
       - 재활용 처리 및 소각로 조작원
       - 전기 및 전자 설비 조작원
       - 전기 및 전자기기 설치 및 수리원
       - 전기 및 전자장비 조립 종사자
       - 전기.전자 및 기계 공학 기술자 및 시험원
       - 전기.전자 부품 및 제품 제조장치 조작원
       - 전기.전자 부품 및 제품 조립원
       - 전기?전자 및 기계 공학 기술자 및 시험원
       - 전기?전자 부품 및 제품 제조장치 조작원
       - 전기?전자 부품 및 제품 조립원
       - 전기·전자 및 기계 공학 기술자 및 시험원
       - 전기·전자 부품 및 제품 제조장치 조작원
       - 전기·전자 부품 및 제품 조립원
       - 전기공
       - 정보 시스템 운영자
       - 정보시스템 개발 전문가
       - 정보통신관련 관리자
       - 제관원 및 판금원
       - 제조관련 단순 종사원
       - 제품 운반원
       - 종교관련 종사자
       - 주방장 및 조리사
       - 주조 및 금속 가공관련 기계조작원
       - 지게차기사
       - 직물 및 신발 관련 기계조작원 및 조립원
       - 채굴 및 토목 관련 기능 종사자
       - 천공기기사
       - 철도 및 전동차 기관사
       - 청소원 및 환경 미화원
       - 축산 및 사육 관련 종사자
       - 치료사 및 의료기사
       - 컴퓨터 하드웨어 및통신공학 전문가
       - 콘크리트믹서트럭기사
       - 콘크리트믹서트럭운전자
       - 퀵서비스기사
       - 큐레이터.사서 및 기록물관리사
       - 큐레이터·사서 및 기록물관리사
       - 택배기사
       - 통계관련 사무원
       - 통신 및 방송송출 장비 기사
       - 특수건설기계기사
       - 판매 및 운송 관리자
       - 판매관련 단순 종사원
       - 품질 검사원
       - 하역 및 적재 단순 종사원
       - 학교 교사
       - 학습지교사
       - 항공기.선박 기관사 및 관제사
       - 항공기?선박 기관사 및 관제사
       - 항공기·선박 기관사 및 관제사
       - 행정 및 경영지원 관리자
       - 행정 사무원
       - 행정 전문가
       - 혼례 및 장례 종사자
       - 화가.사진가 및 공연예술가
       - 화가?사진가 및 공연예술가
       - 화가·사진가 및 공연예술가
       - 화물열차 차장 및 관련 종사원
       - 화물차주(수출입컨테이너운송)
       - 화물차주(위험물질운송)
       - 화물차주(철강재운송)
       - 화학.고무 및 플라스틱 제품 생산기 조작원
       - 화학?고무 및 플라스틱 제품 생산기 조작원
       - 화학·고무 및 플라스틱 제품 생산기 조작원
       - 화학공학 기술자 및 시험원
       - 환경.청소 및 경비 관련 관리자
       - 환경?청소 및 경비 관련 관리자
       - 환경·청소 및 경비 관련 관리자
       - 환경공학 기술자 및 시험원
       - 회계 및 경리 사무원
    ,
    3. 질병 유형:
       - 근골격계질환 (척추질환)
       - 근골격계질환(척추질환 제외)
       - 기타 간질환
       - 난청
       - 뇌혈관질환
       - 독성감염
       - 사인미상
       - 석면폐증
       - 심장질환
       - 악성신생물(직업성 암 포함)
       - 안면신경 마비
       - 안질환
       - 이상기압으로 인한 질병(압착증|| 감압병 등)
       - 일사병,열사병,화상,동상
       - 일사병||열사병||화상||동상
       - 자해행위(자살 포함)
       - 정신질환
       - 진동으로 인한 증상
       - 진폐
       - 피부질환
       - 호흡기질환(천식 포함)   

    다음 JSON 형식으로 응답을 반환해주세요:

    {{
        "diseaseDetails": 
            {{
                "name": "최고의 의사 GPT가 생각한 질병 명",
                "inpatient_category": "질병에 해당하는 입원 진단 카테고리",
                "outpatient_category": "질병에 해당하는 외래 진단 카테고리",
                "description": "의사의 견해가 들어가는 부분 질병의 특징과 통증 설명 후 직업과의 연관성 설명, 이 질병이 유력한 합리적인 이유."
            }}
        "jobKind": "직업 카테고리",
        "diseaseKind": "질병 유형"
    }}
    """
    
    # Call the OpenAI API with the prompt
    response = client.chat.completions.create(
        #model="gpt-4-turbo",
        model="gpt-4o-mini-2024-07-18",
        # model="ft:gpt-3.5-turbo-0125:personal:cis:9iLo62b4", # fine-tuning 모델
        response_format={ "type": "json_object" },
        messages=[
            {"role": "system", "content": "당신은 정말 유능한 한국 의사입니다."},
            {"role": "user", "content": prompt}
        ]
        # max_tokens=1024,
        # n=1,
        # stop=None,
        # temperature=0.7
    )
    # Extract the generated JSON response
    response_text = response.choices[0].message.content
    response_json = json.loads(response_text)
    # 여기서 부터 그래프 데이터 추출
    inpatient_category = response_json['diseaseDetails']['inpatient_category']
    outpatient_category = response_json['diseaseDetails']['outpatient_category']

    inpatient_data = inpatient_df[inpatient_df['3단질병명'] == inpatient_category]
    outpatient_data = outpatient_df[outpatient_df['3단질병명'] == outpatient_category]
    

    # 입원과 외래 중 높은 숫자를 rank로 사용
    if not inpatient_data.empty:
        inpatient_rank = inpatient_data.iloc[0]['순위']
    else:
        inpatient_rank = float('inf')

    if not outpatient_data.empty:
        outpatient_rank = outpatient_data.iloc[0]['순위']
    else:
        outpatient_rank = float('inf')

    disease_details = inpatient_data.iloc[0]
    disease_details = outpatient_data.iloc[0]

    if inpatient_rank < outpatient_rank:
        rank = inpatient_rank
        rank_data = inpatient_df.head(3)[['3단질병명', '환자수', '순위']].reset_index(drop=True)

        disease_details = inpatient_data.iloc[0]
        disease_patients = disease_details['환자수']
    else:
        rank = outpatient_rank
        rank_data = outpatient_df.head(3)[['3단질병명', '환자수', '순위']].reset_index(drop=True)

        disease_details = outpatient_data.iloc[0]
        disease_patients = disease_details['환자수']
        print('아래   ',disease_details['보험자부담금'] ,':',disease_details['청구건수'])
    disease_details = outpatient_data.iloc[0]
    disease_cost = disease_details['보험자부담금'] / disease_details['청구건수']

    # 연도별 입원 및 외래 환자 수 추출
    hospitalization_trend = {
        "2021": int(inpatient_data.iloc[0]['환자수.2']),
        "2022": int(inpatient_data.iloc[0]['환자수.1']),
        "2023": int(inpatient_data.iloc[0]['환자수'])
    }
    outpatient_trend = {
        "2021": int(outpatient_data.iloc[0]['환자수.2']),
        "2022": int(outpatient_data.iloc[0]['환자수.1']),
        "2023": int(outpatient_data.iloc[0]['환자수'])
    }
    # rank_data에서 1, 2, 3위에 해당하는 질병 정보 추출
    rankings = {}
    for i, (index, row) in enumerate(rank_data.iterrows()):
        rankings[f"place{i + 1}"] = {
            "name": row['3단질병명'],
            "patients": int(row['환자수'])
        }
    # 최종 JSON 결과 생성
    result_json = {
        "diseaseDetails": {
            "name": response_json['diseaseDetails']['name'],
            "category": response_json['diseaseDetails']['inpatient_category'],  # Assuming the category is the same for both inpatient and outpatient
            "description": response_json['diseaseDetails']['description'],
            "rank": int(rank),
            "patients": int(disease_patients),
            "medicalCost": int(disease_cost * 1000) # 1,000원 단위
        },
        "rankings": rankings,
        "hospitalization": {
            "rate": round(float(hospitalization_trend["2023"] / int(inpatient_df["환자수"].sum()))/(float(hospitalization_trend["2023"] / int(inpatient_df["환자수"].sum()))+float(outpatient_trend["2023"] / int(outpatient_df["환자수"].sum()))) * 100, 2),
            "trend": {year: int(value) for year, value in hospitalization_trend.items()},
            "averageDays": round(float(inpatient_data.iloc[0]['내원일수'] / inpatient_data.iloc[0]['청구건수']), 2) if not inpatient_data.empty else 0
        },
        "outpatient": {
            "rate": round(float(outpatient_trend["2023"] / int(outpatient_df["환자수"].sum()))/(float(hospitalization_trend["2023"] / int(inpatient_df["환자수"].sum()))+float(outpatient_trend["2023"] / int(outpatient_df["환자수"].sum()))) * 100, 2),
            "trend": {year: int(value) for year, value in outpatient_trend.items()},
            "averageDays": round(float(outpatient_data.iloc[0]['내원일수'] / outpatient_data.iloc[0]['청구건수']), 2) if not outpatient_data.empty else 0
        },
        "jobKind": response_json['jobKind'],
        "diseaseKind": response_json['diseaseKind']
    }

    return result_json