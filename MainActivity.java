package ma.projet.android.geolocalisation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Button bt_location;
    TextView textView1, textView2, textView3, textView4, textView5;
    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_location = findViewById(R.id.bt_location);
        textView1 = findViewById(R.id.text_view1);
        textView2 = findViewById(R.id.text_view2);
        textView3 = findViewById(R.id.text_view3);
        textView4 = findViewById(R.id.text_view4);
        textView5 = findViewById(R.id.text_view5);
        fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(this);
    }
    bt_location.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){
        getLocation();
    }
    })

    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 44);
        } else {

        }
    }
    usedLocationProviderClient.getLastLocation().

    addOnSuccessListener(new
                         OnSuccessListener<Location>() {
        @Override
        public void onSuccess (Location location){
            if (location != null) {
                try {

                    Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());

                    List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),
                            location.getLongitude(), 1);

                    textView1.setText(Html.fromHtml(
                            "<font color='#6200EE'><b>Latitude : </b><br></font>"
                                    + addresses.get(0).getLatitude()
                    ));
                    // afficher la longitude dans le textview
                    textView2.setText(Html.fromHtml(
                            "<font color='#6200EE'><b>Longitude : </b><br></font>"
                                    + addresses.get(0).getLongitude()
                    ));

                    textView3.setText(Html.fromHtml(
                            "<font color='#6200EE'><b>Nom de pays : </b><br></font>"
                                    + addresses.get(0).getCountryName()
                    ));

                    textView4.setText(Html.fromHtml(
                            "<font color='#6200EE'><b>Localité : </b><br></font>"
                                    + addresses.get(0).getLocality()
                    ));

                    textView5.setText(Html.fromHtml(
                            "<font color='#6200EE'><b>Adresse : </b><br></font>"
                                    + addresses.get(0).getAddressLine(0)
                    ));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Aucune position enregistrée",
                        Toast.LENGTH_SHORT).show();
            }
        }
    });
    fusedLocationProviderClient.getLastLocation().

    addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure (@NonNull Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });


}