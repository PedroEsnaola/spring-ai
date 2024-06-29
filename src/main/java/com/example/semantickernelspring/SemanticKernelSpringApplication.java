package com.example.semantickernelspring;


import com.example.semantickernelspring.domain.model.User;
import com.example.semantickernelspring.domain.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@SpringBootApplication
@Slf4j
public class SemanticKernelSpringApplication {

    @Autowired
    OpenAiChatModel chatModel;

    @Autowired
    public ObjectMapper objectMapper;

    @Autowired
    public UserService userService;

    private static final String systemMessage = """
                Voce é um assistente virtual especialista em buscar dados de usuarios em um banco, voce sabe que o usuario pedro tem o email pedro.teste@teste
            """;

    public static void main(String[] args) {
        SpringApplication.run(SemanticKernelSpringApplication.class, args);
    }


    @PostConstruct
    public void init() {
        userService.deleteAll();
        userService.createUser(new User(UUID.randomUUID(), "Pedro", "exemplo"));
        OpenAiChatOptions openaAiOptions = OpenAiChatOptions.builder().withFunctions(Set.of("findEmailByUsername", "updateUser")).build();
        String response = chatModel.call(new Prompt(List.of(new SystemMessage(systemMessage), new UserMessage("Find the user by name Pedro and update his email to 'anewemail'")), openaAiOptions)).getResult().getOutput().getContent();
        log.info("response: " + response);
    }
}
