import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class WriteMail extends JFrame implements ActionListener
{	
	private void init()
	{
		//this.setSize(370,270);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		//set layout
		GridLayout flow = new GridLayout(4,1);
		this.setLayout(flow);
	}
	
	private void createLabelAndTextFieldPanel(String str, int fieldSize)
	{
		JPanel row = new JPanel();
		JLabel Label = new JLabel(str);
		JTextField Field = new JTextField(fieldSize);
		row.add(Label);
		row.add(Field);
		
		this.add(row);
	}
	
	private void createTextArea(String messageSting)
	{
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Message:");
		JTextArea message = new JTextArea(4,22);
		message.setText(messageSting);
		
		//Important!
		message.setLineWrap(true);
		message.setWrapStyleWord(true);
		
		JScrollPane scroll = new JScrollPane(message,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		panel.add(label);
		panel.add(scroll);
		
		this.add(panel);
	}
	
	private void createSendButton()
	{
		JPanel panel = new JPanel();
		JButton button = new JButton("Send");
		button.addActionListener(this);
		panel.add(button);
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand() == "Send");
			this.dispose();
	}
	
	public WriteMail(String messageSting)
	{
		super("Write an E-Mail");
		init();
		createLabelAndTextFieldPanel("To:",24);
		createLabelAndTextFieldPanel("Subject:",24);
		createTextArea(messageSting);
		createSendButton();
		
		this.pack();
		this.setVisible(true);
	}
}
