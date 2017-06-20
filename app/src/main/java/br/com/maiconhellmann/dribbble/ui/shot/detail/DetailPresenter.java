package br.com.maiconhellmann.dribbble.ui.shot.detail;

import javax.inject.Inject;

import br.com.maiconhellmann.dribbble.data.DataManager;
import br.com.maiconhellmann.dribbble.injection.ConfigPersistent;
import br.com.maiconhellmann.dribbble.ui.base.BasePresenter;
import rx.Subscription;

@ConfigPersistent
public class DetailPresenter extends BasePresenter<DetailView>{

    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public DetailPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(DetailView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }
}
