
/*					Author : Francois Mukaba
 * 					Instructor : Fatma Serce
 * 					Course : Algorithms / CS 401
 * 					Spring 2019
 */

import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RecommenderTest {
	Recommender system;

	@BeforeEach
	public void setup() {
		int amount = 2101;
		String friendsFileName = "user_friends_test.dat";
		String artistsFileName = "artists_test.dat";
		String trackerFileName = "user_artists_test.dat";

		system = new Recommender(friendsFileName, artistsFileName, trackerFileName, amount);
	}

	@Test
	public final void constructorWorksProperly() {
		assertNotNull(system);
	}

	@Test
	public final void printUserFriends() {
		// userId : 2
		HashSet<Integer> expected = new HashSet<Integer>();
		expected.add(3);
		expected.add(4);
		expected.add(5);
		expected.add(6);
		expected.add(7);
		HashSet<Integer> hs = (HashSet<Integer>) system.listFriends(2);

		assertEquals(expected, hs);
	}

	@Test
	public final void printUsersCommonFriends() {
		// userId : 3 2
		HashSet<Integer> expected = new HashSet<Integer>();
		expected.add(4);
		expected.add(5);
		expected.add(6);
		expected.add(7);
		HashSet<Integer> hs = (HashSet<Integer>) system.commonFriends(3, 2);

		assertEquals(expected, hs);
	}

	@Test
	public final void printUsersCommonArtists() {
		// userId : 9 4
		// Nothing in common
		HashSet<Integer> expected = new HashSet<Integer>();
		HashSet<Integer> hs = (HashSet<Integer>) system.listArtists(9, 4);

		assertEquals(expected, hs);
	}

	@Test
	public final void print10MostPopularArtists() {
		HashSet<ChartEntry> expected = new HashSet<ChartEntry>();
		expected.add(new ChartEntry(10, 100000000));
		expected.add(new ChartEntry(288, 44292));
		expected.add(new ChartEntry(51, 14111));
		expected.add(new ChartEntry(289, 13370));
		expected.add(new ChartEntry(101, 13176));
		expected.add(new ChartEntry(53, 12218));
		expected.add(new ChartEntry(55, 12134));
		expected.add(new ChartEntry(52, 11690));
		expected.add(new ChartEntry(54, 10300));
		expected.add(new ChartEntry(89, 8286));
		HashSet<ChartEntry> hs = (HashSet<ChartEntry>) system.top10();

		assertEquals(expected, hs);
	}

	@Test
	public final void print10RecommendedArtists() {
		// user id: 10
		HashSet<ChartEntry> expected = new HashSet<ChartEntry>();
		expected.add(new ChartEntry(10, 100000000));
		HashSet<ChartEntry> hs = (HashSet<ChartEntry>) system.recommend10(10);

		assertEquals(expected, hs);
	}
}
