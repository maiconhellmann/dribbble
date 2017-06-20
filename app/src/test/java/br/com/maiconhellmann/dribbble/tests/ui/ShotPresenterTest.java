package br.com.maiconhellmann.dribbble.tests.ui;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import br.com.maiconhellmann.dribbble.data.DataManager;
import br.com.maiconhellmann.dribbble.data.model.Image;
import br.com.maiconhellmann.dribbble.data.model.Shot;
import br.com.maiconhellmann.dribbble.ui.shot.list.ShotPresenter;
import br.com.maiconhellmann.dribbble.ui.shot.list.ShotView;
import rx.Observable;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;

public class ShotPresenterTest {
    DataManager dataManager;

    private ShotPresenter presenter;

    private ShotView view;

    @Before
    public void setup(){
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });

        view = Mockito.mock(ShotView.class);
        dataManager = Mockito.mock(DataManager.class);
        presenter = new ShotPresenter(dataManager);
        presenter.attachView(view);
    }

    @Test
    public void shouldShowRepositoryList(){
        Shot shot = new Shot();
        shot.setId(1);
        shot.setTitle("Title 1");
        shot.setViewsCount(10);
        shot.setDescription("A sample project");

        Image img = new Image();
        img.setNormal("http://www.google.com.br");

        shot.setImages(img);

        List<Shot> shotList = new ArrayList<>();
        shotList.add(shot);
        shotList.add(shot);

        Mockito.when(dataManager.getShots(1)).thenReturn(Observable.just(shotList));

        presenter.loadShots();
        view.showShots(shotList);
        Mockito.verify(view, times(2)).showShots(shotList);
    }

    @After
    public void tearDown() {
        presenter.detachView();
    }
}
