class Termostat {
    public double aktualna_temperatura; //aktualna temperatura
    public double ustawiona_temperatura; // ustawiona temperatura
    public boolean wlaczone_ogrzewanie; // stan ogrzewania
    public boolean wlaczone_chlodzenie; // stan chlodzenia

    public Termostat(double poczatkowa_temperatura, double ustawiona_temperatura) {
        this.aktualna_temperatura = poczatkowa_temperatura;
        this.ustawiona_temperatura = ustawiona_temperatura;
    }

    public void wlaczOgrzewanie() {    //funkcja wlaczajaca ogrzewanie
        wlaczone_ogrzewanie = true;
        wlaczone_chlodzenie = false;
        System.out.println("Właczono ogrzewanie");
    }

    public void wlaczChlodzenie() {   // funkcja wlaczajaca chlodzenie
        wlaczone_chlodzenie = true;
        wlaczone_ogrzewanie = false;
        System.out.println("Wlaczono chlodzenie");
    }

    public void wylaczOgrzewanie() {    //funkcja wylaczajaca ogrzewanie
        wlaczone_ogrzewanie = false;
        System.out.println("Wylaczone ogrzewanie");
    }

    public void wylaczChlodzenie() {   // funkcja wylaczajaca chlodzenie
        wlaczone_chlodzenie = false;
        System.out.println("Wylaczone chlodzenie");
    }

    public void sprawdzTemperature() {
        System.out.println("Aktualna temperatura: " + aktualna_temperatura + "°C");   //wyswietl aktualna temp.
        System.out.println("Ustawiona temperatura: " + ustawiona_temperatura + "°C");  // wyswietl ustawiona temp.

        if (aktualna_temperatura < ustawiona_temperatura && !wlaczone_chlodzenie) { // dzialanie termostatu
            wlaczOgrzewanie();
        } else if (aktualna_temperatura > ustawiona_temperatura && !wlaczone_ogrzewanie) {
            wlaczChlodzenie();
        } else if (aktualna_temperatura == ustawiona_temperatura) {
            wylaczOgrzewanie();
            wylaczChlodzenie();
            System.out.println("Temperatura została osiagnieta");
        }
    }

    public void symulujZmianeTemperatury(double tempZmiana, int czas) {
        for (int i = 0; i < czas; i++) {
            aktualna_temperatura += tempZmiana;
            sprawdzTemperature();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class SymulatorTermostatu {
    public static void main(String[] args) {
        Termostat termostat = new Termostat(20.0, 22.0);

        termostat.symulujZmianeTemperatury(0.5, 10);
    }
}