package com.meest.view.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airbnb.lottie.L;
import com.meest.R;
import com.meest.databinding.FragmentGroupBinding;
import com.meest.model.group.GroupChatModel;
import com.meest.view.adapters.chat.GroupAdapter;

import java.util.ArrayList;
import java.util.List;

public class GroupFragment extends Fragment {


    FragmentGroupBinding fragmentGroupBinding;
    GroupAdapter groupAdapter;
    List<GroupChatModel> groupChatModelList;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentGroupBinding =  DataBindingUtil.inflate(inflater,R.layout.fragment_group, container, false);


        GroupChatModel groupChatModel = new GroupChatModel();
        groupChatModel.setGroupIcon("");
        groupChatModel.setGroupName("My Group");
        groupChatModel.setTextMessage("how you doin");
        groupChatModel.setTextTime("15:11");
        groupChatModel.setTextMessageCount("1");
        groupChatModelList = new ArrayList<>();
        groupChatModelList.add(groupChatModel);

        fragmentGroupBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        groupAdapter = new GroupAdapter(getActivity(),groupChatModelList);
        fragmentGroupBinding.setGroupAdapter(groupAdapter);


        return fragmentGroupBinding.getRoot();
    }
}