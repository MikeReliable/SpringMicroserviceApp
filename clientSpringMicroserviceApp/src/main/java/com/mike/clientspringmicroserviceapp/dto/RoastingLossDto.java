package com.mike.clientspringmicroserviceapp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Coffee residues information")
public class RoastingLossDto {

    @Schema(description = "Raw coffee residues, g")
    private int weightRaw;
    @Schema(description = "Roast coffee residues, g")
    private int weightRoast;
    @Schema(description = " Weight loss, %")
    private float weightLoss;
}
