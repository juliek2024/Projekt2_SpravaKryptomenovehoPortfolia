package cryptoPortfolio.controller;

import cryptoPortfolio.model.Crypto;
import cryptoPortfolio.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
public class CryptoController {

    @Autowired
    CryptoService cryptoService;

    List<Crypto> cryptoData = new ArrayList<>();

    @PostMapping("/cryptos/{crypto}/work")
    public ResponseEntity<String> addCryptoData(@RequestBody Crypto crypto) {
        cryptoData.add(crypto);
        return new ResponseEntity("Kryptoměna byla uložena", ACCEPTED);
    }

    @GetMapping("/cryptos")
    public List<Crypto> getAllCryptosSorted(@RequestParam (required = false) String sort) {
        if (sort != null && sort.equalsIgnoreCase("quantity")) {
            return cryptoService.sortQuantity();
        } else if (sort != null && sort.equalsIgnoreCase("price")) {
            return cryptoService.sortPrice();
        } else if (sort != null && sort.equalsIgnoreCase("name")) {
            return cryptoService.sortName();
        }
        return cryptoData;
    }

    @GetMapping("/cryptos/{id}")
    public ResponseEntity<Crypto> getCryptoOnID(@PathVariable("id") int ID) {
        for (Crypto crypto : cryptoData) {
            if (crypto.getID() == ID) {
            }
            return new ResponseEntity<>(cryptoData.get(ID - 1), FOUND);
        }
        return new ResponseEntity<>(NOT_FOUND);
    }

    @PutMapping("/cryptos/{id}")
    public ResponseEntity<Crypto> updateCryptoOnID(@PathVariable("id") int ID, @RequestBody Crypto updateCrypto) {
        for (int i = 0; i < cryptoData.size(); i++) {
            Crypto crypto = cryptoData.get(i);
            if (crypto.getID() == ID) {
                crypto.setName(updateCrypto.getName());
                crypto.setSymbol(updateCrypto.getSymbol());
                crypto.setPrice(updateCrypto.getPrice());
                crypto.setQuantity(updateCrypto.getQuantity());
            }
        }
        return new ResponseEntity("Kryptoměna byla upravena", OK);
    }

    @GetMapping("/portfolio-value")
    public ResponseEntity<String> portfolioValue() {
        if (!cryptoData.isEmpty()) {
            double portfolioValue = cryptoService.getAllCryptosValue();
            return new ResponseEntity<>( "Hodnota portfolia celkem je: " + Math.floor(portfolioValue)+ " EUR (zaokruhleno na celé jednotky dolu).", OK);
        }
        return new ResponseEntity<>("Portfolio je prazdné", NOT_FOUND);
    }
}







