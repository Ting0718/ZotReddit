package com.example.zotreddit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import adapters.MessageAdapter;

public class MainActivity extends AppCompatActivity {

    private static String currentUser;
    private TextView tvUsername;
    private Button btnPost;
    private TextView tvPost;
    MessageAdapter messageAdapter;

    List<Message> messages;

    DatabaseReference databaseReference;

    @Override
    protected void onStart() {
        super.onStart();
        final RecyclerView rvMessages = findViewById(R.id.rvMessage);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                messages.clear();
                for (DataSnapshot messageDataSnapshot: dataSnapshot.getChildren()) {
                    Message message = messageDataSnapshot.getValue(Message.class);
                    messages.add(message);
                }
                MessageAdapter messageAdapter = new MessageAdapter(MainActivity.this, messages);
                rvMessages.setAdapter(messageAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rvMessages = findViewById(R.id.rvMessage);
        messages = new ArrayList<>();

        tvUsername = findViewById(R.id.tvUsername);
        btnPost = findViewById(R.id.btnPost);
        tvPost = findViewById(R.id.tvPost);


        databaseReference = FirebaseDatabase.getInstance().getReference("message");


        // message adapter
        messageAdapter = new MessageAdapter(this, messages);
        rvMessages.setAdapter(messageAdapter);
        rvMessages.setLayoutManager(new LinearLayoutManager(this));


        Bundle extras = getIntent().getExtras();
        if (extras != null)
        {
            String username = extras.getString("user");
            tvUsername.setText(username);
            currentUser = username;
        }


        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = tvUsername.getText().toString();
                String post = tvPost.getText().toString();
                int initial_upvote = 0;

                if (!post.isEmpty())
                {
                    String key = databaseReference.push().getKey();

                    Message message = new Message(post,username,initial_upvote, key);

                    messages.add(message);

                    databaseReference.child(key).setValue(message);
                }
                else
                {
                    tvPost.setError("the Post content cannot be empty");
                }
            }
        });

    }

    public static String currentUser() {
        return currentUser;
    }
}
