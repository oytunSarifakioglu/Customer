package com.Dank;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        //Dosya açılır.
        Scanner input = null;
        try {
            input = new Scanner(new File("Firma.txt"));
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(0);
        }
        StringTokenizer stringTokenizer = new StringTokenizer(input.nextLine(), ",");

        int urunSayisi = Integer.parseInt(stringTokenizer.nextToken());

        String[] urunIsımleri = new String[urunSayisi]; //productList

        int count = stringTokenizer.countTokens();

        for (int i = 0; i < count; i++) {
            // Urun listesi text dosyasının ilk satırındaki verilerle doldurulur.
            urunIsımleri[i] = stringTokenizer.nextToken();
        }

        int[][] genelListe = new int[200][urunSayisi]; //ratingsList
        Customer[] customerList = new Customer[200]; //customerList

        while (input.hasNext()) {
            Customer newCustomer = null;

            stringTokenizer = new StringTokenizer(input.nextLine(), ",");

            String cesit = stringTokenizer.nextToken();

            if (cesit.equals("n")) {
                //NationalCustomer oluşturulur
                newCustomer = new NationalCustomer(Integer.parseInt(stringTokenizer.nextToken()),
                        stringTokenizer.nextToken(), stringTokenizer.nextToken(),
                        Integer.parseInt(stringTokenizer.nextToken()), stringTokenizer.nextToken());

                stringTokenizer = new StringTokenizer(input.nextLine(), ",");

                for (int i = 0; i < urunSayisi; i++) {
                    // oluturulan Customer için puanlamalar listeye girilir.
                    genelListe[Customer.getCounter() - 1][i] = Integer.parseInt(stringTokenizer.nextToken());
                }

            } else {
                //InternationalCustomer oluşturulur.
                //Text dosyasında n veya i olacağı beklendiğinden n olmadığı durumda i var sayılacaktır.
                newCustomer = new InternationalCustomer(Integer.parseInt(stringTokenizer.nextToken()),
                        stringTokenizer.nextToken(), stringTokenizer.nextToken(), stringTokenizer.nextToken(),
                        stringTokenizer.nextToken());

                stringTokenizer = new StringTokenizer(input.nextLine(), ",");
                for (int i = 0; i < urunSayisi; i++) {
                    // oluturulan Customer için puanlamalar listeye girilir.
                    genelListe[Customer.getCounter() - 1][i] = Integer.parseInt(stringTokenizer.nextToken());
                }

            }
            //// oluturulan Customer CustomerLsit e atılır.
            customerList[Customer.getCounter() - 1] = newCustomer;
        }


//      MADDE 1)
        System.out.println("Her bir ürüne ait ortalama dercelendirme puanları: \n");
        Tools.ortalamaDerece(genelListe,urunIsımleri,customerList,"C");
//      MADDE 2)
        System.out.println("\nHer bir ürün için ulusal müşterilerin ortalama puanları: \n");
        Tools.ortalamaDerece(genelListe,urunIsımleri,customerList,"NC");
//      MADDE 3)
        System.out.println("\nHer bir ürün için uluslararası müşterilerin ortalama puanları: \n");
        Tools.ortalamaDerece(genelListe,urunIsımleri,customerList,"IC");
//      MADDE 4)
        System.out.println("\nHer bir ürün için mesleği doktor olan müşterilerin ortalama puanları: \n");
        Tools.ortalamaDerece(genelListe,urunIsımleri,customerList,"D");
        System.out.println();
//      MADDE 5)
        Tools.ortalamaAlti(genelListe,urunIsımleri,customerList,"NC");
//      MADDE 6)
        Tools.ortalamaAlti(genelListe,urunIsımleri,customerList,"IC");

        int klavyeGiris = Customer.getCounter();//Klavyeden girilecek Customer nesnelerinin başlangıç indeksi olacak.

//      MADDE 7)

        while(Customer.getCounter() <200){

//          a) Klavyeden yeni kullanıcılar oluşturulur, sonra her bir kullanıcı için ürün puanlamaları alınır:

            int indeks = Customer.getCounter();
            customerList[indeks] = Tools.musteriOlustur();

            System.out.println("Oluşturulan müşteri için; ");
            for (int i = 0; i < urunSayisi - 1; i++){
                System.out.print((i+1) + ". ürün puanlamasını giriniz: ");
                genelListe[indeks][i] = Tools.getSpesifikNumara("Derece");
            }

//          b) En son ürün puanı önceden puan vermiş kullanıcıların puanları kullanılarak hesaplanır:
            int min = 1000;
            double aynıBenzerlikSayac = 0.0;
            int esitSayisi = 0;
            for (int i = 0; i < indeks; i++){

                int benzerlikDegeri = 0;

                for(int j = 0; j < urunSayisi - 1; j++){
                    benzerlikDegeri += Tools.mutlakDeger(genelListe[i][j] , genelListe[indeks][j]);
                }

                if ( benzerlikDegeri < min){
                    min = benzerlikDegeri;
                    genelListe[indeks][urunSayisi - 1] = genelListe[i][urunSayisi - 1];
                    aynıBenzerlikSayac = genelListe[indeks][urunSayisi - 1];
                    esitSayisi = 1;
                }else if( benzerlikDegeri == min){
                    esitSayisi++;
                    aynıBenzerlikSayac += genelListe[i][urunSayisi - 1];
                    genelListe[indeks][urunSayisi -1] = (int) Math.round(aynıBenzerlikSayac/(double)esitSayisi);


                }
            }

            String devamMi = Tools.getString("Çıkmak İçin h/H'ye Devam Etmek İçin Herhangi Bir Tuşa Basınız: ");
            if (devamMi.equals("h") || devamMi.equals("H")) {
                break;
            }
        }

//      MADDE 8)
//          a)Klavyeden griş yapılarak oluşturulan kullanıcıların bilgileri:
        int[][] klavyedenGirilen = new int[Customer.getCounter() - klavyeGiris + 1][urunSayisi];
        System.out.println("\nKlavyeden griş yapılarak oluşturulan kullanıcıların bilgileri:\n");
        int j = 0;
        int custmerNumber = Customer.getCounter();
        Customer.setCounter(custmerNumber - klavyeGiris);
        for (int i = klavyeGiris; i < custmerNumber; i++){
            System.out.println(customerList[i]);
            for (int k = 0; k < urunSayisi; k++){
                klavyedenGirilen[j][k] = genelListe[i][k];
            }
            j++;
        }
        System.out.println("\n*************************************************************************************\n");

//          b) Klayveden girilen bilgilerle oluşturulan müşterilerin ürünlere verdiği not ortalamaları:

        System.out.println("Sadece klavyeden girilerek oluşturulan kullanıcıların her bir ürün için not ortalamaları:\n");
        Tools.ortalamaDerece(klavyedenGirilen,urunIsımleri,customerList,"C");


        System.out.println("\n<------------------------------------PROGRAM SONU----------------------------------->\n");
    }
}
