package com.example.loginapp.view.activities.chat.components;

import android.Manifest;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.media.MediaRecorder;
import android.media.SoundPool;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.RequiresApi;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;

import com.example.loginapp.R;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class RecordButton extends AppCompatImageView implements View.OnTouchListener {


    private ScaleAnim scaleAnim;
    private boolean listenForRecord = true;
    private int commId;
    private MediaRecorder recorder = null;
    private boolean isRecording;

    private static SoundPool soundPool = null;
    private static int soundStart,soundEnd;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RecordButton(Context context, int id) {
        super(context);
        init(context, null);
        commId=id;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RecordButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RecordButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RecordButton);

            int imageResource = typedArray.getResourceId(R.styleable.RecordButton_mic_icon, -1);

            if (imageResource != -1) {
                setTheImageResource(imageResource);
            }

            typedArray.recycle();
        }

        if (soundPool==null){
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(5)
                    .build();
            //these are just two wav files with short intro and exit sounds
            soundStart=soundPool.load(getContext(),R.raw.sound,0);
            soundEnd=soundPool.load(getContext(),R.raw.sound,0);
        }

        scaleAnim = new ScaleAnim(this);
        this.setOnTouchListener(this);
    }

    private void setTheImageResource(int imageResource) {
        Drawable image = AppCompatResources.getDrawable(getContext(), imageResource);
        setImageDrawable(image);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setClip(this);
    }

    public void setClip(View v) {
        if (v.getParent() == null) {
            return;
        }

        if (v instanceof ViewGroup) {
            ((ViewGroup) v).setClipChildren(false);
            ((ViewGroup) v).setClipToPadding(false);
        }

        if (v.getParent() instanceof View) {
            setClip((View) v.getParent());
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (isListenForRecord()) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    ((RecordButton) v).startScale();

                    // soundPool.play(soundStart,1,1,0,0,1);
                    if(permissionsAvailable(permissionsRecord)) {

                        startRecording();
                    }else{
                        ActivityCompat.requestPermissions((Activity) getContext(), permissionsRecord, 999);
                    }
                    break;

                case MotionEvent.ACTION_UP:
                    stopRecording();
                    //  soundPool.play(soundEnd,1,1,0,0,1);
                    ((RecordButton) v).stopScale();
                    break;
            }

        }
        return isListenForRecord();
    }

    protected String[] permissionsRecord = {Manifest.permission.VIBRATE, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

    protected boolean permissionsAvailable(String[] permissions) {
        boolean granted = true;
        for (String permission : permissions) {
            if (ActivityCompat.checkSelfPermission(getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                granted = false;
                break;
            }
        }
        return granted;
    }


    File file;
    //audio recording tools
    private void startRecording() {

        if (isRecording){
            stopRecording();
        }
        String fileName=getContext().getExternalCacheDir().getAbsolutePath()+"/"+commId+"_"+new Date().getTime()+".amr";
        file=new File(fileName);
        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_WB);
        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);
        recorder.setAudioSamplingRate(8000);
        recorder.setAudioChannels(1);
        recorder.setAudioEncodingBitRate(12000);
        try {
            recorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        recorder.start();

        startRecordListener.onStartRecord();

        setIsRecording(true);
    }
/*    try {
        mRecorder.stop();
    }
     catch(RuntimeException e) {
    }
      finally {
        mRecorder.release();
        mRecorder = null;
    }*/

    private void stopRecording() {
        if (isRecording) {
            try {

                recorder.stop();
            }
            catch (RuntimeException e)
            {

            }
            finally {
                recorder.release();
                setIsRecording(false);
                recordingFinishedListener.onRecordingFinished(file);
                recorder = null;
            }

        }
    }

    private void setIsRecording(boolean isRecording){
        this.isRecording=isRecording;
    }


    protected void startScale() {
        scaleAnim.start();
    }

    protected void stopScale() {
        scaleAnim.stop();
    }

    public void setListenForRecord(boolean listenForRecord) {
        this.listenForRecord = listenForRecord;

    }

    public boolean isListenForRecord() {
        return listenForRecord;
    }

    public interface StartRecordListener{
        void onStartRecord();
    }
    StartRecordListener startRecordListener;
    public void setOnStartRecord(StartRecordListener startRecordListener){
        this.startRecordListener=startRecordListener;
    }

    //callback for when a recording has been made
    public interface RecordingFinishedListener{
        void onRecordingFinished(File file);
    }

    RecordingFinishedListener recordingFinishedListener;

    public void setRecordingFinishedListener(RecordingFinishedListener recordingFinishedListener) {
        this.recordingFinishedListener = recordingFinishedListener;
    }



    public class ScaleAnim {
        private View view;
        public ScaleAnim(View view) {
            this.view = view;
        }

        void start() {
            AnimatorSet set = new AnimatorSet();
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.3f);
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.3f);
            set.setDuration(150);
            set.setInterpolator(new AccelerateDecelerateInterpolator());
            set.playTogether(scaleY, scaleX);
            set.start();
        }

        void stop() {
            AnimatorSet set = new AnimatorSet();
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.0f);
            //        scaleY.setDuration(250);
            //        scaleY.setInterpolator(new DecelerateInterpolator());

            ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.0f);
            //        scaleX.setDuration(250);
            //        scaleX.setInterpolator(new DecelerateInterpolator());

            set.setDuration(150);
            set.setInterpolator(new AccelerateDecelerateInterpolator());
            set.playTogether(scaleY, scaleX);
            set.start();
        }
    }



}
