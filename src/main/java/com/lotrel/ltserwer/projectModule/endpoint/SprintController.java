package com.lotrel.ltserwer.projectModule.endpoint;

import com.lotrel.ltserwer.lotrelCommons.common.ApiPath;
import com.lotrel.ltserwer.projectModule.domain.dto.SprintDto;
import com.lotrel.ltserwer.projectModule.infrastructure.mappers.SprintToDtoMapper;
import com.lotrel.ltserwer.projectModule.protocol.request.sprint.CreateSprintRequest;
import com.lotrel.ltserwer.projectModule.protocol.request.sprint.EditSprintRequest;
import com.lotrel.ltserwer.projectModule.service.SprintService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@AllArgsConstructor
public class SprintController {

    private final SprintService sprintService;

    @GetMapping(ApiPath.SprintPath.SPRINT_INFO)
    public SprintDto getSprintInfo(Long sprintId) {
        return SprintToDtoMapper.INSTANCE.map(sprintService.getSprint(sprintId));
    }

    @PostMapping(ApiPath.SprintPath.SPRINT_CREATE)
    public SprintDto createSprint(@RequestBody CreateSprintRequest request, Principal principal) {
        request.setPrincipal(principal);
        return SprintToDtoMapper.INSTANCE.map(sprintService.createSprint(request));
    }

    @PutMapping(ApiPath.SprintPath.SPRINT_EDIT)
    public SprintDto editSprint(@RequestBody EditSprintRequest request, Principal principal) {
        request.setPrincipal(principal);
        return SprintToDtoMapper.INSTANCE.map(sprintService.editSprintDetails(request));
    }
}
