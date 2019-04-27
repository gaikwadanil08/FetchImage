package com.example.fetchimage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

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
        final ModelClass listItem = list.get(i);

        viewHolder.tv_imageName.setText(listItem.getName());
//        Glide.with(context)
//                .load(listItem.getImage())
//                .into(viewHolder.imageView);

        Glide.with(context)
                .load(list.get(i)
                        .getImage()).apply((RequestOptions) list).listener(
                new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                Glide.with(context)
                                        .load(list.get(i)
                                                .getImage())
                                        .into(viewHolder.imageView);
                            }

                        });
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean
                            isFirstResource) {
                        return false;
                    }
                }).into(viewHolder.imageView);
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
