package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MainForm extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6900292427557925202L;
	
	// Объявление полей
	String sillWidths[] = {
			"0", "150", "200", "250", "300", "350", "400", "450", "500"
	};
	ButtonGroup sectNumBG = new ButtonGroup();
	JTextField windowHeghtTF = new JTextField();
	JTextField priceTF = new JTextField();
	JPanel sectNumPanel = new JPanel();
	JCheckBox installChB = new JCheckBox("Установка");
	SectionsPropertiesPanel sectPropsPanel = new SectionsPropertiesPanel(1);
	JComboBox<String> sillWidthCB = new JComboBox<String>(sillWidths);

	float typeRate = 0.37f;
	int outflowWidth = 0;
	boolean error = false;
	
	// Конструктор Формы
	public MainForm(String name) {
		super(name);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(1050, 440));
		setSize(1050, 440);
		
		// Главная панель
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		add(mainPanel,BorderLayout.CENTER);
		
		JLabel sectNumLbl = new JLabel("Секций");
		mainPanel.add(sectNumLbl, new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, new Insets(10, 10, 5, 5), 0, 0));
		
		// Панель с радиокнопками
		sectNumPanel.setLayout(new BoxLayout(sectNumPanel,BoxLayout.X_AXIS));
		addRadioButton("1", 1);
		addRadioButton("2", 2);
		addRadioButton("3", 3);
		addRadioButton("4", 4);
		mainPanel.add(sectNumPanel, new GridBagConstraints(1, 0, 1, 1, 1, 0, GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, new Insets(10, 5, 5, 10), 0, 0));
		
		JLabel glassTypeLbl = new JLabel("Тип стеклопакета");
		mainPanel.add(glassTypeLbl, new GridBagConstraints(0, 1, 1, 1, 0, 0, GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 5), 0, 0));
		
		String glassTypes[] = {
			"Стандартный",
			"Теплосберегающий",
			"Солцезащитный",
			"Шумозащитный"
		};
		
		// Выпадающий список для выбора типа стеклопакета
		final JComboBox<String> glassTypeCB = new JComboBox<String>(glassTypes);
		mainPanel.add(glassTypeCB, new GridBagConstraints(1, 1, 1, 1, 0, 0, GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 10), 0, 0));
		// Слушатель для выпадающего списка
		glassTypeCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch (glassTypeCB.getSelectedIndex()) {
					case 0: typeRate = 0.37f;
						break;
					case 1: typeRate = 0.42f;
						break;
					case 2: typeRate = 0.5f;
						break;
					case 3: typeRate = 0.44f;
						break;
				}
				// Посчитать сумму
				UpdateSumm();
			}
		});
		
		JLabel sillWidthLbl = new JLabel("Ширина подоконника");
		mainPanel.add(sillWidthLbl, new GridBagConstraints(0, 2, 1, 1, 0, 0, GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 5), 0, 0));
		
		// Выпадающий список для выбора ширины подоконника
		mainPanel.add(sillWidthCB, new GridBagConstraints(1, 2, 1, 1, 0, 0, GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 10), 0, 0));
		// Слушатель для выпадающего списка
		sillWidthCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Посчитать сумму
				UpdateSumm();
			}
		});

		JLabel outflowWidthLbl = new JLabel("Ширина отлива");
		mainPanel.add(outflowWidthLbl, new GridBagConstraints(0, 3, 1, 1, 0, 0, GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 5), 0, 0));
		
		String outflowWidths[] = {
				"нет", "менее 100мм", "100-150мм", "200-250мм", "250-400мм"
		};
		// Выпадающий список для выбора ширина отлива
		final JComboBox<String> outflowWidthCB = new JComboBox<String>(outflowWidths);
		mainPanel.add(outflowWidthCB, new GridBagConstraints(1, 3, 1, 1, 0, 0, GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 10), 0, 0));
		// Слушатель для выпадающего списка
		outflowWidthCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch (outflowWidthCB.getSelectedIndex()) {
					case 0:
						outflowWidth = 0;
						break;
					case 1:
						outflowWidth = 100;
						break;
					case 2:
						outflowWidth = 150;
						break;
					case 3: 
						outflowWidth = 250;
						break;
					case 4:
						outflowWidth = 400;
						break;
				}
				// Посчитать сумму
				UpdateSumm();
			}
		});
		
		// Панель со свойствами для каждой секции
		mainPanel.add(sectPropsPanel, new GridBagConstraints(0, 4, 2, 1, 0, 0, GridBagConstraints.WEST, 
				GridBagConstraints.WEST, new Insets(5, 5, 5, 0), 0, 0));
		// Слушатель для панели 
		sectPropsPanel.addPropertyChangedListener(new PropertyChangedListener() {
			public void propertyChanged() {
				// Посчитать сумму
				UpdateSumm();
			}
		});
		
		JLabel windowHeightLbl = new JLabel("Высота окна");
		mainPanel.add(windowHeightLbl, new GridBagConstraints(0, 5, 1, 1, 0, 0, GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 5), 0, 0));
		
		// Поле ввода высоты окна
		mainPanel.add(windowHeghtTF, new GridBagConstraints(1, 5, 1, 1, 0, 0, GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 10), 0, 0));
		// Значение по умолчанию для поля ввода
		windowHeghtTF.setText("1000");
		// Слушатель для поля ввода
		windowHeghtTF.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent arg0) { }
			public void insertUpdate(DocumentEvent arg0) {
				try {
					windowHeghtTF.setBackground(Color.white);
					if (Integer.parseInt(windowHeghtTF.getText()) < 500) {
						windowHeghtTF.setBackground(Color.red);
					}
				} catch (NumberFormatException e) {
					// Если поле пустое, высота равна 0
					windowHeghtTF.setBackground(Color.red);
				}
				// Посчитать сумму
				UpdateSumm();
			}
			public void removeUpdate(DocumentEvent arg0) {
				try {
					// Если высота меньше 500 вывести ошибку
					windowHeghtTF.setBackground(Color.white);
					if (Integer.parseInt(windowHeghtTF.getText()) < 500) {
						windowHeghtTF.setBackground(Color.red);
					}
				} catch (NumberFormatException e) {
					// Если поле пустое, высота равна 0
					windowHeghtTF.setBackground(Color.red);
				}
				// Посчитать сумму
				UpdateSumm();
			}
		});
		
		// Флажок "заказать ли установку окна"
		mainPanel.add(installChB, new GridBagConstraints(0, 6, 1, 1, 0, 0, GridBagConstraints.WEST, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 5), 0, 0));
		// Слушатель для флажка
		installChB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Посчитать сумму
				UpdateSumm();
			}
		});
		
		JLabel priceLbl = new JLabel("Цена");
		mainPanel.add(priceLbl, new GridBagConstraints(0, 7, 1, 1, 0, 1, GridBagConstraints.SOUTHWEST, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 10, 5, 5), 0, 0));
		
		// Поле для вывода стоимости
		mainPanel.add(priceTF, new GridBagConstraints(1, 7, 1, 1, 0, 1, GridBagConstraints.SOUTHWEST, 
				GridBagConstraints.HORIZONTAL, new Insets(5, 5, 5, 10), 0, 0));
		priceTF.setEditable(false);
		
		// Посчитать сумму
		UpdateSumm();
	}

	// Функция для добавления радиокнопок
	void addRadioButton(String name, final int num)
	{
		boolean selected = num == 1;
		JRadioButton button = new JRadioButton(name, selected);
		sectNumBG.add(button);
		sectNumPanel.add(button);
		
		// Слушатель для каждой радиокнопки
		ActionListener listener = new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) {
				// Задать количество секций
				sectPropsPanel.setSectionNumber(num);
			}
		};
		// Добавить слушатель радиокнопкеы
		button.addActionListener(listener);
	}
	
	// Функция для счета суммы
	void UpdateSumm() {
		String summ;
		
		summ = Calculate(windowHeghtTF.getText(), sectPropsPanel.getSections(), typeRate, Integer.parseInt(sillWidthCB.getSelectedItem().toString()), outflowWidth, installChB.isSelected());
		
		// Вывести сумму в поле вывода суммы
		priceTF.setText(summ);
		
	}
	
	public String Calculate(String winHeightStr, ArrayList<SectionPropertyPanel> sections, float typeRate, int sillWidth, int outflowWidth, boolean install) {
		// Переменные
		int winHeight = 0;
		int totalWinWidth = 0;
		int winCost = 0;
		int summ = 0;
		error = false;
		// Проверка поля для ввода высоты окна
		try {
			winHeight = Integer.parseInt(winHeightStr);
			// Если высота меньше 500 вывести ошибку
			if (winHeight < 500) {
				return "Высота окна не может быть меньше 500мм";
			}
		} catch (NumberFormatException e) {
			// Если поле пустое, высота равна 0
			if (winHeightStr.isEmpty()) {
				winHeight = 0;
			}
			return "Неверный формат поля 'высота окна'";
		}
		// Для каждой секции окна..
		for (SectionPropertyPanel item : sections) {
			int sectionWidth = 0;
			try {
				sectionWidth = Integer.parseInt(item.getSectionWidth());
				// Если высота меньше 500 вывести ошибку
				if (sectionWidth < 500) {
					return "Ширина окна не может быть меньше 500мм";
				}
			} catch (NumberFormatException e) {
				// Если поле пустое, высота равна 0
				if (item.getSectionWidth().isEmpty()) {
					sectionWidth = 0;
				}
				return "Неверный формат поля 'Ширина'";
			}
			// Посчитать стоимость секции
			winCost = (winHeight * 2) + (sectionWidth * 2);
			totalWinWidth += sectionWidth;
			summ += winCost;
			summ += winCost * typeRate;
			summ += winCost * item.sectTypeRate;
		}
		
		// Посчитать сумму с учетом ширины подоконника и ширина отлива
		summ += totalWinWidth * sillWidth * 0.0015f;
		summ += totalWinWidth * outflowWidth * 0.0005f;
		
		// Посчитать сумму с учетом выбора "заказать ли установку окна"
		if (install) summ += summ * 0.11f;
		
		return String.valueOf(summ);
	}
	
}
