# 프로젝트명

## STS19_REST

 
---
## 사용 기술
- spring framework


---
## 세팅
1. 시험 시작은 이클립스 워크스페이스를 별도로 만들면서 시작하게 된다.
고로 이클립스와 톰캣을 설치하는 것과 자바를 새로 설치하는 과정은 생략한다.
2. 환경 세팅은 
2-1 prefernces에서 
General > Editors > File Associations에서는 xml을 이클립스에서 제공하는 xml 에디터를 선택
sql 파일은 dbeaver로 default

2-2 General > Workspace의 text file encoding을 other의 utf-8로 설정
2-3 web > CSS files에서 encoding utf-8로 변경
2-4 web > HTML files에서 encoding utf-8로 변경
2-5 web > HTML > EDITOR > Templates에서 html5 설명이 쓰인 템플릿 문서로 들어가
<html> 을 > <html lang="ko"> 로 변경 - 브라우저에서 이 문서를 어떤 언어로 해석할까에 대한 것

2-6 web > jsp files 도 인코딩 utf-8 변경
2-7 web > jsp files > editor > templates 이녀석도 html 문서를 만드니 
똑같이 <html lang="ko"> 로 변경

2-8 General > Content Types > 의 text 선택후 나온 리스트 중에서 javaScript source file이 
기본 인코딩이 utf-8 이 아니라면 utf-8 설정

3. 서버 세팅은 톰캣 서버를 연동해야 하는데 시험상 강사님이 바라는 거는 기존 톰캣이 아닌 새로운 톰캣이므로
톰캣을 새로운 폴더에 압축 풀고 톰캣의 루트 폴더 ex)새로운 폴더\apache-tomcat-버전 를 
새로운 서버와 연결로 선택하고 apache tomcat 9 버전이므로 9로 선택 진행

3-1. 이클립스 위의 옵션 중에서 Window를 선택하고 show view에서 servers를 체크해두면
밑에서 servers 창을 띄울 수 있음, 해당 서버를 더블 클릭하면 서버 세팅을 할 수 있음
> 서버가 어디에서 관리 될 것인가인 Server Locations 설정에서 톰캣 installation을 사용한다고하자
톰캣 폴더 위에서 서버 파일과 배포를 하게 설정
> Server Options에서 module contexts를 분리된 xml file들에 푸는 옵션 선택
> 오른쪽의 prots를 기존과 안겹치게들 수정
> 저장
>> Servers 폴더가 생김 
>> Servers > server.xml 에서 
서버 파일 인코딩 설정 : Connector에 URIEncoding="utf-8" useBodyEncodingForURI="true" 추가

3-2. jstl을 서버 단위로 쓰고 싶다면 톰캣 lib 파일에 추가해야함 jstl-버전.jar 파일을 톰캣 lib에 가져다 놓기

4. db 연결
4-1. 오라클은 이미 설치되어 있으므로 오라클 db 설치는 생략
4-2. dbeaver, ermaster 가 이미 설치되어 있으므로 생략
4-3. dbeaver로 데이터베이스 설정을 좀 해야함 - 오라클
데이터베이스 > oracle > edit > libraries > 다 지우고 > add file > 
oracleexe\app\oracle\product\버전\server\jdbc> 6_g 버전 추가
>find class 후 oracle.jdbc.driver.OracleDriver 선택
settings로 그대로 들어가서 default database 를 XE, port 10001, user는 scott 26으로 설정

4-4. dbeaver로 쉽게 데이터 베이스를 프로젝트와 연결 할 수 있음
sid XE, port는 10001로 설정되어 있음 


5. 스프링 프로젝트 생성후 좀 설정해야 될 것들이 있음 
5-1. 한글 표시가 안되므로 필터 옵션을 web.xml에 넣어주어야함
<!-- 스프링 컨테이너 인코딩 설정 -->
	<filter>
		<filter-name>encodingFilter</filter-name>
		<filter-class>
			org.springframework.web.filter.CharacterEncodingFilter
		</filter-class>
		<init-param>
			<param-name>encoding</param-name>
			<param-value>UTF-8</param-value>
		</init-param>
		<init-param>
			<param-name>forceEncoding</param-name>
			<param-value>true</param-value>
		</init-param>
	</filter>
	<filter-mapping>
		<filter-name>encodingFilter</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>

5-2. pom.xml에는 자바와 스프링 버전을 1.8, 5.2.1.RELEASE
라이브러리 추가를 위해 
dependencies에 
<!-- https://mvnrepository.com/artifact/org.hibernate.validator/hibernate-validator -->
		<dependency>
			<groupId>org.hibernate.validator</groupId>
			<artifactId>hibernate-validator</artifactId>
			<version>6.1.5.Final</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/com.oracle.database.jdbc/ojdbc6 -->
		<dependency>
			<groupId>com.oracle.database.jdbc</groupId>
			<artifactId>ojdbc6</artifactId>
			<version>11.2.0.4</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-jdbc</artifactId>
			<version>${org.springframework-version}</version>
		</dependency>
		<!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<version>1.18.16</version>
			<scope>provided</scope>
		</dependency>
		<!-- MyBatis -->
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis</artifactId>
			<version>3.4.6</version>
		</dependency>

		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis-spring</artifactId>
			<version>1.3.2</version>
		</dependency>
추가
5-3. servlet-context.xml에서 빈들과 설정들을 다룰 수 있는데
프론트를 위한 폴더를 추가 할경우 webapp 밑에 생성해야하고 resource 밑이 아니라면
resource와 버금가도록 설정해주어야하는데 그게
servlet-context.xml의 <resources를 보고 잘 배껴서 파일이름만 바꾸면됨

5-4. base-package가 잘 가르키고 있는 확인(context scan으로 빈으로 만들것들 쭉 읽어서 빈으로 만듬)

5-5. db 자원을 다루기 위한 DriverManagerDataSource의 dataSource 생성
	<beans:bean name="dataSource"
		class="org.springframework.jdbc.datasource.DriverManagerDataSource">
		<beans:property name="driverClassName"
			value="oracle.jdbc.driver.OracleDriver"></beans:property>
		<beans:property name="url"
			value="jdbc:oracle:thin:@localhost:10001:XE"></beans:property>
		<beans:property name="username" value="scott26"></beans:property>
		<beans:property name="password" value="tiger26"></beans:property>
	</beans:bean>

5-6. 트랜잭션을 위한 빈 생성
	<!-- PlatformTransactionManger 빈객체 -->
	<beans:bean name="transactionManager"
		class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
		<beans:property name="dataSource" ref="dataSource" />
	</beans:bean>

	<!--TransactionTemplate 객체 -->
	<beans:bean name="transactionTemplate"
		class="org.springframework.transaction.support.TransactionTemplate">
		<beans:property name="transactionManager"
			ref="transactionManager" />
	</beans:bean>

	<!-- @Transactional 사용 -->
	<tx:annotation-driven transaction-manager="transactionManager" />
	
이거하고 open with로 spring editor로 열어서 tx 추가
5-7. myBatis 설정
<!-- MyBatis 설정 -->
	<beans:bean name="sqlSessionFactory"
		class="org.mybatis.spring.SqlSessionFactoryBean">
		<beans:property name="dataSource" ref="dataSource"></beans:property>
		<beans:property name="mapperLocations">
			<beans:list>
				<beans:value>classpath:mapper/*.xml</beans:value>
				<beans:value>classpath:com/spring/test/**/*.xml
				</beans:value>
			</beans:list>
		</beans:property>
	</beans:bean>
	<!-- xml 파일이 여러개 일 수 있다는 것이고, 서버 시작시 mapper 폴더 밑의 모든 xml 파일들을 찾아 매핑해둠 -->
	<beans:bean name="sqlSession"
		class="org.mybatis.spring.SqlSessionTemplate">
		<beans:constructor-arg index="0"
			ref="sqlSessionFactory"></beans:constructor-arg>
	</beans:bean>
	
6. 쿼리 작성
6-1. 프로젝트에 ERD 폴더 생성후 > 조건에 맞는 erdiagram (ERMASTER) 생성>
ddl 생성> sequence 생성 
6-2. DAO 인터페이스를 위한 패키지와 DAO 인터페이스 생성(dao 구현체는 @Component를 붙이지만
mapper는 인터페이스에 구현한걸 넣으므로 @MapperScan을 dao 인터페이스에 넣어준다.)
, DTO도 만들어둠(lombok 활용)(primitive 타입 말고 wrapper를 쓰는걸 추천)
6-3. src/main/resources 패키지에 mapper 폴더 생성
6-4. mybatis xml 생성, namespace 를 컨텍스트 패스+DAO 인터페이스를 만들 패키지+DAO인터페이스
6-5. .sql 파일에서 쿼리들을 작성하며 필요 쿼리가 작동하면 mapper의 xml에 추가
6-6. 위 과정이 완성되면 서비스를 위한 패키지와 클래스 생성후 (@Service 어노테이션추가)

6-7. 서비스 클래스에서 DAO 인터페이스 주입, SqlSession 주입
트랜잭션 작성

6-8. 컨트롤러 패키지 생성 컨트롤러 생성(@Controller 추가)
@RequestMapping 추가
Service 인터페이스에 구현체 주입

6-9. 컨트롤러 작성 - 컨트롤러 작성 시 method 설정이나 ,GetMapping, PostMapping 주의할 것

6-10. @InitBinder 추가후 밑에 
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setValidator(new MenuValidator());
	}
6-11. validator 작성 Validator 인터페이스 상속

6-12. 검증해야될 핸들러에다 dto에 @Valid 추가

6-13. views 작성
jstl 작성시 아래 추가
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
외부 파일을 가져올시 ${pageContext.request.contextPath }이거 잊지말고
컨텍스트 path 사용 안할거면, 컨텍스트 path의 상대적 경로/ 이렇게

부트스트랩은
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
이거 추가

강사님의 편의를 위해서 servlet-context에서 포트 1521로 바꿔서
파일 archive는 해당 프로젝트 클릭해서 하자 
