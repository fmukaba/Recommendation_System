
/*					Author : Francois Mukaba
 * 					Instructor : Fatma Serce
 * 					Course : Algorithms / CS 401
 * 					Spring 2019
 */

// Represents an artist

public class Artist {
	private String name;
	private int id;

	public Artist(int id, String name) {
		this.name = name;
		this.id = id;
	}

	public Artist() {
		this.name = "";
		this.id = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id + ": " + name + "\n";
	}
}
