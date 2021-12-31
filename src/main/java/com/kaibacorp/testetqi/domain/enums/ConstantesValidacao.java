package com.kaibacorp.testetqi.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConstantesValidacao {
    DIAS_MAX(90),
    PARCELAS_MAX(60);

    private final int values;

}
