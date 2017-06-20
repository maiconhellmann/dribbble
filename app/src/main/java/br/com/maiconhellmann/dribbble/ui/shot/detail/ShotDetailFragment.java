package br.com.maiconhellmann.dribbble.ui.shot.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import br.com.maiconhellmann.dribbble.R;
import br.com.maiconhellmann.dribbble.data.model.Shot;
import br.com.maiconhellmann.dribbble.ui.base.BaseFragment;
import br.com.maiconhellmann.dribbble.util.DialogFactory;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ShotDetailFragment extends BaseFragment implements DetailView {

    @BindView(R.id.imageView)
    ImageView imageView;

    @BindView(R.id.text_title)
    TextView textTitle;

    @BindView(R.id.text_views)
    TextView textViews;

    @BindView(R.id.text_description)
    TextView textDescription;

    @Inject
    DetailPresenter presenter;

    private Unbinder unbinder;
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
        return inflater.inflate(R.layout.fragment_shot_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        unbinder = ButterKnife.bind(this, view);
        configureUI();

        presenter.attachView(this);
    }

    private void configureUI() {
        try{
            int count = shot.getViewsCount() != null ? shot.getViewsCount() : 0;
            textViews.setText(getString(R.string.views_count, count));
            textTitle.setText(shot.getTitle());

            Glide.with(getContext())
                    .load(shot.getImages().getNormal())
                    .asBitmap()
                    .placeholder(R.drawable.placeholder)
                    .into(imageView);
            textDescription.setText(Html.fromHtml(shot.getDescription()));
        }catch (Exception e){
            DialogFactory.createGenericErrorDialog(getContext(), e).show();
        }
    }

    public void onDestroyView() {
        super.onDestroyView();

        if(unbinder != null) unbinder.unbind();
    }

    @Override
    public void showError() {
    }

    @Override
    public void showEmptyPullList() {
    }
}
