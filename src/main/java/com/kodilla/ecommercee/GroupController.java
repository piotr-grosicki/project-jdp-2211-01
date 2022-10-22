package com.kodilla.ecommercee;

import com.kodilla.ecommercee.domain.GroupDto;
import com.kodilla.ecommercee.exceptions.GroupNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/v1/groups")
public class GroupController {

    @GetMapping
    public List<GroupDto> getGroups() {
        return Arrays.asList(GroupDto.builder()
                        .id(1l)
                        .name("Test group")
                        .description("Group used only for test")
                        .build(),
                GroupDto.builder()
                        .id(2l)
                        .name("Test group 2")
                        .description("Group used only for test")
                        .build());
    }

    @GetMapping(value = "/{groupId}")
    public GroupDto getGroup(@PathVariable Long groupId) throws GroupNotFoundException {
        return GroupDto.builder()
                .id(groupId)
                .name("Test group")
                .description("Group used only for test")
                .build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public GroupDto updateGroup(@RequestBody GroupDto groupDto) throws GroupNotFoundException {
        return groupDto;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public GroupDto createGroup(@RequestBody GroupDto groupDto) {
        return groupDto;
    }
}
