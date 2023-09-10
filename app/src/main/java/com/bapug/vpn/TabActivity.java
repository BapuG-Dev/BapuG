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
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;

public class TabActivity extends AppCompatActivity {
	
	private String file = "";
	private double n = 0;
	private double tab = 0;
	private String pat = "";
	
	private ArrayList<String> lists = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> listMap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> tabmap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> map = new ArrayList<>();
	private ArrayList<String> path = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout linear4;
	private GridView gridview1;
	private ImageView imageview1;
	private TextView textview1;
	private LinearLayout linear3;
	private TextView textview2;
	private TextView textview3;
	
	private Intent t = new Intent();
	private SharedPreferences tab1;
	private SharedPreferences s;
	private SharedPreferences t1;
	private SharedPreferences gr;
	private SharedPreferences last;
	private Calendar cald = Calendar.getInstance();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.tab);
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
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		linear4 = findViewById(R.id.linear4);
		gridview1 = findViewById(R.id.gridview1);
		imageview1 = findViewById(R.id.imageview1);
		textview1 = findViewById(R.id.textview1);
		linear3 = findViewById(R.id.linear3);
		textview2 = findViewById(R.id.textview2);
		textview3 = findViewById(R.id.textview3);
		tab1 = getSharedPreferences("tab1", Activity.MODE_PRIVATE);
		s = getSharedPreferences("s", Activity.MODE_PRIVATE);
		t1 = getSharedPreferences("t1", Activity.MODE_PRIVATE);
		gr = getSharedPreferences("gr", Activity.MODE_PRIVATE);
		last = getSharedPreferences("last", Activity.MODE_PRIVATE);
		
		gridview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				pat = lists.get((int)(_position));
				t.setClass(getApplicationContext(), WebviewActivity.class);
				s.edit().putString("search", FileUtil.readFile(pat)).commit();
				tab1.edit().putString("tab", String.valueOf((long)(_position))).commit();
				startActivity(t);
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				cald = Calendar.getInstance();
				FileUtil.writeFile("/storage/emulated/0/BapuG/.browserdata/tab/".concat("tab-".concat(new SimpleDateFormat("ddMMyyyy_HHmmss").format(cald.getTime()).concat(".txt"))), last.getString("last", ""));
				_list();
				t.setClass(getApplicationContext(), WebviewActivity.class);
				startActivity(t);
			}
		});
	}
	
	private void initializeLogic() {
		file = "/storage/emulated/0/outcloud/.browserdata/tab/";
		gridview1.setAdapter(new Gridview1Adapter(tabmap));
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/bapug.ttf"), 1);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/bapug.ttf"), 0);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/bapug.ttf"), 1);
		FileUtil.listDir(file, lists);
		n = 0;
		for(int _repeat18 = 0; _repeat18 < (int)(lists.size()); _repeat18++) {
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put(file, lists.get((int)(n)));
				listMap.add(_item);
			}
			
			n++;
		}
		gridview1.setAdapter(new Gridview1Adapter(listMap));
		textview3.setText(String.valueOf((long)(lists.size())));
	}
	
	public void _list() {
		listMap.clear();
		FileUtil.listDir(file, lists);
		n = 0;
		for(int _repeat14 = 0; _repeat14 < (int)(lists.size()); _repeat14++) {
			{
				HashMap<String, Object> _item = new HashMap<>();
				_item.put(file, lists.get((int)(n)));
				listMap.add(_item);
			}
			
			n++;
		}
		gridview1.setAdapter(new Gridview1Adapter(listMap));
		textview3.setText(String.valueOf((long)(lists.size())));
		gr.edit().putString("t", String.valueOf((long)(lists.size()))).commit();
	}
	
	public class Gridview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Gridview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.tab_cus, null);
			}
			
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			
			linear2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)35, 0xFF212121));
			textview1.setText(FileUtil.readFile(lists.get((int)(_position))));
			pat = lists.get((int)(_position));
			imageview1.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					FileUtil.deleteFile(pat);
					_list();
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