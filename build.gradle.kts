import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.6"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
	id("org.jmailen.kotlinter") version "3.12.0"
}

group = "com.odenizturker"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	// Spring
	implementation("org.springframework.boot:spring-boot-starter-webflux")

	// Kotlin
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

	// Swagger support
	implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.0.4")

	// Test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
}

tasks.bootRun {
	doFirst {
		systemProperty("database.endpoint", System.getenv("DATABASE_ENDPOINT") ?: "localhost:5433")
		systemProperty("database.migration.endpoint", System.getenv("DATABASE_MIGRATION_ENDPOINT") ?: "localhost:5433")
		systemProperty("database.name", System.getenv("DATABASE_NAME") ?: "gather_test")
		systemProperty("database.user", System.getenv("DATABASE_USER") ?: "db_user")
		systemProperty("database.password", System.getenv("DATABASE_PASSWORD") ?: "db_pass")
	}
}


tasks.test {
	doFirst {
		systemProperty("database.endpoint", System.getenv("DATABASE_ENDPOINT") ?: "localhost:5433")
		systemProperty("database.migration.endpoint", System.getenv("DATABASE_MIGRATION_ENDPOINT") ?: "localhost:5433")
		systemProperty("database.name", System.getenv("DATABASE_NAME") ?: "gather_test_test")
		systemProperty("database.user", System.getenv("DATABASE_USER") ?: "db_user")
		systemProperty("database.password", System.getenv("DATABASE_PASSWORD") ?: "db_pass")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}