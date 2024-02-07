
public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String[] dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	// return an array that contains all 3000 words in dictionary.txt.
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

	// checks if word is in dictionary
	public static boolean existInDictionary(String word, String[] dictionary) {
		int flag = 0; // indicator if the whole word if in the dictionary.
		// go throu all of the dictionary to seek word.
		for (int i = 0; i < dictionary.length; i++) {
			// check if the lengths are equal (if a char by char check is needed).
			if (dictionary[i].length() == word.length()) {
				// go through each char in word and dictionary.
				for (int j = 0; j < dictionary[i].length(); j++) {
					if (word.charAt(j) == dictionary[i].charAt(j)) {
						flag++;
					}
					// check if all of the characters are equal(value and position)
					if (flag == (word.length())) {
						return true;
					}
				}
			}
			// reseting flag (checking a new word in dictionary).
			flag = 0;
		}
		// if word is not found return false.
		return false;
	}

	// print all words recognized in hashtag from dictionary with a recursive
	// implimantation.s
	public static void breakHashTag(String hashtag, String[] dictionary) {
		// Base case: do nothing (return) if hashtag is an empty string.
		if (hashtag.isEmpty()) {
			return;
		}
		int N = hashtag.length(); // lenght of the string given.
		hashtag = hashtag.toLowerCase(); // turns hashtag into lowercase characters.
		// go thoru all chars and implement a recursiv solution for searching each word
		// identified in dictionary.txt.
		for (int i = 1; i <= N; i++) {
			// check if hashtag is in dictionary.
			if (existInDictionary(hashtag.substring(0, i), dictionary)) {
				// print if the word is recognzed.
				System.out.println(hashtag.substring(0, i));
				// search for more words that apear after the found word.
				breakHashTag(hashtag.substring(i, N), dictionary);
				return;
			}
		}
	}

}
