package com.lxlt.service;

import com.lxlt.bean.Region;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: Lucas_Alison
 * Date: 2020/5/28 18:41
 */


public class RegionServiceImplTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void queryAllRegionAndCategorize() {
        RegionServiceImpl regionService = new RegionServiceImpl();
        List<Region> regionList = regionService.queryAllRegionAndCategorize();
        System.out.println(regionList);

    }
}