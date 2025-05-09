plugins {  //미리 구성해둔 task들의 그룹 ?, 커스터마이징 가능
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"
	kotlin("plugin.jpa") version "1.9.25"
}

group = "study"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {//의존성 - 스프링머시기에서 만든 web이나 db 등을 사용한다 | 이후 필요한건 직접 추가
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation
	implementation("org.springframework.boot:spring-boot-starter-validation")
	// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security
	implementation("org.springframework.boot:spring-boot-starter-security:3.4.4")
	// https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api
	implementation("io.jsonwebtoken:jjwt-api:0.12.6")
	// https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-jackson
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6")
	// https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-impl
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
}

noArg{ //매개변수 없는 생성자를 자동으로 추가
	annotation("jakarta.persistence.Entity")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

allOpen { //plugin.spring외에 추가로 열 기능?
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
//..