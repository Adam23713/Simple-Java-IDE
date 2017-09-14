import javax.swing.*;
import java.awt.FlowLayout;

class StatusBar extends JPanel
{
	private JLabel _rowStatus = new JLabel("x. row, y column");
	private JComboBox<String> _characterCodes;
	private JComboBox<String> _syntax;
	private JComboBox<String> _tabWidth;
	
	public StatusBar()
	{
		FlowLayout flow = new FlowLayout(FlowLayout.LEFT);
		this.setLayout(flow);
		
		setCodesComboBox();
		setSyntaxComboBox();
		setTabWidthComboBox();
		
		this.add(_characterCodes);
		this.add(_syntax);
		this.add(_tabWidth);
		this.add(_rowStatus);
	}
	
	public void setRowStatus(String str)
	{
		_rowStatus.setText(str);
	}
	
	private void setSyntaxComboBox()
	{
		String[] choose = { "Simple Text", "C", "C++", "C#", "Java" };
		_syntax = new JComboBox<String>(choose);
	}
	
	private void setTabWidthComboBox()
	{
		String[] choose = { "Tab Width: 2", "Tab Width: 4", "Tab Width: 8"};
		_tabWidth = new JComboBox<String>(choose);
	}
	
		private void setCodesComboBox()
	{
		String[] choose = { "UTF-8",};
		_characterCodes = new JComboBox<String>(choose);
	}
}
