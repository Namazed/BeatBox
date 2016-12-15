package com.namazed.beatbox;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class SoundAdapter extends RecyclerView.Adapter<SoundAdapter.SoundHolder> {

    @Override
    public SoundHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_sound, parent, false);

        return new SoundHolder(view);
    }

    @Override
    public void onBindViewHolder(SoundHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SoundHolder extends RecyclerView.ViewHolder {
        private Button soundButton;

        public SoundHolder(View itemView) {
            super(itemView);

            soundButton = (Button) itemView.findViewById(R.id.list_item_sound_button);
        }
    }
}
