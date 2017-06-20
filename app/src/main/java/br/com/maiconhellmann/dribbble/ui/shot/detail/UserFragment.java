package br.com.maiconhellmann.dribbble.ui.shot.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import br.com.maiconhellmann.dribbble.R;
import br.com.maiconhellmann.dribbble.data.model.Shot;
import br.com.maiconhellmann.dribbble.ui.base.BaseFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class UserFragment extends BaseFragment {

    @BindView(R.id.tv_repo_name)
    TextView tvRepoName;

    @BindView(R.id.tv_description)
    TextView tvDescription;

    private Unbinder unbinder;
    private String repositoryOwner;
    private Shot shot;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getConfigPersistentComponent().inject(this);

        if(getArguments() != null) {
            shot = getArguments().getParcelable(DetailTabActivity.EXTRA_SHOT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_repository_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        unbinder = ButterKnife.bind(this, view);
        configureUI();

    }

    private void configureUI() {
    }

    public void onDestroyView() {
        super.onDestroyView();

        if(unbinder != null) unbinder.unbind();
    }
}
