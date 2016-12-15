package ogait.com.br.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Main {

	private JButton btnIniciar;
	private JButton btnParar = new JButton("Parar");
	private int minutosDoCronometro;
	private TimeCounter counterComponent; 
	
	
	public Main(){
		this.btnIniciar = new JButton("Resetar");
		this.btnParar = new JButton("Parar");
		this.minutosDoCronometro = 25; //valor default do pomodoro
		counterComponent = new TimeCounter(minutosDoCronometro);
		
		this.addActionListeners();
	}
	
	public void addActionListeners(){
		btnIniciar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				counterComponent.reset();				
			}
		});
		
		btnParar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(counterComponent.isPaused()){
					counterComponent.play();
					btnParar.setText("Parar");
				}else{
					counterComponent.pause();
					btnParar.setText("Reiniciar");
				}
			}
		});
	}
	
	public void display(){
		
		JFrame frame = new JFrame();
		frame.setLayout(null);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setSize(new Dimension(300, 200));
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	
		
		
		TimeCounter counterComponent = new TimeCounter(25);
		
		
		counterComponent.setBounds(0, 0, 150, 150);
		btnIniciar.setBounds(175, 35, 100, 30);
		btnIniciar.setBackground(Color.WHITE);
		btnParar.setBounds(175, 85, 100, 30);
		btnParar.setBackground(Color.WHITE);
		
		frame.add(counterComponent);
		frame.add(btnIniciar);
		frame.add(btnParar);
		
		counterComponent.run();
	}
	
	
	public static void main(String args[]){
		Main telaPincipal = new Main();
		telaPincipal.display();
		
	}
	
}
