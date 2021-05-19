package com.android.exmaple.miwoknew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;

    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT||focusChange==AudioManager.AUDIOFOCUS_GAIN_TRANSIENT){

                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);
                    }
                    else if(focusChange==AudioManager.AUDIOFOCUS_GAIN){
                        mMediaPlayer.start();


                    }
                    else if(focusChange==AudioManager.AUDIOFOCUS_LOSS){

                        releaseMediaPlayer();
                    }

                }
            };

    private MediaPlayer .OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Word> words = new ArrayList<Word>();

        words.add(new Word("where are you going ?","padata",R.raw.phrase_where_are_you_going));
        words.add(new Word("what is your name","chiwes",R.raw.phrase_what_is_your_name));
        words.add(new Word("my name is","oyyasit",R.raw.phrase_my_name_is));
        words.add(new Word("how are you feeling","michakase",R.raw.phrase_how_are_you_feeling));
        words.add(new Word("i'm feeling good","tanayaka",R.raw.phrase_im_feeling_good));
        words.add(new Word("are you coming","amana",R.raw.phrase_are_you_coming));
        words.add(new Word("yes , i'm coming","haa'yaaa",R.raw.phrase_yes_im_coming));
        words.add(new Word("i'm coming","aanam",R.raw.phrase_im_coming));


        WordAdapter Adapter = new WordAdapter(this,words,R.color.category_phrases);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(Adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Word word =words.get(position);
                releaseMediaPlayer();

                int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener , AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getAudioResource());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
                }

            }
        });



    }

    @Override
    protected void onStop(){
        super.onStop();

        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
        if(mMediaPlayer!=null){

            mMediaPlayer.release();
            mMediaPlayer =null;

            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}