package com.code.test.altimetrik.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.code.test.altimetrik.R;
import com.code.test.altimetrik.model.Result;
import com.code.test.altimetrik.utils.Constants;

import java.text.ParseException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM = 0;

    public List<Result> mItemList;

    public AlbumViewAdapter(List<Result> itemList) {
        mItemList = itemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        try {
            populateItemRows((ItemViewHolder) viewHolder, position);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mItemList == null ? 0 : mItemList.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.name)
        TextView name;

        @BindView(R.id.date)
        TextView date;

        @BindView(R.id.price)
        TextView price;

        @BindView(R.id.thumbnail)
        ImageView thumbnail;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private void populateItemRows(ItemViewHolder viewHolder, int position) throws ParseException {
        viewHolder.name.setText(mItemList.get(position).getCollectionCensoredName());

        Glide.with(viewHolder.itemView)
                .load(mItemList.get(position).getArtworkUrl100())
                .centerCrop()
                .placeholder(R.drawable.ic_outline_image_24)
                .into(viewHolder.thumbnail);

        viewHolder.price.setText(String.valueOf(mItemList.get(position).getCollectionPrice()));

        viewHolder.date.setText(Constants.formattedDate(mItemList.get(position).getReleaseDate()));
    }
}