package id.nusantari;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SliderLayout slider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slider = (SliderLayout)findViewById(R.id.slider);
        ArrayList<Bundle> file_maps = new ArrayList<>();
        Bundle data1 = new Bundle();
        data1.putString("title","Selamat Datang");
        data1.putString("description","tes budaya tari Indonesia dengan cara yang menyenangkan");
        data1.putInt("drawable",R.drawable.tarian);
        file_maps.add(data1);


        Bundle data2 = new Bundle();
        data2.putString("title","PAMIRSO");
        data2.putString("description","Eksplorasi budaya tari Indonesia dengan cara yang menyenangkan");
        data2.putInt("drawable",R.drawable.belajar);
        file_maps.add(data2);

        Bundle data3 = new Bundle();
        data3.putString("title","SATRIO");
        data3.putString("description","Eksplorasi budaya tari Indonesia dengan cara yang menyenangkan");
        data3.putInt("drawable",R.drawable.satrio);
        file_maps.add(data3);

        Bundle data4 = new Bundle();
        data4.putString("title","KOREO");
        data4.putString("description","Eksplorasi budaya tari Indonesia dengan cara yang menyenangkan");
        data4.putInt("drawable",R.drawable.koreo);
        file_maps.add(data4);

        for(Bundle data : file_maps){

            SliderDescriptionView sliderView = new SliderDescriptionView(this);
            // initialize a SliderLayout
            sliderView
                    .title(data.getString("title"))
                    .description(data.getString("description"))
                    .image(data.getInt("drawable"))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            slider.addSlider(sliderView);
        }
    }

    private class SliderDescriptionView extends BaseSliderView{

        private String title;

        public SliderDescriptionView(Context context) {
            super(context);
        }

        /**
         * the extended class have to implement getView(), which is called by the adapter,
         * every extended class response to render their own view.
         *
         * @return
         */
        @Override
        public View getView() {
            View v = LayoutInflater.from(getContext()).inflate(R.layout.layout_slider,null);
            ImageView target = (ImageView)v.findViewById(R.id.daimajia_slider_image);
            TextView title = (TextView)v.findViewById(R.id.title);
            TextView description = (TextView)v.findViewById(R.id.description);
            description.setText(getDescription());
            title.setText(getTitle());
            bindEventAndShow(v, target);
            return v;
        }

        public SliderDescriptionView title(String title){
            this.title = title;
            return this;
        }


        public String getTitle() {
            return title;
        }
    }

    public void showPamirso(View v){
        startActivity(new Intent(this,PamirsoActivity.class));
    }

    public void showSatrio(View v){startActivity(new Intent(this,SatrioActivity.class));}

    public void showKoreo(View v){ startActivity(new Intent(this,TestActivity.class));}
}
