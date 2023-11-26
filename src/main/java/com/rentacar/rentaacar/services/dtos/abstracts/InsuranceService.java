package com.rentacar.rentaacar.services.dtos.abstracts;

import com.rentacar.rentaacar.services.dtos.requests.Insurance.AddInsuranceRequest;
import com.rentacar.rentaacar.services.dtos.requests.Insurance.UpdateInsuranceRequest;
import com.rentacar.rentaacar.services.dtos.responses.Insurance.GetInsuranceListResponse;
import com.rentacar.rentaacar.services.dtos.responses.Insurance.GetInsuranceResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface InsuranceService {
    public List<GetInsuranceListResponse> getAll();
    public GetInsuranceResponse getById(@PathVariable int id);
    public void add(@RequestBody AddInsuranceRequest addInsuranceDto);
    public void update(@PathVariable int id, @RequestBody UpdateInsuranceRequest updateInsuranceDto);
    public void delete(@PathVariable int id, String areYouSure);
}
