//Click.java

/*クリックゲームの作成
ゲームの概要
・クリック対象をランダムな位置に表示する
・上記の対象は一定時間経つと位置が変わる
・マウスポインタの位置を中心にアイコンを表示する
・上記2つが重なったときに以下の処理をする
	・クリック対象を赤くする
	・クリックされたら点数を加算する
・15点入ったらクリアの文字を表示する

*/

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.JFrame;

public class Click extends JFrame {

	private final static int LENGTH_OF_WINDOW = 300;	//ウィンドウの大きさ

	private final static int LENGTH_OF_TARGET = 30;		//ターゲットの大きさ

	private final static int LENGTH_OF_PLAYER = 8;		//プレイヤーの大きさ

	private final static int GAME_END_COUNT   = 15;		//カウンター


	private Rectangle target = new Rectangle(-LENGTH_OF_TARGET, -LENGTH_OF_TARGET,
			LENGTH_OF_TARGET, LENGTH_OF_TARGET);

	private Rectangle player = new Rectangle(-LENGTH_OF_PLAYER, -LENGTH_OF_PLAYER,
			LENGTH_OF_PLAYER, LENGTH_OF_PLAYER);

	private int score = 0;


	public static void main(String[] args) {
		new Click();
	}

	public Click() {

		setTitle("クリックゲーム");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(LENGTH_OF_WINDOW, LENGTH_OF_WINDOW);

		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		addMouseListener(myMouseAdapter);
		addMouseMotionListener(myMouseAdapter);

		setVisible(true);

		Timer timer = new Timer();
		timer.schedule(new MyTimeTask(), 1000l, 1500l);	//固定遅延実行を繰り返す
	}


	public void paint(Graphics g)
	{
	    g.drawImage(getScreen(), 0, 0, this);
	}


	private Image getScreen() {
		Image screen = createImage(LENGTH_OF_WINDOW, LENGTH_OF_WINDOW);
	   	Graphics2D g = (Graphics2D)screen.getGraphics();


		//ターゲット描画
		if(target.intersects(player))
		{
			g.setColor(Color.red);
		}else
		{	
			g.setColor(Color.black);
		}
		g.draw(target);

		//プレイヤー描画
		g.setColor(Color.green);
		g.draw(player);

		//点数表示
		g.setColor(Color.black);
		g.drawString("15点でCLEAR！　点数"+score,20,50);
			if (score>=GAME_END_COUNT) {	//ゲームクリア
			g.setColor(Color.blue);
			g.drawString("☆★☆GAME CLEAR☆★☆", 70, 100);
			}
		return screen;
	}


	private class MyTimeTask extends TimerTask {

		@Override	//ターゲットをランダムに表示するようにしている
		public void run() {
			if (score >= GAME_END_COUNT) {
				return; // ゲームエンド
				}else{
			int x = (int)(Math.random() * LENGTH_OF_WINDOW);
			int y = (int)(Math.random() * LENGTH_OF_WINDOW);
			target.setLocation(x, y);
			repaint();
			}
		}

	}


	private class MyMouseAdapter extends MouseAdapter
	{
		@Override	//マウスでクリックしたら点数が入る仕組み
			public void mouseClicked(MouseEvent e){
				//スコア
				if(player.intersects(target)){
				score ++;
				}
				repaint();
			}

		@Override	//コンポーネントの位置設定
			public void mouseMoved(MouseEvent e){
				//長方形の位置変更
				player.setLocation(e.getX() -LENGTH_OF_PLAYER /2, e.getY()-LENGTH_OF_PLAYER/2 );
				//描画する
				repaint();
				}
	}

}