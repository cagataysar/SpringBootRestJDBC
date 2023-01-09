package com.garanti.SpringBootRestJDBC.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Ogrenci
{
    private Integer ID;

    @NonNull
    private String NAME;

    @NonNull
    private int OGR_NUMBER;

    private Integer YEAR;
}
