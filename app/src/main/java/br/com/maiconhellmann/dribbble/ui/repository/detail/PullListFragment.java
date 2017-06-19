package br.com.maiconhellmann.dribbble.ui.repository.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import br.com.maiconhellmann.dribbble.R;
import br.com.maiconhellmann.dribbble.data.model.Pull;
import br.com.maiconhellmann.dribbble.data.model.Repository;
import br.com.maiconhellmann.dribbble.ui.base.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PullListFragment extends BaseFragment implements PullView {

    @Inject
    PullPresenter presenter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private String repositoryOwner;
    private Unbinder unbinder;
    private Repository repository;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getConfigPersistentComponent().inject(this);

        if(getArguments() != null) {
            repository = getArguments().getParcelable(RepositoryTabActivity.EXTRA_REPOSITORY);
            repositoryOwner = getArguments().getString(RepositoryTabActivity.EXTRA_OWNER);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pull, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        unbinder = ButterKnife.bind(this, view);
        configureUI();

        presenter.attachView(this);

        presenter.loadPullRequests(repositoryOwner, repository.getName());
    }

    private void configureUI() {
    }

    public void onDestroyView() {
        super.onDestroyView();

        if(unbinder != null) unbinder.unbind();
    }

    @Override
    public void showError() {
    }

    @Override
    public void showPullRequests(List<Pull> pullList) {
        PullRequestAdapter adapter = new PullRequestAdapter();
        adapter.setPullList(pullList);

        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showEmptyPullList() {

    }


}
