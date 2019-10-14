package com.sjl.remotedb.util;

import android.os.AsyncTask;
import android.util.Log;

import com.sjl.remotedb.db.CachedThreadManager;
import com.sjl.remotedb.db.MainThreadExecutor;

import java.util.concurrent.Callable;

/**
 * AsyncTask简单封装
 * 特点：类似RxJava的链式调用
 *
 * @author Kelly
 * @version 1.0.0
 * @filename AsyncTaskUtils.java
 * @time 2019/7/16 9:25
 * @copyright(C) 2019 song
 */
public class AsyncTaskUtils {

    /**
     * 异步任务(适合短时任务)
     *
     * @param call    执行体
     * @param onNext  成功回调
     * @param onError 失败回调
     * @param <T>     泛型
     */
    public static <T> void doAsync(final Callable<T> call,
                                   final Callback<? super T> onNext, final Callback<? super Throwable> onError) {

        new AsyncTask<Void, Integer, T>() {
            private Exception mException = null;

            @Override
            public T doInBackground(Void... params) {//运行子主线程
                try {
                    return call.call();
                } catch (final Exception e) {
                    this.mException = e;
                }
                return null;
            }

            @Override
            public void onPostExecute(final T result) {//运行在主线程

                if (this.isCancelled()) {
                    this.mException = new Exception("this task is cancelled.");
                }

                if (this.mException == null) {
                    if (onNext != null) {
                        try {
                            onNext.accept(result);
                        } catch (Exception e) {
                            if (onError == null) {
                                Log.e("doAsync Error", e.getMessage(), e);
                            } else {
                                onError.accept(e);
                            }
                        }
                    }
                } else {
                    if (onError == null) {
                        Log.e("doAsync Error", mException.getMessage(), mException);
                    } else {
                        onError.accept(this.mException);
                    }
                }
            }
        }.execute((Void[]) null);
    }



    /**
     * 异步任务
     *
     * @param call    执行体
     * @param onNext  成功回调
     * @param onError 失败回调
     * @param <T>     泛型
     */
    public static <T> void doAsync2(final Callable<T> call,
                                   final Callback<? super T> onNext, final Callback<? super Throwable> onError) {

        CachedThreadManager.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                T result = null;
                Exception exception = null;
                try {
                    result = call.call();
                } catch (Exception e) {
                    exception = e;
                }
                final Exception finalMException = exception;
                final T finalResult = result;
                MainThreadExecutor.runMainThread(new Runnable() {
                    @Override
                    public void run() {
                        if (finalMException == null) {
                            if (onNext != null) {
                                try {
                                    onNext.accept(finalResult);
                                } catch (Exception e) {
                                    if (onError == null) {
                                        Log.e("doAsync2 Error", e.getMessage(), e);
                                    } else {
                                        onError.accept(e);
                                    }
                                }
                            }
                        } else {
                            if (onError == null) {
                                Log.e("doAsync2 Error", finalMException.getMessage(), finalMException);
                            } else {
                                onError.accept(finalMException);
                            }
                        }
                    }
                });

            }
        });
    }

}
