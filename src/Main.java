import java.io.IOException;
import java.util.Scanner;

class Main {

    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException {
        RedBlackTree a1;
        int op, continuar;
        Cell aux;
        a1 = new RedBlackTree();
        do {
            new ProcessBuilder("cmd", "/c", "pause").inheritIO().start().waitFor();
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            System.out.println("\n\n><><>< RED-BLACK TREE ><><><\n");
            System.out.println("< 1 > INSERIR");
            System.out.println("< 2 > REMOVER");
            System.out.println("< 3 > REALIZAR PERCURSOS");
            System.out.println("< 4 > SAIR");
            System.out.print("Opção: ");
            op = in.nextInt();

            switch (op) {
                case 1:
                    System.out.println("\n\n><><><  INSERIR NODO  ><><><\n");
                    continuar = 1;
                    while (continuar == 1){
                        System.out.print("\n<> Digite o número que deseja inserir na árvore:" );
                        a1.insert(in.nextInt());
                        System.out.print("Número adicionado!");
                        System.out.println("\n\n<> Deseja inserir mais algum número? 1 - SIM  2 - NÃO");
                        continuar = validacao();
                    }
                    break;
                case 2:
                    System.out.println("\n\n><><><  REMOVER NODO  ><><><\n");
                    continuar = 1;
                    while (continuar == 1){
                        System.out.print("\n<> Digite o número que deseja remover da árvore: ");
                        aux = a1.search(a1.getRoot(), in.nextInt());
						// A variável auxiliar armazena a a celula (ou nil) em que se encontra 
						//o número inteiro que o usuário deseja buscar.
                        if (aux != null && aux != a1.getNil()){
                            a1.remove(aux);
                            System.out.print("Número removido!");
                        } else {
                            System.out.print("Número não existente na árvore.");
                        }
                        System.out.println("\n\n<> Deseja remover mais algum número? 1 - SIM  2 - NÃO");
                        continuar = validacao();
                    }
                    break;
                case 3:
                    System.out.println("\n\n><><><   PERCURSOS   ><><><\n");
                    System.out.println("< 1 > EM ORDEM");
                    System.out.println("< 2 > PRÉ ORDEM");
                    System.out.println("< 3 > PÓS ORDEM");
                    System.out.print("Opção: ");
                    op = in.nextInt();
                    switch (op){
                        case 1:
                            a1.inOrder(a1.getRoot());
                            break;
                        case 2:
                            a1.preOrder(a1.getRoot());
                            break;
                        case 3:
                            a1.postOrder(a1.getRoot());
                            break;
                        default:
                            System.out.println("OPÇÃO INVALIDA!");
                            break;
                    }
                    break;
                case 4:
                    System.out.println("\nFIM!\n");

                    break;
                default:
                    System.out.println("OPÇÃO INVALIDA!");

            }
        } while (op != 4);
    }

    public static int validacao(){
        //Método de validação da entrada do usuário.
        int continuar = in.nextInt();
        while (continuar != 1 && continuar != 2){
            System.out.print("OPÇÃO INVALIDA");
            System.out.println("\n<> Deseja inserir mais algum número? 1 - SIM  2 - NÃO");
            continuar = in.nextInt();
        }
        return continuar;
    }
}