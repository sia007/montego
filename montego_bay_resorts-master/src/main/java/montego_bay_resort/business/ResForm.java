package montego_bay_resort.business;

import java.util.List;

public class ResForm {
	private String checkin;
	private String checkout;
	private List<RoomBean> searchResults;

	/**
	 * Class constructor which sets defaults that are used to validate if the
	 * form is filled out 
	 */

	public ResForm() {
		setCheckin("");
		setCheckout("");
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

	public List<RoomBean> getSearchResults() {
		return searchResults;
	}

	public void setSearchResults(List<RoomBean> searchResults) {
		this.searchResults = searchResults;
	}
  
	public void removeRoom(int remove) {
    	this.searchResults.remove(remove);

    }
}
