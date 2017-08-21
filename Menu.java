import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class Menu extends JMenuBar
{
	MenuBarEvent eventListener = new MenuBarEvent(this);
	TextEditor mainWindow;
	
	public Menu(TextEditor mainWindow)
	{
		this.mainWindow = mainWindow;
		initializeFileMenu();
		initializeEditMenu();
		initializeSettingMenu();
	}
	
	private void initializeFileMenu()
	{
		JMenu fileMenu = new JMenu("File");
		
		JMenuItem newFile = new JMenuItem("New File");
		JMenuItem fileOpen = new JMenuItem("Open File");
		JMenuItem saveFile = new JMenuItem("Save File");
		JMenuItem saveAs = new JMenuItem("Save As...");
		JMenuItem sendEmail = new JMenuItem("Send To E-Mail");
		JMenuItem exit = new JMenuItem("Exit");
		
		newFile.addActionListener(eventListener);
		saveAs.addActionListener(eventListener);
		fileOpen.addActionListener(eventListener);
		saveFile.addActionListener(eventListener);
		sendEmail.addActionListener(eventListener);
		exit.addActionListener(eventListener);
		
		fileMenu.add(newFile);
		fileMenu.add(fileOpen);
		fileMenu.add(saveFile);
		fileMenu.add(saveAs);
		fileMenu.add(sendEmail);
		fileMenu.addSeparator();
		fileMenu.add(exit);
		
		this.add(fileMenu);
	}
	
	private void initializeSettingMenu()
	{
		JMenu buildMenu = new JMenu("Buid & Run");
		
		JMenuItem setting = new JMenuItem("Setting");
		setting.addActionListener(eventListener);
		
		buildMenu.add(setting);
		
		this.add(buildMenu);
	}
	
	private void initializeEditMenu()
	{
		JCheckBox wordWrap = new JCheckBox("Wrap Style: Word",true);
		JMenuItem colorWin = new JMenuItem("Set Color");
		JMenu editMenu = new JMenu("Edit");
		
		wordWrap.addItemListener(eventListener);
		colorWin.addActionListener(eventListener);
		
		editMenu.add(wordWrap);
		editMenu.add(colorWin);
		
		this.add(editMenu);
	}
}
