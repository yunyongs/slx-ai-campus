package model.dto;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class MenuDTO2 {
    private int code;
    private String name;
    private int price;
    private int categoryName;
    private String orderableStatus;

}
