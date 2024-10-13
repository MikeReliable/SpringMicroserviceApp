package com.mike.clientspringmicroserviceapp.service.interf;

import com.mike.clientspringmicroserviceapp.dto.RoastingLossDto;

public interface GrpcService {

    RoastingLossDto grpcRequest() throws NoSuchFieldException;
}
