package testPckg;

import java.util.ArrayList;

import org.junit.*;

import GUI.MainForm;
import GUI.SectionPropertyPanel;

public class SecondTest {

	@Test
	public void test() {
		String summ;
		
		ArrayList<SectionPropertyPanel> sections = new ArrayList<SectionPropertyPanel>();
		sections.add(new SectionPropertyPanel("1"));
		sections.add(new SectionPropertyPanel("2"));
		sections.get(0).setSectionWidth("500");
		sections.get(0).sectTypeRate = 0;
		sections.get(1).setSectionWidth("550");
		sections.get(1).sectTypeRate = 0.493f;
		
		String winHeight = "1200";
		float typeRate = 0.42f;
		int sillWidth = 200;
		int outflowWidth = 150;
		boolean install = true;
		
		MainForm calc = new MainForm("");
		
		summ = calc.Calculate(winHeight, sections, typeRate, sillWidth, outflowWidth, install);
		
		Assert.assertEquals("13226", summ);
	}

}
