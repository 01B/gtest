# gtest
G*** 코딩테스트

* 프로그램 개요
  - 회원 등록 프로젝트를 만든다.
  - 회원 목록을 볼 수 있다.
  - 회원 등록을 할 수 있다.
  - 회원 등록시에는 이름 중복 체크를 한다.
  
* 프로그램 개발 : Spring boot + JPA + H2 + FreeMarker + Gradle + Lombok

* 프로그램 개발 순서 : 기본적으로 TDD를 지향하였습니다.
  1. Domain 설계
  2. 클래스 간의 구조 설계
  3. 코딩

* Request Mapping
  - GET("/users") : 회원 목록을 불러와 template 페이지에 보여줍니다.
  - GET("/register") : 회원 등록 페이지로 이동합니다.
  - POST("/users") : 회원을 등록합니다.
 
* 주요 문제 해결
  - 회원 등록시 필수값 : @Valid annotation을 사용하였습니다.
  - 회원 등록시 잘못된 이름 : server에서 InvalidNameException이 발생하고 메세지를 front로 던집니다. 
  - 회원 등록시 이름 중복 : server에서 InvalidNameException이 발생하고 메세지를 front로 던집니다.
 
* 주의사항
  - 프로젝트를 받으신 뒤 *Enable annotation processing 옵션을 체크해주세요.
  
*'Build, Execution, Deployment'
-> 'Compiler'
-> 'Annotation Processors'
-> 'Enable annotation processing' check
 
