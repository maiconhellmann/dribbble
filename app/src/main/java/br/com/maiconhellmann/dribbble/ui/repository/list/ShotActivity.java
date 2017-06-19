package br.com.maiconhellmann.dribbble.ui.repository.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import br.com.maiconhellmann.dribbble.R;
import br.com.maiconhellmann.dribbble.data.model.Repository;
import br.com.maiconhellmann.dribbble.data.model.Shot;
import br.com.maiconhellmann.dribbble.ui.base.BaseActivity;
import br.com.maiconhellmann.dribbble.ui.repository.detail.RepositoryTabActivity;
import br.com.maiconhellmann.dribbble.util.DialogFactory;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ShotActivity extends BaseActivity implements ShotView, ShotAdapter.OnItemClickListener {

    @Inject
    ShotPresenter mMainPresenter;

    @Inject
    ShotAdapter mRepositoryAdapter;

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_repository);
        ButterKnife.bind(this);

        mRepositoryAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mRepositoryAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMainPresenter.attachView(this);
        mMainPresenter.loadRepositories();

        setSupportActionBar(toolbar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mMainPresenter.detachView();
    }

    @Override
    public void showShots(List<Shot> shotList) {
        mRepositoryAdapter.setRepositories(shotList);
        mRepositoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_repositories))
                .show();
    }

    @Override
    public void showShotsEmpty() {
        mRepositoryAdapter.setRepositories(Collections.<Shot>emptyList());
        mRepositoryAdapter.notifyDataSetChanged();
        Toast.makeText(this, R.string.empty_repositories, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(Shot shot) {
        try {
            Intent intent = new Intent(this, RepositoryTabActivity.class);
//            intent.putExtra(RepositoryTabActivity.EXTRA_REPOSITORY, shot);
//            intent.putExtra(RepositoryTabActivity.EXTRA_OWNER, shot.getOwner().getLogin());

            startActivity(intent);
        } catch (Exception e) {
            DialogFactory.createGenericErrorDialog(this, e).show();
        }
    }
}
