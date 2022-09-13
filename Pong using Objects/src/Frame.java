import java.awt.Color;
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
	Paddle paddleLeft = new Paddle(1, 100);
	Paddle paddleRight = new Paddle(850, 100);
	
	/* paint is getting called roughly 60x per second */
	public void paint(Graphics g) {
		super.paintComponent(g);
		ball.paint(g);
		

		if (ball.getX() >= 800) {
			// reverse the velocity
			// int curr = ball.getVx();//get curr velocity
			ball.setVelocityX(-10); // reverse it with neg sign
		}
		if (ball.getX() <= 100) {
			ball.setVelocityX(10);
		}
		// To do: detect the left side of the screen
		// at what x position would that be?
		// make the ball reverse just like the
		// right side that we showed u

		if (ball.getY() >= 600) {
			ball.setVelocityY(-10);
		}
		if (ball.getY() <= 100) {
			ball.setVelocityY(10);
		}
		paddleLeft.paint(g);
		paddleRight.paint(g);
	}

	public static void main(String[] arg) {
		Frame f = new Frame();

	}

	@Override
	public void keyPressed(KeyEvent key) {
// information about the key getting pressed
		//lives in the KeyEvent object called arg0
		System.out.println(key.getKeyCode());
		//87 is the key
		//83 is the s key
		if (key.getKeyCode()==87) {
			//update the y position of
			//left paddle so it goes up the screen
			paddleLeft.setY(paddleLeft.getY()+10);
		}
		if (key.getKeyCode()==83) {
			paddleLeft.setY(paddleLeft.getY()-10);
		}
		if(key.getKeyCode()==38) {
			paddleRight.setY(paddleRight.getY()+10);
		}
		if(key.getKeyCode()==40) {
			paddleRight.setY(paddleRight.getY()-10);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {

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
		f.setSize(800, 600);
		f.add(this);
		f.addKeyListener(this);

		ball.setVelocityX(3);
		ball.setVelocityY(6);

		t = new Timer(16, this);
		t.start();
		f.setVisible(true);

	}
}
