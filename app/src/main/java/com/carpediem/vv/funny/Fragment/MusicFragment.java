package com.carpediem.vv.funny.Fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewParent;
import android.widget.ImageButton;

import com.carpediem.vv.funny.Activity.MainActivity;
import com.carpediem.vv.funny.Base.BaseFragment;
import com.carpediem.vv.funny.R;


/**
 * Created by Administrator on 2016/6/28.
 */
public class MusicFragment extends BaseFragment {

    private ImageButton musicPlay;
    private ObjectAnimator mAnimator = null;
    private Boolean isPlaying=false;

    public static MusicFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        MusicFragment fragment = new MusicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData() {

    }

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_music, null);
        musicPlay = (ImageButton) view.findViewById(R.id.music_play);
        musicPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    mAnimator = animateHide(MainActivity.bottomNavigationBar);
                    isPlaying=false;
                } else {
                    mAnimator = animateShow(MainActivity.bottomNavigationBar);
                    isPlaying=true;
                }
            }

            {
                if (mAnimator != null && mAnimator.isRunning()) {
                    mAnimator.cancel();
                }
            }
        });
        return view;
    }

    private ObjectAnimator animateShow(View view) {
        return animationFromTo(view, view.getTranslationY(), 0);
    }

    private ObjectAnimator animateHide(View view) {
        return animationFromTo(view, view.getTranslationY(), getDistance());
    }

    private ObjectAnimator animationFromTo(View view, float start, float end) {
        String propertyName = "translationY";
        ObjectAnimator animator = ObjectAnimator.ofFloat(view, propertyName, start, end);
        animator.setDuration(500);
        animator.start();
        return animator;
    }
    private int getDistance () {
        ViewParent parent = MainActivity.bottomNavigationBar.getParent();
        if (parent instanceof View) {
            return ((View)parent).getHeight() - MainActivity.bottomNavigationBar.getTop();
        }
        return 0;
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }


}
