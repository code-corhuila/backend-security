package com.Corhuila.backend_security.Dto;

import java.time.LocalDate;

public interface IChartLossDateContract {
  LocalDate getEndDate();
  Long getQuantity();
}
