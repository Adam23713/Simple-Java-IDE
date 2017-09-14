import javax.swing.*;
import java.awt.Dimension;
import java.awt.BorderLayout;

class SideBar extends JPanel
{
	private JTextPane _textArea = new JTextPane();
	private JComboBox<String> _possibilities;
	
	public SideBar(int height, int width)
	{	
		//Create elements and set them
		String[] show = { "Functions", "Variable", "Class", "F & V", "Show All" };
		_possibilities = new JComboBox<String>(show);
		_possibilities.setSelectedIndex(4);
		_textArea.setPreferredSize( new Dimension(width,height));
		_textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(_textArea);
		
		//Create Panel
		JPanel panel = new JPanel();
		BorderLayout grid = new BorderLayout(0,4);
		panel.setLayout(grid);
		
		//Add elements
		panel.add("North",_possibilities);
		panel.add("South",scrollPane);
		this.add(panel);
	}
}
