/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_Logic.Locations;

import Business_Logic.IServices.BookingLocationsInterface;
import Business_Logic.IServices.LocationInterface;
import java.util.List;

/**
 *
 * @author lawar15
 */
public class RoomFactory {
   public static LocationInterface getRoom(int id, String nameOfTheRoom, String area, List<String> closeNeighborAreas, List<BookingLocationsInterface> bookings, int numberOfPlaces)
  {
      
      return new Room(id,nameOfTheRoom,area,closeNeighborAreas,bookings,numberOfPlaces);
  } 
}
