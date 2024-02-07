
public class SpellChecker {

	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	// returns str without its first char.
	public static String tail(String str) {
		// check if str has a tail.
		if (str.length() == 1) {
			return "";
		}
		return str.substring(1);
	}

	// returns the first char.
	public static char head(String str) {
		return str.charAt(0);
	}

	// implementation of Levinshrein program recursivly.
	public static int levenshtein(String word1, String word2) {
		// base case - at least on of the strings is empty.
		if (word1.isEmpty()) {
			return word2.length();
		}
		if (word2.isEmpty()) {
			return word1.length();
		}
		word1 = word1.toLowerCase(); // turns word1 into lowercase characters.
		word2 = word2.toLowerCase(); // turns word2 into lowercase characters.
		if (head(word1) == head(word2)) {
			return levenshtein(tail(word1), tail(word2));
		}
		// return the min between deletion, insertion and substitution count.
		return 1 + Math.min(levenshtein(tail(word1), word2),
				Math.min(levenshtein(word1, tail(word2)), levenshtein(tail(word1), tail(word2))));
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000]; // array of strings that will contain all words in dictionary.txt.
		In in = new In(fileName); // open file.
		// goes throu the whole dictionary txt file of 3000 lines.
		for (int i = 0; i < dictionary.length; i++) {
			// save each line in and array of strings.
			dictionary[i] = in.readString();
		}
		// return array of strings that contains all of 3000 words in dictionary.txt.
		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		String mostResembles = word; // will contain the most closly resumbled word.
		// go through the dictionary.
		for (int i = 0; i < dictionary.length; i++) {
			// find matching first letters.
			if (word.charAt(0) == dictionary[i].charAt(0)) {
				// find the first word with given threshold or less.
				if (levenshtein(word, dictionary[i]) <= threshold) {
					mostResembles = dictionary[i];
					threshold = levenshtein(word, dictionary[i]);
				}
			}
		}
		return mostResembles;
	}

}
