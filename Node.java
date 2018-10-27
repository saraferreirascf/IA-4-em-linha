import java.util.*;
import java.util.ArrayList;

public class Node {

  public static int numNos =0;
  private Tabuleiro tab;
  private ArrayList<Node> filhos;
  int nextPlayer;
  int move;
  int score;
  int alpha;
  int beta;
  public Node (Tabuleiro tab, int nextPlayer, int move){
    this.tab=new Tabuleiro(6,7);
    this.tab.copy(tab);
    this.nextPlayer=nextPlayer;
    this.move=move;
    this.score = 0;
    this.alpha=Integer.MIN_VALUE;
    this.beta=Integer.MAX_VALUE;
    filhos = new ArrayList<Node>();

  }
  public Node (Tabuleiro tab, int nextPlayer){
    this.tab=new Tabuleiro(6,7);
    this.tab.copy(tab);
    this.nextPlayer=nextPlayer;
    this.move=0;
    this.score = 0;
    this.alpha=Integer.MIN_VALUE;
    this.beta=Integer.MAX_VALUE;
    filhos = new ArrayList<Node>();

  }

  public void fazfilhos(){
    Tabuleiro tmp = new Tabuleiro(6,7);
    tmp.copy(tab);
    for(int i = 0; i < tmp.getCols();i++){
      if(tmp.Movevalido(i)){
        tmp.copy(tab);
        tmp.dropPeca(i,nextPlayer);

        if(nextPlayer==0) {
          Node filho= new Node(tmp, 1,i);
          filhos.add(filho);
        }
        else{
          Node filho = new Node(tmp,0,i);
          filhos.add(filho);
        }
      }
    }
    numNos+=filhos.size();

  }

  public void copyNode(Node x){
    this.tab.copy(x.tab);
    this.filhos=x.getfilhos(); //faz filhos adiciona a filhos (ArrayList)
    this.nextPlayer=x.nextPlayer;
    this.move=x.move;
    this.score=x.score;
    this.alpha=x.alpha;
    this.beta=x.beta;


  }


  public Tabuleiro getBoardState() {
    return tab;
  }

  public ArrayList<Node> getfilhos() {
    return filhos;
  }





}
