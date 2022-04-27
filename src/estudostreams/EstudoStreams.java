package estudostreams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class EstudoStreams {

    public static void main(String[] args) throws FileNotFoundException, IOException {
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
        
       //Obs.: Em expressões Lambda é necessário o uso de parenteses no parametro quando: Não há parametros, há mais de um parametro ou quiser definir o tipo do parametro (se houver mais de um deve tipar todos).
       //Obs. 2: Em expressões Lambda é necessário o uso de chaves apenas quando há mais de uma linha, mas quando se põe elas é necessário colocar return, em expressões que retornam algo, e finalizar as linhas com ;. 
       
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
 /*
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
         */
 /*
        //Optional
        //Classe inserida no Java 8 que tem por intuito facilitar o trabalho com valores que podem ser null.
        //Existem Optionals para os tipos primitivos.
        //Optinal foi criado para ser utilizado como retorno de método que pode retornar nulo, não como argumento recebido.
        
        String s = "1";
        Optional<Integer> numero = converteEmNumero(s);//Esse optinal, por exemplo, é um obj que pode ou não conter um inteiro
        System.out.println(numero);
        
        String s2 = "a";
        Optional<Integer> numero2 = converteEmNumero(s2);
        System.out.println(numero2);
        
        System.out.println("\n==================================\n");
        
        //Usando os Optinals
        System.out.println(numero.isPresent());//O métodod isPresent() retorna verdadeiro caso o Optinal esteja preenchido
        System.out.println(numero2.isPresent());//e false caso o Optinal esteja vazio.
        
        System.out.println("\n==================================\n");
        
        System.out.println(numero.get());//O método get() retorna o valor presente dentro do Optional ou uma excessão caso ele esteja vazio.
        
        System.out.println("\n==================================\n");
        
        numero.ifPresent(n -> System.out.println(n));//O método ifPresent(Consumer) excuta a expressão Lambda dentro dele apenas se o Optinal estiver preenchido.
        numero2.ifPresent(n -> System.out.println(n)); 
        
        System.out.println("\n==================================\n");
        
        Integer numero3 = converteEmNumero(s).orElse(0);//O método orElse(T other) dispara caso o retorno do método ao qual ele está ligado seja um Optinal vazio, assim ele insere o valor presente nele no lugar do Optional vazio.
        System.out.println(numero3);
        Integer numero4 = converteEmNumero(s2).orElse(0);
        System.out.println(numero4);
        
        System.out.println("\n==================================\n");
        
        Integer numero5 = converteEmNumero(s).orElseGet(() -> -1);//O método orElseGet(Supplier) dispara caso o retorno do método ao qual ele está ligado seja um Optinal vazio, assim ele excuta a expressão Lambda dentro dele.
        System.out.println(numero5);
        Integer numero6 = converteEmNumero(s2).orElseGet(() -> -1);
        System.out.println(numero6);
        //Há um ifPresentOrElse(Consumer, Runnable) que é como uma mistura do ifPresent com o orElseGet, ele performa a primeira expressão Lambda caso o Optional esteja preenchido e a segunda caso não.
        
        System.out.println("\n==================================\n");
        
        Integer numero7 = converteEmNumero(s).orElseThrow(() -> new NullPointerException("Valor vazio."));//O método orElseThrow(Supplier) dispara caso o retorno do método ao qual ele está ligado seja um Optinal vazio, assim ele lança uma excessão excutando a expressão Lambda dentro dele.
        System.out.println(numero7);
        Integer numero8 = converteEmNumero(s2).orElseThrow(() -> new NullPointerException("Valor vazio."));
        System.out.println(numero8);
         */
 /*
         //Streams - Reduce
         //A função do reduce é basicamente juntar todos os elementos de uma stream em um único valor.
         
         List<Integer> list = Arrays.asList(1,2,3,4,5,6);
         
         Optional<Integer> reduceSoma = list.stream()
                 .reduce((n1, n2) -> n1 + n2);//Aqui está sendo usada a função de acumulação do reduce, reduce(Accumulator), através da soma de elementos, na expreção Lambda descrita se soma os 2 primeiros elemetos, então o resultado da soma passa a ser o n1 e o elemento seguinte o n2.
         System.out.println(reduceSoma.get());
         
         System.out.println("\n==================================\n");
         
         Optional<Integer> reduceMult = list.stream()
                 .reduce((n1, n2) -> n1 * n2);//Aqui está sendo feita a mesma coisa do anterior, porém com multiplicação. 
         System.out.println(reduceMult.get());
         
         System.out.println("\n==================================\n");
         
         Optional<String> reduceStr = list.stream()
                 .map(e -> String.valueOf(e))
                 .reduce((s1, s2) -> s1.concat(s2));//Aqui está sendo feita a mesma coisa do anterior, porém com concatenação de textos.
         System.out.println(reduceStr.get());
         
         //Subtração e divisão não podem ser utilizadas no reduce, pois as funções uzadas nele devem ser associativa, ou seja, se forem executadas captando os elementos de forma sequencial ou separando-os em grupos e depois juntando os resultados dos grupos o resultado final deve ser o mesmo.
         
         System.out.println("\n==================================\n");
         
         //Redudce usando valor de identidade, isso é usado para caso onde você não pode ou não quer receber nulo, ao ter um valor de identidade ao invés de receber um Optional você recebe um valor não nulo, como Integer por exemplo.
         //O valor de identidade atual como um valor mínimo para a operção, é utilizado quando a stream está vazia e tem de seguir a seguinte regra: ao fazer a operação entre e um valor da stream o resultado deve ser esse valor da stream.
         
         List<Integer> listVazia = Arrays.asList();
         
         Integer reduceSoma2 = list.stream()
                 .reduce(0, (n1, n2) -> n1 + n2);//O valor identidade da soma é 0, pois qualquer número somado a 0 é o próprio número.
         System.out.println(reduceSoma2);
         
         Integer reduceSoma3 = listVazia.stream()
                 .reduce(0, (n1, n2) -> n1 + n2);
         System.out.println(reduceSoma3);
         
         System.out.println("\n==================================\n");
         
         Integer reduceMult2 = list.stream()
                 .reduce(1, (n1, n2) -> n1 * n2);//O valor identidade da multiplicação é 1, pois qualquer número multiplicado por 1 é o próprio número.
         System.out.println(reduceMult2);
         
         Integer reduceMult3 = listVazia.stream()
                 .reduce(1, (n1, n2) -> n1 * n2);
         System.out.println(reduceMult3);
         
         System.out.println("\n==================================\n");
         
         String reduceStr2 = list.stream()
                 .map(e -> String.valueOf(e))
                 .reduce("", (s1, s2) -> s1.concat(s2));//O valor identidade da concatenção é "", pois qualquer texto concatenado com "" é o próprio texto.
         System.out.println(reduceStr2);
         
         String reduceStr3 = listVazia.stream()
                 .map(e -> String.valueOf(e))
                 .reduce("", (s1, s2) -> s1.concat(s2));
         System.out.println(reduceStr3);
         
         System.out.println("\n==================================\n");
         
         //Reduce usando valor de identidade e função de combinação.
         //Basicamente usado para quando temos streams paralelas, onde o reduce pode acabar separando a stream em vários grupos, nesses casos a função de acumulação é aplicada nos grupos e a de combinação é aplicada para juntar os grupos.
         
         Integer reduceSoma4 = list.stream()
                 .reduce(0, (n1, n2) -> n1 + n2, (n1, n2) -> n1 + n2);//Na soma, e nas outras operações acima, as funções de acumulação e combinação são iguais, pois queremos a mesma coisa mesmo que o reduce quebre a stream em vários grupos.
         System.out.println(reduceSoma4);
         
         Integer reduceSoma5 = listVazia.stream()
                 .reduce(0, (n1, n2) -> n1 + n2, (n1, n2) -> n1 + n2);
         System.out.println(reduceSoma5);
         
         System.out.println("\n==================================\n");
         
         String reduceStr4 = list.stream()
                 .reduce("", (s1, s2) -> s1.toString().concat(s2.toString()), (s1, s2) -> s1.concat(s2));//Nesse caso a função de acumulação e combinação são diferentes, pois estamos praticamente aplicando uma map nos grupos enquando acumulamos, mas o map não é necessário na hora de combinar os grupos. Abre possibilidade para realizarmos operações de transformação usando reduce.
         System.out.println(reduceStr4);
         //Esse modelo de reduce existe especificamente para casos como esse, pois pode haver um ganho de performace, pois nos outros casos a função de acumulação e combinação são a mesma, então as opções anteriores de reduce já bastam.
         */
 /*
        //Streams - Collect
        //Semelhante ao reduce, porém para trabalhar com objetos mutáveis.
        
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);

        List<Integer> collect = list.stream()
                .collect(() -> new ArrayList<>(), (l,e) -> l.add(e), (l1,l2) -> l1.addAll(l2));
        //Nessa maneira de usar collectors personalizados ao invés dos prontos, primeiro temos que informar um Supplier que é uma Lambda que retorna a instância de onde vamos armazenar os dados, segundamente informamos o método de acumulação e por fim o de combinação, esses 2 últimos funcionam como no reduce.
        System.out.println(collect);
        
        System.out.println("\n==================================\n");
        
        Set<Integer> collect2 = list.stream()
                .collect(() -> new HashSet(), (l,e) -> l.add(e), (l1,l2) -> l1.addAll(l2));
        //Usando outra instância no Supplier.
        System.out.println(collect2);
        
        //Essa versão mais crua do Collect normalmente não é utilizada, já que os collectors já prontos atendem muito bem as necessidades.
        
        System.out.println("\n==================================\n");
        
        //Collector prontos (Não estou anotando os que já foram vistos em aulas anteriores, toList; groupingBy e joining).
        
        Set<Integer> collectSet = list.stream()
                .filter(e -> e % 2 == 0)
                .collect(Collectors.toSet());//O método collect(Collectors.toSet()) agrupa e retorna o resultado das manipulações feitas na stream em um set.
        System.out.println(collectSet);
        
        System.out.println("\n==================================\n");
        
        Set<Integer> collectCollection = list.stream()
                .filter(e -> e % 2 == 0)
                .collect(Collectors.toCollection(() -> new TreeSet<>()));//O método collect(Collectors.toCollection()) agrupa e retorna o resultado das manipulações feitas na stream em uma collection a sua escolha (outros exemplo são LinkedList, ArrayDeque etc...).
        System.out.println(collectCollection);
        
        System.out.println("\n==================================\n");
        
        Double collectAvereging = list.stream()
                .collect(Collectors.averagingInt(n -> n.intValue()));//O método collect(Collectors.averagingInt(mapper)) retorna a média de todos os valores dentro da stream, mapper é só para transformar o tipo do valor em um tipo primitivo e existem versões do avareging para double e float também.
        System.out.println(collectAvereging);
        
        System.out.println("\n==================================\n");
        
        Integer collectSumming = list.stream()
                .collect(Collectors.summingInt(n -> n.intValue()));//O método collect(Collectors.summingInt(mapper)) retorna a soma de todos os valores dentro da stream, mapper é só para transformar o tipo do valor em um tipo primitivo e existem versões do summing para double e float também.
        System.out.println(collectSumming);
        
        System.out.println("\n==================================\n");
        
        IntSummaryStatistics collectSummarizing = list.stream()
                .collect(Collectors.summarizingInt(n -> n.intValue()));//O método collect(Collectors.summarizingInt(mapper)) retorna uma série de resultados de operações envolvendo todos os valores dentro da stream, mapper é só para transformar o tipo do valor em um tipo primitivo e existem versões do summarizing para double e float também.
        System.out.println(collectSummarizing.getAverage());
        System.out.println(collectSummarizing.getCount());
        System.out.println(collectSummarizing.getMax());
        System.out.println(collectSummarizing.getMin());
        System.out.println(collectSummarizing.getSum());
        
        System.out.println("\n==================================\n");
         
        Long collectCounting = list.stream()
                .filter(e -> e % 2 == 0)
                .collect(Collectors.counting());//O método collect(Collectors.counting()) retorna a quantia de elementos na stream em um Long.
        System.out.println(collectCounting);
        
        System.out.println("\n==================================\n");
         
        list.stream()
                .collect(Collectors.minBy(Comparator.naturalOrder()))//O método collect(Collectors.minBy(comparator)) compara através de um método compareTo presente na classe do elemento ou através de um comparator implemtentado aqui e retorna o menor elemento da stream em um Opcional<T>.
                .ifPresent(e -> System.out.println(e));
        
        System.out.println("\n==================================\n");
         
        list.stream()
                .collect(Collectors.maxBy(Comparator.naturalOrder()))//O método collect(Collectors.maxBy(comparator)) compara através de um método compareTo presente na classe do elemento ou através de um comparator implemtentado aqui e retorna o maior elemento da stream em um Opcional<T>.
                .ifPresent(e -> System.out.println(e));
        
        System.out.println("\n==================================\n");
         
        Map<Boolean, List<Integer>> collectPartitioningBy = list.stream()
                .collect(Collectors.partitioningBy(e -> e % 3 == 0));//O método collect(Collectors.partitioningBy(Predicate)) agrupa e retorna o resultado das manipulações feitas na stream em um map obrigatóriamente booleano com a organização dele sendo definida pela função passada no partitioningBy.
        System.out.println(collectPartitioningBy);
        
        System.out.println("\n==================================\n");
         
        Map<Integer, Double> collectToMap = list.stream()
                .collect(Collectors.toMap(e -> e, e -> Math.pow(e.doubleValue(), 5)));//O método collect(Collectors.toMap(Function, Function)) agrupa e retorna o resultado das manipulações feitas na stream em um map com a chave sendo definida pela primeira função e o valor pela segunda, há mais assituras de toMap essa é a mais simples, mas o toMap é só uma opção mais personalizavel para retorna um map de uma stream.
        System.out.println(collectToMap);
         */
 /*
        //Method Reference
        //Uma maneira de encurtar códigos
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        //ex.:
        list.stream()
                .forEach(System.out::println);//O method reference é essa maneira alternativa de escrever expressões Lambdas que diminui o código e, por conta disso, diminui as chances de erro.
        
        System.out.println("\n==================================\n");
        
        //Há 4 tipos de Method Reference:
        //para métodos static
        list.stream()
                .map(EstudoStreams::multiplicaPorDois)//Para métodos static basta colocarmos o nome da classe, seguido de :: e por fim o nome do método.
                .forEach(System.out::println);
        
        System.out.println("\n==================================\n");
        
        //para construtores
        list.stream()
                .map(BigDecimal::new)//Para construtores basta colocarmos o nome da classe, seguido de :: e por fim colocarmos new.
                .forEach(System.out::println);
        
        System.out.println("\n==================================\n");
        
        //para múltiplas instâncias
        list.stream()
                .map(Integer::doubleValue)//Aqui se está usando um Method Reference em cada valor da stream que é instanciado como Integer, invocando o método doubleValue deles, por isso várias instâncias.
                .forEach(System.out::println);
        
        System.out.println("\n==================================\n");
        
        //para instância única
        BigDecimal dois = new BigDecimal(2);
        list.stream()
                .map(BigDecimal::new)
                .map(dois::multiply)//Aqui se está usando um Method Reference apenas no obejeto dois instânciado como BigDecimal, invocando o método multiply dele, por isso instância única.
                .forEach(System.out::println);
        
        //Method Reference só podem ser usados em métodos que recebem no máximo 1 parametro.
         */
 /*
        //Streams - Interfaces Funcionais
        //As interfaces funcionais são as interfaces no padrão SAM, aqui vamos ver e enteder sobre as que já vem prontas no Java e são usadas nos métodos de stream.
        
        //Supplier (Fornecedor)
        //Não recebe nenhum parametro e retorna um valor T. Ele basicamente gera valores.
        //Quando um método pede um Supplier é porque ele não quer receber nenhum parametro, apenas quer receber o valor que será gerado pelo Supplier para fazer algo.
        Stream.generate(() -> new Random().nextInt())//O generate recebe um Supplier que gera os elementos para ele criar uma Stream sequêncial não-ordenada.
                .limit(5)
                .forEach(System.out::println);
        
        System.out.println("\n==================================\n");
        
        //Consumer (Consumidor)
        //Basicamente o oposto do Supplier, ele recebe um parametro, faz algo com ele, e não retorna nada.
        //Quando um método pede um Consumer é porque ele quer receber um parametro e realizar alguma ação com ele e não retornar nada.
        Stream.generate(() -> new Random().nextInt())
                .limit(2)
                .forEach((e) -> System.out.println(e));//O forEach recebe um Consumer onde ele recebe um parametro para realizar uma ação, como printar por exemplo, e não retorna nada.
        
        //Existe também o Biconsumer (Biconsumidor) que é um Consumer que recebe 2 parametros e atua da mesma maneira.
        
        System.out.println("\n==================================\n");
        
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        
        //Predicate (Predicado)
        //Ele recebe um parametro e retorna um boolean.
        //Quando um método pede um Predicate é porque ele quer receber um parametro e retornar o resultado de uma comparação (True ou False).        
        list.stream()
                .filter(e -> e % 2 ==0)//O filter recebe um Predicate onde ele recebe um um parametro e faz uma comparação com ele para gerar um nova stream apenas com os valores que passaram na comparação.
                .forEach(System.out::println); 
        
        //Existe também o Bipredicate (Bipredicado) que é um Predicate que recebe 2 parametros e atua da mesma maneira.
        
        System.out.println("\n==================================\n");
        
        //Function (Função) (A mais genérica das Interfaces Funcionais)
        //Ela recebe um parametro e retorna um valor T.
        //Quando um método pede uma Function é porque ele quer receber um parametro e retornar um valor T como resultado de uma operação feita.
        list.stream()
                .map(e -> e * 2)//O map recebe uma Function onde ela recebe um parametro e faz uma modificação determinada na expressão lambda para gerar um nova stream apenas com os valores modificados.
                .forEach(System.out::println);
        
        //Existe também a Bifunction (Bifunção) que é uma Function que recebe 2 parametros e atua da mesma maneira.
        
        System.out.println("\n==================================\n");
        
        //UnaryOperator (Operador Unitário)
        //Ela recebe um parametro do tipo T e retorna um valor também do tipo T. Ele extende Function, logo atua como ela, tendo como única difereça que a entrada e saída sejam do mesmo tipo.
        //Quando um método pede um UnaryOperator é porque ele quer receber um parametro de um tipo e retornar um valor do mesmo tipo como resultado de uma operação feita.
        //Existe também o BinaryOperator (Operador Binário) que extende Bifunction recebendo 2 parametros de um único tipo e retornando um valor do mesmo tipo, assim atuando de maneira semelhante ao UnaryOperator.
        list.stream()
                .reduce((n1, n2) -> n1+n2)//O reduce recebe um BinaryOperator onde ele recebe 2 parametros e faz, nesse exemplo, um acumulação determinada na função lambda para gerar um Optional<T> com o resultado final da acumulação.
                .ifPresent(System.out::println);
         */
 
         /*
        //Streams - Todas as maneiras de se gerar streams.
        //Collection - pode se gerar uma stream de qualquer coleção de dados, por exemplo, List e suas filhas, Set etc...
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        list.stream()
                .forEach(System.out::println);

        System.out.println("\n==================================\n");

        //Arrays - através da classe Arrays é possível criar stream de arrays comuns também
        Integer[] intArray = new Integer[]{1, 2, 3, 4};
        Arrays.stream(intArray)
                .forEach(System.out::println);

        System.out.println("\n==================================\n");

        //Stream.of - com o método of da classe Stream é possível criar uma stream de qualquer sequência de valores que você inserir dentro do of.
        Stream.of("A", "t", "e", "n")
                .forEach(System.out::println);
        
        System.out.println("\n==================================\n");
        
        //IntStream.range - com o método range(inicial, final) da classe IntStream (também exite a DoubleStream, LongStream e FloatStream) é possível criar uma stream de um range de números que vai do inicial ao anterior do final (existe também o método rangeClosed que inclui o número final).
        IntStream.range(0, 5)
                .forEach(System.out::println);
        
        System.out.println("\n==================================\n");
        
        //Stream.interate - com o método interate(seed, f) da classe Stream é possível criar uma stream infinita onde é dado o ponto inicial e uma expreção lambda (UnaryOperator) que diz como gerar o próximo elemento (Também há uma versão mais customizável dele).
        Stream.iterate(1, n -> n*2)
                .limit(5)
                .forEach(System.out::println);
        
        System.out.println("\n==================================\n");
        
        //BufferedReader - o método lines() gera uma stream de Strings presentes em linhas de um arquivo.
        //Streams.txt - 11, 12, 13 (cada um em uma linha)
        File file = new File("/C://Users//boder//Downloads//Streams.txt/");
        FileReader in = new FileReader(file);
        try (BufferedReader bufferedReader = new BufferedReader(in)) {
            bufferedReader.lines()
                    .forEach(System.out::println);
        }
        
        System.out.println("\n==================================\n");
        
        //Files - o método list(Path) gera uma stream com todos os arquivos presentes naquele path.
        Path p = Paths.get("");
        Files.list(p)
                .forEach(System.out::println);
        
        System.out.println("\n==================================\n");
        
        //Random - atráves dos métodos ints, doubles ou longs é possível criar streams númericas que são infinitas e aleatórias (também há versões desses métodos com mais parametros). 
        new Random().ints()
                .limit(3)
                .forEach(System.out::println);
        
        System.out.println("\n==================================\n");
        
        //Pattern - através do método splitAsStream é possível gerar uma stream de Strings ao separar uma String em partes tomando como ponto de separação uma Regex.
        Pattern pattern = Pattern.compile("\\s");
        pattern.splitAsStream("Oi,Meu nome é Aten.")
                .forEach(System.out::println);
        */
         
         //Streams paralelas
         
         List<Integer> list = Arrays.asList(1,2,3,4);
         
         /*
         //parallel vs parallelStream
         //O meio para para criar uma stream paralela varia dependendo do jeito que você cria a Stream:
         list.parallelStream();//Basicamente se for uma collection basta substituir o método stream por parallelStream().
         
         IntStream.range(0, 5).parallel();//Em qualquer outro caso se adiciona o método parallel após a criação da stream.
         */
         
         //Usar streams paralelas de maneira pontual apenas quando for necessário, questão de trabalhar com milhares de registros, pois só assim ganho vai ser maior que o custo de processamento e vai haver um real ganho de performance.
         
         //forEach vs forEachOrdered
         list.parallelStream()
                 .forEach(System.out::println);//Quando se trabalha com streams paralelas, pelo sistema de processamento usado por elas, o forEach passa os elementos de maneira aleatória.
         
         System.out.println("\n==================================\n");
         
         list.parallelStream()
                 .forEachOrdered(System.out::println);//Para preservar a ordem original da stream devesse usar o forEachOrdered, caso seja necessário preservá-la.
         
         System.out.println("\n==================================\n");
         
         //findAny
         
         list.stream()
                 .findAny()//Em uma stream comum onde o tratamento é sequêncial o findAny retorna um Optional vazio ou com o primeiro elemento da stream.
                 .ifPresent(System.out::println);
         
         System.out.println("\n==================================\n");
         
         list.parallelStream()
                 .findAny()//Mas em uma stream paralela onde é feito um tratamento múltiplo o findAny retona um Optional vazio ou com o elemento presente no primeiro tratramento resolvido pelo sistema.
                 .ifPresent(System.out::println);
         
         System.out.println("\n==================================\n");
         
         //unordered
         //Método criado para se usar junto de skip, limit e distinct que exigem certa sincronia entre as threads que deviriam ser independêntes, então, caso no seu código não importe qual elemento será pulado pelo skip, quais serão tratados dentro do limit ou qual será pego pelo distinct, use unordered para utilizar nesse método a ordem em que os elementos ficam prontos nas threads, assim ganhando performance.
         list.parallelStream()
                 .unordered()
                 .skip(1)
                 .limit(2)
                 .forEach(System.out::println);
         
         System.out.println("\n==================================\n");
         
         //toConcurrentMap
         //Quando se usa parallel streams, para ganhar performance, ao invés criar um mapa usando toMap usa-se o toConcurrentMap, pois ele permite que todas as threads acessem ele ao mesmo tempo para inserir seus valores, não forçando o programa a criar diversos mapa e dar um merge em todos eles.
         Map<Integer, Boolean> mapa = list.parallelStream()
                 .collect(Collectors.toConcurrentMap(n -> n, n -> n%2 == 0));
         System.out.println(mapa);
         
         System.out.println("\n==================================\n");
         
         //groupingByConcurrent
         //Mesma ideia do toConcurrentMap, porém aplicado ao grouppingBy.
         Map<Boolean, List<Integer>> mapaGBC = list.parallelStream()
                 .collect(Collectors.groupingByConcurrent(n -> n%2 == 0));
         System.out.println(mapaGBC);
         
         //Nas versões Concurrent você pode perder a ordem dos elementos.
    }

    /*
    //Optional
    public static Optional<Integer> converteEmNumero(String s) {
        try {
            Integer inteiro = Integer.valueOf(s);
            return Optional.of(inteiro);//O método of(valor) retorna um Optional de um valor não-nulo.
            //Há um método para Optinal chamado ofNullable(valor), usado para converter valores que tem a possibilidade de serem nulos, se for nulo retorna um Optional vazio.
        }catch(Exception e){
            return Optional.empty();//O método empty() retorna um Optional vazio.
        }
    }
     */
 /*
    //Method Reference
    public static Integer multiplicaPorDois(Integer n){
        return n*2;
    }
     */
}
