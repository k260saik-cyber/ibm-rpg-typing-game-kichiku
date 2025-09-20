package com.example.typing;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import jakarta.annotation.PostConstruct;

@RestController
public class GameController {

    private final List<Question> questions = new ArrayList<>();
    private final Random random = new Random();

    @PostConstruct
    public void init() throws Exception {
        // resourcesフォルダにあるcommands.csvを読み込む
        ClassPathResource resource = new ClassPathResource("commands.csv");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 3);
                if (parts.length == 3) {
                    // 読み込んだ行をQuestionオブジェクトにしてリストに追加
                    questions.add(new Question(parts[0], parts[1], parts[2]));
                }
            }
        }
    }

    @GetMapping("/api/question")
    public Question getQuestion() {
        // 問題リストが空でなければ、ランダムに1問選んで返す
        if (questions.isEmpty()) {
            return new Question("ERROR", "No questions loaded", "問題が読み込まれていません");
        }
        return questions.get(random.nextInt(questions.size()));
    }
}