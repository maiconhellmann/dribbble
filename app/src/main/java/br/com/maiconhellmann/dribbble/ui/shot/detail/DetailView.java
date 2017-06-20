package br.com.maiconhellmann.dribbble.ui.shot.detail;

import br.com.maiconhellmann.dribbble.ui.base.MvpView;

public interface DetailView extends MvpView{
    void showError();
    void showEmptyPullList();
}
