package xyz.sangdam.counseling.controllers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestStatusChange {
    private List<Long> rno;
    private List<String> status;
}
