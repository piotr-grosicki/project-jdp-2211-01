package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.entity.Group;
import com.kodilla.ecommercee.exception.GroupNotFoundException;
import com.kodilla.ecommercee.mapper.GroupMapper;
import com.kodilla.ecommercee.service.GroupService;
import com.kodilla.ecommercee.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/groups")
@RequiredArgsConstructor
public class GroupController {
    private final SecurityService securityService;
    private final GroupService groupService;
    private final GroupMapper groupMapper;

    @GetMapping
    public ResponseEntity<List<GroupDto>> getGroups() {
        return ResponseEntity.ok(groupService.getAll().stream()
                .map(groupMapper::mapToDto).collect(Collectors.toList())
        );
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GroupDto> getGroup(@PathVariable Long id) throws GroupNotFoundException {
        return ResponseEntity.ok(
                groupMapper.mapToDto(
                        groupService.getGroup(id)
                )
        );
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateGroup(@RequestBody GroupDto groupDto) throws GroupNotFoundException {
        securityService.authorize();
        groupService.update(groupMapper.mapToEntity(groupDto));
        return ResponseEntity.ok(null);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto groupDto) {
        securityService.authorize();
        Group group = groupMapper.mapToEntity(groupDto);
        GroupDto savedGroup = groupMapper.mapToDto(groupService.create(group));
        return ResponseEntity.ok(savedGroup);
    }
}
