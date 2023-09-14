package com.amni.pica;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.zip.Inflater;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {


    private List<ImageItem> imageList;
    private Context context;
    private ImageAdapter.OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ImageItem item);
    }

    public ImageAdapter(Context context, List<ImageItem> imageList, OnItemClickListener listener) {
        this.context = context;
        this.imageList = imageList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_image,
                parent, false);


        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder holder, int position) {

        ImageItem imageItem = imageList.get(position);

        // Load the image using a library like Picasso or Glide
        Glide.with(context)
                .load(imageItem.getImageUrl())
                .into(holder.img);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(imageItem);
            }
        });

    }


    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.demoimg);
        }
    }
}
