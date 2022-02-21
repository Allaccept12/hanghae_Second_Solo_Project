# hanghae_Second_Solo_Project
항해 두 번째 솔로 프로젝트

매거진 사이트 만들기
 

## 기술 스택
**`Back-end`**
- Java 8

- SpringBoot
- Spring Security 
- Gradle
- JPA
- QueryDSL - 안써도 되지만 연습
- MySQL 8.0

## CORS 해결하기
    - CORS란 무엇인가 ? 교차 출처 리소스 공유(Cross-Origin Resource Sharing, CORS)는 추가 HTTP 헤더를 사용하여, 한 출처에서 실행 중인 웹 애플리케이션이 다른 출처의 선택한 자원에 접근할 수 있는 권한을 부여하도록 브라우저에 알려주는 체제입니다.다른 출처의 자원을 공유할 수 있도록 설정하는 권한 체제 즉, 프론트와 백엔드가 합쳐지지 모담.
    - 어떤 상황에서 일어나는지 - Preflight 요청에서 적절한 Access-Control을 확인하지 못하면 발생. 즉, 허용 받지 못한 요청은 안됨
    - 어떻게 해결하는지 알아보고 
       1, CorsFilter 로 직접 response에 header 를 넣어주기
       2, Controller 에서 @CrossOrigin 어노테이션 추가하기
       3, WebMvcConfigurer 를 이용해서 처리하기
    - 프로젝트에 적용하기

## N+1 문제 해결하기
    - N+1 문제란 무엇인가 ? 부모 엔티티에서 전체를 조회할때 부모자신 엔티티 조회 (1) + 자식엔티티 (n) 조회 한다고해서 n+1 만큼 조회커리가 나간다고 해서 붙여진 이름
    - 해결방안 ? 1, fetchJoin으로 한번에 가져오고싶은 데이터를 한 쿼리에가져오기 - 문제점은 데이터 뻥튀기,불필요한 필드값 데이터까지 가져옴, 페이징 불가능, 두개의 fetchJoin 불가
                2, 일대다 양방향 안쓰고 단방향으로 하기 - 문제점 코드가 길어질 수 있음(아닐수도..나는 길어지던데 잘모르겠음), 양방향은 객체그래프로 쉽게 가져오기가 가능하지만 단방향은 join을 쓰던지 필요한 값을 구하기전에 필요한 데이터를 조회후에 조회한 것을 바탕으로 조회하는 불편함
                3, NamedEntityGraphs - 안써봐서 잘모르겠음               
                4, **Hibernate default_batch_fetch_size - 해당 옵션은 지정된 수만큼 in절에 부모 Key를 사용함으로서 가장 좋은 방법(?) in절에 키값을 넣으면서 데이터 뻥튀기x
    - 우리 프로젝트에서 N+1 문제에 해당하는 내용 있는지 알아보고 있다면 해결하기
     -> 일어날만한 요소를 최대한 배제함 -> 객체 단방향 . 근데 내가 모르는 곳에 있었을수도 있는데 배치페치사이즈를 기본적으로 설정해놓고 코딩해서 N+1 만난적은 없음.
## API

| 기능       |     메서드 |            URI             | REQUEST | RESPONSE |
|:---------|--------:|:--------------------------:|---------|----------|
| 회원가입     |    POST |       /api/register        |         |          |
| 로그인      |    POST |         /api/login         |         |          |
| 게시글 전체목록 |     GET |         api/board          |         |          |
| 게시글 등록   |    POST |         /api/board         |         |          |
| 게시글 수정   |     PUT |    /api/board/{boardId}    |         |          |
| 게시글 삭제   |  DELETE |    /api/board/{boardId}    |         |          |
| 좋아요      |    POST | /api/board/like/{boardId}  |         |          |
| 좋아요 취소   |  DELETE | /api/board/like/{boardId}  |         |          |

