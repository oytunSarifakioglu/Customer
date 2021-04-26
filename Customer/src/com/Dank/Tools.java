package com.Dank;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by nurullah on 4.04.2017.
 */
public class Tools {

    public static String getString(String s) {
        /*
        * Scanner classı kullanarak int ve string değerlerini ard arda aldığımızda
        * satır karışıklığından kurtulmak için ayrı string donduren metod
        * Ayrıca String s şeklinde ekrana çıktı verir.
        */
        System.out.print(s);
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public static int getInt(String s) {
        /*
        * Scanner classı kullanarak int ve string değerlerini ard arda aldığımızda
        * satır karışıklığından kurtulmak için ayrı int döndüren metod
        * int e çevrilemeyecek bir klavye girdisi halinde düzgün girdi alasaya
        * kadar recursive bir şekilde tekrar eder.
        * Ayrıca String s şeklinde ekrana çıktı verir.*/
        Scanner in = new Scanner(System.in);
        System.out.print(s);
        try {
            return in.nextInt();
        } catch (InputMismatchException e) {
            //System.out.println("Lütfen sayısal bir değer giriniz.");
            return getInt("Lütfen sayısal bir değer giriniz: ");
        }
    }

    public static void ortalamaDerece(int[][] list, String[] urunListesi, Customer[] customerList, String mod) {
        /*
        * İki boyulu int array şeklinde product list alır
        * listenin sütunlarını temsil için bir String array alır
        * listenin Customer'larını temsil için bir Customer array alır
        * ayrıca nelerin ortalamalarının alınacağına göre String türünde Mod girdisi alaır (C,NC,IC,D)
        * Sonuç olarak tüm ürünlerin ortalama derecesi girilen moda göre yazdırılır.
        */

        for (int j = 0; j < urunListesi.length; j++) {
            int counter = 0;
            double ortalama = 0;
            //Genel durum (Madde 1)
            if (mod.equals("C")) {
                for (int i = 0; i < Customer.getCounter(); i++) {
                    counter += list[i][j];
                }
                ortalama = (double) counter / (double) Customer.getCounter();
            }
            //NationalCustomer durumu (Madde 2)
            else if (mod.equals("NC")) {
                for (int i = 0; i < Customer.getCounter(); i++) {
                    if (customerList[i] instanceof NationalCustomer) {
                        counter += list[i][j];
                    }
                }
                ortalama = (double) counter / (double) NationalCustomer.getNCCounter();

            }
            //InternationalCustomer durumu (Madde 3)
            else if (mod.equals("IC")) {
                for (int i = 0; i < Customer.getCounter(); i++) {
                    if (customerList[i] instanceof InternationalCustomer) {
                        counter += list[i][j];
                    }
                }
                ortalama = (double) counter / (double) InternationalCustomer.getICCounter();
            }
            //NationalCustomer içinde mesleğin Doktor olma durumu (Madde 4)
            else if (mod.equals("D")) {
                int doktorCounter = 0;
                for (int i = 0; i < Customer.getCounter(); i++) {
                    if (customerList[i] instanceof NationalCustomer) {
                        NationalCustomer NCustomer = (NationalCustomer) customerList[i];
                        if (NCustomer.getOccupation().equalsIgnoreCase("Doktor")) {
                            counter += list[i][j];
                            doktorCounter++;
                        }
                    }
                }
                ortalama = (double) counter / (double) doktorCounter;


            }
            //Sırayla ürün başına spesifik ortalamalar yazdırılır.
            System.out.printf("%s adlı ürünün ortalama puanı %.1f\n", urunListesi[j], ortalama);
        }
        System.out.println("\n*************************************************************************************\n");
    }

    private static double ortalama(int[][] genelListe, int indeks) {
        /*
        * ortalamaAltı() metodunda kullanılmak üzere oluşturulmuş private metod.
        * İki boyutlu int array girdisi ile sözkonusu array'ın sütununu temsil edecek
        * bir int indeks girdisi alır
        * Sonuç olarak array'ın indeksi verilmiş sütununun ortalamasını döndürür.
        */
        int counter = 0;
        for (int i = 0; i < Customer.getCounter(); i++) {
            counter += genelListe[i][indeks];
        }
        return (double) counter / (double) Customer.getCounter();
    }

    public static void ortalamaAlti(int[][] genelListe, String[] urunListesi, Customer[] customerList, String mod) {
        /*
        * İki boyulu int array şeklinde product list alır
        * listenin sütunlarını temsil için bir String array alır
        * listenin Customer'larını temsil için bir Customer array alır
        * ayrıca nelerin ortalamalarının alınacağına göre String türünde Mod girdisi alır (NC,IC)
        * Ortalama belirlemek için ortalama() metodu kullanılır.
        * Sonuç olarak ilgili ürüne ortalama altı not veren çeşidi belirtilmiş Customer'lar ekrana yazdırılır.
        */
        for (int j = 0; j < urunListesi.length; j++) {
            int sayac = 0;
            if (mod.equals("NC")){
                System.out.println(urunListesi[j] + " ürününe ortalamanın altında not veren ulusal müsteriler:\n");
            }
            else if (mod.equals("IC")){
                System.out.println(urunListesi[j] + " ürününe ortalamanın altında not veren uluslararası müsteriler:\n");
            }
            double urunOrt = ortalama(genelListe, j);
            for (int i = 0; i < customerList.length; i++) {
                if ((double) genelListe[i][j] < urunOrt && customerList[i] != null) {

                    if (mod.equals("NC")) {
                        if (customerList[i] instanceof NationalCustomer) {
                            System.out.println(customerList[i]);
                            sayac ++;
                        }
                    } else if (mod.equals("IC")) {
                        if (customerList[i] instanceof InternationalCustomer) {
                            System.out.println(customerList[i]);
                            sayac ++;
                        }
                    }
                }
            }
            if (sayac == 0){
                System.out.println("Hiçbir kullanıcı bulunamadı.");
            }
            System.out.println("\n*************************************************************************************\n");
        }
    }
    public static int mutlakDeger(int a, int b){
        /*
        * İki tamsayının mutlak değerini döndüren kısa bir metod.
        */
        if(a > b)
            return a - b;
        else
            return b - a;
    }
    public static int getSpesifikNumara(String mod){
        /*
        * Kullanıcıdan belirlia aralıklarda girdi istendiğinde kullanılmak ürezere oluşturulmuş metod.
        */
        int deger;
        if (mod.equals("Derece")) { //0-5 arası ürün puanlaması için
            deger = getInt("");
            if (deger < 0 || deger > 5) {
                System.out.print("Lütfen 0-5 arası değerler giriniz: ");
                return getSpesifikNumara("Derece");
            } else {
                return deger;
            }
        }else if(mod.equals("PlakaNo")){ // 1-81 arası plaka kodu için
            deger = getInt("Müşteri il plaka kodunu giriniz (1-81 arası): ");
            if (deger < 1 || deger > 81) {
                return getSpesifikNumara("PlakaNo");
            } else {
                return deger;
            }
        }
        return 0;
    }
    public static Customer musteriOlustur(){
        /*
        * Kullanıcıdan alınan bilgilerle NAtionalCustomer & InternationalCustomer oluşturmak için kullanılır.
        * Customer tipinde veri döndürür.
        */
        Customer newCustomer = null;
        String musteriTipi = getString("Lütfen müşteri tipini giriniz (i/n)");
        if (musteriTipi.equals("n")){
            newCustomer = new NationalCustomer(getInt("Müşteri ID si giriniz: "),getString("Müşteri adı giriniz: "),
                    getString("Müşteri soyadı giriniz: "), getSpesifikNumara("PlakaNo"),
                    getString("Müşterinin mesleğini giriniz: "));
        }else if (musteriTipi.equals("i")){
            newCustomer = new InternationalCustomer(getInt("Müşteri ID si giriniz: "),getString("Müşteri adı giriniz: "),
                    getString("Müşteri soyadı giriniz: "), getString("Müşterinin ülkesini giriniz: "),
                    getString("Müşterinin yaşadığı şehri giriniz: "));
        }
        return newCustomer;
    }

}






