package com.example.zotreddit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity.java";

    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    private Button btnLogOut;

    //Intent intent = getIntent();
    //String username = intent.getStringExtra(LoginActivity.username);

    private TextView tvUsername;
    private TextView tvMessage;
    private TextView tvUpvotes;
    private Button btnUpvote;
    private Button btnPost;

    List<Message> messages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messages = new ArrayList<>();

        tvUsername = findViewById(R.id.tvUsername);
        tvMessage = findViewById(R.id.tvMessage);
        tvUpvotes = findViewById(R.id.tvUpvotes);
        btnUpvote = findViewById(R.id.btnUpvote);
        btnPost = findViewById(R.id.btnPost);

        btnLogOut = findViewById(R.id.btnLogout);


        // adapter view
        RecyclerView rvMessages = findViewById(R.id.rvMessage);

        final MessageAdapter messageAdapter = new MessageAdapter(this, messages);

        rvMessages.setAdapter(messageAdapter);

        rvMessages.setLayoutManager(new LinearLayoutManager(this));

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String post = tvMessage.getText().toString();
                //tvUsername.setText(username);
                String user = tvUsername.getText().toString();

                Message message = new Message(post, user, 0);

                if (! post.isEmpty())
                {
                    messages.add(message);
                    messageAdapter.notifyDataSetChanged();
                    Log.i(TAG, "Post is composed successfully");
                }
            }
        });


        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });


    }
}
