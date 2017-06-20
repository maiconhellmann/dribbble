package br.com.maiconhellmann.dribbble.ui.shot.list;

import java.util.List;

import javax.inject.Inject;

import br.com.maiconhellmann.dribbble.data.DataManager;
import br.com.maiconhellmann.dribbble.data.model.Shot;
import br.com.maiconhellmann.dribbble.injection.ConfigPersistent;
import br.com.maiconhellmann.dribbble.ui.base.BasePresenter;
import br.com.maiconhellmann.dribbble.util.RxUtil;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

@ConfigPersistent
public class ShotPresenter extends BasePresenter<ShotView> {

    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public ShotPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(ShotView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void loadRepositories() {
        getMvpView().showProgressBar();

        checkViewAttached();
        RxUtil.unsubscribe(mSubscription);
        mSubscription = mDataManager.getShots(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Shot>>() {
                    @Override
                    public void onCompleted() {
                        getMvpView().hideProgressBar();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().hideProgressBar();

                        Timber.e(e, "There was an error loading the repositorys.");
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(List<Shot> shotList) {
                        getMvpView().hideProgressBar();

                        if (shotList.isEmpty()) {
                            getMvpView().showShotsEmpty();
                        } else {
                            getMvpView().showShots(shotList);
                        }
                    }
                });
    }
}
