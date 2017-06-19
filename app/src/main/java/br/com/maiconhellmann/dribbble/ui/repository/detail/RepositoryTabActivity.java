package br.com.maiconhellmann.dribbble.ui.repository.detail;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import br.com.maiconhellmann.dribbble.R;
import br.com.maiconhellmann.dribbble.ui.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RepositoryTabActivity extends BaseActivity {
    public static final String EXTRA_OWNER = "owner";
    public static final String EXTRA_REPOSITORY = "repository";

    @BindView(R.id.vp_details) ViewPager viewPager;

    @BindView(R.id.toolbar) Toolbar toolbar;

    @BindView(R.id.tab_repository) TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_repository_tabs);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        configureUI();
    }

    private void configureUI() {
        viewPager.setAdapter(new RepositoryFragmentPagerAdapter(getSupportFragmentManager(), this));
        tabLayout.setupWithViewPager(viewPager);
    }


    private class RepositoryFragmentPagerAdapter extends FragmentPagerAdapter {

        private final RepositoryTabActivity context;
        private final ArrayList<Fragment> fragmentList;
        private String[] pageTitleList;

        RepositoryFragmentPagerAdapter(FragmentManager supportFragmentManager, RepositoryTabActivity repositoryDetailActivity) {
            super(supportFragmentManager);
            this.context = repositoryDetailActivity;

            this.fragmentList = new ArrayList<>(2);

            pageTitleList = new String[]{
                    context.getString(R.string.infos),
                    context.getString(R.string.pulls)
            };
            Fragment fragment = new RepositoryDetailFragment();
            fragment.setArguments(getIntent().getExtras());
            fragmentList.add(fragment);

            fragment = new PullListFragment();
            fragment.setArguments(getIntent().getExtras());
            fragmentList.add(fragment);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return pageTitleList[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }
    }
}
