package com.bapug.vpn;

import android.Manifest;
import android.animation.*;
import android.animation.ObjectAnimator;
import android.app.*;
import android.content.*;
import android.content.ClipData;
import android.content.Intent;
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
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.webkit.*;
import android.widget.*;
import android.widget.EditText;
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
import com.bumptech.glide.Glide;
import de.blinkt.openvpn.*;
import de.hdodenhof.circleimageview.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import com.bapug.vpn.interfaces.ChangeServer;
import com.bapug.vpn.model.Server;
import com.bapug.vpn.CheckInternetConnection;
import com.bapug.vpn.SharedPreference;
//import com.lazycoder.cakevpn.dataFragmentMainBinding;
import com.bapug.vpn.interfaces.ChangeServer;
import com.bapug.vpn.model.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import androidx.annotation.*;
import de.blinkt.openvpn.OpenVpnApi;
import de.blinkt.openvpn.core.OpenVPNService;
import de.blinkt.openvpn.core.OpenVPNThread;
import de.blinkt.openvpn.core.VpnStatus;

import static android.app.Activity.RESULT_OK;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.app.FragmentTransaction;

public class MyfFragmentActivity extends  Fragment implements ChangeServer{ 

	public final int REQ_CD_BB = 101;
	
	private String FNAME = "";
	private boolean isEmpty = false;
	private boolean Sutter_animation = false;
	Server server;
	OpenVPNThread vpnThread = new OpenVPNThread();
	SharedPreference preference;
	OpenVPNService vpnService = new OpenVPNService();
	CheckInternetConnection connection;
	private boolean click = false;
	boolean vpnStart = false;
	
	private LinearLayout base;
	private EditText editText;
	private LinearLayout connectionStatus;
	private LinearLayout linear1115;
	private LinearLayout circle_main;
	private LinearLayout linear4;
	private TextView textview_status;
	private LinearLayout sabrar;
	private TextView durationTv;
	private LinearLayout linear1116;
	private TextView logTv;
	private TextView textview2;
	private LinearLayout linear1118;
	private LinearLayout linear1117;
	private TextView byteInTv;
	private TextView byteOutTv;
	private LinearLayout vpnBut;
	private TextView textview5;
	private TextView IP;
	private CircleImageView selectedServerIcon;
	private TextView selectedServer;
	private ImageView imageview1;
	private TextView lastPacketReceiveTv;
	
	private ObjectAnimator Ami = new ObjectAnimator();
	private Intent bb = new Intent(Intent.ACTION_GET_CONTENT);
	private RequestNetwork ip_address;
	private RequestNetwork.RequestListener _ip_address_request_listener;
	private ObjectAnimator Amie = new ObjectAnimator();
	private Intent i = new Intent();
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.myf_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		base = _view.findViewById(R.id.base);
		editText = _view.findViewById(R.id.editText);
		connectionStatus = _view.findViewById(R.id.connectionStatus);
		linear1115 = _view.findViewById(R.id.linear1115);
		circle_main = _view.findViewById(R.id.circle_main);
		linear4 = _view.findViewById(R.id.linear4);
		textview_status = _view.findViewById(R.id.textview_status);
		sabrar = _view.findViewById(R.id.sabrar);
		durationTv = _view.findViewById(R.id.durationTv);
		linear1116 = _view.findViewById(R.id.linear1116);
		logTv = _view.findViewById(R.id.logTv);
		textview2 = _view.findViewById(R.id.textview2);
		linear1118 = _view.findViewById(R.id.linear1118);
		linear1117 = _view.findViewById(R.id.linear1117);
		byteInTv = _view.findViewById(R.id.byteInTv);
		byteOutTv = _view.findViewById(R.id.byteOutTv);
		vpnBut = _view.findViewById(R.id.vpnBut);
		textview5 = _view.findViewById(R.id.textview5);
		IP = _view.findViewById(R.id.IP);
		selectedServerIcon = _view.findViewById(R.id.selectedServerIcon);
		selectedServer = _view.findViewById(R.id.selectedServer);
		imageview1 = _view.findViewById(R.id.imageview1);
		lastPacketReceiveTv = _view.findViewById(R.id.lastPacketReceiveTv);
		bb.setType("gh");
		bb.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		ip_address = new RequestNetwork((Activity) getContext());
		
		circle_main.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				vpnBut.performClick();
			}
		});
		
		sabrar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getContext().getApplicationContext(), ServersActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
		});
		
		vpnBut.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				 if (vpnStart) {
					                    confirmDisconnect();
					                }else {
					                    prepareVpn();
					                }
			}
		});
		
		_ip_address_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				if (true) {
					               	IP.setText("Ip : ".concat(_response));
					               	IP.setVisibility(View.VISIBLE);
					                }
				                 else {
						               IP.setVisibility(View.GONE);
					                }
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				SketchwareUtil.showMessage(getContext().getApplicationContext(), "unable to get ip");
			}
		};
	}
	
	private void initializeLogic() {
		_onCreate_();
		preference = new SharedPreference(getContext());
		        server = preference.getServer();
		
		        // Update current selected server icon
			/*if(this.getPackageName().equals(R.string.pkg)){
		}
		else { finishAffinity();}*/
		        updateCurrentServerIcon(server.getFlagUrl());
		
		        connection = new CheckInternetConnection();
				isServiceRunning();
		      VpnStatus.initLogCache(getActivity().getCacheDir());
		    
		editText.setVisibility(View.GONE);
		
		IP.setVisibility(View.GONE);
		logTv.setVisibility(View.INVISIBLE);
		textview2.setVisibility(View.GONE);
		byteInTv.setVisibility(View.INVISIBLE);
		byteOutTv.setVisibility(View.INVISIBLE);
		lastPacketReceiveTv.setVisibility(View.INVISIBLE);
		durationTv.setVisibility(View.INVISIBLE);
	}
	
	@Override
	public void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_BB:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getContext().getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getContext().getApplicationContext(), _data.getData()));
					}
				}
				
			}
			else {
				SketchwareUtil.showMessage(getContext().getApplicationContext(), "permission denied");
				Intent intent = VpnService.prepare(getContext());
				
				                if (intent != null) {
					                    startActivityForResult(intent, REQ_CD_BB);
					              }
			}
			break;
			default:
			break;
		}
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		 if (vpnStart) {
			                    stopVpn();
			                }
	}
	public void _designs() {
		
		linear1116.setElevation((float)3);
		circle_main.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)100, (int)3, 0xFFBDBDBD, 0xFFBDBDBD));
		vpnBut.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFFFFFFFF));
		sabrar.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)50, (int)3, 0xFF000000, 0xFFD50000));
		textview_status.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_rubic_bold.ttf"), 1);
		textview2.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_rubic_bold.ttf"), 1);
		textview5.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_rubic_bold.ttf"), 1);
		IP.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_rubic_bold.ttf"), 1);
		durationTv.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_rubic_bold.ttf"), 1);
		lastPacketReceiveTv.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_rubic_bold.ttf"), 1);
		logTv.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_rubic_bold.ttf"), 1);
		byteInTv.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_rubic_bold.ttf"), 1);
		byteOutTv.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_rubic_bold.ttf"), 1);
		byteInTv.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_rubic_bold.ttf"), 1);
		byteOutTv.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/google_rubic_bold.ttf"), 1);
		textview2.setSelected(true); 
		textview2.setSingleLine(true);
		textview2.setSelected(true); 
		textview2.setSingleLine(true);
	}
	
	
	public void _onCreate_() {
		_designs();
		isEmpty = true;
		selectedServer.setText("NO SERVER SELECTED");
		selectedServerIcon.setImageResource(R.drawable.icon_for_all);
	}
	
	
	public void _gy() {
	}
	public void confirmDisconnect(){
		        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		        builder.setMessage(getActivity().getString(R.string.connection_close_confirm));
		
		        builder.setPositiveButton(getActivity().getString(R.string.yes), new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int id) {
				                if (true) {
						                circle_main.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
						                circle_main.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFFBDBDBD));
					                }
				                stopVpn();
				            }
			        });
		        builder.setNegativeButton(getActivity().getString(R.string.no), new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog, int id) {
				                // User cancelled the dialog
				            }
			        });
		
		        // Create the AlertDialog
		        AlertDialog dialog = builder.create();
		        dialog.show();
		    }
	
	private void prepareVpn() {
		        if (!vpnStart) {
			            if (getInternetStatus()) {
				
				                // Checking permission for network monitor
				                Intent intent = VpnService.prepare(getContext());
				
				                if (intent != null) {
					                    startActivityForResult(intent, REQ_CD_BB);
					                } else startVpn();//have already permission
				
				                // Update confection status
				                status("connecting");
				
				            } else {
				
				                // No internet connection available
				                showToast("you have no internet connection !!");
				            }
			
			        } else if (stopVpn()) {
			
			            // VPN is stopped, show a Toast message.
			            showToast("Disconnect Successfully");
			        }
		    }
	public boolean getInternetStatus() {
		
		        return connection.netCheck(getContext());
		    }
	
	    /**
     * Get service status
     */
	    public void isServiceRunning() {
		        setStatus(vpnService.getStatus());
		    }
	
	    /**
     * Start the VPN
     */
	    private void startVpn() {
		        try {
			            // .ovpn file
			            InputStream conf = getActivity().getAssets().open(server.getOvpn());
			            InputStreamReader isr = new InputStreamReader(conf);
			            BufferedReader br = new BufferedReader(isr);
			            String config = "";
			            String line;
			
			            while (true) {
				                line = br.readLine();
				                if (line == null) break;
				                config += line + "\n";
				            }
			
			            br.readLine();
			            OpenVpnApi.startVpn(getContext(), config, server.getCountry(), server.getOvpnUserName(), server.getOvpnUserPassword());
			
			            // Update log
			            logTv.setText("Connecting...");
			            vpnStart = true;
			
			        } catch (IOException | RemoteException e) {
			            e.printStackTrace();
			        }
		    }
	
	public boolean stopVpn() {
		        try {
			            vpnThread.stop();
			
			            status("connect");
			            vpnStart = false;
			            return true;
			        } catch (Exception e) {
			            e.printStackTrace();
			        }
		
		        return false;
		    }
	public void setStatus(String connectionState) {
		        if (connectionState!= null)
		        switch (connectionState) {
			            case "DISCONNECTED":
			                status("connect");
			                vpnStart = false;
			                vpnService.setDefaultStatus();
			                logTv.setText("");
			                break;
			            case "CONNECTED":
			                vpnStart = true;// it will use after restart this activity
			                status("connected");
			                logTv.setText("");
			                break;
			            case "WAIT":
			                logTv.setText("waiting for server connection!!");
			                break;
			            case "AUTH":
			                logTv.setText("server authenticating!!");
			                break;
			            case "RECONNECTING":
			                status("connecting");
			                logTv.setText("Reconnecting...");
			                break;
			            case "NONETWORK":
			                logTv.setText("No network connection");
			                break;
			        }
		
		    }
	
	    /**
     * Change button background color and text
     * @param status: VPN current status
     */
	     
	    public void status(String status) {
		
		        if (status.equals("connect")) {
			            textview_status.setText(getContext().getString(R.string.connect));
			            circle_main.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)100, (int)3, 0xFF000000, 0xFFD50000));
			                    
			        } else if (status.equals("connecting")) {
			                   Ami.setTarget(vpnBut);
			                   Ami.setPropertyName("translationX");
			                   Ami.setFloatValues(50.0f, 130.0f);
			                   Ami.setDuration(100); // No need to cast to int
			                   Ami.setRepeatMode(ValueAnimator.REVERSE);
			                   Ami.setRepeatCount(50); // No
			                   textview_status.setText(getContext().getString(R.string.connecting));
			                   circle_main.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)100, (int)3, 0xFF000000, 0xFFFFFF00));
			                          
			        } else if (status.equals("connected")) {
			            circle_main.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)100, (int)3, 0xFF000000, 0xFF00E676));
			            textview_status.setText(getContext().getString(R.string.disconnect));
			
			        } else if (status.equals("tryDifferentServer")) {
			
			            textview_status.setBackgroundResource(R.drawable.button_connected);
			            textview_status.setText("Try Different\nServer");
			        } else if (status.equals("loading")) {
			            textview_status.setBackgroundResource(R.drawable.button);
			            textview_status.setText("Loading Server..");
			        } else if (status.equals("invalidDevice")) {
			            textview_status.setBackgroundResource(R.drawable.button_connected);
			            textview_status.setText("Invalid Device");
			        } else if (status.equals("authenticationCheck")) {
			            textview_status.setBackgroundResource(R.drawable.button_connecting);
			            textview_status.setText("Authentication \n Checking...");
			        }
		        if (status.equals("connected")) {
			            circle_main.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)100, (int)1, 0xFF000000, 0xFFD50000));
			        
			        } else if (status.equals("connect")) {
			            circle_main.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)100, (int)1, 0xFFFFFFFF, 0xFFBDBDBD));
			          
			          /*  (()1. animation())*/  
			       
			        }
		        try {
			            float startValue = Float.parseFloat(editText.getText().toString());
			            float endValue = Float.parseFloat(editText.getText().toString());
			    
			            if (Sutter_animation) {
				                if (status.equals("connect")) {
					                    Sutter_animation = false;
					                    Ami.setTarget(vpnBut);
					                    Ami.setPropertyName("translationX");
					                    Ami.setFloatValues(200.0f, 0.0f);
					                    Ami.setDuration(200); // No need to cast to int
					                    Ami.setRepeatMode(ValueAnimator.REVERSE);
					                    Ami.setRepeatCount(0); // No need to cast to int
					                    Ami.setInterpolator(new LinearInterpolator());
					                    Ami.start();
					                }
				            
				            }else if (status.equals("connected")) {
				                Sutter_animation = true;
				                Ami.setTarget(vpnBut);
				                Ami.setPropertyName("translationX");
				                Ami.setFloatValues(0.0f, 200.0f);
				                Ami.setDuration(400); // No need to cast to int
				                Ami.setRepeatMode(ValueAnimator.REVERSE);
				                Ami.setRepeatCount(0); // No need to cast to int
				                Ami.setInterpolator(new LinearInterpolator());
				                Ami.start();
				                byteInTv.setVisibility(View.VISIBLE);
				                byteOutTv.setVisibility(View.VISIBLE);
				                lastPacketReceiveTv.setVisibility(View.VISIBLE);
				                durationTv.setVisibility(View.VISIBLE);
				                
				            }
			        
			        } catch (NumberFormatException e) {
			            // Handle the case where the text in the EditText is not a valid float.
			            // You might want to show a message to the user or take appropriate action.
			        }
		        
		    }      
	    
	    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
		        @Override
		        public void onReceive(Context context, Intent intent) {
			            try {
				                setStatus(intent.getStringExtra("state"));
				            } catch (Exception e) {
				                e.printStackTrace();
				            }
			
			            try {
				
				                String duration = intent.getStringExtra("duration");
				                String lastPacketReceive = intent.getStringExtra("lastPacketReceive");
				                String byteIn = intent.getStringExtra("byteIn");
				                String byteOut = intent.getStringExtra("byteOut");
				                  	/*if(getActivity().getPackageName().equals(R.string.pkg)){
		}
		else { finishAffinity();}*/
				                if (duration == null) duration = "00:00:00";
				                if (lastPacketReceive == null) lastPacketReceive = "0";
				                if (byteIn == null) byteIn = " ";
				                if (byteOut == null) byteOut = " ";
				                updateConnectionStatus(duration, lastPacketReceive, byteIn, byteOut);
				            } catch (Exception e) {
				                e.printStackTrace();
				            }
			
			        }
		    };
	
	    /**
     * Update status UI
     * @param duration: running time
     * @param lastPacketReceive: last packet receive time
     * @param byteIn: incoming data
     * @param byteOut: outgoing data
     */
	    public void updateConnectionStatus(String duration, String lastPacketReceive, String byteIn, String byteOut) {
		        durationTv.setText("Duration: " + duration);
		        lastPacketReceiveTv.setText("Packet: " + lastPacketReceive + " second ago");
		        byteInTv.setText("DL: " + byteIn);
		        byteOutTv.setText("UP: " + byteOut);
		    }
	
	    /**
     * Show toast message
     * @param message: toast message
     */
	    public void showToast(String message) {
		        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
		    }
	
	    /**
     * VPN server country icon change
     * @param serverIcon: icon URL
     */
	    public void updateCurrentServerIcon(String serverIcon) {
		        Glide.with(getContext())
		                .load(serverIcon)
		                .into(selectedServerIcon);
		    }
	
	    /**
     * Change server when user select new server
     * @param server ovpn server details
     */
	    @Override
	    public void newServer(Server server) {
		        this.server = server;
		        updateCurrentServerIcon(server.getFlagUrl());
		
		        // Stop previous connection
		        if (vpnStart) {
			            stopVpn();
			        }
		
		        prepareVpn();
		    }
	
	    @Override
	    public void onResume() {
		        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver, new IntentFilter("connectionState"));
		
		        if (server == null) {
			            server = preference.getServer();
			        }
		        super.onResume();
		    }
	
	    @Override
	    public void onPause() {
		        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);
		        super.onPause();
		    }/*
@Override
    public void newServer(Server server) {
      this.server = server;
        updateCurrentServerIcon(server.getFlagUrl());

        // Stop previous connection
        if (vpnStart) {
            stopVpn();
        }

        prepareVpn();
    }}*/
	    /**
     * Save current selected server on local shared preference
     */
	    @Override
	    public void onStop() {
		        if (server != null) {
			            preference.saveServer(server);
			        }
		
		        super.onStop();
		    
	}
	
}