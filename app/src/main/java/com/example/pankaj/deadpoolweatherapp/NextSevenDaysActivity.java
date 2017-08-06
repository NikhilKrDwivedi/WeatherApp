package com.example.pankaj.deadpoolweatherapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
/*This class get weather information from intent list and update listview*/
public class NextSevenDaysActivity extends AppCompatActivity {

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next_seven_days);
        listView=(ListView) findViewById(R.id.weatherList);


        try{
            //get Intent information parse using serializable for custom class WeatherInfo
            Intent i=getIntent();
            listView.setAdapter(new CustomAdapterWeatherList(this,(List<WeatherInfo>) i.getSerializableExtra("list")));
        }catch (Exception e){

        }



    }

    /*This is Private Custom Adapter to to fill the data in Listview and perform action on list item click*/

    private class CustomAdapterWeatherList extends BaseAdapter {

        private Context context;
        private NextSevenDaysActivity mActivity;
        private List<WeatherInfo> weatherInfoList=new ArrayList<>();
        private LayoutInflater inflater=null;

        public CustomAdapterWeatherList(NextSevenDaysActivity mActivity, List<WeatherInfo> weatherInfoList) {


            context=mActivity;
            this.mActivity=mActivity;
            this.weatherInfoList=weatherInfoList;

            inflater = ( LayoutInflater )context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        @Override
        public int getCount() {

            return weatherInfoList.size();
        }

        @Override
        public Object getItem(int position) {

            return position;
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        private class Holder
        {
            TextView txtDate,txtMin,txtMax,txtDay,txtNight,txtMorning,txtEvening,txtMain,txtDescription;

        }
        @Override
        public View getView(final int position, final View convertView, final ViewGroup parent) {

            Holder holder1;
            View rowView1=convertView;
            if(rowView1==null){
                holder1=new Holder();
                rowView1 = inflater.inflate(R.layout.weather_info_template, null);
                holder1.txtDate=(TextView) rowView1.findViewById(R.id.txtDateDay);
                holder1.txtMin=(TextView) rowView1.findViewById(R.id.txtMin);
                holder1.txtMax=(TextView) rowView1.findViewById(R.id.txtMax);
                holder1.txtDay=(TextView) rowView1.findViewById(R.id.txtDaytemp);
                holder1.txtNight=(TextView) rowView1.findViewById(R.id.txtNighttemp);
                holder1.txtMorning=(TextView) rowView1.findViewById(R.id.txtMorningtemp);
                holder1.txtEvening=(TextView) rowView1.findViewById(R.id.txtEveingtemp);
                holder1.txtMain=(TextView) rowView1.findViewById(R.id.txtmaininfo);
                holder1.txtDescription=(TextView) rowView1.findViewById(R.id.txtmaininfoDescription);
                rowView1.setTag(holder1);
            }else{
                //holder=(Holder) rowView.getTag();
                holder1=(Holder) rowView1.getTag();
            }



            holder1.txtDate.setText(weatherInfoList.get(position).getWeatherDate());
            holder1.txtMin.setText(String.valueOf("Min: "+weatherInfoList.get(position).getMinTemp()+" \u2103"));
            holder1.txtMax.setText(String.valueOf("Max: "+weatherInfoList.get(position).getMaxTemp()+" \u2103"));
            holder1.txtDay.setText(String.valueOf("Day: "+weatherInfoList.get(position).getDayTemp()+" \u2103"));
            holder1.txtNight.setText(String.valueOf("Night: "+weatherInfoList.get(position).getNightTemp()+" \u2103"));
            holder1.txtMorning.setText(String.valueOf("Morning: "+weatherInfoList.get(position).getMorningTemp()+" \u2103"));
            holder1.txtEvening.setText(String.valueOf("Evening: "+weatherInfoList.get(position).getEveningTemp()+" \u2103"));
            holder1.txtMain.setText("Weather: "+weatherInfoList.get(position).getMainMsg());
            holder1.txtDescription.setText("Description: "+weatherInfoList.get(position).getDescription());


            rowView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                }
            });
            return rowView1;
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.item_change_city:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
