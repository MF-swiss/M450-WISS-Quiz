package ch.wiss.wiss_quiz.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.wiss.wiss_quiz.model.Answer;
import ch.wiss.wiss_quiz.model.Foo;

class QuizServiceTest {

    private QuizService service;

    @BeforeEach
    void setUp() {
        service = new QuizService(null, null);
    }

    // Station 1 – Standardfälle (Happy Path)
    @Test
    void returnsAllIfLessOrEqualMax() {
        List<Foo> input = List.of(new Foo(), new Foo());

        List<Foo> result = service.pickQuizFoos(input, 3);

        assertEquals(2, result.size());
    }

    // Station 1 – Standardfälle (Happy Path)
    @Test
    void limitsToMaxIfMoreThanMax() {
        List<Foo> input = List.of(
                new Foo(), new Foo(), new Foo(), new Foo(), new Foo()
        );

        List<Foo> result = service.pickQuizFoos(input, 3);

        assertEquals(3, result.size());
    }

    // Station 1 – Standardfälle (Happy Path)
    @Test
    void doesNotModifyOriginalList() {
        List<Foo> input = new ArrayList<>(List.of(
                new Foo(), new Foo(), new Foo(), new Foo()
        ));

        service.pickQuizFoos(input, 3);

        assertEquals(4, input.size());
    }

    // Station 1 – Standardfälle (Happy Path)
    @Test
    void calculateScorePercent_returns60_when3Of5Correct() {
        double result = service.calculateScorePercent(3, 5);

        assertEquals(60.0, result);
    }

    // Station 1 – Standardfälle (Happy Path)
    @Test
    void isQuizPassed_returnsTrue_whenPercentIs60() {
        boolean result = service.isQuizPassed(60.0);

        assertTrue(result);
    }

    // Station 1 – Standardfälle (Happy Path)
    @Test
    void isQuizPassed_returnsFalse_whenPercentIs59() {
        boolean result = service.isQuizPassed(59.0);

        assertFalse(result);
    }

    // Station 2 – Randfälle (Edge Cases)
    @Test
    void handlesBadInputs() {
        assertTrue(service.pickQuizFoos(null, 3).isEmpty());
        assertTrue(service.pickQuizFoos(List.of(), 3).isEmpty());
        assertTrue(service.pickQuizFoos(List.of(new Foo()), 0).isEmpty());
    }

    // Station 2 – Randfälle (Edge Cases)
    @Test
    void pickQuizFoos_returnsEmptyList_whenMaxFoosIsNegative() {
        List<Foo> input = List.of(new Foo(), new Foo());

        List<Foo> result = service.pickQuizFoos(input, -1);

        assertTrue(result.isEmpty());
    }

    // Station 2 – Randfälle (Edge Cases)
    @Test
    void calculateScorePercent_returnsZero_whenTotalIsZero() {
        double result = service.calculateScorePercent(3, 0);

        assertEquals(0.0, result);
    }

    // Station 2 – Randfälle (Edge Cases)
    @Test
    void calculateScorePercent_returnsZero_whenTotalIsNegative() {
        double result = service.calculateScorePercent(3, -5);

        assertEquals(0.0, result);
    }

    // Station 2 – Randfälle (Edge Cases)
    @Test
    void hasEnoughFoos_returnsTrue_whenListHasAtLeastThreeFoos() {
        List<Foo> input = List.of(new Foo(), new Foo(), new Foo());

        boolean result = service.hasEnoughFoos(input);

        assertTrue(result);
    }

    // Station 2 – Randfälle (Edge Cases)
    @Test
    void hasEnoughFoos_returnsFalse_whenListHasLessThanThreeFoos() {
        List<Foo> input = List.of(new Foo(), new Foo());

        boolean result = service.hasEnoughFoos(input);

        assertFalse(result);
    }

    // Station 3 – Validierung von Quizfragen
    @Test
    void validateFooForQuiz_returnsFalse_whenFooIsNull() {
        boolean result = service.validateFooForQuiz(null);

        assertFalse(result);
    }

    // Station 3 – Validierung von Quizfragen
    @Test
    void validateFooForQuiz_returnsFalse_whenFooTextIsNull() {
        Foo Foo = new Foo();
        Foo.setFoo(null);
        Foo.setAnswers(createValidAnswers());

        boolean result = service.validateFooForQuiz(Foo);

        assertFalse(result);
    }

    // Station 3 – Validierung von Quizfragen
    @Test
    void validateFooForQuiz_returnsFalse_whenFooTextIsBlank() {
        Foo Foo = new Foo();
        Foo.setFoo("   ");
        Foo.setAnswers(createValidAnswers());

        boolean result = service.validateFooForQuiz(Foo);

        assertFalse(result);
    }

    // Station 3 – Validierung von Quizfragen
    @Test
    void validateFooForQuiz_returnsFalse_whenAnswersAreNull() {
        Foo Foo = new Foo();
        Foo.setFoo("Frage?");

        boolean result = service.validateFooForQuiz(Foo);

        assertFalse(result);
    }

    // Station 3 – Validierung von Quizfragen
    @Test
    void validateFooForQuiz_returnsFalse_whenLessThanTwoAnswersExist() {
        Foo Foo = new Foo();
        Foo.setFoo("Frage?");
        Foo.setAnswers(List.of(createAnswer("Nur eine", true)));

        boolean result = service.validateFooForQuiz(Foo);

        assertFalse(result);
    }

    // Station 3 – Validierung von Quizfragen
    @Test
    void validateFooForQuiz_returnsFalse_whenAnswerIsNull() {
        Foo Foo = new Foo();
        Foo.setFoo("Frage?");

        List<Answer> answers = new ArrayList<>();
        answers.add(createAnswer("A", true));
        answers.add(null);

        Foo.setAnswers(answers);

        boolean result = service.validateFooForQuiz(Foo);

        assertFalse(result);
    }
    // Station 3 – Validierung von Quizfragen
    @Test
    void validateFooForQuiz_returnsFalse_whenAnswerTextIsBlank() {
        Foo Foo = new Foo();
        Foo.setFoo("Frage?");
        Foo.setAnswers(List.of(createAnswer(" ", true), createAnswer("B", false)));

        boolean result = service.validateFooForQuiz(Foo);

        assertFalse(result);
    }

    // Station 3 – Validierung von Quizfragen
    @Test
    void validateFooForQuiz_returnsFalse_whenNoCorrectAnswerExists() {
        Foo Foo = new Foo();
        Foo.setFoo("Frage?");
        Foo.setAnswers(List.of(createAnswer("A", false), createAnswer("B", false)));

        boolean result = service.validateFooForQuiz(Foo);

        assertFalse(result);
    }

    // Station 3 – Validierung von Quizfragen
    @Test
    void validateFooForQuiz_returnsFalse_whenMultipleCorrectAnswersExist() {
        Foo Foo = new Foo();
        Foo.setFoo("Frage?");
        Foo.setAnswers(List.of(createAnswer("A", true), createAnswer("B", true)));

        boolean result = service.validateFooForQuiz(Foo);

        assertFalse(result);
    }

    // Station 3 – Validierung von Quizfragen
    @Test
    void validateFooForQuiz_returnsTrue_whenExactlyOneCorrectAnswerExists() {
        Foo Foo = new Foo();
        Foo.setFoo("Frage?");
        Foo.setAnswers(createValidAnswers());

        boolean result = service.validateFooForQuiz(Foo);

        assertTrue(result);
    }

    // Station 3 – Validierung von Quizfragen
    @Test
    void countValidFoos_returnsZero_whenFoosIsNull() {
        int result = service.countValidFoos(null);

        assertEquals(0, result);
    }

    // Station 3 – Validierung von Quizfragen
    @Test
    void countValidFoos_returnsZero_whenFoosIsEmpty() {
        int result = service.countValidFoos(List.of());

        assertEquals(0, result);
    }

    // Station 3 – Validierung von Quizfragen
    @Test
    void countValidFoos_countsOnlyValidFoos() {
        Foo valid1 = new Foo();
        valid1.setFoo("Q1");
        valid1.setAnswers(createValidAnswers());

        Foo valid2 = new Foo();
        valid2.setFoo("Q2");
        valid2.setAnswers(createValidAnswers());

        Foo invalid = new Foo();
        invalid.setFoo("Q3");
        invalid.setAnswers(List.of(createAnswer("Only one", true)));

        int result = service.countValidFoos(List.of(valid1, invalid, valid2));

        assertEquals(2, result);
    }

    private List<Answer> createValidAnswers() {
        return List.of(
                createAnswer("A", true),
                createAnswer("B", false)
        );
    }

    private Answer createAnswer(String text, boolean correct) {
        Answer answer = new Answer();
        answer.setAnswer(text);
        answer.setCorrect(correct);
        return answer;
    }
}