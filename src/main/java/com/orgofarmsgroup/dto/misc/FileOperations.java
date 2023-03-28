package com.orgofarmsgroup.dto.misc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileOperations implements Serializable {
    public static final long serialVersionUID = 6746536528L;

    private Boolean upload;
}
