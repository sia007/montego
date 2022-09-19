package montego_bay_resort.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class room {
	private String checkin;
	private String checkout;
	private int numOfGuest;
	private String roomId;
	private int roomNumber;
	private int roomSize;
	private int roomBasePrice;
	private int roomOccupancy;
	private String roomType;
	private String roomFacing;
	private  ArrayList<room> room_list;
	private int totalcost;
	private String name;
	private String email;
	private String reservation;

	public String getReservation() {
		return this.reservation;
	}

	public void setReservation(String r) {
		this.reservation = r;
	}
  
	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

	public int getNumOfGuest() {
		return numOfGuest;
	}

	public void setNumOfGuest(int numOfGuest) {
		this.numOfGuest = numOfGuest;
	}

	public int roomsSize() {
		return this.room_list.size();
	}
	
	public room(final HttpServletRequest request) {

	}
  
	public  ArrayList<room> getRoomList(){
		return this.room_list;
	}

	public void setRoomList( ArrayList<room> a) {
		this.room_list = a;
		 
	}
  
	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public void setRoomSize(int roomSize) {
		this.roomSize = roomSize;
	}
  
	public int getRoomSize() {
		return this.roomSize;
	}

	public int getRoomBasePrice() {
		return roomBasePrice;
	}

	public void setRoomBasePrice(int roomBasePrice) {
		this.roomBasePrice = roomBasePrice;
	}

	public int getRoomOccupancy() {
		return roomOccupancy;
	}

	public void setRoomOccupancy(int roomOccupancy) {
		this.roomOccupancy = roomOccupancy;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomFacing() {
		return roomFacing;
	}

	public void setRoomFacing(String roomFacing) {
		this.roomFacing = roomFacing;
	}
  
    public void removeRoom(int remove) {
    	this.room_list.remove(remove);

    }
    
    public void addRoom(room r1) {
    	this.room_list.add(r1);

    	
    }
    public void setTotalCost(int cost) {
    	this.totalcost = cost;
    }
    
    public int getTotalCost() {
    	this.totalcost =0;
    	int length = this.room_list.size();
    	 for(int i = 0; i < length; i++) {
    		 totalcost = totalcost + this.room_list.get(i).getRoomBasePrice();
    	     
    	    }
    	return totalcost;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
   
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }
    
}
