package hai.com.myapp.anim;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import hai.com.myapp.R;

/**
 * Created by Administrator on 2017/6/10.
 */

public class AnimationActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        imageView = (ImageView) findViewById(R.id.iv_anim);
    }

    public void alpha(View view) {
        AlphaAnimation animation = new AlphaAnimation(1.0f, 0.0f);
        animation.setDuration(2000);
        animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatCount(2);
        //设置动画完成后是否听到在结束的状态
        animation.setFillAfter(true);
        imageView.setAnimation(animation);
        animation.start();
    }

    public void rotate(View view) {
//        RotateAnimation animation=new RotateAnimation();
    }

    public void scale(View view) {
        //属性动画
        ObjectAnimator oa = ObjectAnimator.ofFloat(imageView, "scaleY", 0.1f, 2, 1, 2);
        oa.setDuration(2000);
        oa.start();
    }

    public void transparent(View view) {

    }

    public void set(View view) {

    }
}
