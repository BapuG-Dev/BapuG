package com.bapug.vpn;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.*;
import com.blogspot.atifsoftwares.animatoolib.*;
import de.blinkt.openvpn.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;

public class PageActivity extends AppCompatActivity {
	
	private double tabcurrentpathdata = 0;
	private String tabCurrentPathData = "";
	
	private LinearLayout linear2;
	private LinearLayout linear3;
	private LinearLayout linear4;
	private ProgressBar progges;
	private WebView webview1;
	private LinearLayout button_navegation;
	private ImageView icon_menu;
	private TextView textview5;
	private ImageView imageview1;
	private ImageView icon_grupo;
	private ImageView icon_noteficaciones;
	private TextView textview1;
	private ImageView imageview2;
	
	private SharedPreferences c;
	private SharedPreferences s;
	private SharedPreferences h;
	private SharedPreferences tab1;
	private SharedPreferences gr;
	private SharedPreferences current;
	private SharedPreferences t1;
	private Intent i = new Intent();
	private SharedPreferences web;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.page);
		initialize(_savedInstanceState);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		linear4 = findViewById(R.id.linear4);
		progges = findViewById(R.id.progges);
		webview1 = findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		button_navegation = findViewById(R.id.button_navegation);
		icon_menu = findViewById(R.id.icon_menu);
		textview5 = findViewById(R.id.textview5);
		imageview1 = findViewById(R.id.imageview1);
		icon_grupo = findViewById(R.id.icon_grupo);
		icon_noteficaciones = findViewById(R.id.icon_noteficaciones);
		textview1 = findViewById(R.id.textview1);
		imageview2 = findViewById(R.id.imageview2);
		c = getSharedPreferences("c", Activity.MODE_PRIVATE);
		s = getSharedPreferences("s", Activity.MODE_PRIVATE);
		h = getSharedPreferences("h", Activity.MODE_PRIVATE);
		tab1 = getSharedPreferences("tab1", Activity.MODE_PRIVATE);
		gr = getSharedPreferences("gr", Activity.MODE_PRIVATE);
		current = getSharedPreferences("current", Activity.MODE_PRIVATE);
		t1 = getSharedPreferences("t1", Activity.MODE_PRIVATE);
		web = getSharedPreferences("webv", Activity.MODE_PRIVATE);
		
		webview1.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				progges.setProgress((int)webview1.getProgress());
				h.edit().putString("history", _url).commit();
				textview5.setText(_url);
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				h.edit().putString("history", _url).commit();
				textview5.setText(_url);
				progges.setVisibility(View.GONE);
				webview1.clearHistory();
				super.onPageFinished(_param1, _param2);
			}
		});
		
		textview5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				c.edit().putString("current", textview5.getText().toString()).commit();
				i.setClass(getApplicationContext(), SearchActivity.class);
				startActivity(i);
			}
		});
		
		icon_grupo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), SearchActivity.class);
				startActivity(i);
			}
		});
		
		textview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				FileUtil.writeFile(tabCurrentPathData, webview1.getUrl());
				current.edit().putString("current", webview1.getUrl()).commit();
				i.setClass(getApplicationContext(), TabActivity.class);
				startActivity(i);
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				View popupView = getLayoutInflater().inflate(R.layout.menu, null);
				final PopupWindow popup = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
				
				TextView t1 = popupView.findViewById(R.id.textview3);
				TextView t2 = popupView.findViewById(R.id.textview5);
				ImageView t3 = popupView.findViewById(R.id.imageview4);
				ImageView t4 = popupView.findViewById(R.id.imageview1);
				ImageView t5 = popupView.findViewById(R.id.imageview5);
				linear2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)35, 0xFFFFFFFF));
				textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/bapug.ttf"), 0);
				textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/bapug.ttf"), 0);
				t1.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v){
						//CODES TO BE EXECUTED
						popup.dismiss();
					}
				});
				
				
				
				t3.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick (View v){
						webview1.loadUrl(webview1.getUrl());
						popup.dismiss();
					}
				});
				t4.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick (View v){
						if (webview1.canGoForward()) {
							webview1.goForward();
						} else {
							if (webview1.canGoBack()) {
								webview1.goBack();
							}
						}
						popup.dismiss();
					}
				});
				t5.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick (View v){
						popup.dismiss();
					}
				});
				t2.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick (View v){
						//CODES TO BE EXECUTED
						
						
						i.setClass(getApplicationContext(), HistoryActivity.class);
						startActivity(i);
						popup.dismiss();
					}
				});
				popup.showAtLocation(popupView, Gravity.CENTER, 0, 0);
			}
		});
	}
	
	private void initializeLogic() {
		
		webview1.setDownloadListener(new DownloadListener() {
			public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
				try {
					DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
					String cookies = CookieManager.getInstance().getCookie(url);
					request.addRequestHeader("cookie", cookies);
					request.addRequestHeader("User-Agent", userAgent);
					request.setDescription("Downloading file...");
					request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimetype));
					request.allowScanningByMediaScanner(); request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
					
					java.io.File aatv = new java.io.File(Environment.getExternalStorageDirectory().getPath() + "/Download");
					if(!aatv.exists()){if (!aatv.mkdirs()){ Log.e("TravellerLog ::","Problem creating Image folder");}}
					
					request.setDestinationInExternalPublicDir("/Download", URLUtil.guessFileName(url, contentDisposition, mimetype));
					
					DownloadManager manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
					manager.enqueue(request);
					showMessage("Downloading File....");
					
					//Notif if success
					BroadcastReceiver onComplete = new BroadcastReceiver() {
						public void onReceive(Context ctxt, Intent intent) {
							showMessage("Download Complete!");
							unregisterReceiver(this);
						}};
					registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
				} catch (Exception e){
					showMessage(e.toString());
				}
			}
			
		});
		//To save image from web view @Override public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) { super.onCreateContextMenu(menu, v, menuInfo); // Get the web view hit test result final WebView1.HitTestResult result = myWebView.getHitTestResult(); /* WebView1.HitTestResult IMAGE_TYPE HitTestResult for hitting an HTML::img tag. SRC_IMAGE_ANCHOR_TYPE HitTestResult for hitting a HTML::a tag with src=http + HTML::img. */ // If user long press on an image if (result.getType() == WebView1.HitTestResult.IMAGE_TYPE || result.getType() == WebView1.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) { // Set the title for context menu menu.setHeaderTitle("CONTEXT MENU"); // Add an item to the menu menu.add(0, 1, 0, "Save Image") .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() { @Override public boolean onMenuItemClick(MenuItem menuItem) { // Get the image url String imgUrl = result.getExtra(); // If this is an image url then download it if (URLUtil.isValidUrl(imgUrl)) { // Initialize a new download request DownloadManager.Request request = new DownloadManager.Request(Uri.parse(imgUrl)); request.allowScanningByMediaScanner(); request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED); DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE); downloadManager.enqueue(request); Toast.makeText(myContext, "image saved.", Toast.LENGTH_SHORT).show(); } else { Toast.makeText(myContext, "Invalid image url.", Toast.LENGTH_SHORT).show(); } return false; } });
		
		
		webview1.loadUrl("https://www.google.com/search?q=".concat(s.getString("search", "")));
		textview5.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c) { this.setStroke(a, b); this.setColor(c); return this; } }.getIns((int)0, 0xFFFFFFFF, Color.TRANSPARENT));
		textview5.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)35, 0xFFEEEEEE));
		textview5.setText(gr.getString("tab", ""));
		textview1.setText("");
		tab1.edit().putString("tab", textview5.getText().toString()).commit();
		h.edit().putString("history", textview5.getText().toString()).commit();
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/bapug.ttf"), 0);
		textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/jhanina.ttf"), 0);
	}
	
	@Override
	public void onBackPressed() {
		i.setClass(getApplicationContext(), WebviewActivity.class);
		i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(i);
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}