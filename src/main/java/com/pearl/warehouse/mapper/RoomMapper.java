package com.pearl.warehouse.mapper;

import com.pearl.warehouse.dto.input.RoomInput;
import com.pearl.warehouse.dto.response.RoomResponse;
import com.pearl.warehouse.model.Room;
import com.pearl.warehouse.exceptions.enums.RoomType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", source = "isAvailable")
    Room toEntity(RoomInput roomInput);

    RoomResponse toResponse(Room entity);

    @Named("stringToRoomType")
    default RoomType stringToRoomType(String roomType) {
        if (roomType == null) return null;
        try {
            return RoomType.valueOf(roomType.toUpperCase().trim());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid room type: " + roomType);
        }
    }

    @Named("roomTypeToString")
    default String roomTypeToString(RoomType roomType) {
        return roomType != null ? roomType.name() : null;
    }
}