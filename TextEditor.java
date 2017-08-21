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
    PagesPanel pagesPanel;
	private JScrollPane scroll;
	private JTextArea textArea;
	private JPanel workPanel;
	
	Menu menuBar = new Menu(this);
	
	public TextEditor()
	{
		init();
		
		workPanel = createWorkPanel();
		this.add("Center", workPanel);
		this.add("North", new SpecToolBar(this));
		
		this.setVisible(true);
	}
	
	private void init()
	{
		setTitle("Java Text Editor");
		setSize(600,450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BorderLayout grid = new BorderLayout(15,15);
		setLayout(grid);
		
		this.setJMenuBar(menuBar);
	}
	
	private JPanel createWorkPanel()
	{
		JPanel panel = new JPanel();
		textArea = new JTextArea();
		
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		panel.setLayout(new BorderLayout());
		scroll = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		pagesPanel = PagesPanel.getInstance(this);
		JScrollPane scrollPane = new JScrollPane(pagesPanel,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		panel.add("North", scrollPane);
		panel.add("Center", scroll);
		
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
	
	public String getText()
	{
		return textArea.getText();
	}
	
	public void setText(String str)
	{
		textArea.setText(str);
	}
	
	public void setEnabletTextArea(boolean value)
	{
		textArea.setEnabled(value);
	}
	
	public void setSeek(int seek)
	{
		textArea.setCaretPosition(seek);
	}
		
	public void createNewFile(String title)
	{
		if(title == null)
		{
			pagesPanel.addNewPage("Unnamed","",false);
			return;
		}
	}
	
	public void showErrorDialog(String arg1, String arg2)
	{
		JOptionPane.showMessageDialog(this,arg1,arg2,JOptionPane.ERROR_MESSAGE);
	}
	
	public void saveFile(String fileName)
	{
		FileOperations fileOperator = new FileOperations(this);
		
		File file;
		if(fileName == null)
		{
			file = fileOperator.saveFileDialog();
			if(file == null) return;
			fileName = file.getAbsolutePath();
		}
		else
			file = new File(fileName);
			
		fileOperator.saveFile(file,textArea.getText());
		pagesPanel.savePage(fileName);
	}
	
	public void openFile(File targetFile)
	{
		FileOperations fileOperator = new FileOperations(this);
		
		if(targetFile == null)
		{
			targetFile = fileOperator.openFileDialog();
			if(targetFile == null)
			{
				return;
			}
			String text = fileOperator.readFile(targetFile);
			if(text != null)
			{
				pagesPanel.addNewPage(targetFile.getName(),text,true);
			}
		}
	}
}
