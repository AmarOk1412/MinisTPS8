package com.example.amarok.tp1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends Activity {
    private static final String TAG = "CreateActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        final Button createBtn = (Button) findViewById(R.id.createBtn);
        createBtn.setEnabled(false);
        final EditText titleTextEdit = (EditText) findViewById(R.id.editTitle);
        titleTextEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                createBtn.setEnabled(!titleTextEdit.getText().equals(""));
            }
        });
    }

    public void createButtonClicked(View view) {
        Log.i(TAG, "Create button clicked");
        Context context = getApplicationContext();
        CharSequence text = "Create clicked";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();


        Intent data = new Intent();
        EditText title = (EditText) findViewById(R.id.editTitle);
        data.putExtra("title", title.getText());
        if (getParent() == null) {
            setResult(Activity.RESULT_OK, data);
        } else {
            getParent().setResult(Activity.RESULT_OK, data);
        }
        finish();
    }
}
