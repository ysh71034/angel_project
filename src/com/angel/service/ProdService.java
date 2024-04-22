package com.angel.service;

import com.angel.dto.ProdDTO;

import java.sql.Connection;
import java.util.List;

public class ProdService {
    private static ProdService service = new ProdService();
    public static ProdService getService() {
        return service;
    }
    private ProdService(){}


}
