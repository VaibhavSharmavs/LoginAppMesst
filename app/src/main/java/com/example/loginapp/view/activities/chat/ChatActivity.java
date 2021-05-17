package com.example.loginapp.view.activities.chat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.loginapp.R;
import com.example.loginapp.view.adapters.ViewPagerApdater;
import com.example.loginapp.view.fragments.CallHistoryFragment;
import com.example.loginapp.view.fragments.ChatListFragment;
import com.example.loginapp.view.fragments.ChatStoryFragment;
import com.example.loginapp.view.fragments.GroupFragment;
import com.example.loginapp.view.fragments.TaskListFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    List<Fragment> mFragments;
    TabLayout tabLayout;
    ViewPager viewPager;

    private int[] tabIconArray = {R.drawable.selected_story,
            R.drawable.selected_chat,
            R.drawable.selected_group,
            R.drawable.selected_call,
            R.drawable.selected_reminder};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);


        mFragments = new ArrayList<>();
        mFragments.add(new ChatStoryFragment());
        mFragments.add(new ChatListFragment());
        mFragments.add(new GroupFragment());
        mFragments.add(new CallHistoryFragment());
        mFragments.add(new TaskListFragment());


        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new ViewPagerApdater(getSupportFragmentManager(), mFragments));


        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setSelectedTabIndicator(0);
        tabLayout.setupWithViewPager(viewPager);
        for (int i = 0; i < tabIconArray.length; i++)
            setupTabIcons(tabLayout, tabIconArray[i], i);


    }

    private void setupTabIcons(TabLayout tabLayout, int drawableIcon, int tabPosition) {
        View view1 = LayoutInflater.from(this).inflate(R.layout.chat_custom_tab, null);
        view1.findViewById(R.id.tabIcon).setBackgroundResource(drawableIcon);

        tabLayout.getTabAt(tabPosition).setCustomView(view1);

//        if (tabPosition <= 2 && tabPosition > 0) {
//            BadgeView badge = new BadgeView(this, view1);
//            badge.setText("1");
//            badge.show();
//        }

    }
}