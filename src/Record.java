
/*					Author : Francois Mukaba
 * 					Instructor : Fatma Serce
 * 					Course : Algorithms / CS 401
 * 					Spring 2019
 */

// Record captures a relationship between a user and how much they listen to an artist

public class Record {
	private int artistID;
	private int userID;
	private int listeningCount;

	public Record(int userID, int artistId, int listeningCount) {
		this.artistID = artistId;
		this.userID = userID;
		this.listeningCount = listeningCount;
	}

	public int getArtistId() {
		return artistID;
	}

	public void setArtistId(int artistId) {
		this.artistID = artistId;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getListeningCount() {
		return listeningCount;
	}

	public void setListeningCount(int listeningCount) {
		this.listeningCount = listeningCount;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Record) {
			Record r = (Record) o;
			return (userID == r.userID) && (artistID == r.artistID) && (listeningCount == r.listeningCount);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return userID * 7 * artistID;
	}

	@Override
	public String toString() {
		return userID + " -> " + artistID + ": " + listeningCount;
	}

}
