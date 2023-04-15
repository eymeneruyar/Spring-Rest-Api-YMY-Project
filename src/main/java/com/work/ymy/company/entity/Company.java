package com.work.ymy.company.entity;

import com.work.ymy.company.utility.DateUtil;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean status = true;

    private String date = DateUtil.getDateFormatYYYYMMDD();

    private long userId;

    @NotNull(message = "Firma kodu boş olamaz!")
    @NotEmpty(message = "Firma kodu boş olamaz!")
    @Pattern(regexp="(^$|[0-9]{10})")
    private String code;

    @NotNull(message = "Firma ismi boş olamaz!")
    @NotEmpty(message = "Firma ismi boş olamaz!")
    @Size(min = 1, max = 150, message = "Firma ismi minimum 1, maksimum 150 karakter olmalıdır!")
    @Column(length = 150)
    private String name;

    @Size(max = 150, message = "Firma yetkilisi maksimum 150 karakter olmalıdır!")
    @Column(length = 150)
    private String authorisedPerson;

    @NotNull(message = "Firma telefonu boş olamaz!")
    @NotEmpty(message = "Firma telefonu boş olamaz!")
    //@Pattern(regexp="(^$|[0-9]{20})",message = "Girilen telefon formatı (###-###-####) olmalıdır!")
    @Column(length = 12)
    private String phone;

    @Size(max=50)
    @Pattern(regexp="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    @Column(length = 50)
    private String email;

    @Size(max = 50, message = "Vergi dairesi maksimum 50 karakter olmalıdır!")
    @Column(length = 50)
    private String taxOffice;

    @Size(max = 11, message = "Vergi dairesi numarası maksimum 11 karakter olmalıdır!")
    @Pattern(regexp="(^$|[0-9]{11})",message = "Vergi numarası 11 karakter olmalıdır!")
    @Column(length = 11)
    private String taxNumber;

    @Size(max = 50, message = "İl maksimum 50 karakter olmalıdır!")
    @Column(length = 50)
    private String city; //İl

    @Size(max = 50, message = "İlçe maksimum 50 karakter olmalıdır!")
    @Column(length = 75)
    private String town; //İlçe

    @Column(columnDefinition = "TEXT")
    private String address;

}
