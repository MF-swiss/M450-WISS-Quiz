package ch.wiss.wiss_quiz.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuizServiceMagicNumberTest {

    @Test
    @DisplayName("Quiz ist bestanden bei genau 60 Prozent")
    void quizIsPassedAt60Percent() {
        QuizService service = new QuizService(null, null);
        assertTrue(service.isQuizPassed(60));
    }

    @Test
    @DisplayName("Quiz ist nicht bestanden bei 59 Prozent")
    void quizIsNotPassedAt59Percent() {
        QuizService service = new QuizService(null, null);
        assertFalse(service.isQuizPassed(59));
    }

    @Test
    @DisplayName("hasEnoughFoos liefert true bei genau 3 Foos")
    void hasEnoughFoosReturnsTrueForThree() {
        QuizService service = new QuizService(null, null);

        var foos = java.util.List.of(new Foo(), new Foo(), new Foo());
        assertTrue(service.hasEnoughFoos(foos));
    }

    @Test
    @DisplayName("hasEnoughFoos liefert false bei weniger als 3 Foos")
    void hasEnoughFoosReturnsFalseForTwo() {
        QuizService service = new QuizService(null, null);

        var foos = java.util.List.of(new Foo(), new Foo());
        assertFalse(service.hasEnoughFoos(foos));
    }
}
