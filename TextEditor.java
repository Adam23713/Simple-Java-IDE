import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.border.EtchedBorder;

class TextEditor extends JFrame
{
	JTextArea textArea;
	JPanel textPanel;
	
	Menu menuBar = new Menu(this);
	
	public TextEditor()
	{
		init();
		
		textPanel = createTextPanel();
		this.add("Center", textPanel);
		this.add("North", new SpecToolBar(this));
		
		this.setVisible(true);
	}
	
	private void init()
	{
		setTitle("Java Text Editor");
		setSize(400,430);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout grid = new BorderLayout(15,15);
		setLayout(grid);
		
		this.setJMenuBar(menuBar);
	}
	
	private JPanel createTextPanel()
	{
		JPanel panel = new JPanel();
		textArea = new JTextArea();
		
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		panel.setLayout(new BorderLayout());
		JScrollPane scroll = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(scroll);
		
		// Modify some properties.
		textArea.setRows(10);
		textArea.setColumns(10);
		panel.setBorder(new EtchedBorder());

		return panel;
	}
	
	public void changeBackgroundColor(Color newColor)
	{
		textArea.setBackground(newColor);
		textArea.repaint();
	}
	
	public void changeTextColor(Color newColor)
	{
		textArea.setForeground(newColor);
		textArea.repaint();
	}
}
