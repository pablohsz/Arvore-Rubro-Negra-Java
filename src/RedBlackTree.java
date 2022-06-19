class RedBlackTree {
    private Cell root;
    private Cell nil;

    public RedBlackTree() {
        //Inicializa a árvore com todos os ponteiros apontados para o Nil.
        this.nil = new Cell(true);
        this.root = this.nil;
        this.root.setLeft(this.nil);
        this.root.setRight(this.nil);
        this.root.setFather(this.nil);
    }

    public Cell getNil() {
        return nil;
    }

    public void setNil(Cell nil) {
        this.nil = nil;
    }

    public Cell getRoot() {
        return root;
    }

    public void setRoot(Cell root) {
        this.root = root;
    }

    public void insert(int key) {
        //Metódo de inserção realizada através da chave 'key' que será atribuída à célula z.
        Cell z = new Cell(key);
        Cell y = this.nil;
        Cell x = this.root;
        while (x != this.nil) {
            //Laço de repetição para encontrar onde o nodo será inserido.
            y = x;
            if (z.getKey() < x.getKey()) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }
        }
        z.setFather(y);
        if (y == this.nil) {
            //Caso o pai de z seja Nil, o nodo será posicionado na raiz.
            this.root = z;
        } else {
            if (z.getKey() < y.getKey()) {
                y.setLeft(z);
            } else {
                y.setRight(z);
            }
        }
        z.setLeft(this.nil);
        z.setRight(this.nil);
        z.setColor(false);
        insertFixup(z);
    }

    public void insertFixup(Cell z) {
        //Método para correção caso a inserção se enquadre dentro de algum caso.
        Cell y;
        while (!z.getFather().isColor()) {
            if (z.getFather() == z.getFather().getFather().getLeft()) {
                y = z.getFather().getFather().getRight();
                if (!y.isColor()) {
                    z.getFather().setColor(true);               //caso1
                    y.setColor(true);                           //caso1
                    z.getFather().getFather().setColor(false);  //caso1
                    z = z.getFather().getFather();              //caso1
                } else {
                    if (z == z.getFather().getRight()) {
                        z = z.getFather();                      //caso2
                        leftRotate(z);                          //caso2
                    }
                    z.getFather().setColor(true);               //caso3
                    z.getFather().getFather().setColor(false);  //caso3
                    rightRotate(z.getFather().getFather());     //caso3
                }
            } else {
                y = z.getFather().getFather().getLeft();
                if (!y.isColor()) {
                    z.getFather().setColor(true);               //caso1
                    y.setColor(true);                           //caso1
                    z.getFather().getFather().setColor(false);  //caso1
                    z = z.getFather().getFather();              //caso1
                } else {
                    if (z == z.getFather().getLeft()) {
                        z = z.getFather();                      //caso2
                        rightRotate(z);                         //caso2
                    }
                    z.getFather().setColor(true);               //caso3

                    if (z.getFather().getFather() != this.nil) {
                        z.getFather().getFather().setColor(false);  //caso3
                        leftRotate(z.getFather().getFather());      //caso3
                    }
                }
            }
        }
        this.root.setColor(true);
    }

    public Cell remove(Cell z) {
        //Método de remoção.
        Cell y = z;
        Cell x;
        boolean yOriginalColor = y.isColor();
        if (z.getLeft() == this.nil) {
            x = z.getRight();
            transplant(z, z.getRight());
        } else {
            if (z.getRight() == this.nil) {
                x = z.getLeft();
                transplant(z, z.getLeft());
            } else {
                y = min(z.getRight());
                yOriginalColor = y.isColor();
                x = y.getRight();
                if (y.getFather() == z) {
                    x.setFather(y);
                } else {
                    transplant(y, y.getRight());
                    y.setRight(z.getRight());
                    y.getRight().setFather(y);
                }
            }
            transplant(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setFather(y);
            y.setColor(z.isColor());
        }
        if(yOriginalColor == true){
            removeFixup(x);
        }
        return z;
    }

    public void removeFixup(Cell x){
        //Método para correção caso a inserção se enquadre dentro de algum caso de remoção.
        Cell w;
        while (x != this.getRoot() && x.isColor() == true){
            if (x == x.getFather().getLeft()){
                w = x.getFather().getRight();
                if (w.isColor() == false) {
                    w.setColor(true);                                                   //caso1
                    x.getFather().setColor(false);                                      //caso1
                    leftRotate(x);                                                      //caso1
                    w = x.getFather().getRight();                                       //caso1
                }
                if(w.getLeft().isColor() == true && w.getRight().isColor() == true){
                    w.setColor(false);                                                  //caso2
                    x = x.getFather();                                                  //caso2
                } else {
                    if (w.getRight().isColor() == true) {                               //caso3
                        w.getLeft().setColor(true);                                     //caso3
                        w.setColor(false);                                              //caso3
                        rightRotate(x);                                                 //caso3
                        w = x.getFather().getRight();                                   //caso3
                    }
                    w.setColor(x.getFather().isColor());                                //caso4
                    x.getFather().setColor(true);                                       //caso4
                    w.getRight().setColor(true);                                        //caso4
                    leftRotate(x.getFather());                                          //caso4
                    x = this.root;                                                      //caso4
                }
            } else {
                w = x.getFather().getLeft();
                if (w.isColor() == false) {
                    w.setColor(true);                                                   //caso1
                    x.getFather().setColor(false);                                      //caso1
                    rightRotate(x);                                                     //caso1
                    w = x.getFather().getLeft();                                        //caso1
                }
                if(w.getRight().isColor() == true && w.getLeft().isColor() == true){
                    w.setColor(false);
                    x = x.getFather();
                } else {
                    if (w.getLeft().isColor() == true) {
                        w.getRight().setColor(true);
                        w.setColor(false);
                        leftRotate(x);
                        w = x.getFather().getLeft();
                    }
                    w.setColor(x.getFather().isColor());
                    x.getFather().setColor(true);
                    w.getLeft().setColor(true);
                    rightRotate(x.getFather());
                    x = this.root;
                }
            }
        }
        x.setColor(true);
    }

    public Cell min(Cell x) {
        //Método para encontrar o mínimo de uma célula.
        while (x.getLeft() != this.nil) {
            x = x.getLeft();
        }
        return x;
    }

    public void leftRotate(Cell x) {
        //Método de rotação para a esquerda.
        Cell y = x.getRight();
        x.setRight(y.getLeft());
        if (y.getLeft() != this.nil) {
            y.getLeft().setFather(x);
        }
        y.setFather(x.getFather());
        if (x.getFather() == this.nil) {
            this.root = y;
        } else {
            if (x == x.getFather().getLeft()) {
                x.getFather().setLeft(y);
            } else {
                x.getFather().setRight(y);
            }
        }
        y.setLeft(x);
        x.setFather(y);
    }

    public void rightRotate(Cell x) {
        //Método de rotação para a direita.
        Cell y = x.getLeft();
        x.setLeft(y.getRight());
        if (y.getLeft() != this.nil) {
            y.getRight().setFather(x);
        }
        y.setFather(x.getFather());
        if (x.getFather() == this.nil) {
            this.root = y;
        } else {
            if (x == x.getFather().getRight()) {
                x.getFather().setRight(y);
            } else {
                x.getFather().setLeft(y);
            }
        }
        y.setRight(x);
        x.setFather(y);
    }

    public void transplant(Cell u, Cell v) {
        //Método para transplantar uma célula.
        if (u.getFather() == this.nil) {
            this.root = v;
        } else {
            if (u == u.getFather().getLeft()) {
                u.getFather().setLeft(v);
            } else {
                (u.getFather()).setRight(v);
            }
        }
        v.setFather(u.getFather());
    }

    public void inOrder(Cell x) {
        //Método para impressão em percurso em ordem.
        if (x != this.nil) {
            this.inOrder(x.getLeft());
            System.out.print(x.getKey() + "|" + x.printColor());
            this.inOrder(x.getRight());
        }
    }

    public void preOrder(Cell x) {
        //Método para impressão em percurso pré-ordem.
        if (x != this.nil) {
            System.out.println(x.getKey() + "|" + x.printColor());
            this.preOrder(x.getLeft());
            this.preOrder(x.getRight());
        }
    }

    public void postOrder(Cell x) {
        //Método para impressão em percurso pós-ordem.
        if (x != this.nil) {
            this.postOrder(x.getLeft());
            this.postOrder(x.getRight());
            System.out.println(x.getKey() + "|" + x.printColor());
        }
    }

    public Cell search(Cell x, int k) {
        //Método para a busca de um inteiro, partindo da célula raiz.
        if (x == this.nil || k == x.getKey()) {
            return x;
        }
        if (k < x.getKey()) {
            return search(x.getLeft(), k);
        } else {
            return search(x.getRight(), k);
        }
    }

}