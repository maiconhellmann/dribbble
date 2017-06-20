package br.com.maiconhellmann.dribbble.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.maiconhellmann.dribbble.data.model.Shot;
import br.com.maiconhellmann.dribbble.data.remote.DribbbleService;
import rx.Observable;

@Singleton
public class DataManager {

    private final DribbbleService mGithubService;

    @Inject
    public DataManager(DribbbleService dribbleService) {
        mGithubService = dribbleService;
    }

    public Observable<List<Shot>> getShots(Integer page) {
        return mGithubService.getShots(page.toString());
    }
}
