package com.android.exmaple.miwoknew;

public class Word {

     private String mDefaultTranslation;
     private String mMiwokTranslation;
     private int mImageResourceId = NO_IMAGR_PROVIDED;

     private int mAudioResourceID;

     private static final int NO_IMAGR_PROVIDED =-1;


    public Word(String mDefaultTranslation,String mMiwokTranslation,int audioResource){

        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mAudioResourceID = audioResource;

    }


    public Word(String mDefaultTranslation,String mMiwokTranslation,int imageResourceId,int audioResource){

        this.mDefaultTranslation = mDefaultTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
        this.mImageResourceId = imageResourceId;
        this.mAudioResourceID = audioResource;

    }


    public String getDefaultTranslation(){

        return mDefaultTranslation;
    }

    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }


    public  int getImageResourceId(){
        return mImageResourceId;
    }

    public boolean hasImage(){
       return mImageResourceId!=NO_IMAGR_PROVIDED;

    }
    public int getAudioResource(){
        return mAudioResourceID;
    }





}
