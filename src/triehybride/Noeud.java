package triehybride;

public class Noeud {

    protected char lettre;
    protected Noeud inf;
    protected Noeud eq;
    protected Noeud sup;
    protected int finMot;

    public Noeud(char lettre, Noeud inf, Noeud eq, Noeud sup, int finMot){

        this.lettre = lettre;
        this.inf = inf;
        this.eq = eq;
        this.sup = sup;
        this.finMot = finMot;
    }

    public Noeud(char lettre, Noeud inf, Noeud eq, Noeud sup){

        this.lettre = lettre;
        this.inf = inf;
        this.eq = eq;
        this.sup = sup;
        this.finMot = 0;
    }

    public Noeud(char lettre, Noeud eq){

        this.lettre = lettre;
        this.inf = null;
        this.eq = eq;
        this.sup = null;
        this.finMot = 0;
    }

    public Noeud GetEq(){
        return this.eq;
    }

    public Noeud GetSup(){
        return this.sup;
    }

    public void SetSup(Noeud A){
        this.sup = A;
    }

    public void SetEq(Noeud A){
        this.eq = A;
    }

    public void SetInf(Noeud A){
        this.inf = A;
    }

    public void SetVal(char lettre){
        this.lettre = lettre;
    }

    public void SetFinMot(int val){
        this.finMot = val;
    }

    public Noeud GetInf(){
        return this.inf;
    }

    public char Val(){
        return this.lettre;
    }
  
    public int GetFinMot(){
        return this.finMot;
    }


    @Override
    public String toString(){
        return lettre + "" + finMot+ " " + "inf:"+inf + "eq:"+eq + "sup:"+sup;
    }

}
