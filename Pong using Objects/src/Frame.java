import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
public class Frame extends JPanel implements KeyListener, ActionListener {

// Create a Ball object at the center
// o the 800 by 600 frame
//with a given width of 50
	Ball ball = new Ball(400, 300, 50);
	Paddle paddleLeft = new Paddle(25, 225);
	Paddle paddleRight = new Paddle(920, 225);
	
	//scoring variables
	int player1score=0;
	int player2score=0;
	Color black = new Color(0, 0, 0);
	Color red = new Color(225, 0, 0);
	
	
	/* paint is getting called roughly 60x per second */
	public void paint(Graphics g) {
		super.paintComponent(g);
		Font font = new Font("Monospaced", Font.BOLD, 90);
		g.setFont(font);
		g.drawString(player1score + "", 100, 100);
		g.drawString(player2score + "", 500, 100);
		
		int Xvelocity = ball.getVx();
		int Yvelocity = ball.getVy();
		//paint the ball
		ball.paint(g);
		paddleLeft.paint(g);
		paddleRight.paint(g);
		paddleLeft.setC(red);
		ball.setColor(black);
		
		if (ball.getX()>= 900) {
			ball.setX(400);
			ball.setY(300);
			player1score +=1;
		}
		if (ball.getX()<= 0) {
			ball.setX(400);
			ball.setY(300);
			player2score +=1;
		}	
		if (ball.getY()>= 550) {
			ball.setVelocityY(-Yvelocity);
		}
		if (ball.getY() <= 0) {
			ball.setVelocityY(-Yvelocity);
		}
		
		Rectangle rball = new Rectangle(ball.getX(), ball.getY(), ball.getWidth(), ball.getWidth());
		Rectangle rpaddleLeft = new Rectangle(paddleLeft.getX(), paddleLeft.getY(), paddleLeft.getWidth(), paddleLeft.getHeight());
		Rectangle rpaddleRight = new Rectangle(paddleRight.getX(), paddleRight.getY(), paddleRight.getWidth(), paddleRight.getHeight());
		
		
		if(rball.intersects(rpaddleLeft)) {
			ball.setVelocityX(-Xvelocity);
			ball.setVelocityY(-Yvelocity);
		}
		if (rball.intersects(rpaddleRight)) {
			ball.setVelocityX(-Xvelocity);
			ball.setVelocityY(-Yvelocity);
		}
		ball.paint(g);
		paddleLeft.paint(g);
		paddleRight.paint(g);
		}

	public static void main(String[] arg) {
		Frame f = new Frame();

	}

	@Override
	public void keyPressed(KeyEvent key) {
		if (key.getKeyCode()==87) {
			if(paddleLeft.getY()>= 0) {
				paddleLeft.setY(paddleLeft.getY()-25);
			}
		}
		if (key.getKeyCode()==83) {
			if (paddleLeft.getY()<= 425) {
				paddleLeft.setY(paddleLeft.getY()+25);
			}
		}
		if(key.getKeyCode()==38) {
			if (paddleRight.getY() >= 0) {
				paddleRight.setY(paddleRight.getY()-25);
			}
		}
		if(key.getKeyCode()==40) {
			if (paddleRight.getY() <= 425) {
				paddleRight.setY(paddleRight.getY()+25);
			}
		}
		
		}


	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode()==87 || arg0.getKeyCode()==83) {
			paddleLeft.setVy(0);
			
		}
		if(arg0.getKeyCode()==38 || arg0.getKeyCode()==40) {
			paddleRight.setVy(0);
			}
		}
	

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		repaint();
	}

	Timer t;

	public Frame() {
		JFrame f = new JFrame("Pong");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1000, 650);
		f.add(this);
		f.addKeyListener(this);

		ball.setVelocityX(4);
		ball.setVelocityY(4);

		t = new Timer(16, this);
		t.start();
		f.setVisible(true);

	}
}
