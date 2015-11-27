package cn.shellc.youqiubiying;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.gsm.SmsManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class MainActivity extends Activity {

	//@Override
	
	private Token token1,token2,token3,token4;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.activity_main);	
		
		initView();
		initNum();
	}

	
	private void initView() {
		// TODO Auto-generated method stub
		
		//初始化对话券
		token1=new Token(
				"token1",
				(ImageView)findViewById(R.id.iv_token1), 
				(TextView)findViewById(R.id.tv_token1), 
				(Button)findViewById(R.id.bt_token1));	
		
		token2=new Token(
				"token2",
				(ImageView)findViewById(R.id.iv_token2), 
				(TextView)findViewById(R.id.tv_token2), 
				(Button)findViewById(R.id.bt_token2));	
		
		token3=new Token(
				"token3",
				(ImageView)findViewById(R.id.iv_token3), 
				(TextView)findViewById(R.id.tv_token3), 
				(Button)findViewById(R.id.bt_token3));	
		
		token4=new Token(
				"token4",
				(ImageView)findViewById(R.id.iv_token4), 
				(TextView)findViewById(R.id.tv_token4), 
				(Button)findViewById(R.id.bt_token4));
		
		//设置“使用”按钮监听事件
//		token1.getTokenButton().setOnClickListener(OnTokenButtonClickListener(token1));
//		token2.getTokenButton().setOnClickListener(OnTokenButtonClickListener(token2));
//		token3.getTokenButton().setOnClickListener(OnTokenButtonClickListener(token3));
//		token4.getTokenButton().setOnClickListener(OnTokenButtonClickListener(token4));
        
		
		
		
	}
	


//	private OnClickListener OnTokenButtonClickListener(final Token token) {
//		// TODO Auto-generated method stub
	
		 
//		new AlertDialog.Builder(MainActivity.this)
//		.setTitle("确认使用")
//		.setMessage("女王大人确定使用此券吗？")
//		.setPositiveButton("莫要拦我", new DialogInterface.OnClickListener() {
//			public void onClick(DialogInterface dialog, int which) {
//				useToken(token);
//			}
//		})
//		.setNegativeButton("不用也罢", null)
//		.show();
//		return null;
//	}


	private void initNum() {
		// TODO Auto-generated method stub
		// 初始化兑换券数量
				SharedPreferences sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
				Editor editor = sharedPreferences.edit();
				boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
				if (isFirstRun) {
					editor.putInt("token1", 10);
					editor.putInt("token2", 10);
					editor.putInt("token3", 10);
					editor.putInt("token4", 10);
					editor.putBoolean("isFirstRun", false);
					editor.commit();
				}				
				
				changeNum(token1);
				changeNum(token2);
				changeNum(token3);
				changeNum(token4);
	}


	private void changeNum(final Token token) {
		// TODO Auto-generated method stub
		SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("token", Context.MODE_PRIVATE);
		final int number = sharedPreferences.getInt(token.getTokenname(), 0);
		new Handler().post(new Runnable() {
			public void run() {
				token.getTokenText().setText(Integer.toString(number));
			}
		});
	}
	
	// 使用兑换券
	public void useToken(Token token) {
		// TODO Auto-generated method stub
		SharedPreferences sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		int number = sharedPreferences.getInt(token.getTokenname(), 0);
		if (number == 0) {
			Toast.makeText(MainActivity.this, "女王大人的兑换券用完，快去卖萌求宠爱吧！", Toast.LENGTH_SHORT).show();
		} else {
			number--;
			editor.putInt(token.getTokenname(), number);
			editor.commit();
			changeNum(token);
			sendSMS(token);
		}

	}
	
	
	//发送指令
	public void sendSMS(Token token) {
		// TODO Auto-generated method stub
		String tokenname = null;
		switch (token.getTokenname()) {
		case "token1":
			tokenname = "叠衣券";
			break;
		case "Token2":
			tokenname = "刷鞋券";
			break;
		case "Token3":
			tokenname = "按摩券";
			break;
		case "Token4":
			tokenname = "任意券";
			break;
		default:
			break;
		}

		SmsManager sms = SmsManager.getDefault();
		sms.sendTextMessage("+8613919841220", null, "女王大人有令，赐你" + tokenname + "1张，速速领旨！\n钦此！", null, null);

	}
	
	
	
	
	
	
	
	

	
}
