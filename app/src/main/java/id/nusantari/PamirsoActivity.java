package id.nusantari;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.muzakki.ahmad.material.detail.DetailTabActivity;
import com.muzakki.ahmad.material.list.ListLocal;
import com.muzakki.ahmad.material.list.ListViewHolder;
import com.muzakki.ahmad.material.list.RowView;

import java.util.ArrayList;

public class PamirsoActivity extends DetailTabActivity {


    private ChallengeList challenge;
    private TopScoreList top_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        render();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setCoverImage(BitmapFactory.decodeResource(getResources(),R.drawable.belajar));
        setTitleSubtitle("PAMIRSO","asdf");
    }

    @Override
    protected ArrayList<String> getTabs() {
        ArrayList<String> tabs = new ArrayList<>();
        tabs.add("Challenge");
        tabs.add("Top Score");
        return tabs;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    @Override
    protected View getTabView(int i) {
        if(i==0) {
            if (challenge == null) {
                challenge = new ChallengeList(this);
                challenge.render();
            }
            return challenge;
        }else{
            if(top_score==null){
                top_score = new TopScoreList(this);
                top_score.render();
            }
            return top_score;
        }
    }

    private class ChallengeList extends ListLocal{

        public ChallengeList(Activity act) {
            super(act);
        }

        @Override
        protected ViewHolder getViewHolder(RowView rv) {
            return new ListViewHolder(rv) {
                @Override
                public void onBindView(Bundle row) {
                    getTitle().setText(row.getString("title"));
                    ((TextView)getDescription()).setText(row.getString("description"));
                    int res = 0;
                    switch(Integer.parseInt(row.getString("id"))){
                        case 1:default: res=R.drawable.tarimerak;break;
                        case 2: res = R.drawable.taripiring;break;
                        case 3: res=R.drawable.tarikecak;break;
                    }

                    getIcon().setImageBitmap(BitmapFactory.decodeResource(getResources(),res));
                }
            };
        }

        @Override
        protected String getTableName() {
            return "pamirso_challenge";
        }

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
}
