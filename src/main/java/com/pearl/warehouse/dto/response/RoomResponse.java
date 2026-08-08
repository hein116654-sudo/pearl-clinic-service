package com.pearl.warehouse.dto.response;

import com.pearl.warehouse.exceptions.enums.RoomType;
import lombok.Data;

@Data
public class RoomResponse {

    private Long id;
    private String roomNo;
    private RoomType roomType;
    private Boolean status;

}
