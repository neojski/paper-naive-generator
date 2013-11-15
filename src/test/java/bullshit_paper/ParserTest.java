package bullshit_paper;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

public class ParserTest {

	@Test
	public void SimpleTest() {
		String text = "Bądź tak uprzejmy i spróbuj przemyśleć następujący problem – na co by się zdało twoje dobro, gdyby nie istniało zło, i jak by wyglądała ziemia, gdyby z niej zniknęły cienie? "
		+ "Przecież cienie rzucają przedmioty i ludzie. "
		+ "Oto cień mojej szpady. "
		+ "Ale są również cienie drzew i cienie istot żywych.";
		
		List<String> sentences = Arrays.asList(
		"Bądź tak uprzejmy i spróbuj przemyśleć następujący problem – na co by się zdało twoje dobro, gdyby nie istniało zło, i jak by wyglądała ziemia, gdyby z niej zniknęły cienie? ",
		"Przecież cienie rzucają przedmioty i ludzie. ",
		"Oto cień mojej szpady. ",
		"Ale są również cienie drzew i cienie istot żywych. ");

		assertEquals(Parser.parseText(text).equals(sentences), true);
	}
	
	@Test
	public void AbbreviationsTest() {
		String text = "Rada Ministrów przyjęła dziś projekt tzw. ustawy rekrutacyjnej. "
					+ "Ustawa \"rekrutacyjna\" realizuje wyrok Trybunału Konstytucyjnego dotyczący zasad rekrutacji m.in. do przedszkoli.";
		
		List<String> sentences = Arrays.asList(
				"Rada Ministrów przyjęła dziś projekt tzw. ustawy rekrutacyjnej. ",
				"Ustawa \"rekrutacyjna\" realizuje wyrok Trybunału Konstytucyjnego dotyczący zasad rekrutacji m.in. do przedszkoli. ");		

		assertEquals(Parser.parseText(text).equals(sentences), true);
	}
	
	@Test
	public void ElipsisTest() {
		String text = "Konsument wysłał list wprost do… prezydenta. Zaczął recytować: „Ruszyła maszyna…”, ale dziecko mu przerwało.";
		
		List<String> sentences = Arrays.asList(
			"Konsument wysłał list wprost do… prezydenta. ",
			"Zaczął recytować: „Ruszyła maszyna…”, ale dziecko mu przerwało. ");		

		assertEquals(Parser.parseText(text).containsAll(sentences), true);
	}

}
