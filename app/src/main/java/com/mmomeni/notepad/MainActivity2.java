package com.mmomeni.notepad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {
    private EditText titleB;
    private EditText descB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        titleB = findViewById(R.id.titleBox);
        descB = findViewById(R.id.DescBox);

        descB.setMovementMethod(new ScrollingMovementMethod());
        descB.setTextIsSelectable(true);

        Intent intent = getIntent();
        String text1 = intent.getStringExtra("title");
        String text2 = intent.getStringExtra("description");
        titleB.setText(text1);
        descB.setText(text2);

    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu){ //this is the only code we have for menues
        //the menu we pass here is the actual menu we have made in layout
        //inflating means to build live objects
        getMenuInflater().inflate(R.menu.second_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()){
            case R.id.menu_save:
                saveAndExit(); // This closes the current activity, returning us to the original activity
                // Toast.makeText(this, "hahaha", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void saveAndExit (){
        Intent data = new Intent();
        data.putExtra("title", titleB.getText().toString());
        data.putExtra("description", descB.getText().toString());
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Save data");
        builder.setMessage("Do you want to save this data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                saveAndExit();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}