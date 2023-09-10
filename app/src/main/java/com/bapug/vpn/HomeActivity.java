package com.bapug.vpn;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
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
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.*;
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.blogspot.atifsoftwares.animatoolib.*;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import de.blinkt.openvpn.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import org.json.*;
import com.bapug.vpn.adapter.ServerListRVAdapter;
import com.bapug.vpn.interfaces.ChangeServer;
import com.bapug.vpn.interfaces.NavItemClickListener;
import com.bapug.vpn.model.Server;

import java.util.ArrayList;

import com.bapug.vpn.Utils;
import androidx.fragment.app.FragmentTransaction;

public class HomeActivity extends  AppCompatActivity implements NavItemClickListener { 
	
	private Toolbar _toolbar;
	private AppBarLayout _app_bar;
	private CoordinatorLayout _coordinator;
	private DrawerLayout _drawer;
	private String FNAME = "";
	private String URL = "";
	private double n = 0;
	private  AlertDialog dialog;
	private String ICON = "";
	private String name = "";
	private double time = 0;
	private double stopwatch2 = 0;
	private double stopwatch3 = 0;
	private double stopwatch4 = 0;
	private double stopwatch = 0;
	private boolean Sutter_animation = false;
	private boolean click = false;
	ServerListRVAdapter serverListRVAdapter;
	ChangeServer changeServer;
	ArrayList<Server> serverLists;
	
	private ArrayList<HashMap<String, Object>> listmap1 = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private LinearLayout container;
	private LinearLayout toolbar;
	private ImageView imageview4;
	private TextView text_toolbar;
	private ImageView imageview3;
	private RecyclerView _drawer_serverListRv;
	
	private Intent intent = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.home);
		initialize(_savedInstanceState);
		initializeLogic();
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
		_drawer = findViewById(R.id._drawer);
		ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(HomeActivity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
		_drawer.addDrawerListener(_toggle);
		_toggle.syncState();
		
		LinearLayout _nav_view = findViewById(R.id._nav_view);
		
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		container = findViewById(R.id.container);
		toolbar = findViewById(R.id.toolbar);
		imageview4 = findViewById(R.id.imageview4);
		text_toolbar = findViewById(R.id.text_toolbar);
		imageview3 = findViewById(R.id.imageview3);
		_drawer_serverListRv = _nav_view.findViewById(R.id.serverListRv);
		
		imageview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (_drawer.isDrawerOpen(GravityCompat.START)) {
					            _drawer.closeDrawer(GravityCompat.END);
					        } else {
					            _drawer.openDrawer(GravityCompat.END);
					        }
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), SplashActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
	}
	
	private void initializeLogic() {
		fragment = new MyfFragmentActivity();
		serverLists = getServerList();
		        changeServer = (ChangeServer) fragment;
			if(this.getPackageName().equals("com.bapug.vpn")){
				}
				else { finishAffinity();}
		
		text_toolbar.setText(R.string.app_name);
		_toolbar.setVisibility(View.GONE);
		_drawer_serverListRv.setLayoutManager(new LinearLayoutManager(this));
		_drawer_serverListRv.setHasFixedSize(true);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		
		transaction.add(R.id.container, fragment);
		        transaction.commit();
		
		        // Server List recycler view initialize
		        if (serverLists != null) {
			            serverListRVAdapter = new ServerListRVAdapter(serverLists, this);
			            _drawer_serverListRv.setAdapter(serverListRVAdapter);
			        }
		
		text_toolbar.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/google_rubic_bold.ttf"), 1);
	}
	
	
	@Override
	public void onBackPressed() {
		finishAffinity();
	}
	public void _extra() {
	}
	private ArrayList getServerList() {
		
		        ArrayList<Server> servers = new ArrayList<>();
		
		        servers.add(new Server("United States",
		                Utils.getImgURL(R.drawable.usa_flag),
		                "us.ovpn",
		                "freeopenvpn",
		                "416248023"
		        ));
		        servers.add(new Server("Japan",
		                Utils.getImgURL(R.drawable.japan),
		                "japan.ovpn",
		                "vpn",
		                "vpn"
		        ));
		        servers.add(new Server("Sweden",
		                Utils.getImgURL(R.drawable.sweden),
		                "us1.ovpn",
		                "vpn",
		                "vpn"
		        ));
		        servers.add(new Server("Korea",
		                Utils.getImgURL(R.drawable.korea),
		                "japan11.ovpn",
		                "vpn",
		                "vpn"
		        ));
		        servers.add(new Server("Korea",
		                Utils.getImgURL(R.drawable.korea),
		                "japan11.ovpn",
		                "vpn",
		                "vpn"
		        ));
		        servers.add(new Server("Korea",
		                Utils.getImgURL(R.drawable.korea),
		                "japan11.ovpn",
		                "vpn",
		                "vpn"
		        ));
		
		        return servers;
		    }
	@Override
	    public void clickedItem(int index) {
		       _drawer.closeDrawer(GravityCompat.END);
		        changeServer.newServer(serverLists.get(index));
	}
	
	
	public void _init() {
	} private FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
	    private Fragment fragment;{
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