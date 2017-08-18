import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;

class ColorPanel extends JPanel
{
	Color background;
	
	public ColorPanel()
	{
		background = Color.red;
	}
	
	public void paintComponent(Graphics comp)
	{
		Graphics2D comp2D = (Graphics2D)comp;
		comp2D.setColor(background);
		comp2D.fillRect(0,0,getSize().width, getSize().height);
	}
	
	public void changeColor(Color newColor)
	{
		background = newColor;
	}
}

class ColorSlide extends JFrame implements ChangeListener, ActionListener
{
	TextEditor mainWindow;
	JCheckBox backgroundCheck;
	JCheckBox textCheck;
	ColorPanel RGBpanel = new ColorPanel();
	JLabel infoLabel;
	JSlider red;
	JSlider green;
	JSlider blue;
	
	public ColorSlide(TextEditor mainWindow)
	{
		init();
		this.mainWindow = mainWindow;
		add(createCheckPanel());
		add(createPanel("Red",0,255,255));
		add(createPanel("Green",0,255,0));
		add(createPanel("Blue",0,255,0));
		add(RGBpanel);
		add(createInfoLabelPanel());
		add(createCopyPanel());
		
		pack();
		setVisible(true);
	}
	
	private JPanel createCheckPanel()
	{
		backgroundCheck = new JCheckBox("Background", true);
		textCheck = new JCheckBox("Text", false);
		ButtonGroup group = new ButtonGroup();
		
		group.add(backgroundCheck);
		group.add(textCheck);
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.add(backgroundCheck);
		panel.add(textCheck);
		
		return panel;
	}
	
	private void copyStringToClipBoard(String theString)
	{
		StringSelection selection = new StringSelection(theString);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection,selection);
	}
	
	private JPanel createCopyPanel()
	{
		JButton button = new JButton("Copy RGB Code");
		JButton okButton = new JButton("OK");
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		button.addActionListener(this);
		okButton.addActionListener(this);
		
		panel.add(button);
		panel.add(okButton);
		
		return panel;
	}
	
	private JPanel createInfoLabelPanel()
	{
		infoLabel = new JLabel("Red: 255   Green: 0   Blue: 0");
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel.add(infoLabel);
		return panel;
	}
	
	private void init()
	{
		setTitle("Color Slide");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridLayout grid = new GridLayout(7,1,15,15);
		setLayout(grid);
	}
	
	private JSlider createSlider(int R, int G, int B)
	{
		JSlider slider = new JSlider(JSlider.HORIZONTAL,R,G,B);
		
		slider.setMajorTickSpacing(50);
		slider.setMinorTickSpacing(10);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.addChangeListener(this);
		
		return slider;
	}
	
	private JPanel createPanel(String colorName, int R, int G, int B)
	{
		FlowLayout layout = new FlowLayout(FlowLayout.CENTER);
		JPanel panel = new JPanel();
		
		JSlider slider = createSlider(R,G,B);
		if( colorName.equals("Red") ) red = slider;
		if( colorName.equals("Green") ) green = slider;
		if( colorName.equals("Blue") ) blue = slider;
		
		panel.setLayout(layout);
		panel.add(new JLabel(colorName + ": "));
		panel.add(slider);
		
		return panel;
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand() == "Copy RGB Code")
		{
			copyStringToClipBoard(new String( red.getValue() + "," + green.getValue() + "," + blue.getValue() ));
			return;
		}
		if(e.getActionCommand() == "OK")
		{
			if(backgroundCheck.isSelected())
			{
				mainWindow.changeBackgroundColor(new Color(red.getValue(), green.getValue(), blue.getValue()));
				this.dispose();
			}
			else
			{
				mainWindow.changeTextColor(new Color(red.getValue(), green.getValue(), blue.getValue()));
				this.dispose();
			}
		}
	}
	
	public void stateChanged(ChangeEvent e)
	{
		JSlider slider = (JSlider)e.getSource();
		if( slider.getValueIsAdjusting() )
		{
				if(red == null || blue == null || green == null)
				{
					System.out.println("Null Reference");
					return;
				}
				infoLabel.setText("Red: " + red.getValue() + "   " + "Green: " + green.getValue() + "   " + "Blue: " + blue.getValue() + "   ");
				RGBpanel.changeColor(new Color(red.getValue(),green.getValue(), blue.getValue()));
				RGBpanel.repaint();
		}
	}
}
