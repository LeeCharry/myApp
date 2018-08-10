package com.example.jack.myapp.demo.customize;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.example.jack.myapp.AppConstant;
import com.example.jack.myapp.R;
import com.example.jack.myapp.demo.data.Log;

import java.text.DecimalFormat;

/**
 * 仿支付宝支付弹出框
 */
public class PasswordBoxActivity extends AppCompatActivity {
    private String passStr = "123456";
    private LocationClientOption option = new LocationClientOption();
    private GeoCoder geoCoder;
//    private PasswordBoxView pwd_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_input_box);

        final PasswordBoxView passwordBoxView = new PasswordBoxView(this);


        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 passwordBoxView.showPasswordBox();

//                 startLocate();
//                 latlngToAddress(new LatLng(32.287981d,118.775194d));
//                 latlngToAddress(new LatLng(31.24642,121.336335)); //121.336335,31.24642
             }
         });

         geoCoder = GeoCoder.newInstance();

        passwordBoxView.setOnInputCompleteListener(new PasswordBoxView.OnInputCompleteListener() {
            @Override
            public void inputComplete(String input) {
                if (!input.equals(passStr)) {
                    ToastUtils.showShort("密码不正确！");
                }else{
                    ToastUtils.showShort("恭喜，密码正确！");
                    passwordBoxView.hidePasswordBox();
                }
            }
        });
    }

    public LocationClient mLocationClient = null;
    private void startLocate() {
        mLocationClient = new LocationClient(this);
        // 声明LocationClient类
        mLocationClient.registerLocationListener(mListener);
        setOpatation();
        mLocationClient.start(); // 开启定位服务

        LogUtils.a(AppConstant.TAG,getDecimal(String.valueOf(12.2539641666))+"");
        LogUtils.a(AppConstant.TAG,getDecimal(String.valueOf(31.24642))+"");
        LogUtils.a(AppConstant.TAG,getDecimal(String.valueOf(121.335))+"");
    }

    /**
     * 保留六位小数
     * @param f
     * @return
     */
    private String  getDecimal(String f) {
       return new DecimalFormat("#,##0.000000").format(Double.parseDouble(f));
    }

    private void latlngToAddress(LatLng latlng) {
        // 设置反地理经纬度坐标,请求位置时,需要一个经纬度
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latlng));

        //设置地址或经纬度反编译后的监听,这里有两个回调方法,

        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {

            //经纬度转换成地址

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                if (result == null ||  result.error != SearchResult.ERRORNO.NO_ERROR) {
                    Toast.makeText(PasswordBoxActivity.this, "找不到该地址!",Toast.LENGTH_SHORT).show();
                }
                LogUtils.a(AppConstant.TAG,result.getAddress());
                ReverseGeoCodeResult.AddressComponent addressDetail = result.getAddressDetail();
                LogUtils.a(AppConstant.TAG,addressDetail.countryName+addressDetail.province+addressDetail.city+addressDetail.district+addressDetail.street+addressDetail.streetNumber);
            }
        //把地址转换成经纬度
            @Override
            public void onGetGeoCodeResult(GeoCodeResult result) {
                // 详细地址转换在经纬度

                String address=result.getAddress();
            }
        });
    }


    OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
        public void onGetGeoCodeResult(GeoCodeResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有检索到结果
            }
            //获取地理编码结果
        }

        @Override
        public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
            if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                //没有找到检索结果
            }
            //获取反向地理编码结果
        }
    };



    public void setOpatation() {
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        // 可选，设置定位模式，默认高精度
        // LocationMode.Hight_Accuracy：高精度；
        // LocationMode. Battery_Saving：低功耗；
        // LocationMode. Device_Sensors：仅使用设备；

        option.setCoorType("bd09ll");
        // 可选，设置返回经纬度坐标类型，默认gcj02
        // gcj02：国测局坐标；
        // bd09ll：百度经纬度坐标；
        // bd09：百度墨卡托坐标；
        // 海外地区定位，无需设置坐标类型，统一返回wgs84类型坐标；

//        option.setScanSpan(1000 * 60 * 5); // 这里设置5分钟定位一次
        option.setScanSpan(1000 * 30 * 1); // 这里设置5分钟定位一次
        // 可选，设置发起定位请求的间隔，int类型，单位ms
        // 如果设置为0，则代表单次定位，即仅定位一次，默认为0
        // 如果设置非0，需设置1000ms以上才有效

        option.setOpenGps(true);
        // 可选，设置是否使用gps，默认false
        // 使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(true);
        // 可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(false);
          // 可选，定位SDK内部是一个service，并放到了独立进程。
        // 设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

        // option.setIgnoreCacheException(false);
        // 可选，设置是否收集Crash信息，默认收集，即参数为false

        // option.setWifiValidTime(5*60*1000);
        // 可选，7.2版本新增能力
        // 如果设置了该接口，首次启动定位时，会先判断当前WiFi是否超出有效期，若超出有效期，会先重新扫描WiFi，然后定位

        option.setAddrType("all");  // 定位的街道类型，只有设定为all才可以全部显示
        option.setEnableSimulateGps(false);
        // 可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false

        option.setIsNeedAddress(true);// 可选，设置是否需要地址信息，默认不需要
        option.setIsNeedLocationDescribe(true);// 可选，设置是否需要地址描述
        option.setNeedDeviceDirect(false);// 可选，设置是否需要设备方向结果
        option.setLocationNotify(false);// 可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIgnoreKillProcess(true);// 可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.setIsNeedLocationDescribe(true);// 可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);// 可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.SetIgnoreCacheException(false);// 可选，默认false，设置是否收集CRASH信息，默认收集

        mLocationClient.setLocOption(option);
        // mLocationClient为第二步初始化过的LocationClient对象
        // 需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        // 更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
    }

    private String longtitude;
    private String locationStr;
    private String latitude;
    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {


            LogUtils.a(AppConstant.TAG,location.getLongitude()+"   "+location.getLatitude()+"  "+location.getLocType());
            // TODO Auto-generated method stub
            locationStr = "";
            longtitude = "";
            latitude = "";
            if (null != location
                    && location.getLocType() != BDLocation.TypeServerError) {
                StringBuffer sb = new StringBuffer(256);
                StringBuffer sbTemp = new StringBuffer(256);
                sb.append("time : ");
                /**
                 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
                 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
                 */
                sb.append(location.getTime());
                sb.append("\nlocType : ");// 定位类型
                sb.append(location.getLocType());
                sb.append("\nlocType description : ");// *****对应的定位类型说明*****
                sb.append(location.getLocTypeDescription());
                sb.append("\nlatitude : ");// 纬度
                sb.append(location.getLatitude());
                sb.append("\nlontitude : ");// 经度
                sb.append(location.getLongitude());
                sb.append("\nradius : ");// 半径
                sb.append(location.getRadius());
                sb.append("\nCountryCode : ");// 国家码
                sb.append(location.getCountryCode());
                sb.append("\nCountry : ");// 国家名称
                sb.append(location.getCountry());
                sb.append("\ncitycode : ");// 城市编码
                sb.append(location.getCityCode());
                sb.append("\ncity : ");// 城市
                sb.append(location.getCity());
                sb.append("\nDistrict : ");// 区
                sb.append(location.getDistrict());
                sb.append("\nStreet : ");// 街道
                sb.append(location.getStreet());
                sb.append("\naddr : ");// 地址信息
                sb.append(location.getAddrStr());
                sb.append("\nUserIndoorState: ");// *****返回用户室内外判断结果*****
                sb.append(location.getUserIndoorState());
                sb.append("\nDirection(not all devices have value): ");
                sb.append(location.getDirection());// 方向
                sb.append("\nlocationdescribe: ");
                sb.append(location.getLocationDescribe());// 位置语义化信息
                sb.append("\nPoi: ");// POI信息
                if (location.getPoiList() != null
                        && !location.getPoiList().isEmpty()) {
                    for (int i = 0; i < location.getPoiList().size(); i++) {
                        Poi poi = (Poi) location.getPoiList().get(i);
                        sb.append(poi.getName() + ";");
                    }
                }
//                locationType = location.getLocType();
                // 定位流数据
                sbTemp.append(location.getLatitude()); // 纬度
                sbTemp.append(";");
                sbTemp.append(location.getLongitude()); // 精度

                longtitude = String.valueOf(location.getLongitude());
                latitude = String.valueOf(location.getLatitude());


//                logMsg(sbTemp.toString());
                // 定位流数据end
                Log.getInstance().writeLog(
                        "=====>最近一次定位时间：" + location.getTime() + "定位的经纬度为："
                                + longtitude + ";" + latitude); // 在这里输出下日志,

                //2018-06-13 (登陆成功后，)
                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                    sb.append("\nspeed : ");
                    sb.append(location.getSpeed());// 速度 单位：km/h
                    sb.append("\nsatellite : ");
                    sb.append(location.getSatelliteNumber());// 卫星数目
                    sb.append("\nheight : ");
                    sb.append(location.getAltitude());// 海拔高度 单位：米
                    sb.append("\ngps status : ");
                    sb.append(location.getGpsAccuracyStatus());// *****gps质量判断*****
                    sb.append("\ndescribe : ");
                    sb.append("gps定位成功");

                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                    // 运营商信息
                    if (location.hasAltitude()) {// *****如果有海拔高度*****
                        sb.append("\nheight : ");
                        sb.append(location.getAltitude());// 单位：米
                    }
                    sb.append("\noperationers : ");// 运营商信息
                    sb.append(location.getOperators());
                    sb.append("\ndescribe : ");
                    sb.append("网络定位成功");
                    Log.getInstance().writeLog("=====>最近一次定位汉语说明：" + "网络定位成功"); // 在这里输出下日志,

                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                    sb.append("\ndescribe : ");
                    sb.append("离线定位成功，离线定位结果也是有效的");
                    Log.getInstance().writeLog(
                            "=====>最近一次定位汉语说明：" + "离线定位成功，离线定位结果也是有效的"); // 在这里输出下日志,

                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    sb.append("\ndescribe : ");
                    sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                    Log.getInstance()
                            .writeLog(
                                    "=====>最近一次定位汉语说明："
                                            + "服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    sb.append("\ndescribe : ");
                    sb.append("网络不同导致定位失败，请检查网络是否通畅");
                    Log.getInstance().writeLog("=====>网络不同导致定位失败，请检查网络是否通畅");
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    sb.append("\ndescribe : ");
                    sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                    Log.getInstance()
                            .writeLog(
                                    "=====>无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                } else {
                    Log.getInstance().writeLog("===>其他原因待查");
                }
            }
        }

    };

}
