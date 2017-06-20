package br.com.maiconhellmann.dribbble.ui.shot.detail;

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

public class DetailTabActivity extends BaseActivity {
    public static final String EXTRA_SHOT = "shot";

    @BindView(R.id.vp_details) ViewPager viewPager;

    @BindView(R.id.toolbar) Toolbar toolbar;

    @BindView(R.id.tab_shot_detail) TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_shot_detail_tabs);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        configureUI();
    }

    private void configureUI() {
        viewPager.setAdapter(new RepositoryFragmentPagerAdapter(getSupportFragmentManager(), this));
        tabLayout.setupWithViewPager(viewPager);
    }


    private class RepositoryFragmentPagerAdapter extends FragmentPagerAdapter {

        private final DetailTabActivity context;
        private final ArrayList<Fragment> fragmentList;
        private String[] pageTitleList;

        RepositoryFragmentPagerAdapter(FragmentManager supportFragmentManager, DetailTabActivity repositoryDetailActivity) {
            super(supportFragmentManager);
            this.context = repositoryDetailActivity;

            this.fragmentList = new ArrayList<>(2);

            pageTitleList = context.getResources().getStringArray(R.array.array_tab_detail);

            Fragment fragment = new UserFragment();
            fragment.setArguments(getIntent().getExtras());
            fragmentList.add(fragment);

            fragment = new ShotDetailFragment();
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
