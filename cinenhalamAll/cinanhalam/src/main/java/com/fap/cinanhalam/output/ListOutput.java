package com.fap.cinanhalam.output;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ListOutput<T> {
    List<T> listResult = new ArrayList<>();
}
