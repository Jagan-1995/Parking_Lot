package ParkingLot.factory;

import ParkingLot.models.SpotAssignmentStrategyType;
import ParkingLot.strategies.CheapestSpotAssignmentStrategy;
import ParkingLot.strategies.NearestSpotAssignmentStrategy;
import ParkingLot.strategies.SpotAssignmentStrategy;

public class SpotAssignmentStrategyFactory {
    public static SpotAssignmentStrategy getSpotAssignmentStrategyForType(SpotAssignmentStrategyType spotAssignmentStrategyType){
        if (spotAssignmentStrategyType.equals(SpotAssignmentStrategyType.NEAREST)){
            return new NearestSpotAssignmentStrategy();
        } else if (spotAssignmentStrategyType.equals(SpotAssignmentStrategyType.NEAREST)) {
            return new CheapestSpotAssignmentStrategy();
        }else {
            return null;
        }
//        return new RandomSpotAssignmentStrategy();
    }
}
