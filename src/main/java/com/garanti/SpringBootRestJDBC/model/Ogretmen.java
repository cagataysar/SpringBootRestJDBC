package com.garanti.SpringBootRestJDBC.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Ogretmen
{
    private Integer ID;

    @NonNull
    private String NAME;

    @NonNull // bu constructor'da olacak diyoruz. @RequiredArgsConstructor için
    private boolean IS_GICIK;
}