package id.nusantari;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.muzakki.ahmad.material.detail.DetailFragment;
import com.muzakki.ahmad.material.detail.DetailTabActivity;
import com.muzakki.ahmad.material.list.ListLocal;
import com.muzakki.ahmad.material.list.ListViewHolder;
import com.muzakki.ahmad.material.list.RowView;
import com.muzakki.ahmad.material.map.MapLocal;

import java.util.ArrayList;

public class KoreoActivity extends DetailTabActivity{
    private final LatLng jakarta = new LatLng(-6.1745,106.8227);
    private TopScoreList top;
    private Bundle mapInstance;
    private MapSanggar map;

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
            mapInstance = savedInstanceState;
            if(map==null){
                map = new MapSanggar(this);
                map.setCameraPosition(new CameraPosition.Builder().target(jakarta)
                        .zoom(13)
                        .build());
            }
            return map;
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
                    map.render(mapInstance);
                }
            }
        };
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

    private class MapSanggar extends MapLocal{

        public MapSanggar(Context context) {
            super(context);
        }

        @Override
        protected String getTable() {
            return "sanggar";
        }

        @Override
        protected MarkerOptions getMarkerOptions(Bundle data) {
            MarkerOptions opt = new MarkerOptions();
            Log.i("jeki","lat:"+data.getString("latitude"));
            opt.position(new LatLng(Double.parseDouble(data.getString("latitude")),
                    Double.parseDouble(data.getString("longitude"))));
            opt.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            opt.title(data.getString("nama"));
            opt.snippet(data.getString("alamat"));
            return opt;
        }
    }

}
