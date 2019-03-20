package testPckg;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import GUI.MainForm;
import GUI.SectionPropertyPanel;

class FourthTest {

	@Test
	void test() {
		String summ;
		
		ArrayList<SectionPropertyPanel> sections = new ArrayList<SectionPropertyPanel>();
		sections.add(new SectionPropertyPanel("1"));
		sections.add(new SectionPropertyPanel("2"));
		sections.add(new SectionPropertyPanel("3"));
		sections.get(0).setSectionWidth("52см");
		sections.get(0).sectTypeRate = 0;
		sections.get(1).setSectionWidth("520");
		sections.get(1).sectTypeRate = 0.193f;
		sections.get(2).setSectionWidth("500");
		sections.get(2).sectTypeRate = 0;
		
		String winHeight = "1000";
		float typeRate = 0.37f;
		int sillWidth = 150;
		int outflowWidth = 100;
		boolean install = false;
		
		MainForm calc = new MainForm("");
		
		summ = calc.Calculate(winHeight, sections, typeRate, sillWidth, outflowWidth, install);
		
		Assert.assertEquals("Неверный формат поля 'Ширина'", summ);
	}

}
