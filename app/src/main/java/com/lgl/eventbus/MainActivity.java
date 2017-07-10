package com.lgl.eventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * EventBus3.0
 * Created by lgl on 2016/5/9.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //发送按钮
    private Button btn_send;
    //接收消息
    private TextView tv_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //注册
        EventBus.getDefault().register(this);

        initView();

    }

    /**
     * 初始化View
     */
    private void initView() {
        btn_send = (Button) findViewById(R.id.btn_send);
        //点击事件
        btn_send.setOnClickListener(this);
        tv_content = (TextView) findViewById(R.id.tv_content);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:

                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
                break;
        }
    }
/*
    //主线程接收消息
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserEvent(UserEvent event) {
        //如果多个消息，可在实体类中添加type区分消息
        tv_content.setText(event.getText());
    }*/
    /**
     * 用这个函数来接收消息
     * @param event 自己定义的类
     *              sticky = true  默认情况下，其为false。什么情况下使用sticky呢？
        当你希望你的事件不被马上处理的时候，举个栗子，比如说，在一个详情页点赞之后，产生一个VoteEvent，VoteEvent并不立即被消费，
       而是等用户退出详情页回到商品列表之后，接收到该事件，然后刷新Adapter等。priority通过priority指定订阅者优先级以实现控制事件交付顺序。
    priority仅支持在相同ThreadMode下，高优先级订阅者会更早收到事件(被唤醒)。
    EventBus默认所有订阅者的priority均为0。priority越大，级别越高
    Called in Android UI's main thread
     */
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true,priority = 1000)
    public void receiveMsg(FirstEvent event){
        String tag=event.getTag();
        if(tag!=null&&!TextUtils.isEmpty(tag)){
            Log.i("hemiy", "收到了tag的消息");
        }else{
            String msg = "BACKGROUND收到了消息" + event.getMsg();
            tv_content.setText(msg);
            Log.i("hemiy", "不是tag的消息");
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }
    }


    /**
     * @param event/默认调用方式，在调用post方法的线程执行，避免了线程切换，性能开销最少
     */
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void receiveMsg1(FirstEvent event){
        String tag=event.getTag();
        if(tag!=null&&!TextUtils.isEmpty(tag)){
            Log.i("hemiy", "收到了tag的消息");
        }else{

            String msg = "BACKGROUND收到了消息" + event.getMsg();
            tv_content.setText(msg);
            Log.i("hemiy", "不是tag的消息");
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * @param event 如果调用post方法的线程不是主线程，则直接在该线程执行
     如果是主线程，则切换到后台单例线程，多个方法公用同个后台线程，按顺序执行，避免耗时操作
     */
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void receiveMsg2(FirstEvent event){
        String tag=event.getTag();
        if(tag!=null&&!TextUtils.isEmpty(tag)){
            Log.i("hemiy", "收到了tag的消息");
        }else{

            String msg = "BACKGROUND收到了消息" + event.getMsg();
            tv_content.setText(msg);
            Log.i("hemiy", "不是tag的消息");
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * @param event 开辟新独立线程，用来执行耗时操作，例如网络访问
    //EventBus内部使用了线程池，但是要尽量避免大量长时间运行的异步线程，限制并发线程数量
    //可以通过EventBusBuilder修改，默认使用Executors.newCachedThreadPool()
     */
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void receiveMsg3(FirstEvent event){
        String tag=event.getTag();
        if(tag!=null&&!TextUtils.isEmpty(tag)){
            Log.i("hemiy", "收到了tag的消息");
        }else{

            String msg = "BACKGROUND收到了消息" + event.getMsg();
            tv_content.setText(msg);
            Log.i("hemiy", "不是tag的消息");
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }
    }


}
