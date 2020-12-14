package com.example.Salsa.model;

public class ChatmessageModel {

    String musername,mMessage,mTimeOfMessage;

    public ChatmessageModel(){

    }

    public ChatmessageModel(String username, String message, String timeOfMessage){

        if(username.trim().equals("")){
            username="NO TITLE";
        }
        if(message.trim().equals("")){
            message="NO MESSAGE";
        }
        if(message.trim().equals("")){
            message="Notime";
        }

        musername=username;
        mMessage=message;
        mTimeOfMessage=timeOfMessage;

    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public void setmTimeOfMessage(String mTimeOfMessage) {
        this.mTimeOfMessage = mTimeOfMessage;
    }

    public void setMusername(String musername) {
        this.musername = musername;
    }


    public String getmMessage() {
        return mMessage;
    }

    public String getmTimeOfMessage() {
        return mTimeOfMessage;
    }

    public String getMusername() {
        return musername;
    }
}
