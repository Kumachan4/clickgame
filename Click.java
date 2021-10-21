//Click.java

/*�N���b�N�Q�[���̍쐬
�Q�[���̊T�v
�E�N���b�N�Ώۂ������_���Ȉʒu�ɕ\������
�E��L�̑Ώۂ͈�莞�Ԍo�ƈʒu���ς��
�E�}�E�X�|�C���^�̈ʒu�𒆐S�ɃA�C�R����\������
�E��L2���d�Ȃ����Ƃ��Ɉȉ��̏���������
	�E�N���b�N�Ώۂ�Ԃ�����
	�E�N���b�N���ꂽ��_�������Z����
�E15�_��������N���A�̕�����\������

*/

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.JFrame;

public class Click extends JFrame {

	private final static int LENGTH_OF_WINDOW = 300;	//�E�B���h�E�̑傫��

	private final static int LENGTH_OF_TARGET = 30;		//�^�[�Q�b�g�̑傫��

	private final static int LENGTH_OF_PLAYER = 8;		//�v���C���[�̑傫��

	private final static int GAME_END_COUNT   = 15;		//�J�E���^�[


	private Rectangle target = new Rectangle(-LENGTH_OF_TARGET, -LENGTH_OF_TARGET,
			LENGTH_OF_TARGET, LENGTH_OF_TARGET);

	private Rectangle player = new Rectangle(-LENGTH_OF_PLAYER, -LENGTH_OF_PLAYER,
			LENGTH_OF_PLAYER, LENGTH_OF_PLAYER);

	private int score = 0;


	public static void main(String[] args) {
		new Click();
	}

	public Click() {

		setTitle("�N���b�N�Q�[��");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(LENGTH_OF_WINDOW, LENGTH_OF_WINDOW);

		MyMouseAdapter myMouseAdapter = new MyMouseAdapter();
		addMouseListener(myMouseAdapter);
		addMouseMotionListener(myMouseAdapter);

		setVisible(true);

		Timer timer = new Timer();
		timer.schedule(new MyTimeTask(), 1000l, 1500l);	//�Œ�x�����s���J��Ԃ�
	}


	public void paint(Graphics g)
	{
	    g.drawImage(getScreen(), 0, 0, this);
	}


	private Image getScreen() {
		Image screen = createImage(LENGTH_OF_WINDOW, LENGTH_OF_WINDOW);
	   	Graphics2D g = (Graphics2D)screen.getGraphics();


		//�^�[�Q�b�g�`��
		if(target.intersects(player))
		{
			g.setColor(Color.red);
		}else
		{	
			g.setColor(Color.black);
		}
		g.draw(target);

		//�v���C���[�`��
		g.setColor(Color.green);
		g.draw(player);

		//�_���\��
		g.setColor(Color.black);
		g.drawString("15�_��CLEAR�I�@�_��"+score,20,50);
			if (score>=GAME_END_COUNT) {	//�Q�[���N���A
			g.setColor(Color.blue);
			g.drawString("������GAME CLEAR������", 70, 100);
			}
		return screen;
	}


	private class MyTimeTask extends TimerTask {

		@Override	//�^�[�Q�b�g�������_���ɕ\������悤�ɂ��Ă���
		public void run() {
			if (score >= GAME_END_COUNT) {
				return; // �Q�[���G���h
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
		@Override	//�}�E�X�ŃN���b�N������_��������d�g��
			public void mouseClicked(MouseEvent e){
				//�X�R�A
				if(player.intersects(target)){
				score ++;
				}
				repaint();
			}

		@Override	//�R���|�[�l���g�̈ʒu�ݒ�
			public void mouseMoved(MouseEvent e){
				//�����`�̈ʒu�ύX
				player.setLocation(e.getX() -LENGTH_OF_PLAYER /2, e.getY()-LENGTH_OF_PLAYER/2 );
				//�`�悷��
				repaint();
				}
	}

}