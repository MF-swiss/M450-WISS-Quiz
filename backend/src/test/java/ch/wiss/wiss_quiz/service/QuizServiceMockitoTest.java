package ch.wiss.wiss_quiz.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ch.wiss.wiss_quiz.model.Answer;
import ch.wiss.wiss_quiz.model.Category;
import ch.wiss.wiss_quiz.model.CategoryRepository;
import ch.wiss.wiss_quiz.model.Foo;
import ch.wiss.wiss_quiz.model.FooRepository;

@ExtendWith(MockitoExtension.class)
class QuizServiceMockitoTest {

    @Mock
    private FooRepository FooRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private QuizService service;

    // Station 4 – Service und Datenbank (Mockito), Fehlerfall
    @Test
    void getQuizFoosByCategoryId_throwsException_whenNotEnoughFoosExist() {
        Category category = new Category();
        List<Foo> Foos = List.of(
                new Foo(),
                new Foo()
        );

        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(FooRepository.findByCategory(category)).thenReturn(Foos);

        assertThrows(IllegalStateException.class, () -> service.getQuizFoosByCategoryId(1));
    }

    // Station 4 – Service und Datenbank (Mockito)
    @Test
    void getQuizFoosByCategoryId_throwsException_whenCategoryDoesNotExist() {
        when(categoryRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.getQuizFoosByCategoryId(99));
    }

    // Station 4 – Service und Datenbank (Mockito)
    @Test
    void getQuizFoosByCategoryId_returnsThreeFoos_whenEnoughValidFoosExist() {
        Category category = new Category();
        List<Foo> Foos = List.of(
                createValidFoo("Q1"),
                createValidFoo("Q2"),
                createValidFoo("Q3"),
                createValidFoo("Q4")
        );

        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(FooRepository.findByCategory(category)).thenReturn(Foos);

        List<Foo> result = service.getQuizFoosByCategoryId(1);

        assertEquals(3, result.size());
    }

    // Station 4 – Service und Datenbank (Mockito)
    @Test
    void getQuizFoosByCategoryId_throwsException_whenNotEnoughValidFoosExist() {
        Category category = new Category();
        List<Foo> Foos = List.of(
                createValidFoo("Q1"),
                createValidFoo("Q2"),
                createInvalidFoo()
        );

        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(FooRepository.findByCategory(category)).thenReturn(Foos);

        assertThrows(IllegalStateException.class, () -> service.getQuizFoosByCategoryId(1));
    }

    // Station 4 – Service und Datenbank (Mockito)
    @Test
    void categoryHasEnoughValidFoos_returnsTrue_whenEnoughValidFoosExist() {
        Category category = new Category();
        List<Foo> Foos = List.of(
                createValidFoo("Q1"),
                createValidFoo("Q2"),
                createValidFoo("Q3"),
                createInvalidFoo()
        );

        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(FooRepository.findByCategory(category)).thenReturn(Foos);

        boolean result = service.categoryHasEnoughValidFoos(1, 3);

        assertTrue(result);
    }

    // Station 4 – Service und Datenbank (Mockito)
    @Test
    void categoryHasEnoughValidFoos_returnsFalse_whenNotEnoughValidFoosExist() {
        Category category = new Category();
        List<Foo> Foos = List.of(
                createValidFoo("Q1"),
                createInvalidFoo(),
                createInvalidFoo()
        );

        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(FooRepository.findByCategory(category)).thenReturn(Foos);

        boolean result = service.categoryHasEnoughValidFoos(1, 2);

        assertFalse(result);
    }

    // Station 4 – Service und Datenbank (Mockito)
    @Test
    void categoryHasEnoughValidFoos_returnsFalse_whenCategoryDoesNotExist() {
        when(categoryRepository.findById(99)).thenReturn(Optional.empty());

        boolean result = service.categoryHasEnoughValidFoos(99, 3);

        assertFalse(result);
    }

    // Station 4 – Service und Datenbank (Mockito)
    @Test
    void categoryHasEnoughValidFoos_returnsFalse_whenCategoryIdIsNull() {
        boolean result = service.categoryHasEnoughValidFoos(null, 3);

        assertFalse(result);
        verifyNoInteractions(categoryRepository, FooRepository);
    }

    // Station 4 – Service und Datenbank (Mockito)
    @Test
    void categoryHasEnoughValidFoos_returnsFalse_whenRequiredCountIsZero() {
        boolean result = service.categoryHasEnoughValidFoos(1, 0);

        assertFalse(result);
        verifyNoInteractions(categoryRepository, FooRepository);
    }

    // Station 4 – Service und Datenbank (Mockito)
    @Test
    void getQuizFoosByCategoryId_callsRepositories() {
        Category category = new Category();
        List<Foo> Foos = List.of(
                createValidFoo("Q1"),
                createValidFoo("Q2"),
                createValidFoo("Q3")
        );

        when(categoryRepository.findById(1)).thenReturn(Optional.of(category));
        when(FooRepository.findByCategory(category)).thenReturn(Foos);

        service.getQuizFoosByCategoryId(1);

        verify(categoryRepository).findById(1);
        verify(FooRepository).findByCategory(category);
    }

    private Foo createValidFoo(String text) {
        Foo Foo = new Foo();
        Foo.setFoo(text);
        Foo.setAnswers(List.of(
                createAnswer("A", true),
                createAnswer("B", false)
        ));
        return Foo;
    }

    private Foo createInvalidFoo() {
        Foo Foo = new Foo();
        Foo.setFoo("Invalid");
        Foo.setAnswers(List.of(
                createAnswer("A", true),
                createAnswer("B", true)
        ));
        return Foo;
    }

    private Answer createAnswer(String text, boolean correct) {
        Answer answer = new Answer();
        answer.setAnswer(text);
        answer.setCorrect(correct);
        return answer;
    }
}