package homework.testing;

public class Example {

	/**
	 * Counts the number of multiple contiguous space substrings in a given string.

	 * @param inputText the string to process
	 * @return the number of contiguous space substrings
	 */
	public int example(String inputText) {

		boolean foundSpace = false;
		boolean multiple = false;
		int result = 0;

		for (int i = 0; i < inputText.length(); i++) {

			if (inputText.charAt(i) == ' ') {
				if (foundSpace) {
					multiple = true;
				}
				foundSpace = true;
			} else {
				if (foundSpace && multiple) {
					result++;
				}
				foundSpace = false;
				multiple = false;
			}
		}

		return result;
	}
}
