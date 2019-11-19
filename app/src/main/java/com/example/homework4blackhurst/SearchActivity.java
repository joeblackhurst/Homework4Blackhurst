package com.example.homework4blackhurst;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editTextFindBird;

    Button buttonSearch;

    TextView textViewShowBird;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        editTextFindBird = findViewById(R.id.editTextFindBird);

        buttonSearch = findViewById(R.id.buttonSearch);

        textViewShowBird = findViewById(R.id.textViewShowBird);

        buttonSearch.setOnClickListener(this);


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

            Intent reportIntent = new Intent(this, MainActivity.class);
            startActivity(reportIntent);

        } else if (item.getItemId() == R.id.itemSearch){

            Toast.makeText(this, "You're Already Here!", Toast.LENGTH_SHORT).show();


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("birds");

        if (view == buttonSearch){

            String findBird = editTextFindBird.getText().toString();

            myRef.orderByChild("zipCode").equalTo(findBird).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                  Bird foundBird = dataSnapshot.getValue(Bird.class);
                  String findBirdName = foundBird.birdName;
                  String findZipCode = foundBird.zipCode;
                  String findPersonName = foundBird.personName;

                  textViewShowBird.setText(findBirdName + " found by " + findPersonName);

                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });




        }

    }
}
