package Extra.Location;

import org.json.JSONException;
import org.json.JSONObject;

import static Extra.Extra.splitLatLong;

public abstract class LocationHandler {
    protected Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocationHandler(Location location) {
        this.location = location;
    }

    public LocationHandler(JSONObject jObj) {
        try {
            String geoLoc = jObj.getString("LatLng");
            Double[] xyCor = splitLatLong(geoLoc);

            this.location = new Location(xyCor[0], xyCor[1]);
        } catch (JSONException ex) {
            Double latitude = Double.parseDouble(jObj.getString("LATITUDE"));
            Double longitude = Double.parseDouble(jObj.getString("LONGITUDE"));
            this.location = new Location(latitude, longitude);
        }
    }
}
