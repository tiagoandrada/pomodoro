package ogait.com.br.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class TimeCounter extends JPanel implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*Grau inicia em 90*/
	private int grauDeRotacao = 0;
	private int minutosDoContador;
	private static int CONST_MINUTOS_DO_CONTADOR;
	
	private static boolean reset = false;
	private static boolean pause = false;
	
	public TimeCounter(int minutosDoContador){
		this.minutosDoContador = minutosDoContador;
		CONST_MINUTOS_DO_CONTADOR = this.minutosDoContador;
	}
	
	public void reset(){
		reset = true;
	}
	
	public void pause(){
		pause = true;
	}
	
	public void play(){
		pause = false;
	}
	
	public boolean isPaused(){
		return pause;
	}
	
	public void paint(Graphics g){
		super.paintComponent(g);
		this.setBackground(Color.BLACK);
		g.setColor(Color.RED);
		g.fillOval(25, 25, 100, 100);	
		g.drawOval(25, 25, 100, 100);
		g.setColor(Color.BLACK);
		g.fillArc(25, 25, 100, 100, 90, grauDeRotacao);
	}
	
	public void run(){
		
		int timer = 0;
		int minutosDoContadorEmMS = minutosDoContador * 60 * 1000;
		int tempoParaMudarGrau = (minutosDoContador*60) / 360;
		while(true){
			if(!pause){
				if(reset){
					this.grauDeRotacao = 0;
					this.minutosDoContador = CONST_MINUTOS_DO_CONTADOR;
					timer = 0;
					reset = false;
				}
				this.grauDeRotacao -= 1;
				repaint();
				try {
					Thread.sleep(tempoParaMudarGrau*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				timer += tempoParaMudarGrau; 
				if((timer*1000) > minutosDoContadorEmMS){
					JOptionPane.showMessageDialog(null, "Seu tempo pomodoro chegou ao fim!");
					int dialogButton =  JOptionPane.YES_NO_OPTION;
					int repetir = JOptionPane.showConfirmDialog(null, "Desejar Resetar o contador?", "Warning", dialogButton);
					if(repetir == JOptionPane.YES_OPTION){
						reset = true;
					}else{
						System.out.println("Finalizando aplicação ...");
						return;
					}
					
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
