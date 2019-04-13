package com.example.collagehelper.base;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collagehelper.MyClickListener;
import com.example.collagehelper.R;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 基本的Activity，所有Activity都继承它
 * Created by liang on 2018/10/28
 */
public class BaseActivity extends AppCompatActivity {

    private ImageButton ibLeft;
    private ImageButton ibRight;
    private TextView tittle;
    private FrameLayout fContent;
    private static boolean isExit = false;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    /**
     * 点击两次退出程序
     */
    public void clickTwiceToExit(){
        if (!isExit){
            isExit = true;
            Toast.makeText(this,"再点击一次退出程序",Toast.LENGTH_SHORT).show();
            handler.sendMessageDelayed(Message.obtain(),2000);
        }else {
            Intent home = new Intent(Intent.ACTION_MAIN);
            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            home.addCategory(Intent.CATEGORY_HOME);
            startActivity(home);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.layout_actionbar);
        initView();
    }

    //实例化控件
    private void initView() {
        ibLeft = findViewById(R.id.left_button);
        ibRight = findViewById(R.id.right_button);
        tittle = findViewById(R.id.tittle);
        fContent = findViewById(R.id.f_content);
    }

    //设置标题栏标题
    public void setTittle(String s){
        tittle.setText(s);
    }

    //重写setContentView方法，将布局加载到layout_actionbar的framelayout中
    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(layoutResID,null);
        fContent.addView(view,FrameLayout.LayoutParams.MATCH_PARENT,FrameLayout.LayoutParams.MATCH_PARENT);
    }

    //隐藏系统自带标题栏
    public void hideActionBar(){
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }
    }

    /**
     * 设置textview上面的图片大小
     * @param textView textview控件实例
     * @param drawable 要设置的图片资源
     */
    public void setDrawbleSize(TextView textView, Drawable drawable){
        drawable.setBounds(0,0,200,200);
        textView.setCompoundDrawables(null,drawable,null,null);
    }

    //标题栏左侧按钮的点击事件
    public void setLeftClick(MyClickListener myClickListener) {
        proxyOnClickListener(1, ibLeft, myClickListener);
    }

    //标题栏右侧按钮的点击事件（在子类中重写实现）
    public void setRightClick(MyClickListener myClickListener){
        proxyOnClickListener(1,ibRight,myClickListener);
    }

    //设置标题栏左侧的图片
    public void setLeftPicture(int drawableResource){
        ibLeft.setBackgroundResource(drawableResource);
    }

    //设置标题栏右侧的图片
    public void setRightPicture(int drawableResource){
        ibRight.setBackgroundResource(drawableResource);
    }

    /**
     * 基于Rxjava的形式实现点击事件，防抖
     * @param seconds 两次点击按钮的时间间隔
     * @param view 控件实例
     * @param myClickListener 自定义点击事件接口
     */
    public void proxyOnClickListener(int seconds, final View view, final MyClickListener myClickListener){
        ObservableOnSubscribe<View> onSubscribe = new ObservableOnSubscribe<View>() {
            @Override
            public void subscribe(final ObservableEmitter<View> emitter) throws Exception {
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        emitter.onNext(view);
                    }
                });
            }
        };
        Observer<View> observer = new Observer<View>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(View view) {
                myClickListener.onClick(view);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        Observable
                .create(onSubscribe)
                .throttleFirst(seconds,TimeUnit.SECONDS)
                .subscribe(observer);
    }

    public void hideLeftImage(){
        ibLeft.setVisibility(View.GONE);
    }

    public void hideRightImage(){
        ibRight.setVisibility(View.GONE);
    }

}
