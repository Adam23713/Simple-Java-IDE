import java.awt.*;
import java.util.*;
import java.io.File;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.JOptionPane;
import javax.swing.border.EtchedBorder;

class TextEditor extends JFrame
{
	private int HEIGHT = 600;
	private int WIDTH = 450;
	private SideBar _sidebar;
	private StatusBar _statusBar;
    private PagesPanel _pagesPanel;
    private Settings _setting;
    TextListener _textListener;
	private JScrollPane _scroll;
	private JTextPane _textArea;
	private JPanel _workPanel;
	Menu _menuBar = new Menu(this);
	
	public TextEditor(Settings setting)
	{
		init();
		this._setting = setting;
		
		_textListener = new TextListener(this);
		_workPanel = createWorkPanel();
		_sidebar = createSideBar();
		_statusBar = createStatusBar();
		_textArea.getDocument().addDocumentListener(_textListener);
		
		this.add("South",_statusBar);
		this.add("West",_sidebar);
		this.add("Center", _workPanel);
		this.add("North", new SpecToolBar(this));
		
		openAllFile();
		
		this.setVisible(true);
	}
	
	private void init()
	{
		setTitle("Java Text Editor");
		setSize(HEIGHT,WIDTH);
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent event)
			{
				exitProcedure();
			}
		});
		
		BorderLayout grid = new BorderLayout(0,0);
		setLayout(grid);
		
		this.setJMenuBar(_menuBar);
	}
	
	public void exitProcedure()
	{
		saveAllFile();
		_setting.setOpenedFile(_pagesPanel.getFileList());
		try
		{
			_setting.writeXMLFile();
		}
		catch(SettingsException e)
		{
			//Nothing to do
		}
		finally
		{
			this.dispose();
			System.exit(0);
		}
	}
	
	private StatusBar createStatusBar()
	{
		StatusBar panel = new StatusBar();
		
		//Create element 
		
		return panel;
	}
	
	private SideBar createSideBar()
	{
		SideBar panel = new SideBar(HEIGHT,150);
		return panel;
	}
	
	private JPanel createWorkPanel()
	{
		
		//Create TextPane-----------------------------------------------
		_textArea = new JTextPane();
		JScrollPane scrollPane1 = new JScrollPane(_textArea);
		TextLineNumber tln = new TextLineNumber(_textArea);
		scrollPane1.setRowHeaderView( tln );
		//--------------------------------------------------------------
		
		//Create Pages Panel
		_pagesPanel = PagesPanel.getInstance(this);
		JScrollPane scrollPane = new JScrollPane(_pagesPanel,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		//Create and set panel
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add("North", scrollPane);
		panel.add("Center", scrollPane1);
		panel.setBorder(new EtchedBorder());

		return panel;
	}
	
	public void changeBackgroundColor(Color newColor)
	{
		_textArea.setBackground(newColor);
		_textArea.repaint();
	}
	
	public void changeTextColor(Color newColor)
	{
		_textArea.setForeground(newColor);
		_textArea.repaint();
	}
	
	public String getText()
	{
		return _textArea.getText();
	}
	
	public void setText(String str)
	{
		_textArea.setText(str);
	}
	
	public void setEnabletTextArea(boolean value)
	{
		_textArea.setEnabled(value);
	}
	
	public void setSeek(int seek)
	{
		_textArea.setCaretPosition(seek);
	}
		
	public void createNewFile()
	{
		_pagesPanel.addNewUnnamedPagePanel();
	}
	
	public void showErrorDialog(String arg1, String arg2)
	{
		JOptionPane.showMessageDialog(this,arg1,arg2,JOptionPane.ERROR_MESSAGE);
	}
	
	public void saveAs()
	{
		FileOperations fileOperator = new FileOperations(this);
		
		File file;
		file = fileOperator.saveFileDialog();
		if(file == null)
		{
			showErrorDialog("I can't save the file","File Error");
			return;
		}
		String filePath = file.getAbsolutePath();
		String fileName = file.getName();
		
		try
		{
			fileOperator.saveFile(file,_textArea.getText());
		}
		catch(FileOperationsException e)
		{
			showErrorDialog(e.getMessage(),"IOException Error!");
			return;
		}
		_pagesPanel.savePage(fileName,filePath,true);
	}
	
	public void saveAllFile()
	{
		if(_pagesPanel.pagesSize() == 0) return;
		
		int index = _pagesPanel.getActivePageIndex();
		for(int i = 0; i < _pagesPanel.pagesSize(); i++)
		{
			_pagesPanel.setActivePage(i);
			
			//If save the file step to next page
			if(_pagesPanel.isItSave()) continue;

			if(i == index)
			{
				saveFile(_textArea.getText());
			}
			else
			{
				saveFile(_pagesPanel.getText());
			}
		}
		_pagesPanel.setActivePage(index);
	}
	
	public boolean askWhatDoAtTheSave(String fileName)
	{
		int reply = JOptionPane.showConfirmDialog(this, "Save " + fileName + " as..." , "What should I do?", JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.YES_OPTION)
        {
          return true;
        }
        else
        {
           return false;
        }
	}
	
	public void saveFile(String text)
	{
		if(_pagesPanel.pagesSize() == 0) return;
		
		File file;
		FileOperations fileOperator = new FileOperations(this);
		
		if(_pagesPanel.unnamedPage())
		{
			file = fileOperator.saveFileDialog();
			if(file == null)
				return;
		}
		else
		{
			file = new File(_pagesPanel.getActivePageFileName());
		}
		String filePath = file.getAbsolutePath();
		String fileName = file.getName();
		
		//Select and save text------------------------------------------
		if(text == null)
		{
			try
			{
				fileOperator.saveFile(file,_textArea.getText());
			}
			catch(FileOperationsException e)
			{
				showErrorDialog(e.getMessage(),"I can't save the file");
				if(askWhatDoAtTheSave( filePath ))
				{
					saveAs();
					return;
				}
				
			}
			_pagesPanel.savePage(fileName,filePath,true);
		}
		else
		{
			try
			{
				fileOperator.saveFile(file,text);
			}
			catch(FileOperationsException e)
			{
				showErrorDialog(e.getMessage(),"I can't save the file");
				if(askWhatDoAtTheSave( filePath ))
				{
					saveAs();
					return;
				}
			}
			_pagesPanel.savePage(fileName,filePath,false);
		}
		//--------------------------------------------------------------
	}
	
	public void openAllFile()
	{
		if(_setting == null) return;
		String[] files = _setting.getFilesName();
		FileOperations fileOperator = new FileOperations(this);
		for(int i = 0; i < files.length; i++)
		{
			File file = fileOperator.openFile(files[i]);
			if(file != null)
			{
				readFile(file);
			}
		}
	}
	
	public void openFile()
	{
		FileOperations fileOperator = new FileOperations(this);
		File file = fileOperator.openFileDialog();
		if(file == null)
		{			
			showErrorDialog("I can't open the file","File Error");
			return;
		}
		readFile(file);
	}
	
	private void readFile(File file)
	{
		String text;
		FileOperations fileOperator = new FileOperations(this);
		
		try
		{
			text = fileOperator.readFile(file);
		}
		catch(FileOperationsException e)
		{
			showErrorDialog(e.getMessage(),"IOException Error!");
			return;
		}
		
		String filePath = file.getAbsolutePath();
		String fileName = file.getName();
		if(text != null)
		{
			_pagesPanel.addPagePanel(new PagePanel(text,fileName,filePath));
			return;
		}
		else
		{
			showErrorDialog("I can't read the file","File Error");
			return;
		}
	}
	
	public PagesPanel getPagesPanel()
	{
		return _pagesPanel;
	}
}
