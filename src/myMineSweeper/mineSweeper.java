package myMineSweeper;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import myMineSweeper.mineButton;
import myMineSweeper.mineTipsPanel;
public class mineSweeper extends JFrame{
		/**
	 * 
	 */
	/*游戏相关的全局变量*/
	private final int MAX_X = 10;//雷区的最大行数
	private final int MAX_Y = 10;//雷区的最大列数
	private final int MINETOTAL=10;	//地雷总数	
	
	private boolean gameOver=false;
	private mineButton[][] minebutton;	
//	private int[][] userMineMap;//玩家的地雷地图，标记了的为-1，没有雷是1，未探索为0
	private Set<mineCoordinates> pointsFlaggedSet = new HashSet<mineCoordinates>();
	private int safeAreaCounts;
	private int timeSpared;
	private int[][] mineMap;	//看看是不是有地雷哦，-1是有的意思
	private boolean firstClick; 
	
	/*绘图相关的变量*/
	private mineTipsPanel gameTipsPanel = new mineTipsPanel(true); 
	private JPanel minePanel = new JPanel();
	private mineTipsPanel timerTip = new mineTipsPanel(String.valueOf(timeSpared)+"秒",mineTipsPanel.TIME);
	private mineTipsPanel Tip = new mineTipsPanel(String.valueOf(pointsFlaggedSet.size())+"个",mineTipsPanel.MINEFLAGGED);
	Timer time = new Timer(true);
	private static final long serialVersionUID = 1L;
	public class mineCoordinates
	{
		public int x;
		public int y;
		public mineCoordinates()
		{
			x=-1;y=-1;// TODO Auto-generated constructor stub
		}
		public mineCoordinates(int xindex,int yindex)
		{
			x=xindex;y=yindex;// TODO Auto-generated constructor stub
		}
		public void setmineCoordinates(int xindex,int yindex)
		{
			x=xindex;y=yindex;// TODO Auto-generated constructor stub
		}
	}
	public static void main(String[] args)
		{
			mineSweeper ms = new mineSweeper();
			ms.setVisible(true);
			ms.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		/*
		按钮响应
		 */
		public mineSweeper() {
			super();
			try
			{
				Image Ico = ImageIO.read(this.getClass().getResourceAsStream("/myMineSweeper/img/mines.png"));
				setIconImage(Ico);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			setSize(new Dimension(400, 500));
			
			setTitle("扫雷");
			
			getContentPane().setLayout(new BorderLayout(0, 10));
			getContentPane().add(gameTipsPanel, BorderLayout.NORTH);
			gameTipsPanel.setLayout(new BorderLayout(1,1));
			gameTipsPanel.add(timerTip,BorderLayout.WEST);
			gameTipsPanel.add(Tip,BorderLayout.CENTER);
			
			
			minePanel.setPreferredSize(new Dimension(400, 400));
			minePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
			getContentPane().add(minePanel, BorderLayout.CENTER);
			GridLayout gbl_minePanel = new GridLayout(10,10);
			minePanel.setLayout(gbl_minePanel);
			
			mineInit();
			for(int x=0;x<MAX_X;x++) 					
				for(int y=0;y<MAX_Y;y++)
				{
					minebutton[x][y]=new mineButton();
					minePanel.add(minebutton[x][y]);
					minebutton[x][y].addMouseListener(new mineSweeperAction(x,y));
				}
			time.schedule(new TimeTask(), 1000, 1000);
		}
		public void mineInit()	//参数初始化
		{
			minebutton=new mineButton[MAX_X][MAX_Y];	
			firstClick=true;
			mineMap = new int[MAX_X][MAX_Y];
//			userMineMap = new int[MAX_X][MAX_Y];
		}

		private void mineGenerate(int x,int y)//生成雷区
		{
			int xtemp,ytemp;
			for(int i=0;i<MINETOTAL;i++)
			{
				xtemp=(int)(Math.random()*MAX_X);
				ytemp=(int)(Math.random()*MAX_Y);
				if(Math.abs(xtemp-x)<2&&Math.abs(ytemp-y)<2||mineMap[xtemp][ytemp]==-1)
				{
					i--;//continue后i++还是会执行
					continue;
				}
				mineMap[xtemp][ytemp]=mineButton.Mine;
				for(int xOffset=-1;xOffset<2;xOffset++)
					for(int yOffset=-1;yOffset<2;yOffset++)
					{
						if((xtemp+xOffset)<0||(ytemp+yOffset)<0||(xtemp+xOffset)>=MAX_X||(ytemp+yOffset)>=MAX_Y||(xOffset==0&yOffset==0)||mineMap[xtemp+xOffset][ytemp+yOffset]==-1)
								continue;
						mineMap[xtemp+xOffset][ytemp+yOffset]++;
					}
//				minebutton[xtemp][ytemp].setPattern(mineButton.Mine);
//				minebutton[xtemp][ytemp].updateUI();
			}
		}	
		
		private boolean isWin()
		{
			if(safeAreaCounts==MAX_X*MAX_Y-MINETOTAL)
				return true;
				else if(pointsFlaggedSet.size()==MINETOTAL)
					{
//					for(int i=0;i<MAX_X;i++)
//						for(int j=0;j<MAX_Y;j++)
//						{
//							if(userMineMap[i][j]==FLAGGED&&mineMap[i][j]!=mineButton.Mine)
//								return false;
//						}
//					return true;
						for(Iterator<mineCoordinates> i=pointsFlaggedSet.iterator();i.hasNext();)
							{
								mineCoordinates temp = i.next();
								if(mineMap[temp.x][temp.y]==mineButton.Mine)
									continue;
								else
									return false;
							}
						return true;		
					}
				else return false;
		}
		
		private class TimeTask extends TimerTask
		{

			@Override
			public void run()
			{
				if(!gameOver&&!firstClick)// TODO Auto-generated method stub
				{	
					timeSpared++;
					timerTip.refresh(String.valueOf(timeSpared)+"秒");
				}
			}
			
		}
		
		private void gameOver(boolean isWin)
		{
			gameOver=true;
			for(int i=0;i<MAX_X;i++)
				for(int j=0;j<MAX_Y;j++)
				{
					minebutton[i][j].setPattern(mineMap[i][j]);
					minebutton[i][j].repaint();
					minebutton[i][j].setEnabled(false);
					mineMap[i][j]=0;
					pointsFlaggedSet.clear();
				}
			timeSpared=0;
			firstClick=true;
			safeAreaCounts=0;
			timeSpared=0;

			if(isWin)
				JOptionPane.showMessageDialog(minePanel, "你胜利了！确认以重新开始");
			else
				JOptionPane.showMessageDialog(minePanel, "你失败了！确认以重新开始");
			gameRestart();
		}
		
		
		private void gameRestart()
		{
			timerTip.refresh(String.valueOf(timeSpared)+"秒");
			Tip.refresh(String.valueOf(pointsFlaggedSet.size())+"个");
			for(int i=0;i<MAX_X;i++)
				for(int j=0;j<MAX_Y;j++)
				{
					minebutton[i][j].setPattern(mineMap[i][j]);
					minebutton[i][j].setEnabled(true);
					minebutton[i][j].setSelected(false);
					minebutton[i][j].repaint();
				}
			gameOver=false;
		}
		
		
		public class mineSweeperAction  implements MouseListener,Runnable
		{
			private int xindex,yindex;
			public mineSweeperAction(int x1index,int y1index)
			{
				xindex=x1index;
				yindex=y1index;
			}

			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(!gameOver)
				switch(e.getButton())
				{
					case 1:new mineSweeperAction(xindex,yindex).run(); break;			
					case 3:
						boolean hasFind = false;
						for(Iterator<mineCoordinates> i=pointsFlaggedSet.iterator();i.hasNext();)
						{
							mineCoordinates temp = i.next();
							if(xindex==temp.x&&yindex==temp.y)
								{
									pointsFlaggedSet.remove(temp);
									minebutton[xindex][yindex].setPattern(0);
									hasFind=true;
									break;
								}
						}
						if(!hasFind)
						{
							pointsFlaggedSet.add(new mineCoordinates(xindex,yindex));
							minebutton[xindex][yindex].setPattern(mineButton.Flagged);
						}
						minebutton[xindex][yindex].repaint();
						Tip.refresh(String.valueOf(pointsFlaggedSet.size()+"个"));
						break;
				}
				if(isWin())
				gameOver(true);
			}
			
			
			public void run()
			{
				if(firstClick)				
				{
					mineGenerate(xindex,yindex);
					firstClick=false;
				}
				
				if(mineMap[xindex][yindex]==-1)
				{
					minebutton[xindex][yindex].setPattern(mineButton.Mine);
					gameOver(isWin());
				}
				else
					showMineTip(xindex,yindex);
				
			}
	
			private void showMineTip(int xindex,int yindex)
			{
				if(mineMap[xindex][yindex]!=0)
				{
					minebutton[xindex][yindex].setPattern(mineMap[xindex][yindex]);
					minebutton[xindex][yindex].setSelected(true);
					safeAreaCounts++;
					minebutton[xindex][yindex].repaint();
				}
				else if(mineMap[xindex][yindex]==0)
				{
					mineMap[xindex][yindex]=-2;
					for(int xOffset=-1;xOffset<2;xOffset++)
						for(int yOffset=-1;yOffset<2;yOffset++)
						{
							if((xindex+xOffset)<0||(yindex+yOffset)<0||(xindex+xOffset)>=MAX_X||(yindex+yOffset)>=MAX_Y||(xOffset==0&yOffset==0)||mineMap[xindex+xOffset][yindex+yOffset]==-1)
									continue;
							showMineTip(xindex+xOffset,yindex+yOffset);
						}
					minebutton[xindex][yindex].setSelected(true);
				}
			}

			@Override
			public void mousePressed(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}
			
		
		}
//	public void paint(Graphics g)
//	{
//		
//		BufferedImage backImg = null;
//		String imgDir = "H:\\Java工作区\\mineSweeper\\img\\";
//		try
//		{
//			backImg = ImageIO.read(new File(imgDir+"background.jpg"));
//		} catch (IOException e)
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		g.drawImage(backImg,0,0,null);
//		super.paint(g);
//	}
}
