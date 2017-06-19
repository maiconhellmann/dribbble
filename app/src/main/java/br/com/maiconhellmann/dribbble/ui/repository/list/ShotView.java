package br.com.maiconhellmann.dribbble.ui.repository.list;

import java.util.List;

import br.com.maiconhellmann.dribbble.data.model.Repository;
import br.com.maiconhellmann.dribbble.data.model.Shot;
import br.com.maiconhellmann.dribbble.ui.base.MvpView;

public interface ShotView extends MvpView {

    void showShots(List<Shot> shots);

    void showShotsEmpty();

    void showError();

}
