package com.hm2.ws.rest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CovidResponse {
    private String Confirmed;
    private String Recovered;
    private String Hospitalized;
    private String Deaths;
    private String NewConfirmed;
    private String NewRecovered;
    private String NewHospitalized;
    private String NewDeaths;
    private String UpdateDate;
    private String Source;
    private String DevBy;
    private String SeverBy;
}
