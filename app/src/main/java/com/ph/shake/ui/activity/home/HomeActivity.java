package com.ph.shake.ui.activity.home;

import android.graphics.Point;
import android.location.Location;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.Projection;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.ph.lib.BaseActivity;
import com.ph.lib.injector.LayoutId;
import com.ph.lib.injector.Presenter;
import com.ph.shake.R;
import com.ph.shake.manager.SpManager;


/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-9
 * 地图承载Activity
 */
@LayoutId(R.layout.drawlayout_home)
@Presenter(HomePresenter.class)
public class HomeActivity extends BaseActivity<HomePresenter, IHomeView> implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private MapView mapView;
    private AMap map;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView = findViewById(R.id.map);
        toolbar = findViewById(R.id.tool_bar);
        //初始化抽屉和Menu菜单
        initNavgation();
        //根据缓存设置默认区域
        initArea(savedInstanceState);
        //初始化定位
        initLocation();
        //初始化地图Ui
        initUISetting();
    }

    private void initNavgation() {
        toolbar.inflateMenu(R.menu.action_menu);

        DrawerLayout drawer = findViewById(R.id.draw_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * 重新设置初始化视图，使得位置为上一次缓存的经纬度
     *
     * @param savedInstanceState bundle
     */
    private void initArea(Bundle savedInstanceState) {
        Location location = SpManager.getLocation(this);
        if (location != null) {
            Log.e("ph", "initArea: 取值时 la " + location.getLatitude() + "long " + location.getLongitude());
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            AMapOptions options = new AMapOptions();
            options.camera(new CameraPosition(latLng, 19, 30, 0));
            //动态布局 添加参数化之后的map
            LinearLayout linearLayout = findViewById(R.id.home_linear_layout);
            linearLayout.removeView(mapView);
            //移除掉之前的mapView并使用新的配置参数的mapView
            mapView = new MapView(this, options);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mapView.setLayoutParams(params);
            linearLayout.addView(mapView);
        }
        mapView.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        initCache();
        mapView.onDestroy();
    }

    private void initLocation() {
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//定位一次，且将视角移动到地图中心点。
        myLocationStyle.radiusFillColor(R.color.primary);

        CameraUpdate mCameraUpdate = CameraUpdateFactory.zoomTo(19);//设置地图缩放级别  从1～19
        map = mapView.getMap();//延时加载先定位完毕再加载map信息
        map.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        map.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        map.moveCamera(mCameraUpdate);//设置地图缩放级别等参数

        /**
         * 地图中心点
         */
        map.setOnCameraChangeListener(new AMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                LatLng target = cameraPosition.target;
                double latitude = target.latitude;
                double longitude = target.longitude;
            }

            @Override
            public void onCameraChangeFinish(CameraPosition cameraPosition) {
                float bearing = cameraPosition.bearing;
                float zoom = cameraPosition.zoom;
            }
        });

        //添加 marker 标记
        LatLng latLng = new LatLng(39.906901, 116.397972);
        final Marker marker = map.addMarker(new MarkerOptions().position(latLng).title("北京").snippet("DefaultMarker"));
    }

    private void initUISetting() {
        UiSettings uiSettings = map.getUiSettings();
        uiSettings.setZoomControlsEnabled(false);
        uiSettings.setCompassEnabled(true);
        uiSettings.setScaleControlsEnabled(true);
    }

    /**
     * 获取屏幕中心点
     *
     * @return
     */
    public LatLng getMapCenterPoint() {
        int left = mapView.getLeft();
        int top = mapView.getTop();
        int right = mapView.getRight();
        int bottom = mapView.getBottom();
        // 获得屏幕点击的位置
        int x = (int) (mapView.getX() + (right - left) / 2);
        int y = (int) (mapView.getY() + (bottom - top) / 2);
        Projection projection = mapView.getMap().getProjection();
        return projection.fromScreenLocation(new Point(x, y));
    }

    /**
     * 重新设置当前位置缓存，下次加载视图从该位置开始加载
     */
    private void initCache() {
        //获取当前经纬度
        CameraPosition cameraPosition = map.getCameraPosition();
        LatLng target = cameraPosition.target;
        double latitude = target.latitude;
        double longitude = target.longitude;
        Log.e("ph", "initLocation: 赋值时 la " + latitude + "long " + longitude);
        SpManager.putLocation(this, latitude, longitude);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        DrawerLayout drawer = findViewById(R.id.draw_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 持续定位监听   估计很定是耗时的
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            String city = location.getCity();
        }
    };

}
