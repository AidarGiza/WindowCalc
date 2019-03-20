package GUI;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SectionsPropertiesPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8430636894855864675L;
	
	// Список слушателей событий 
	private ArrayList<PropertyChangedListener> listeners = new ArrayList<PropertyChangedListener>();
	
	// Добавить слушателя
	public void addPropertyChangedListener(PropertyChangedListener listener) {
		this.listeners.add(listener);
	}
	
	// Функция для вызова события о смене параметра секции
	protected void firePropertyChanges() {
		for (PropertyChangedListener listener : listeners) {
			listener.propertyChanged();
		}
	}
	
	// Свойство "Количество секций"
	private int SectionNumber;
	public int getSectionNumber() {
		return SectionNumber;
	}
	public void setSectionNumber(int sectionNumber) {
		// Удалить секцию
		if (sectionNumber < SectionNumber) {
			for(int i = SectionNumber; i > sectionNumber; i--) {
				remove(Sections.get(i - 1));
				Sections.remove(i - 1);
			}
		} else if (sectionNumber > SectionNumber){ // Добавить секцию
			for (int i = SectionNumber; i < sectionNumber; i++) {
				// Добавить панель выбора параметров секции
				SectionPropertyPanel secPropPan = new SectionPropertyPanel("Секция " + (i + 1));
				add(secPropPan, new GridBagConstraints(i + 1, 0, 1, 2, 1, 1, GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, new Insets(10, 5, 10, 5), 0, 0));
				secPropPan.sectWidthTF.getDocument().addDocumentListener(new DocumentListener() {
					@Override
					public void changedUpdate(DocumentEvent arg0) {	}
					@Override
					public void insertUpdate(DocumentEvent arg0) {
						// Вызвать событие для слушателей
						try {
							secPropPan.sectWidthTF.setBackground(Color.white);
							// Если ширина меньше 500 вывести ошибку
							if (Integer.parseInt(secPropPan.sectWidthTF.getText()) < 500) {
								secPropPan.sectWidthTF.setBackground(Color.red);
							}
						} catch (NumberFormatException e) {
							// Если поле пустое, высота равна 0
							secPropPan.sectWidthTF.setBackground(Color.red);
						}
						secPropPan.setSectionWidth(secPropPan.sectWidthTF.getText());
						firePropertyChanges();
					}
					@Override
					public void removeUpdate(DocumentEvent arg0) {
						// Вызвать событие для слушателей
						try {
							secPropPan.sectWidthTF.setBackground(Color.white);
							// Если ширина меньше 500 вывести ошибку
							if (Integer.parseInt(secPropPan.sectWidthTF.getText()) < 500) {
								secPropPan.sectWidthTF.setBackground(Color.red);
							}
						} catch (NumberFormatException e) {
							// Если поле пустое, высота равна 0
							secPropPan.sectWidthTF.setBackground(Color.red);
						}
						secPropPan.setSectionWidth(secPropPan.sectWidthTF.getText());
						firePropertyChanges();
					}
				});
				secPropPan.sectTypeCB.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						secPropPan.setSectionType(secPropPan.sectTypeCB.getSelectedItem().toString());
						// Вызвать событие для слушателей
						firePropertyChanges();
					}
				});
				Sections.add(secPropPan);
			}	
		}
		SectionNumber = sectionNumber;
		// Вызвать событие для слушателей
		firePropertyChanges();
		updateUI();
	}
	
	// Свойство "Список секций"
	private ArrayList<SectionPropertyPanel> Sections;
	public ArrayList<SectionPropertyPanel> getSections() {
		return Sections;
	}
	public void setSections(ArrayList<SectionPropertyPanel> sections) {
		Sections = sections;
	}

	// Конструктор панели списка параметров секции
	public SectionsPropertiesPanel(int num) {

		setLayout(new GridBagLayout());
		Sections = new ArrayList<SectionPropertyPanel>();
		
		JLabel sectWidthLbl = new JLabel("Ширина");
		add(sectWidthLbl, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.SOUTHWEST, 
				GridBagConstraints.NONE, new Insets(10, 5, 13, 5), 0, 0));
		
		JLabel sectTypeLbl = new JLabel("Тип створки");
		add(sectTypeLbl, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.SOUTHWEST, 
				GridBagConstraints.NONE, new Insets(5, 5, 30, 5), 0, 0));
		
		// Задать количество секций
		setSectionNumber(num);
	}
	
}
