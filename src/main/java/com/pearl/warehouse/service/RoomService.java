package com.pearl.warehouse.service;

import com.pearl.warehouse.dto.input.RoomInput;
import com.pearl.warehouse.dto.response.RoomResponse;
import com.pearl.warehouse.exceptions.BadRequestException;
import com.pearl.warehouse.mapper.RoomMapper;
import com.pearl.warehouse.model.Room;
import com.pearl.warehouse.repository.RoomRepository;
import com.pearl.warehouse.repository.specification.RoomSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("id", "roomNo", "roomType", "status");

    public RoomResponse createRoom(RoomInput roomInput) {

        //duplicate check
        if (roomRepository.existsByRoomNo(roomInput.roomNo())) {
            throw new IllegalArgumentException("Room number '" + roomInput.roomNo() + "' already exists.");
        }

        Room room = roomMapper.toEntity(roomInput);

        Room saveRoom = roomRepository.save(room);

        return roomMapper.toResponse(saveRoom);
    }
    public List<RoomResponse> getAllRooms() {
        return roomRepository.findAll().stream()
                .map(roomMapper::toResponse)
                .collect(Collectors.toList());
    }
    public RoomResponse getRoomById (Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Room not found with id: " + id));
        return roomMapper.toResponse(room);
    }
    public RoomResponse updateRoom(Long id, RoomInput roomInput) {
        Room existingRoom = roomRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Room not found with id:" + id));
        if(!existingRoom.getRoomNo().equals(roomInput.roomNo())
            && roomRepository.existsByRoomNo(roomInput.roomNo())) {
            throw new BadRequestException("Room number '" + roomInput.roomNo() + "' already exists.");
        }
        existingRoom.setRoomNo(roomInput.roomNo());
        existingRoom.setRoomType(roomMapper.stringToRoomType(roomInput.roomType()));
        existingRoom.setStatus(roomInput.isAvailable());

        Room updatedRoom = roomRepository.save(existingRoom);
        return roomMapper.toResponse(updatedRoom);
    }
    public void deleteRoom(Long id) {
        if(!roomRepository.existsById(id)) {
            throw new BadRequestException("Room not found with id:" + id);
        }
        roomRepository.deleteById(id);
    }
    public Page<RoomResponse> getAllRoomListPaging (
            int page,
            int size,
            String search,
            Boolean status,
            String sortBy,
            String direction
    ) {
        if (!ALLOWED_SORT_FIELDS.contains(sortBy)) {
            sortBy = "id";
        }

        Sort sort = "DESC".equalsIgnoreCase(direction)
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Specification<Room> spec = Specification.allOf(
                RoomSpecification.search(search),
                RoomSpecification.hasStatus(status)
        );

        Page<Room> roomPage = roomRepository.findAll(spec, pageable);
        return roomPage.map(roomMapper::toResponse);
    }
}
