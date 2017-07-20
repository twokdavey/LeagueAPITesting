import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main {

	public static void main(String[] args) throws IOException, JSONException {
		String summonerName = JOptionPane.showInputDialog("What is your summoner name?");
		String userApi = "RGAPI-d3279eb7-8697-44bc-b54f-b81858e05d97";

		URL webSummonerID = new URL("https://na1.api.riotgames.com/lol/summoner/v3/summoners/by-name/" + summonerName
				+ "?api_key=" + userApi);

		BufferedReader input = new BufferedReader(new InputStreamReader(webSummonerID.openStream()));
		String leagueIdJson = input.readLine();
		JSONObject test = new JSONObject(leagueIdJson);
		System.out.println(test);
		int summonerId = test.getInt("id");
		System.out.println(summonerId);

		// Retrieve champ by ID
		URL webChampID = new URL(
				"https://na1.api.riotgames.com/lol/static-data/v3/champions?locale=en_US&dataById=false&api_key="
						+ userApi);

		BufferedReader input3 = new BufferedReader(new InputStreamReader(webChampID.openStream()));
		String leagueChampionJson = input3.readLine();
		JSONObject test1 = new JSONObject(leagueChampionJson);
		System.out.println(test1);
		// for(int i=0;i<test1.length())
		int totalChampCount = 0;
		System.out.println("this is" +test1.getJSONObject("data").length());
		String[] champListOption = new String[totalChampCount];

		JFrame frame = new JFrame("Input Dialog Example3");
		String championChoice = (String) JOptionPane.showInputDialog(frame, "Select Your Champion Choice", "Champ List",
				JOptionPane.QUESTION_MESSAGE, null, champListOption, champListOption[0]);
		System.out.println(championChoice);
		System.out.println(test1.getJSONObject("data").getJSONObject("MonkeyKing").getInt("id"));

		// champion information
		URL webChampionInfo = new URL("https://na.api.riotgames.com/api/lol/NA/v1.3/stats/by-summoner/" + summonerId
				+ "/ranked?season=SEASON2017&api_key=" + userApi);
		BufferedReader input2 = new BufferedReader(new InputStreamReader(webChampionInfo.openStream()));
		String leagueChampJson = input2.readLine();
		JSONObject champList = new JSONObject(leagueChampJson);
		System.out.println(champList);
		JSONArray array = champList.getJSONArray("champions");
		JSONObject champ1 = array.getJSONObject(1);
		int tester = champ1.getJSONObject("stats").getInt("totalTripleKills");
		System.out.println(tester);

		System.out.println(array.getJSONObject(1));
		System.out.println(array.getJSONObject(2));
		int arrayChampFind = 0;
		int CHAMPID = array.getJSONObject(1).getInt("id");
		System.out.println(CHAMPID);
		for (int i = 0; i < array.length(); i++) {
			if (array.getJSONObject(i).getJSONObject("stats").getInt("totalTripleKills") == 2) {
				System.out.println("successful find");
				arrayChampFind = i;
				return;
			}

		}

	}
}