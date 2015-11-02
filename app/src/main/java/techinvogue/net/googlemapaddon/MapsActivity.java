package techinvogue.net.googlemapaddon;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class MapsActivity extends FragmentActivity
{
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    List<Address> addressList0=null; //Address (latitude and longitude) of the source
    List<Address> addressList1=null;//Address (latitude and longitude) of the destination
    List<Address> addressList2=null;//Address (latitude and longitude) of the stop-off location
    //delete
    ArrayList<LatLng> markerPoints;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        setUpMapIfNeeded();
    }
    public void onRouting(View v) throws IOException
    {
        EditText txtsrc = (EditText)findViewById(R.id.txtSrc);
        String strsrc=txtsrc.getText().toString();              //parsing the edit text into string for source location
        EditText txtdestn=(EditText)findViewById(R.id.txtDestn);
        String strdestn=txtdestn.getText().toString();          //parsing the edit text into string for destination
        EditText txtstopoff=(EditText)findViewById(R.id.txtStopOff);
        String strstopoff=txtstopoff.getText().toString();      //parsing the edit text into string for stop - off location
        if (strsrc!=null || !strsrc.equals("")|| strdestn!=null || !strdestn.equals(""))
        //checks if source and destination are entered as inputs
        {
            Geocoder geocoder = new Geocoder(this);
            try {
                //When source string is current location in any case we accept the GPS co-ordinates of the place the person is currently using mobile device from
                if (strsrc.equalsIgnoreCase("Current Location"))
                {
                    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE); //Enables Location Services
                    Criteria criteria = new Criteria();
                    String provider = locationManager.getBestProvider(criteria, false);
                    if (provider != null && !provider.equals(""))
                    {
                        if( mMap.getMyLocation() !=null)
                        {
                            List<Address> aLc=null; //Address (latitude and longitude) of the current Location
                            aLc=geocoder.getFromLocation(mMap.getMyLocation().getLatitude(),mMap.getMyLocation().getLongitude(),1);
                            Address current= aLc.get(0);
                            LatLng srclatlng1 = new LatLng(current.getLatitude(), current.getLongitude());//gets the latitude and longitude of the current location
                            mMap.addMarker(new MarkerOptions().position(srclatlng1).title("Source")); //adds marker to the source of current location
                        }
                    }
                    //When user enters a string for his/her source location
                    else {
                        addressList0 = geocoder.getFromLocationName(strsrc, 1);//
                        Address address0 = addressList0.get(0);
                        LatLng srclatlng = new LatLng(address0.getLatitude(), address0.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(srclatlng).title("Source")); //adds marker to the source of current location
                    }
                    addressList1 = geocoder.getFromLocationName(strdestn, 1);
                    addressList2 = geocoder.getFromLocationName(strstopoff, 1);
                }
            }
            catch(IOException ex)
            {
                ex.printStackTrace();
            }
            Address address1 = addressList1.get(0);
            Address address2 = addressList2.get(0);
            LatLng destnlatlng = new LatLng(address1.getLatitude(), address1.getLongitude()); //destnlatlng will store the latitude and longitude of the destination
            LatLng stopofflatlng = new LatLng(address2.getLatitude(), address2.getLongitude());//stopofflatlng will store the latitude and longitude of the stop-off location
            mMap.addMarker(new MarkerOptions().position(destnlatlng).title("Destination")); //Will add a marker to the destination
            mMap.addMarker(new MarkerOptions().position(stopofflatlng).title("Stop - Off Location"));//Will add a marker to the stop - off location
            mMap.animateCamera(CameraUpdateFactory.newLatLng(destnlatlng));// Zoom in the camera to the destination place
        }
    }
    public void onZoom(View v)
    {
        // Zoom in function using + button on the UI
        if(v.getId()==R.id.btnZoomIn)
        {
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        }
        // Zoom out function using - button on the UI
        if (v.getId()==R.id.btnZoomOut)
        {
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
        }
    }
    public void onmylocation(View v)
    {
        //Click of the current location button
        if(v.getId()==R.id.btn_mylocation)
        {
            EditText source=(EditText)findViewById(R.id.txtSrc);
            source.setText("Current Location");
        }
    }
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }
    private void setUpMap()
    {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));//Default location at lat and longitude 0,0 named as Marker
        mMap.setMyLocationEnabled(true);
    }
}
