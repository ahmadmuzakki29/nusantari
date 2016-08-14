package id.nusantari;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.muzakki.ahmad.material.detail.DetailTabActivity;
import com.muzakki.ahmad.material.form.Field;
import com.muzakki.ahmad.material.form.Fields;
import com.muzakki.ahmad.material.form.Form;
import com.muzakki.ahmad.material.list.ListLocal;
import com.muzakki.ahmad.material.list.ListViewHolder;
import com.muzakki.ahmad.material.list.RowView;

import java.util.ArrayList;

public class SatrioActivity extends DetailTabActivity {

    private Fields fields;
    private Challenge challenge;
    private TopScore topscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fields = new Fields();
        fields.add(new Field("foto", Field.Type.IMAGE));
        render();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setCoverImage(BitmapFactory.decodeResource(getResources(),R.drawable.satrio));
        setTitleSubtitle("SATRIO","asdf");
    }

    @Override
    protected ArrayList<String> getTabs() {
        ArrayList<String> tabs = new ArrayList<>();
        tabs.add("Challenge");
        tabs.add("Foto Terbaik");
        return tabs;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.clear();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected View getTabView(int i, ViewGroup parent, Bundle savedInstanceState) {
        if(i==0) {
            if (challenge == null)
                challenge = new Challenge(this, fields, Form.SaveType.LOCAL, Form.Action.ADD, null);
            return challenge;
        }else{
            if(topscore==null)  topscore = new TopScore(this);
            return topscore;
        }
    }

    private class Challenge extends Form{

        private Challenge(AppCompatActivity act, Fields fields, SaveType saveType, Action action, Listener listener) {
            super(act, fields, saveType, action, listener);
        }

    }

    private class TopScore extends ListLocal{

        public TopScore(Activity act) {
            super(act);
        }

        @Override
        protected String getTableName() {
            return "best_picture";
        }

        @Override
        protected ViewHolder getViewHolder(RowView rv) {
            return new ListViewHolder(rv) {
                @Override
                public void onBindView(Bundle row) {
                    getTitle().setText(row.getString("nama"));
                    ((TextView)getDescription()).setText(row.getString("title"));

                    int res;
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
        protected RowView getRowView(ViewGroup parent) {
            return new RowView(parent,R.layout.satrio_best_picture);
        }
    }
}
