package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoa() {
        kortti.lataaRahaa(1000);
        assertEquals("saldo: 20.0", kortti.toString());
    }
    
    @Test
    public void rahanNosto1() {
        kortti.otaRahaa(500);
        assertEquals("saldo: 5.0", kortti.toString());
    }
    
    @Test
    public void rahanNosto2() {
        kortti.otaRahaa(5000);
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void rahanNostoBoolean1() {
        assertEquals(true, kortti.otaRahaa(500));
    }
    
    @Test
    public void rahanNostoBoolean2() {
        assertEquals(false, kortti.otaRahaa(500000));
    }
    
    @Test
    public void saldoTest() {
        assertEquals(1000, kortti.saldo());
    }
    
    @Test
    public void toStringTest() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
}
