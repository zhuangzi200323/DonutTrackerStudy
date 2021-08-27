package com.jack.donuttrackerstudy.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jack.donuttrackerstudy.R;
import com.jack.donuttrackerstudy.databinding.DonutItemBinding;
import com.jack.donuttrackerstudy.listener.ItemClickListener;
import com.jack.donuttrackerstudy.model.Donut;

import java.util.ArrayList;
import java.util.List;

public class DonutListAdapter extends RecyclerView.Adapter<DonutListAdapter.DonutListViewHolder> {
    List<Donut> items = new ArrayList<>();
    ItemClickListener itemClickListener;
    public DonutListAdapter(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public DonutListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DonutListViewHolder(DonutItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(@NonNull DonutListViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    public void updateData(List<Donut> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    class DonutListViewHolder extends RecyclerView.ViewHolder {
        private Donut donut;
        DonutItemBinding binding;

        public DonutListViewHolder(DonutItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(Donut donut) {
            binding.name.setText(donut.name);
            binding.description.setText(donut.description);
            binding.rating.setText(donut.rating + "");
            binding.thumbnail.setImageResource(R.drawable.donut_with_sprinkles);
            this.donut = donut;
            binding.deleteButton.setOnClickListener(view-> {
                if (itemClickListener != null) {
                    itemClickListener.delete(donut);
                }
            });
            binding.getRoot().setOnClickListener(view-> {
                if (itemClickListener != null) {
                    itemClickListener.edit(donut);
                }
            });
        }
    }
}
