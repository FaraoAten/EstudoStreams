package estudostreams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

 /*
        //Streams parte 1 - operações intermediárias
        List<Integer> lista = Arrays.asList(1, 5, 8, 9, 1, 4, 7, 6, 6, 9, 9);

        lista.stream()
                .skip(2) //O método skip(n) ignora os n primeiros elementos na stream.
                .forEach(e -> System.out.println(e));

        //skip é uma operação intermediária.
        //operações intermediárias são chamadas assim porque é possível realizá-las diversas vezes antes de realizar uma operação terminal (inclusive uma já realizada).
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
         */
        //Streams parte 2 - operações terminais.
        //forEach(Consumer) é uma operação terminal.
        //Operações terminais só podem ser usada uma vez no fluxo de uma stream e encerram ela.
        List<Integer> lista = Arrays.asList(1, 5, 8, 9, 1, 4, 7, 6, 6, 9, 9);

        long count = lista.stream()
                .distinct()
                .filter(e -> e % 2 == 0)
                .count(); //O método count() é uma operação terminal que retona a quantia de elementos nessa stream.
        System.out.println(count);
        
        System.out.println("\n==================================\n");
        
        Optional<Integer> min = lista.stream()
                .distinct()
                .filter(e -> e % 2 == 0)
                .min(Comparator.naturalOrder()); //O método min(Comparator) é uma operação terminal que retona o "menor" elemento nessa stream baseado na comparação que você deu.
                //aqui está se usando uma comparação nativa do Java, mas para elementos autorais se deve extender comparable na classe que cria os elementos e implementar um compareTo() ou criar aqui uma função de comparação dos elementos.
        System.out.println(min.get());
        
        System.out.println("\n==================================\n");
        
        Optional<Integer> max = lista.stream()
                .distinct()
                .filter(e -> e % 2 == 0)
                .max(Comparator.naturalOrder()); //O método max(Comparator) é o oposto do min(Comparator).
        System.out.println(max.get());
        
        System.out.println("\n==================================\n");
        
        //O método collect é uma operação terminal com várias versões que tem como objetivo agrupar as manipulações feitas em uma stream.
        
        List<Integer> novaLista = lista.stream()
                .distinct()
                .filter(e -> e % 2 == 0)
                .collect(Collectors.toList()); //O método collect(Collectors.toList()) é uma operação terminal que agrupa e retorna o resultado das manipulações feitas na stream em uma lista.
        System.out.println(novaLista);
        
        System.out.println("\n==================================\n");
        
       Map<Boolean,  List<Integer>> mapa = lista.stream()
                .map(e -> e * 3)
                .collect(Collectors.groupingBy(e -> e % 2 == 0)); //O método collect(Collectors.groupingBy(Function)) é uma operação terminal que agrupa e retorna o resultado das manipulações feitas na stream em um map com a organização dele sendo definida pela função passada no groupingBy.
        System.out.println(mapa);
        
        System.out.println("\n==================================\n");
        
        Map<Integer,  List<Integer>> mapa2 = lista.stream()
                .collect(Collectors.groupingBy(e -> e % 3)); 
        System.out.println(mapa2);
        //aqui o mapa é de inteiros, pois o resultado da função é um inteiro.
        System.out.println("\n==================================\n");
        
        String joining = lista.stream()
                .map(e -> String.valueOf(e))
                .collect(Collectors.joining()); //O método collect(Collectors.joining) é uma operação terminal que agrupa e retorna o resultado das manipulações feitas na stream em uma String concatenada.
        System.out.println(joining);
        
        System.out.println("\n==================================\n");
        
        String joining2 = lista.stream()
                .map(e -> String.valueOf(e))
                .collect(Collectors.joining(";")); 
        System.out.println(joining2);
        //também é possível fazer com que o joining contate algo entre os elementos ao adicionar um delimitador (há também uma terceira assinatura mais detalhada).
        System.out.println("\n==================================\n");
    }
}
