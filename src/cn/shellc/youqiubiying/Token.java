package cn.shellc.youqiubiying;

import android.view.View;
import android.view.View.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.jar.Attributes.Name;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.telephony.gsm.SmsManager;

@SuppressWarnings("deprecation")
public class Token {
	private String tokenname;
	private ImageView tokenImage;
	private TextView tokenText;
	private Button tokenButton;

	public Token(final Activity pActivity, String name, ImageView iv, TextView tv, Button btn) {
		// TODO Auto-generated constructor stub

		// 初始化兑换券数量
		SharedPreferences sharedPreferences = pActivity.getSharedPreferences("token", Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		boolean isFirstRun = sharedPreferences.getBoolean("isFirstRun", true);
		if (isFirstRun) {
			editor.putInt("Token1", 10);
			editor.putInt("Token2", 10);
			editor.putInt("Token3", 10);
			editor.putInt("Token4", 10);
			editor.putBoolean("isFirstRun", false);
		}
		editor.commit();

		setTokenname(name);
		setTokenImage(iv);
		setTokenText(tv);
		setTokenButton(btn);
		
		tokenButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new AlertDialog.Builder(pActivity)
				.setTitle("确认使用")
				.setMessage("确定使用此券吗？")
				.setPositiveButton("莫要拦我", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						useToken(pActivity);
					}
				})
				.setNegativeButton("不用也罢", null)
				.show();

			};
		});

	}
	

	// 使用兑换券
	public void useToken(Context context) {
		// TODO Auto-generated method stub
		SharedPreferences sharedPreferences = context.getSharedPreferences("token", Context.MODE_PRIVATE);
		Editor editor = sharedPreferences.edit();
		int number = sharedPreferences.getInt(getTokenname(), 0);
		if (number == 0) {
			Toast.makeText(context, "女王大人的兑换券用完，快去卖萌求宠爱吧！", Toast.LENGTH_SHORT).show();
		} else {
			number--;
			editor.putInt(getTokenname(), number);
			editor.commit();
			changeNumber(context);
			sendSMS();
		}

	}

	public void sendSMS() {
		// TODO Auto-generated method stub
		String tokenname = null;
		switch (getTokenname()) {
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
		sms.sendTextMessage("13919841220", null, "女王大人有令，赐你" + tokenname + "1张，速速领旨！\n钦此！", null, null);

	}

	public void changeNumber(Context context) {
		SharedPreferences sharedPreferences = context.getSharedPreferences("token", Context.MODE_PRIVATE);
		int number = sharedPreferences.getInt(getTokenname(), 0);
//		tokenText.post(new Runnable() {
//			public void run() {
//				//tokenText.setText("10");
//			}
//		});
	}

	public void setTokenname(String tokenname) {
		this.tokenname = tokenname;
	}

	public String getTokenname() {
		return tokenname;
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
