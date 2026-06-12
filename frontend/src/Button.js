export default function Button(props) {
  return (
    <button
      onClick={props.onClick}
      data-testid={props.testId}
    >
      {props.label}
    </button>
  );
}