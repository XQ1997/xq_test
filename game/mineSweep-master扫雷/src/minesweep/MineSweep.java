package minesweep;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
//import javax.swing.JApplet;

public class MineSweep extends JFrame {
	private static final long serialVersionUID = -144004528048552373L;
	JPanel game;
	CardLayout cardLayout = new CardLayout();

	
	public MineSweep() { // 启动

		getContentPane().setLayout(cardLayout);
		System.gc();
		setBackground(Color.darkGray);
		
		setSize(800,510);
		mainWindow();
		selectClass();
	}

	public void beginGame(int difficulty) { // 调用Game开始游戏

		if (game != null) {
			remove(game);
		}
		game = new Game(difficulty);
		getContentPane().add(game);
		cardLayout.next(getContentPane());
	}

	public void mainWindow() { // 主起始窗口

		JPanel mainPanel = new JPanel(new GridLayout());
		mainPanel.setPreferredSize(new Dimension(800, 480));
		mainPanel.setBackground(Color.DARK_GRAY);
		JButton start = new JButton("开始扫雷游戏");
		start.setCursor(new Cursor(Cursor.HAND_CURSOR));

		start.setBackground(Color.DARK_GRAY);
		start.setFont(new Font(null, Font.CENTER_BASELINE, 100));
		mainPanel.add(start);
		getContentPane().add(mainPanel);
		setVisible(true);

		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// remove(nowPanel);
				cardLayout.next(getContentPane());
			}
		});

	}

	public void selectClass() { // 选择难度窗口
		JPanel selectPanel;
		selectPanel = new JPanel(new FlowLayout());
		selectPanel.setBackground(Color.DARK_GRAY);
		selectPanel.setPreferredSize(new Dimension(800, 480));

		final JButton[] buttons = new JButton[4];
		String[] buttonsName = { "简单", "中等", "困难", "返回" };
		ActionListener[] actions = new ActionListener[4];

		ActionListener main = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.previous(getContentPane());
			}
		};

		ActionListener begin = new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource() == buttons[0]) {
					beginGame(0);
				} else {
					if (arg0.getSource() == buttons[1]) {
						beginGame(1);
					} else {
						beginGame(2);
					}
				}
				System.gc();
			}
		};

		actions[0] = begin;
		actions[1] = begin;
		actions[2] = begin;
		actions[3] = main;

		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton(buttonsName[i]);
			buttons[i].setBackground(Color.BLACK);
			buttons[i].setCursor(new Cursor(Cursor.HAND_CURSOR));

			buttons[i].setFont(new Font(null, Font.BOLD, 80));
			buttons[i].addActionListener(actions[i]);
			selectPanel.add(buttons[i]);
		}

		buttons[3].setPreferredSize(new Dimension(600, 100));

		getContentPane().add(selectPanel);
	}
}


/**
 * 
 * @author hrmthw
 * 
 */
class Game extends JPanel {

	class Cell extends JButton {
		/**
		 * 
		 */
		private static final long serialVersionUID = -1579037868724460466L;
		static final int MINE = -1, OFF = 0, NUMBER = 1, MARKED = 2;
		int status = OFF;
		int underGround;
		int posx, posy;
		Cell[] neighbours = new Cell[8];
		int clickedTimes = 0;

		Timer doubleClickTimer = new Timer(200, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (clickedTimes == 1) {
					leftClicked();
					clickedTimes--;
				} else if (clickedTimes == 2) {
					doubleClick();
					clickedTimes--;
					clickedTimes--;
				}

				doubleClickTimer.stop();
			}
		});

		MouseListener cellAction = new MouseListener() {

			public void mouseClicked(MouseEvent e) {

			}

			public void mouseEntered(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {
				if (status == OFF) {
					setIcon(icons[0]);
				}
			}

			public void mousePressed(MouseEvent e) {
				if (status == OFF) {
					setIcon(icons[9]);
				}
				if (timeCost == 0) {
					timer.start();
				}
				if (e.getButton() == 1) {
					clickedTimes++;
					if (clickedTimes == 1) {
						doubleClickTimer.start();
					}
				}
				if (e.getButton() == 3) {
					rightClick();
				}
			}

			public void mouseReleased(MouseEvent e) {

			}
		};

		public Cell(int underGround, int x, int y) {
			this.underGround = underGround;
			posx = x;
			posy = y;
			addMouseListener(cellAction);
		}

		public void doubleClick() {

			int num = 0;
			for (int i = 0; i < 8; i++) {
				if (neighbours[i] != null && neighbours[i].status == MARKED)
					num++;
			}
			if (num == underGround && num != 0) {
				for (int i = 0; i < 8; i++) {
					if (neighbours[i] != null && neighbours[i].status != MARKED
							&& neighbours[i].status == OFF)
						if (neighbours[i].leftClicked() == 1)
							break;
					;
				}
			}

		}

		public int leftClicked() {
			if (status != OFF) {
				return 0;
			}
			status = NUMBER;
			switch (underGround) {
			case 0:
				setIcon(icons[11]);
				for (int i = 0; i < 8; i++) {
					if (neighbours[i] != null && neighbours[i].status == OFF)
						neighbours[i].leftClicked();
				}
				break;
			case -1:
				gameover();
				return 1;
			default:
				setIcon(icons[underGround]);
				setVisible(false);
				setVisible(true);
				break;
			}

			int breakable = 0;
			for (int i = 0; i < cells.length; i++) {
				for (int j = 0; j < cells[0].length; j++) {
					if (cells[i][j].underGround != -1
							&& cells[i][j].status == Cell.OFF) {
						breakable = 1;
						break;
					}
				}
				if (breakable == 1)
					break;
			}
			if (breakable == 0) {
				win();
				return 1;
			}

			return 0;
		}

		public void rightClick() {
			if (status == OFF) {
				status = MARKED;
				setIcon(icons[10]);
				found++;
			} else if (status == MARKED) {
				status = OFF;
				setIcon(icons[0]);
				found--;
			}
			lastMineLabel.setText("Found:" + found + "/"
					+ difficulties[difficulty].mineNum);

		}

		public void setNeighbour(Cell[][] cells) {

			if (posy != 0) {
				neighbours[0] = cells[posy - 1][posx];
				if (posx < cells[0].length - 1)
					neighbours[1] = cells[posy - 1][posx + 1];
				if (posx > 0)
					neighbours[7] = cells[posy - 1][posx - 1];
			}
			if (posx < cells[0].length - 1)
				neighbours[2] = cells[posy][posx + 1];
			if (posx > 0)
				neighbours[6] = cells[posy][posx - 1];
			if (posy != cells.length - 1) {
				neighbours[4] = cells[posy + 1][posx];
				if (posx < cells[0].length - 1)
					neighbours[3] = cells[posy + 1][posx + 1];
				if (posx > 0)
					neighbours[5] = cells[posy + 1][posx - 1];
			}
		}

	}

	class Difficulty { // 不同难度预设
		int x;
		int y;
		int mineNum;

		Difficulty(int x, int y, int mineNum) {
			this.x = x;
			this.y = y;
			this.mineNum = mineNum;
		}
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3307057194262998313L;
	final Difficulty[] difficulties = { new Difficulty(9, 9, 10),
			new Difficulty(16, 16, 40), new Difficulty(30, 16, 99) };
	int difficulty;
	int found = 0; // 已标记数量
	JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

	int timeCost = 0; // 计时器

	JLabel lastMineLabel;
	Random random = new Random(); // 随机数产生器

	JLabel timerLabel = new JLabel("Cost:" + timeCost);

	Timer timer = new Timer(1000, new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			timeCost++;
			timerLabel.setText("Cost:" + timeCost);
			repaint();
		}
	});
	Timer paintTimer = new Timer(50, new ActionListener() { // 延时重绘定时器
				public void actionPerformed(ActionEvent arg0) {
					setVisible(false);
					setVisible(true);
					paintTimer.stop();
				}
			});

	JButton restartButton = new JButton("Restart!");
	ActionListener restart = new ActionListener() { // ”重新开始“事件
		public void actionPerformed(ActionEvent arg0) {
			init();
		}
	};

	JButton returnButton = new JButton("return top!"); //

	ActionListener returnActionListener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
	};
	private URL iconsURL[] = new URL[13]; // 图标组

	private Icon icons[] = new ImageIcon[13];

	Cell[][] cells; // 格子

	/**
	 * 
	 * @param difficulty
	 */
	public Game(int difficulty) { // 构造器

		for (int i = 0; i < iconsURL.length; i++) {
			iconsURL[i] = this.getClass().getResource("res/icon" + i + ".png");
		}

		for (int i = 0; i < iconsURL.length; i++) {
			icons[i] = new ImageIcon(iconsURL[i]);
		}

		this.difficulty = difficulty;
		setLayout(new BorderLayout());
		setBackground(Color.DARK_GRAY);
		JPanel ctrlContainer = new JPanel(new BorderLayout());
		ctrlContainer.setBackground(Color.gray);

		
		timerLabel.setFont(new Font(Font.SERIF,Font.BOLD ,20));
		timerLabel.setPreferredSize(new Dimension(200, 30));
		restartButton.addActionListener(restart);
		restartButton.setBackground(Color.LIGHT_GRAY);

		returnButton.addActionListener(returnActionListener); 
		lastMineLabel=new JLabel();
		lastMineLabel.setPreferredSize(new Dimension(200, 20));
		lastMineLabel.setFont(new Font(Font.SERIF,Font.BOLD ,20));
		
//		ctrlContainer.add(returnButton, BorderLayout.SOUTH);
		ctrlContainer.add(timerLabel, BorderLayout.WEST);
		ctrlContainer.add(restartButton, BorderLayout.CENTER);
		ctrlContainer.add(lastMineLabel, BorderLayout.EAST);

		
		add(ctrlContainer, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(returnButton, BorderLayout.SOUTH);
		mapGenerator(difficulty);
		paintTimer.start();
	}

	private void gameover() {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				if (cells[i][j].underGround == -1) {
					cells[i][j].setIcon(icons[12]);
					cells[i][j].status = Cell.MINE;
				}
			}
		}
		timer.stop();
		JOptionPane.showMessageDialog(null, "Game over!");
		init();
		// System.exit(0);
	}

	private void init() {
		int mineNum = difficulties[difficulty].mineNum;
		timeCost = 0;
		found = 0;
		timer.stop();
		timerLabel.setText("Cost:" + timeCost);
		lastMineLabel.setText("Found:" + found + "/"
				+ difficulties[difficulty].mineNum);

		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				cells[i][j].underGround = 0;
				cells[i][j].status = Cell.OFF;
				cells[i][j].setIcon(icons[0]);
			}
		} // 重置游戏

		for (int i = 0; i < mineNum; i++) {
			int randx = random.nextInt(cells[0].length);
			int randy = random.nextInt(cells.length);
			if (cells[randy][randx].underGround != -1) {
				cells[randy][randx].underGround = -1;
			} else {
				i--;
			}
		} // 随机产生地雷

		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				if (cells[i][j].underGround != 0) {
					continue;
				}
				int num = 0;
				for (int k = 0; k < 8; k++) {
					if (cells[i][j].neighbours[k] != null
							&& cells[i][j].neighbours[k].underGround == -1)
						num++;
				}
				cells[i][j].underGround = num;
			}
		} // 产生号码牌

	}

	private void mapGenerator(int dif) { // 地图构建
		final int BLOCK_SIZE = 25;
		int x = difficulties[dif].x;
		int y = difficulties[dif].y;
		JPanel map = new JPanel(new GridLayout(y, 0));
		map.setPreferredSize(new Dimension(x * 25, y * 25));
		centerPanel.add(map, BorderLayout.CENTER);

		restartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

		cells = new Cell[y][];
		for (int i = 0; i < cells.length; i++) {
			cells[i] = new Cell[x];
		}
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				cells[i][j] = new Cell(0, j, i);
				cells[i][j].setPreferredSize(new Dimension(BLOCK_SIZE,
						BLOCK_SIZE));
				cells[i][j].setIcon(icons[0]);
				map.add(cells[i][j]);
			}
		} // 产生相应数量的格子

		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				cells[i][j].setNeighbour(cells);
			}
		} // 设置邻居

		init(); // 游戏初始化
	}

	private void win() {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[0].length; j++) {
				if (cells[i][j].underGround == -1) {
					cells[i][j].setIcon(icons[10]);
					// cells[i][j].status=Cell.MARKED;
				}
			}
		}
		timer.stop();
		JOptionPane.showMessageDialog(null, "You finish it in " + timeCost
				+ "seconds.");
		init();

	}

}


