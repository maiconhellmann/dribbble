package br.com.maiconhellmann.dribbble.ui.shot.list;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import br.com.maiconhellmann.dribbble.R;
import br.com.maiconhellmann.dribbble.data.model.Shot;
import br.com.maiconhellmann.dribbble.ui.base.BaseActivity;
import br.com.maiconhellmann.dribbble.ui.shot.detail.DetailTabActivity;
import br.com.maiconhellmann.dribbble.util.DialogFactory;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShotActivity extends BaseActivity implements ShotView, ShotAdapter.OnItemClickListener {

    @Inject
    ShotPresenter mMainPresenter;

    @Inject
    ShotAdapter mRepositoryAdapter;

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    @BindView(R.id.toolbar) Toolbar toolbar;

    @BindView(R.id.btnLoadMore) Button btnLoadMore;

    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_shot_list);
        ButterKnife.bind(this);

        mRepositoryAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mRepositoryAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMainPresenter.attachView(this);
        mMainPresenter.loadShots();

        setSupportActionBar(toolbar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mMainPresenter.detachView();
    }

    @Override
    public void showShots(List<Shot> shotList) {
        mRepositoryAdapter.addRepositories(shotList);
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading))
                .show();
    }

    @Override
    public void showProgressBar() {
        btnLoadMore.setVisibility(View.INVISIBLE);
        progressBar = DialogFactory.createProgressDialog(this, R.string.loading);
        progressBar.show();
    }

    @Override
    public void hideProgressBar() {
        btnLoadMore.setVisibility(View.VISIBLE);
        if(progressBar != null){
            progressBar.dismiss();
            progressBar = null;
        }
    }

    @Override
    public void showShotsEmpty() {
        mRepositoryAdapter.setRepositories(Collections.<Shot>emptyList());
        mRepositoryAdapter.notifyDataSetChanged();
        Toast.makeText(this, R.string.empty_shot, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(Shot shot) {
        try {
            Intent intent = new Intent(this, DetailTabActivity.class);
            intent.putExtra(DetailTabActivity.EXTRA_SHOT, shot);

            startActivity(intent);
        } catch (Exception e) {
            DialogFactory.createGenericErrorDialog(this, e).show();
        }
    }

    @OnClick(R.id.btnLoadMore)
    void onClickLoadMore(){
        try{
            mMainPresenter.loadShots();
        }catch (Exception e){
            DialogFactory.createGenericErrorDialog(this, e).show();
        }
    }
}
