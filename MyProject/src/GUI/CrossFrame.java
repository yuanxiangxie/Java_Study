package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CrossFrame implements Parameter
{
	private String EncodeMAC;
	private JFrame Frame;
	private JTextField textField;
	private final String Tips = "ÇëÊäÈëœÓÊÕµœµÄÑéÖ€Âë!";
	private final int Length = 30;
	
	public CrossFrame(String EncodeMAC)
	{
		this.EncodeMAC = EncodeMAC;
		Frame = new JFrame(Frame_Name);
	}
	
	private void buildPanel()
	{
		JPanel Panel = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		Panel.setLayout(gridBagLayout);
		
		Insets insets = new Insets(18, 0, 18, 0);
		
		Font font = new Font(Font_Name, Font.BOLD, Font_Number);
		JLabel Label = new JLabel(Tips, JLabel.CENTER);
		Label.setFont(font);
		gridBagConstraints.insets = insets;
		gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraints.gridwidth = GridBagConstraints.REMAINDER; gridBagConstraints.gridheight = 1;
		gridBagConstraints.gridx = 2; gridBagConstraints.gridy = 2;
		gridBagLayout.setConstraints(Label, gridBagConstraints);
		Panel.add(Label);
		
		textField = new JTextField(Length);
		gridBagConstraints.gridx = 2; gridBagConstraints.gridy = 6;
		gridBagLayout.setConstraints(textField, gridBagConstraints);
		Panel.add(textField);
		
		Frame.add(Panel);
	}
	
	private void buildButton()
	{
		JPanel Panel = new JPanel( new FlowLayout(FlowLayout.RIGHT));
		JButton Button = new JButton("Cross");
		Button.setBackground( new Color(244, 244, 244));
		Button.addActionListener( new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
				
			}
		});
		
		Panel.add(Button);
		Frame.add(Panel, BorderLayout.SOUTH);
	}
	
	public void showFrame()
	{
		buildPanel();
		buildButton();
		
		Frame.setSize(Frame_Width, Frame_Height);
		Frame.setLocationRelativeTo(null);
		Frame.setResizable(false);
		Frame.setVisible(true);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}