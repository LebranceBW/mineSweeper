package myMineSweeper;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class mineTipsPanel extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tipType;
	private String tipText = new String();
	public static final int MINEFLAGGED=1;
	public static final int TIME=2;
	BufferedImage img1 = null;
	BufferedImage img2 = null;
	public String baseDir = "H:\\Java工作区\\mineSweeper";
	
	public mineTipsPanel()
	{
		init();
	}
	private void init()
	{
		try
		{
			img1 = ImageIO.read(new File(baseDir+"\\img\\timer.png"));
			img2 = ImageIO.read(new File(baseDir+"\\img\\flag.png"));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(baseDir+"\\img\\flag");
		}
		this.setPreferredSize(new Dimension(180, 50));
//		this.setBackground(Color.pink);
		this.setBorder(new LineBorder(Color.black, 2));
	}
	public mineTipsPanel(String inTipText,int inTipType)
	{
		super();
		tipType=inTipType;
		tipText=inTipText;
		init();
	}
	
	public void refresh(String inTipText)
	{
		tipText=inTipText;
		updateUI();
	}
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setFont(new Font("simkai",Font.PLAIN, 15));
		switch(tipType)
		{
			case TIME:
				g.drawImage(img1,0,0,null);
				g.drawString("剩余时间："+tipText,50,30);
				break;
			case MINEFLAGGED:
				g.drawImage(img2, 0, 0,null);
				g.drawString("已标记的地雷数："+tipText,50,30);
				break;
		}
	}
}
