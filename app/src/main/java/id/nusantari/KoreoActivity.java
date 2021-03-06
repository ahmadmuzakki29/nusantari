package id.nusantari;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.muzakki.ahmad.material.detail.DetailFragment;
import com.muzakki.ahmad.material.detail.DetailTabActivity;
import com.muzakki.ahmad.material.list.ListLocal;
import com.muzakki.ahmad.material.list.ListViewHolder;
import com.muzakki.ahmad.material.list.RowView;

import java.util.ArrayList;

public class KoreoActivity extends DetailTabActivity implements OnMapReadyCallback {

    private TopScoreList top;
    private Bundle mapInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        render();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setCoverImage(BitmapFactory.decodeResource(getResources(),R.drawable.koreo));
        setTitleSubtitle("KOREO","asdf");
    }

    @Override
    protected ArrayList<String> getTabs() {
        ArrayList<String> tabs = new ArrayList<>();
        tabs.add("Challenge");
        tabs.add("Lokasi Sanggar");
        tabs.add("Top Skor");
        return tabs;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.clear();
        return true;
    }

    @Override
    protected View getTabView(int i, ViewGroup parent, Bundle savedInstanceState) {
        if(i==0){
            return getLayoutInflater().inflate(R.layout.koreo_chalenge,null);
        }else if(i==1){
            return getMap(parent,savedInstanceState);
        }else{
            if(top==null) top = new TopScoreList(this);
            return top;
        }
    }

    @Override
    protected DetailFragment.Listener getListener(int position) {
        return new DetailFragment.Listener() {
            @Override
            public void render(int position, View layout) {
                if(position==1) {
                    MapView map = (MapView) layout.findViewById(R.id.map);
                    map.onCreate(mapInstance);
                    map.onResume();

                    try {
                        MapsInitializer.initialize(KoreoActivity.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    map.getMapAsync(KoreoActivity.this);
                }
            }
        };
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    private class TopScoreList extends ListLocal{

        public TopScoreList(Activity act) {
            super(act);
        }

        @Override
        protected String getTableName() {
            return "top_score";
        }

        @Override
        protected ViewHolder getViewHolder(RowView rv) {
            return new ListViewHolder(rv) {
                @Override
                public void onBindView(Bundle row) {
                    getTitle().setText(row.getString("nama"));
                    ((TextView)getDescription()).setText("Point:"+row.getString("point"));
                    getIcon().setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.user_default));
                }
            };
        }
    }

    private View getMap(ViewGroup parent, Bundle savedInstanceState){
        View view = getLayoutInflater().inflate(R.layout.koreo_lokasi_sanggar, null);
        mapInstance = savedInstanceState;
        return view;
    }
}
