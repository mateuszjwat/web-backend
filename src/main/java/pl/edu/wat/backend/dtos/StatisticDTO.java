package pl.edu.wat.backend.dtos;

import lombok.Getter;

@Getter
public class StatisticDTO {
    private long setId;
    private String setName;
    private int goodAns;
    private int wrongAns;
}
