package com.example.loginapp.viewmodel.chat;


import androidx.lifecycle.ViewModel;

import com.example.loginapp.model.chat.ChatStoryListModel;


public class StoryViewModel extends ViewModel  {

    public ChatStoryListModel chatStoryListModel;

    public String name,image ,time;

    public StoryViewModel(ChatStoryListModel chatStoryListModel) {
        this.chatStoryListModel = chatStoryListModel;
        this.name = chatStoryListModel.getName();
        this.image = chatStoryListModel.getImage();
        this.time = chatStoryListModel.getTime();

    }






}