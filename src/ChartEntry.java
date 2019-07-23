
/*					Author : Francois Mukaba
 * 					Instructor : Fatma Serce
 * 					Course : Algorithms / CS 401
 * 					Spring 2019
 */

// ChartEntry captures the relationship between an artist and the total count of listening of all users

public class ChartEntry implements Comparable {
	private int artistID;
	private int totalCount;

	public ChartEntry(int artistID, int totalCount) {
		this.artistID = artistID;
		this.totalCount = totalCount;
	}

	public int getArtistID() {
		return artistID;
	}

	public void setArtistID(int artistID) {
		this.artistID = artistID;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ChartEntry) {
			ChartEntry ch = (ChartEntry) obj;
			return (ch.getArtistID() == artistID) && (ch.getTotalCount() == totalCount);
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return artistID + 7 * totalCount;
	}

	@Override
	public int compareTo(Object obj) {
		ChartEntry ch = (ChartEntry) obj;
		if (totalCount < ch.getTotalCount()) {
			return -1;
		} else if (totalCount > ch.getTotalCount()) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return artistID + ": " + totalCount;
	}
}