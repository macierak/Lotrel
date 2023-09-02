package com.lotrel.ltserwer.projectModule.infrastructure.validators;

import com.lotrel.ltserwer.lotrelCommons.common.exception.ValidationException;
import com.lotrel.ltserwer.projectModule.model.Sprint;
import com.lotrel.ltserwer.projectModule.protocol.request.sprint.CreateSprintRequest;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Objects;

@Service
public class ValidateSprintRequests {

    public void request(CreateSprintRequest request) {

    }

    public static void validateDateInSprintRequest(Sprint sprint, OffsetDateTime begin, OffsetDateTime end) {
        boolean validationResult;

        if (Objects.isNull(end) && Objects.nonNull(begin)) {
            validationResult = begin.isBefore(sprint.getEndDate());
        } else if (Objects.nonNull(end) && Objects.isNull(begin)) {
            validationResult = end.isAfter(sprint.getBeginDate());
        } else if (Objects.nonNull(end)) {
            validationResult = end.isAfter(begin);
        } else {
            validationResult = true;
        }

        if (!validationResult) {
            throw new ValidationException("Date mismatch! Check dates");
        }

    }

}
