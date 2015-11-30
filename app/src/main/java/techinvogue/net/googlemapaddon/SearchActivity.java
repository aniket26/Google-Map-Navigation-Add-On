package techinvogue.net.googlemapaddon;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class SearchActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    List<Address> addressList0=null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setUpMapIfNeeded();
    }

    public void changeView(View v)
    {
        if (mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL)//if the map type is normal, change to satellite view or vice versa
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        } else
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
    }

    public void onRouting(View v) throws IOException
    {
        AutoCompleteTextView at=(AutoCompleteTextView)findViewById(R.id.txtLocation);
        String strlocation=at.getText().toString();
        //Assigning search location to string

        if (strlocation!=null || !strlocation.equals(""))//If the search location is not specified by the user, an error will be generated
        {
            Geocoder geocoder = new Geocoder(this);
            try
            {
                addressList0 = geocoder.getFromLocationName(strlocation, 1);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            Address address0 = addressList0.get(0);
            LatLng location_latlng = new LatLng(address0.getLatitude(), address0.getLongitude());//the latlng will store latitude and longitude of the search location

            mMap.addMarker(new MarkerOptions().position(location_latlng).title("Location"));//a marker will be added at the search location

            mMap.animateCamera(CameraUpdateFactory.newLatLng(location_latlng));

        }
    }

    public void onZoom(View v)
    {
        if(v.getId()==R.id.btnZoomIn)
        {
            mMap.animateCamera(CameraUpdateFactory.zoomIn());//when the '+' button is clicked, zoom in will take place at the search location
        }
        if (v.getId()==R.id.btnZoomOut)
        {
            mMap.animateCamera(CameraUpdateFactory.zoomOut());//when the '-' button is clicked, zoom out will take place at the search location
        }
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null)
        {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null)
            {
                setUpMap();
            }
        }
    }
    private void setUpMap()
    {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));//the default marker is placed
        mMap.setMyLocationEnabled(true);
    }
}