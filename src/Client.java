
/*					Author : Francois Mukaba
 * 					Instructor : Fatma Serce
 * 					Course : Algorithms / CS 401
 * 					Spring 2019
 */

public class Client {

	public static void main(String[] args) {
		// prepare parameters
		int amount = 2101;
		String friendsFileName = "user_friends.dat";
		String artistsFileName = "artists.dat";
		String trackerFileName = "user_artists.dat";

		Recommender system = new Recommender(friendsFileName, artistsFileName, trackerFileName, amount);

		System.out.println("list all friends of user - userID is 2: ");
		system.listFriends(2);

		System.out.println("list all common friends of users 12 and 46: ");
		system.commonFriends(12, 46);

		System.out.println("list all common artist of users 4 and 2: ");
		system.listArtists(4, 2);

		// list 10 most listened artists
		system.top10();

		// l list 10 recommended artists from user 10
		system.recommend10(10);

	}
}
