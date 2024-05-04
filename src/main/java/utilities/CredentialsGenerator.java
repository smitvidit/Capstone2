package utilities;

import java.util.Random;
import java.util.UUID;

public class CredentialsGenerator {

	public static String FirstName;
	public static String LastName;

	public static void main(String[] args) {
		getRandomPassword1();
		System.out.println(getRandomPassword1());

		generateRandomFirstName(6);
		generateRandomLastName(5);

		System.out.println(FirstName);
		System.out.println(LastName);

		getRandomEmail();
		System.out.println(randomEmail);
	}

	// Declare a class-level variables to store the random email & password
	private static String randomEmail;
	private static String randomPassword;

	// Generate Random Email
	public static String getRandomEmail() {
		randomEmail = "user" + System.currentTimeMillis() + "@example.com";
		// randomEmail = "user" + System.nanoTime() + "@example.com";

		randomEmail = FirstName + "_" + LastName + "@example.com";

		return randomEmail;
	}

	// Generate Random Password
	public static String getRandomPassword() {
		randomPassword = UUID.randomUUID().toString();
		return randomPassword;
	}

	public static String getRandomPassword1() {
		Random random = new Random();

		String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
		String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String SPECIAL_CHARACTERS = "@";

		StringBuilder password_StringBuild = new StringBuilder();

		// Add one lower case character
		password_StringBuild.append(LOWER_CASE.charAt(random.nextInt(LOWER_CASE.length())));

		// Add one upper case character
		password_StringBuild.append(UPPER_CASE.charAt(random.nextInt(UPPER_CASE.length())));

		// Add one special character
		password_StringBuild.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));

		// Add remaining characters
		for (int i = 0; i < 7; i++) {
			String characterSet = LOWER_CASE + UPPER_CASE + SPECIAL_CHARACTERS;
			password_StringBuild.append(characterSet.charAt(random.nextInt(characterSet.length())));
		}
		String password = password_StringBuild.toString();
		return password;
	}

	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	// Generate Random First Name
	public static String generateRandomFirstName(int length) {
		Random random = new Random();
		StringBuilder firstName_stringBuilder = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			firstName_stringBuilder.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
		}

		FirstName = firstName_stringBuilder.toString();

		return FirstName;
	}

	// Generate Random Last Name
	public static String generateRandomLastName(int length) {
		Random random = new Random();
		StringBuilder lastName_stringBuilder = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			lastName_stringBuilder.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
		}
		LastName = lastName_stringBuilder.toString();
		return LastName;
	}
}