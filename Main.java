import java.util.*;

public class Main {
  static int currentPlayer=0;
  public static void main(String[] args) {
    Scanner stdin=new Scanner (System.in);
    int op=0;

    System.out.println("--------------");
    System.out.println("| 4 em linha |");
    System.out.println("--------------");
    System.out.println();
    Tabuleiro jogo= new Tabuleiro(6,7); //a matriz é sempre do mesmo tamanho

    System.out.println("Quer jogar contra um amigo ou contra o pc?");
    System.out.println("|0|- Contra amigo");
    System.out.println("|1|- Contra o PC");
    int versus=stdin.nextInt();
    System.out.println();
    while(versus!=0 && versus!=1) {
      	System.out.println("Insira o numero 0 ou 1");
      	versus=stdin.nextInt();
      }

    if(versus==1) {//se vai jogar contra o pc
      System.out.println("Quer jogar em primeiro lugar?");
      System.out.println("|0|- Sim");
      System.out.println("|1|- Não");
      int op2=stdin.nextInt();
      while(op2!=0 && op2!=1) {
      	System.out.println("Insira o numero 0 ou 1");
      	op2=stdin.nextInt();
      }
      System.out.println();
      int op3=0;
      if(op2 == 0){
        System.out.println("Escolha a coluna onde pretende fazer a sua jogada:");
        op3=stdin.nextInt();
        jogo.dropPeca(op3, currentPlayer);
        System.out.println();
        System.out.println("---------Sua jogada---------");
        System.out.println();
        System.out.println(jogo);
        proxjogador();}
        else {
          proxjogador();
        }
      }
      if(versus==1) {
        System.out.println("Escolha o algoritmo a ser usado no seu jogo");
        System.out.println("|1|- MinMax");
        System.out.println("|2|- AlfaBeta");
        op=stdin.nextInt(); //primeiro joador;
        while(op!=1 && op!=2) {
        	System.out.println("Insira o numero 1 ou 2");
        	op=stdin.nextInt();
        }

      }
      Minimax PC= new Minimax();
      long start = System.currentTimeMillis();
      int op3=0;

      while(jogo.Acabou()==0){
        if(versus==1){
          if(currentPlayer==0) {//vez do jogador
            System.out.println("Escolha a coluna onde pretende fazer a sua jogada");
            op3=stdin.nextInt();
            jogo.dropPeca(op3, currentPlayer);
            System.out.println();
            System.out.println("---------Sua jogada---------");
            System.out.println();
            System.out.println(jogo);
            proxjogador();
          }
          else { // pc
            Node inicial=new Node(jogo,currentPlayer);
            Node move=null;
            if(op==1) {

              move=PC.minimax(inicial,0, currentPlayer);
            }
            else {
            
              move=PC.alphaBeta(inicial, 0 ,currentPlayer, inicial.alpha, inicial.beta); //inicial.alpha?
            }

            jogo.dropPeca(move.move, currentPlayer); //move.move?
            System.out.println();
            System.out.println("---------Jogada do Adversário---------");
            System.out.println();
            System.out.println(jogo);
            long tempoFinal= (long)(System.currentTimeMillis());
            System.out.printf("Tempo decorrido: %.3f s%n", (tempoFinal - start) / 1000d);
            System.out.println("Nós gerados na jogada: " + move.numNos);
            move.numNos=0; //para fazer reset a cada jogada
            proxjogador();
          }
        }
        else {
          System.out.println("Escolha a coluna onde pretende fazer a sua jogada");
          op3=stdin.nextInt();
          jogo.dropPeca(op3, currentPlayer);
          System.out.println();
          System.out.println("---------Sua jogada---------");
          System.out.println();
          System.out.println(jogo);
          proxjogador();

        }
        int finito=jogo.Acabou();
        if(finito==1) {
          System.out.println("O jogador 'X' ganhou!");
          break;
        }
        else if(finito== -1) {
          System.out.println("O jogador 'O' ganhou!");
          break;
        }
         else if(finito==-1)//ver se esta certo
        System.out.println("Empate!");
      }
    }



    public static void proxjogador() {
      if(currentPlayer==0)
      currentPlayer=1;
      else
      currentPlayer=0;

    }
  }
