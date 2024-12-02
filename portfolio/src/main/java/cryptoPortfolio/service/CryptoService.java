package cryptoPortfolio.service;

import cryptoPortfolio.model.Crypto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class CryptoService {

    public List<Crypto> cryptoData = new ArrayList<>();
    public Crypto bitcoin = new Crypto(1, "Bitcoin", "BTC", 91630.54, 300.0);
    public Crypto ethereum = new Crypto(2, "Ethereum", "ETH", 3325.28, 200.0);
    public Crypto monero = new Crypto(3, "Monero", "XMR", 160.69, 1600.0);

    CryptoService () {
        cryptoData.add(bitcoin);
        cryptoData.add(ethereum);
        cryptoData.add(monero);
    }


    public void addCrypto(Crypto crypto) {
        cryptoData.add(crypto);
    }

    public void getAllCryptos() {
//        for (int i =0; i< cryptoData.size(); i++) {
//            cryptoData.forEach(System.out::println);
//        }
    }

    public List<Crypto> sortName() {
        cryptoData.sort(Comparator.comparing(Crypto::getName));
        return cryptoData;
    }

    public List<Crypto> sortPrice() {
        cryptoData.sort(Comparator.comparing(Crypto::getPrice));
        return cryptoData;
    }

    public List<Crypto> sortQuantity() {
        cryptoData.sort(Comparator.comparing(Crypto::getQuantity));
        return cryptoData;
    }

    public double getAllCryptosValue() {
        double sum = 0;
            for (Crypto crypto: cryptoData) {
                sum += crypto.getPrice()* crypto.getQuantity();
            }
        return sum;
    }
}
