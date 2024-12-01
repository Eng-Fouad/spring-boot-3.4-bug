plugins {
    java
    alias(deps.plugins.graalvm)
    alias(deps.plugins.springboot)
}

group = "io.fouad"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(deps.libs.spring.boot.starter.web)
    implementation(deps.libs.spring.boot.starter.oauth2.resource.server)
    testImplementation(deps.libs.spring.boot.starter.test)
}

tasks.test {
    useJUnitPlatform()
}