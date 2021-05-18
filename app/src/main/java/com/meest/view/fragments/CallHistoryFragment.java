package com.meest.view.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meest.R;
import com.meest.databinding.FragmentCallHistoryBinding;
import com.meest.model.call.CallHistoryModel;
import com.meest.view.adapters.chat.CallHistoryAdapter;

import java.util.List;


public class CallHistoryFragment extends Fragment {

    FragmentCallHistoryBinding fragmentCallHistoryBinding;
    List<CallHistoryModel> callHistoryModelList;
    CallHistoryAdapter callHistoryAdapter;



    public CallHistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentCallHistoryBinding =  DataBindingUtil.inflate(inflater,R.layout.fragment_call_history, container, false);


        return fragmentCallHistoryBinding.getRoot();
    }
}