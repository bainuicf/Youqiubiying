package cn.shellc.youqiubiying;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Token {
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
		
		this.tokenImage = iv;
		this.tokenText = tv;
		this.tokenButton = btn;

	}

//	点击“使用”按钮
	public void setOnClickListner(final Context context,final Token token) {
		tokenButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new AlertDialog
					.Builder(context).
					setTitle("确认使用").setMessage("确定使用此券吗？")
						.setNegativeButton("不用也罢", null)
						.setPositiveButton("莫要拦我", new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int which) {
								useToken(context,token);
							}							
						}).show();
			}
		});
	}
	
	//使用兑换券
	public void useToken(Context context,Token token) {
		// TODO Auto-generated method stub
		SharedPreferences sharedPreferences = context.getSharedPreferences("token", Context.MODE_PRIVATE);
		Editor editor=sharedPreferences.edit();
		int number=sharedPreferences.getInt(token.toString(), 0);
		if(number==0)
		{
			Toast.makeText(context, "您的兑换券用完，快去卖萌换券吧！", Toast.LENGTH_SHORT).show();			
		}else{
			number--;
			editor.putInt(token.toString(), number);
			editor.commit();
			changeNumber(context, token);
			//sendSMS();
		}

		
	}
	
//	private void //sendSMS() {
//		// TODO Auto-generated method stub
//		
//	}

	public void changeNumber(Context context,final Token token){
		SharedPreferences sharedPreferences = context.getSharedPreferences("token", Context.MODE_PRIVATE);
		final int number=sharedPreferences.getInt(token.toString(), 0);
		new Runnable() {
             public void run() {
            	 token.tokenButton.setText(number);
             }
		};
	
				
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
