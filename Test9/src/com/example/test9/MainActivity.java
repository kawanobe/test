package com.example.test9;


import com.example.test9.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.res.Resources;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	int[] splitNumber = new int[100];
	String [] name = new String[100];
	int[] have = new int[100];
	int[] pay = new int[100];
	int[] balance = new int[100];
	int[] result = new int[100];
	int[] shortage = new int[100];
	Person[] person = new Person[100];
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button btnCalc = (Button)findViewById(R.id.btnCalc);
		Button btnCancel = (Button)findViewById(R.id.btnCancel);
		//名前参照・割り勘人数算出
		Resources res = getResources();
		splitCount:
		for(int i=0;i<4;i++){	
			  String tempName = "name"+i;
			  int strId = getResources().getIdentifier(tempName, "string", getPackageName());
			  name[i] = res.getString(strId);
			  splitNumber[0] = i;
			  if (name[i] == ""){
				  break splitCount;
			  }
		}
		//所持金・支払金・残高参照  Person情報初期化 画面表示
		for(int i = 0; i< splitNumber[0]; i++){
			//所持金参照
			String tempHave = "have"+i;
			int strHaveId = getResources().getIdentifier(tempHave, "string", getPackageName());
			String tempHave2 = res.getString(strHaveId);
			have[i] = Integer.parseInt(tempHave2);
			//支払金参照
			String tempPay = "pay"+i;
			int strPayId = getResources().getIdentifier(tempPay, "string", getPackageName());
			String tempPay2 = res.getString(strPayId);
			pay[i] = Integer.parseInt(tempPay2);
			//残高参照
			String tempBalance = "balance"+i;
			int strBalanceId = getResources().getIdentifier(tempBalance, "string", getPackageName());
			String tempBalance2 = res.getString(strBalanceId);
			balance[i] = Integer.parseInt(tempBalance2);
			//Person情報初期化
			person[i] =new Person();
			person[i].setName(name[i]);
			person[i].setHave(have[i]);
			person[i].setPay(pay[i]);
			person[i].setBalance(have[i]);
		}
			//割り勘結果初期化
			for(int i = 0; i< splitNumber[0]; i++){
				result[i] = 0;
			}
			//不足金額初期化
			for(int i = 0; i< splitNumber[0]; i++){
				shortage[i] = 0;
			}
			//初期画面表示	
			TextView etxtHave0   = (TextView)findViewById(R.id.txtHave0);
			TextView etxtHave1   = (TextView)findViewById(R.id.txtHave1);
			TextView etxtHave2   = (TextView)findViewById(R.id.txtHave2);
			TextView etxtPay0   = (TextView)findViewById(R.id.txtPay0);
			TextView etxtPay1   = (TextView)findViewById(R.id.txtPay1);
			TextView etxtPay2   = (TextView)findViewById(R.id.txtPay2);
			TextView etxtBalance0   = (TextView)findViewById(R.id.txtBalance0);
			TextView etxtBalance1   = (TextView)findViewById(R.id.txtBalance1);
			TextView etxtBalance2   = (TextView)findViewById(R.id.txtBalance2);
			TextView etxtSplitNumber0   = (TextView)findViewById(R.id.txtSplitNumber0);
			TextView etxtShortageMoney0 = (TextView)findViewById(R.id.txtShortageMoney0);
			TextView etxtResult = (TextView)findViewById(R.id.txtResult);
//			EditText etxtMoney   = (EditText)findViewById(R.id.txtMoney);
			// 入力内容を取得
			int tempHave0  = person[0].getMoney();//have[0];
			int tempHave1  = person[1].getMoney();
			int tempHave2  = person[2].getMoney();
			int tempPay0  = person[0].getPay();
			int tempPay1  = person[1].getPay();
			int tempPay2  = person[2].getPay();
			int tempBalance0  = person[0].getBalance();
			int tempBalance1  = person[1].getBalance();
			int tempBalance2  = person[2].getBalance();
			int tempSplitNumber0  = splitNumber[0];
			int tempShortageMoney = shortage[splitNumber[0]-1];
			int tempResult = result[0];
			// 結果表示用テキストに設定
			etxtHave0.setText(Integer.toString(tempHave0));
			etxtHave1.setText(Integer.toString(tempHave1));
			etxtHave2.setText(Integer.toString(tempHave2));
			etxtPay0.setText(Integer.toString(tempPay0));
			etxtPay1.setText(Integer.toString(tempPay1));
			etxtPay2.setText(Integer.toString(tempPay2));
			etxtBalance0.setText(Integer.toString(tempBalance0));
			etxtBalance1.setText(Integer.toString(tempBalance1));
			etxtBalance2.setText(Integer.toString(tempBalance2));
			etxtSplitNumber0.setText(Integer.toString(tempSplitNumber0));
			etxtShortageMoney0.setText(Integer.toString(tempShortageMoney));
			etxtResult.setText(Integer.toString(tempResult));
			// 結果表示用テキストを表示
			etxtHave0.setVisibility(View.VISIBLE);
			etxtHave1.setVisibility(View.VISIBLE);
			etxtHave2.setVisibility(View.VISIBLE);
			etxtPay0.setVisibility(View.VISIBLE);
			etxtPay1.setVisibility(View.VISIBLE);
			etxtPay2.setVisibility(View.VISIBLE);
			etxtBalance0.setVisibility(View.VISIBLE);
			etxtBalance1.setVisibility(View.VISIBLE);
			etxtBalance2.setVisibility(View.VISIBLE);
			etxtSplitNumber0.setVisibility(View.VISIBLE);
			etxtShortageMoney0.setVisibility(View.VISIBLE);
			etxtResult.setVisibility(View.VISIBLE);
		//計算ボタンを押した時の動作
			btnCalc.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				int[] splitNumber2 = new int[splitNumber[0]+1];
				int have;
				int pay;
				int balance;
				//オブジェクト取得
				EditText etxtMoney   = (EditText)findViewById(R.id.txtMoney);
				// 金額入力内容を取得
				String strTotalMoney = etxtMoney.getText().toString();
				// 数値に変換
				int intTotalMoney = Integer.parseInt(strTotalMoney);
				// 金額計算
				for(int i = 0; i< splitNumber[0]+1; i++){
					shortage[i] = 0;
				}
				shortage[0] = intTotalMoney;
				result[0] = shortage[0]/splitNumber[0];
				for(int i=0; i<splitNumber[0]; i++){
					splitNumber2[i] = splitNumber[0];
					if(person[i].getBalance() == 0 ){
						for(int j=0; j<splitNumber[0]; j++){
						splitNumber2[j] = splitNumber2[j]-1;
						}
					}
					person[i].setPay(0);
				}
				calc:
				for(int i = 0; i< splitNumber2[0]; i++){
					if(splitNumber2[i] == 0){
						shortage[splitNumber2[0]] = shortage[i];
						break calc;
					}
					shortage[i+1] = shortage[i+1] + (shortage[i] % splitNumber2[i]);
					for(int j = 0; j<splitNumber[0]; j++){
						have = person[j].getBalance();
						pay = shortage[i] / splitNumber2[i];
						balance = person[j].getBalance();
						if(balance != 0){
							balance = balance - pay;
							if(balance <= 0){
								shortage[i+1]= shortage[i+1]+(pay-have);
								pay = have;
								balance = 0;
								splitNumber2[i+1] = splitNumber2[i+1]-1;
							}
						}
						if(person[j].getPay() != person[j].getMoney()){
							person[j].setPay(person[j].getPay()+pay);
							person[j].setBalance(balance);
						}
					}
				}
				//テキスト情報取得
				TextView etxtHave0   = (TextView)findViewById(R.id.txtHave0);
				TextView etxtHave1   = (TextView)findViewById(R.id.txtHave1);
				TextView etxtHave2   = (TextView)findViewById(R.id.txtHave2);
				TextView etxtPay0   = (TextView)findViewById(R.id.txtPay0);
				TextView etxtPay1   = (TextView)findViewById(R.id.txtPay1);
				TextView etxtPay2   = (TextView)findViewById(R.id.txtPay2);
				TextView etxtBalance0   = (TextView)findViewById(R.id.txtBalance0);
				TextView etxtBalance1   = (TextView)findViewById(R.id.txtBalance1);
				TextView etxtBalance2   = (TextView)findViewById(R.id.txtBalance2);
				TextView etxtSplitNumber0   = (TextView)findViewById(R.id.txtSplitNumber0);
				TextView etxtResult = (TextView)findViewById(R.id.txtResult);
				TextView etxtShortage   = (TextView)findViewById(R.id.txtShortageMoney0);
				String strPreShortage = etxtShortage.getText().toString();
				int intPreShortage = Integer.parseInt(strPreShortage);
				int intShortageMoney = shortage[splitNumber2[0]];
				int tempResult = result[0];
				// 結果表示用テキストに設定
				etxtHave0.setText(Integer.toString(person[0].getMoney()));
				etxtHave1.setText(Integer.toString(person[1].getMoney()));
				etxtHave2.setText(Integer.toString(person[2].getMoney()));
				etxtPay0.setText(Integer.toString(person[0].getPay()));
				etxtPay1.setText(Integer.toString(person[1].getPay()));
				etxtPay2.setText(Integer.toString(person[2].getPay()));
				etxtBalance0.setText(Integer.toString(person[0].getBalance()));
				etxtBalance1.setText(Integer.toString(person[1].getBalance()));
				etxtBalance2.setText(Integer.toString(person[2].getBalance()));
				etxtShortage.setText(Integer.toString(intPreShortage + intShortageMoney));//前回の不足金額に加算
				etxtResult.setText(Integer.toString(tempResult));
				// 結果表示用テキストを表示
				etxtHave0.setVisibility(View.VISIBLE);
				etxtHave1.setVisibility(View.VISIBLE);
				etxtHave2.setVisibility(View.VISIBLE);
				etxtPay0.setVisibility(View.VISIBLE);
				etxtPay1.setVisibility(View.VISIBLE);
				etxtPay2.setVisibility(View.VISIBLE);
				etxtBalance0.setVisibility(View.VISIBLE);
				etxtBalance1.setVisibility(View.VISIBLE);
				etxtBalance2.setVisibility(View.VISIBLE);
				etxtSplitNumber0.setVisibility(View.VISIBLE);
				etxtShortage.setVisibility(View.VISIBLE);
				etxtResult.setVisibility(View.VISIBLE);
				for(int i = 0; i<splitNumber[0]; i++){
					person[i].setHave(person[i].getBalance());
				}
		}
		});
			//キャンセルボタンを押した時の動作
			btnCancel.setOnClickListener(new View.OnClickListener() {
			Resources res = getResources();
			public void onClick(View v) {
				//所持金・支払金・残高参照  Person情報初期化 画面表示
				for(int i = 0; i< splitNumber[0]; i++){
					//所持金参照
					String tempHave = "have"+i;
					int strHaveId = getResources().getIdentifier(tempHave, "string", getPackageName());
					String tempHave2 = res.getString(strHaveId);
					have[i] = Integer.parseInt(tempHave2);
					//支払金参照
					String tempPay = "pay"+i;
					int strPayId = getResources().getIdentifier(tempPay, "string", getPackageName());
					String tempPay2 = res.getString(strPayId);
					pay[i] = Integer.parseInt(tempPay2);
					//残高参照
					String tempBalance = "balance"+i;
					int strBalanceId = getResources().getIdentifier(tempBalance, "string", getPackageName());
					String tempBalance2 = res.getString(strBalanceId);
					balance[i] = Integer.parseInt(tempBalance2);
					//Person情報初期化
					person[i] =new Person();
					person[i].setName(name[i]);
					person[i].setHave(have[i]);
					person[i].setPay(pay[i]);
					person[i].setBalance(have[i]);
				}
					//割り勘結果初期化
					for(int i = 0; i< splitNumber[0]; i++){
						result[i] = 0;
					}
					//不足金額初期化
					for(int i = 0; i< splitNumber[0]+1; i++){
						shortage[i] = 0;
					}
					//初期画面表示	
					TextView etxtHave0   = (TextView)findViewById(R.id.txtHave0);
					TextView etxtHave1   = (TextView)findViewById(R.id.txtHave1);
					TextView etxtHave2   = (TextView)findViewById(R.id.txtHave2);
					TextView etxtPay0   = (TextView)findViewById(R.id.txtPay0);
					TextView etxtPay1   = (TextView)findViewById(R.id.txtPay1);
					TextView etxtPay2   = (TextView)findViewById(R.id.txtPay2);
					TextView etxtBalance0   = (TextView)findViewById(R.id.txtBalance0);
					TextView etxtBalance1   = (TextView)findViewById(R.id.txtBalance1);
					TextView etxtBalance2   = (TextView)findViewById(R.id.txtBalance2);
					TextView etxtSplitNumber0   = (TextView)findViewById(R.id.txtSplitNumber0);
					TextView etxtShortageMoney0 = (TextView)findViewById(R.id.txtShortageMoney0);
					TextView etxtResult = (TextView)findViewById(R.id.txtResult);
					// 入力内容を取得
					int tempHave0  = person[0].getMoney();
					int tempHave1  = person[1].getMoney();
					int tempHave2  = person[2].getMoney();
					int tempPay0  = person[0].getPay();
					int tempPay1  = person[1].getPay();
					int tempPay2  = person[2].getPay();
					int tempBalance0  = person[0].getBalance();
					int tempBalance1  = person[1].getBalance();
					int tempBalance2  = person[2].getBalance();
					int tempSplitNumber0  = splitNumber[0];
					int tempShortageMoney = shortage[splitNumber[0]];
					int tempResult = result[0];
					// 結果表示用テキストに設定
					etxtHave0.setText(Integer.toString(tempHave0));
					etxtHave1.setText(Integer.toString(tempHave1));
					etxtHave2.setText(Integer.toString(tempHave2));
					etxtPay0.setText(Integer.toString(tempPay0));
					etxtPay1.setText(Integer.toString(tempPay1));
					etxtPay2.setText(Integer.toString(tempPay2));
					etxtBalance0.setText(Integer.toString(tempBalance0));
					etxtBalance1.setText(Integer.toString(tempBalance1));
					etxtBalance2.setText(Integer.toString(tempBalance2));
					etxtSplitNumber0.setText(Integer.toString(tempSplitNumber0));
					etxtShortageMoney0.setText(Integer.toString(tempShortageMoney));
					etxtResult.setText(Integer.toString(tempResult));
					// 結果表示用テキストを表示
					etxtHave0.setVisibility(View.VISIBLE);
					etxtHave1.setVisibility(View.VISIBLE);
					etxtHave2.setVisibility(View.VISIBLE);
					etxtPay0.setVisibility(View.VISIBLE);
					etxtPay1.setVisibility(View.VISIBLE);
					etxtPay2.setVisibility(View.VISIBLE);
					etxtBalance0.setVisibility(View.VISIBLE);
					etxtBalance1.setVisibility(View.VISIBLE);
					etxtBalance2.setVisibility(View.VISIBLE);
					etxtSplitNumber0.setVisibility(View.VISIBLE);
					etxtShortageMoney0.setVisibility(View.VISIBLE);
					etxtResult.setVisibility(View.VISIBLE);
			}
			});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

