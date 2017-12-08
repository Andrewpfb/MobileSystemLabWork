package example.mapslocation.Services;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import example.mapslocation.Helpers.JsonHelper;
import example.mapslocation.Models.LocationInfo;
import example.mapslocation.Utils.CommonLocationList;

import static java.lang.Math.abs;

public class LocationService extends Service {

    private LocationListener locationListener;
    private LocationManager locationManager;
    private Context context;
    private Calendar StartTime,EndTime;

        @Override
        public IBinder onBind(Intent intent) {
            // TODO: Return the communication channel to the service.
            throw new UnsupportedOperationException("Not yet implemented");
        }

        @Override
        public void onCreate() {
            StartTime = Calendar.getInstance();
            Toast.makeText(this, "Service start", Toast.LENGTH_SHORT).show();
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            context = getApplicationContext();
            if (CommonLocationList.locationInfos == null) {
                CommonLocationList.locationInfos = new ArrayList<>();
            }
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    CommonLocationList.locationInfos.add(new LocationInfo(
                            location.getLatitude(),
                            location.getLongitude(),
                            new Date(location.getTime()),
                            location.getSpeed(),
                            location.getAltitude()
                    ));
                    //Toast.makeText(getApplicationContext(), formatLocation(location), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onProviderDisabled(String provider) {
                }

                @Override
                public void onProviderEnabled(String provider) {
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                }
            };
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            }
            try {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        1000 * 1, 1, locationListener);
               /* locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER, 1000 * 1, 1,
                        locationListener);*/
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        EndTime = Calendar.getInstance();
        Toast.makeText(this, "Service stop ", Toast.LENGTH_SHORT).show();
        locationManager.removeUpdates(locationListener);
        if(JsonHelper.exportToJSON(context,CommonLocationList.locationInfos)) {
            Toast.makeText(context, "Track was saved", Toast.LENGTH_SHORT).show();
        }
        if(CommonLocationList.locationInfos.size()==0){
            Toast.makeText(context, "The location did not change", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Speed is: " + GetSpeed() + " coordinates in second", Toast.LENGTH_SHORT).show();
        }
        startService(new Intent(context,LocationService.class));
    }

    private String GetSpeed(){
        int size = CommonLocationList.locationInfos.size();
        Double firstX = CommonLocationList.locationInfos.get(0).getLatitude();
        Double firstY = CommonLocationList.locationInfos.get(0).getLongitude();
        Double secondX = CommonLocationList.locationInfos.get(size-1).getLatitude();
        Double secondY = CommonLocationList.locationInfos.get(size-1).getLongitude();
        Double distance = Math.sqrt( (abs(firstX-secondX))*(abs(firstX-secondX)) + (abs(firstY-secondY))*(abs(firstY-secondY)) );
        Calendar calculate = Calendar.getInstance();
        calculate.setTime(new Date(EndTime.getTime().getTime() - StartTime.getTime().getTime()));
        int time = calculate.get(Calendar.SECOND);
        Double speed = distance/time;
        return speed.toString();
    }
}