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
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.*;
import com.blogspot.atifsoftwares.animatoolib.*;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import de.blinkt.openvpn.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import org.json.*;

public class WebviewActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private boolean listened = false;
	private boolean isEmpty = false;
	private double nn = 0;
	
	private ArrayList<HashMap<String, Object>> no = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> n = new ArrayList<>();
	
	private ScrollView vscroll3;
	private LinearLayout button_navegation;
	private LinearLayout linear56;
	private CollapsingToolbarLayout collapsingtoolbar1;
	private ImageView imageview3;
	private TextView textview2;
	private LinearLayout linear52;
	private LinearLayout linear57;
	private LinearLayout linear59;
	private LinearLayout linear60;
	private ListView listview2;
	private ImageView imageview17;
	private ImageView imageview18;
	private ImageView imageview13;
	private LinearLayout linear61;
	private ImageView imageview14;
	private LinearLayout linear62;
	private ImageView imageview15;
	private LinearLayout linear63;
	private ImageView imageview16;
	private LinearLayout linear64;
	private TextView textview19;
	private TextView textview20;
	private TextView textview21;
	private TextView textview22;
	private TextView textview18;
	private ImageView icon_home;
	private ImageView icon_grupo;
	private ImageView icon_noteficaciones;
	private TextView textview3;
	private ImageView icon_menu;
	
	private SpeechRecognizer stt;
	private TimerTask timer;
	private SharedPreferences s;
	private SharedPreferences sd;
	private SharedPreferences t;
	private SharedPreferences t1;
	private SharedPreferences t2;
	private SharedPreferences t3;
	private SharedPreferences se;
	private SharedPreferences m;
	private SharedPreferences gr;
	private Intent i = new Intent();
	private SharedPreferences web;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.webview);
		initialize(_savedInstanceState);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECORD_AUDIO}, 1000);
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
		_app_bar = findViewById(R.id._app_bar);
		_coordinator = findViewById(R.id._coordinator);
		_toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(_toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		vscroll3 = findViewById(R.id.vscroll3);
		button_navegation = findViewById(R.id.button_navegation);
		linear56 = findViewById(R.id.linear56);
		collapsingtoolbar1 = findViewById(R.id.collapsingtoolbar1);
		imageview3 = findViewById(R.id.imageview3);
		textview2 = findViewById(R.id.textview2);
		linear52 = findViewById(R.id.linear52);
		linear57 = findViewById(R.id.linear57);
		linear59 = findViewById(R.id.linear59);
		linear60 = findViewById(R.id.linear60);
		listview2 = findViewById(R.id.listview2);
		imageview17 = findViewById(R.id.imageview17);
		imageview18 = findViewById(R.id.imageview18);
		imageview13 = findViewById(R.id.imageview13);
		linear61 = findViewById(R.id.linear61);
		imageview14 = findViewById(R.id.imageview14);
		linear62 = findViewById(R.id.linear62);
		imageview15 = findViewById(R.id.imageview15);
		linear63 = findViewById(R.id.linear63);
		imageview16 = findViewById(R.id.imageview16);
		linear64 = findViewById(R.id.linear64);
		textview19 = findViewById(R.id.textview19);
		textview20 = findViewById(R.id.textview20);
		textview21 = findViewById(R.id.textview21);
		textview22 = findViewById(R.id.textview22);
		textview18 = findViewById(R.id.textview18);
		icon_home = findViewById(R.id.icon_home);
		icon_grupo = findViewById(R.id.icon_grupo);
		icon_noteficaciones = findViewById(R.id.icon_noteficaciones);
		textview3 = findViewById(R.id.textview3);
		icon_menu = findViewById(R.id.icon_menu);
		stt = SpeechRecognizer.createSpeechRecognizer(this);
		s = getSharedPreferences("s", Activity.MODE_PRIVATE);
		sd = getSharedPreferences("sd", Activity.MODE_PRIVATE);
		t = getSharedPreferences("t", Activity.MODE_PRIVATE);
		t1 = getSharedPreferences("t1", Activity.MODE_PRIVATE);
		t2 = getSharedPreferences("t2", Activity.MODE_PRIVATE);
		t3 = getSharedPreferences("t3", Activity.MODE_PRIVATE);
		se = getSharedPreferences("se", Activity.MODE_PRIVATE);
		m = getSharedPreferences("m", Activity.MODE_PRIVATE);
		gr = getSharedPreferences("gr", Activity.MODE_PRIVATE);
		web = getSharedPreferences("webv", Activity.MODE_PRIVATE);
		
		textview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), SearchActivity.class);
				startActivity(i);
			}
		});
		
		imageview13.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				web.edit().putString("web", "google.com").commit();
				i.setClass(getApplicationContext(), PageActivity.class);
				startActivity(i);
			}
		});
		
		imageview14.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				web.edit().putString("web", "youtube.com").commit();
				i.setClass(getApplicationContext(), PageActivity.class);
				startActivity(i);
			}
		});
		
		imageview15.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				web.edit().putString("web", "instagram.com").commit();
				i.setClass(getApplicationContext(), PageActivity.class);
				startActivity(i);
			}
		});
		
		imageview16.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				web.edit().putString("web", "crazygames.com").commit();
				i.setClass(getApplicationContext(), PageActivity.class);
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
		
		textview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), TabActivity.class);
				startActivity(i);
			}
		});
		
		stt.setRecognitionListener(new RecognitionListener() {
			@Override
			public void onReadyForSpeech(Bundle _param1) {
			}
			
			@Override
			public void onBeginningOfSpeech() {
			}
			
			@Override
			public void onRmsChanged(float _param1) {
			}
			
			@Override
			public void onBufferReceived(byte[] _param1) {
			}
			
			@Override
			public void onEndOfSpeech() {
			}
			
			@Override
			public void onPartialResults(Bundle _param1) {
			}
			
			@Override
			public void onEvent(int _param1, Bundle _param2) {
			}
			
			@Override
			public void onResults(Bundle _param1) {
				final ArrayList<String> _results = _param1.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
				final String _result = _results.get(0);
				
			}
			
			@Override
			public void onError(int _param1) {
				final String _errorMessage;
				switch (_param1) {
					case SpeechRecognizer.ERROR_AUDIO:
					_errorMessage = "audio error";
					break;
					
					case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
					_errorMessage = "speech timeout";
					break;
					
					case SpeechRecognizer.ERROR_NO_MATCH:
					_errorMessage = "speech no match";
					break;
					
					case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
					_errorMessage = "recognizer busy";
					break;
					
					case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
					_errorMessage = "recognizer insufficient permissions";
					break;
					
					default:
					_errorMessage = "recognizer other error";
					break;
				}
				SketchwareUtil.showMessage(getApplicationContext(), "try again");
			}
		});
	}
	
	private void initializeLogic() {
		if (gr.getString("t", "").equals("")) {
			
		}
		else {
			textview3.setText(gr.getString("t", ""));
		}
		textview2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)35, 0xFFEEEEEE));
		_app_bar.setVisibility(View.GONE);
		nn = 4;
		Bitmap bm = ((android.graphics.drawable.BitmapDrawable)imageview3.getDrawable()).getBitmap();
		imageview3.setImageBitmap(getRoundedCornerBitmap(bm,360));
		
		
	}
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) { Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888); Canvas canvas = new Canvas(output); final int color = 0xff424242; final Paint paint = new Paint(); final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()); final RectF rectF = new RectF(rect); final float roundPx = pixels; paint.setAntiAlias(true); canvas.drawARGB(0, 0, 0, 0); paint.setColor(color); canvas.drawRoundRect(rectF, roundPx, roundPx, paint); paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN)); canvas.drawBitmap(bitmap, rect, rect, paint); return output;
	}
	
	@Override
	public void onBackPressed() {
		finish();
	}
	public void _Glide(final ImageView _img, final String _url) {
		Glide.with(WebviewActivity.this)
		        .load(_url)
		        .circleCrop()
		        .into(_img);
		
	}
	
	
	public void _shape(final double _top1, final double _top2, final double _bottom2, final double _bottom1, final String _inside_color, final String _side_color, final double _side_size, final View _view) {
		Double tlr = _top1;
		Double trr = _top2;
		Double blr = _bottom2;
		Double brr = _bottom1;
		Double sw = _side_size;
		android.graphics.drawable.GradientDrawable s = new android.graphics.drawable.GradientDrawable();
		s.setShape(android.graphics.drawable.GradientDrawable.RECTANGLE);
		s.setCornerRadii(new float[] {tlr.floatValue(),tlr.floatValue(), trr.floatValue(),trr.floatValue(), blr.floatValue(),blr.floatValue(), brr.floatValue(),brr.floatValue()}); 
		
		s.setColor(Color.parseColor(_inside_color));
		s.setStroke(sw.intValue(), Color.parseColor(_side_color));
		_view.setBackground(s);
	}
	
	
	public void _SetCornerRadius(final View _view, final double _radius, final double _shadow, final String _color) {
		android.graphics.drawable.GradientDrawable ab = new android.graphics.drawable.GradientDrawable();
		
		ab.setColor(Color.parseColor(_color));
		ab.setCornerRadius((float) _radius);
		_view.setElevation((float) _shadow);
		_view.setBackground(ab);
	}
	
	
	public void _ListMap(final ArrayList<HashMap<String, Object>> _map, final boolean _work) {
		if (_work) {
			Collections.reverse(_map);
		}
		else {
			
		}
	}
	
	public class Listview2Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview2Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.nwe_cus, null);
			}
			
			final LinearLayout linear5lis = _view.findViewById(R.id.linear5lis);
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout linear9 = _view.findViewById(R.id.linear9);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final TextView textview5 = _view.findViewById(R.id.textview5);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final TextView textview4 = _view.findViewById(R.id.textview4);
			final ImageView imageview3 = _view.findViewById(R.id.imageview3);
			
			listview2.setSelector(android.R.color.transparent);
			if (n.get((int)_position).containsKey("title")) {
				textview5.setText(_data.get((int)_position).get("title").toString());
			}
			if (n.get((int)_position).containsKey("message")) {
				textview1.setText(_data.get((int)_position).get("message").toString());
			}
			if (n.get((int)_position).containsKey("date")) {
				textview4.setText(_data.get((int)_position).get("date").toString());
			}
			if (n.get((int)_position).containsKey("thumbnail")) {
				Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("thumbnail").toString())).into(imageview3);
			}
			_SetCornerRadius(linear1, 20, 2, "#ffffff");
			_SetCornerRadius(linear4, 20, 0, "#f5f5f5");
			linear1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					t.edit().putString("t", _data.get((int)_position).get("title").toString()).commit();
					m.edit().putString("m", _data.get((int)_position).get("message").toString()).commit();
					t1.edit().putString("t2", _data.get((int)_position).get("date").toString()).commit();
					t2.edit().putString("t3", _data.get((int)_position).get("thumbnail").toString()).commit();
					i.setClass(getApplicationContext(), StoryActivity.class);
					startActivity(i);
				}
			});
			
			return _view;
		}
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