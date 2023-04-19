package com.work.ymy.company.payload.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CompanyRequest {

    private Long id;

    private Long userId;

    @NotNull(message = "Firma kodu boş olamaz!")
    @NotEmpty(message = "Firma kodu boş olamaz!")
    @Pattern(regexp="(^$|[0-9]{10})", message = "Firma kodu 1 ile 9 aresında 10 karakter değer almalıdır.")
    private String code;

    @NotNull(message = "Firma ismi boş olamaz!")
    @NotEmpty(message = "Firma ismi boş olamaz!")
    @Size(min = 1, max = 150, message = "Firma ismi minimum 1, maksimum 150 karakter olmalıdır!")
    private String name;

    @Size(max = 150, message = "Firma yetkilisi maksimum 150 karakter olmalıdır!")
    private String authorisedPerson;

    @NotNull(message = "Firma telefonu boş olamaz!")
    @NotEmpty(message = "Firma telefonu boş olamaz!")
    private String phone;

    @Size(max=50)
    @Pattern(regexp="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @Size(max = 50, message = "Vergi dairesi maksimum 50 karakter olmalıdır!")
    private String taxOffice;

    @Size(max = 11, message = "Vergi dairesi numarası maksimum 11 karakter olmalıdır!")
    @Pattern(regexp="(^$|[0-9]{11})",message = "Vergi numarası 11 karakter olmalıdır!")
    private String taxNumber;

    @Size(max = 50, message = "İl maksimum 50 karakter olmalıdır!")
    private String city;

    @Size(max = 50, message = "İlçe maksimum 50 karakter olmalıdır!")
    private String town;

    private String address;
}
