//Archbold Katsande

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RandomRectangleGUI{
	JFrame frame;
	RandomRectDrawPanel drawPanel=new RandomRectDrawPanel();
	JButton colorButton;
	JButton sizeButton;

	public static void main (String[] args){
		RandomRectangleGUI gui = new RandomRectangleGUI();
			gui.go();
	}

	public void go(){
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		colorButton = new JButton("Click me for a random colour");
		sizeButton = new JButton("Click me for a random size");
		frame.getContentPane().add(BorderLayout.PAGE_START, colorButton);
		frame.getContentPane().add(BorderLayout.PAGE_END, sizeButton);
		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
		frame.setSize(500,500);
		frame.setVisible(true);
		colorButton.addActionListener(new RandomColorListener());
		sizeButton.addActionListener(new SizeListener());

	}
	class RandomRectDrawPanel extends JPanel{
		 Color color;
		 int height = 50;
		 int width = 80;
		 int x = 50;
		 int y = 50;
		 int reducingValue;
		 final int minimumHeight =5;
		 final int minimumWidth =5;

		public void paintComponent (Graphics graphicsComponent){
			super.paintComponent(graphicsComponent);
			graphicsComponent.setColor(color);
			graphicsComponent.fillRect(x,y,width,height);
		}

		public Color randomColor(){
			int red = (int)(Math.random()*255);
			int green = (int)(Math.random()*255);
			int blue = (int)(Math.random()*255);
			color = new Color(red,green,blue);
			return color;
		}

		private int getPanelHeight(){
			return getHeight();
		}
		private int getPanelWidth(){
			return getWidth();
		}
		private void reduceRectangleHeight(int height, int temporalValue,int displace){
              this.height= height+temporalValue-displace;
		}
		private void reduceRectangleWidth(int width, int temporalValue,int displace){
			this.width= width+temporalValue-displace;
		}
		private void createOrReturnReducingValue(int panelWidthOrLength,int rectangleWidthOrLength){
			reducingValue = panelWidthOrLength-rectangleWidthOrLength;
		}
		private void setRectangleHeightToMinimumHeight(){
			this.height = minimumHeight;
		}
		private void setRectangleWidthToMinimumWidth(){
			this.width = minimumHeight;
		}
		private boolean rectangleWidthIsTooSmall(int width){
			return width < minimumWidth;
		}
		private boolean rectangleHeightIsTooSmall(int height){
			return height < minimumHeight;
		}
		private boolean rectangleHeightIsTooBig(int rectangleHeightPlusX){
			return rectangleHeightPlusX > getPanelHeight();
		}
		private boolean rectangleWidthPlusXIsTooBig(int rectangleWidthPlusX){
			return rectangleWidthPlusX > getPanelWidth();
		}


		public Rectangle randomSize(){
			int displace = 5;
			height = (int)(Math.random()*getHeight());
			width = (int)(Math.random()*getWidth());

			int heightPlusX =Math.addExact(y , height) ;
			int widthPlusX = Math.addExact(x , width);

			if (rectangleHeightIsTooBig(heightPlusX)){
				createOrReturnReducingValue(height,heightPlusX);
				reduceRectangleHeight(height,reducingValue,displace);
			}
			if (rectangleHeightIsTooSmall(height)) {
				setRectangleHeightToMinimumHeight();
			}
			if (rectangleWidthPlusXIsTooBig(widthPlusX)){
				createOrReturnReducingValue(width,widthPlusX);
				reduceRectangleWidth(width,reducingValue,displace);
			}
			if (rectangleWidthIsTooSmall(width)) {
				setRectangleWidthToMinimumWidth();
			}
			return new Rectangle(width,height);
		}
	}

	class RandomColorListener extends RandomRectDrawPanel implements ActionListener{
		private void changeRectangleColorToRandomColor(){
			drawPanel.randomColor();
		}
		private void showNewColorToScreen(){
			drawPanel.repaint();
		}
		public void actionPerformed(ActionEvent clickedButton) {
			changeRectangleColorToRandomColor();
			showNewColorToScreen();

		}
	}

	class SizeListener extends RandomRectDrawPanel implements ActionListener{
		private void changeRectangleSizeToRandomSize(){
			drawPanel.randomSize();
		}
		private void showNewSizeToScreen(){
			drawPanel.repaint();
		}
		public void actionPerformed(ActionEvent clickedButton) {
			changeRectangleSizeToRandomSize();
			showNewSizeToScreen();

		}
	}
}
