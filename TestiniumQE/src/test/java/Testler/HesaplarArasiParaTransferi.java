package Testler;

import Sayfalar.*;
import org.junit.jupiter.api.*;
import org.testng.annotations.*;
import org.junit.jupiter.api.AfterEach; //AfterEach annotation kullanimi icin "junit-jupiter-api" isimli Maven dependency pom.xml'e eklendi
import org.junit.jupiter.api.BeforeEach; //BeforeEach annotation kullanimi icin "junit-jupiter-api" isimli Maven dependency pom.xml'e eklendi
import org.junit.jupiter.api.Test; //Test annotation kullanimi icin "junit-jupiter-api" isimli Maven dependency pom.xml'e eklendi
//import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.*; // WebDriver kullanmak icin
import org.openqa.selenium.chrome.ChromeDriver; // chrome driver kullanabilmek icin "selenium-chrome-driver" isimli Maven dependency pom.xml'e eklendi
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait; // WebDriverWait kullanabilmek icin "selenium-support" isimli Maven dependency pom.xml'e eklendi.
import org.openqa.selenium.support.ui.ExpectedConditions; // ExpectedConditions kullanabilmek icin "selenium-support" isimli Maven dependency pom.xml'e eklendi.
//import org.testng.annotations.Test;
//import java.io.IOException;
import javax.swing.plaf.TableHeaderUI;
import java.time.Duration; //dinamic wait methodunu kullanabilmek için eklendi.

public class HesaplarArasiParaTransferi {

    WebDriver driver;
    private LoginSayfasi loginSayfasi;
    private OpenSayfasi openSayfasi;
    private AnaSayfa anaSayfa;
    private TransferMoney transferMoney;

    @BeforeEach
    void setup(){
        driver = new ChromeDriver();

        //sayfaların nesneleri oluşturuldu
        loginSayfasi = new LoginSayfasi(driver);
        openSayfasi = new OpenSayfasi(driver);
        anaSayfa = new AnaSayfa(driver);
        transferMoney = new TransferMoney(driver);

        driver.manage().window().maximize(); //açılan chrome sayfasını tam ekran boyutu yapmak için kullanıldı.
        driver.get("https://catchylabs-webclient.testinium.com/"); //uygulama sitesine git

    }

    @Test
    void HesaplarArasiParaTransferi_Testinium1() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20L)); //bu method sayesinde static wait olan thread yerine dinamic wait kullanıyoruz.

        System.out.println("HesaplarArasiParaTransferi_Testinium1 testi basladi.");

                loginSayfasi.ElementGozukeneKadarBekleCssSelector(loginSayfasi.loginButton); //login butonunu görene kadar bekle
                loginSayfasi.yaziGirCssSelector(loginSayfasi.username,"burak.tigli"); //username gir
                loginSayfasi.yaziGirCssSelector(loginSayfasi.password,"Catchylabs.3568"); //password gir
                loginSayfasi.tusaBasCssSelector(loginSayfasi.loginButton); // login butonuna tıkla

                openSayfasi.ElementGozukeneKadarBekleCssSelector(openSayfasi.openSayfasi); // sayfa açılana kadar bekle
                String ilkSayfaMesaji = openSayfasi.yaziyiKaydetCssSelector(openSayfasi.challengeNameYazisi); // ilk sayfadaki challenge name'i kaydet
                Assertions.assertEquals("Challenge name: QA ve/veya QE Uygulaması 28.01.25",ilkSayfaMesaji);//sayfanın açıldığını anlayabilmek için beklenen yazı ile çıkan sonuçu kıyasla
                System.out.println("    ilk sayfanin acildigi dogrulandi ->> " + ilkSayfaMesaji);
                openSayfasi.tusaBasCssSelector(openSayfasi.openMoneyTransferButton); // open money transfer butonuna tıkla

                anaSayfa.ElementGozukeneKadarBekleCssSelector(anaSayfa.transferMoneyButton); // transfer money butonunu görene kadar bekle
                String oldAmountPart1 = anaSayfa.yaziyiKaydetCssSelector(anaSayfa.amount).substring(0,3); // ana sayfadaki amount alanının ilk 3 hanesini kaydet
                String oldAmountPart2 = anaSayfa.yaziyiKaydetCssSelector(anaSayfa.amount).substring(4,7); // ana sayfadaki amount alanının virgülden sonraki 3 hanesini kaydet
                String totalOldAmount = oldAmountPart1+oldAmountPart2; //kaydedilen 2 sayıyı birleştir. böylece virgülden kurtulmuş oldum. elde edilen String değerli sayıyı int e dönüştürebileceğim
            Integer totalOldAmountInt = Integer.valueOf(totalOldAmount); // String veri tipli sayıyı integer a çevir
            System.out.println("    oldAmount: " + totalOldAmountInt); //oldAmount değerini yazdır
                anaSayfa.tusaBasCssSelector(anaSayfa.transferMoneyButton); //transfer money butonuna tıkla

                transferMoney.ElementGozukeneKadarBekleCssSelector(transferMoney.transferAmount); //ilgili ekran açılana kadar bekle
                transferMoney.yaziGirCssSelector(transferMoney.transferAmount,"1000"); //amount gir
                transferMoney.tusaBasCssSelector(transferMoney.transferSendButton); // send butonuna tıkla
                System.out.println("1000 dolar transfer gerceklesti.");

                anaSayfa.ElementGozukeneKadarBekleCssSelector(anaSayfa.amount); //amount gözükene kadar bekle
                Thread.sleep(2000);
                String newAmountPart1 = anaSayfa.yaziyiKaydetCssSelector(anaSayfa.amount).substring(0,3); // ana sayfadaki amount alanının ilk 3 hanesini kaydet
                String newAmountPart2 = anaSayfa.yaziyiKaydetCssSelector(anaSayfa.amount).substring(4,7); // ana sayfadaki amount alanının virgülden sonraki 3 hanesini kaydet
                String totalNewAmount = newAmountPart1+newAmountPart2; //kaydedilen 2 sayıyı birleştir. böylece virgülden kurtulmuş oldum. elde edilen String değerli sayıyı int e dönüştürebileceğim
            Integer totalNewAmountInt = Integer.valueOf(totalNewAmount); // String veri tipli sayıyı integer a çevir
            System.out.println("    newAmount: " + totalNewAmountInt); //newAmount değerini yazdır
                Assertions.assertEquals(totalOldAmountInt-1000,totalNewAmountInt);// old amount ile new amount u kıyasla.


    }


    @Test
    void HesaplarArasiParaTransferi_Testinium2() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20L)); //bu method sayesinde static wait olan thread yerine dinamic wait kullanıyoruz.

        System.out.println("HesaplarArasiParaTransferi_Testinium2 testi basladi.");
                loginSayfasi.ElementGozukeneKadarBekleCssSelector(loginSayfasi.loginButton); //login butonunu görene kadar bekle
                loginSayfasi.yaziGirCssSelector(loginSayfasi.username,"burak.tigli"); //username gir
                loginSayfasi.yaziGirCssSelector(loginSayfasi.password,"Catchylabs.3568"); //password gir
                loginSayfasi.tusaBasCssSelector(loginSayfasi.loginButton); // login butonuna tıkla

                openSayfasi.ElementGozukeneKadarBekleCssSelector(openSayfasi.openSayfasi); // sayfa açılana kadar bekle
                String ilkSayfaMesaji = openSayfasi.yaziyiKaydetCssSelector(openSayfasi.challengeNameYazisi); // ilk sayfadaki challenge name'i kaydet
                Assertions.assertEquals("Challenge name: QA ve/veya QE Uygulaması 28.01.25",ilkSayfaMesaji);//sayfanın açıldığını anlayabilmek için beklenen yazı ile çıkan sonuçu kıyasla
                System.out.println("    ilk sayfanin acildigi dogrulandi ->> " + ilkSayfaMesaji);
                openSayfasi.tusaBasCssSelector(openSayfasi.openMoneyTransferButton); // open money transfer butonuna tıkla

                anaSayfa.ElementGozukeneKadarBekleCssSelector(anaSayfa.transferMoneyButton); // transfer money butonunu görene kadar bekle
                String oldAmountPart1 = anaSayfa.yaziyiKaydetCssSelector(anaSayfa.amount).substring(0,3); // ana sayfadaki amount alanının ilk 3 hanesini kaydet
                String oldAmountPart2 = anaSayfa.yaziyiKaydetCssSelector(anaSayfa.amount).substring(4,7); // ana sayfadaki amount alanının virgülden sonraki 3 hanesini kaydet
                String totalOldAmount = oldAmountPart1+oldAmountPart2; //kaydedilen 2 sayıyı birleştir. böylece virgülden kurtulmuş oldum. elde edilen String değerli sayıyı int e dönüştürebileceğim
            Integer totalOldAmountInt = Integer.valueOf(totalOldAmount); // String veri tipli sayıyı integer a çevir
            System.out.println("    oldAmount: " + totalOldAmountInt); //oldAmount değerini yazdır
                anaSayfa.tusaBasCssSelector(anaSayfa.transferMoneyButton); //transfer money butonuna tıkla

                transferMoney.ElementGozukeneKadarBekleCssSelector(transferMoney.transferAmount); //ilgili ekran açılana kadar bekle
                transferMoney.tusaBasCssSelector(transferMoney.receiveraccount); //recevier account a tıkla
                transferMoney.yaziGirCssSelector(transferMoney.receiveraccount,"Testinium-2"); //testinium-2 i seç
                Thread.sleep(2000);
                System.out.println("receiver account secimi yapildiginde web sitesinin verdigi hatadan dolayi otomasyon islemine devam edilemiyor.");
                System.out.println("verilen hata: Something went wrong. Please try again!");
                transferMoney.yaziGirCssSelector(transferMoney.transferAmount,"1000"); //amount gir
                transferMoney.tusaBasCssSelector(transferMoney.transferSendButton); // send butonuna tıkla
                System.out.println("1000 dolar transfer gerceklesti.");

                anaSayfa.ElementGozukeneKadarBekleCssSelector(anaSayfa.amount); //amount gözükene kadar bekle
                Thread.sleep(2000);
                String newAmountPart1 = anaSayfa.yaziyiKaydetCssSelector(anaSayfa.amount).substring(0,3); // ana sayfadaki amount alanının ilk 3 hanesini kaydet
                String newAmountPart2 = anaSayfa.yaziyiKaydetCssSelector(anaSayfa.amount).substring(4,7); // ana sayfadaki amount alanının virgülden sonraki 3 hanesini kaydet
                String totalNewAmount = newAmountPart1+newAmountPart2; //kaydedilen 2 sayıyı birleştir. böylece virgülden kurtulmuş oldum. elde edilen String değerli sayıyı int e dönüştürebileceğim
            Integer totalNewAmountInt = Integer.valueOf(totalNewAmount); // String veri tipli sayıyı integer a çevir
            System.out.println("    newAmount: " + totalNewAmountInt); //newAmount değerini yazdır
                Assertions.assertEquals(totalOldAmountInt-1000,totalNewAmountInt);// old amount ile new amount u kıyasla.

    }


    @AfterEach
    void teardown () {

        driver.quit();
    }
}