package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

public class ProtocolFrame implements Parameter
{
	private JFrame Frame;
	private final String Title = "Èç¹ûÄãÊ¹ÓÃ±Ÿ³ÌÐòÔòŽú±íÍ¬ÒâÈçÏÂÐ­Òé£¡";
	private final String Content= "<html>1. Òò³ÌÐòÊÇžöÈË¿ª·¢ËùÒÔ²»ŽæÔÚÊÛºó·þÎñºÍÎ¬»€¹€×÷ <br> "
			+ "2. ÒòÎª²»ŽæÔÚÎ¬»€¹€×÷ËùÒÔºÜÓÐ¿ÉÄÜŽæÔÚÊ§Ð§µÄÎÊÌâ <br> 3. ÒòÊ¹ÓÃ±Ÿ³ÌÐò¶øµŒÖÂµçÄÔ¹ÊÕÏÎÊÌâÓë±Ÿ¿ª·¢ÕßÎÞ¹Ø <br>"
			+ "4. ÒòÏÞÖÆÈËÊýµÄÊ¹ÓÃÃ¿ŽÎÔËÐÐ³ÌÐò¶ŒÐèÓÃÑéÖ€ÂëÑéÖ€  <br> 5. Èç¹ûÔÚÊ¹ÓÃÖÐÓöµœÈÎºÎÇé¿ö¿ÉÏòÎÒ·¢ÓÊŒþœøÐÐ·ŽÀ¡ <br>"
			+ "6. ÎªÏÞÖÆ·­ÇœÈËÊýÍ¬Ê±±£»€ÀÍ¶¯³É¹ûÔÝ²»¿ª·ÅÔŽŽúÂë  <br><br></html>";
	private Color color;
	
	public ProtocolFrame()
	{
		Frame = new JFrame(Frame_Name);
		color = new Color(244, 244, 244);
	}
	
	private void buildTop()
	{
		JPanel Panel = new JPanel( new BorderLayout());
		JPanel contentPanel = new JPanel();
		
		JLabel blankLabel = new JLabel(" ");
		Panel.add(blankLabel, BorderLayout.NORTH);
		
		Font font = new Font(Font_Name, Font.BOLD, Font_Number);
		JLabel titleLabel = new JLabel(Title, JLabel.CENTER);
		titleLabel.setFont(font);
		Panel.add(titleLabel, BorderLayout.CENTER);
		
		font = new Font(Font_Name, Font.BOLD, 15);
		JLabel Label = new JLabel(Content);
		Label.setFont(font);
		contentPanel.add(Label);
		Panel.add(contentPanel, BorderLayout.SOUTH);
		
		JScrollPane scrollPane = new JScrollPane(Panel);
		Frame.add(scrollPane, BorderLayout.NORTH);
	}
	
	private void buildBottom()
	{
		JButton Button = new JButton("Continue");
		Button.setEnabled(false);
		
		JPanel Panel = new JPanel( new BorderLayout());
		JPanel RadioPanel = new JPanel();
		
		JRadioButton agreeRadioButton = new JRadioButton("Agree");
		agreeRadioButton.setBackground(color);

		JRadioButton cancelRadioButton = new JRadioButton("Cancel", true);
		cancelRadioButton.setBackground(color);
		
		class ActionTry implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				if(e.getSource() == agreeRadioButton)
				{
					Button.setEnabled(true);
				}
				else
				{
					Button.setEnabled(false);
				}
			}
		};
		
		agreeRadioButton.addActionListener( new ActionTry());
		cancelRadioButton.addActionListener( new ActionTry());
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(cancelRadioButton);
		buttonGroup.add(agreeRadioButton);
		RadioPanel.add(cancelRadioButton);
		RadioPanel.add(agreeRadioButton);
		

		JPanel ButtonPanel = new JPanel( new FlowLayout(FlowLayout.RIGHT));
		Button.setBackground(color);
		Button.addActionListener( new AbstractAction()
		{
			public void actionPerformed(ActionEvent e)
			{
				new MailFrame().showFrame();
				Frame.dispose();
			}
		});
		ButtonPanel.add(Button);
		Panel.add(RadioPanel, BorderLayout.CENTER);
		Panel.add(ButtonPanel, BorderLayout.SOUTH);
		
		Frame.add(Panel);
	}
	
	public void showFrame()
	{
		buildTop();
		buildBottom();
		
		Frame.setSize(Frame_Width, Frame_Height);
		Frame.setLocationRelativeTo(null);
		Frame.setResizable(false);
		Frame.setVisible(true);
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args)
	{
		ProtocolFrame M = new ProtocolFrame();
		M.showFrame();
	}
}