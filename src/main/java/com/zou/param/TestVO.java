package com.zou.param;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
public class TestVO {


    @NotBlank
    private String msg;

    @NotNull(message = "id不能为空")
    @Max(value = 10, message = "id不能大于200")
    @Min(value = 0, message = "id至少大于等于0")
    private Integer id;

    @NotEmpty
    private List<String> str;
}
