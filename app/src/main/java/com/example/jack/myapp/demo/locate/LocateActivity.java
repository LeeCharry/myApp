package com.example.jack.myapp.demo.locate;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Looper;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.blankj.utilcode.util.CrashUtils;
import com.blankj.utilcode.util.LogUtils;
import com.example.jack.myapp.R;
import com.example.jack.myapp.widget.CrashHandler;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class LocateActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                toggleGprs();

            }
        });

//        LogUtils.a("lcy", "11");
//        initLocateListener();
//        initGprs();
//         intent = new Intent(LocateActivity.this, LocateService.class);
//        startService(intent);

//        locate2();
    }
//    private LocationUtils.OnLocationChangeListener locationChangeListener = new LocationUtils.OnLocationChangeListener() {
//        @Override
//        public void getLastKnownLocation(Location location) {
//            LogUtils.a("lcy", location.toString());
//        }
//        @Override
//        public void onLocationChanged(Location location) {
//            LogUtils.a("lcy", location.toString());
//        }
//
//        @Override
//        public void onStatusChanged(String provider, int status, Bundle extras) {
//
//        }
//    };

    private void locate2() {
//        if (LocationUtils.isLocationEnabled()) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    Looper.prepare();
//                    boolean register = LocationUtils.register(0, 0, locationChangeListener);
//                    LogUtils.a("lcy", register + "");
//                    Looper.loop();
//                }
//            }).start();
//        } else {
//            LocationUtils.openGpsSettings();
//        }
    }

    private void initLocateListener() {
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                LogUtils.a("lcy", "得到经纬度：" + location.getLongitude() + "  " + location.getLatitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
    }

    //打开（关闭）gprs
    private void toggleGprs() {
        ContentResolver contentResolver = getContentResolver();
        if (!Settings.Secure.isLocationProviderEnabled(contentResolver, LocationManager.GPS_PROVIDER)) {
            //打开
            Settings.Secure.setLocationProviderEnabled(contentResolver, LocationManager.GPS_PROVIDER, true);
        } else {
            //关闭
            Settings.Secure.setLocationProviderEnabled(contentResolver, LocationManager.GPS_PROVIDER, false);
        }
    }

    private void initGprs() {
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        ContentResolver contentResolver = getContentResolver();
        if (Settings.Secure.isLocationProviderEnabled(contentResolver, LocationManager.GPS_PROVIDER)) {
//            getGprsData();
        } else {
            startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onResume() {
        super.onResume();
//        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,locationListener);
    }

    //    @SuppressLint("MissingPermission")
//    private void getGprsData() {
//        List<String> providers = locationManager.getProviders(true);
//        for (int i = 0; i < providers.size(); i++) {
//            LogUtils.a("lcy", providers);
//        }
//        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//        LogUtils.a("lcy", location);
//        Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//        LogUtils.a("lcy",lastKnownLocation);
//    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_locate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static String articalData;
        private TextView textView;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);

            return fragment;
        }

        public void setArticalData(String articalData) {
            textView.setText(articalData);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_locate, container, false);
            textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

            new Thread(new Runnable() {
                @Override
                public void run() {
//                    requestHttp();
//                    requestByPost();
                }
            }).start();
            return rootView;
        }


        private void requestHttp() {
            // http://www.wanandroid.com/article/list/0/json
            String str = "http://www.wanandroid.com/article/list/0/json";
            InputStream is = null;
            try {
                URL url = new URL(str);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(20 * 1000);
                httpURLConnection.setReadTimeout(20 * 1000);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() == 200) {
                    is = httpURLConnection.getInputStream();
                    String result = stream2String(is);
                    LogUtils.a("lcy", result.toString());
                    textView.setText(result.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private String stream2String(InputStream is) {
            byte[] bytes = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = 0;
            try {
                if ((len = is.read(bytes)) != -1) {
                    baos.write(bytes, 0, len);
                }
                byte[] byteArray = baos.toByteArray();
                baos.close();
                is.close();
                return new String(byteArray);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        private void requestByPost() {
            // http://www.wanandroid.com/user/login
            String str = "http://www.wanandroid.com/user/login";
            OutputStream os = null;
            InputStream is = null;
            try {
                URL url = new URL(str);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout(20 * 1000);
                httpURLConnection.setReadTimeout(20 * 1000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestProperty("Content-Type", "application/json");
                httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                httpURLConnection.connect();
                os = httpURLConnection.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(os);
                String params = "{\"username\":\"lichengyan\",\"password\":\"lcy123\"}";
                params = "username=lichengyan&password=lcy123";
                dataOutputStream.write(params.getBytes());
                dataOutputStream.flush();
                dataOutputStream.close();
                if (httpURLConnection.getResponseCode() == 200) {
                    is = httpURLConnection.getInputStream();
                    String result = stream2String(is);
                    LogUtils.a("lcy", result.toString());
//                    textView.setText(result.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
