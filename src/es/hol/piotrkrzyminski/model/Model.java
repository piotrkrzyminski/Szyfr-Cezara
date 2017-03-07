package es.hol.piotrkrzyminski.model;

public class Model {
	private String inputText; //ciag znakow pobrany z pola tekstowego
	private int key; //klucz szyfrujacy
	private String[] words; //lancuch inputText rozbity na tablice zlozona z wyrazow
	private StringBuilder builder = new StringBuilder();
	
	
	/**
	 * pobierz ciag znakow z pola tekstowego i zapisz do pola inputText
	 * pobierz klucz szyfrujacy i zapisz do pola key
	 * wywolaj metode rozbijajaca ciag na wyrazy
	 * wywolaj metode szyfrujaca wyrazy
	 * zwroc zaszyfrowany ciag
	 * @param inputText
	 * @param key
	 * @return
	 */
	public String encrypt(String inputText, String key) {
		this.inputText = inputText;
		this.key = Integer.parseInt(key);
		
		splitText();
		encryptWords();
		
		return builder.toString();
	}
	
	/**
	 * podziel za pomoca metody split ciag inputText i przypisz wyrazy do tablicy words
	 */
	private void splitText() {
		words = inputText.split(" ");
	}
	
	/**
	 * podziel kazde slowo z tablicy words na litery, a jej kod ASCII przesun o pozycje o wartosci pola key
	 * polacz te litery w wyraz, a wyraz dopisz za pomoc¹ SceneBuilder do ciagu zakodowanego
	 */
	
	private void encryptWords() {
		for(int i=0; i<words.length; i++) {
			String word = words[i];
			String encryptedWord = "";
			
			for(int j=0; j<word.length(); j++) {
				char letter = word.charAt(j);
				
				if(letter >= 65 && letter <= 90) {
					letter = (char)(65+(letter-65+key)%26);
				} else if(letter >= 97 && letter <= 122) {
					letter = (char)(97+(letter-97+key)%26);
				}
				encryptedWord+=letter;
			}
			encryptedWord+=" ";
			builder.append(encryptedWord);
		}
	}	
}
