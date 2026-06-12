import React from "react";
import Button from "./Button.js";
import "./Question.css";

class Question extends React.Component {
  render() {
    const buttons = this.props.answers.map((answer, index) => (
      <Button
        label={answer.answer}
        key={answer.id}
        onClick={() => this.props.callback(answer)}
        testId={`answer-button-${index}`}
      />
    ));

    return (
      <div className="question">
        <h2 data-testid="question-text">{this.props.question}</h2>
        <img src="img/question_smiley.png" alt="" width="150px" />
        <hr />
        <div className="button-bar">
          {buttons}
        </div>
      </div>
    );
  }
}

export default Question;