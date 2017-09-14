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
	JButton saveAllButton;
	JButton buildAndRunButton;
	
	public SpecToolBar(TextEditor mainWindow)
	{
		super();
		this.mainWindow = mainWindow;
		ImageIcon newFile = new ImageIcon("icons/new-file-icon.jpg");
		ImageIcon openFile = new ImageIcon("icons/open-file.jpg");
		ImageIcon saveFile = new ImageIcon("icons/Save-icon.jpg");
		ImageIcon saveAll = new ImageIcon("icons/saveAll.jpg");
		ImageIcon buildAndRun = new ImageIcon("icons/buildAndRun.jpg");
		
		newButton = new JButton(newFile);
		openButton = new JButton(openFile);
		saveButton = new JButton(saveFile);
		saveAllButton = new JButton(saveAll);
		buildAndRunButton = new JButton(buildAndRun);
		
		newButton.addActionListener(this);
		openButton.addActionListener(this);
		saveButton.addActionListener(this);
		saveAllButton.addActionListener(this);
		
		this.add(newButton);
		this.add(openButton);
		this.add(saveButton);
		this.add(saveAllButton);
		this.add(buildAndRunButton);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == newButton)
			mainWindow.createNewFile();
			
		if(e.getSource() == openButton)
			mainWindow.openFile();
			
		if(e.getSource() == saveButton)
		{
			mainWindow.saveFile(null);
		}
			
		if(e.getSource() == saveAllButton)
			mainWindow.saveAllFile();
	}
}
