package acsse.csc03a3.obj;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

	private List<Person> registeredUsers;

	public FileHandler() {
		registeredUsers = new ArrayList<>();
	}

	public void registerUser(Person person) {
		registeredUsers.add(person);
		saveUserData();
	}

	public boolean loginUser(String username, String id) {
		for (Person user : registeredUsers) {
			if (user.getUsername().equals(username) && user.getUserid().equals(id)) {
				return true; 
			}
		}
		return false; 
	}

	private void saveUserData() {
		try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("userData.txt"))) {
			os.writeObject(registeredUsers);
		} catch (IOException ex) {
			System.out.println("Error saving user data: " + ex.getMessage());
		}
	}

	/*
	 * public void loadUserData() { try (ObjectInputStream ois = new
	 * ObjectInputStream(new FileInputStream("userData.txt"))) { Object obj =
	 * ois.readObject(); if (obj instanceof List<?>) { registeredUsers =
	 * (List<Person>) obj; } } catch (IOException | ClassNotFoundException e) {
	 * System.out.println("Error loading user data: " + e.getMessage()); } }
	 */

	public List<Person> loadUserData() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("userData.txt"))) {
			Object obj = ois.readObject();
			if (obj instanceof List<?>) {
				registeredUsers = (List<Person>) obj;
				return registeredUsers;
			}
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error loading user data: " + e.getMessage());
		}
		return new ArrayList<>(); // Return empty list if loading fails
	}
}
