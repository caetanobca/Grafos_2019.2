import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

public class VendaImoveisTest2 {
    // Estruturas de dados a serem usadas nos testes
    VendaImoveis v1;
    HashSet<String> imoveis1;

    @Before
    public void init () {
        // Criando Dados de Teste
        v1 = new VendaImoveis("./src/main/resources/Vizinhanca2.csv");

        imoveis1 = new HashSet<String>();
        imoveis1.add("I1"); imoveis1.add("I2"); imoveis1.add("I3"); imoveis1.add("I4");

    }

    @Test
    public void test1() {
        String imovel = v1.localizaImovel("UNIVERSIDADE",imoveis1);
        assertEquals("I1",imovel);

    }

    @Test
    public void test2() {
        String imovel = v1.localizaImovel("POSTO",imoveis1);
        assertEquals("I3",imovel);

    }
    @Test
    public void test3() {
        String imovel = v1.localizaImovel("HOTEL",imoveis1);
        assertEquals("I3",imovel);

    }
    @Test
    public void test4() {
        String imovel = v1.localizaImovel("PRACA",imoveis1);
        assertEquals("I2",imovel);

    }
    @Test
    public void test5() {
        String imovel = v1.localizaImovel("Q10",imoveis1);
        assertEquals("I2",imovel);

    }
}
