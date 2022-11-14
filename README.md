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

## Exercise extra

Test the operation of **Dialogs**.

<details>

**<summary>Application Images</summary>**

<img src="resForReadme/mobile.gif">

</details>

### **Code**

<ul>

#### <li>**Java files**

<ul>

<li>

<details>

**<summary>`MainActivity.java`</summary>**

```java
package com.example.dialogs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Attributes
    private Button btnDialog;
    private Button advancedDialog;
    private TextView textViewMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.btnDialog = this.findViewById(R.id.btnDialog);
        this.advancedDialog = this.findViewById(R.id.advancedDialog);
        this.textViewMain = this.findViewById(R.id.textViewMain);


        this.btnDialog.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);


            builder.setTitle("Hello");

            builder.setMessage("Message Alert");

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    textViewMain.setText("You pressed CANCEL");
                }
            });

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    textViewMain.setText("You pressed OK");
                }
            });


            builder.create().show();
        });


        this.advancedDialog.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setView(R.layout.advanced_dialog);


            builder.setMultiChoiceItems(R.array.arrayTest, null, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                }
            });


            builder.setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            });


            builder.create().show();
        });
    }
}
```

</details>

</li>

</ul>

</li>

### <li>**XML files**

<ul>

#### <li>**`layout`**

<ul>

<li>

<details>

**<summary>`activity_main.xml`</summary>**

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">


    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal">


        <Button
            android:id="@+id/btnDialog"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Dialog" />

        <Button
            android:id="@+id/advancedDialog"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginStart="10dp"
            android:text="Advanced dialog" />

    </LinearLayout>


    <TextView
        android:id="@+id/textViewMain"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center"
        android:text="Hello World!" />

</LinearLayout>
```

</details>

</li>

<li>

<details>

**<summary>`advanced_dialog.xml`</summary>**

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:orientation="vertical">

    <ImageView
        android:layout_width="match_parent"
        android:layout_height="64dp"
        android:background="#FFFFBB33"
        android:contentDescription="@string/app_name"
        android:scaleType="center"
        android:src="@drawable/ic_launcher_foreground" />

    <EditText
        android:id="@+id/username"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginLeft="4dp"
        android:layout_marginTop="16dp"
        android:layout_marginRight="4dp"
        android:layout_marginBottom="4dp"
        android:hint="Username"
        android:inputType="textEmailAddress" />

    <EditText
        android:id="@+id/password"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginLeft="4dp"
        android:layout_marginTop="4dp"
        android:layout_marginRight="4dp"
        android:layout_marginBottom="16dp"
        android:fontFamily="sans-serif"
        android:hint="Password"
        android:inputType="textPassword" />
</LinearLayout>
```

</details>

</li>

</ul>

</li>

#### <li>**`values`**

<ul>

<li>

<details>

**<summary>`array_test.xml`</summary>**

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string-array name="arrayTest">
        <item>Item 1</item>
        <item>Item 2</item>
        <item>Item 3</item>
    </string-array>
</resources>
```

</details>

</li>

</ul>

</ul>

</li>

</ul>
