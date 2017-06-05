package myMineSweeper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JToggleButton;
/*
 * 状态status：绘图的状态
 * 1. null 不显示
 * 2. Flagged 被标记了有雷
 * 3. # 数字表示周围的地雷数字
 * 4. mine 有雷
 */
public class mineButton extends JToggleButton {
	/**
	 * 
	 */
	public static final int Mine = -1;
	public static final int Flagged =10;
	private static final long serialVersionUID = 1L;
	private int pattern = 0;
	public String imgDir = "H:\\Java工作区\\mineSweeper\\img\\";
	private BufferedImage img1 = null;
	private BufferedImage img2 = null;
	public mineButton()
	{
		super();
		setBackground(Color.white);
		try
		{
			img1 = ImageIO.read(this.getClass().getResourceAsStream("/myMineSweeper/img/mines.png"));
			img2 = ImageIO.read(this.getClass().getResourceAsStream("/myMineSweeper/img/flag.png"));
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(this.getClass().getResource("/myMineSweeper/img/mines.png").getFile());
		}
	}
	public boolean setPattern(int inpattern)
	{
		if(inpattern<=10&&inpattern>-3)
		{
			pattern=inpattern;
			return true;
		}
		return false;
	}
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setFont(new Font("MS PGothic",Font.PLAIN, 30));
		switch(pattern)
		{
		case Flagged: 
			g.setColor(Color.BLUE);
//			g.drawLine(width-10, 20, width-10, height-20);
//			g.setColor(Color.red);
//			Polygon triAngle = new Polygon();
//			triAngle.addPoint(width-10, 20);triAngle.addPoint(width-10, 50);triAngle.addPoint(width-30, 35);
//			g.drawString("♬",getWidth()/2-fm.stringWidth("♬"), getHeight()/2+fm.getAscent());
			g.drawImage(img2,0,0,null);
			break;
//		case "1":
//			g.setColor(Color.BLUE);
////			g.drawString("1",getWidth()/2-fm.stringWidth("1"), getHeight()/2+fm.getAscent());
//			g.drawString("1",10,30);
//			break;
		case Mine:
			g.setColor(Color.BLUE);
//			g.drawString("※",getWidth()/2-fm.stringWidth("※"), getHeight()/2+fm.getAscent());
			g.drawImage(img1,0,0,null);
		}
		if(pattern>0&&pattern<10)
		{
			g.drawString(String.valueOf(pattern),10,30);
		}
	}
}
