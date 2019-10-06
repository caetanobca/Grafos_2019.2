import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jgrapht.alg.util.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CirculoAmigosTest {

    @Test
    public void test1() {
        ArrayList<Pair<String, String>> listaAmigos = new ArrayList<Pair<String, String>>();
        listaAmigos.add(new Pair<String, String>("a", "b"));
        listaAmigos.add(new Pair<String, String>("c", "d"));
        listaAmigos.add(new Pair<String, String>("e", "f"));
        listaAmigos.add(new Pair<String, String>("f", "g"));
        listaAmigos.add(new Pair<String, String>("i", "j"));

        // Circulos que devem ser encontrados
        Set<String> c1 = new HashSet<String>();
        c1.add("a");
        c1.add("b");
        Set<String> c2 = new HashSet<String>();
        c2.add("c");
        c2.add("d");
        Set<String> c3 = new HashSet<String>();
        c3.add("e");
        c3.add("f");
        c3.add("g");
        Set<String> c4 = new HashSet<String>();
        c4.add("i");
        c4.add("j");

        // Calculando círculos
        List<Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);

        assertTrue(circuloAmigos.contains(c1));
        assertTrue(circuloAmigos.contains(c2));
        assertTrue(circuloAmigos.contains(c3));
        assertTrue(circuloAmigos.contains(c4));
        assertEquals(circuloAmigos.size(), 4);
    }

    @Test
    public void test2() {
        ArrayList<Pair<String, String>> listaAmigos = new ArrayList<Pair<String, String>>();
        listaAmigos.add(new Pair<String, String>("a", "b"));
        listaAmigos.add(new Pair<String, String>("c", "b"));
        listaAmigos.add(new Pair<String, String>("e", "a"));
        listaAmigos.add(new Pair<String, String>("f", "b"));
        listaAmigos.add(new Pair<String, String>("f", "a"));

        // Circulos que devem ser encontrados
        Set<String> c1 = new HashSet<String>();
        c1.add("a");
        c1.add("b");
        c1.add("c");
        c1.add("e");
        c1.add("f");

        // Calculando círculos
        List<Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);

        assertTrue(circuloAmigos.contains(c1));
        assertEquals(circuloAmigos.size(), 1);
    }

    @Test
    public void test3() {
        ArrayList<Pair<String, String>> listaAmigos = new ArrayList<Pair<String, String>>();

        // Calculando círculos
        List<Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);

        assertEquals(circuloAmigos.size(), 0);
    }

    @Test
    public void test4() {
        ArrayList<Pair<String, String>> listaAmigos = new ArrayList<Pair<String, String>>();
        listaAmigos.add(new Pair<String, String>("a", "b"));
        listaAmigos.add(new Pair<String, String>("b", "c"));
        listaAmigos.add(new Pair<String, String>("c", "d"));

        // Circulo que deve ser encontrado
        Set<String> c1 = new HashSet<String>();
        c1.add("a");
        c1.add("b");
        c1.add("c");
        c1.add("d");

        // Calculando círculos
        List<Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);

        assertTrue(circuloAmigos.contains(c1));
        assertEquals(1, circuloAmigos.size());
    }


    @Test
    public void test5() {
        ArrayList<Pair<String, String>> listaAmigos = new ArrayList<Pair<String, String>>();
        listaAmigos.add(new Pair<String, String>("i", "f"));
        listaAmigos.add(new Pair<String, String>("f", "g"));
        listaAmigos.add(new Pair<String, String>("j", "h"));

        // Circulos que devem ser encontrados
        Set<String> c1 = new HashSet<String>();
        c1.add("f");
        c1.add("g");
        c1.add("i");
        Set<String> c2 = new HashSet<String>();
        c2.add("j");
        c2.add("h");

        // Calculando círculos
        List<Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);

        assertTrue(circuloAmigos.contains(c1));
        assertTrue(circuloAmigos.contains(c2));

        assertEquals(2, circuloAmigos.size());
    }

    @Test
    public void test6() {
        ArrayList<Pair<String, String>> listaAmigos = new ArrayList<Pair<String, String>>();
        listaAmigos.add(new Pair<String, String>("a", "b"));
        listaAmigos.add(new Pair<String, String>("c", "d"));
        listaAmigos.add(new Pair<String, String>("e", "f"));

        // Circulos que devem ser encontrados
        Set<String> c1 = new HashSet<String>();
        c1.add("a");
        c1.add("b");
        Set<String> c2 = new HashSet<String>();
        c2.add("c");
        c2.add("d");
        Set<String> c3 = new HashSet<String>();
        c3.add("e");
        c3.add("f");

        // Calculando círculos
        List<Set<String>> circuloAmigos = CirculoAmigos.retornaCirculos(listaAmigos);

        assertTrue(circuloAmigos.contains(c1));
        assertTrue(circuloAmigos.contains(c2));
        assertTrue(circuloAmigos.contains(c3));

        assertEquals(3, circuloAmigos.size());
    }
}