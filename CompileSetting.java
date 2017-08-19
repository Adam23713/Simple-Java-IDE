import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class CompileSetting extends JFrame implements ActionListener
{
	JTextField compileField = new JTextField(20);
	JTextField runField = new JTextField(20);
	JPanel compilePanel = new JPanel();
	JPanel runPanel = new JPanel();
	
	public CompileSetting()
	{
		super("Build And Run Commands");
		JPanel mainPanel = new JPanel();
		BoxLayout box = new BoxLayout(mainPanel,BoxLayout.Y_AXIS);
		mainPanel.setLayout(box);
		
		compilePanel.add(new JLabel("Compile Command:"));
		compilePanel.add(compileField);
		
		runPanel.add(new JLabel("Run Command:"));
		runPanel.add(runField);
		
		mainPanel.add(compilePanel);
		mainPanel.add(runPanel);
		this.add(mainPanel);
		
		pack();
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
	}
}
