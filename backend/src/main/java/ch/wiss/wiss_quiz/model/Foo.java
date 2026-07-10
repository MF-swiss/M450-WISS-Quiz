package ch.wiss.wiss_quiz.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "foo")
public class Foo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	public class Foo {

  		private static Foo foo;
  		public Foo getInstance() {
    		if(foo==null) {
      			foo = new Foo();
    	}
    	return foo;
  	}
}

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFoo() {
		return Foo;
	}

	public void setFoo(String foo) {
		this.Foo = foo;
	}

	public void setCategory(Category cat) {
		this.category = cat;
	}

	public Category getCategory() {
		return category;
	}

	@OneToMany(mappedBy = "foo", cascade = CascadeType.ALL)
	private List<Answer> answers = new ArrayList<>();

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> foo) {
		this.answers = foo;
	}

}
