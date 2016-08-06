package id.nusantari;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.muzakki.ahmad.material.detail.DetailTabActivity;
import com.muzakki.ahmad.material.list.ListLocal;
import com.muzakki.ahmad.material.list.ListViewHolder;
import com.muzakki.ahmad.material.list.RowView;

import java.util.ArrayList;

public class KoreoActivity extends DetailTabActivity implements OnMapReadyCallback {

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
    protected View getTabView(int i, ViewGroup parent, Bundle savedInstanceState) {
        if(i==0){
            return null;
        }else if(i==1){
            return getMap(parent,savedInstanceState);
        }else{
            TopScoreList top = new TopScoreList(this);
            top.render();
            return top;
        }
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
        MapView map = (MapView) view.findViewById(R.id.map);
        map.onCreate(savedInstanceState);
        map.onResume();

        try {
            MapsInitializer.initialize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        map.getMapAsync(this);

        return view;
    }
}
