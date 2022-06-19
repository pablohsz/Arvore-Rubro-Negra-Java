class Cell {

    private int key;
    private Cell father;
    private Cell right;
    private Cell left;
    private boolean color;
    //A cor foi definida como boolean, sendo, respectivamente:
    //          false: RED        true: BLACK

    public boolean isColor() {return color; }

    public void setColor(boolean color) { this.color = color; }

    public Cell(int chave) {
        key = chave;
    }

    public Cell(boolean cor) {
        color = cor;
    }


    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Cell getFather() {
        return father;
    }

    public void setFather(Cell father) {
        this.father = father;
    }

    public Cell getRight() {
        return right;
    }

    public void setRight(Cell right) {
        this.right = right;
    }

    public Cell getLeft() {
        return left;
    }

    public void setLeft(Cell left) {
        this.left = left;
    }

    public String printColor(){
        if (this.isColor()){
            return "BLACK";
        } else {
            return "RED";
        }
    }
}