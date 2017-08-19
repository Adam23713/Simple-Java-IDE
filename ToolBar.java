import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

class SpecToolBar extends JToolBar implements ActionListener
{
	TextEditor mainWindow;
	
	public SpecToolBar(TextEditor mainWindow)
	{
		super();
		this.mainWindow = mainWindow;
		ImageIcon newFile = new ImageIcon("icons/new-file-icon.jpg");
		ImageIcon openFile = new ImageIcon("icons/open-file.jpg");
		ImageIcon saveFile = new ImageIcon("icons/Save-icon.jpg");
		ImageIcon buildAndRun = new ImageIcon("icons/buildAndRun.jpg");
		
		JButton newButton = new JButton(newFile);
		JButton openButton = new JButton(openFile);
		JButton saveButton = new JButton(saveFile);
		JButton buildAndRunButton = new JButton(buildAndRun);
		
		this.add(newButton);
		this.add(openButton);
		this.add(saveButton);
		this.add(buildAndRunButton);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
	}
}
