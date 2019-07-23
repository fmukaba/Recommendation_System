/*					Author : Francois Mukaba
 * 					Instructor : Fatma Serce
 * 					Course : Algorithms / CS 401
 * 					Spring 2019
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

// class to read all the files needed for the Recommender

public class RecommenderFileReader {
	private String friendsFileName;
	private String artistsFileName;
	private String trackerFileName;

	public RecommenderFileReader(String friendsFileName, String artistsFileName, String trackerFileName) {
		this.friendsFileName = friendsFileName;
		this.artistsFileName = artistsFileName;
		this.trackerFileName = trackerFileName;
	}

	public Graph readUsersLinks(Graph usersDB) {
		try {
			String[] lines = Files.readAllLines(new File(friendsFileName).toPath()).toArray(new String[0]);
			String splitBy = "\t";
			for (int i = 1; i < lines.length; i++) {
				String line = lines[i];
				String[] sequence = line.split(splitBy);
				int friend1 = Integer.parseInt(sequence[0]);
				int friend2 = Integer.parseInt(sequence[1]);
				usersDB.addEdge(friend1, friend2);
			}
		} catch (IOException e) {
			System.out.println("File not found!");
		}
		return usersDB;
	}

	public HashMap<Integer, Artist> readArtists(HashMap<Integer, Artist> artistsDB) {
		try {
			String[] lines = Files.readAllLines(new File(artistsFileName).toPath()).toArray(new String[0]);
			String splitBy = "\t";
			for (int i = 1; i < lines.length; i++) {
				String line = lines[i];
				String[] sequence = line.split(splitBy);
				int id = Integer.parseInt(sequence[0]);
				String name = sequence[1];
				artistsDB.put(id, new Artist(id, name));
			}
		} catch (IOException e) {
			System.out.println("File not found!");
		}
		return artistsDB;
	}

	@SuppressWarnings("rawtypes")
	public Map[] readUserArtistRelation(HashMap<Integer, HashSet<Record>> user_artist,
			HashMap<Integer, Integer> listeningChart) {
		try {
			String[] lines = Files.readAllLines(new File(trackerFileName).toPath()).toArray(new String[0]);
			String splitBy = "\t";
			for (int i = 1; i < lines.length; i++) {
				String line = lines[i];
				String[] sequence = line.split(splitBy);
				int userId = Integer.parseInt(sequence[0]);
				int artistId = Integer.parseInt(sequence[1]);
				int listeningCount = Integer.parseInt(sequence[2]);
				Record record = new Record(userId, artistId, listeningCount);
				HashSet<Record> hs;
				if (user_artist.containsKey(userId)) {
					hs = user_artist.get(userId);
					hs.add(record);
					user_artist.put(userId, hs);
				} else {
					hs = new HashSet<Record>();
					hs.add(record);
					user_artist.put(userId, hs);
				}
				if (listeningChart.containsKey(artistId)) {
					int count = listeningChart.get(artistId);
					count += listeningCount;
					listeningChart.put(artistId, count);
				} else {
					listeningChart.put(artistId, listeningCount);
				}
			}
		} catch (IOException e) {
			System.out.println("File not found !");
		}
		Map[] maps = new Map[2];
		maps[0] = user_artist;
		maps[1] = listeningChart;
		return maps;
	}

	public String getFriendsFileName() {
		return friendsFileName;
	}

	public void setFriendsFileName(String friendsFileName) {
		this.friendsFileName = friendsFileName;
	}

	public String getArtistsFileName() {
		return artistsFileName;
	}

	public void setArtistsFileName(String artistsFileName) {
		this.artistsFileName = artistsFileName;
	}

	public String getTrackerFileName() {
		return trackerFileName;
	}

	public void setTrackerFileName(String trackerFileName) {
		this.trackerFileName = trackerFileName;
	}
}
