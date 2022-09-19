package montego_bay_resort.business;

public class RoomBean {
	private String checkIn;
	private String checkOut;
	private String roomId;
	private String roomNumber;
	private String roomSize;
	private String roomFacing;
	private String roomBasePrice;
	private String roomOccupancy;
	private String roomType;

	public RoomBean() {
		setCheckIn("-1");
		setCheckOut("-1");
		setRoomId("-1");
		setRoomNumber("-1");
		setRoomSize("-1");
		setRoomFacing("-1");
		setRoomBasePrice("-1");
		setRoomOccupancy("-1");
		setRoomType("-1");
	}

	public RoomBean(String checkIn, String checkOut, String id, String number, String size, String facing, String basePrice, String occupancy,
			String type) {
		setCheckIn(checkIn);
		setCheckOut(checkOut);
		setRoomId(id);
		setRoomNumber(number);
		setRoomSize(size);
		setRoomFacing(facing);
		setRoomBasePrice(basePrice);
		setRoomOccupancy(occupancy);
		setRoomType(type);
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(String roomSize) {
		this.roomSize = roomSize;
	}

	public String getRoomFacing() {
		return roomFacing;
	}

	public void setRoomFacing(String roomFacing) {
		this.roomFacing = roomFacing;
	}

	public String getRoomBasePrice() {
		return roomBasePrice;
	}

	public void setRoomBasePrice(String roomBasePrice) {
		this.roomBasePrice = roomBasePrice;
	}

	public String getRoomOccupancy() {
		return roomOccupancy;
	}

	public void setRoomOccupancy(String roomOccupancy) {
		this.roomOccupancy = roomOccupancy;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}

	public String getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}

}
