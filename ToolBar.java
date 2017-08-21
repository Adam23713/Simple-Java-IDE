import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

class SpecToolBar extends JToolBar implements ActionListener
{
	TextEditor mainWindow;
	JButton newButton;
	JButton openButton;
	JButton saveButton;
	JButton buildAndRunButton;
	
	public SpecToolBar(TextEditor mainWindow)
	{
		super();
		this.mainWindow = mainWindow;
		ImageIcon newFile = new ImageIcon("icons/new-file-icon.jpg");
		ImageIcon openFile = new ImageIcon("icons/open-file.jpg");
		ImageIcon saveFile = new ImageIcon("icons/Save-icon.jpg");
		ImageIcon buildAndRun = new ImageIcon("icons/buildAndRun.jpg");
		
		newButton = new JButton(newFile);
		openButton = new JButton(openFile);
		saveButton = new JButton(saveFile);
		buildAndRunButton = new JButton(buildAndRun);
		
		newButton.addActionListener(this);
		
		this.add(newButton);
		this.add(openButton);
		this.add(saveButton);
		this.add(buildAndRunButton);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == newButton)
			mainWindow.createNewFile(null);
	}
}
