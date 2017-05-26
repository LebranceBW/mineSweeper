package myMineSweeper;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import myMineSweeper.mineButton;
public class mineSweeper extends JFrame{
		/**
	 * 
	 */
	private final int MAX_AREA = 100;
	JLabel welLabel = new JLabel("\u626B\u96F7\u5C0F\u6E38\u620F");				//��ӭ����
	mineButton[] minebutton;
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args)
		{
			System.out.println("run?");
			mineSweeper ms = new mineSweeper();
			ms.setVisible(true);
			ms.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		/*
		 ���캯������ʼ��
		 */
		public mineSweeper() {
			setSize(new Dimension(400, 500));
			setTitle("mineSweeper");
			getContentPane().setLayout(new BorderLayout(0, 30));
			
			welLabel.setName("welLabel");
			getContentPane().add(welLabel, BorderLayout.NORTH);
			
			JPanel minePanel = new JPanel();
			minePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
			getContentPane().add(minePanel, BorderLayout.CENTER);
			GridLayout gbl_minePanel = new GridLayout(10,10);

			minePanel.setLayout(gbl_minePanel);
			
			minebutton=new mineButton[MAX_AREA];
			for(int i=0;i<MAX_AREA;i++)
			{
				minebutton[i]=new mineButton();
				minePanel.add(minebutton[i]);
			}
			// TODO Auto-generated constructor stub
		}
		
		public class mineSweeperAction implements MouseListener
		{

			@Override
			public void mouseClicked(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
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
}
