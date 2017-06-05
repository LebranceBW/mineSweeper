package myMineSweeper;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

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
	private boolean isBack = false;
	private Image image;//缓冲图像
	BufferedImage img1 = null;
	BufferedImage img2 = null;
	public mineTipsPanel()
	{
		super();
		init();
	}
	public mineTipsPanel(boolean flag)
	{
		super();
		isBack=flag;
		init();
	}
	private void init()
	{
		try
		{
			img1 = ImageIO.read(this.getClass().getResourceAsStream("/myMineSweeper/img/timer.png"));
			img2 = ImageIO.read(this.getClass().getResourceAsStream("/myMineSweeper/img/mines.png"));
		} catch (IOException e)
		{
			e.printStackTrace();
			System.out.println(this.getClass().getResource("/myMineSweeper/img/timer.png").getFile());
		}
		if(!isBack)
			{
				this.setPreferredSize(new Dimension(180, 50));
			}
		else 
			this.setPreferredSize(new Dimension(400, 50));
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
	private void drawBufferedImage()
	{
		 image = createImage(this.getWidth(), this.getHeight());
		    // 获取图像上下文对象
		 Graphics g = image.getGraphics();
		 if(!isBack)
			{
				g.setColor(Color.black);
				g.setFont(new Font("simkai",Font.PLAIN, 15));
				switch(tipType)
				{
					case TIME:
						g.drawImage(img1,0,0,null);
						g.drawString("已用时间："+tipText,50,30);
						break;
					case MINEFLAGGED:
						g.drawImage(img2, 0, 0,null);
						g.drawString("已标记的地雷数："+tipText,50,30);
						break;
					}
				}
	}
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
//		g.setColor(Color.white);
//		g.drawRect(0, 0, getWidth(), getHeight());
		drawBufferedImage();
		g.drawImage(image, 0, 0, this);
	}
}
