package GUI;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SectionPropertyPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8240584799077267979L;

	// Свойство "Ширина секции окна"
	private String SectionWidth;
	public String getSectionWidth() {
		return SectionWidth;
	}
	public void setSectionWidth(String sectionWidth) {
		SectionWidth = sectionWidth;
	}

	// Свойство "Тип секции окна" 
	private String SectionType;
	public String getSectionType() {
		return SectionType;
	}
	public void setSectionType(String sectionType) {
		switch (sectTypeCB.getSelectedIndex()) {
			case 0: 
				sectTypeRate = 0;
				break;
			case 1:
			case 3:
				sectTypeRate = 0.193f;
				break;
			case 2:
			case 4:
				sectTypeRate = 0.493f;
				break;
		}
		SectionType = sectionType;
	}
	
	// Поле "множитель для выбранного типа секции"
	public float sectTypeRate;
	
	String items[] = {
			"Глухая",
			"Поворотная влево",
			"Поворотно/откидная влево",
			"Поворотная вправо",
			"Поворотно/откидная вправо"
	};
	JComboBox<String> sectTypeCB = new JComboBox<String>(items);
	public JTextField sectWidthTF = new JTextField();
	
	// Конструктор панели выбора параметров секции
	public SectionPropertyPanel(String name) {
		super();
		setBorder(BorderFactory.createTitledBorder(name));
		setLayout(new GridBagLayout());
		
		// Поле ввода ширины секции
		add(sectWidthTF, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.EAST, 
				GridBagConstraints.HORIZONTAL, new Insets(10, 10, 5, 10), 0, 0));
		sectWidthTF.setText("500");
		setSectionWidth("500");
		
		// Выпадающий список типов секции
		add(sectTypeCB, new GridBagConstraints(0, 1, 1, 1, 1, 1, GridBagConstraints.EAST, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 10, 10, 10), 0, 0));
		
	}
}
