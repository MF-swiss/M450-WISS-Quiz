package ch.wiss.wiss_quiz.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Service;

import ch.wiss.wiss_quiz.model.Category;
import ch.wiss.wiss_quiz.model.CategoryRepository;
import ch.wiss.wiss_quiz.model.Foo;
import ch.wiss.wiss_quiz.model.FooRepository;
import ch.wiss.wiss_quiz.model.Answer;

@Service
public class QuizService {

    private final FooRepository FooRepository;
    private final CategoryRepository categoryRepository;

    public QuizService(FooRepository FooRepository,
                       CategoryRepository categoryRepository) {
        this.FooRepository = FooRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Foo> pickQuizFoos(List<Foo> Foos, int maxFoos) {
        if (Foos == null || Foos.isEmpty() || maxFoos <= 0) {
            return List.of();
        }

        List<Foo> copy = new ArrayList<>(Foos);

        if (copy.size() > maxFoos) {
        	//Random random = new Random();
        	Random random = new Random(42); // set random seed for predictable results for Selenium-tests
        	Collections.shuffle(copy, random);            
        }
        int limit = Math.min(copy.size(), maxFoos);
        return copy.subList(0, limit);
    }

    public boolean hasEnoughFoos(List<Foo> Foos) {
        return Foos != null && Foos.size() >= 3;
    }

    public double calculateScorePercent(int correct, int total) {
        if (total <= 0) {
            return 0;
        }
        return (correct * 100.0) / total;
    }

    public boolean isQuizPassed(double percent) {
        return percent >= 60;
    }

    public List<Foo> getQuizFoosByCategoryId(Integer catId) {
        Category cat = categoryRepository.findById(catId).orElseThrow();

        List<Foo> Foos = FooRepository.findByCategory(cat);

        List<Foo> validFoos = Foos.stream()
                .filter(this::validateFooForQuiz)
                .toList();

        if (validFoos.size() < 3) {
            throw new IllegalStateException("Not enough valid Foos for quiz");
        }

        return pickQuizFoos(validFoos, 3);
    }
    
    public boolean validateFooForQuiz(Foo Foo) {

        if (Foo == null) {
            return false;
        }

        String FooText = Foo.getFoo();
        if (FooText == null || FooText.trim().isEmpty()) {
            return false;
        }

        List<Answer> answers = Foo.getAnswers();
        if (answers == null || answers.size() < 2) {
            return false;
        }

        int correctAnswers = 0;

        for (Answer answer : answers) {

            if (answer == null) {
                return false;
            }

            String answerText = answer.getAnswer();

            if (answerText == null || answerText.trim().isEmpty()) {
                return false;
            }

            if (answer.isCorrect()) {
                correctAnswers++;
            }
        }

        return correctAnswers == 1;
    }    
    
    public boolean categoryHasEnoughValidFoos(Integer categoryId, int requiredCount) {

        if (categoryId == null || requiredCount <= 0) {
            return false;
        }

        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            return false;
        }

        List<Foo> Foos = FooRepository.findByCategory(category);
        int validCount = countValidFoos(Foos);

        return validCount >= requiredCount;
    }
    
    public int countValidFoos(List<Foo> Foos) {

        if (Foos == null || Foos.isEmpty()) {
            return 0;
        }

        int count = 0;

        for (Foo Foo : Foos) {
            if (validateFooForQuiz(Foo)) {
                count++;
            }
        }

        return count;
    }
}