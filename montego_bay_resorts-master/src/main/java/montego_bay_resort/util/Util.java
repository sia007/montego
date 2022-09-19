package montego_bay_resort.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Locale;
import java.util.regex.Pattern;

public class Util {
	private final static Pattern UUID_REGEX_PATTERN = Pattern.compile(
			"^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$");

	private final static Pattern EMAIL_REGEX_PATTERN = Pattern
			.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
					+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

	public static boolean validateDate(String date) {
		DateTimeFormatter f = DateTimeFormatter
				.ofPattern("uuuu-MM-dd", Locale.US).withResolverStyle(ResolverStyle.STRICT);
		try {
			LocalDate.parse(date, f);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

	public static int validateNumOfGuest(String numOfGuest) {
		try {
			return Integer.parseInt(numOfGuest);
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public static boolean validateUUID(String uuid) {
		if (uuid == null) {
			return false;
		}
		return UUID_REGEX_PATTERN.matcher(uuid).matches();
	}

	public static boolean validateEmail(String email) {
		if(email == null) {
			return false;
		}
		return EMAIL_REGEX_PATTERN.matcher(email).matches();
	}
}
