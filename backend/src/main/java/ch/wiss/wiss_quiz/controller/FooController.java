package ch.wiss.wiss_quiz.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ch.wiss.wiss_quiz.model.Answer;
import ch.wiss.wiss_quiz.model.AnswerRepository;
import ch.wiss.wiss_quiz.model.Category;
import ch.wiss.wiss_quiz.model.CategoryRepository;
import ch.wiss.wiss_quiz.model.Foo;
import ch.wiss.wiss_quiz.model.FooRepository;

@Controller // This means that this class is a Controller
@RequestMapping(path="/Foo") // This means URL's start with /Foo (after Application path)
public class FooController {
  @Autowired
  private FooRepository FooRepository;
  @Autowired
  private CategoryRepository categoryRepository;
  @Autowired
  private AnswerRepository answerRepository;
  
  //@CrossOrigin(origins = "http://localhost:3000")
  @PostMapping(path="/{cat_id}") // Map ONLY POST Requests
  public @ResponseBody String addNewFoo(@PathVariable(value="cat_id") Integer catId, @RequestBody Foo Foo) {
      
	Optional<Category> cat = categoryRepository.findById(catId);
	Foo.setCategory(cat.get());
	// we need to store nested Answer-Objects seperately
	List<Answer> answers = List.copyOf(Foo.getAnswers());
	
	Foo.setAnswers(null);
	FooRepository.save(Foo);
	
	// we need to store nested Answer-Objects seperately
	answers.forEach(a -> {
		a.setFoo(Foo);
		answerRepository.save(a);
	});
	
	return "Saved";
  }
  
  //@CrossOrigin(origins = "http://localhost:3000")
  @GetMapping(path="")
  public @ResponseBody Iterable<Foo> getAllFoos() {    
    return FooRepository.findAll();
  } 
    
}