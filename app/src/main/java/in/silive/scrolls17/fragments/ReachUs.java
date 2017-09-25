package in.silive.scrolls17.fragments;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

import in.silive.scrolls17.R;


/**
 * Created by kone on 12/9/15.
 */
public class ReachUs extends Fragment implements RoutingListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, OnMapReadyCallback {
    private static final LocationRequest REQUEST = LocationRequest.create()
            .setInterval(5000)         // 5 seconds
            .setFastestInterval(3000)    // 16ms = 60fps
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    private static final int MY_PERMISSIONS_ACCESS_FINE_LOCATION = 345;
    public static ReachUs instance;
    private static SupportMapFragment mapFragment;
    Location lastlocation;
    private GoogleMap mMap;
    private View rootView;
    private LatLng latLngAKGEC = new LatLng(28.676564, 77.500242), startLocation;
    private ProgressDialog progressDialog;
    private GoogleApiClient mGoogleApiClient;
    private boolean locationLoaded;
    private boolean pathLoaded;
    private boolean connectedToAPI;

    public static ReachUs getInstance() {
        if (instance == null) {
            instance = new ReachUs();
        }
        return instance;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case MY_PERMISSIONS_ACCESS_FINE_LOCATION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                            getLoc();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_reach_us, container, false);
            android.support.v4.app.FragmentManager fManager = getChildFragmentManager();
            mapFragment = (SupportMapFragment) fManager.findFragmentById(R.id.map);
            rootView.findViewById(R.id.bottom_sheet).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mMap!=null){
                        addAKGECMarker();
                    }
                }
            });
            if (mapFragment == null) {
                mapFragment = SupportMapFragment.newInstance();
                fManager.beginTransaction().replace(R.id.map, mapFragment).commit();
            }
            setUpGoogleApiClientIfNeeded();

            mGoogleApiClient.connect();
        }
        if (mMap == null) {
            mapFragment.getMapAsync(this);
        }
        if (locationLoaded && !pathLoaded)
            loadPath();
        return rootView;
    }

    private void setUpGoogleApiClientIfNeeded() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
        }
    }


    @Override
    public void onStart() {

        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onRoutingFailure(RouteException var1) {
        //progressDialog.dismiss();
        //  Toast.makeText(getActivity(), "Internet connection not available", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onRoutingStart() {

    }

    @Override
    public void onRoutingSuccess(ArrayList<Route> route, int shortestRouteIndex) {
        try {

                PolylineOptions polyOptions = new PolylineOptions();
                polyOptions.color(ContextCompat.getColor(getContext(), R.color.colorPrimaryDark));
                polyOptions.width(10);
                polyOptions.addAll(route.get(shortestRouteIndex).getPoints());
                Polyline polyline = mMap.addPolyline(polyOptions);
                // Start marker
                MarkerOptions options = new MarkerOptions();
                options.position(startLocation);
                options.icon(BitmapDescriptorFactory.fromResource(R.drawable.location_pointer));
                mMap.addMarker(options);
                pathLoaded = true;
            Log.d("Scrolls","Maps - Path loaded");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onRoutingCancelled() {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)));


        mMap.getUiSettings().setZoomControlsEnabled(true);
        // End marker
        addAKGECMarker();

    }
    public void addAKGECMarker(){
        MarkerOptions options = new MarkerOptions();
        options.position(latLngAKGEC);
        options.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_akgec));
        mMap.addMarker(options);
        CameraUpdate center = CameraUpdateFactory.newLatLng(latLngAKGEC);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(16);
        mMap.moveCamera(center);
        mMap.animateCamera(zoom, 100, null);
    }

    @Override
    public void onConnected(Bundle bundle) {
        connectedToAPI = true;
if (isVisible()) {

    getLoc();
}
    }
    @Override
    public void setUserVisibleHint(boolean visible){
        super.setUserVisibleHint(visible);
        if (visible && isResumed()){
            if (locationLoaded && !pathLoaded && connectedToAPI)
                loadPath();
        }
    }

    public void getLoc(){
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_ACCESS_FINE_LOCATION);
            return;
        }
        lastlocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (lastlocation != null) {
            startLocation = new LatLng(lastlocation.getLatitude(), lastlocation.getLongitude());
            locationLoaded = true;
            loadPath();
            Log.d("Scrolls","Maps - Path Req");
        }
    }


    public void loadPath() {
        try {
          /*  progressDialog = ProgressDialog.show(getActivity(), "Please wait.",
                    "Fetching route information.", true);*/
            if (startLocation!=null) {
                Routing routing = new Routing.Builder()
                        .travelMode(AbstractRouting.TravelMode.WALKING)
                        .withListener(ReachUs.this)
                        .waypoints(startLocation, latLngAKGEC)
                        .build();
                routing.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }


}