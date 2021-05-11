package com.example.loginapp.model.signup.components;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;

import com.example.loginapp.R;

public class GenderLayout {

    RelativeLayout otherback, maleback, femaleback;
    TextView tvMale, tvFemale, tvOther;
    ImageView other, male, female;
    Button submitGender;
    String selectedGender = "";

    public void  showGender(Context context, MutableLiveData<String> gender,View view) {
        final Dialog genderDialog = new Dialog(view.getContext());
        genderDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        genderDialog.setContentView(R.layout.dialog_gender);
        genderDialog.setCancelable(false);
        genderDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        genderDialog.show();


        otherback = genderDialog.findViewById(R.id.otherback);
        maleback = genderDialog.findViewById(R.id.maleBack);
        femaleback = genderDialog.findViewById(R.id.femaleback);
        female = genderDialog.findViewById(R.id.female);
        male = genderDialog.findViewById(R.id.male);
        other = genderDialog.findViewById(R.id.other);
        tvFemale = genderDialog.findViewById(R.id.tvFemale);
        tvMale = genderDialog.findViewById(R.id.tvMale);
        tvOther = genderDialog.findViewById(R.id.tvOther);
        submitGender = genderDialog.findViewById(R.id.selectGender);
//        --------------------------male click relative layout
        maleback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maleback.setBackgroundResource(R.drawable.ic_selected_boundary);
                femaleback.setBackgroundResource(R.drawable.ic_unselected_boundary);
                otherback.setBackgroundResource(R.drawable.ic_unselected_boundary);
//                -------------------------------for image------------------------------------
                male.setImageResource(R.drawable.ic_male_color);
                female.setImageResource(R.drawable.ic_female_grey);
                other.setImageResource(R.drawable.ic_not_specify_grey);
//                ---------------------------------for text----------------------------------
                tvMale.setTextColor(ContextCompat.getColor(context, R.color.second_color));
                tvFemale.setTextColor(ContextCompat.getColor(context, R.color.unselectedColor));
                tvOther.setTextColor(ContextCompat.getColor(context, R.color.unselectedColor));
                selectedGender = "Male";
            }
        });
        //        --------------------------female click relative layout
        femaleback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maleback.setBackgroundResource(R.drawable.ic_unselected_boundary);
                femaleback.setBackgroundResource(R.drawable.ic_selected_boundary);
                otherback.setBackgroundResource(R.drawable.ic_unselected_boundary);
//                -------------------------------for image------------------------------------
                male.setImageResource(R.drawable.ic_male_grey);
                female.setImageResource(R.drawable.ic_female_color);
                other.setImageResource(R.drawable.ic_not_specify_grey);
//                ---------------------------------for text----------------------------------
                tvMale.setTextColor(ContextCompat.getColor(context, R.color.unselectedColor));
                tvFemale.setTextColor(ContextCompat.getColor(context, R.color.second_color));
                tvOther.setTextColor(ContextCompat.getColor(context, R.color.unselectedColor));
                selectedGender = "Female";
            }
        });
        //        --------------------------other click relative layout
        otherback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maleback.setBackgroundResource(R.drawable.ic_unselected_boundary);
                femaleback.setBackgroundResource(R.drawable.ic_unselected_boundary);
                otherback.setBackgroundResource(R.drawable.ic_selected_boundary);
//                -------------------------------for image------------------------------------
                male.setImageResource(R.drawable.ic_male_grey);
                female.setImageResource(R.drawable.ic_female_grey);
                other.setImageResource(R.drawable.ic_not_specify_color);
//                ---------------------------------for text----------------------------------
                tvMale.setTextColor(ContextCompat.getColor(context, R.color.unselectedColor));
                tvFemale.setTextColor(ContextCompat.getColor(context, R.color.unselectedColor));
                tvOther.setTextColor(ContextCompat.getColor(context, R.color.second_color));
                selectedGender = "Prefer not to disclose";
            }
        });
        submitGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedGender.length()>1) {

                    gender.setValue(selectedGender);
                    genderDialog.dismiss();
                }else {
                    Toast.makeText(context, "Please Select Gender", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
