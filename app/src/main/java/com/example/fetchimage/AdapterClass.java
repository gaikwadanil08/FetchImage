package com.example.fetchimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolder> {

    private List<ModelClass> list;
    private Context context;

    public AdapterClass(List<ModelClass> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.modelclass, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        Bitmap bmp = null;

        final ModelClass listItem = list.get(i);

        viewHolder.tv_imageName.setText(listItem.getName());

        URL url = null;
        try {
            url = new URL("http://mdconstructionpune.com/AndriodPHP/Images/cat1.jpg");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            viewHolder.imageView.setImageBitmap(bmp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*Glide.with(context)
                .load(listItem.getImage())
                .into(viewHolder.imageView);*/
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_imageName;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_imageName = itemView.findViewById(R.id.tv_imageName);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
