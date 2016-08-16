package model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(TrainStation.class)
public class TrainStationsList<T> {
   private List<T> trainStationList = new ArrayList<>();

   @XmlAnyElement(lax = true)
   public List<T> getTrainStationList() {
      return trainStationList;
   }
}
