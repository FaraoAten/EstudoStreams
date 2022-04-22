package estudostreams;

import java.util.Arrays;
import java.util.List;

public class EstudoStreams {
    public static void main(String[] args) {
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
    }    
}
