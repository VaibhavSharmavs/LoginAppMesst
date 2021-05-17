package com.meest.viewmodel.chat.chatboatviewmodels;

import com.meest.model.chat.chatmodel.ChatTextModel;

public class ChatTextViewModel {

   public String message;
   public String time;
   public String read;

    public ChatTextViewModel(ChatTextModel chatTextModel) {
        this.chatTextModel = chatTextModel;
        this.message = chatTextModel.getMessage();
    }

    private ChatTextModel chatTextModel;

}
