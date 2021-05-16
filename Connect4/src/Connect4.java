import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
public class Connect4 {
	int numClicks;
	JButton[][] buttons;
	public Connect4() {
		JFrame frame=new JFrame("Connect 4 - Red is up");
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(700, 600));
		frame.setLayout(new GridLayout(6,7));
		buttons=new JButton[7][6];
		for(int i=0;i<6;i++) {
			final int indexI=i;
			for(int j=0;j<7;j++) {
				final int indexJ=j;
				buttons[j][i]=new JButton();
				buttons[j][i].setBackground(Color.BLUE);
				frame.add(buttons[j][i]);
				buttons[j][i].addActionListener(new ActionListener(){
			        public void actionPerformed(ActionEvent e) {
			        	if(buttons[indexJ][indexI].getBackground().equals(Color.BLUE)) {
			        		Color color;
				        	if(numClicks%2==0) {
				        		color = Color.RED;
				        		frame.setTitle("Connect 4 - Yellow is up");
				        	} else {
				        		color = Color.YELLOW;
				        		frame.setTitle("Connect 4 - Red is up");
				        	}
							for(int k=5;k>=0;k--) {
								if(buttons[indexJ][k].getBackground().equals(Color.BLUE)) {
									buttons[indexJ][k].setBackground(color);
									System.out.printf("Button at (%d,%d) changed to %s\n",indexJ,k,color);
									numClicks++;
									if(color.equals(Color.YELLOW))
										if(checkForWin(indexJ,k,color)) {
											JOptionPane.showMessageDialog(frame, "Yellow wins!");
											System.exit(0);
										}
									if(color.equals(Color.RED))
										if(checkForWin(indexJ,k,color)) {
											JOptionPane.showMessageDialog(frame, "Red wins!");
											System.exit(0);
										}
									break;
								}
							}
						}
			        }
			    });
			}
		}		
		frame.setVisible(true);
	}
	public boolean checkForWin(int j, int k, Color color){
		//vertical
		int numInRow=0;
		for(int i=k-3;i<=k+3;i++) {
			if(i>=0 && i<6)
				if(buttons[j][i].getBackground().toString().equals(color.toString())) {
					numInRow++;
					System.out.println("vertical " + numInRow);
					if(numInRow==4)
						return true;
			}
		}
		//horizontal
		numInRow=0;
		for(int i=j-3;i<=j+3;i++) {
			if(i>=0 && i<7)
				if(buttons[i][k].getBackground().toString().equals(color.toString())) {
					numInRow++;
					System.out.println("horizontal " + numInRow);
					if(numInRow==4)
						return true;
			}
		}
		numInRow=0;
		for(int i=-3;i<=3;i++) {
			if(j+i>=0 && j+i<7 && k+i>=0 && k+i<6)
				if(buttons[j+i][k+i].getBackground().toString().equals(color.toString())) {
					numInRow++;
					System.out.println("negative diagonal " + numInRow);
					if(numInRow==4)
						return true;
				}
		}
		numInRow=0;
		for(int i=-3;i<=3;i++) {
			if(j+i>=0 && j+i<7 && k-i>=0 && k-i<6)
				if(buttons[j+i][k-i].getBackground().toString().equals(color.toString())) {
					numInRow++;
					System.out.println("positive diagonal " + numInRow);
					if(numInRow==4)
						return true;
				}
		}
		return false;
	}
}