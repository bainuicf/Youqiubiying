package cn.shellc.youqiubiying;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	//@Override
	
	private Token token1,token2,token3,token4;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initView();	
		setContentView(R.layout.activity_main);	
		
	}

	private void initView() {
		// TODO Auto-generated method stub
		token1=new Token(MainActivity.this,
				"token1",
				(ImageView)findViewById(R.id.iv_token1), 
				(TextView)findViewById(R.id.tv_token1), 
				(Button)findViewById(R.id.bt_token1));	
		
		token2=new Token(MainActivity.this,
				"token2",
				(ImageView)findViewById(R.id.iv_token2), 
				(TextView)findViewById(R.id.tv_token2), 
				(Button)findViewById(R.id.bt_token2));	
		
		token3=new Token(MainActivity.this,
				"token3",
				(ImageView)findViewById(R.id.iv_token3), 
				(TextView)findViewById(R.id.tv_token3), 
				(Button)findViewById(R.id.bt_token3));	
		
		token4=new Token(MainActivity.this,
				"token4",
				(ImageView)findViewById(R.id.iv_token4), 
				(TextView)findViewById(R.id.tv_token4), 
				(Button)findViewById(R.id.bt_token4));	
		Handler handler=new Handler();		
		token1.changeNumber(MainActivity.this,handler);
		token2.changeNumber(MainActivity.this,handler);
		token3.changeNumber(MainActivity.this,handler);
		token4.changeNumber(MainActivity.this,handler);
		
		
	}
	
	
	
	
	
	
	
	
	

	
}
