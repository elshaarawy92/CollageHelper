package com.example.collagehelper.base;

import android.support.v4.app.Fragment;
import android.view.View;

import com.example.collagehelper.MyClickListener;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 主要的Fragment类，所有fragment都继承它
 * Created by liang on 2018/10/29
 */
public class BaseFragment extends Fragment {

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

}
