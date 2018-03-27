package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;

    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(500);
    }
    
    @Test
    public void kassassaRahaaTest() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisestiBooleanTrue() {
        assertTrue(kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void syoEdullisestiBooleanFalse() {
        kortti = new Maksukortti(0);
        assertFalse(kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void syoMaukkaastiBooleanTrue() {
        assertTrue(kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void syoMaukkaastiBooleanFalse() {
        kortti = new Maksukortti(0);
        assertFalse(kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void rahaEiLisaannyMaukkaasti() {
        kortti = new Maksukortti(0);
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void rahaEiLisaannyEdullisesti() {
        kortti = new Maksukortti(0);
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisesti1() {
        kassa.syoEdullisesti(500);
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisesti2() {
        kassa.syoEdullisesti(200);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoEdullisesti3() {
        assertEquals(260, kassa.syoEdullisesti(500));
    }
    
    @Test
    public void syoMaukkaasti1() {
        kassa.syoMaukkaasti(500);
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaasti2() {
        kassa.syoMaukkaasti(200);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void syoMaukkaasti3() {
        assertEquals(100, kassa.syoMaukkaasti(500));
    }
    
    @Test
    public void edullisiaMyyty1() {
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(500);
        assertEquals(2, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullisiaMyyty2() {
        kortti = new Maksukortti(0);
        kassa.syoEdullisesti(kortti);
        kassa.syoEdullisesti(200);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaitaMyyty1() {
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(500);
        assertEquals(2, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukkaitaMyyty2() {
        kortti = new Maksukortti(0);
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(200);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void lataaRahaaKortille1() {
        kassa.lataaRahaaKortille(kortti, 500);
        assertEquals(1000, kortti.saldo());
    }
    
    @Test
    public void lataaRahaaKortille2() {
        kassa.lataaRahaaKortille(kortti, -500);
        assertEquals(500, kortti.saldo());
    }
    
    @Test
    public void lataaRahaaKortille3() {
        kassa.lataaRahaaKortille(kortti, 500);
        assertEquals(100500, kassa.kassassaRahaa());
    }
}
