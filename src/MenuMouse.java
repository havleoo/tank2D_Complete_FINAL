import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuMouse implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if (Gameplay.State == Gameplay.STATE.MENU) {
			// Play button
			if (mx >= 350 && mx <= 500) {

				if (my >= 200 && my <= 250) {

					Gameplay.State = Gameplay.STATE.GAME;
				}
			}

			// Setting Button

			if (mx >= 350 && mx <= 500) {

				if (my >= 300 && my <= 350) {

					Gameplay.State = Gameplay.STATE.CONTROL;

				}
			}

			// Exit button
			if (mx >= 350 && mx <= 500) {

				if (my >= 400 && my <= 500) {

					System.exit(0);
				}
			}
		}

		else if (Gameplay.State == Gameplay.STATE.PAUSE) {
			// Resume Button
			if (mx >= 350 && mx <= 500) {

				if (my >= 100 && my <= 150) {

					Gameplay.State = Gameplay.Resume;
				}
			}
			// Restart button
			if (mx >= 350 && mx <= 500) {

				if (my >= 200 && my <= 250) {

					Gameplay.State = Gameplay.STATE.RESTART;
				}
			}

			// Main Menu Button
			if (mx >= 350 && mx <= 500) {

				if (my >= 300 && my <= 350) {

					Gameplay.State = Gameplay.STATE.MAINMENU;
				}
			}

			// Exit button
			if (mx >= 350 && mx <= 500) {

				if (my >= 400 && my <= 450) {

					System.exit(0);
				}
			}
		}

		else if (Gameplay.State == Gameplay.STATE.CONTROL) {
			// Control Button
			if (mx >= 0 && mx <= 65) {

				if (my >= 0 && my <= 32) {

					Gameplay.State = Gameplay.STATE.MENU;
				}
			}
		}

		else if (Gameplay.State == Gameplay.STATE.GAME) {
			// PVP Button
			if (mx >= 350 && mx <= 500) {

				if (my >= 250 && my <= 300) {

					Gameplay.State = Gameplay.STATE.PVP;
				}
			}

			// PVE Button

			if (mx >= 350 && mx <= 500) {

				if (my >= 400 && my <= 450) {

					Gameplay.State = Gameplay.STATE.PVE;

				}
			}
		} else if (Gameplay.State == Gameplay.STATE.PVE) {
			// Easy button
			if (mx >= 350 && mx <= 500) {

				if (my >= 200 && my <= 250) {

					Gameplay.botspeed = 2;

					Gameplay.State = Gameplay.STATE.PVEMODE;
				}
			}

			// Medium Button

			if (mx >= 350 && mx <= 500) {

				if (my >= 300 && my <= 350) {

					Gameplay.botspeed = 4;
				
					Gameplay.State = Gameplay.STATE.PVEMODE;

				}
			}

			// Hard button
			if (mx >= 350 && mx <= 500) {

				if (my >= 400 && my <= 500) {

					Gameplay.botspeed = 6;

					Gameplay.State = Gameplay.STATE.PVEMODE;

				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
