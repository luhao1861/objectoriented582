package com.csun.objectoriented582.system.dto;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class PasswordDto implements Serializable {

    @NotBlank(message = "New password cannot be null")
    private String password;

    @NotBlank(message = "Old password cannot be null")
    private String currentPass;
}