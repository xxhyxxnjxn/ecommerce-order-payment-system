group = "imgsystem"
version = "0.0.1-SNAPSHOT"

plugins {
    java
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.4"
    // Spring REST Docs 의 결과물을 OpenAPI 3 스펙으로 변환
    id("com.epages.restdocs-api-spec") version "0.17.1"
    // OpenAPI 3 Spec을 기반으로 SwaggerUI 생성(HTML, CSS, JS)
    id("org.hidetake.swagger.generator") version "2.18.2"
    // 위에 두개 라이브러리를 어떻게 사용할 수 있냐면 dependencies 에 있는 기능을 통해 사용할 수 있다.

    // AVRO 추가
    id("com.github.davidmc24.gradle.plugin.avro") version "1.9.1"
}


java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}


repositories {
    mavenCentral()
    //avro 추가
    maven {
        url = uri("https://packages.confluent.io/maven")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.projectlombok:lombok")
    compileOnly("org.projectlombok:lombok")
    // Retrofit and OkHttp
    implementation("com.squareup.retrofit2:retrofit:2.10.0")
    implementation("com.squareup.retrofit2:converter-jackson:2.10.0")
    implementation("com.squareup.retrofit2:converter-gson:2.10.0")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.10.0")
    implementation("com.google.code.gson:gson")
//    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("com.mysql:mysql-connector-j")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("com.google.code.gson:gson")
    testAnnotationProcessor("org.projectlombok:lombok")
    testImplementation("org.projectlombok:lombok")
    // API Docs
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("org.springframework.restdocs:spring-restdocs-asciidoctor")
    testImplementation("com.epages:restdocs-api-spec-mockmvc:0.17.1")

    // Mockito
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-actuator")
    testImplementation("org.mockito:mockito-core:3.3.0")
    testImplementation("com.squareup.retrofit2:retrofit-mock:2.10.0")

    // Kafka Client
    implementation("org.springframework.kafka:spring-kafka")
    // avro 추가
    implementation("org.apache.avro:avro:1.11.3")
    implementation("io.confluent:kafka-avro-serializer:7.0.1")
}

//avro 추가
tasks.withType<com.github.davidmc24.gradle.plugin.avro.GenerateAvroJavaTask> {
    fieldVisibility = "PRIVATE"
    setCreateSetters("false")
    setSource("src/main/avro") //avro 파일의 위치를 찾아서 자바 클래스로 변경
    setOutputDir(file("build/generated-sources")) // 자바 클래스로 변경한 파일 저장 경로
}

tasks.withType<Test> {
    useJUnitPlatform()
}

openapi3 {
    this.setServer("https://localhost:8080") // list로 넣을 수 있어 각종 환경의 URL들을 넣을 수 있음!
    title = "FastEcommerce - Order/Payment"
    description = "Order/Payment API"
    version = "0.1.0"
    format = "yaml" // or json
}

tasks.register<Copy>("copyOasToSwagger") {
    delete("src/main/resources/static/swagger-ui/openapi3.yaml") // 기존 yaml 파일 삭제
    from(layout.buildDirectory.dir("api-spec/openapi3.yaml").get().asFile) // 복제할 yaml 파일 타겟팅
    into("src/main/resources/static/swagger-ui/.") // 타겟 디렉토리로 파일 복제
    dependsOn("openapi3") // openapi3 task가 먼저 실행되도록 설정
}

//restdocs는 원래 asciidocs로 html을 만드는데, ui를 swagger로 하기 때문에 asciidocs와 관련된 설정들은 없음

