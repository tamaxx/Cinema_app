package com.example.cinema_sensor;

import android.transition.Slide;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class SliderAdapter extends RecyclerView.Adapter<SliderAdapter.SliderViewHolder>{

    private List<SliderItem> sliderItems;
    private ViewPager2 viewPager2;

    private OnItemSelectedListener listener;

    SliderAdapter(OnItemSelectedListener listener, List<SliderItem> sliderItems, ViewPager2 viewPager) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SliderViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.slide_item_container,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull SliderViewHolder holder, int position) {
        SliderItem item = sliderItems.get(position);
        holder.textDesc.setText(item.getGeneros());
        holder.textNome.setText(item.getNome());
        holder.imageView.setImageResource(item.getImage());
        if(position == sliderItems.size() - 2){
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return sliderItems.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private RoundedImageView imageView;
        private TextView textNome;
        private TextView textDesc;

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = itemView.findViewById(R.id.imageSlide);
            textNome = itemView.findViewById(R.id.txt_Nome);
            textDesc = itemView.findViewById(R.id.txt_Desc);
        }

        @Override
        public void onClick(View v) {
            SliderItem selectedItem = sliderItems.get(getAdapterPosition());

            listener.onItemSelected(selectedItem);
        }
    }

    private Runnable runnable = () -> {
        sliderItems.addAll(sliderItems);
        notifyDataSetChanged();
    };

    public interface OnItemSelectedListener {
        void onItemSelected(SliderItem item);
    }
}
