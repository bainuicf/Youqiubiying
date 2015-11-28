package cn.shellc.youqiubiying;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;

public class Token extends Activity {
	private String tokenName;
	private ImageView tokenImage;
	private TextView tokenText;
	private Button tokenButton;

	public Token(String name, ImageView iv, TextView tv, Button btn) {
		// TODO Auto-generated constructor stub

		setTokenName(name);
		setTokenImage(iv);
		setTokenText(tv);
		setTokenButton(btn);

	}

	public void setTokenName(String tokenname) {
		this.tokenName = tokenname;
	}

	public String getTokenName() {
		return tokenName;
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
