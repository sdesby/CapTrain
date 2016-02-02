package engine;

import model.TrainStation;
import org.json.simple.JSONObject;

/**
 * Created by SDesby on 02/02/2016.
 */
public class JSONTrainStationEncoder {


    public JSONObject encode(TrainStation trainStation){

        JSONObject object = new JSONObject();
        object.put("name", trainStation.getName());
        object.put("latitude", trainStation.getCoordinates().getLatitude());
        object.put("longitude", trainStation.getCoordinates().getLongitude());

        return object;
    }
}
