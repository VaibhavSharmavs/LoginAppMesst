package com.meest.viewmodel.chat;

import androidx.lifecycle.ViewModel;

import com.example.loginapp.model.chat.ChatListModel;

public class ChatViewModel extends ViewModel {

    public String username,image,msg,time,msgCount;

    public ChatListModel chatListModel;



    public ChatViewModel(ChatListModel chatListModel) {

        this.chatListModel = chatListModel;
        this.username = chatListModel.getUsername();
        this.msg = chatListModel.getMsg();
        this.time = chatListModel.getTime();
        this.msgCount = chatListModel.getMsgCount();
    }
}
