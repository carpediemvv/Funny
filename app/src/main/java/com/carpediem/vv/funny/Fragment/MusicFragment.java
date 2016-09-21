package com.carpediem.vv.funny.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.carpediem.vv.funny.Activity.MainActivity;
import com.carpediem.vv.funny.Base.BaseFragment;
import com.carpediem.vv.funny.R;


/**
 * Created by Administrator on 2016/6/28.
 */
public class MusicFragment extends BaseFragment {

    private ImageButton musicPlay;
    private ObjectAnimator mAnimator = null;
    private Boolean isPlaying=true;
    private ImageButton musicNext;
    private ImageButton musicPre;
    private LinearLayout linearLayout;
    private Handler handler;
    private ImageButton musicStylus;
    private ImageButton musicneedle;
    private ImageButton musiRedBg;

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
        handler = new Handler();
        View view = View.inflate(mActivity, R.layout.fragment_music, null);
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout_root);
        initLinearLayout();
        musicPlay = (ImageButton) view.findViewById(R.id.music_play);
        musicNext = (ImageButton) view.findViewById(R.id.music_next);
        musicPre = (ImageButton) view.findViewById(R.id.music_pre);
        musicStylus = (ImageButton) view.findViewById(R.id.stylus_lp);
        musicneedle = (ImageButton) view.findViewById(R.id.needle_lp);
        musiRedBg = (ImageButton) view.findViewById(R.id.red_music_bg);
        musicPlay.setOnClickListener(new View.OnClickListener() {

            private ObjectAnimator animator5;

            @Override
            public void onClick(View v) {
                musicStylus.setPivotX(50);
                musicStylus.setPivotY(57);
                musicneedle.setPivotX(50);
                musicneedle.setPivotY(57);

                if (isPlaying) {
                    //底部导航动画
                    musicPlay.setImageDrawable(getResources().getDrawable(R.drawable.image_button_selector));
                    mAnimator = animateHide(MainActivity.bottomNavigationBar);
                    isPlaying=false;
                    //切换动画
                    ObjectAnimator animator = ObjectAnimator.ofFloat(musicNext, "translationX", 0, 300);
                    ObjectAnimator animator1 = ObjectAnimator.ofFloat(musicPre, "translationX", 0, -300);
                    animator.setDuration(500).start();
                    animator1.setDuration(500).start();

                   //唱针动画
                    ObjectAnimator animator3 = ObjectAnimator.ofFloat(musicStylus, "rotation", 0f,15f);
                    animator3.setDuration(500).start();
                    //指针阴影动画
                    ObjectAnimator animator4 = ObjectAnimator.ofFloat(musicneedle, "rotation", 0f,15f);
                    animator4.setDuration(500).start();
                    animator4.setStartDelay(60);
                    //光盘背景旋转动画
                    animator5 = ObjectAnimator.ofFloat(musiRedBg, "rotation", 0f,360f);
                    animator5.setDuration(5000).start();
                    animator5.setRepeatCount(Animation.INFINITE);
                    animator5.setRepeatMode(ValueAnimator.RESTART);
                    animator5.setInterpolator(new LinearInterpolator());
                } else {
                    //底部导航动画
                    musicPlay.setImageDrawable(getResources().getDrawable(R.drawable.image_button_pause_selector));
                    mAnimator = animateShow(MainActivity.bottomNavigationBar);
                    isPlaying=true;
                    //切换动画
                    ObjectAnimator animator = ObjectAnimator.ofFloat(musicNext, "translationX", 300, 0);
                    ObjectAnimator animator1 = ObjectAnimator.ofFloat(musicPre, "translationX", -300,0);
                    animator.setDuration(500).start();
                    animator1.setDuration(500).start();
                    //唱针动画
                    ObjectAnimator animator3 = ObjectAnimator.ofFloat(musicStylus, "rotation", 15f,0f);
                    animator3.setDuration(500).start();
                    //指针阴影动画
                    ObjectAnimator animator4 = ObjectAnimator.ofFloat(musicneedle, "rotation", 15f,0f);
                    animator4.setDuration(500).start();
                    animator4.setStartDelay(60);
                    //光盘背景旋转动画暂停
                    animator5.cancel();
                }
            }

        });
        return view;
    }

    private void initLinearLayout() {
        linearLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        System.out.println("---action down-----");
                        mAnimator = animateShow(MainActivity.bottomNavigationBar);
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mAnimator = animateHide(MainActivity.bottomNavigationBar);
                            }
                        }, 3000);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        System.out.println("---action move-----");

                        break;
                    case MotionEvent.ACTION_UP:
                        System.out.println("---action up-----");


                }
                return true;
            }
        });
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
