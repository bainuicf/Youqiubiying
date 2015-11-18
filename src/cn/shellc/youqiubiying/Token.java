package cn.shellc.youqiubiying;

import android.view.View;
import android.view.View.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.telephony.gsm.SmsManager;

@SuppressWarnings("deprecation")
public class Token{
	private ImageView tokenImage;
	private TextView tokenText;
	private Button tokenButton;
	

	public Token(Context context,Token token,ImageView iv, TextView tv, Button btn) {
		// TODO Auto-generated constructor stub
				
		//初始化兑换券数量
		SharedPreferences sharedPreferences = context.getSharedPreferences("token", Context.MODE_PRIVATE);
		Editor editor=sharedPreferences.edit();
		boolean isFirstRun=sharedPreferences.getBoolean("isFirstRun", true);
		if(isFirstRun)
		{
			editor.putInt("Token1", 10);
			editor.putInt("Token2", 10);
			editor.putInt("Token3", 10);
			editor.putInt("Token4", 10);
			editor.putBoolean("isFirstRun", false); 			
		}
		editor.commit();
		
		setTokenImage(iv);
		setTokenText(tv);
		setTokenButton(btn);
		
		

	}

//	点击“使用”按钮
	public void setOnClickListner(final Context context,final Token token) {
//		tokenButton.setOnClickListener(new OnClickListener() {
//
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				new AlertDialog
//					.Builder(context).
//					setTitle("确认使用").setMessage("确定使用此券吗？")
//						.setNegativeButton("不用也罢", null)
//						.setPositiveButton("莫要拦我", new DialogInterface.OnClickListener() {
//							public void onClick(DialogInterface dialog, int which) {
//								useToken(context,token);
//							}							
//						}).show();
//			
//			};
//		});

			
	}
	
	//使用兑换券
	public void useToken(Context context,Token token) {
		// TODO Auto-generated method stub
		SharedPreferences sharedPreferences = context.getSharedPreferences("token", Context.MODE_PRIVATE);
		Editor editor=sharedPreferences.edit();
		int number=sharedPreferences.getInt(token.toString(), 0);
		if(number==0)
		{
			Toast.makeText(context, "您的兑换券用完，快去卖萌求宠爱吧！", Toast.LENGTH_SHORT).show();			
		}else{
			number--;
			editor.putInt(token.toString(), number);
			editor.commit();
			changeNumber(context, token);
			sendSMS(token);
		}

		
	}
	

	private void sendSMS(Token token) {
		// TODO Auto-generated method stub
		String tokenname=null;
		switch (token.toString()) {
		case "token1":
			tokenname="洗衣券";			
			break;
		case "Token2":
			tokenname="刷鞋券";
			break;
		case "Token3":
			tokenname="按摩券";
			break;
		case "Token4":
			tokenname="任意券";
			break;
		default:
			break;
		}
		
		SmsManager sms=SmsManager.getDefault();			
		sms.sendTextMessage("13919841220", 
				"13893658856",
				"女王大人有令，赐你"+tokenname+"1张，速速领旨！/n钦此！", 
				null, null);
		
	}

	public void changeNumber(Context context,final Token token){
		SharedPreferences sharedPreferences = context.getSharedPreferences("token", Context.MODE_PRIVATE);
//		final String number=new Integer(sharedPreferences.getInt(token.toString(), 0)).toString();
//		token.tokenText.post(new Runnable() {
//            public void run() {
//           	 token.tokenButton.setText(number);
//            }
//		}); 	
	}

	public void setTokenImage(ImageView tokenImage) {
		this.tokenImage = tokenImage;
	}

	public ImageView getTokenImage() {
		return tokenImage;
	}

	public void setTokenText(TextView tokenText) {
		this.tokenText = tokenText;
	}

	public TextView getTokenText() {
		return tokenText;
	}

	public void setTokenButton(Button tokenButton) {
		this.tokenButton = tokenButton;
	}

	public Button getTokenButton() {
		return tokenButton;
	}

}
