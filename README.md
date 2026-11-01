Spring Boot Backend 実習 (백엔드 프로그래밍 수업 실습)

栄進専門大学「バックエンドプログラミング(I)」の授業で、Spring Boot 3 を使って REST API・JPA・テストコードを学んだ実習リポジトリです。

영진전문대학교 「백엔드프로그래밍(I)」 수업에서 Spring Boot 3로 REST API, JPA, 테스트 코드를 실습한 저장소입니다.

🛠 Tech Stack
分野 / 분야	技術 / 기술
Language	Java 17
Framework	Spring Boot 3.4.0 (Web, Data JPA)
Database	H2 (ファイルモード / 파일 모드)
Library	Lombok
Test	JUnit 5, MockMvc, AssertJ
Build	Gradle
📌 API
Method	URL	内容 / 내용
GET	/hello	Hello World を返す
POST / PUT / DELETE	/test	HTTPメソッドごとの応答確認 / 메서드별 응답 확인
GET	/quiz?code=1	code によって 201 / 400 / 200 を返す
POST	/quiz	ボディ {"value": 1} なら 403、それ以外は 200
POST	/api/members	会員登録 / 회원 저장
GET	/api/members	会員一覧 / 회원 목록 조회
📂 構成 / 구조
src/main/java/me/scpark/springdeveloper/
├─ HelloWorldController.java     # 基本のGET/POST/PUT/DELETE
├─ QuizController.java           # ステータスコードの使い分け
├─ Member.java                   # JPAエンティティ (members テーブル)
├─ MemberRepository.java         # Spring Data JPA (findByName, @Query)
├─ MemberService.java            # 保存・一覧 (トランザクション)
├─ MemberController.java         # /api/members
└─ MemberCreateRequest / MemberResponse   # リクエスト・レスポンスDTO

src/test/java/
├─ JUnitTest.java / JUnitCycleTest.java          # JUnitの基本とライフサイクル
└─ me/scpark/springdeveloper/
   ├─ MemberControllerTest.java   # MockMvcでAPIをテスト
   └─ MemberRepositoryTest.java   # @DataJpaTestでCRUDと変更検知をテスト
▶ 実行方法 / 실행 방법
bash
# サーバー起動 / 서버 실행 (http://localhost:8080/hello)
./gradlew bootRun

# テスト実行 / 테스트 실행
./gradlew test

※ Windows では gradlew.bat bootRun / gradlew.bat test

📝 学んだこと / 배운 것
Controller → Service → Repository の3層構造で責任を分ける方法
DTO（Request / Response）でエンティティを外部に直接出さない設計
@Transactional と JPA の変更検知（dirty checking）
@SpringBootTest + MockMvc、@DataJpaTest + @Sql によるテストの書き方
