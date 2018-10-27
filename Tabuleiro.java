import java.util.*;

public class Tabuleiro {

  //tamanho de todos os tabuleiros
  private int linhas,colunas;
  public char tab[][]; //Estado do jogo



  public Tabuleiro(int linhas, int colunas) { //construtor padrao com tab vazio
    this.linhas=linhas;
    this.colunas=colunas;
    tab= new char[linhas][colunas];


    for(int i=0;i<linhas;i++) {
      for(int j=0; j<colunas;j++) {
        tab[i][j]='-';
      }
    }
  }
  //o x é o 0, o O é o 1
  public void dropPeca(int coluna, int jogador){
    if((coluna<0 || coluna > linhas) || (tab[0][coluna]!='-')) {
      System.out.println("Coluna inválida");
      System.out.println("Devias estar atento.. O adversário substitui-te!!");
      System.out.println();
    }
    else {
      for(int i=linhas-1;i>=0;i--) {
        if(tab[i][coluna]=='-') {
          if(jogador==0) {
            tab[i][coluna]='X';

          }
          else {
            tab[i][coluna]='O';
          }
          break;

        }
      }
    }
  }

  public String toString(){ //imprime o tabuleiro
    String aux = "0 1 2 3 4 5 6\n\n";
    for(int i=0;i<linhas;i++) {
      for(int j=0; j< colunas;j++) {
        aux=aux + tab[i][j]+" ";
      }
      aux=aux+'\n';
    }
    return aux;

  }

  public int Acabou() {
    int soma=0;
    //Vai as linhas
    for(int i=0;i<linhas;i++){
      for(int j=0; j<colunas;j++) {
        if(tab[i][j]=='X') {
          soma++;
        }
        else {
          soma=0;
        }
        if(soma==4) {
          return 1;
        }
      }
      soma=0;
    }
    //vais as colunas agora
    for(int i=0; i<linhas;i++) {
      for(int j=0; j<colunas;j++) {
        if(tab[i][j]=='O')
        soma++;
        else {
          soma=0;
        }
        if(soma==4){
          return -1;
        }
      }
      soma=0;
    }
    for(int i=0;i<colunas;i++) {
      for(int j=0; j<linhas;j++) {
        if(tab[j][i]=='X')
        soma++;
        else {
          soma=0;
        }
        if(soma==4){
          return 1;
        }
      }
      soma=0;
    }
    for(int i=0;i<colunas;i++) {
      for(int j=0; j<linhas;j++) {
        if(tab[j][i]=='O')
        soma++;
        else {
          soma=0;
        }
        if(soma==4) {
          return -1;
        }
      }
      soma=0;
    }

    //diagonais "/////"
    for(int i=3;i<linhas;i++) {
      for(int j=0; j<colunas-3;j++){
        if(tab[i][j]=='X' && tab[i-1][j+1]=='X' && tab[i-2][j+2]=='X' && tab[i-3][j+3]=='X')
        return 1;
        else if(tab[i][j]=='O'&& tab[i-1][j+1]=='O' && tab[i-2][j+2]=='O' && tab[i-3][j+3]=='O')
        return -1;
      }
    }

    //diagonais "\\\"
    for(int i=3;i<linhas;i++) {
      for(int j=colunas-1;j>=colunas-4;j--){
        if(tab[i][j] =='X' && tab[i-1][j-1] == 'X' && tab[i-2][j-2]=='X' && tab[i-3][j-3]=='X')
        return 1;
        else if(tab[i][j]=='O' && tab[i-1][j-1]=='O' && tab[i-2][j-2]=='O' && tab[i-3][j-3]=='O')
        return -1;
      }
    }
    //Empate
    int somaX=0;
    for(int i=0; i<linhas;i++) {
      for(int j=0; j<colunas;j++) {
        if(tab[i][j]=='-')
        somaX++;
      }
    }
    if(somaX==0)
    return 2;

    return 0;

  }
  public boolean Movevalido(int coluna){
    if((coluna <0 || coluna > linhas) || (tab[0][coluna]!='-'))
    return false;

    return true;
  }

  public void setValor(int x, int y, int currentPlayer) {
    if(currentPlayer==0)
    tab[x][y]='X';
    else
    tab[x][y]='O';
  }

  public int avaliar(int player) {
    int sum=0;

    int a= avaliarlinhas(player);
    int b= avaliarcolunas(player);
    int c= avaliardiaesq(player);
    int d= avaliardiadir(player);

    sum=a+b+c+d;
    return sum; }

    private int avaliarlinhas(int currentPlayer){
      int somaX = 0;
      int somaO = 0;
      int soma = 0;
      int best = 0;
      for( int i = 0 ; i < linhas; i++){
        for(int j = 0 ; j < colunas-3; j++){
          if (tab[i][j] == 'X')
          somaX++;
          if(tab[i][j+1] == 'X')
          somaX++;
          if(tab[i][j+2] == 'X')
          somaX++;
          if(tab[i][j+3] == 'X')
          somaX++;

          if (tab[i][j] == 'O')
          somaO++;
          if(tab[i][j+1] == 'O')
          somaO++;
          if(tab[i][j+2] == 'O')
          somaO++;
          if(tab[i][j+3] == 'O')
          somaO++;

          soma = soma + avaliarval(somaX, somaO);
          somaX = 0;
          somaO = 0;
        }
      }
      int result;
      if(currentPlayer == 0) { //cases where its X
        result = soma + 16;
      }
      else{
        result = soma - 16;
      }
      //System.out.println(result);
      return result;

    }

    private int avaliarcolunas(int currentPlayer){

      int somaX = 0;
      int somaO = 0;
      int soma = 0;
      int best = 0;
      for( int i = 0 ; i < colunas-4; i++){ //lin-6
        for(int j = 0 ; j < linhas+1; j++){  //col-7
          if (tab[i][j] == 'X')
          somaX++;
          if(tab[i+1][j] == 'X')
          somaX++;
          if(tab[i+2][j] == 'X')
          somaX++;
          if(tab[i+3][j] == 'X')
          somaX++;

          if (tab[i][j] == 'O')
          somaO++;
          if(tab[i+1][j] == 'O')
          somaO++;
          if(tab[i+2][j] == 'O')
          somaO++;
          if(tab[i+3][j] == 'O')
          somaO++;

          soma = soma + avaliarval(somaX, somaO);
          somaX = 0;
          somaO = 0;
        }
      }
      int result;
      if(currentPlayer == 0) { //cases where its X
        result = soma + 16;
      }
      else{
        result = soma - 16;
      }
      //System.out.println(result);
      return result;
    }

    private int avaliardiadir(int currentPlayer){
      int somaX = 0;
      int somaO = 0;
      int soma= 0;
      int best = 0;
      for(int i=3;i<linhas;i++){
        for(int j=0;j<colunas-3;j++){
          if(tab[i][j] == 'X')
          somaX++;
          if( tab[i-1][j+1] == 'X')
          somaX++;
          if(tab[i-2][j+2] == 'X')
          somaX++;
          if(tab[i-3][j+3] == 'X')
          somaX++;

          if(tab[i][j] == 'O')
          somaO++;
          if(tab[i-1][j+1] == 'O')
          somaO++;
          if(tab[i-2][j+2] == 'O')
          somaO++;
          if(tab[i-3][j+3] == 'O')
          somaO++;

          soma = soma + avaliarval(somaX, somaO);
          somaX = 0;
          somaO = 0;
        }
      }
      int result;
      if(currentPlayer == 0) { //cases where its X
        result = soma + 16;
      }
      else{
        result = soma - 16;
      }
      //System.out.println(result);
      return result;
    }

    private int avaliardiaesq(int currentPlayer){
      int somaX = 0;
      int somaO = 0;
      int soma = 0;
      int best = 0;
      for(int i=3;i<linhas;i++){
        for(int j=colunas-1;j>=colunas-4;j--){
          if(tab[i][j] == 'X')
          somaX++;
          if(tab[i-1][j-1] == 'X' )
          somaX++;
          if(tab[i-2][j-2] == 'X')
          somaX++;
          if(tab[i-3][j-3] == 'X')
          somaX++;

          if(tab[i][j] == 'O' )
          somaO++;
          if(tab[i-1][j-1] == 'O')
          somaO++;
          if(tab[i-2][j-2] == 'O')
          somaO++;
          if(tab[i-3][j-3] == 'O')
          somaO++;

          soma = soma + avaliarval(somaX, somaO);
          somaX = 0;
          somaO = 0;
        }
      }
      int result;
      if(currentPlayer == 0) { //cases where its X
        result = soma + 16;
      }
      else{
        result = soma - 16;
      }
      //System.out.println(result);
      return result;
    }

    private int avaliarval(int x, int o){
      if(x == 1 && o == 0)
      return 1;
      if(x == 2 && o == 0)
      return 10;
      if(x == 3 && o == 0)
      return 50;
      if(x == 4 && o == 0)
      return 512;

      if(x == 0 && o == 1)
      return -1;
      if(x == 0 && o == 2)
      return -10;
      if(x == 0 && o == 3)
      return -50;
      if(x == 0 && o == 4)
      return -512;

      else
      return 0;

    }


    public void copy(Tabuleiro x){
      for(int i = 0;i<x.tab.length;i++){
        for(int j=0; j < x.tab[i].length;j++){
          this.tab[i][j]=x.tab[i][j];
        }
      }


    }
    public int getLinhas() {
      return linhas;

    }
    public int getCols() {
      return colunas;
    }
  }
