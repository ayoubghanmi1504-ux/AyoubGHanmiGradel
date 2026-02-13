package org.example;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.openai.OpenAiChatModel;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        var modelPreguntador = OpenAiChatModel.builder()
                .baseUrl("http://localhost:11434/v1")
                .apiKey("NO_TOKEN")
                .modelName("qwen2.5:7b")
                .build();


        var modelRespondedor = OpenAiChatModel.builder()
                .baseUrl("http://localhost:11434/v1")
                .apiKey("NO_TOKEN")
                .modelName("llama3.1:8b")
                .build();

        List<ChatMessage> history = new ArrayList<>();


        history.add(new SystemMessage("Eres un experto en inteligencia artificial."));


        String tema = "Genera una pregunta sobre si las IAs podrán tener sentimientos.";


        String preguntaTexto = modelPreguntador.chat(tema);
        System.out.println("Qwen pregunta: " + preguntaTexto);


        history.add(new UserMessage(preguntaTexto));


        AiMessage respuestaIA = modelRespondedor.chat(history).aiMessage();
        System.out.println("\nLlama3.1 responde: " + respuestaIA.text());
    }
}