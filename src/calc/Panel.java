package calc;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javax.swing.*;

public class Panel extends JPanel {
    private JButton numbers[] = new JButton[10];
    private JTextField input = new JTextField(); 
    private JButton clean = new JButton("C");
    private JButton plus = new JButton("+"), div = new JButton("/"), minus = new JButton("-"), mul = new JButton("*");  
    private JButton equ = new JButton("=");
    private String[] oper = {"+", "-", "*", "/", "="};
    
    eHandler handler = new eHandler();
    
    public Panel(){
        setLayout(null);
        
        for (int x=0; x<3; x++){
            for (int y=0; y<3; y++){
                numbers[x*3+y+1] = new JButton((x*3+y+1) + "");
                numbers[x*3+y+1].setBounds(x*(50+10)+10, y*(50+10)+70, 50, 50);
                add(numbers[x*3+y+1]);
            }
        }
        numbers[0] = new JButton("0");
        numbers[0].setBounds(70, 250, 50, 50);
        add(numbers[0]);
        
        input.setBounds(10, 10, 227, 50);
        input.setEditable(false);
        add(input);
        
        clean.setBounds(10,250, 50, 50);
        add(clean);
        clean.addActionListener(handler);
        
        plus.setBounds(190, 70,50, 50);
        add(plus);
        plus.addActionListener(handler);
        
        minus.setBounds(190, 130,50, 50);
        add(minus);
        minus.addActionListener(handler);
        
        mul.setBounds(190, 190,50, 50);
        add(mul);
        mul.addActionListener(handler);
        
        div.setBounds(190, 250,50, 50);
        add(div);
        div.addActionListener(handler);
        
        equ.setBounds(130, 250 , 50 , 50);
        add(equ);
        
        ActionListener l = new ActionListener(){
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                String expr = input.getText().trim();
                String so1, so2, srez;
                double do1, do2, drez;
                int indo, flago = 0;
                JButton b = (JButton) e.getSource();
                if ((b.getText() == "+") || (b.getText() == "-") || (b.getText() == "*") || (b.getText() == "/")) {
                    for (String o : oper) {
                        if (expr.indexOf(o) != -1) {
                            // символ операции уже есть
                            Pattern pattern1 = Pattern.compile("^(\\-)?(\\d)+)");
                            Pattern pattern2 = Pattern.compile("^(\\-)?(\\d)+\\b[\\+\\-\\*\\/]$");
                            Matcher matcher1 = pattern1.matcher(expr);
                            Matcher matcher2 = pattern2.matcher(expr);
                            System.out.println("expr: " + expr);
                            System.out.println("pattern1: " + pattern1);
                            System.out.println("pattern2: " + pattern2);
                            if ((b.getText() == "-") && (matcher1.find() || matcher2.find())) {
                                input.setText(input.getText() + " " + b.getText());
                                flago = 1;
                                break;                                
                            }
                            else if (matcher1.find()) {
                                input.setText(input.getText() + " " + b.getText());
                                flago = 1;
                                break;
                            }
                            else    
                                return;   
                        }
                        else {
                            if (flago == 0) {
                               input.setText(input.getText() + b.getText());
                               flago = 1;
                            }
                            else
                               input.setText(input.getText() + " " + b.getText()); 
                            break;
                        }
                    }                   
                }
                else if ((b.getText() == "=")) {
                    for (String o : oper) {
                        if (expr.indexOf(o) != -1) {
                            srez = "";
                            indo = expr.indexOf(o);
                            so1 = expr.substring(0, indo).trim();
                            so2 = expr.substring(indo + 1).trim();
                            do1 = Double.parseDouble(so1);
                            do2 = Double.parseDouble(so2);
                            if (o == "+")
                                srez = "" + (do1 + do2);
                            else if (o == "-")
                                srez = "" + (do1 - do2);
                            else if (o == "*")
                                srez = "" + (do1 * do2);
                            else if (o == "/") {
                                if (do2 != 0)
                                    srez = "" + (do1 / do2);
                                else
                                    srez = "error: division on 0";
                            }
                            input.setText(srez);
                            break;
                        }
                    }                   
                }
                else {
                    if (flago == 1)
                       input.setText(input.getText() + " " + b.getText());
                    else
                       input.setText(input.getText() + b.getText());                        
                }
            }
            
        };
        for(JButton b : numbers){
            b.addActionListener(l);        
        }
        plus.addActionListener(l);
        minus.addActionListener(l);
        mul.addActionListener(l);
        div.addActionListener(l);
        equ.addActionListener(l);
        
    
    }
    
    public class eHandler implements ActionListener {
        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            if (e.getSource()==clean){
                input.setText(null);
                
            }
        }        
    }
    
}
