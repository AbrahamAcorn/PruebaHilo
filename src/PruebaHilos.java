import javax.swing.*;
import java.awt.*;
import java.util.Random;


class Vector{
	String respuestas[] = new String[10000000];
	
	public String[] Encuesta() {
		Random aleatorio = new Random();
		
		int azar;// = aleatorio.nextInt(1000);
		for (int i = 0; i < respuestas.length; i++) {
			azar = aleatorio.nextInt();
			if(azar >=0.5 ) {
				respuestas[i]="si";
			}
			else {
				respuestas[i]="no";
			}
		}
		return respuestas;
	}
}



class Window extends JFrame implements Runnable{
	Vector cs=new Vector();
	JTextArea si,no;
	JProgressBar barraSi,barraNo;
	Window(){
		
		getContentPane().setLayout(new FlowLayout());;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Hilos Twitter");
		setSize(400, 300);
		setVisible(true);
		setLocationRelativeTo(null);
		
		add(new JLabel("Si"));
		si=new JTextArea(10,15);
		si.setBackground(new Color(125, 175, 125));
		si.setLineWrap(true);
		si.setWrapStyleWord(true);
		JScrollPane scrollSi = new JScrollPane(si);
		scrollSi.setVisible(true);
		scrollSi.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(scrollSi);
		
		add(new JLabel("No"));
		no=new JTextArea(10,15);
		no.setBackground(new Color(250, 126, 100));
		no.setLineWrap(true);
		no.setWrapStyleWord(true);
		JScrollPane scrollNo = new JScrollPane(no);
		scrollNo.setVisible(true);
		scrollNo.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(scrollNo);
		
		barraSi = new JProgressBar(0,10000000);
		barraSi.setForeground(new Color(125, 175, 125));
		add(barraSi);
		barraNo = new JProgressBar(0,10000000);
		barraNo.setForeground(new Color(250, 126, 100));
		add(barraNo);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String res[] = cs.Encuesta();
		for (int i = 0; i < res.length; i++) {
			if(res[i]=="si") {
				si.setText(si.getText()+","+res[i]);
			}
			else {
				no.setText(no.getText()+","+res[i]);
			}
		}
		
		//Thread hiloH = new Thread(new Histograma());
		//hiloH.start()
		int num=0;
		String s1[]=si.getText().split(",");
		String []s2=no.getText().split(",");
		
		barraNo.setValue(0);
		barraNo.setStringPainted(true);
		
		 while (num < s2.length) {
			  barraNo.setValue(num);
			  num += 5;
			  }
		 barraSi.setValue(0);
		 barraSi.setStringPainted(true);
			num=0;
		 while (num < s1.length) {
				  barraSi.setValue(num);
				  num += 5;
				  } 
	}
}

class Histograma implements Runnable{
	int valorSi,valorNo;
	Window win=new Window();
	@Override
	public void run() {
		int num=0;
		String s1=win.si.getText();
		String []s2=win.no.getText().split(",");
		
		win.barraNo.setValue(0);
		win.barraNo.setStringPainted(true);
		
		 while (num < s2.length) {
			  win.barraNo.setValue(num);
			  num += 1;
			  }
	}
	
}

public class PruebaHilos {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Thread hilo1 = new Thread(new Window());
				hilo1.start();
			}
		});
	}

}