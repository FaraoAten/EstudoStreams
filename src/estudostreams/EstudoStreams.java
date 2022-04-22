package estudostreams;

import java.util.Arrays;
import java.util.List;

public class EstudoStreams {

    public static void main(String[] args) {
        /*
       //Funções Lambda
       //Funções Lambda são um jeito mais simples de implementar métodos de interfaces SAM (Single Abstract Method)
       //Interfaces que tem apenas 1 método abstrato.
       
       //Seu padrão de contrução é (parametro recebido -> implementação), quando não há parametro a ser recebido se usa ().
       //Ex.:
       
       //Versão original
       new Thread(new Runnable(){
           @Override
           public void run(){
               System.out.println("Olá mundo!");
           }
       }).run();
       
       //Versão Lambda
       new Thread(() -> System.out.println("Olá mundo!")).run();
       
       //É possível usar expressões Lambdas tanto de interfaces SAM já presentes no Java quanto daquelas criadas por você.
       //O principal motivo para a implementação das funções Lambda no Java foi para possibilitar o uso das Streams.
       //Stream é um fluxo de dados.
       //Ela traz consigo uma séries de métodos que facilitam a manipulação de complidos de de dados, como Arrays, listas etc.
       //Ex. de stream:
       List<Integer> lista = Arrays.asList(1,2,3,4);
       lista.stream()
               .filter(e -> e%2==0) //O método filter filtra os elementos presentes na stream através do que foi implementado nele com expressões Lambda.
               .forEach(e->System.out.println(e)); //O método forEach funciona como um for each;    
         */

        //Streams parte 1 - operações intermediárias
        List<Integer> lista = Arrays.asList(1, 5, 8, 9, 1, 4, 7, 6, 6, 9, 9);

        lista.stream()
                .skip(2) //O método skip(n) ignora os n primeiros elementos na stream.
                .forEach(e -> System.out.println(e));

        //skip é uma operação intermediária.
        //operações intermediárias são chamadas assim porque é possível realizá-las diversas vezes antes de realizar uma operação final (inclusive uma já realizada).
        System.out.println("\n==================================\n");

        //Agora outros exemplos de operações intermediárias:
        lista.stream()
                .limit(2) //O método limit(n) ignora todos os outros elementos na stream após os n primeiros.
                .forEach(e -> System.out.println(e));

        System.out.println("\n==================================\n");

        lista.stream()
                .distinct() //O método distinct() ignora todos os elementos repetidos na stream, para isso ele usa o equals e o hashcode, então para utilizar esse método em uma lista de elementos autorais deve se implementar o equals e o hashcode na classe que cria esses elementos.
                .forEach(e -> System.out.println(e));

        System.out.println("\n==================================\n");
        //Exemplo demonstrando a utilização de várias op. intermediárias em sequência:
        lista.stream()
                .skip(5)
                .limit(4)
                .distinct() //O método distinct() ignora todos os elementos repetidos na stream, para isso ele usa o equals e o hashcode, então para utilizar esse método em uma lista de elementos autorais deve se implementar o equals e o hashcode na classe que cria esses elementos.
                .forEach(e -> System.out.println(e));

        System.out.println("\n==================================\n");

        lista.stream()
                .filter(e -> e % 2 == 0) //O método filter(Predicate) permite que implementemos o filtro que quisermos.
                .forEach(e -> System.out.println(e));
        
        System.out.println("\n==================================\n");

        lista.stream()
                .map(e -> e * 2) //O método map(Function) permite que implementemos uma transformação nos da stream que passam por ele (a lista original não é alterada).
                .forEach(e -> System.out.println(e));
        
        //O ideal é utilizar as operções de filtragem antes das interativas.
    }
}
