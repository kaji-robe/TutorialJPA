package com.techacademy;

import java.util.List;
import java.util.Optional; // 追加

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // 追加

@Service
public class CountryService {
    private final CountryRepository repository;

    public CountryService(CountryRepository repository) {
        this.repository = repository;
    }

    // 全件を検索して返す
    public List<Country> getCountryList() {
        // リポジトリのfindAllメソッドを呼び出す
        return repository.findAll();
    }





    // ----- 追加:ここから -----
    // 1件を検索して返す
    public Country getCountry(String code) {
        // findByIdで検索
        Optional<Country> option = repository.findById(code);
        // 取得できなかった場合はnullを返す
        Country country = option.orElse(null);
        return country;
    }

    // 更新（追加）を行なう
    @Transactional
    public void updateCountry(String code, String name, int population) {
        Country country = new Country(code, name, population);
        repository.save(country);
    }

    // 削除画面用の全件を検索して返す
    public Country getDeleteCountryList(String code) {
       // findByIdで検索
        Optional<Country> option = repository.findById(code);
        // 取得できなかった場合はnullを返す
        Country country = option.orElse(null);
        return country;
     }



    // 削除を行なう
    @Transactional
    public void deleteCountry(String code) {
        repository.deleteById(code);
    }
    // ----- 追加:ここまで -----
}
