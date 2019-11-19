package com.example.homework4blackhurst;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextBirdName, editTextZipCode, editTextPersonName;

    Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextBirdName = findViewById(R.id.editTextBirdName);
        editTextZipCode = findViewById(R.id.editTextZipCode);
        editTextPersonName = findViewById(R.id.editTextPersonName);

        buttonSubmit = findViewById(R.id.buttonSubmit);

        buttonSubmit.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.itemReport){

            Toast.makeText(this, "You're Already Here!", Toast.LENGTH_SHORT).show();

        } else if (item.getItemId() == R.id.itemSearch){

            Intent searchIntent = new Intent(this, SearchActivity.class);
            startActivity(searchIntent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("birds");

        if (view == buttonSubmit){

            String createBirdName = editTextBirdName.getText().toString();
            String createPersonName = editTextPersonName.getText().toString();

            String createZipCode = editTextZipCode.getText().toString();


            Bird createBird = new Bird(createBirdName, createZipCode, createPersonName);

            myRef.push().setValue(createBird);





        }





    }
}
