package br.com.maiconhellmann.dribbble.ui.repository.detail;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.maiconhellmann.dribbble.R;
import br.com.maiconhellmann.dribbble.data.model.Pull;
import butterknife.BindView;
import butterknife.ButterKnife;

public class PullRequestAdapter extends RecyclerView.Adapter<PullRequestAdapter.PullRequestViewHolder> {

    private List<Pull> pullList;

    @Inject
    public PullRequestAdapter() {
        pullList = new ArrayList<>();
    }

    public void setPullList(List<Pull> pullList) {
        this.pullList = pullList;
    }

    @Override
    public PullRequestAdapter.PullRequestViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_pull_row, parent, false);
        return new PullRequestViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final PullRequestAdapter.PullRequestViewHolder holder, int position) {
        final Pull repository = pullList.get(position);
        holder.tvTitle.setText(repository.getTitle());
        holder.tvUrl.setText(repository.getHtmlUrl());

    }

    @Override
    public int getItemCount() {
        return pullList.size();
    }

    class PullRequestViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title) TextView tvTitle;
        @BindView(R.id.tv_url) TextView tvUrl;

        public PullRequestViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
