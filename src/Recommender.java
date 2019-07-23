
/*					Author : Francois Mukaba
 * 					Instructor : Fatma Serce
 * 					Course : Algorithms / CS 401
 * 					Spring 2019
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Recommender {
	private Graph usersDB; // friends relationship
	private HashMap<Integer, Artist> artistsDB; // list of all artists
	private HashMap<Integer, HashSet<Record>> user_artist; // link a user to a pool of artists they listen to
	private HashMap<Integer, Integer> listeningChart; // link an artist to total count of listening of all users

	public Recommender(String friendsFileName, String artistsFileName, String trackerFileName, int amount) {
		usersDB = new Graph(amount);
		artistsDB = new HashMap<Integer, Artist>();
		user_artist = new HashMap<Integer, HashSet<Record>>();
		listeningChart = new HashMap<Integer, Integer>();

		readFiles(friendsFileName, artistsFileName, trackerFileName);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	// input data from files
	private void readFiles(String friendsFileName, String artistsFileName, String trackerFileName) {
		RecommenderFileReader fr = new RecommenderFileReader(friendsFileName, artistsFileName, trackerFileName);
		usersDB = fr.readUsersLinks(usersDB);
		artistsDB = fr.readArtists(artistsDB);
		Map[] map = fr.readUserArtistRelation(user_artist, listeningChart);
		user_artist = (HashMap<Integer, HashSet<Record>>) map[0];
		listeningChart = (HashMap<Integer, Integer>) map[1];

	}

	// list all user's friends
	public Iterable<Integer> listFriends(int userID) {
		HashSet<Integer> hs = (HashSet<Integer>) usersDB.adj(userID);
		if (hs.isEmpty() || hs == null) {
			System.out.println("The user has no friend");
		} else {
			System.out.println("User Id: " + userID + "\nFriends:");
			for (Integer i : hs) {
				System.out.print(i + " ");
			}
			System.out.println("\n");
		}
		return hs;
	}

	// list users' friends in common
	public Iterable<Integer> commonFriends(int user1, int user2) {
		HashSet<Integer> hs1 = (HashSet<Integer>) usersDB.adj(user1);
		HashSet<Integer> hs2 = (HashSet<Integer>) usersDB.adj(user2);
		try {
			hs1.retainAll(hs2);
			if (hs1.isEmpty()) {
				System.out.println("The users have no friends in common !\n");
			} else {
				System.out.println("User Ids: " + user1 + " " + user2 + "\nFriends in common:  ");
				for (Integer i : hs1) {
					System.out.print(i + " ");
				}
				System.out.println("\n");
				return hs1;
			}
		} catch (Exception e) {
			System.out.println("The users have no friends in common !\n");
		}
		return null;
	}

	// list users' artists in common
	public Iterable<Integer> listArtists(int user1, int user2) {
		HashSet<Record> pool1 = user_artist.get(user1);
		HashSet<Record> pool2 = user_artist.get(user2);

		HashSet<Integer> artists1 = new HashSet<Integer>();
		for (Record r : pool1) {
			artists1.add(r.getArtistId());
		}

		HashSet<Integer> artists2 = new HashSet<Integer>();
		for (Record r : pool2) {
			artists2.add(r.getArtistId());
		}

		try {
			artists1.retainAll(artists2);
			if (!artists1.isEmpty()) {
				System.out.println("User Ids: " + user1 + " " + user2 + "\nCommon artists listened to:  ");
				for (Integer x : artists1) {
					System.out.print(x + ": " + artistsDB.get(x).getName() + "\n");
				}
				System.out.println("\n");
			} else {
				System.out.println("The users have no artists they both listen to !\n");
			}
			return artists1;
		} catch (Exception e) {
			System.out.println("The users have no artists they both listen to !\n");
		}
		return null;
	}

	// prints the 10 most listened artists across the network
	@SuppressWarnings("unchecked")
	public Iterable<ChartEntry> top10() {
		ArrayList<ChartEntry> chart = new ArrayList<ChartEntry>();
		Set<Integer> artists = listeningChart.keySet();
		for (Integer i : artists) {
			ChartEntry newEntry = new ChartEntry(i, listeningChart.get(i));
			chart.add(newEntry);
		}
		Collections.sort(chart);
		return print10("Top 10 artists", chart);
	}

	// prints 10 recommended listened artists for the user
	public Iterable<ChartEntry> recommend10(int user) {
		Set<Integer> artists = new HashSet<Integer>();
		HashSet<Integer> users = (HashSet<Integer>) usersDB.adj(user);
		ArrayList<ChartEntry> chart = new ArrayList<ChartEntry>();
		users.add(user);
		HashSet<Record> pool = new HashSet<Record>();
		try {
			for (Integer id : users) {
				pool.addAll(user_artist.get(id));
			}
			for (Record rec : pool) {
				artists.add(rec.getArtistId()); // no duplicate from records
			}
			for (Integer id : artists) {
				ChartEntry newEntry = new ChartEntry(id, listeningChart.get(id));
				chart.add(newEntry);
			}
			return print10("Top 10 artists recommended", chart);
		} catch (Exception e) {
			System.out.println("No results");
		}
		return null;
	}

	// helper method to print array of chart entries
	private Iterable<ChartEntry> print10(String str, ArrayList<ChartEntry> chart) {
		Set<ChartEntry> last10 = new HashSet<ChartEntry>();
		System.out.println(str);
		for (int i = 0; i < str.length(); i++) {
			System.out.print("_");
		}
		System.out.println();
		int count = chart.size() - 10;
		for (int i = chart.size() - 1; (i >= 0 && i >= count); i--) {
			last10.add(chart.get(i));
			String artistName = artistsDB.get(chart.get(i).getArtistID()).getName();
			ChartEntry ch = chart.get(i);
			int totalCount = ch.getTotalCount();
			int artistID = ch.getArtistID();
			System.out.println(artistID + ": " + artistName + " -> " + totalCount);
		}
		System.out.println();
		return last10;
	}

	public Graph getUsersDB() {
		return usersDB;
	}

	public HashMap<Integer, Artist> getArtistsDB() {
		return artistsDB;
	}

	public HashMap<Integer, HashSet<Record>> getUser_artist() {
		return user_artist;
	}

	public HashMap<Integer, Integer> getListeningChart() {
		return listeningChart;
	}
}