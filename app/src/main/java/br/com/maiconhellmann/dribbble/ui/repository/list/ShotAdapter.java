package br.com.maiconhellmann.dribbble.ui.repository.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    public interface OnItemClickListener{
        void onItemClick(Shot shot);
    }

    @Inject
    public ShotAdapter() {
        mShotList = new ArrayList<>();
    }

    public void setRepositories(List<Shot> shots) {
        mShotList = shots;
    }

    @Override
    public ShotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_repo, parent, false);
        return new ShotViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ShotViewHolder holder, int position) {
        final Shot shot = mShotList.get(position);
        holder.nameTextView.setText(shot.getTitle());
        holder.emailTextView.setText(shot.getDescription());


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

        @BindView(R.id.image_view_type) View hexColorView;
        @BindView(R.id.text_name) TextView nameTextView;
        @BindView(R.id.text_owner) TextView emailTextView;

        public ShotViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
