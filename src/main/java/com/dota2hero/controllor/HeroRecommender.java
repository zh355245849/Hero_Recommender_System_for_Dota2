package com.dota2hero.controllor;
import java.util.*;

public class HeroRecommender {
	static DataBase db = new DataReader("/Users/zh355245849/Desktop/test.csv");
	public static List<Contribution> recommendBasedCosineSimilarity(String playerId) {
		SimilarityStrategy cosine = new CosineSimilarity();
		Similarity similarity = new Similarity(db, cosine);
		//System.out.println(similarity.similarities.get(new Pair<Player, Player>(db.getPlayerById("99"), db.getPlayerById("97"))));
		PredictEmptyContribution predictContribution = new PredictEmptyContribution(db, similarity, 20);
		List<Contribution> l = predictContribution.getContributionsByPlayerId(playerId);
		Collections.sort(l, new Comparator<Contribution>() {
			public int compare(Contribution c1, Contribution c2) {
				return Double.compare(c2.getContribution(), c1.getContribution());
			}
		});
		int i = 0;
		return l.subList(0, 5);
	}
	public static List<Contribution> recommendBasedPearsonCorrelationSimilarity(String playerId) {
		SimilarityStrategy cosine = new PearsonCorrelationSimilarity();
		Similarity similarity = new Similarity(db, cosine);
		//System.out.println(similarity.similarities.get(new Pair<Player, Player>(db.getPlayerById("99"), db.getPlayerById("97"))));
		PredictEmptyContribution predictContribution = new PredictEmptyContribution(db, similarity, 20);
		List<Contribution> l = predictContribution.getContributionsByPlayerId(playerId);
		Collections.sort(l, new Comparator<Contribution>() {
			public int compare(Contribution c1, Contribution c2) {
				return Double.compare(c2.getContribution(), c1.getContribution());
			}
		});
		int i = 0;
		return l.subList(0, 5);
	}
	public boolean isExistPlayer(String playerId) {
		List<Player> players = db.getPlayers();
		for(Player p : players) {
			if(p.getPlayerId().equals(playerId))
				return true;
		}
		return false;
	}
}
