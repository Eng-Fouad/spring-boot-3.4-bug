dependencyResolutionManagement {
    versionCatalogs {
        create("deps") {
            version("graalvmPluginVersion", "0.10.3") // https://central.sonatype.com/artifact/org.graalvm.buildtools.native/org.graalvm.buildtools.native.gradle.plugin
            version("springBootLibVersion", "3.4.0") // https://central.sonatype.com/artifact/org.springframework.boot/spring-boot-dependencies

            plugin("graalvm", "org.graalvm.buildtools.native").versionRef("graalvmPluginVersion")
            plugin("springboot", "org.springframework.boot").versionRef("springBootLibVersion")

            library("libs.spring.boot.starter.web", "org.springframework.boot", "spring-boot-starter-web").versionRef("springBootLibVersion")
            library("libs.spring.boot.starter.oauth2.resource.server", "org.springframework.boot", "spring-boot-starter-oauth2-resource-server").versionRef("springBootLibVersion")
            library("libs.spring.boot.starter.test", "org.springframework.boot", "spring-boot-starter-test").versionRef("springBootLibVersion")
        }
    }
}

rootProject.name = "spring-boot-3.4-bug"