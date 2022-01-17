package com.example.wesold;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Sub2Activity extends AppCompatActivity {
    private DatabaseReference m_Database;
    EditText m_edittext1;
    Button m_button1;
    ListView m_listview;
    int m_nCount = 0;
    ArrayList<String> m_arritems;
    ArrayAdapter m_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailmain);

        m_Database = FirebaseDatabase.getInstance().getReference();
        m_edittext1 = findViewById(R.id.edit1);
        m_button1 = findViewById(R.id.button1);
        m_listview =findViewById(R.id.listview1);

        m_arritems = new ArrayList<String>() ;
        m_adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, m_arritems);


        m_listview.setAdapter(m_adapter);

        readUser();

        m_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data1 = m_edittext1.getText().toString();

                m_Database.child("users").child("Data"+m_nCount).child("username").setValue(data1)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(Sub2Activity.this, "저장 성공.", Toast.LENGTH_SHORT).show();
                                readUser();
                                m_edittext1.setText("");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Sub2Activity.this, "저장 실패.", Toast.LENGTH_SHORT).show();
                            }
                        });

            }

        });

        Button imageButton = (Button) findViewById(R.id.button_naver);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NaverMap.class);
                startActivity(intent);
            }
        });


    }


    private void readUser(){
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                m_arritems.clear();
                m_nCount = (int)dataSnapshot.getChildrenCount();
                String name;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    if(postSnapshot.child("username").getValue(String.class) != null) {
                        name = postSnapshot.child("username").getValue(String.class);
                        m_arritems.add(name);
                    }
                }
                m_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("FirebaseDatabase","onCancelled", databaseError.toException());
            }
        };
        Query sortbyAge = m_Database.child("users").orderByChild("users");
        sortbyAge.addListenerForSingleValueEvent(postListener);

    }
}