package jzh.rxjavatext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import io.reactivex.functions.Predicate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //create();
        //fromArray();
        //just();
        //repeat();
        //range();
        //interval();
        timer();
    }

    //通过create创建observable对象，在subscribe中调用ObservableEmitter的onnext方法
    void create() {

        Observable<String> ob = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                for (int i = 0; i < 5; i++) {
                    e.onNext("the fuck i is " + i);
                }
                e.onComplete();
            }
        });
        //上面的代码我们已经构建了一个观察者，我们接下来新建一个订阅者
        //通过调用subscribe方法使观察者和订阅者产生关联，一旦订阅就观察者就开始发送消息
        ob.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.d("rxjava", value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("rxjava", "结束");
            }
        });
    }


    void fromArray() {

        Observable<String> ob = Observable.fromArray("1", "2", "3");
        ob.filter(new Predicate<String>() {//过滤
            @Override
            public boolean test(String s) throws Exception {
                if (s.equals("1")) {//如果字符串是1 则过滤掉，不走onNext方法
                    return false;
                }
                return true;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.d("rxjava", value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("rxjava", "结束");
            }
        });
    }


    void just() {

        Observable<String> ob = Observable.just("1", "2", "3", "4");
        ob.filter(new Predicate<String>() {//过滤
            @Override
            public boolean test(String s) throws Exception {
                if (s.equals("1")) {//如果字符串是1 则过滤掉，不走onNext方法
                    return false;
                }
                return true;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.d("rxjava", value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("rxjava", "结束");
            }
        });
    }

    //repeat重复
    void repeat() {

        Observable<String> ob = Observable.just("1", "2", "3", "4").repeat(3);//重复3次
        ob.filter(new Predicate<String>() {//过滤
            @Override
            public boolean test(String s) throws Exception {
                if (s.equals("1")) {//如果字符串是1 则过滤掉，不走onNext方法
                    return false;
                }
                return true;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.d("rxjava", value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("rxjava", "结束");
            }
        });
    }


    //range
    void range() {

        Observable<Integer> ob = Observable.range(10, 5);

        ob.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer value) {
                Log.d("rxjava", value + "");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("rxjava", "结束");
            }
        });
//        打印结果
//        10-23 14:59:41.203 11001-11001/jzh.rxjavatext D/rxjava: 10
//        10-23 14:59:41.203 11001-11001/jzh.rxjavatext D/rxjava: 11
//        10-23 14:59:41.203 11001-11001/jzh.rxjavatext D/rxjava: 12
//        10-23 14:59:41.203 11001-11001/jzh.rxjavatext D/rxjava: 13
//        10-23 14:59:41.203 11001-11001/jzh.rxjavatext D/rxjava: 14
//        10-23 14:59:41.203 11001-11001/jzh.rxjavatext D/rxjava: 结束
    }

    //interval轮询  好用的要死！！！！
    void interval() {
        //interval()函数的两个参数：
        // 一个指定两次发射的时间间隔.
        // 另一个是用到的时间单位。
        // 这个只会执行一次，添加subscribeOn(Schedulers.newThread())好像能够达到轮训的效果

        Observable.interval(3, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long value) {
                Log.d("rxjava", value + "");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("rxjava", "结束");
            }
        });
    }


    //如果你需要一个一段时间之后才发射的Observable，你可以像下面的例子使用timer()：
    void timer() {
        //它将3秒后发射0,然后就完成了。
        Observable.timer(3, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Long value) {
                Log.d("rxjava", value + "");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d("rxjava", "结束");
            }
        });
    }

}
