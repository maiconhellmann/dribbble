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

import br.com.maiconhellmann.dribbble.R;
import br.com.maiconhellmann.dribbble.data.model.Shot;
import br.com.maiconhellmann.dribbble.ui.base.BaseFragment;
import br.com.maiconhellmann.dribbble.util.DialogFactory;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class UserFragment extends BaseFragment {

    @BindView(R.id.text_title)
    TextView textTitle;

    @BindView(R.id.text_biografia)
    TextView textBio;

    @BindView(R.id.imageView)
    ImageView imageView;

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
        return inflater.inflate(R.layout.fragment_user_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        unbinder = ButterKnife.bind(this, view);
        configureUI();

    }

    private void configureUI() {
        try{
            textTitle.setText(shot.getUser().getName());

            Glide.with(imageView.getContext())
                    .load(shot.getUser().getAvatarUrl())
                    .asBitmap()
                    .placeholder(R.drawable.placeholder)
                    .into(imageView);


            textBio.setText(Html.fromHtml(shot.getUser().getBio()));
        }catch (Exception e){
            DialogFactory.createGenericErrorDialog(getContext(), e).show();
        }
    }

    public void onDestroyView() {
        super.onDestroyView();

        if(unbinder != null) unbinder.unbind();
    }
}
