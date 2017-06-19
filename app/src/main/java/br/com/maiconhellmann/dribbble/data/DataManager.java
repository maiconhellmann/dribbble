package br.com.maiconhellmann.dribbble.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.maiconhellmann.dribbble.data.model.Pull;
import br.com.maiconhellmann.dribbble.data.model.Repository;
import br.com.maiconhellmann.dribbble.data.model.Shot;
import br.com.maiconhellmann.dribbble.data.remote.DribbbleService;
import rx.Observable;

@Singleton
public class DataManager {

    private final DribbbleService mGithubService;

    @Inject
    public DataManager(DribbbleService githubService) {
        mGithubService = githubService;
    }

    public Observable<List<Shot>> getShots(Integer page) {
        return mGithubService.getShots(page);
    }

    public Observable<List<Pull>> getPulls(String owner, String repository) {
        return mGithubService.getPulls(owner, repository);
    }
}
