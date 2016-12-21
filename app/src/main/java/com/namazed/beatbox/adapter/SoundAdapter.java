package com.namazed.beatbox.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.namazed.beatbox.BeatBox;
import com.namazed.beatbox.R;
import com.namazed.beatbox.Sound;

import java.util.List;


public class SoundAdapter extends RecyclerView.Adapter<SoundAdapter.SoundHolder> {

    private final BeatBox beatBox;
    private List<Sound> sounds;

    public SoundAdapter(List<Sound> sounds, BeatBox beatBox) {
        this.sounds = sounds;
        this.beatBox = beatBox;
    }

    @Override
    public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_sound, parent, false);

        return new SoundHolder(view);
    }

    @Override
    public void onBindViewHolder(SoundHolder holder, int position) {
        Sound sound = sounds.get(position);
        holder.bindSound(sound);
    }

    @Override
    public int getItemCount() {
        return sounds.size();
    }

    public class SoundHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Button soundButton;
        private Sound sound;

        public SoundHolder(View itemView) {
            super(itemView);
            soundButton = (Button) itemView.findViewById(R.id.list_item_sound_button);
            soundButton.setOnClickListener(this);
        }

        public void bindSound(Sound sound) {
            this.sound = sound;
            soundButton.setText(this.sound.getName());
        }

        @Override
        public void onClick(View view) {
            beatBox.play(sound);
        }
    }
}
