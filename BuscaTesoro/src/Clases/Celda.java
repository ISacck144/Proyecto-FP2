/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;
import Formulario.Fin;
import Formulario.Principal;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
public class Celda extends JButton {
    private int x;
    private int y;
    private int tipoCelda;
    private boolean visible;
    private Color color[];

    public boolean getVisible() {
        return visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    

    public Celda(int x, int y) {
        this.x = x;
        this.y = y;
        this.visible=false;
        this.color=new Color[]{Color.BLUE,Color.GREEN,Color.ORANGE,Color.YELLOW,Color.PINK,Color.BLACK};
        this.setMinimumSize(new Dimension(33, 9));
        this.setBackground(new java.awt.Color(0,0,204));
        this.setFont(new java.awt.Font("Tahoma",1, 12));
        this.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt){
                celdaActionPerformed(evt);
            }
        });
    }

    public void setTipoCelda(int tipoCelda) {
        this.tipoCelda = tipoCelda;
    }
    private void celdaActionPerformed(java.awt.event.ActionEvent evt){
        clic();
        int cont=0;
        for(int i=0; i<Principal.filas; i++){
            for(int j=0; j<Principal.columnas; j++){
                if(Principal.celda[i][j].getVisible()){
                    cont++;
                }
            }
        }
        if(!Principal.gano&&cont==(Principal.filas*Principal.columnas-Principal.tesoros)){
            Principal.gano=true;
            Fin fin=new Fin(Principal.jDesktopPane1);
            Principal.jDesktopPane1.add(fin);
            fin.show();
            fin.setVisible(true);
        }    
    }
    public void clic(){
        if(!visible&&Principal.gano==false){
            this.visible=true;
            this.setBackground(new java.awt.Color(240,240,240));
            switch(this.tipoCelda){
                case 0:
                    for(int i=0; i<Principal.filas; i++){
                        for(int j=0; j<Principal.columnas; j++){
                            if(Principal.celda[i][j].getTipoCelda()==0){
                                Principal.celda[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/tesoro5.png"))); 
                                Principal.celda[i][j].setBackground(new java.awt.Color(240,240,240));
                            }
                        }
                    }
                    Principal.gano=true;
                    break;
                case 1:
                    int cont=0;
                    this.setBackground(new java.awt.Color(240,240,240));
                    for(int i=-1; i<=1; i++){
                            if(x+i>=0&&x+i<Principal.filas){
                                for(int j=-1;j<=1;j++){
                                    if((y+j>=0&&(y+j)<Principal.columnas)&&Principal.celda[x+i][y+j].getTipoCelda()==0){
                                    cont++;    
                                } 
                            }
                        }
                    }
                    this.setText(""+cont);
                    this.setForeground(this.color[cont]);
                    break;
                default:
                    for(int i= -1; i<=1;i++){
                        if(x+i>=0&&x+i<Principal.filas){
                            for(int j=-1;j<=1;j++){
                                if((y+j>=0&&(y+j)<Principal.columnas)&&Principal.celda[x+i][y+j].getTipoCelda()!=0){
                                    if(!Principal.celda[x+i][y+j].getVisible()){
                                    Principal.celda[x+i][y+j].clic();
                                    }
                                }
                            }
                        }
                }
            }    
        }
    }
    public int getTipoCelda() {
        return tipoCelda;
    }  
}
