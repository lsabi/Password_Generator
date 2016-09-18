
import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

import javafx.geometry.Rectangle2D;

import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;

public class Gui extends JPanel
{
	JPanel totalpanel = new JPanel(), optionpanel = new JPanel(), generatepanel = new JPanel(), messagespanel = new JPanel(), letterpanel=new JPanel();
	JPanel panel = new JPanel(), optionspanel = new JPanel(), outputpanel = new JPanel(), typelabel = new JPanel(), lengthpanel = new JPanel(), incrementdecrementpanel = new JPanel();
	JButton generate = new JButton("Generate password"), decrement = new JButton("-"), increment = new JButton("+");
	ArrayList<JRadioButton> type = new ArrayList<JRadioButton>();
	JRadioButton letters, digits, both;
	JLabel lengthlabel = new JLabel(), messagelabel = new JLabel();
	int t = 3, l= 24, letter=50, digit=50;
	    
	Image img;
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this);
	}
	
	
	public Gui()
	{
		try
		{
			img = Toolkit.getDefaultToolkit().getImage("lib/back.jpg");
		}
		catch(Exception e)
		{
			System.out.println(e.getStackTrace());
		}
		
//		totalpanel.setOpaque(false);
		totalpanel.setLayout(new BoxLayout(totalpanel, BoxLayout.Y_AXIS));
		JPanel totallengthpanel = new JPanel();
		totallengthpanel.setLayout(new GridLayout(2,1));
		totallengthpanel.add(new JLabel("Length:"));
		totallengthpanel.setOpaque(false);
		
//		Part for the increment / decrement section
//		increment.setText("+");
		increment.addActionListener(new actions());
		increment.setPreferredSize(new Dimension(40,20));
		increment.setFont(new Font("Arial",Font.ITALIC,15));
		increment.setOpaque(false);
		increment.setContentAreaFilled(false);
		increment.setBorderPainted(false);
		
//		decrement.setText("-");
		decrement.addActionListener(new actions());
		decrement.setPreferredSize(new Dimension(40,20));
		decrement.setFont(new Font("Arial",Font.ITALIC,15));
		decrement.setOpaque(false);
		decrement.setContentAreaFilled(false);
		decrement.setBorderPainted(false);
	
		incrementdecrementpanel.setLayout(new BoxLayout(incrementdecrementpanel, BoxLayout.Y_AXIS));
		incrementdecrementpanel.add(increment);
		incrementdecrementpanel.add(decrement);
		incrementdecrementpanel.setOpaque(false);
		
//		Part for setting the length of the password
		lengthlabel.setText(String.valueOf(l));
		
		lengthpanel.setLayout(new BoxLayout(lengthpanel, BoxLayout.X_AXIS));
		lengthpanel.add(lengthlabel);
		lengthpanel.add(incrementdecrementpanel);
		lengthpanel.setOpaque(false);
		
		totallengthpanel.add(lengthpanel);
		
//		Part for setting the distribution of letters and digits
		JButton moreletters=new JButton("+"), lessletters=new JButton("-"), moredigits=new JButton("+"), lessdigits=new JButton("-");
		JLabel letterscount=new JLabel(), digitscount=new JLabel();
		letterscount.setText(String.valueOf(letter));
		digitscount.setText(String.valueOf(digit));
		JPanel let=new JPanel(), dig=new JPanel(), lettertemp=new JPanel(), digittemp=new JPanel();
		let.setLayout(new BoxLayout(let, BoxLayout.X_AXIS));
		dig.setLayout(new BoxLayout(dig, BoxLayout.X_AXIS));
		lettertemp.setLayout(new BoxLayout(lettertemp, BoxLayout.Y_AXIS));
		digittemp.setLayout(new BoxLayout(digittemp, BoxLayout.Y_AXIS));
		
		lettertemp.add(moreletters);
		lettertemp.add(lessletters);
		let.add(letterscount);
		let.add(lettertemp);
		
		digittemp.add(moredigits);
		digittemp.add(lessdigits);
		dig.add(digitscount);
		dig.add(digittemp);
		
		letterpanel.setLayout(new BoxLayout(letterpanel, BoxLayout.X_AXIS));
		letterpanel.add(let);
		letterpanel.add(dig);
		
		optionpanel.setOpaque(false);
		optionpanel.setLayout(new GridLayout(1,3));
		optionpanel.add(totallengthpanel);
		
		optionpanel.add(letterpanel);
		
//   	Part of code for the jradiobuttons
		type.add(letters = new JRadioButton("Only letters"));
		type.add(digits = new JRadioButton("Only digits"));
		type.add(both = new JRadioButton("Both letters and digits",true));
		
		ButtonGroup type = new ButtonGroup();
		typelabel.setLayout(new GridLayout(3,1));
		type.add(letters);
		type.add(digits);
		type.add(both);
		
		letters.addActionListener(new change());
		letters.setOpaque(false);
		letters.setContentAreaFilled(false);
		letters.setBorderPainted(false);
		
		digits.addActionListener(new change());
		digits.setOpaque(false);
		digits.setContentAreaFilled(false);
		digits.setBorderPainted(false);
		
		both.addActionListener(new change());
		both.setOpaque(false);
		both.setContentAreaFilled(false);
		both.setBorderPainted(false);
		
		typelabel.add(letters);
		typelabel.add(digits);
		typelabel.add(both);
		
		typelabel.setOpaque(false);
		
		optionpanel.add(typelabel);
		
		generate.setPreferredSize(new Dimension(75,25));
		
//		messagelabel.setFont(new Font("Arial", Font.ITALIC, 10));
		messagespanel.setSize(new Dimension(100,30));
//		messagespanel.setBorder(BorderFactory.createLineBorder(Color.black));
		messagespanel.setOpaque(false);
		messagespanel.add(messagelabel);
		messagespanel.setSize(new Dimension(105,50));
		
		generate.addActionListener(new gen());
		totalpanel.add(optionpanel);
		totalpanel.add(generate);
		totalpanel.setAlignmentX(generate.CENTER);
		totalpanel.add(messagespanel);
		totalpanel.setBackground(new Color(254, 254, 254, 150));
		
        add(totalpanel);
	}
	
	
	public class actions implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == decrement && l > 1)
				l--;
		
			if(e.getSource() == increment && l < 50)
				l++;
			if(l >= 50 || l <= 1)
			{
				if (l >= 50)
					messagelabel.setText("The maximum length of the password can be only 50!");
				else
					messagelabel.setText("The minimum length of the password can be only 1!");
			}
			else
				messagelabel.setText("");
						
			lengthlabel.setText(String.valueOf(l));
		}
	}

	public class change implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			if(e.getSource() == letters)
			{
				t = 1;
				return ;
			}

			if(e.getSource() == digits)
			{
				t = 2;
				return ;
			}

			if(e.getSource() == both)
			{
				t = 3;
				return ;
			}

		}
	}
	
	public class gen implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
			if(e.getSource() == generate)
			{
				Password p = new Password();
				JOptionPane.showMessageDialog(null, "Your password: \n"+p.generatePassword(l, t, letter, digit), "Generated password", JOptionPane.PLAIN_MESSAGE);	
			}
		}
	}
}