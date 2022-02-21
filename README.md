# hanghae_Second_Solo_Project
항해 두 번째 솔로 프로젝트

<<<<<<< HEAD
매거진 사이트 만들기
=======
매거진 사이트 만들기 
>>>>>>> edbea5559ad9b8a72829b22eed7e7d5fca7019f4

## 기술 스택
**`Back-end`**
- Java 8
<<<<<<< HEAD
- SpringBoot
- Spring Security
- Gradle
=======
- SpringBoot 
- Spring Security
- Gradle 
>>>>>>> edbea5559ad9b8a72829b22eed7e7d5fca7019f4
- JPA
- QueryDSL
- MySQL 8.0


<<<<<<< HEAD
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

=======

API 타임리프 특성상 메서드 GET, POST만 사용

| 기능     |   메서드 | URI  |
|:-------|------:|:----:|
| 게시글 조회 | GET | posts/  |
|   게시글 작성     |  POST    |  post/new    |
| 게시글 수정 | POST | post/{post_id}/edit |
| 게시글 삭제 | POST | post/{post_id}/delete |
| 댓글 등록  |   POST    |  comment/{post_id}    |
| 댓글 수정  |   POST    |   comment/{comment_id}/edit   |
| 댓글 삭제  |  POST     |   comment/{comment_id}/delete   |
>>>>>>> edbea5559ad9b8a72829b22eed7e7d5fca7019f4
