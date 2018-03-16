package com.ph.shake.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;

/**
 * 项目： Shake
 * 公司： TT
 * Programmer: 潘浩
 * 时间： 18-3-14
 *
 * 轻量级数据的存储
 */

public class SpManager {

    /**
     * 存入地点
     *
     * @param context   上下文
     * @param latitude  经度
     * @param longitude 纬度
     */
    public static void putLocation(Context context, double latitude, double longitude) {
        /**
         * 存放在Location的文件中
         */
        SharedPreferences sp = context.getSharedPreferences("LOCATION", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();

        edit.putString("latitude", String.valueOf(latitude));
        edit.putString("longitude", String.valueOf(longitude));

        edit.apply();
    }

    /**
     * 获取location
     *
     * @param context 上下文
     * @return location
     */
    public static Location getLocation(Context context) {
        SharedPreferences sp = context.getSharedPreferences("LOCATION", Context.MODE_PRIVATE);

        String latitude = sp.getString("latitude", "");
        String longitude = sp.getString("longitude", "");
        if (latitude.isEmpty() || longitude.isEmpty()) {
            return null;
        }
        Location location = new Location("");
        location.setLatitude(Double.parseDouble(latitude));
        location.setLongitude(Double.parseDouble(longitude));
        return location;
    }


}
