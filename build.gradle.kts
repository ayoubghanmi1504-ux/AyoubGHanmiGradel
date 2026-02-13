plugins {
    java

    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("dev.langchain4j:langchain4j-bom:1.10.0"))
    implementation("dev.langchain4j:langchain4j-open-ai")
}


application {
    mainClass.set("org.example.Main")
}


tasks.register<Exec>("ollamaVersion") {
    if (System.getProperty("os.name").lowercase().contains("windows")) {
        commandLine("cmd", "/c", "ollama --version")
    } else {
        commandLine("sh", "-c", "ollama --version")
    }
}


tasks.register<Exec>("ollamaPs") {
    if (System.getProperty("os.name").lowercase().contains("windows")) {
        commandLine("cmd", "/c", "ollama ps")
    } else {
        commandLine("sh", "-c", "ollama ps")
    }
}

// Paso 11: Tarea llmInfo con dependencias
tasks.register("llmInfo") {
    dependsOn("ollamaVersion", "ollamaPs")
    doLast {
        println("Demo finalizada")
    }
}