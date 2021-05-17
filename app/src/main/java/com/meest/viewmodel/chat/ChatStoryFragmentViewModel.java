package com.example.loginapp.viewmodel.chat;

import android.view.View;

import androidx.lifecycle.ViewModel;

import com.example.loginapp.model.chat.ChatStoryFragmentModel;



public class ChatStoryFragmentViewModel extends ViewModel {

    public ChatStoryFragmentModel chatStoryFragmentModel;
    public String userStatusImage,userStatusTime;



    public ChatStoryFragmentViewModel(ChatStoryFragmentModel chatStoryFragmentModel) {
        this.chatStoryFragmentModel = chatStoryFragmentModel;
        getOwnUserStories();
        this.userStatusTime = chatStoryFragmentModel.getUserStatusTime();
        this.userStatusImage = chatStoryFragmentModel.getUserStatusImage();
    }




    private void getOwnUserStories() {

        chatStoryFragmentModel.setUserStatusImage("");
        chatStoryFragmentModel.setUserStatusTime("12:17");


    }

    public void onAddStoryClick(){

    }
}
