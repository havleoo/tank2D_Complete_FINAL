import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class Gameplay extends JPanel implements ActionListener {

	private brick br;

	private ImageIcon player1;
	private int player1X = 200;
	private int player1Y = 550;
	private boolean player1right = false, p1right = false;
	private boolean player1left = false, p1left = false;
	private boolean player1down = false, p1down = false;
	private boolean player1up = true, p1up = false;
	private int player1score = 0;
	private int player1lives = 5;
	private boolean player1Shoot = false;
	private String bulletShootDir1 = "";

	private ImageIcon player2;
	private int player2X = 400;
	private int player2Y = 550;
	private boolean player2right = false, p2right = false;
	private boolean player2left = false, p2left = false;
	private boolean player2down = false, p2down = false;
	private boolean player2up = true, p2up = false;
	private int player2score = 0;
	private int player2lives = 5;
	private boolean player2Shoot = false;
	private String bulletShootDir2 = "";

	private ImageIcon enemy;
	private int enemyX = 400;
	private int enemyY = 550;
	private boolean enemyright = false, eneright = false;
	private boolean enemyleft = false, eneleft = false;
	private boolean enemydown = false, enedown = false;
	private boolean enemyup = true, eneup = false;
	private int enemyscore = 0;
	private int enemylives = 5;
	private boolean enemyShoot = false;
	public static int botspeed = 0;

	private Timer timer;
	private int delay = 10;

	private Player1Listener player1Listener;
	private Player2Listener player2Listener;
	private ClientListener clientListener;

	private EnemyBullet EnemyBullet = null;

	private Player1Bullet player1Bullet = null;
	private Player2Bullet player2Bullet = null;

	private boolean play = true;

	public int speed = 5;

	public static enum STATE {
		MENU, GAME, RESTART, PAUSE, MAINMENU, CONTROL, PVP, PVE, RESUME, PVEMODE
	};

	public static STATE State = STATE.MENU;
	public static STATE Resume = STATE.RESUME;

	private Menu menu = new Menu();
	private Pause pause = new Pause();
	private Control control = new Control();
	private GameMode gamemode = new GameMode();
	private PVEMode pvemode = new PVEMode();

	private static boolean checkStartSound = true;
	private static boolean checkGameOver = true;

	public Gameplay() {
		br = new brick();
		player1Listener = new Player1Listener();
		player2Listener = new Player2Listener();
		clientListener = new ClientListener();
		setFocusable(true);
		// addKeyListener(this);
		addKeyListener(clientListener);
		addKeyListener(player1Listener);
		addKeyListener(player2Listener);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		addMouseListener(new MenuMouse());
	}

	public void paint(Graphics g) {
		if (State == STATE.GAME) {

			gamemode.render(g);

		}

		if (State == STATE.PVP) {
			// play background
			g.setColor(Color.black);
			g.fillRect(0, 0, 650, 600);

			// right side background
			g.setColor(Color.DARK_GRAY);
			g.fillRect(650, 0, 140, 600);

			// draw solid bricks
			br.drawSolids(this, g);

			// draw Breakable bricks
			br.draw(this, g);

			if (play) {
				checkGameOver = true;
				if (checkStartSound) {
					Sound.Started();
					checkStartSound = false;
				}
				if (State == STATE.PVP) {
					// draw player 1
					Resume = State;
					if (player1up)
						player1 = new ImageIcon("player1_tank_up.png");
					else if (player1down)
						player1 = new ImageIcon("player1_tank_down.png");
					else if (player1right)
						player1 = new ImageIcon("player1_tank_right.png");
					else if (player1left)
						player1 = new ImageIcon("player1_tank_left.png");

					player1.paintIcon(this, g, player1X, player1Y);

					// draw player 2
					if (player2up)
						player2 = new ImageIcon("player2_tank_up.png");
					else if (player2down)
						player2 = new ImageIcon("player2_tank_down.png");
					else if (player2right)
						player2 = new ImageIcon("player2_tank_right.png");
					else if (player2left)
						player2 = new ImageIcon("player2_tank_left.png");

					player2.paintIcon(this, g, player2X, player2Y);

					if (player1Bullet != null && player1Shoot) {
						if (bulletShootDir1.equals("")) {
							if (player1up) {
								bulletShootDir1 = "up";
							} else if (player1down) {
								bulletShootDir1 = "down";
							} else if (player1right) {
								bulletShootDir1 = "right";
							} else if (player1left) {
								bulletShootDir1 = "left";
							}
						} else {
							player1Bullet.move(bulletShootDir1);
							player1Bullet.draw(g);
						}

						if (new Rectangle(player1Bullet.getX(), player1Bullet.getY(), 10, 10)
								.intersects(new Rectangle(player2X, player2Y, 50, 50))) {
							Sound.BulletHitTank();
							player1score += 10;
							player2lives -= 1;
							player1Bullet = null;
							player1Shoot = false;
							bulletShootDir1 = "";
						}

						if (br.checkCollision(player1Bullet.getX(), player1Bullet.getY())
								|| br.checkSolidCollision(player1Bullet.getX(), player1Bullet.getY())) {
							player1Bullet = null;
							player1Shoot = false;
							bulletShootDir1 = "";
						}

						if (player1Bullet.getY() < 1
								|| player1Bullet.getY() > 580
								|| player1Bullet.getX() < 1
								|| player1Bullet.getX() > 630) {
							player1Bullet = null;
							player1Shoot = false;
							bulletShootDir1 = "";
						}
					}

					if (player2Bullet != null && player2Shoot) {
						if (bulletShootDir2.equals("")) {
							if (player2up) {
								bulletShootDir2 = "up";
							} else if (player2down) {
								bulletShootDir2 = "down";
							} else if (player2right) {
								bulletShootDir2 = "right";
							} else if (player2left) {
								bulletShootDir2 = "left";
							}
						} else {
							player2Bullet.move(bulletShootDir2);
							player2Bullet.draw(g);
						}

						if (new Rectangle(player2Bullet.getX(), player2Bullet.getY(), 10, 10)
								.intersects(new Rectangle(player1X, player1Y, 50, 50))) {
							Sound.BulletHitTank();
							player2score += 10;
							player1lives -= 1;
							player2Bullet = null;
							player2Shoot = false;
							bulletShootDir2 = "";
						}

						if (br.checkCollision(player2Bullet.getX(), player2Bullet.getY())
								|| br.checkSolidCollision(player2Bullet.getX(), player2Bullet.getY())) {
							player2Bullet = null;
							player2Shoot = false;
							bulletShootDir2 = "";
						}

						if (player2Bullet.getY() < 1
								|| player2Bullet.getY() > 580
								|| player2Bullet.getX() < 1
								|| player2Bullet.getX() > 630) {
							player2Bullet = null;
							player2Shoot = false;
							bulletShootDir2 = "";
						}
					}

					if (br.checkspeedup(player1X, player1Y)) {
						if (p1up)
							if (!(player1Y < speed))
								if (br.checkTankCollision(player1X, player1Y - 2*speed) == false)
									player1Y -= 2 * speed;
						if (p1left)
							if (!(player1X < speed))
								if (br.checkTankCollision(player1X - 2*speed, player1Y) == false)
									player1X -= 2 * speed;
						if (p1down)
							if (!(player1Y > 550 + speed))
								if (br.checkTankCollision(player1X, player1Y + 2*speed) == false)
									player1Y += 2 * speed;
						if (p1right)
							if (!(player1X > 600 + speed))
								if (br.checkTankCollision(player1X + 2*speed, player1Y) == false)
									player1X += 2 * speed;
					} else if (br.checkspeeddown(player1X, player1Y)) {

						if (p1up)
							if (!(player1Y < speed))
								if (br.checkTankCollision(player1X, player1Y - speed) == false)
									player1Y -= 0.5 * speed;
						if (p1left)
							if (!(player1X < speed))
								if (br.checkTankCollision(player1X - speed, player1Y) == false)
									player1X -= 0.5 * speed;
						if (p1down)
							if (!(player1Y > 550 + speed))
								if (br.checkTankCollision(player1X, player1Y + speed) == false)
									player1Y += 0.5 * speed;
						if (p1right)
							if (!(player1X > 600 + speed))
								if (br.checkTankCollision(player1X + speed, player1Y) == false)
									player1X += 0.5 * speed;
					} else {
						if (p1up)
							if (!(player1Y < speed))
								if (br.checkTankCollision(player1X, player1Y - speed) == false)
									player1Y -= speed;
						if (p1left)
							if (!(player1X < speed))
								if (br.checkTankCollision(player1X - speed, player1Y) == false)
									player1X -= speed;
						if (p1down)
							if (!(player1Y > 550 + speed))
								if (br.checkTankCollision(player1X, player1Y + speed) == false)
									player1Y += speed;
						if (p1right)
							if (!(player1X > 600 + speed))
								if (br.checkTankCollision(player1X + speed, player1Y) == false)
									player1X += speed;
					}

					if (br.checkspeedup(player2X, player2Y)) {
						if (p2up)
							if (!(player2Y < speed))
								if (br.checkTankCollision(player2X, player2Y - 2*speed) == false)
									player2Y -= 2 * speed;
						if (p2left)
							if (!(player2X < speed))
								if (br.checkTankCollision(player2X - 2*speed, player2Y) == false)
									player2X -= 2 * speed;
						if (p2down)
							if (!(player2Y > 550 + speed))
								if (br.checkTankCollision(player2X, player2Y + 2*speed) == false)
									player2Y += 2 * speed;
						if (p2right)
							if (!(player2X > 600 + speed))
								if (br.checkTankCollision(player2X + 2*speed, player2Y) == false)
									player2X += 2 * speed;
					} else if (br.checkspeeddown(player2X, player2Y)) {
						if (p2up)
							if (!(player2Y < speed))
								if (br.checkTankCollision(player2X, player2Y - speed) == false)
									player2Y -= 0.5 * speed;
						if (p2left)
							if (!(player2X < speed))
								if (br.checkTankCollision(player2X - speed, player2Y) == false)
									player2X -= 0.5 * speed;
						if (p2down)
							if (!(player2Y > 550 + speed))
								if (br.checkTankCollision(player2X, player2Y + speed) == false)
									player2Y += 0.5 * speed;
						if (p2right)
							if (!(player2X > 600 + speed))
								if (br.checkTankCollision(player2X + speed, player2Y) == false)
									player2X += 0.5 * speed;
					} else {
						if (p2up)
							if (!(player2Y < speed))
								if (br.checkTankCollision(player2X, player2Y - speed) == false)
									player2Y -= speed;
						if (p2left)
							if (!(player2X < speed))
								if (br.checkTankCollision(player2X - speed, player2Y) == false)
									player2X -= speed;
						if (p2down)
							if (!(player2Y > 550 + speed))
								if (br.checkTankCollision(player2X, player2Y + speed) == false)
									player2Y += speed;
						if (p2right)
							if (!(player2X > 600 + speed))
								if (br.checkTankCollision(player2X + speed, player2Y) == false)
									player2X += speed;
					}
				}

				// the scores
				g.setColor(Color.white);
				g.setFont(new Font("serif", Font.BOLD, 15));
				g.drawString("Scores", 700, 30);
				g.drawString("Player 1:  " + player1score, 670, 60);
				g.drawString("Player 2:  " + player2score, 670, 90);

				g.drawString("Lives", 700, 150);
				g.drawString("Player 1:  " + player1lives, 670, 180);
				g.drawString("Player 2:  " + player2lives, 670, 210);
			}
			if ((player1lives == 0 || player2lives == 0) && checkGameOver) {
				Sound.GameOver();
				checkGameOver = false;
			}

			if (player1lives == 0) {
				g.setColor(Color.black);
				g.fillRect(0, 0, 800, 630);

				g.setColor(Color.white);
				g.setFont(new Font("serif", Font.BOLD, 60));
				g.drawString("Game Over", 250, 200);
				g.drawString("Player 2 Won", 230, 280);
				play = false;
				g.setColor(Color.white);
				g.setFont(new Font("serif", Font.BOLD, 30));
				g.drawString("(Space to Restart)", 280, 330);

				checkStartSound = true;
			} else if (player2lives == 0) {
				g.setColor(Color.black);
				g.fillRect(0, 0, 800, 630);

				g.setColor(Color.white);
				g.setFont(new Font("serif", Font.BOLD, 60));
				g.drawString("Game Over", 250, 200);
				g.drawString("Player 1 Won", 230, 280);
				g.setColor(Color.white);
				g.setFont(new Font("serif", Font.BOLD, 30));
				g.drawString("(Space to Restart)", 280, 330);
				play = false;

				checkStartSound = true;
			}

			g.dispose();
		} else if (State == STATE.PVE) {
			// play background
			pvemode.render(g);
		}
		if (State == STATE.PVEMODE) {
			Resume = State;

			g.setColor(Color.black);
			g.fillRect(0, 0, 650, 600);

			// right side background
			g.setColor(Color.DARK_GRAY);
			g.fillRect(650, 0, 140, 600);

			// draw solid bricks
			br.drawSolids(this, g);

			// draw Breakable bricks
			br.draw(this, g);

			if (play) {
				checkGameOver = true;
				if (checkStartSound) {
					Sound.Started();
					checkStartSound = false;
				}
				if (State == STATE.PVEMODE) {
					// draw player 1
					if (player1up)
						player1 = new ImageIcon("player1_tank_up.png");
					else if (player1down)
						player1 = new ImageIcon("player1_tank_down.png");
					else if (player1right)
						player1 = new ImageIcon("player1_tank_right.png");
					else if (player1left)
						player1 = new ImageIcon("player1_tank_left.png");

					player1.paintIcon(this, g, player1X, player1Y);

					// draw player 2
					if (enemyup)
						enemy = new ImageIcon("enemy_tank_up.png");
					else if (enemydown)
						enemy = new ImageIcon("enemy_tank_down.png");
					else if (enemyright)
						enemy = new ImageIcon("enemy_tank_right.png");
					else if (enemyleft)
						enemy = new ImageIcon("enemy_tank_left.png");

					enemy.paintIcon(this, g, enemyX, enemyY);

					if (player1Bullet != null && player1Shoot) {
						if (bulletShootDir1.equals("")) {
							if (player1up) {
								bulletShootDir1 = "up";
							} else if (player1down) {
								bulletShootDir1 = "down";
							} else if (player1right) {
								bulletShootDir1 = "right";
							} else if (player1left) {
								bulletShootDir1 = "left";
							}
						} else {
							player1Bullet.move(bulletShootDir1);
							player1Bullet.draw(g);
						}

						if (new Rectangle(player1Bullet.getX(), player1Bullet.getY(), 10, 10)
								.intersects(new Rectangle(enemyX, enemyY, 50, 50))) {
							Sound.BulletHitTank();
							player1score += 10;
							enemylives -= 1;
							player1Bullet = null;
							player1Shoot = false;
							bulletShootDir1 = "";
						}

						if (br.checkCollision(player1Bullet.getX(), player1Bullet.getY())
								|| br.checkSolidCollision(player1Bullet.getX(), player1Bullet.getY())) {
							player1Bullet = null;
							player1Shoot = false;
							bulletShootDir1 = "";
						}

						if (player1Bullet.getY() < 1
								|| player1Bullet.getY() > 580
								|| player1Bullet.getX() < 1
								|| player1Bullet.getX() > 630) {
							player1Bullet = null;
							player1Shoot = false;
							bulletShootDir1 = "";
						}
					}
					
					// BOT run
					double rds = Math.random();
					if (rds < (double)botspeed*0.01) {
						if (!enemyShoot) {
							if (enemyup) {
								EnemyBullet = new EnemyBullet(enemyX + 20, enemyY);
							} else if (enemydown) {
								EnemyBullet = new EnemyBullet(enemyX + 20, enemyY + 40);
							} else if (enemyright) {
								EnemyBullet = new EnemyBullet(enemyX + 40, enemyY + 20);
							} else if (enemyleft) {
								EnemyBullet = new EnemyBullet(enemyX, enemyY + 20);
							}

							enemyShoot = true;
						}
					}

					double rd = Math.random();
					if (0 < rd && rd < 0 + (double)botspeed*0.005) {
						enemyright = false;
						enemyleft = false;
						enemydown = false;
						enemyup = true;
						setfalse2();
						eneup = true;

					}
					if (0.25 < rd && rd < 0.25 + (double)botspeed*0.005) {
						enemyright = false;
						enemyleft = true;
						enemydown = false;
						enemyup = false;
						setfalse2();
						eneleft = true;

					}
					if (0.5 < rd && rd < 0.5 + (double)botspeed*0.005) {
						enemyright = false;
						enemyleft = false;
						enemydown = true;
						enemyup = false;
						setfalse2();
						enedown = true;

					}
					if (1 - (double)botspeed*0.005 < rd && rd < 1) {
						enemyright = true;
						enemyleft = false;
						enemydown = false;
						enemyup = false;
						setfalse2();
						eneright = true;

					}
					if (EnemyBullet != null && enemyShoot) {
						if (bulletShootDir2.equals("")) {
							if (enemyup) {
								bulletShootDir2 = "up";
							} else if (enemydown) {
								bulletShootDir2 = "down";
							} else if (enemyright) {
								bulletShootDir2 = "right";
							} else if (enemyleft) {
								bulletShootDir2 = "left";
							}
						} else {
							EnemyBullet.move(bulletShootDir2);
							EnemyBullet.draw(g);
						}

						if (new Rectangle(EnemyBullet.getX(), EnemyBullet.getY(), 10, 10)
								.intersects(new Rectangle(player1X, player1Y, 50, 50))) {
							Sound.BulletHitTank();
							enemyscore += 10;
							player1lives -= 1;
							EnemyBullet = null;
							enemyShoot = false;
							bulletShootDir2 = "";
						}

						if (br.checkCollision(EnemyBullet.getX(), EnemyBullet.getY())
								|| br.checkSolidCollision(EnemyBullet.getX(), EnemyBullet.getY())) {
							EnemyBullet = null;
							enemyShoot = false;
							bulletShootDir2 = "";
						}

						if (EnemyBullet.getY() < 1
								|| EnemyBullet.getY() > 580
								|| EnemyBullet.getX() < 1
								|| EnemyBullet.getX() > 630) {
							EnemyBullet = null;
							enemyShoot = false;
							bulletShootDir2 = "";
						}
					}

					if (br.checkspeedup(player1X, player1Y)) {
						if (p1up)
							if (!(player1Y < speed))
								if (br.checkTankCollision(player1X, player1Y - 2*speed) == false)
									player1Y -= 2 * speed;
						if (p1left)
							if (!(player1X < speed))
								if (br.checkTankCollision(player1X - 2*speed, player1Y) == false)
									player1X -= 2 * speed;
						if (p1down)
							if (!(player1Y > 550 + speed))
								if (br.checkTankCollision(player1X, player1Y + 2*speed) == false)
									player1Y += 2 * speed;
						if (p1right)
							if (!(player1X > 600 + speed))
								if (br.checkTankCollision(player1X + 2*speed, player1Y) == false)
									player1X += 2 * speed;
					} else if (br.checkspeeddown(player1X, player1Y)) {

						if (p1up)
							if (!(player1Y < speed))
								if (br.checkTankCollision(player1X, player1Y - speed) == false)
									player1Y -= 0.5 * speed;
						if (p1left)
							if (!(player1X < speed))
								if (br.checkTankCollision(player1X - speed, player1Y) == false)
									player1X -= 0.5 * speed;
						if (p1down)
							if (!(player1Y > 550 + speed))
								if (br.checkTankCollision(player1X, player1Y + speed) == false)
									player1Y += 0.5 * speed;
						if (p1right)
							if (!(player1X > 600 + speed))
								if (br.checkTankCollision(player1X + speed, player1Y) == false)
									player1X += 0.5 * speed;
					} else {
						if (p1up)
							if (!(player1Y < speed))
								if (br.checkTankCollision(player1X, player1Y - speed) == false)
									player1Y -= speed;
						if (p1left)
							if (!(player1X < speed))
								if (br.checkTankCollision(player1X - speed, player1Y) == false)
									player1X -= speed;
						if (p1down)
							if (!(player1Y > 550 + speed))
								if (br.checkTankCollision(player1X, player1Y + speed) == false)
									player1Y += speed;
						if (p1right)
							if (!(player1X > 600 + speed))
								if (br.checkTankCollision(player1X + speed, player1Y) == false)
									player1X += speed;
					}

					if (eneup)
						if (!(enemyY < speed))
							if (br.checkTankCollision(enemyX, enemyY - botspeed) == false)
								enemyY -= botspeed;
					if (eneleft)
						if (!(enemyX < speed))
							if (br.checkTankCollision(enemyX - botspeed, enemyY) == false)
								enemyX -= botspeed;
					if (enedown)
						if (!(enemyY > 550 + speed))
							if (br.checkTankCollision(enemyX, enemyY + botspeed) == false)
								enemyY += botspeed;
					if (eneright)
						if (!(enemyX > 600 + speed))
							if (br.checkTankCollision(enemyX + botspeed, enemyY) == false)
								enemyX += botspeed;
				}
			}

			// the scores
			g.setColor(Color.white);
			g.setFont(new Font("serif", Font.BOLD, 15));
			g.drawString("Scores", 700, 30);
			g.drawString("Player :  " + player1score, 670, 60);
			g.drawString("BOT :  " + enemyscore, 670, 90);

			g.drawString("Lives", 700, 150);
			g.drawString("Player :  " + player1lives, 670, 180);
			g.drawString("BOT :  " + enemylives, 670, 210);

			if ((player1lives == 0 || enemylives == 0) && checkGameOver) {
				Sound.GameOver();
				checkGameOver = false;
			}

			if (player1lives <= 0) {
				g.setColor(Color.black);
				g.fillRect(0, 0, 800, 630);

				g.setColor(Color.white);
				g.setFont(new Font("serif", Font.BOLD, 60));
				g.drawString("Game Over", 250, 200);
				g.drawString("BOT Won", 260, 280);
				play = false;
				g.setColor(Color.white);
				g.setFont(new Font("serif", Font.BOLD, 30));
				g.drawString("(Space to Restart)", 280, 330);

				checkStartSound = true;
			} else if (enemylives <= 0) {
				g.setColor(Color.black);
				g.fillRect(0, 0, 800, 630);

				g.setColor(Color.white);
				g.setFont(new Font("serif", Font.BOLD, 60));
				g.drawString("Game Over", 250, 200);
				g.drawString("Player Won", 250, 280);
				g.setColor(Color.white);
				g.setFont(new Font("serif", Font.BOLD, 30));
				g.drawString("(Space to Restart)", 280, 330);
				play = false;

				checkStartSound = true;
			}

			g.dispose();
		} else if (State == STATE.MENU) {
			menu.render(g);
		} else if (State == STATE.PAUSE) {
			pause.render(g);
		} else if (State == STATE.RESTART) {
			br = new brick();
			player1X = 200;
			player1Y = 550;
			player1right = false;
			player1left = false;
			player1down = false;
			player1up = true;

			player2X = 400;
			player2Y = 550;
			player2right = false;
			player2left = false;
			player2down = false;
			player2up = true;

			enemyX = 400;
			enemyY = 550;
			enemyright = false;
			enemyleft = false;
			enemydown = false;
			enemyup = true;

			player1score = 0;
			player1lives = 5;

			player2score = 0;
			player2lives = 5;

			enemyscore = 0;
			enemylives = 5;

			play = true;

			repaint();
			State = Resume;
		} else if (State == STATE.MAINMENU) {
			State = STATE.MENU;
			br = new brick();
			player1X = 200;
			player1Y = 550;
			player1right = false;
			player1left = false;
			player1down = false;
			player1up = true;

			player2X = 400;
			player2Y = 550;
			player2right = false;
			player2left = false;
			player2down = false;
			player2up = true;

			enemyX = 400;
			enemyY = 550;
			enemyright = false;
			enemyleft = false;
			enemydown = false;
			enemyup = true;

			player1score = 0;
			player1lives = 5;

			player2score = 0;
			player2lives = 5;

			enemyscore = 0;
			enemylives = 5;

			play = true;

			repaint();
		} else if (State == STATE.CONTROL) {
			control.render(g);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();

		repaint();
	}

	private void setfalse2() {
		eneup = false;
		enedown = false;
		eneleft = false;
		eneright = false;
	}

	private class ClientListener implements KeyListener {
		public void keyTyped(KeyEvent e) {
		}

		public void keyReleased(KeyEvent e) {
		}

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				State = STATE.PAUSE;
			}
			if (e.getKeyCode() == KeyEvent.VK_SPACE && (player1lives == 0 || player2lives == 0 || enemylives == 0)) {
				br = new brick();
				player1X = 200;
				player1Y = 550;
				player1right = false;
				player1left = false;
				player1down = false;
				player1up = true;

				player2X = 400;
				player2Y = 550;
				player2right = false;
				player2left = false;
				player2down = false;
				player2up = true;

				enemyX = 400;
				enemyY = 550;
				enemyright = false;
				enemyleft = false;
				enemydown = false;
				enemyup = true;

				player1score = 0;
				player1lives = 5;

				player2score = 0;
				player2lives = 5;

				enemyscore = 0;
				enemylives = 5;

				play = true;

				repaint();
				State = Resume;
			}
		}

	}

	private class Player1Listener implements KeyListener {
		public void keyTyped(KeyEvent e) {
		}

		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				p1up = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_A) {
				p1left = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				p1down = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				p1right = false;
			}
		}

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_U) {
				if (!player1Shoot) {
					if (player1up) {
						player1Bullet = new Player1Bullet(player1X + 20, player1Y);
					} else if (player1down) {
						player1Bullet = new Player1Bullet(player1X + 20, player1Y + 40);
					} else if (player1right) {
						player1Bullet = new Player1Bullet(player1X + 40, player1Y + 20);
					} else if (player1left) {
						player1Bullet = new Player1Bullet(player1X, player1Y + 20);
					}

					player1Shoot = true;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_W) {
				player1right = false;
				player1left = false;
				player1down = false;
				player1up = true;
				p1right = false;
				p1left = false;
				p1down = false;
				p1up = true;

			}
			if (e.getKeyCode() == KeyEvent.VK_A) {
				player1right = false;
				player1left = true;
				player1down = false;
				player1up = false;
				p1right = false;
				p1left = true;
				p1down = false;
				p1up = false;

			}
			if (e.getKeyCode() == KeyEvent.VK_S) {
				player1right = false;
				player1left = false;
				player1down = true;
				player1up = false;
				p1right = false;
				p1left = false;
				p1down = true;
				p1up = false;

			}
			if (e.getKeyCode() == KeyEvent.VK_D) {
				player1right = true;
				player1left = false;
				player1down = false;
				player1up = false;
				p1right = true;
				p1left = false;
				p1down = false;
				p1up = false;

			}

		}
	}

	private class Player2Listener implements KeyListener {
		public void keyTyped(KeyEvent e) {
		}

		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				p2up = false;

			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				p2left = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				p2down = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				p2right = false;
			}
		}

		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_M) {
				if (!player2Shoot) {
					if (player2up) {
						player2Bullet = new Player2Bullet(player2X + 20, player2Y);
					} else if (player2down) {
						player2Bullet = new Player2Bullet(player2X + 20, player2Y + 40);
					} else if (player2right) {
						player2Bullet = new Player2Bullet(player2X + 40, player2Y + 20);
					} else if (player2left) {
						player2Bullet = new Player2Bullet(player2X, player2Y + 20);
					}

					player2Shoot = true;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				player2right = false;
				player2left = false;
				player2down = false;
				player2up = true;
				p2right = false;
				p2left = false;
				p2down = false;
				p2up = true;

			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				player2right = false;
				player2left = true;
				player2down = false;
				player2up = false;
				p2right = false;
				p2left = true;
				p2down = false;
				p2up = false;

			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				player2right = false;
				player2left = false;
				player2down = true;
				player2up = false;
				p2right = false;
				p2left = false;
				p2down = true;
				p2up = false;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				player2right = true;
				player2left = false;
				player2down = false;
				player2up = false;
				p2right = true;
				p2left = false;
				p2down = false;
				p2up = false;
			}

		}
	}
}
