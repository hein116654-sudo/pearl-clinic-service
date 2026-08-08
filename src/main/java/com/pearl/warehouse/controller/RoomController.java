package com.pearl.warehouse.controller;

import com.pearl.warehouse.dto.api.ApiResponse;
import com.pearl.warehouse.dto.api.Pagination;
import com.pearl.warehouse.dto.input.RoomInput;
import com.pearl.warehouse.dto.response.RoomResponse;
import com.pearl.warehouse.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")

public class RoomController {

    private final RoomService roomService;

    @PostMapping("/save")
    public ApiResponse<RoomResponse> saveRoom (@Valid @RequestBody RoomInput roomInput) {
        return ApiResponse.success(
            roomService.createRoom(roomInput),
                "Room created successfully"
        );
    }
    @GetMapping("/list")
    public ApiResponse<List<RoomResponse>> getAllRooms() {
        return ApiResponse.success(
                roomService.getAllRooms(),
                "Room data fetched Successfully"
        );
    }
    @PutMapping("/update/{id}")
    public ApiResponse<Boolean> updateRoom(@PathVariable Long id, @Valid @RequestBody RoomInput roomInput) {
        roomService.updateRoom(id, roomInput);
        return ApiResponse.success(true,"Room updated Successfully");
    }
    @DeleteMapping("/delete/{id}")
    public ApiResponse<Boolean> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ApiResponse.success(true,"Room deleted Successfully");
    }
    @GetMapping("list-paging")
    public ApiResponse<List<RoomResponse>> getAllRooms(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Boolean status,
            @RequestParam(defaultValue = "id", required = false) String sortBy,
            @RequestParam(defaultValue = "DESC", required = false) String direction
    ) {
        Page<RoomResponse> roomResponsePage = roomService.getAllRoomListPaging(page, size, search, status, sortBy, direction);

        Pagination pagination = new Pagination(
                roomResponsePage.getNumber(),
                roomResponsePage.getSize(),
                roomResponsePage.getTotalElements(),
                roomResponsePage.getTotalPages(),
                roomResponsePage.hasNext(),
                roomResponsePage.hasPrevious()
        );

        ApiResponse<List<RoomResponse>> response = ApiResponse.success(
                roomResponsePage.getContent(),
                "Rooms retrieved successfully"
        );
        response.setPagination(pagination);

        return response;
    }
}
