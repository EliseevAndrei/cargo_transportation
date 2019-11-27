package com.eliseev.app.repository.custom;

import com.eliseev.app.models.Map;
import com.eliseev.app.repository.IDAO;

import java.util.List;

public interface MapDAO extends IDAO<Map> {

    List<Map> list(long routeId);

}

