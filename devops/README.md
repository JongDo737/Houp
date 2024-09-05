## 파이썬 패키지 설치 및 Layer 설정 (CentOS)
### Openai
```python
sudo yum update -y
sudo yum install python

# pip 설치
curl -O https://bootstrap.pypa.io/get-pip.py
python3 get-pip.py --user

python --version
pip --version

# --------------------------- openai 패키지 설치 ---------------------------
mkdir python
pip3 install openai -t ./python

# zip 파일 생성
zip -r9 openai_layer.zip ./python

# aws configure
aws configure

# 업로드
aws lambda publish-layer-version \
     --layer-name openai-python-layer \
     --description "Layer containing OpenAI Python package" \
     --zip-file fileb://openai_layer.zip \
     --compatible-runtimes python3.9

```
### Pandas
AWS Lambda에서 Pandas 라이브러리를 사용하기 위해 **AWS Lambda Managed Layers**를 활용할 수 있습니다. `AWSSDKPandas-Python39`는 AWS에서 관리하는 Layer로, 이 Layer를 Lambda 함수에 추가하면 별도로 패키지를 설치할 필요가 없습니다.

### 참고 자료:
- [AWS Lambda Managed Layers for Pandas](https://aws-sdk-pandas.readthedocs.io/en/stable/layers.html)

## Lambda -> S3 
/devops/data 폴더 내부 데이터들은 S3에 넣고 사용하였습니다. Lambda함수의 Role에 AmazonS3ReadOnlyAccess 를 추가하였습니다.
```
arn:aws:iam::aws:policy/AmazonS3ReadOnlyAccess
```