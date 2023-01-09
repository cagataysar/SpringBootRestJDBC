package com.garanti.SpringBootRestJDBC.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class DersDTO
{
    private Integer ID;

    @NonNull
    private String OGRETMEN;

    @NonNull
    private String KONU;
}