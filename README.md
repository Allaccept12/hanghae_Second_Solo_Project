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
- QueryDSL
- MySQL 8.0



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

