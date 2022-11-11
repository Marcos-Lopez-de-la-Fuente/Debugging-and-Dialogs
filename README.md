# Exercices-Debugging

## GeoQuiz 1

### Locate error

Android Studio throws an error indicating an error in the `updateQuestion()` method of the Activity `QuizActivity.java`, the error indicates that we are trying to edit the values ​​of the `mQuestionTextView` attribute without having created the value of this attribute:

`java.lang.NullPointerException: Attempt to invoke virtual method 'void android.widget.TextView.setText(int)' on a null object reference`

```java
private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question); // Line where the error is displayed
}
```

### Fix Error

To fix this error, simply put the value of the `mQuestionTextView` attribute before the `setText()` method, in my case I'll do it in the `onCreate()` method to make sure there can't be any errors:

```java
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_quiz);

    this.mQuestionTextView = this.findViewById(R.id.question_text_view);

    {...}
}
```

## GeoQuiz 2

### Locate error

The error occurs when the "Next" button is pressed, after pressing this button the next question should be displayed, however when pressing the button nothing happens.

After analyzing the code, we see that the `setOnClickListener` method of the button in question should have the `updateQuestion()` method inside it, but it doesn't appear here.

```java
mNextButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        mIsCheater = false;
    }
});
```

### Fix error

To fix this error, simply call the `updateQuestion()` method inside the overridden `onClick()` method, as shown in the following example:

```java
mNextButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
        mIsCheater = false;

        updateQuestion();
    }
});
```
