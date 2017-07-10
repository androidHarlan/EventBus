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
       而是等用户退出详情页回到商品列表之后，接收到该事件，然后刷新Adapter等。其实这就是之前我们用startActivityForResult和onActivityResult做的事情。
     */
 /*   @Subscribe(threadMode = ThreadMode.MAIN)
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
    }*/
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true,priority = 1000)
    public void receive(FirstEvent event){
        Log.i("hemiy", "收到了的消息");
    }

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
