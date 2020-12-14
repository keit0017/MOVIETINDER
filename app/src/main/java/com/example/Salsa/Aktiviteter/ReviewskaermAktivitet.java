package com.example.Salsa.Aktiviteter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.Salsa.R;
import com.example.Salsa.model.ChatmessageModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.SnapshotParser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.example.Salsa.controllere.ChatsAdapter;

public class ReviewskaermAktivitet extends AppCompatActivity {

    TextView titleMOv, descritption;
    Uri image;
    ImageView movieposter,finishBUtton;
    Button sendtext;
    RecyclerView mainchat;
    EditText typedText;
    private ArrayList<ChatmessageModel> chats;
    private DatabaseReference mDatabaseRef2,mdatabasref3;
    private ChatsAdapter mChatAdapter;
    private LinearLayoutManager linearLayoutManager;
    private FirebaseRecyclerAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviewskaerm_aktivitet);

        Intent data = getIntent();
        final String title = data.getStringExtra("cmTitle");
        String name = data.getStringExtra("cmDesc");
        String simage = data.getStringExtra("cmImg");
        mChatAdapter = new ChatsAdapter(this,chats);
        chats=new ArrayList<>();


        image= Uri.parse(simage);


        titleMOv = (TextView) findViewById(R.id.Titleyo);
        descritption= (TextView) findViewById(R.id.mDesc);
        movieposter =(ImageView) findViewById(R.id.Postr);
        finishBUtton = (ImageView) findViewById(R.id.ibackimage);
        sendtext= (Button) findViewById(R.id.Sendchat);
        typedText= (EditText) findViewById(R.id.editTextChattext);

        //sætter billeder og tekst
        titleMOv.setText(title);
        descritption.setText(name);
        Picasso.with(this).load(simage)
                .fit().into(movieposter);


        //recyclerviewrelated
        mainchat=(RecyclerView) findViewById(R.id.recyclerViewChat);
        mainchat.hasFixedSize();
        mainchat.setLayoutManager(new LinearLayoutManager(this));
        getChats(title);



        finishBUtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sendtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String message = typedText.getText().toString();
                Uploadmesaage(message, title);
            }
        });

        //prøver at hente ting
        typedText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    final String message = typedText.getText().toString();
                    Uploadmesaage(message, title);
                    return true;
                }
                return false;
            }
        });

        mdatabasref3= FirebaseDatabase.getInstance().getReference("userChats/"+title);


    }

    void Uploadmesaage( String mMessage, String movieTitle ){
        Date currentTime = Calendar.getInstance().getTime();
        String mUsername = getusername();
        String timenow= currentTime.toString();
        ChatmessageModel chatmessageModel =new ChatmessageModel();

        chatmessageModel.setmMessage(mMessage);
        chatmessageModel.setmTimeOfMessage(timenow);
        chatmessageModel.setMusername(mUsername);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("posts/"+movieTitle).push();
        Map<String, Object> map = new HashMap<>();
        map.put("user", chatmessageModel.getMusername());
        map.put("text", chatmessageModel.getmMessage());
        map.put("timestamp", chatmessageModel.getmTimeOfMessage());
        databaseReference.setValue(map);

    }

    String getusername(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String userID = user.getEmail();
        String username = userID.split("@")[0];
        return username;
    }


    //henter chatten for filment

    private void getChats(String films){
        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("posts/"+films);

        FirebaseRecyclerOptions<ChatmessageModel> options =
                new FirebaseRecyclerOptions.Builder<ChatmessageModel>()
                        .setQuery(query, new SnapshotParser<ChatmessageModel>() {
                            @NonNull
                            @Override
                            public ChatmessageModel parseSnapshot(@NonNull DataSnapshot snapshot) {
                                return new ChatmessageModel(snapshot.child("user").getValue().toString(),
                                        snapshot.child("text").getValue().toString(),
                                        snapshot.child("timestamp").getValue().toString());
                            }
                        })
                        .build();

        adapter = new FirebaseRecyclerAdapter<ChatmessageModel, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull ChatmessageModel chatmessageModel) {
                viewHolder.setTxtTitle(chatmessageModel.getMusername());
                viewHolder.setTxtDesc(chatmessageModel.getmMessage());
                String time =chatmessageModel.getmTimeOfMessage().toString();
                viewHolder.setTxtTime(time);

            }

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.chatlayout, parent, false);
                return new ViewHolder(view);
            }
        };
        mainchat.setAdapter(adapter);
}

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout root;
    public TextView txtUser;
    public TextView txtDesc;
    public TextView txtTime;


    public ViewHolder(View itemView) {
        super(itemView);
        root = itemView.findViewById(R.id.list_root);
        txtUser = itemView.findViewById(R.id.mUsernameText2);
        txtDesc = itemView.findViewById(R.id.mCommentText2);
        txtTime = itemView.findViewById(R.id.mDatetask2);;
    }
    public void setTxtTitle(String string) {
        txtUser.setText(string);
    }
    public void setTxtDesc(String string) {
        txtDesc.setText(string);
    }

    public void setTxtTime(String txtTime2) {
        txtTime.setText(txtTime2);;
    }}


}