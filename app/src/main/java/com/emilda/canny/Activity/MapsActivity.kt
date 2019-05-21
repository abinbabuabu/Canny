package com.emilda.canny.Activity

import android.graphics.Color
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.emilda.canny.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    /**
     * Obtain from firebase the Zip codes that we are available in and then Create Markers
     * and Show it in the map View.
     * Create Polygons Out of the area we are delivering rather than creating circles
     */

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val marker = LatLng(12.910474299999999, 77.4841775)
        mMap.addMarker(MarkerOptions().position(marker).title("560060"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
       mMap.setMinZoomPreference(12.5f)
        val circleOptions =CircleOptions().center(marker).radius(120.0).strokeColor(
            Color.argb(10,29,233,182) ).fillColor(Color.argb(100,29,233,182))
        mMap.addCircle(circleOptions)

        checkAndPlaceMarker()


    }

    private fun checkAndPlaceMarker(){
        check_btn.setOnClickListener {
            val zip = zip_code.text.toString()
            if(zip.length.equals(6)){
                val geocoder = Geocoder(this)
                val result= geocoder.getFromLocationName(zip,1)
                val lat= result[0].latitude
                val lng =result[0].longitude
                val marker =LatLng(lat,lng)
                mMap.addMarker(MarkerOptions().position(marker).title("Your Location"))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            }
        }

    }
}
