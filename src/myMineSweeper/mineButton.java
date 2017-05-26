package myMineSweeper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

import javax.swing.JButton;
/*
 * 状态status：绘图的状态
 * 1. null 不显示
 * 2. Flagged 被标记了有雷
 * 3. # 数字表示周围的地雷数字
 * 4. mine 有雷
 */
public class mineButton extends JButton {
	private String status;
	public mineButton()
	{
		super();
	}
	protected void paintComponent(Graphics g)
	{
		int width=getWidth();
		int height=getHeight();
		switch(status)
		{
		case "Flagged": 
			g.setColor(Color.BLACK);
			g.drawLine(width-10, 20, width-10, height-20);
			g.setColor(Color.red);
			Polygon triAngle = new Polygon();
			triAngle.addPoint(width-10, 20);triAngle.addPoint(width-10, 50);triAngle.addPoint(width-30, 35);
			g.drawPolygon(triAngle);
			break;
		case "1":
			g.setColor(Color.BLUE);
			g.drawString("1",2*width/5, 2*height/5);
			break;
		case "mine":
			g.setColor(Color.red);
			g.drawString("※",2*width/5, 2*height/5);
		}
	}
}
