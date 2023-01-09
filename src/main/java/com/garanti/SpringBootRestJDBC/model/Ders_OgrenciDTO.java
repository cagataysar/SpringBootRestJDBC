package com.garanti.SpringBootRestJDBC.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ders_OgrenciDTO
{
    private Integer ID;

    private String OGRETMEN_NAME;

    private String KONU;

    private String OGRENCI_NAME;

    private Integer NOTE;

    private Integer DEVAMSIZLIK;
}
