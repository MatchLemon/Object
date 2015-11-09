import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChessBoard extends JFrame {
	final JLabel label1 = new JLabel();
	final JTextField textField1 = new JTextField();
	final JLabel label2 = new JLabel();
	final JTextField textField2 = new JTextField();
	final JLabel label3 = new JLabel();
	final JTextField textField3 = new JTextField();
	final JButton suButton = new JButton();
	final JButton caButton = new JButton();
	final JPanel viewPanel = new JPanel();

	int length = 1;
	int board[][];
	JLabel countLabel[][];
	Color[] setColor = new Color[13];

	// 给窗体初始化
	public ChessBoard() {
		this.setTitle("棋盘覆盖");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.getContentPane().add(viewPanel, BorderLayout.NORTH);

		this.setBounds(0, 0, 800, 600);
		label1.setText("棋盘大小(偶数)");
		label1.setVisible(true);

		label2.setText("特殊方格所在行数（从0开始）");
		label3.setText("特殊方格所在列数（从0开始）");

		viewPanel.add(label1);
		textField1.setColumns(5);
		viewPanel.add(textField1);

		viewPanel.add(label2);
		textField2.setColumns(5);
		viewPanel.add(textField2);

		viewPanel.add(label3);
		textField3.setColumns(5);
		viewPanel.add(textField3);

		suButton.setText("确定");
		viewPanel.add(suButton);
		suButton.addActionListener(new ButtonListener());
		caButton.setText("取消");
		caButton.addActionListener(new ButtonListener());
		viewPanel.add(caButton);

		setColor[0] = new Color(255, 255, 255);// 白色
		setColor[1] = new Color(192, 192, 192);// lightgray
		setColor[2] = new Color(128, 128, 128);// gray
		setColor[3] = new Color(64, 64, 64);// darkgray
		setColor[4] = new Color(0, 0, 0);// black
		setColor[5] = new Color(255, 0, 0);// red
		setColor[6] = new Color(255, 175, 175);// pink
		setColor[7] = new Color(255, 200, 0);// orange
		setColor[8] = new Color(255, 255, 0);// yellow
		setColor[9] = new Color(0, 255, 0);// green
		setColor[10] = new Color(255, 0, 255);// magenta
		setColor[11] = new Color(0, 255, 255);// cyan
		setColor[12] = new Color(0, 0, 255);// blue

	}

	void setBoard(int sX, int sY, int dX, int dY, int size, int board[][]) {
		if (size == 1)
			return;
		int t = length++;
		size = size / 2;

		/****** 左上角 *****/
		if ((sX + size > dX) && (sY + size > dY))
			setBoard(sX, sY, dX, dY, size, board);
		else {
			board[sX + size - 1][sY + size - 1] = t;
			setBoard(sX, sY, sX + size - 1, sY + size - 1, size, board);
		}

		/****** 左下角 ****/
		if ((sX + size <= dX) && (sY + size) > dY)
			setBoard(sX + size, sY, dX, dY, size, board);
		else {
			board[sX + size][sY + size - 1] = t;
			setBoard(sX + size, sY, sX + size, sY + size - 1, size, board);
		}

		/****** 右上角 *****/
		if ((sX + size > dX) && (sY + size) <= dY)
			setBoard(sX, sY + size, dX, dY, size, board);
		else {
			board[sX + size - 1][sY + size] = t;
			setBoard(sX, sY + size, sX + size - 1, sY + size, size, board);
		}

		/****** 右下角 ****/
		if ((sX + size <= dX) && (sY + size) <= dY)
			setBoard(sX + size, sY + size, dX, dY, size, board);
		else {
			board[sX + size][sY + size] = t;
			setBoard(sX + size, sY + size, sX + size, sY + size, size, board);
		}
	}

	// /给board赋值
	void initBoard(int len) {
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				board[i][j] = 0;
			}
		}
	}

	// /将board清空
	void cleBoard(int len) {
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				countLabel[i][j].setText("");
			}
		}
	}

	public static void main(String[] args) {

		ChessBoard cb = new ChessBoard();
		cb.setVisible(true);

	}

	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			String name = button.getActionCommand();
			String textChess = textField1.getText();
			String textRow = textField2.getText();
			String textColumn = textField3.getText();
			int countChess = Integer.parseInt(textChess);

			int row = Integer.parseInt(textRow);
			int column = Integer.parseInt(textColumn);

			board = new int[countChess][countChess];
			countLabel = new JLabel[countChess][countChess];

			if (name.equals("确定")) {
				initBoard(countChess);
				setBoard(0, 0, row, column, countChess, board);

				final JPanel labelPanel = new JPanel();
				getContentPane().add(labelPanel, BorderLayout.CENTER);
				labelPanel.updateUI();
				final GridLayout gridLayout = new GridLayout(countChess, 0);
				gridLayout.setVgap(0);
				gridLayout.setHgap(0);
				labelPanel.setLayout(gridLayout);

				for (int i = 0; i < countChess; i++) {
					for (int j = 0; j < countChess; j++) {
						countLabel[i][j] = new JLabel(
								Integer.toString(board[i][j]));
						countLabel[i][j].setOpaque(true);
						countLabel[i][j]
								.setBackground(setColor[board[i][j] % 13]);
						labelPanel.add(countLabel[i][j]);
					}
				}

				length = 1;
			} else {
				textField1.setText("");
				textField2.setText("");
				textField3.setText("");
				length = 1;
			}
		}
	}
}