package com.apppassarourbano.AppPassaroUrbano.repository;

import java.util.List;
import java.util.Map;

public interface CustomRepository {
    List findAllByParams(Map<String,String> params);
}
