import React from "react";
import "./GameSession.css";
import Question from "./Question.js";
import API_BASE_URL from "./api";

/**
 * handles a set of Questions
 */
class GameSession extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      score: 0,
      index: 0,
      questions: typeof props.questions === "undefined" ? [] : props.questions,
      correct: null,
      categories: [],
      selectedCategory: ""
    };

    this.fetchAnswer = this.fetchAnswer.bind(this);
    this.nextQuestion = this.nextQuestion.bind(this);
    this.handleCategoryChange = this.handleCategoryChange.bind(this);
    this.startQuizForSelectedCategory = this.startQuizForSelectedCategory.bind(this);
  }

  componentDidMount() {
    fetch(API_BASE_URL + "/category")
      .then((response) => response.json())
      .then((data) => {
        const firstCategoryId = data.length > 0 ? String(data[0].id) : "";
        this.setState(
          {
            categories: data,
            selectedCategory: firstCategoryId
          },
          () => {
            if (this.state.questions.length === 0 && this.state.selectedCategory !== "") {
              this.startQuizForSelectedCategory();
            }
          }
        );
      })
      .catch((err) => {
        console.log(err);
      });
  }

  handleCategoryChange(event) {
    this.setState({
      selectedCategory: event.target.value
    });
  }

  startQuizForSelectedCategory() {
    if (!this.state.selectedCategory) {
      return;
    }

    fetch(API_BASE_URL + "/quiz?cat_id=" + this.state.selectedCategory)
      .then((response) => response.json())
      .then((data) =>
        this.setState({
          questions: data,
          index: 0,
          score: 0,
          correct: null
        })
      )
      .catch((err) => {
        console.log(err);
      });
  }

  fetchAnswer = (selectedAnswer) => {
    if (this.state.correct !== null) {
      return;
    }

    const currentQuestion = this.state.questions[this.state.index];
    const correctAnswer = currentQuestion.answers.find(
      (answer) => answer.correct === true
    );

    const isCorrect =
      correctAnswer !== undefined && correctAnswer.id === selectedAnswer.id;

    this.setState({
      correct: isCorrect,
      score: this.state.score + (isCorrect ? 100 : 0)
    });
  };

  nextQuestion() {
    this.setState({
      index: this.state.index + 1,
      correct: null
    });
  }

  render() {
    let feedback = "";
    if (this.state.correct !== null) {
      feedback = (
        <div data-testid="feedback-area">
          <h4 data-testid="feedback-text">{this.state.correct ? "Hurra" : "Ohh nein"}</h4>
          <button data-testid="next-button" onClick={this.nextQuestion}>
            Next
          </button>
        </div>
      );
    }

    let q = "";
    if (this.state.index < this.state.questions.length) {
      q = (
        <Question
          question={this.state.questions[this.state.index].question}
          answers={this.state.questions[this.state.index].answers}
          callback={this.fetchAnswer}
        />
      );
    } else if (this.state.questions.length === 0) {
      q = (
        <div data-testid="error-area">
          <h1>Error fetching questions</h1>
          <h2>Please fix me</h2>
        </div>
      );
    } else {
      q = (
        <div data-testid="quiz-finished">
          <h1>Congrats, you made it!</h1>
          <h2>No more questions</h2>
        </div>
      );
    }

    return (
      <div>
        <div style={{ marginBottom: "1rem" }}>
          <label htmlFor="category-select">Kategorie: </label>
          <select
            id="category-select"
            data-testid="category-select"
            value={this.state.selectedCategory}
            onChange={this.handleCategoryChange}
          >
            {this.state.categories.map((cat) => (
              <option key={cat.id} value={cat.id}>
                {cat.name}
              </option>
            ))}
          </select>

          <button
            data-testid="start-quiz-button"
            style={{ marginLeft: "0.5rem" }}
            onClick={this.startQuizForSelectedCategory}
          >
            Quiz starten
          </button>
        </div>

        <div data-testid="score-text">
          Question: {Math.min(this.state.questions.length, this.state.index + 1)} /{" "}
          {this.state.questions.length} Score: {this.state.score}
        </div>

        {q}

        {feedback}
      </div>
    );
  }
}

export default GameSession;