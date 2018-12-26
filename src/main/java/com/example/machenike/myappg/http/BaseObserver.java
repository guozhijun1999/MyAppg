package com.example.machenike.myappg.http;


import com.example.machenike.myappg.base.moudle.HttpFinishCallback;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class BaseObserver<T> implements Observer<T> {
    private HttpFinishCallback mHttpFinishCallback;

    public BaseObserver(HttpFinishCallback httpFinishCallback) {
        mHttpFinishCallback = httpFinishCallback;
    }
    private CompositeDisposable mCompositeDisposable=new CompositeDisposable();
    @Override
    public void onSubscribe(Disposable d) {
        mCompositeDisposable.add(d);
    }

    @Override
    public void onError(Throwable e) {
        if (mCompositeDisposable!=null){
            mCompositeDisposable.clear();
        }
        if (mCompositeDisposable!=null){
            if (e instanceof HttpException){
                mHttpFinishCallback.setError("网络请求错误");

            }else {
                mHttpFinishCallback.setError("其他请求错误");
            }
            mHttpFinishCallback.setHideProgressbar();
        }
    }

    @Override
    public void onComplete() {
        if(mCompositeDisposable!=null){
            mCompositeDisposable.clear();
        }
        if(mHttpFinishCallback!=null){
            mHttpFinishCallback.setHideProgressbar();
        }
    }
}
