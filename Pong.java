import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Pong extends JPanel implements KeyListener{
  int ballX = 650; int ballY = 350; int ballSpeedX = 5; int ballSpeedY = 1; int balldiameter = 25; //ball variables
  int player1width = 15; int player2width = 15; int player1height = 75; int player2height = 75; int player1Y = 0; int player2Y = 0; int player1SpeedY = 10; int player2SpeedY = 10; int player1X = 15; int player2X = 1255;
  String msg = " ";
  Font f = new Font("Monospaaced", Font.PLAIN, 33);
  
  void moveBall() {
    ballX =ballX+ballSpeedX;
    ballY =ballY+ballSpeedY;
    if (ballX > 1258) {
      msg = "Player 1 Wins";
      repaint();
    }
    if (ballX < 0) {
      msg = "Player 2 Wins";
      repaint();
    }
    if (ballY > 638) {
      ballSpeedY = -5;
      repaint();
    }
    if (ballY < 0) {
      ballSpeedY = 5;
      repaint();
    }
  }
  
  void movePlayer1Up() {
    player1SpeedY = -10;
    player1Y =player1Y+player1SpeedY;
    if (player1Y < 0) {
      player1Y = 0;
    }
    repaint();
  }
  
  void movePlayer1Down() {
    player1SpeedY = 10;
    player1Y =player1Y+player1SpeedY;
    if (player1Y > 587) {
      player1Y = 587;
    }
    repaint();
  }
  
  void movePlayer2Up() {
    player2SpeedY = -10;
    player2Y =player2Y+player2SpeedY;
    if (player2Y < 0) {
      player2Y = 0;
    }
    repaint();
  }
  
  void moverPlayer2Down() {
    player2SpeedY = 10;
    player2Y =player2Y+player2SpeedY;
    if (player2Y > 587) {
      player2Y = 587;
    }
    repaint();
  }
  
  Pong() { 
	setBackground(Color.black);
    addKeyListener(this);
    setFocusable(true);
    setFocusTraversalKeysEnabled(false);
  }
  
  public void keyPressed(KeyEvent e)
  {
    int key = e.getKeyCode();
    if (key == e.VK_UP) {
      movePlayer2Up();
    }
    else if (key == e.VK_DOWN) {
      moverPlayer2Down();
    }
    else if (key == e.VK_W) {
      movePlayer1Up();
    }
    else if (key == e.VK_S) {
      movePlayer1Down();
    }
    else if (key == e.VK_ENTER)
    {
      if (msg == "Player 1 Wins") {
        ballSpeedX = -5;
        ballSpeedY = -5;
        repaint();
      }
      if (msg == "Player 2 Wins") {
        ballSpeedX = 5;
        ballSpeedY = 5;
        repaint();
      }
      msg = " ";
      ballX = 650;
      ballY = 350;
    }
  }
  


  public void keyReleased(KeyEvent e){}
  
  public void keyTyped(KeyEvent e){}
  

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    g.setColor(Color.white);
    g.fillRect(player1X, player1Y, player1width, player1height);
    g.fillRect(player2X, player2Y, player2width, player2height);
    g.setColor(Color.blue);
    for (int i = 0; i < 650; i += 70) {
      g.fillRect(650, i, 8, 50);
    }
    g.setColor(Color.red);
    g.fillOval(ballX, ballY, balldiameter, balldiameter);
    g.setColor(Color.white);
    g.setFont(f);
    g.drawString(msg, 550, 350);
  }
  
  public Rectangle player1() {
    return (new Rectangle(player1X, player1Y, player1width, player1height));
  }
  
  public Rectangle player2() { 
  return (new Rectangle(player2X, player2Y, player2width, player2height)); 
  }
  

  public Rectangle ball(){
	return (new Rectangle(ballX, ballY, balldiameter, balldiameter));
	}
  
  public void collision() {
    if (player1().intersects(ball())) {
      if (ballX <= 55) {
        if ((ballY >= player1Y) && (ballY <= player1Y + 75)) {
          ballSpeedX = 5;
        }
        repaint();
      }
      repaint();
    }
    
    if (player2().intersects(ball())) {
      if (ballX >= 1205) {
        if ((ballY >= player2Y) && (ballY <= player2Y + 75)) {
          ballSpeedX = -5;
        }
        repaint();
      }
      repaint();
    }
  }
  
  public static void main(String[] args)throws Exception{
    JFrame frame = new JFrame("PONG-2D");
    frame.setSize(1300, 700);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	frame.setResizable(false);
    Pong panel = new Pong();
    frame.getContentPane().add(panel);
    while(true){
      panel.moveBall();
      panel.collision();
      panel.repaint();
	  Thread.sleep(15);
    }
  }
}