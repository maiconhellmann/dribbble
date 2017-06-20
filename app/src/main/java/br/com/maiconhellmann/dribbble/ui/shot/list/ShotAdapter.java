package br.com.maiconhellmann.dribbble.ui.shot.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.maiconhellmann.dribbble.R;
import br.com.maiconhellmann.dribbble.data.model.Shot;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ShotAdapter extends RecyclerView.Adapter<ShotAdapter.ShotViewHolder> {

    private List<Shot> mShotList;

    private OnItemClickListener onItemClickListener;

    public void addRepositories(List<Shot> shotList) {
        mShotList.addAll(shotList);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener{
        void onItemClick(Shot shot);
    }

    @Inject
    public ShotAdapter() {
        mShotList = new ArrayList<>();
    }

    public void setRepositories(List<Shot> shots) {
        mShotList = shots;
        notifyDataSetChanged();
    }

    @Override
    public ShotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_shot, parent, false);
        return new ShotViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ShotViewHolder holder, int position) {
        final Shot shot = mShotList.get(position);
        holder.titleTextView.setText(shot.getTitle());

        Integer count = shot.getViewsCount() != null ? shot.getViewsCount() : 0;
        holder.viewsTextView.setText(holder.viewsTextView.getContext().getString(R.string.views_count,count));

        Glide.with(holder.imageView.getContext())
                .load(shot.getImages().getNormal())
                .asBitmap()
                .placeholder(R.drawable.placeholder)
                .into(holder.imageView);

        if(onItemClickListener != null) holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(shot);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mShotList.size();
    }

    class ShotViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView) ImageView imageView;
        @BindView(R.id.text_title) TextView titleTextView;
        @BindView(R.id.text_views) TextView viewsTextView;

        public ShotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
