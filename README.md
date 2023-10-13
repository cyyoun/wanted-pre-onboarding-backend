# wanted-pre-onboarding-backend

</br>

## 1. 요구사항 분석
1. 회사는 채용 공고를 **등록**한다.
(속성 : 회사 아이디, 채용 포지션, 채용 보상금, 채용내용, 사용 기술)
2. 회사는 채용 공고를 **수정**할 수 있다.
(채용 포지션, 채용 보상금, 채용내용, 사용 기술)
3. 회사는 채용공고를 **삭제**할 수 있다.
4. 사용자는 채용공고 **목록**을 확인할 수 있다.
5. 채용공고 **검색** 기능 구현(선택)
6. **상세 페이지**를 가져올 수 있다. (채용 내용이 추가적으로 있음)
7. 회사가 올린 **다른 채용공고 리스트**가 포함되어 있음 (선택)
8. 사용자는 채용공고에 **지원**할 수 있습니다 (선택)

```
💡 개체 : 회사, 사용자, 채용공고, 지원 내역(포함)
   기능 : 채용 공고 CRUD, 채용공고 지원, 공고 검색 
```
</br>

## 2. 데이터 모델링 설계
#### 1) 개념데이터 모델링
```
1. 회사 vs 채용공고 (단방향 →)
각 회사는 여러 채용공고를 작성할 수 있다 (o)
각 채용공고는 여러 회사로부터 작성될 수 없다 (x) 

2. 채용공고 vs 사용자 (양방향 ↔)
각 사용자는 여러 채용공고에 지원할 수 있다. (o)
각 채용공고는 여러 사용자로부터 지원될 수 있다. (o)
```
![ERD 설계](https://github.com/cyyoun/wanted-pre-onboarding-backend/assets/88883052/da6a45f9-2abd-4404-95b9-7b008683d1b0)


#### 2) 논리 및 물리데이터 모델링
![논리 및 물리 모델링](https://github.com/cyyoun/wanted-pre-onboarding-backend/assets/88883052/07e19af1-57d7-4ba7-ad3b-81afc72bd2ed)

</br>

## 3. API 설계
|Function|URI|HTTP API|
|---|---|---|
|채용공고 등록|/recruitment/add|POST|
|채용공고 수정|/recruitment/{id}|PATCH|
|채용공고 삭제|/recruitment/{id}|DELETE|
|채용공고 목록|/recruitment/list|GET|
|채용공고 상세페이지 </br> 회사 다른 채용 공고 리스트|/recruitment/{id}|GET|
|채용공고 목록 검색|/recruitment?search={keyword}|GET|
|채용공고 지원|/apply|POST|

</br>

## 4. 컨벤션 설계
#### 1) Git 컨벤션
- 제목은 50자, 본문은 72자까지
- 태그명과 Init 메시지 외에 한국어만 사용
- 태그 첫 글자는 대문자 표기
- 제목은 `태그명: 제목` 의 띄어쓰기 형태로 작성

|태그명|설명|
|---|---|
|Feat|새로운 기능 추가|
|Fix|버그 고친 경우|
|Test|테스트 추가 및 테스트 리팩토링|
|Simplify|코드를 단순화 하는 경우|
|Rename|폴더명, 파일명, 변수명, 함수명 등을 변경만 한 경우|
|Remove|삭제 작업만 한 경우|
|Comment|주석 관련 작업만 한 경우|
|Refactor|코드 리팩토링|
|Design|UI 관련 작업만 한 경우|
|Docs|README.md 관련 작업의 경우|

#### 2) 기타 개발 규칙
- 네이밍 </br>
   클래스 : UpperCamelCase </br>
   메서드, 변수 : lowerCamelCase </br>
   상수 : 대문자, 언더스코어 

- 제어 구조 </br>
   if, for, while : 항상 중괄호로 둘러싸야 함 

- 주석 </br>
   문장으로 작성하며 `//` 또는 `/** */` 스타일 사용
</br>

## 5. 구현 과정
#### 1) 시스템 환경
- Java 11
- Spring Boot Framework
- MySQL
- JPA
#### 2) 프로젝트 설정
- Git 저장소 복제
- `build.gradle` 라이브러리 설정
- `application.yml` 수정하여 데이터베이스 연결 
#### 3) 기능 구현
1. **채용공고 등록**: 회사가 새 채용공고를 등록
2. **채용공고 수정**: 회사가 새 채용공고를 수정
3. **채용공고 삭제**: 회사가 새 채용공고를 삭제
4. **채용공고 리스트**: 모든 채용공고를 볼 수 있는 목록 페이지
5. **채용공고 상세정보**: 특정 채용공고의 상세 정보
6. **채용공고 검색**: 키워드를 통해 채용공고를 검색
7. **회사의 다른 채용공고 리스트**: 채용공고 상세정보에 해당 회사의 다른 채용공고 확인
</br>

1. 채용공고 등록
```java
//RecruitmentController.java
@PostMapping("/add")
public RecruitmentDto add(@RequestBody Recruitment recruitment) {
    recruitmentService.save(recruitment);
    Recruitment getRecruitment = recruitmentService.findRecruitment(recruitment.getId());
    return modelMapper.map(getRecruitment, RecruitmentDto.class);
}
```
```json
//response
{
    "companyId": 2,
    "position": "백엔드 개발자",
    "compensation": "3500만원",
    "content": "저희 우아한 현제들과 함께 하실 ERP 서버 개발자 구합니다!",
    "skill": "Java, Spring boot, DBMS, JPA, Spring Security",
    "recruitmentList": []
}
```

</br>
2. 채용공고 수정

```java
//RecruitmentController.java
@PatchMapping("/{recruitmentId}")
public RecruitmentDto edit(@PathVariable long recruitmentId, @RequestBody Recruitment recruitment) {
    Recruitment getRecruitment = recruitmentService.editRecruitment(recruitment, recruitmentId);
    return modelMapper.map(getRecruitment, RecruitmentDto.class);
}
```
```json
//response
{
    "companyId": 2,
    "position": "프론트엔드 개발자", #변경
    "compensation": "3600만원", #변경
    "content": "저희 우아한 현제들과 함께 하실 프론트엔드 개발자 구합니다! ^^",
    "skill": "HTML, CSS, Javascript", #변경
    "recruitmentList": []
}
```
</br>
3. 채용공고 삭제

```java
//RecruitmentController.java
   @DeleteMapping("/{recruitmentId}")
    public String delete(@PathVariable Long recruitmentId) {
        recruitmentService.delRecruitment(recruitmentId);
        return "공고가 삭제되었습니다. 🙂";
    }
```
```json
//recruitment/1
//response
공고가 삭제되었습니다. 🙂
```

</br>
4. 채용공고 리스트

```java
//RecruitmentController.java
@GetMapping("/list")
public List<RecruitmentListDto> list() {
    List<Recruitment> recruitmentList = recruitmentService.findRecruitmentList();

    return recruitmentList.stream()
            .map(recruitment -> modelMapper.map(recruitment, RecruitmentListDto.class))
            .collect(Collectors.toList());
}
```
```json
//response
[
    {
        "companyId": 2,
        "position": "프론트엔드 개발자",
        "compensation": "3600만원",
        "skill": "HTML, CSS, Javascript"
    },
    {
        "companyId": 2,
        "position": "백엔드 개발자",
        "compensation": "3500만원",
        "skill": "Java, Spring boot, DBMS, JPA, Spring Security"
    },
    {
        "companyId": 3,
        "position": "백엔드 개발자, 프론트엔드 개발자, 데이터분석가",
        "compensation": "협의",
        "skill": "홈페이지 상세확인"
    },
    {
        "companyId": 3,
        "position": "백엔드 개발자, 프론트엔드 개발자, 데이터분석가, UI/UX 디자이너",
        "compensation": "홈페이지 상세확인",
        "skill": "홈페이지 상세확인"
    },
    {
        "companyId": 3,
        "position": "회계, 재무 담당자",
        "compensation": "홈페이지 상세확인",
        "skill": "홈페이지 상세확인"
    }
]
```
</br>
5. 채용공고 상세정보, 회사의 다른 채용공고 리스트

```java
//RecruitmentController.java
 @GetMapping("/{recruitmentId}")
public RecruitmentDto detail(@PathVariable Long recruitmentId) {
    Recruitment getRecruitment = recruitmentService.findRecruitment(recruitmentId);
    RecruitmentDto dto = modelMapper.map(getRecruitment, RecruitmentDto.class);

    //다른 채용 공고
    dto.setRecruitmentList(recruitmentService.findRecruitmentIdList(getRecruitment));
    return dto;
}
```
```json
//recruitment/4
//response
{
    "companyId": 3,
    "position": "백엔드 개발자, 프론트엔드 개발자, 데이터분석가",
    "compensation": "협의",
    "content": "저희와 함께 하실 개발자 급구 !!",
    "skill": "홈페이지 상세확인",
    "recruitmentList": [
        5,
        6
    ]
}
```

6. 채용공고 검색
```java
//RecruitmentController.java
@GetMapping
public List<RecruitmentDto> search(@RequestParam("search") String search) {
    List<Recruitment> recruitments = recruitmentService.searchRecruitment(search);
    return recruitments.stream()
            .map(recruitment -> modelMapper.map(recruitment, RecruitmentDto.class))
            .collect(Collectors.toList());
}
```
```json
//?search=백엔드
//response
[
    {
        "companyId": 2,
        "position": "백엔드 개발자",
        "compensation": "3500만원",
        "content": "저희 우아한 현제들과 함께 하실 ERP 서버 개발자 구합니다!",
        "skill": "Java, Spring boot, DBMS, JPA, Spring Security",
        "recruitmentList": []
    },
    {
        "companyId": 3,
        "position": "백엔드 개발자, 프론트엔드 개발자, 데이터분석가",
        "compensation": "협의",
        "content": "저희와 함께 하실 개발자 급구 !!",
        "skill": "홈페이지 상세확인",
        "recruitmentList": []
    },
    {
        "companyId": 3,
        "position": "백엔드 개발자, 프론트엔드 개발자, 데이터분석가, UI/UX 디자이너",
        "compensation": "홈페이지 상세확인",
        "content": "디자이너, 데이터베이서 운영 담당자 급구!",
        "skill": "홈페이지 상세확인",
        "recruitmentList": []
    }
]
```



