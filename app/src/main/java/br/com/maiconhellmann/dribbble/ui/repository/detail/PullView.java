package br.com.maiconhellmann.dribbble.ui.repository.detail;

import java.util.List;

import br.com.maiconhellmann.dribbble.data.model.Pull;
import br.com.maiconhellmann.dribbble.ui.base.MvpView;

public interface PullView extends MvpView{
    void showError();
    void showPullRequests(List<Pull> pullList);
    void showEmptyPullList();
}
