package com.namazed.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;

    private AssetManager assets;
    private List<Sound> sounds = new ArrayList<>();
    private SoundPool soundPool;

    public BeatBox(Context context) {
        assets = context.getAssets();
        // TODO: 21.12.2016 конструктор для API ниже 21, выше нужен SoundPool.Builder
        soundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
//        SoundPool.Builder builder = new SoundPool.Builder();
//        builder.setMaxStreams(MAX_SOUNDS)
//                .setAudioAttributes()
//                .build();

        loadSounds();
    }

    public void play(Sound sound) {
        Integer soundId = sound.getSoundId();
        if (soundId == null) {
            return;
        }
        soundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);
    }

    public List<Sound> getSounds() {
        return sounds;
    }

    public void release() {
        soundPool.release();
    }

    private void loadSounds() {
        String[] soundNames;
        try {
            soundNames = assets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found " + soundNames.length + " sounds");
        } catch (IOException e) {
            Log.e(TAG, "Could not list assets", e);
            return;
        }

        for (String filename : soundNames) {
            try {
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                sounds.add(sound);
            } catch (IOException e) {
                Log.e(TAG, "Could not load sound " + filename, e);
            }
        }
    }

    private void load(Sound sound) throws IOException {
        AssetFileDescriptor assetFileDescriptor = assets.openFd(sound.getAssetPath());
        int soundId = soundPool.load(assetFileDescriptor, 1);
        sound.setSoundId(soundId);
    }
}
