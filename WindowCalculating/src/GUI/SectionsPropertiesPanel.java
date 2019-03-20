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
	
	// ������ ���������� ������� 
	private ArrayList<PropertyChangedListener> listeners = new ArrayList<PropertyChangedListener>();
	
	// �������� ���������
	public void addPropertyChangedListener(PropertyChangedListener listener) {
		this.listeners.add(listener);
	}
	
	// ������� ��� ������ ������� � ����� ��������� ������
	protected void firePropertyChanges() {
		for (PropertyChangedListener listener : listeners) {
			listener.propertyChanged();
		}
	}
	
	// �������� "���������� ������"
	private int SectionNumber;
	public int getSectionNumber() {
		return SectionNumber;
	}
	public void setSectionNumber(int sectionNumber) {
		// ������� ������
		if (sectionNumber < SectionNumber) {
			for(int i = SectionNumber; i > sectionNumber; i--) {
				remove(Sections.get(i - 1));
				Sections.remove(i - 1);
			}
		} else if (sectionNumber > SectionNumber){ // �������� ������
			for (int i = SectionNumber; i < sectionNumber; i++) {
				// �������� ������ ������ ���������� ������
				SectionPropertyPanel secPropPan = new SectionPropertyPanel("������ " + (i + 1));
				add(secPropPan, new GridBagConstraints(i + 1, 0, 1, 2, 1, 1, GridBagConstraints.WEST, 
					GridBagConstraints.HORIZONTAL, new Insets(10, 5, 10, 5), 0, 0));
				secPropPan.sectWidthTF.getDocument().addDocumentListener(new DocumentListener() {
					@Override
					public void changedUpdate(DocumentEvent arg0) {	}
					@Override
					public void insertUpdate(DocumentEvent arg0) {
						// ������� ������� ��� ����������
						try {
							secPropPan.sectWidthTF.setBackground(Color.white);
							// ���� ������ ������ 500 ������� ������
							if (Integer.parseInt(secPropPan.sectWidthTF.getText()) < 500) {
								secPropPan.sectWidthTF.setBackground(Color.red);
							}
						} catch (NumberFormatException e) {
							// ���� ���� ������, ������ ����� 0
							secPropPan.sectWidthTF.setBackground(Color.red);
						}
						secPropPan.setSectionWidth(secPropPan.sectWidthTF.getText());
						firePropertyChanges();
					}
					@Override
					public void removeUpdate(DocumentEvent arg0) {
						// ������� ������� ��� ����������
						try {
							secPropPan.sectWidthTF.setBackground(Color.white);
							// ���� ������ ������ 500 ������� ������
							if (Integer.parseInt(secPropPan.sectWidthTF.getText()) < 500) {
								secPropPan.sectWidthTF.setBackground(Color.red);
							}
						} catch (NumberFormatException e) {
							// ���� ���� ������, ������ ����� 0
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
						// ������� ������� ��� ����������
						firePropertyChanges();
					}
				});
				Sections.add(secPropPan);
			}	
		}
		SectionNumber = sectionNumber;
		// ������� ������� ��� ����������
		firePropertyChanges();
		updateUI();
	}
	
	// �������� "������ ������"
	private ArrayList<SectionPropertyPanel> Sections;
	public ArrayList<SectionPropertyPanel> getSections() {
		return Sections;
	}
	public void setSections(ArrayList<SectionPropertyPanel> sections) {
		Sections = sections;
	}

	// ����������� ������ ������ ���������� ������
	public SectionsPropertiesPanel(int num) {

		setLayout(new GridBagLayout());
		Sections = new ArrayList<SectionPropertyPanel>();
		
		JLabel sectWidthLbl = new JLabel("������");
		add(sectWidthLbl, new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.SOUTHWEST, 
				GridBagConstraints.NONE, new Insets(10, 5, 13, 5), 0, 0));
		
		JLabel sectTypeLbl = new JLabel("��� �������");
		add(sectTypeLbl, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.SOUTHWEST, 
				GridBagConstraints.NONE, new Insets(5, 5, 30, 5), 0, 0));
		
		// ������ ���������� ������
		setSectionNumber(num);
	}
	
}
